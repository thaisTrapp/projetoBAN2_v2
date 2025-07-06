package com.thais.livraria.resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thais.livraria.domain.Livro;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
    

@RestController
@RequestMapping(value = "/livros")

public class LivrosResources {

    @RequestMapping(method = RequestMethod.GET)
       
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livros = List.of(
            new Livro("O Senhor dos Anéis", 1, 1, 1954, 1, 10, 39.90, "Fantasia"),
            new Livro("1984", 2, 2, 1949, 2, 5, 29.90, "Distopia"),
            new Livro("Dom Casmurro", 3, 3, 1899, 3, 8, 19.90, "Romance"),
            new Livro("A Revolução dos Bichos", 4, 4, 1945, 4, 6, 24.90, "Fábula")  
       
       
        );
        return ResponseEntity.ok().body(livros);     
    }
        

}
