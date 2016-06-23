package com.test;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class MyController {
	@RequestMapping("/")
	public String index(HttpServletRequest request) {

		String name = "";

		// name = accountService.getAccount().get(0).getName();

		name = accountRepository.findAll().get(0).getName();

		request.setAttribute("user", name);
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/en")
	public void en(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Locale locale = new Locale("en", "US");
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		response.sendRedirect("/");
	}

	@RequestMapping("/zh")
	public void zh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Locale locale = new Locale("zh", "CN");
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		response.sendRedirect("/");
	}

	@Resource
	AccountService accountService;
	@Resource
	AccountRepository accountRepository;
}
