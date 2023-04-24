package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {

    private Integer id;
    private String nome;
    private String data;
    private String local;

    public EventModel(String nome, String data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

}
