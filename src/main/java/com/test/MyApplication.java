package com.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class MyApplication {

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new MySecurityConfigurer();
	}

	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	public static class MySecurityConfigurer extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			/*
			 * builder.inMemoryAuthentication().withUser("user").password("user"
			 * ).roles("USER").and().withUser("admin")
			 * .password("admin").roles("ADMIN");
			 */
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

			// custom login
			/*
			 * http.authorizeRequests().antMatchers("/fonts/**").permitAll().
			 * antMatchers("/img/**").permitAll()
			 * .anyRequest().authenticated().and().formLogin().loginPage(
			 * "/login").permitAll();
			 */
		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return slr;
	}

	@Bean
	public static LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new HandlerInterceptorAdapter() {

				@Override
				public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
						throws Exception {
					request.getContextPath();

					/*String local = "";

					Locale locale = LocaleContextHolder.getLocale();

					if (local != null && !local.equals("")) {
						if (local.equals("zh")) {
							locale = new Locale("zh", "CN");
						} else if (local.equals("en")) {
							locale = new Locale("en", "US");
						}
					}

					request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
*/
					return true;
				}
			}).addPathPatterns("/*");

			registry.addInterceptor(localeChangeInterceptor());
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
}
