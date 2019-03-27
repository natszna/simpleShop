package pl.natalia.simpleShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.natalia.simpleShop.model.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/public/**").permitAll()
                .antMatchers("/user/**").hasRole(User.Role.USER.name())
                .antMatchers("/users/**").hasRole(User.Role.ADMIN.name())
                .and().csrf().disable()
                .httpBasic().and().logout().and().formLogin().loginPage("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("user").roles(User.Role.USER.name()).and()
                .withUser("admin").password("admin").roles(User.Role.ADMIN.name(), User.Role.USER.name());
    }
}

