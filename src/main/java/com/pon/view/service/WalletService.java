package com.pon.view.service;



import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.pon.view.dto.Money;



@Service
public class WalletService {

	@Autowired
	private Environment env;

	public String addmoneyservice(Money money) {
		String uri = env.getProperty("wallet.uri")+"/managewallet/addwallet";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 MultiValueMap<String,Object> map =  new LinkedMultiValueMap<>();

		 map.add("price", money.getMoney());
		 map.add("usernameBuyer", money.getUsername());
		
		HttpEntity<String> entity = new HttpEntity<String>(map.toString() ,headers);
		String response = rt.postForObject( uri, entity , String.class );
		return response;
	}
}
