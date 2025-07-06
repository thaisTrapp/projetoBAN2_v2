package com.thais.livraria.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "livro")
public class Livro implements java.io.Serializable {


@Id
private String id;

private String titulo;
private int id_autor; 
private int id_editora;
private int ano_publicacao;
private String genero;
private double valor;
private int quantidade;


public Livro(String titulo, int id_autor, int id_editora, int ano_publicacao, String id, int quantidade, double valor, String genero) {
    this.titulo = titulo;
    this.id_autor = id_autor;
    this.id_editora = id_editora;
    this.ano_publicacao = ano_publicacao;
    this.id = id;
    this.genero = genero;
    this.valor = valor;
    this.quantidade = quantidade;
}



public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getid_autor() {
        return id_autor;
    }

    public void setid_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getid_editora() {
        return id_editora;
    }

    public void setid_editora(int id_editora) {
        this.id_editora = id_editora;
    }

    public int getano_publicacao() {
        return id_editora;
    }

    public void setano_publicacao(int ano_publicacao) {
        this.id_editora = ano_publicacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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




@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Livro livro = (Livro) o;
    return id != null ? id.equals(livro.id) : livro.id == null;
}

@Override
public int hashCode() {
    return id != null ? id.hashCode() : 0;
}


}

