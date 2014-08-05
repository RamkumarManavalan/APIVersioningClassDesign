package com.example.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import com.example.model.User;
import com.example.util.ConnectionManager;

public abstract class UserController {
    private UserController previousInChain;

    public void setPreviousInChain(UserController next) {
        this.previousInChain = next;
    }

    public User process(String userid) {
        // Process
        Map<String, Object> qMap = new HashMap<String, Object>();
        qMap.put("id", userid);
        List<LinkedHashMap<String, Object>> docs = ConnectionManager.get().find(User.getTableName(), qMap);

        // Compose Response
        User user = new User();
        composeResponse(user, docs);
        return user;
    }

    public void composeResponse(User user, List<LinkedHashMap<String, Object>> docs) {
        if (previousInChain != null) {
            previousInChain.composeResponse(user, docs);
        }
    }
}
