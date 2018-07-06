package com.pon.view.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pon.view.dto.Money;
import com.pon.view.dto.WalletDTO;
import com.pon.view.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	private WalletService walletservice;
	@PostMapping("/addwallet")
	public String addWallet(@RequestBody Money money){
		return walletservice.addmoneyservice(money);
	}
	
	@PostMapping("/widraw")
	public String widrawWallet(@RequestBody WalletDTO walletDTO){
		return walletservice.widrawWallet(walletDTO);
	}
	
	@PostMapping("/exchange")
	public String exchangeWallet(@RequestBody WalletDTO walletDTO){
		return walletservice.exchangeWallet(walletDTO);
	}

}
