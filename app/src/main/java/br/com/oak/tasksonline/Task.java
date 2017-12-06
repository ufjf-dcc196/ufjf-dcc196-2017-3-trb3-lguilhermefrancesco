package br.com.oak.tasksonline;

/**
 * Created by Franc on 05/12/2017.
 */

public class Task {

    private int id;
    private String titulo;
    private String descricao;
    private int nivel;
    private String status;


    public Task() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
