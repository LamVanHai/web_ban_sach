package com.bookshop.service.impl;

import com.bookshop.entity.User_group;
import com.bookshop.repository.User_GroupRepository;
import com.bookshop.service.User_groupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class User_groupImpl implements User_groupService {

    @Autowired
    private User_GroupRepository user_groupRepository;
    @Override
    public Map<Long, String> findAll() {
        Map<Long,String> map=new ConcurrentHashMap<>();
        List<User_group>  user_groups=user_groupRepository.findAll();
        for (User_group user_group:user_groups){
            map.put(user_group.getId(),user_group.getName());
        }
        return map;
    }
}
