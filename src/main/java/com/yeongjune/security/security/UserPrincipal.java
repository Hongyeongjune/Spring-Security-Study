package com.yeongjune.security.security;

import com.yeongjune.security.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        //Extract list of permissions (name)
        this.user.getPermissionList().forEach(action -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(action);
            authorities.add(grantedAuthority);
        });

        //Extract list of roles (ROLE_name)
        this.user.getRoleList().forEach(action -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + action);
            authorities.add(grantedAuthority);

        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getActive() == 1;
    }
}
