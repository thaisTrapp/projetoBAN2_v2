package com.thais.livraria.services;

import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thais.livraria.domain.Livro;
import com.thais.livraria.dto.LivroDto;
import com.thais.livraria.repository.LivroRepository;


@Service
public class LivroService {

    @Autowired 
    private LivroRepository livroRep;

    public List<Livro> findAll(){
        return livroRep.findAll();
}


//erro
    public Livro findById(String id) {
        
        Optional<Livro> obj = livroRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }


//alterações livro
    public Livro insert(LivroDto objDto) {
       Livro livro = fromDto(objDto);
        livro.setId(null);
        return livroRep.save(livro);
    }
    public Livro update(LivroDto objDto) { 
            Livro entity = findById(objDto.getId());
            updateData(entity, objDto);
            return livroRep.save(entity);
        }

    public void delete(String id) {
            findById(id);
            livroRep.deleteById(id);
        }

//transforma livroDto em Livro
public Livro fromDto(LivroDto objDto) {
       
    Livro livro = new Livro(
            objDto.getTitulo(),
            objDto.getIdAutor(),
            objDto.getIdEditora(),
            objDto.getAnoPublicacao(),
            objDto.getGenero(),
            objDto.getValor(),
            objDto.getQuantidade()
        );
        // Para o caso de update, onde a DTO já virá com um ID
        if (objDto.getId() != null && !objDto.getId().isEmpty()) {
            livro.setId(objDto.getId());
        }
        return livro;
    }

 
 private void updateData(Livro entity, LivroDto objDto) {
        if (objDto.getTitulo() != null && !objDto.getTitulo().isEmpty()) {
            entity.setTitulo(objDto.getTitulo());
        }
        entity.setIdAutor(objDto.getIdAutor());
        entity.setIdEditora(objDto.getIdEditora());
        entity.setAnoPublicacao(objDto.getAnoPublicacao());
        if (objDto.getGenero() != null && !objDto.getGenero().isEmpty()) {
            entity.setGenero(objDto.getGenero());
        }
        entity.setValor(objDto.getValor());
        entity.setQuantidade(objDto.getQuantidade());
    }
}

