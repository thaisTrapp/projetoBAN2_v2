package com.thais.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thais.livraria.domain.Livro;
import com.thais.livraria.dto.LivroDto;
import com.thais.livraria.services.LivroService;
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
        

}
