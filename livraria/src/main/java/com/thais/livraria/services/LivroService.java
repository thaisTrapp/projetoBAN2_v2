package com.thais.livraria.services;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;

import com.thais.livraria.domain.Livro;
import com.thais.livraria.dto.LivroDto;
import com.thais.livraria.repository.LivroRepository;
import com.thais.livraria.services.exceptions.DuplicateEntryException;
import com.thais.livraria.services.exceptions.ObjectNotFoundException;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRep;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Livro> findAll() {
        return livroRep.findAll();
    }

    public Livro findById(String id) {
        Optional<Livro> obj = livroRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public Livro insert(LivroDto objDto) {
        Livro livro = fromDto(objDto);
        livro.setId(null);
        try {
            return livroRep.save(livro);
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntryException("Livro com este título já existe.");
        }
    }

    public Livro update(LivroDto objDto) {
        Livro entity = findById(objDto.getId());
        updateData(entity, objDto);
        try {
            return livroRep.save(entity);
        } catch (DuplicateKeyException e) {
            throw new DuplicateEntryException("Livro com este título já existe.");
        }
    }

    public void delete(String id) {
        findById(id);
        livroRep.deleteById(id);
    }

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

    public List<Livro> findTop3MostExpensive() {
        return livroRep.findTop3ByOrderByValorDesc();
    }

    public List<Livro> findPublishedInLast20Years() {
        int currentYear = Year.now().getValue();
        int twentyYearsAgo = currentYear - 20;
        return livroRep.findByAnoPublicacaoGreaterThanEqual(twentyYearsAgo);
    }

    public List<Map<String, Object>> countBooksByEditora() {
        LookupOperation lookupEditoras = lookup("editora", "idEditora", "_id", "editoraInfo");
        UnwindOperation unwindEditoras = unwind("editoraInfo", true);
        GroupOperation groupByEditora = group("editoraInfo.nome").count().as("totalLivros");
        MatchOperation filterByCount = match(Criteria.where("totalLivros").gt(1));
        ProjectionOperation projectResult = project()
            .and("_id").as("nomeEditora")
            .and("totalLivros").as("totalLivros");

        Aggregation aggregation = newAggregation(
            lookupEditoras,
            unwindEditoras,
            groupByEditora,
            filterByCount,
            projectResult
        );

        AggregationResults<Map<String, Object>> results =
            mongoTemplate.aggregate(aggregation, "livro", (Class<Map<String, Object>>)(Class<?>) Map.class);

        return results.getMappedResults();
    }

    public List<Map<String, Object>> getAveragePriceByAuthorNationality() {
        LookupOperation lookupAuthors = lookup("autor", "idAutor", "_id", "autorInfo");
        UnwindOperation unwindAuthors = unwind("autorInfo", true);
        GroupOperation groupByNationality = group("autorInfo.nacionalidade")
            .avg("valor").as("precoMedio");
        ProjectionOperation projectResult = project()
            .and("_id").as("nacionalidade")
            .and("precoMedio").as("precoMedio");

        Aggregation aggregation = newAggregation(
            lookupAuthors,
            unwindAuthors,
            groupByNationality,
            projectResult
        );

        AggregationResults<Map<String, Object>> results =
            mongoTemplate.aggregate(aggregation, "livro", (Class<Map<String, Object>>)(Class<?>) Map.class);

        return results.getMappedResults();
    }

    public List<Map<String, Object>> getLivrosComAutorDetalhes() {
        LookupOperation lookupAuthors = lookup("autor", "idAutor", "_id", "autorInfo");
        UnwindOperation unwindAuthors = unwind("autorInfo", true);
        ProjectionOperation project = project()
            .andExclude("_id")
            .and("id").as("livroId")
            .and("titulo").as("tituloLivro")
            .and("anoPublicacao").as("anoPublicacaoLivro")
            .and("genero").as("generoLivro")
            .and("valor").as("valorLivro")
            .and("quantidade").as("quantidadeLivro")
            .and("autorInfo.nome").as("nomeAutor")
            .and("autorInfo.nacionalidade").as("nacionalidadeAutor");

        Aggregation aggregation = newAggregation(
            lookupAuthors,
            unwindAuthors,
            project
        );

        AggregationResults<Map<String, Object>> results =
            mongoTemplate.aggregate(aggregation, "livro", (Class<Map<String, Object>>)(Class<?>) Map.class);

        return results.getMappedResults();
    }

    public List<Map<String, Object>> getLivrosComEditoraDetalhes() {
        LookupOperation lookupEditoras = lookup("editora", "idEditora", "_id", "editoraInfo");
        UnwindOperation unwindEditoras = unwind("editoraInfo", true);
        ProjectionOperation project = project()
            .andExclude("_id")
            .and("id").as("livroId")
            .and("titulo").as("tituloLivro")
            .and("anoPublicacao").as("anoPublicacaoLivro")
            .and("genero").as("generoLivro")
            .and("valor").as("valorLivro")
            .and("quantidade").as("quantidadeLivro")
            .and("editoraInfo.nome").as("nomeEditora");

        Aggregation aggregation = newAggregation(
            lookupEditoras,
            unwindEditoras,
            project
        );

        AggregationResults<Map<String, Object>> results =
            mongoTemplate.aggregate(aggregation, "livro", (Class<Map<String, Object>>)(Class<?>) Map.class);

        return results.getMappedResults();
    }

    public List<Map<String, Object>> getLivrosPorAutorEEditora(String nomeAutor, String nomeEditora) {
        LookupOperation lookupAuthors = lookup("autor", "idAutor", "_id", "autorInfo");
        UnwindOperation unwindAuthors = unwind("autorInfo", true);
        LookupOperation lookupEditoras = lookup("editora", "idEditora", "_id", "editoraInfo");
        UnwindOperation unwindEditoras = unwind("editoraInfo", true);

        MatchOperation matchCriteria = match(
            Criteria.where("autorInfo.nome").is(nomeAutor)
                .and("editoraInfo.nome").is(nomeEditora)
        );

        ProjectionOperation project = project()
            .andExclude("_id")
            .and("id").as("livroId")
            .and("titulo").as("tituloLivro")
            .and("anoPublicacao").as("anoPublicacaoLivro")
            .and("genero").as("generoLivro")
            .and("valor").as("valorLivro")
            .and("quantidade").as("quantidadeLivro")
            .and("autorInfo.nome").as("nomeAutor")
            .and("editoraInfo.nome").as("nomeEditora");

        Aggregation aggregation = newAggregation(
            lookupAuthors,
            unwindAuthors,
            lookupEditoras,
            unwindEditoras,
            matchCriteria,
            project
        );

        AggregationResults<Map<String, Object>> results =
            mongoTemplate.aggregate(aggregation, "livro", (Class<Map<String, Object>>)(Class<?>) Map.class);

        return results.getMappedResults();
    }
}
