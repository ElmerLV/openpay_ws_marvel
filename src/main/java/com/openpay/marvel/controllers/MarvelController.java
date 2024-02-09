package com.openpay.marvel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openpay.marvel.entity.LoginEntity;
import com.openpay.marvel.services.MarvelService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

/**
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/marvel")
public class MarvelController {

	@Autowired
	private MarvelService marvelService;

	@GetMapping("/characters")
	public @ResponseBody Object getCharacters(@RequestParam(name = "id", required = false) Long id,
											  @RequestHeader("Authorization") String authorization)
			throws JsonProcessingException {

		byte[] decodedBytes = Base64.getDecoder().decode(authorization);
		String decodedString = new String(decodedBytes);

		ObjectMapper objectMapper = new ObjectMapper();
		LoginEntity loginEntity = objectMapper.readValue(decodedString, LoginEntity.class);

		return this.marvelService.getCharacters(id, loginEntity.getEmail(), loginEntity.getPass());
	}

	@GetMapping("/consultas")
	public @ResponseBody Object getConsultas() {

		return this.marvelService.getConsultas(); 
	}
}
