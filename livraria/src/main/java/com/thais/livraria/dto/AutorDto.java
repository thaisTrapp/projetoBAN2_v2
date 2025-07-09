package com.thais.livraria.dto;

import java.io.Serializable;
import com.thais.livraria.domain.Autor;


public class AutorDto implements Serializable {

    private String id;
    private String nome;
    private String nacionalidade;


    public AutorDto(){   
    }

    public AutorDto (Autor obj){
        id = obj.getId();
        nome = obj.getNome();
        nacionalidade = obj.getNacionalidade();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
  
    

}
