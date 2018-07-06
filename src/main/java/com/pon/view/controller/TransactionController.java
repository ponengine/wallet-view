package com.pon.view.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.JSONObject;
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
import com.pon.view.dto.DaySearch;
import com.pon.view.dto.TransactionUserDTO;
import com.pon.view.dto.TransactionWalletDTO;
import com.pon.view.service.TransactionService;



@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionservice;
	//Admin
	@GetMapping("/admin/gettranwalletall")
	public List<TransactionWalletDTO> getTransactionAll(){
		return transactionservice.getalltransaction();	
	}
	@GetMapping("/admin/gettranwallettoday")
	public List<TransactionWalletDTO> getTransactionToDay(){
		return transactionservice.gettransactiontoday();			
	}
	@PostMapping("/admin/gettranwalletwithsearch")
	public List<TransactionWalletDTO> getBySearch(@RequestBody DaySearch daysearch){
		return transactionservice.gettranwithsearch(daysearch);
	}
	//User
	@GetMapping("/user/gettranwallettoday/{username}")
	public List<TransactionWalletDTO> getTransactionByUserToday(@PathVariable String username){
		return transactionservice.gettransactionbyusertoday(username);
	}
	@GetMapping("/user/getalltranwallet/{username}")
	public List<TransactionWalletDTO> getTransactionByUserAll(@PathVariable("username") String username){
		return transactionservice.gettransactionbyuserall(username);
	}
	@PostMapping("/user/gettranwalletbysearch/{username}")
	public List<TransactionWalletDTO> getTransactionByUserSearch(@RequestBody DaySearch daysearch ,@PathVariable("username")String username){
		return transactionservice.gettransactionbyusersearch(daysearch,username);
	}
	@GetMapping("/admin/getuserall")
	public List<TransactionUserDTO> getUserAll(){
		return transactionservice.getuserall();
	}
	@GetMapping("/admin/getusertoday")
	public List<TransactionUserDTO> getUserToDay(){
		return transactionservice.getusertoday();	
	}
}
