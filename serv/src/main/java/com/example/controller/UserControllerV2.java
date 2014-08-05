package com.example.controller;

import java.util.List;
import java.util.LinkedHashMap;
import com.example.model.User;

/**
 * This version of User returns country too in the response
*/
public class UserControllerV2 extends UserController {
    @Override
    public void composeResponse(User user, List<LinkedHashMap<String, Object>> docs) {
        if ((user != null) && (docs.size() == 1)) {
                user.country = (String)docs.get(0).get("country");
        }
        super.composeResponse(user, docs);
    }
}
