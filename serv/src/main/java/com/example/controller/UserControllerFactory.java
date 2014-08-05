package com.example.controller;

public class UserControllerFactory {
    private static UserControllerV1 v1;
    private static UserControllerV2 v2;


    // set the chain of responsibility
    static {
        v1 = new UserControllerV1();
        v2 = new UserControllerV2();
        v1.setPreviousInChain(null);
        v2.setPreviousInChain(v1);
    }

    public static UserController getController(String version) {
        if (version.equals("v2.0.0")) {
            return v2;
        }
        return v1; // default, even if version is empty or of invalid value
    }
}
