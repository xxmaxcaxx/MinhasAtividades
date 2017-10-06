package com.example.logonrm.minhasatividades.dao;

import com.activeandroid.query.Select;
import com.example.logonrm.minhasatividades.model.Atividade;

import java.util.List;

/**
 * Created by logonrm on 06/10/2017.
 */

public class AtividadeDAO {
    public List<Atividade> findAll(){
        return new Select()
                .from(Atividade.class)
                .orderBy("titulo ASC")
                .execute();
    }
}
