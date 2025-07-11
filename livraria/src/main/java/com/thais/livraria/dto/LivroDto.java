package com.thais.livraria.dto;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.thais.livraria.domain.Livro;

public class LivroDto implements Serializable {

    private String id;
    private String titulo;
    private ObjectId idAutor;
    private ObjectId idEditora;
    private int anoPublicacao;
    private String genero;
    private double valor;
    private int quantidade;


    public LivroDto(){   
    }

    public LivroDto (Livro obj){
        id = obj.getId();
        titulo = obj.getTitulo();
        idAutor = obj.getIdAutor();
        idEditora = obj.getIdEditora();
        anoPublicacao = obj.getAnoPublicacao();
        genero = obj.getGenero();
        valor = obj.getValor();
        quantidade = obj.getQuantidade();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ObjectId getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(ObjectId idAutor) {
        this.idAutor = idAutor;
    }

    public ObjectId getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(ObjectId idEditora) {
        this.idEditora = idEditora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    

}
