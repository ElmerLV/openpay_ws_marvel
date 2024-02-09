package com.openpay.marvel.model;

import lombok.*;

/**
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
	
	  private Long id;
	  private String name;
	  private String description;
	  private String image;

}


