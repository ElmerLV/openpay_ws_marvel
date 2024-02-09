package com.openpay.marvel.services;

import com.openpay.marvel.entity.LoginEntity;
import com.openpay.marvel.entity.MarvelEntity;
import com.openpay.marvel.model.CharacterList;
import com.openpay.marvel.repository.LoginRepository;
import com.openpay.marvel.repository.MarvelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la implementación de charapters de Marvel
 *
 * @author Elmer LV
 * @enterprise OpenPay
 * @since 08/02/24
 */

@SpringBootTest
class MarvelServicesTest {

    @Mock
    private MarvelRepository marvelRepository;

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MarvelServiceImpl marvelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCharacters() {
        LoginEntity loginEntity = new LoginEntity(/* Parámetros de prueba */);
        Mockito.when(loginRepository.findByEmailAndPass(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(loginEntity));

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
                .thenReturn("{'name':'name','description':'description'}");

        CharacterList result = marvelService.getCharacters(1L, "elmer.lopezv@gmail.com", "123abc");

        assertNotNull(result);
    }

    @Test
    void testGetConsultas() {
        List<MarvelEntity> marvelEntities = new ArrayList<>();
        Mockito.when(marvelRepository.findAll()).thenReturn(marvelEntities);

        List<MarvelEntity> result = marvelService.getConsultas();

        assertNotNull(result);
        assertEquals(marvelEntities, result);
    }

}