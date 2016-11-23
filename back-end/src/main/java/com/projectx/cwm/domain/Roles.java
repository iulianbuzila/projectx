package com.projectx.cwm.domain;

/**
 * Created by sl0 on 11/24/16.
 */
public enum Roles {
    USER, MANAGER, CONTRIBUTOR, ADMIN;

    public static String[] toStringArray() {
        String[] roles = new String[Roles.values().length];
        int k = 0;
        for (Roles r :
                Roles.values()) {
            roles[k] = r.toString();
            k+=1;
        }
        return roles;
    }
}
