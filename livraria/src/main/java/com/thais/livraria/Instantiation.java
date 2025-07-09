package com.thais.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thais.livraria.domain.Livro;
import com.thais.livraria.repository.LivroRepository;
import com.thais.livraria.domain.Autor; 
import com.thais.livraria.repository.AutorRepository; 

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired 
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {

        livroRepository.deleteAll();
        autorRepository.deleteAll();
        //Livros
        Livro livro1 = new Livro(
            "Aventuras de Sherlock Holmes",  
            101,                           
            201,                            
            1892,                          
            "Mistério",                    
            35.50,                         
            10                             
        );

        Livro livro2 = new Livro(
            "Cem Anos de Solidão",
            102,
            202,
            1967,
            "Realismo Mágico",
            49.90,
            7
        );

        Livro livro3 = new Livro(
            "1984",
            103,
            203,
            1949,
            "Distopia",
            29.99,
            15
        );

      /*   livroRepository.saveAll(List.of(livro1, livro2, livro3)); // Salva todos de uma vez

        // --- Inserção de Autores ---
        Autor autor1 = new Autor(
            "Arthur Conan Doyle", 
            "Britânica"           
        );

        Autor autor2 = new Autor(
            "Gabriel García Márquez", 
            "Colombiana"              
        );

        Autor autor3 = new Autor(
            "George Orwell",      
            "Britânica"           
        );

        autorRepository.saveAll(List.of(autor1, autor2, autor3)); // Salva todos de uma vez
*/
        System.out.println("Dados de exemplo (Livros e Autores) inseridos no MongoDB!");
    }
}