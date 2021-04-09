package cn.gao.server.config.security;

import cn.gao.server.pojo.Admin;
import cn.gao.server.pojo.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Administrator
 * @Date 2021/3/26 22:12
 * @Created by Administrator
 */

public class AdminUserDetails  implements UserDetails {

    private Admin admin;
    private List<Role> rolse;

    public AdminUserDetails(Admin admin){
        this.admin=admin;
    }

    public AdminUserDetails(Admin admin,List<Role> rolse){
        this.admin=admin;
        this.rolse = rolse;
    }

    public Integer getId(){
        return admin.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority>  authorities = rolse
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
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
        return admin.getEnabled();
    }
}
