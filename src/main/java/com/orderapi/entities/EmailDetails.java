package com.orderapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
	
	private String to;
    private String msgBody;
    private String subject;

}
