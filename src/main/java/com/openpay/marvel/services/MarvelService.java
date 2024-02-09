package com.openpay.marvel.services;

import com.openpay.marvel.entity.MarvelEntity;

import java.util.List;

/**
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

public interface MarvelService {

	Object getCharacters(Long id,
								String email,
								String pass);

	List<MarvelEntity> getConsultas();

}
