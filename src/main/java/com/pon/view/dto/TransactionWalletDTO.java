package com.pon.view.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionWalletDTO {
	private Long id;
	private String createDate;
	private int  moneyWallet;
	private String status;
	private String usernameBuyer;
	private String usernameSeller;
	
	private int  money;
}
