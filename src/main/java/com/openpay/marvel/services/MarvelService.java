package com.openpay.marvel.services;

import com.openpay.marvel.entity.MarvelEntity;
import com.openpay.marvel.model.Character;

import java.util.List;

/**
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

public interface MarvelService {

	List<Character> getCharacters(Long id,
								  String email,
								  String pass);

	List<MarvelEntity> getConsultas();

}
