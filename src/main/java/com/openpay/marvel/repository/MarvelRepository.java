package com.openpay.marvel.repository;

import com.openpay.marvel.entity.MarvelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelRepository extends JpaRepository<MarvelEntity, Long> {
}
