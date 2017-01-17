package com.projectx.cwm.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    List<GrantedAuthority> authorities;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public JwtAuthenticationResponse(String token, Collection<? extends GrantedAuthority> authorities) {
    	this.token = token;
    	this.authorities=new ArrayList<>();
    	this.authorities.addAll(authorities);
	}

	public String getToken() {
        return this.token;
    }

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
