package com.test;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("file://");
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/api/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
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

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				request.getContextPath();
				//request.setCharacterEncoding("UTF-8");
				//response.setCharacterEncoding("UTF-8");
				return true;
			}
		}).addPathPatterns("/*").addPathPatterns("/api/**");

		registry.addInterceptor(localeChangeInterceptor());
	}

	/*
	 * @Bean public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter()
	 * { return new MySecurityConfigurer(); }
	 * 
	 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) public static class
	 * MySecurityConfigurer extends WebSecurityConfigurerAdapter {
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder builder)
	 * throws Exception {
	 * 
	 * builder.inMemoryAuthentication().withUser("user").password("user").roles(
	 * "USER").and().withUser("admin") .password("admin").roles("ADMIN");
	 * 
	 * }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * //http.antMatcher("/**").authorizeRequests().anyRequest().authenticated()
	 * ; //access denied
	 * //http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
	 * ; //basic check // custom login check page
	 * http.authorizeRequests().antMatchers("/fonts/**").permitAll().antMatchers
	 * ("/img/**").permitAll()
	 * .anyRequest().authenticated().and().formLogin().loginPage("/login").
	 * permitAll();
	 * 
	 * } }
	 */
}
