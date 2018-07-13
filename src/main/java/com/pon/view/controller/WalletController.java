package com.pon.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pon.view.domain.BaseRestApi;
import com.pon.view.dto.WalletDTO;
import com.pon.view.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	private WalletService walletservice;
	@PostMapping("/addwallet")
	public String addWallet(@RequestBody WalletDTO walletDTO){
		return walletservice.addmoneyservice(walletDTO);
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
	
	@PostMapping("/cancel/{id}")
	public BaseRestApi cancelTransaction(@PathVariable("id") Long id,WalletDTO walletDTO){
		return walletservice.canceltransaction(id,walletDTO);
	}

}
