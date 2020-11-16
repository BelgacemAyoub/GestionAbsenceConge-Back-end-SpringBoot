package de.tekup.gca.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HolidaysCalendar {

	private LocalDate date;
	private String title;
	
	public HolidaysCalendar(LocalDate date, String title) {
		super();
		this.date = date;
		this.title = title;
	}
	
	
	
}
