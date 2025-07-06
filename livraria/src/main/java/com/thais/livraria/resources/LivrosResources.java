package com.thais.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thais.livraria.domain.Livro;
import com.thais.livraria.dto.LivroDto;
import com.thais.livraria.services.LivroService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(value = "/livro")
public class LivrosResources {

   @Autowired
   private LivroService livroService;
   
   
    @RequestMapping(method = RequestMethod.GET)
       
    public ResponseEntity<List<LivroDto>> findAll() {
        List<Livro> LivroList = livroService.findAll();
        List<LivroDto> listDto = LivroList.stream().map(x -> new LivroDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);     
    }
        

    //procurar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LivroDto> findById(@PathVariable String id) {
        Livro obj = livroService.findById(id);
        return ResponseEntity.ok().body(new LivroDto(obj));
}

//inserir
@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody LivroDto objDto) {
        Livro obj = livroService.insert(objDto); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                  .buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }

//deletar
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build(); 
    }

//update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody LivroDto objDto, @PathVariable String id) {
        objDto.setId(id);
        Livro obj = livroService.update(objDto);
        return ResponseEntity.noContent().build(); 
    }



}