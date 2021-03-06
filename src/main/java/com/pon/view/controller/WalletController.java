package com.pon.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pon.view.domain.BaseRestApi;
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
	public BaseRestApi exchangeWallet(@RequestBody WalletDTO walletDTO){
		return walletservice.exchangeWallet(walletDTO);
	}
	@PostMapping("/checkwallet")
	public BaseRestApi checkWallet(@RequestBody WalletDTO walletDTO){
		return walletservice.checkwallet(walletDTO);
	}
	@GetMapping("/walletget/{username}")
	public WalletDTO getwallet(@PathVariable("username") String username){
		return walletservice.getwalletuser(username);
	}
}
