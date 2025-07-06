package com.thais.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thais.livraria.domain.Livro;
import com.thais.livraria.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired 
    private LivroRepository livroRep;

    public List<Livro> findAll(){
        return livroRep.findAll();
}

public Livro findById(String id) {
        return livroRep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public Livro insert(Livro livro) {
        return livroRep.save(livro);
    }

    public Livro update(Livro livro) {
        findById(livro.getId());
        return livroRep.save(livro);
    }

    public void delete(String id) {
        findById(id);
        livroRep.deleteById(id);
    }

}
