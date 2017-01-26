package br.com.jmsstudio.agenda.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jms on 24/01/17.
 */
public class Prova implements Serializable {
    private String materia;
    private Date data;
    private List<String> topicos;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public Prova(String materia, Date data, List<String> topicos) {
        this.materia = materia;
        this.data = data;
        this.topicos = topicos;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getData() {
        return data;
    }

    public String getDataAsString() {
        return sdf.format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return this.materia;
    }
}
