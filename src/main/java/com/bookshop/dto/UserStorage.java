package com.bookshop.dto;

import java.util.*;

public class UserStorage {

    private static UserStorage instance;
    private static Set<String> users;
    private static Map<String, List<MessageResponse>> listMessage;
    private static List<MessageResponse> list;

    private UserStorage() {
        users = new HashSet<>();
        users.add("admin");
        listMessage=new HashMap<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if(userName.equals("admin")){
            users.remove(userName);
        }
        if (users.contains(userName)) {
            throw new Exception("User aready exists with userName: " + userName);
        }
        users.add(userName);
    }


    public static void setListMessage(String fromLogin, MessageResponse messageResponse) {
        list=new ArrayList<>();
        if(check(fromLogin)){
            list=listMessage.get(fromLogin);
        }
        list.add(messageResponse);
        listMessage.put(fromLogin,list);

    }
    public static boolean check(String fromLogin){
        for (Map.Entry<String,List<MessageResponse>> map: listMessage.entrySet()
             ) {
            if(fromLogin.equals(map.getKey())){
                return true;
            }
        }
        return false;
    }

    public static List<MessageResponse> getMessage(String fromLogin){
        List<MessageResponse> list1=new ArrayList<>();
        if(check(fromLogin)){
            list1=listMessage.get(fromLogin);
        }
        return list1;
    }
    public static void deleteByUserName(String username){
        users.remove(username);
    }

    public static void deleteMessageByUserName(String username){
        listMessage.get(username).clear();
    }

}
