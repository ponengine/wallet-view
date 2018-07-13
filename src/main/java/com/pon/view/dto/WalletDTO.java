package com.pon.view.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDTO {
	private Long id;
	private double money;
	private String payer;
	private String receiver;
	private String note;
	private String usernameadmin;
}
