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
}
