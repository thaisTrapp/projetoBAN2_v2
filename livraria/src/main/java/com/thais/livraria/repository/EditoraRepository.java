package com.thais.livraria.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thais.livraria.domain.Editora;

@Repository
public interface EditoraRepository extends MongoRepository<Editora, String> {
     Optional<Editora> findByNome(String nome);
}