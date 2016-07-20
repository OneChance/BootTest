package com.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Account> get(HttpServletRequest request) {
		return accountRepository.findAll();
	}
	
	@RequestMapping(value="/api/account/{id}",method=RequestMethod.GET)	
	public Account getById(@PathVariable Long id,HttpServletRequest request) {
		return accountRepository.findById(id);
	}
	
	@RequestMapping(value="/api/account",method=RequestMethod.POST)
	public Account save(@RequestBody Account account,HttpServletRequest request) {
		return accountRepository.save(account);
	}	
	
	@RequestMapping(value="/api/account/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id,HttpServletRequest request) {
		accountRepository.delete(id);
	}
	
	@RequestMapping(value="/api/account/{id}",method=RequestMethod.PUT)
	public void update(@RequestBody Account account,HttpServletRequest request) {
		accountRepository.save(account);
	}
	
	@Resource
	AccountRepository accountRepository;
}
