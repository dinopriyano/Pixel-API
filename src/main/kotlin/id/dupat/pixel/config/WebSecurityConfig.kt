package id.dupat.pixel.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable()
            .authorizeRequests()
            .antMatchers( "/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

}