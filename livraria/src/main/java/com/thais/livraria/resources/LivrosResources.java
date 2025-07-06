package com.thais.livraria.resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thais.livraria.domain.Livro;
import com.thais.livraria.services.LivroService;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(value = "/livro")
public class LivrosResources {

   @Autowired
   private LivroService livroService;
   
   
    @RequestMapping(method = RequestMethod.GET)
       
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livro = livroService.findAll();


        return ResponseEntity.ok().body(livro);     
    }
        

}
