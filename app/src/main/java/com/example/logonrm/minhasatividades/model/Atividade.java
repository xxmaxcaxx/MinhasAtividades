package com.example.logonrm.minhasatividades.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by logonrm on 06/10/2017.
 */

@Table(name = "atividade")
public class Atividade  extends Model{

    @Column(name = "titulo")
    private  String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
