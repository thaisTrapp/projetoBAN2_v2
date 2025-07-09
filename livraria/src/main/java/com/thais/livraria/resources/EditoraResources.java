package com.thais.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thais.livraria.domain.Editora;
import com.thais.livraria.dto.EditoraDto;
import com.thais.livraria.services.EditoraService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(value = "/editora")
public class EditoraResources {

   @Autowired
   private EditoraService editoraService;
   
   
    @RequestMapping(method = RequestMethod.GET)
       
    public ResponseEntity<List<EditoraDto>> findAll() {
        List<Editora> EditoraList = editoraService.findAll();
        List<EditoraDto> listDto = EditoraList.stream().map(x -> new EditoraDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);     
    }
        

    //procurar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EditoraDto> findById(@PathVariable String id) {
        Editora obj = editoraService.findById(id);
        return ResponseEntity.ok().body(new EditoraDto(obj));
}

//inserir
@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody EditoraDto objDto) {
        Editora obj = editoraService.insert(objDto); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                  .buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }

//deletar
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        editoraService.delete(id);
        return ResponseEntity.noContent().build(); 
    }

//update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody EditoraDto objDto, @PathVariable String id) {
        objDto.setId(id);
        Editora obj = editoraService.update(objDto);
        return ResponseEntity.noContent().build(); 
    }



}