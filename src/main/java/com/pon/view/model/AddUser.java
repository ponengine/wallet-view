package com.pon.view.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddUser {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String cityzenId;
	private int pin;
}
