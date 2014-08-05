package com.example.controller;

import java.util.List;
import java.util.LinkedHashMap;
import com.example.model.User;

/**
 * This version of User returns id and name of user in the response
*/
public class UserControllerV1 extends UserController {
    @Override
    public void composeResponse(User user, List<LinkedHashMap<String, Object>> docs) {
        if ((user != null) && (docs.size() == 1)) {
                user.id = (String)docs.get(0).get("id");
                user.name = (String)docs.get(0).get("name");
        }
        super.composeResponse(user, docs);
    }
}
