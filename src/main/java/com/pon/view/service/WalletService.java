package com.pon.view.service;




import java.math.BigDecimal;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pon.view.domain.BaseResponse;
import com.pon.view.domain.BaseRestApi;
import com.pon.view.dto.WalletDTO;




@Service
public class WalletService {

	@Autowired
	private Environment env;

	public String addmoneyservice(WalletDTO money) {
		String uri = env.getProperty("wallet.uri")+"/managewallet/addwallet";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 MultiValueMap<String,Object> map =  new LinkedMultiValueMap<>();

		 map.add("price", money.getMoney());
		 map.add("payer", money.getPayer());
		
		HttpEntity<String> entity = new HttpEntity<String>(map.toString() ,headers);
		String response = rt.postForObject( uri, entity , String.class );
		return response;
	}

	public String widrawWallet(WalletDTO walletDTO) {
		String uri = env.getProperty("wallet.uri")+"/managewallet/withdrawwallet";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 MultiValueMap<String,Object> map =  new LinkedMultiValueMap<>();

		 map.add("price", walletDTO.getMoney());
		 map.add("usernameBuyer", walletDTO.getPayer());
		
		HttpEntity<String> entity = new HttpEntity<String>(map.toString() ,headers);
		String response = rt.postForObject( uri, entity , String.class );
		return response;
	}

	public BaseRestApi exchangeWallet(WalletDTO walletDTO) {
		
		BaseRestApi checkwallet = checkwallet(walletDTO);
		if(!checkwallet.isSuccess()){
			return checkwallet;
		}
		String uri = env.getProperty("wallet.uri")+"/api/walletpocket/exchangewallet";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		obj.put("money", walletDTO.getMoney());
		obj.put("payer", walletDTO.getPayer());
		obj.put("receiver", walletDTO.getReceiver());	
		HttpEntity<String> entity = new HttpEntity<String>(obj.toString() ,headers);
		BaseRestApi response = rt.postForObject( uri, entity , BaseRestApi.class );
		return response;
	}

	public BaseRestApi checkwallet(WalletDTO walletDTO) {
		 BaseRestApi baseRestApi = new BaseRestApi();
		 BaseResponse<Map<String, Object>> baseResponse = new BaseResponse<>();
		String uri = env.getProperty("wallet.uri")+"/api/walletpocket/checkuserandwallet";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		obj.put("money", walletDTO.getMoney());
		obj.put("payer", walletDTO.getPayer());
		obj.put("receiver", walletDTO.getReceiver());	
		HttpEntity<String> entity = new HttpEntity<String>(obj.toString() ,headers);
		BaseRestApi response = rt.postForObject( uri, entity , BaseRestApi.class );
		return response;
	}

	public BaseRestApi canceltransaction(Long id,WalletDTO walletDTO) {
		String uri = env.getProperty("wallet.uri")+"/api/walletpocket//calceltransaction/"+id;
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		obj.put("money", walletDTO.getMoney());
		HttpEntity<String> entity = new HttpEntity<String>(obj.toString() ,headers);
		BaseRestApi response = rt.getForObject(uri, BaseRestApi.class);
		return response;
	}
}
