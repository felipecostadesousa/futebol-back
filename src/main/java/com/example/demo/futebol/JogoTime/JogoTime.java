package com.example.demo.futebol.JogoTime;

import jakarta.persistence.*;
import com.example.demo.futebol.Time.Time;
import com.example.demo.futebol.Jogo.Jogo;

@Entity
@Table(name="jogo_time", schema = "public")
public class JogoTime {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_time", referencedColumnName = "id")
    private Time time;

    @ManyToOne
    @JoinColumn(name="id_jogo", referencedColumnName = "id")
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

    public JogoTime(Integer id, Time time){
        this.id = id;
        this.time = time;
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
