package com.thais.livraria.dto;

import java.io.Serializable;
import com.thais.livraria.domain.Editora;


public class EditoraDto implements Serializable {

    private String id;
    private String nome;

    public EditoraDto(){   
    }

    public EditoraDto (Editora obj){
        id = obj.getId();
        nome = obj.getNome();
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


}
