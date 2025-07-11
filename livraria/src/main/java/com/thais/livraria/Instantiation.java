package com.thais.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.ObjectId; // IMPORTANTE: necessário para criar ObjectId

import com.thais.livraria.domain.Livro;
import com.thais.livraria.repository.LivroRepository;
import com.thais.livraria.domain.Autor;
import com.thais.livraria.repository.AutorRepository;
import com.thais.livraria.domain.Editora;
import com.thais.livraria.repository.EditoraRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void run(String... args) throws Exception {

        livroRepository.deleteAll();
        autorRepository.deleteAll();
        editoraRepository.deleteAll();

        // Inserção de autores
        Autor autor1 = new Autor("Arthur Conan Doyle", "Britânica");
        Autor autor2 = new Autor("Gabriel García Márquez", "Colombiana");
        autorRepository.saveAll(List.of(autor1, autor2));

        // Inserção de editoras
        Editora editora1 = new Editora("Companhia das Letras");
        Editora editora2 = new Editora("HarperCollins Brasil");
        editoraRepository.saveAll(List.of(editora1, editora2));

        // Inserção de livros usando ObjectId
        Livro livro1 = new Livro(
            "Aventuras de Sherlock Holmes",
            new ObjectId(autor1.getId()),     // Convertendo String para ObjectId
            new ObjectId(editora1.getId()),   // Convertendo String para ObjectId
            1892,
            "Mistério",
            35.50,
            10
        );

        Livro livro2 = new Livro(
            "Cem Anos de Solidão",
            new ObjectId(autor2.getId()),
            new ObjectId(editora2.getId()),
            1967,
            "Realismo Mágico",
            49.90,
            7
        );

        livroRepository.saveAll(List.of(livro1, livro2));

        System.out.println("Dados de exemplo (Livros, Autores e Editoras) inseridos no MongoDB!");
    }
}
