package com.orderapi.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
public class OrderDTO {
	
	private Long orderId;
	private Long userId;
	private Long itemId;
	private String userName;
	private String state;

}
