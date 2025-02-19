package com.example.school.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.String;
import java.util.Date;
@Entity(tableName = "examen")
public class Examen {
    @PrimaryKey(autoGenerate = true)
    private int idExamen;
    private String type;
    private int semestre;
    private String date;
    private String matiere;



//    String dateString = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
//
//    String localDate = String.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

    public Examen(int idExamen, String type, int semestre, String date, String matiere) {
        this.idExamen = idExamen;
        this.type = type;
        this.semestre = semestre;
        this.date = date;
        this.matiere = matiere;
    }

    public Examen(String type, int semestre,String  date, String matiere) {
        this.type = type;
        this.semestre = semestre;
        this.date = date;
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "idExamen=" + idExamen +
                ", type=" + type +
                ", semestre=" + semestre +
                ", date=" + date +
                ", matiere=" + matiere +
                '}';
    }

    public Examen() {
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
