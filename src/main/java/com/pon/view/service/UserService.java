package com.pon.view.service;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.pon.view.dto.ProfileDTO;
import com.pon.view.dto.WalletDTO;
import com.pon.view.model.AddUser;


@Service
public class UserService {
	@Autowired
	private Environment env;
	ModelMapper modelmapper = new ModelMapper();
	public String createuser(AddUser adduser) {
		final String uri = env.getProperty("user.uri")+"/usermanage/adduser";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MultiValueMap<String, Object> map =  new LinkedMultiValueMap<>();
		
		map.add("cityzenId", adduser.getCityzenId());
		map.add("firstName", adduser.getFirstName());
		map.add("lastName", adduser.getLastName());
		map.add("password", adduser.getPassword());
		map.add("pin", adduser.getPin());
		map.add("userName", adduser.getUsername());
		
		HttpEntity<String> entity = new HttpEntity<String>(map.toString() ,headers);
		String response = rt.postForObject( uri, entity , String.class );
		return response;
	}

	public ProfileDTO getprofile(String username) {
		final String uri1 = env.getProperty("user.uri")+"/usermanage/getuser/"+username;
		final String uri2 = env.getProperty("wallet.uri")+"/managewallet/walletget/"+username;
		
		RestTemplate rt = new RestTemplate();
		Object userinfo = rt.getForObject(uri1, Object.class );
		Object wallet = rt.getForObject( uri2, Object.class );
		ProfileDTO profiledto = modelmapper.map(userinfo, ProfileDTO.class);
		WalletDTO walletdto = modelmapper.map(wallet, WalletDTO.class);
		profiledto.setWalletDto(walletdto);
				
		return profiledto;
	}
}
