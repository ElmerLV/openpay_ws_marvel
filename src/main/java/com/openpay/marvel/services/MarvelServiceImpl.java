package com.openpay.marvel.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openpay.marvel.entity.LoginEntity;
import com.openpay.marvel.entity.MarvelEntity;
import com.openpay.marvel.model.Character;
import com.openpay.marvel.model.CharacterList;
import com.openpay.marvel.repository.LoginRepository;
import com.openpay.marvel.repository.MarvelRepository;
import com.openpay.marvel.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

@Slf4j
@Service
public class MarvelServiceImpl implements MarvelService {

	@Autowired
	private MarvelRepository marvelRepository;
	@Autowired
	private LoginRepository loginRepository;
	private final RestTemplate restTemplate;

	@Autowired
	public MarvelServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public CharacterList getCharacters(Long id,
								String email,
								String pass) {

		Optional<LoginEntity> login = loginRepository.findByEmailAndPass(email, pass);

		String basePath = "https://gateway.marvel.com:443/v1/public/characters";

		if(id != null) {
			basePath = basePath + "/" + id;
		}

		String ts = "1";

		String json = getDataApiExtern(basePath, ts, login.get().getKeyPrivate(), login.get().getKeyPublic());

		List<Character> characterList = new ArrayList<>();
		CharacterList characters = new CharacterList();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(json);

			JsonNode resultsNode = rootNode.path("data").path("results");

			Character character;

			for (JsonNode characterNode : resultsNode) {
				character = new Character();
				character.setId(characterNode.path("id").asLong());
				character.setName(characterNode.path("name").asText());
				character.setDescription(characterNode.path("description").asText());

				String image = characterNode.path("thumbnail").path("path").asText()
						+ "." + characterNode.path("thumbnail").path("extension").asText();

				character.setImage(image);
				characterList.add(character);
				characters.setCharacters(characterList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		MarvelEntity marvelEntity = new MarvelEntity();
		marvelEntity.setHoraConsulta(LocalDateTime.now());
		marvelEntity.setBasePath(basePath);
		marvelRepository.save(marvelEntity);

        return characters;
	}

	@Override
	public List<MarvelEntity> getConsultas() {
		return marvelRepository.findAll();
	}

	public String getDataApiExtern(String basePath, String ts, String keyPrivate, String keyPublic) {

		String hash = MD5Util.encodeMD5(ts+keyPrivate+keyPublic);
		String apiUrl = basePath+"?ts="+ts+"&apikey="+keyPublic+"&hash="+hash;
		String response = restTemplate.getForObject(apiUrl, String.class);

		return response;
	}

}
