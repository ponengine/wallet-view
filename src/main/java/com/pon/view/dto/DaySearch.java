package com.pon.view.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DaySearch {
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
}
