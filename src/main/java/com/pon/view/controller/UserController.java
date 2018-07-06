package com.pon.view.controller;

import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pon.view.dto.ProfileDTO;
import com.pon.view.model.AddUser;
import com.pon.view.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping("/add")
	public String adduser(@RequestBody AddUser adduser){
		return userservice.createuser(adduser);
	}
	@GetMapping("/getprofile/{username}")
	public ProfileDTO getProfile(@PathVariable("username") String username){
		return userservice.getprofile(username);
	}

}
