package com.lgp.springsecurity.config;

import com.lgp.springsecurity.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/18 14:21
 * @DESCRIPTION
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。
     * 2.我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
     */
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserService());
    }

    /**
     * 3.在configure(HttpSecurity http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。
     * 4.permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
     * 5.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，我可以这样来写：.
     *          antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也
     * 可以设置admin文件夹下的文件可以有多个角色来访问，写法如下：
     *         antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
     * 6.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，写法如下.
     *          antMatchers("/admin/**").hasIpAddress("210.210.210.210")
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
//                留意代码顺序
//                首页（不需要登陆就能访问的页面
                .antMatchers("/index").permitAll()
                .anyRequest().authenticated()
//                需要登陆才能访问的页面
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
                .and().rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).key("kkkkkkkk");
    }
}
