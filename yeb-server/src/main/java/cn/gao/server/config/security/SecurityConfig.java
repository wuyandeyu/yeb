package cn.gao.server.config.security;

import cn.gao.server.pojo.Admin;
import cn.gao.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Description:
 *
 * @author Administrator
 * @Date 2021/3/27 14:52
 * @Created by Administrator
 */
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private CustomUrlDecisionManger customUrlDecisionManger;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "index.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs/**",
                "/captcha"
        );

    }

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf()
               .disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               .anyRequest()
               .authenticated()
               .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                   @Override
                   public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                       o.setAccessDecisionManager(customUrlDecisionManger);
                       o.setSecurityMetadataSource(customFilter);
                       return o;
                   }
               })
               .and()
               .headers()
               .cacheControl();
       http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
       http.exceptionHandling()
               .accessDeniedHandler(restfulAccessDeniedHandler)
               .authenticationEntryPoint(restAuthorizationEntryPoint);
    }
    //authenticate 认证   Authorization授权    authorize   授权之前先得认证
    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username->{
            Admin admin = adminService.getAdminByUserName(username);
            if(null!= admin){
                admin.setRoles(adminService.getRoles(admin.getId()));
                return new AdminUserDetails(admin) ;
            }
            throw  new UsernameNotFoundException("用户名和密码不正确");
        };
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }
}
