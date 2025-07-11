package com.thais.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.ObjectId;

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

        // Autores
        Autor autor1 = new Autor("Arthur Conan Doyle", "Britânica");
        Autor autor2 = new Autor("Gabriel García Márquez", "Colombiana");
        Autor autor3 = new Autor("J.K. Rowling", "Britânica");
        Autor autor4 = new Autor("Machado de Assis", "Brasileira");
        autorRepository.saveAll(List.of(autor1, autor2, autor3, autor4));

        // Editoras
        Editora editora1 = new Editora("Companhia das Letras");
        Editora editora2 = new Editora("HarperCollins Brasil");
        Editora editora3 = new Editora("Rocco");
        editoraRepository.saveAll(List.of(editora1, editora2, editora3));

        // Livros
        List<Livro> livros = List.of(
            new Livro("Aventuras de Sherlock Holmes", new ObjectId(autor1.getId()), new ObjectId(editora1.getId()), 1892, "Mistério", 35.50, 10),
            new Livro("O Cão dos Baskervilles", new ObjectId(autor1.getId()), new ObjectId(editora1.getId()), 1902, "Mistério", 38.00, 5),
            new Livro("Cem Anos de Solidão", new ObjectId(autor2.getId()), new ObjectId(editora2.getId()), 1967, "Realismo Mágico", 49.90, 7),
            new Livro("Crônica de uma Morte Anunciada", new ObjectId(autor2.getId()), new ObjectId(editora2.getId()), 1981, "Drama", 39.90, 4),
            new Livro("Harry Potter e a Pedra Filosofal", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 1997, "Fantasia", 59.90, 12),
            new Livro("Harry Potter e a Câmara Secreta", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 1998, "Fantasia", 61.90, 11),
            new Livro("Harry Potter e o Prisioneiro de Azkaban", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 1999, "Fantasia", 65.00, 8),
            new Livro("Dom Casmurro", new ObjectId(autor4.getId()), new ObjectId(editora1.getId()), 1899, "Romance", 29.90, 6),
            new Livro("Memórias Póstumas de Brás Cubas", new ObjectId(autor4.getId()), new ObjectId(editora1.getId()), 1881, "Romance", 28.90, 5),
            new Livro("Quincas Borba", new ObjectId(autor4.getId()), new ObjectId(editora1.getId()), 1891, "Romance", 31.00, 3),
            new Livro("Harry Potter e o Cálice de Fogo", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 2000, "Fantasia", 70.00, 9),
            new Livro("Harry Potter e a Ordem da Fênix", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 2003, "Fantasia", 75.00, 8),
            new Livro("Harry Potter e o Enigma do Príncipe", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 2005, "Fantasia", 78.90, 10),
            new Livro("Harry Potter e as Relíquias da Morte", new ObjectId(autor3.getId()), new ObjectId(editora3.getId()), 2007, "Fantasia", 82.00, 7),
            new Livro("O Alienista", new ObjectId(autor4.getId()), new ObjectId(editora2.getId()), 1882, "Sátira", 24.90, 6),
            new Livro("O Espelho", new ObjectId(autor4.getId()), new ObjectId(editora2.getId()), 1882, "Conto", 22.00, 4),
            new Livro("Noite na Taverna", new ObjectId(autor4.getId()), new ObjectId(editora2.getId()), 1855, "Romance Gótico", 19.90, 3),
            new Livro("O Amor nos Tempos do Cólera", new ObjectId(autor2.getId()), new ObjectId(editora2.getId()), 1985, "Romance", 44.90, 6),
            new Livro("Um Estudo em Vermelho", new ObjectId(autor1.getId()), new ObjectId(editora1.getId()), 1887, "Mistério", 33.00, 8),
            new Livro("O Signo dos Quatro", new ObjectId(autor1.getId()), new ObjectId(editora1.getId()), 1890, "Mistério", 34.50, 5)
        );

        livroRepository.saveAll(livros);

        System.out.println("✅ Banco de dados populado com 20 livros, 4 autores e 3 editoras!");
    }
}
