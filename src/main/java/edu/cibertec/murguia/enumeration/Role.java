package edu.cibertec.murguia.enumeration;

import static edu.cibertec.murguia.constant.Authority.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    //Constructor de la enumeración
    Role(String... authorities){
        this.authorities=authorities;
    }

    //Método para obtener las autoridades
    public String[] getAuthorities(){
        return authorities;
    }
}
