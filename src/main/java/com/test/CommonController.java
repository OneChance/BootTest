package com.test;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	
	@RequestMapping("/api/check/username")
	public CheckRes check(Map<String, Object> model,HttpServletRequest request) {
		CheckRes data = new CheckRes();
		data.setUnique(true);
		String username = request.getParameter("value");
		
		if(username.equals("tom")||username.equals("tomjerry")){
			data.setUnique(false);
		}
		
		return data;
	}
	
	@RequestMapping(value="/api/account",method=RequestMethod.GET)
	public Object[] get(HttpServletRequest request) {
		return accountRepository.findAll().toArray();
	}
	
	@RequestMapping(value="/api/account",method=RequestMethod.POST)
	public Account save(Account account,HttpServletRequest request) {
		return accountRepository.save(account);
	}	
	
	@RequestMapping(value="/api/account",method=RequestMethod.DELETE)
	public void delete(Long id,HttpServletRequest request) {
		accountRepository.delete(id);
	}
	
	@Resource
	AccountRepository accountRepository;
}
