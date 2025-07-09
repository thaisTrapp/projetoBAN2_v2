package com.thais.livraria.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "editora")
public class Editora implements java.io.Serializable {

    @Id
    private String id; // preenchido  pelo MongoDB
    
    @Indexed(unique = true)
    private String nome;
    
    public Editora(String nome) {
        this.nome = nome;
 
    }

    public Editora() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editora editora = (Editora) o;
        return id != null ? id.equals(editora.id) : editora.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
