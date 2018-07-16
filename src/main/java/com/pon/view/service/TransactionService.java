package com.pon.view.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pon.view.dto.DaySearch;
import com.pon.view.dto.TransactionUserDTO;
import com.pon.view.dto.TransactionWalletDTO;



@Service
public class TransactionService {
	@Autowired
	private Environment env;

	public List<TransactionWalletDTO> getalltransaction() {
		String uri = env.getProperty("wallet.uri")+"/transaction/getalltran";
		RestTemplate rs = new RestTemplate();
		List<TransactionWalletDTO> tran = rs.getForObject(uri, List.class);
		return tran;		
	}

	public List<TransactionWalletDTO> gettransactiontoday() {
		String uri = env.getProperty("wallet.uri")+"/transaction/gettrantoday";
		RestTemplate rs = new RestTemplate();
		List<TransactionWalletDTO> tran = rs.getForObject(uri, List.class);
		return tran;
	}

	public List<TransactionWalletDTO> gettranwithsearch(DaySearch daysearch) {
		String uri = env.getProperty("wallet.uri")+"/transaction/getbysearch";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 JSONObject objstr = new JSONObject();
		 try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String st = daysearch.getStartDate().format(formatter);
			String en = daysearch.getEndDate().format(formatter);
			System.out.println(st);
		 objstr.put("stasrtDate", st);
		 objstr.put("endDate", en);
		 objstr.put("status", daysearch.getStatus());

		 }catch(Exception e){}
		HttpEntity<String> entity = new HttpEntity<String>(objstr.toString() ,headers);
		 System.out.println("55555555");
		List<TransactionWalletDTO> response = rt.postForObject(uri,entity, List.class );
		return response;	
	}

	public List<TransactionWalletDTO> gettransactionbyusertoday(String username) {
		String uri = env.getProperty("wallet.uri")+"/transaction/getusertrantoday";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 JSONObject objstr = new JSONObject();
		 try{
		 objstr.put("usernameBuyer", username);
		 }catch(Exception e){}
		HttpEntity<String> entity = new HttpEntity<String>(objstr.toString() ,headers);
		List<TransactionWalletDTO> response = rt.postForObject( uri, entity , List.class );
		return response;	
	}

	public List<TransactionWalletDTO> gettransactionbyuserall(String username) {
		String uri = env.getProperty("wallet.uri")+"api/transaction/user/getallbyuser/" + username;
		System.out.println(">>>>>" + uri);
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 JSONObject objstr = new JSONObject();
		 try{
		 objstr.put("usernameBuyer", username);
		 }catch(Exception e){}
		HttpEntity<String> entity = new HttpEntity<String>(objstr.toString() ,headers);
		List<TransactionWalletDTO> response = rt.getForObject( uri, List.class );
		return response;	
	}

	public List<TransactionWalletDTO> gettransactionbyusersearch(DaySearch daysearch, String username) {
		String uri = env.getProperty("wallet.uri")+"/transaction/getbysearchwithuser";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 JSONObject objstr = new JSONObject();
		 try{
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String st = daysearch.getStartDate().format(formatter);
				String en = daysearch.getEndDate().format(formatter);
		 objstr.put("usernameBuyer", username);
		 objstr.put("stasrtDate",st);
		 objstr.put("endDate", en);
		 objstr.put("status", daysearch.getStatus());
		 }catch(Exception e){}
		HttpEntity<String> entity = new HttpEntity<String>(objstr.toString() ,headers);
		List<TransactionWalletDTO> response = rt.postForObject( uri, entity , List.class );
		return response;	
	}

	public List<TransactionUserDTO> getuserall() {
		String uri = env.getProperty("user.uri")+"/report/showuserall";
		RestTemplate rs = new RestTemplate();
		List<TransactionUserDTO> tran = rs.getForObject(uri, List.class);
		return tran;
	}

	public List<TransactionUserDTO> getusertoday() {
		String uri =env.getProperty("user.uri")+"/report/showusertoday";
		RestTemplate rs = new RestTemplate();
		List<TransactionUserDTO> tran = rs.getForObject(uri, List.class);
		return tran;
	}
}
