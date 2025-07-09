package com.thais.livraria.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thais.livraria.domain.Autor;

@Repository
public interface AutorRepository extends MongoRepository<Autor, String> {
     Optional<Autor> findByNome(String nome);
}