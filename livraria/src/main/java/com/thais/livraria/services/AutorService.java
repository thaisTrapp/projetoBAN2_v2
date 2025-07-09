package com.thais.livraria.services;


import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DuplicateKeyException;
import com.thais.livraria.domain.Autor;
import com.thais.livraria.dto.AutorDto;
import com.thais.livraria.repository.AutorRepository;
import com.thais.livraria.services.exceptions.DuplicateEntryException;
import com.thais.livraria.services.exceptions.ObjectNotFoundException;



@Service
public class AutorService {

    @Autowired 
    private AutorRepository autorRep;

    public List<Autor> findAll(){
        return autorRep.findAll();
}


//erro
    public Autor findById(String id) {
        
        Optional<Autor> obj = autorRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
    }


//alterações autor

 public Autor insert(AutorDto objDto) {
        Autor autor = fromDto(objDto);
        autor.setId(null); 
        
        try {
            return autorRep.save(autor);
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntryException("Autor com este nome já existe.");
        }
    }
    public Autor update(AutorDto objDto) { 
            Autor entity = findById(objDto.getId());
            updateData(entity, objDto);
            try {
                return autorRep.save(entity);
            } catch (DuplicateKeyException e) {
                throw new DuplicateEntryException("Autor com este nome já existe.");
        }
    }
    public void delete(String id) {
            findById(id);
            autorRep.deleteById(id);
        }

//transforma autorDto em Autor
public Autor fromDto(AutorDto objDto) {
       
    Autor autor = new Autor(
            objDto.getNome(),
            objDto.getNacionalidade()
            );

        // Para o caso de update, onde a DTO já virá com um ID
        if (objDto.getId() != null && !objDto.getId().isEmpty()) {
            autor.setId(objDto.getId());
        }
        return autor;
    }

 
private void updateData(Autor entity, AutorDto objDto) {
    if (objDto.getNome() != null && !objDto.getNome().isEmpty()) {
        entity.setNome(objDto.getNome());
    }
    if (objDto.getNacionalidade() != null && !objDto.getNacionalidade().isEmpty()) {
        entity.setNacionalidade(objDto.getNacionalidade());
    }

    }

}