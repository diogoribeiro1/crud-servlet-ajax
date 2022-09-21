package model;

import java.sql.Date;

public class EventModel {

    private Integer id;
    private String nome;
    private String data;
    private String local;

    public EventModel() {
    }

    public EventModel(String nome, String data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public EventModel(Integer id, String nome, String data, String local) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
