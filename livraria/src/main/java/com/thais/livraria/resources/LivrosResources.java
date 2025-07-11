package com.thais.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thais.livraria.domain.Livro;
import com.thais.livraria.dto.LivroDto;
import com.thais.livraria.services.LivroService;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livro")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem

public class LivrosResources {

    @Autowired
    private LivroService livroService;

    // --- CRUD BÁSICO ---

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll() {
        List<Livro> livros = livroService.findAll();
        List<LivroDto> listDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable String id) {
        Livro obj = livroService.findById(id);
        return ResponseEntity.ok().body(new LivroDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody LivroDto objDto) {
        Livro obj = livroService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody LivroDto objDto, @PathVariable String id) {
        objDto.setId(id);
        livroService.update(objDto);
        return ResponseEntity.noContent().build();
    }

    // --- CONSULTAS E RELATÓRIOS ---

    @GetMapping("/consultas/top-3-caros")
    public ResponseEntity<List<LivroDto>> getTop3MostExpensive() {
        List<Livro> livros = livroService.findTop3MostExpensive();
        List<LivroDto> listDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/consultas/ultimos-20-anos")
    public ResponseEntity<List<LivroDto>> getPublishedInLast20Years() {
        List<Livro> livros = livroService.findPublishedInLast20Years();
        List<LivroDto> listDto = livros.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/consultas/contagem-por-editora")
    public ResponseEntity<List<Map<String, Object>>> getCountBooksByEditora() {
        List<Map<String, Object>> results = livroService.countBooksByEditora();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/consultas/preco-medio-por-nacionalidade")
    public ResponseEntity<List<Map<String, Object>>> getAveragePriceByAuthorNationality() {
        List<Map<String, Object>> results = livroService.getAveragePriceByAuthorNationality();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/relatorios/livros-com-autores")
    public ResponseEntity<List<Map<String, Object>>> getLivrosComAutorDetalhes() {
        List<Map<String, Object>> results = livroService.getLivrosComAutorDetalhes();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/relatorios/livros-com-editoras")
    public ResponseEntity<List<Map<String, Object>>> getLivrosComEditoraDetalhes() {
        List<Map<String, Object>> results = livroService.getLivrosComEditoraDetalhes();
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/relatorios/livros-por-autor-editora")
    public ResponseEntity<List<Map<String, Object>>> getLivrosPorAutorEEditora(
            @RequestParam("nomeAutor") String nomeAutor,
            @RequestParam("nomeEditora") String nomeEditora) {
        List<Map<String, Object>> results = livroService.getLivrosPorAutorEEditora(nomeAutor, nomeEditora);
        return ResponseEntity.ok().body(results);
    }
}
