package com.bookshop.util;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RandomUtil {

    public <T> List<T> random(List<T> list) {
        List<T> list1;
        if(list.size()>8){
            Map<Integer, T> map=new HashMap<>();
            list1=new ArrayList<>();
            int i = 0;
            for (T t : list) {
                map.put(i++, t);
            }
            Random rd = new Random();
            List<Integer> list11=new ArrayList<>();
            for (int j = 0;j<map.size()+50; j++) {
                int index = rd.nextInt(map.size());
                if(list11.size()>0){
                    if(check(list11,index)){
                        continue;
                    }
                }
                list11.add(index);
                list1.add(map.get(index));
                if (list1.size() == 8) {
                    break;
                }
            }
        }
        else {
            return list;
        }

        return list1;
    }
    public static boolean check(List<Integer> list,int k){
        for (Integer integer:list) {
            if (integer==k){
                return true;
            }
        }
        return false;
    }
}
