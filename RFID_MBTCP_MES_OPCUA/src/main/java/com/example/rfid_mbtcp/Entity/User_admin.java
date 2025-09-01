package com.example.rfid_mbtcp.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class User_admin implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser_admin;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)

    private String password;
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
    public Long getIdUser_admin() {
        return idUser_admin;
    }

    public void setIdUser_admin(Long idUser_admin) {
        this.idUser_admin = idUser_admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER")); // Все пользователи имеют право "USER"
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User_admin() {
    }

    public User_admin(Long idUser_admin, String username, String password) {
        this.idUser_admin = idUser_admin;
        this.username = username;
        this.password = password;
    }
}
