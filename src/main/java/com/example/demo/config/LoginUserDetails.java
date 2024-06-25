package com.example.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LoginUserDetails implements UserDetails {
    private final Integer userid;
    private final String name;
    private final Integer employeeNumber;
    private final Integer departmentid;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String email;
    private final String password;

    public LoginUserDetails(User user) {
        this.userid = user.getUserid();
        this.name = user.getName();
        this.employeeNumber = user.getEmployeeNumber();
        this.departmentid = user.getDepartmentid();
        this.authorities = Arrays.stream(user.getRoles().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Integer getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }
    
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }
    
    public Integer getDepartmentid() {
        return departmentid;
    }

    // 他のgetterメソッドも必要に応じて追加

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
