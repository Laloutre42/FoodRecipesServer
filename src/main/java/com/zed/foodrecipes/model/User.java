package com.zed.foodrecipes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Arnaud on 03/05/2015.
 */
@Data
@Document
public class User implements SocialUserDetails {

    @Id
    private String id;

    private String name;

    private String fullName;

    private String password;

    private String email;

    private UserRole role = UserRole.USER;

    private String providerId;

    private boolean accountLocked;

    public User(String name, String fullName, String email, String providerId) {
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.role = UserRole.USER;
        this.providerId = providerId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserRole[]{role});
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUserId() {
        return this.getId();
    }

}