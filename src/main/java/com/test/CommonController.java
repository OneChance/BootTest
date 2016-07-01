package com.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
