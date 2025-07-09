package com.thais.livraria.services;


import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DuplicateKeyException;
import com.thais.livraria.domain.Editora;
import com.thais.livraria.dto.EditoraDto;
import com.thais.livraria.repository.EditoraRepository;
import com.thais.livraria.services.exceptions.DuplicateEntryException;
import com.thais.livraria.services.exceptions.ObjectNotFoundException;



@Service
public class EditoraService {

    @Autowired 
    private EditoraRepository editoraRep;

    public List<Editora> findAll(){
        return editoraRep.findAll();
}


//erro
    public Editora findById(String id) {
        
        Optional<Editora> obj = editoraRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Editora não encontrado"));
    }


//alterações editora

 public Editora insert(EditoraDto objDto) {
        Editora editora = fromDto(objDto);
        editora.setId(null); 
        
        try {
            return editoraRep.save(editora);
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntryException("Editora com este nome já existe.");
        }
    }
    public Editora update(EditoraDto objDto) { 
            Editora entity = findById(objDto.getId());
            updateData(entity, objDto);
            try {
                return editoraRep.save(entity);
            } catch (DuplicateKeyException e) {
                throw new DuplicateEntryException("Editora com este nome já existe.");
        }
    }
    public void delete(String id) {
            findById(id);
            editoraRep.deleteById(id);
        }

//transforma editoraDto em Editora
public Editora fromDto(EditoraDto objDto) {
       
    Editora editora = new Editora(
            objDto.getNome()
            );

        // Para o caso de update, onde a DTO já virá com um ID
        if (objDto.getId() != null && !objDto.getId().isEmpty()) {
            editora.setId(objDto.getId());
        }
        return editora;
    }

 
private void updateData(Editora entity, EditoraDto objDto) {
    if (objDto.getNome() != null && !objDto.getNome().isEmpty()) {
        entity.setNome(objDto.getNome());
    }

    }

}