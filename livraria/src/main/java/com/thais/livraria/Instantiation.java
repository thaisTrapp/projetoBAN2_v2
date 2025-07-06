package com.thais.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.thais.livraria.domain.Livro;
import com.thais.livraria.repository.LivroRepository; 

@Configuration 
public class Instantiation implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Override
public void run(String... args) throws Exception {

    livroRepository.deleteAll();

    Livro livro1 = new Livro(
        "Aventuras de Sherlock Holmes", // titulo
        101,                           // idAutor
        201,                           // idEditora
        1892,                          // anoPublicacao
        "Mistério",                    // genero (agora o 5º parâmetro, como na chamada)
        35.50,                         // valor
        10                             // quantidade
    );

    Livro livro2 = new Livro(
        "Cem Anos de Solidão",         // titulo
        102,                           // idAutor
        202,                           // idEditora
        1967,                          // anoPublicacao
        "Realismo Mágico",             // genero
        49.90,                         // valor
        7                              // quantidade
    );

    Livro livro3 = new Livro(
        "1984",                        // titulo
        103,                           // idAutor
        203,                           // idEditora
        1949,                          // anoPublicacao
        "Distopia",                    // genero
        29.99,                         // valor
        15                             // quantidade
    );

    livroRepository.save(livro1);
    livroRepository.save(livro2);
    livroRepository.save(livro3);

    System.out.println("Livros de exemplo inseridos no MongoDB!");
}
}