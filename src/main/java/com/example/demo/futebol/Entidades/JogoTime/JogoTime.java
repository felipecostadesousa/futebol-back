package com.example.demo.futebol.Entidades.JogoTime;

import jakarta.persistence.*;
import com.example.demo.futebol.Entidades.Time.Time;
import com.example.demo.futebol.Entidades.Jogo.Jogo;

@Entity
@Table(name="jogo_time", schema = "public")
public class JogoTime {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="fk_time", referencedColumnName = "id_time")
    private Time time;

    @ManyToOne
    @JoinColumn(name="fk_jogo", referencedColumnName = "id_jogo")
    private Jogo jogo;

    public JogoTime() {
    }

    public JogoTime(Integer id, Time time, Jogo jogo){
        this.id = id;
        this.time = time;
        this.jogo = jogo;
    }

    public JogoTime(Time time, Jogo jogo){
        this.time = time;
        this.jogo = jogo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
