package com.thais.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thais.livraria.domain.Autor;
import com.thais.livraria.dto.AutorDto;
import com.thais.livraria.services.AutorService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(value = "/autor")
@CrossOrigin(origins = "*") public class AutorResources {

   @Autowired
   private AutorService autorService;
   
   
    @RequestMapping(method = RequestMethod.GET)
       
    public ResponseEntity<List<AutorDto>> findAll() {
        List<Autor> AutorList = autorService.findAll();
        List<AutorDto> listDto = AutorList.stream().map(x -> new AutorDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);     
    }
        

    //procurar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AutorDto> findById(@PathVariable String id) {
        Autor obj = autorService.findById(id);
        return ResponseEntity.ok().body(new AutorDto(obj));
}

//inserir
@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody AutorDto objDto) {
        Autor obj = autorService.insert(objDto); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                  .buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }

//deletar
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build(); 
    }

//update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody AutorDto objDto, @PathVariable String id) {
        objDto.setId(id);
        Autor obj = autorService.update(objDto);
        return ResponseEntity.noContent().build(); 
    }



}