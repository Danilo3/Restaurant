package ru.koryakin.dacha2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.koryakin.dacha2.domain.DachaUser;

import java.util.ArrayList;
import java.util.Collection;

public class DachaUserPrincipal implements UserDetails {

    private DachaUser dachaUser;

    public DachaUserPrincipal(DachaUser dachaUser) {
        this.dachaUser = dachaUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(dachaUser.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return dachaUser.getPassword();
    }

    @Override
    public String getUsername() {
        return dachaUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
