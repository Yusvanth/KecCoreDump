package com.keccoredump.demo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.keccoredump.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.keccoredump.demo.entity.Roles;

public class UserDetailsDto implements UserDetails{
	
	private int id;
    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    private List<Roles> roles;
    
    public UserDetailsDto(User user) {
    	this.id= user.getId();
    	this.email= user.getEmail();
    	this.password= user.getPassword();
    	this.active=true;
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        this.roles = user.getUserRoles();

        String[] authStrings = new String[roles.size()];
        for(int i=0;i<roles.size();i++)
        {
            authStrings[i]=roles.get(i).getRoles();
        }

        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }
        this.authorities=authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", authorities=" + authorities +
                ", roles=" + roles +
                '}';
    }
	
	
}
