package com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountService {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AccountService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Account> getAccount() {
		return jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
	}
}
