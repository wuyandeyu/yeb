package cn.gao.server.config.security.filter;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Description:
 *
 * @Date 2021/4/7 21:50
 * @Created by Administrator
 */
@Component
public class CustomUrlDecisionManger  implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for(ConfigAttribute configAttribute : collection){
            //当前url所需的角色，根据路径去查出来的角色，从CustomFilter过滤器中查询出来的
            String needRole =configAttribute.getAttribute();
            //判断角色是否是登录即刻访问的的角色，
            if("ROLE_LOGIN".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请先登录");
                }else{
                    return;
                }
            }
            //判断用户橘色是否是url所需的角色。此处的角色使用用户登录后存到userdetails中的去角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority :authorities){
                //System.out.println(authority.getAuthority().equals(needRole));
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }

        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
