package com.example.school.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "classe")
public class Classe {
    @PrimaryKey(autoGenerate = true)
    private int idClass;
    private String niveau;
    private String className;
    private String emploi;

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Classe() {
    }

    public Classe(int idClass, String niveau, String className, String emploi) {
        this.idClass = idClass;
        this.niveau = niveau;
        this.className = className;
        this.emploi = emploi;
    }

    public Classe(String niveau, String className, String emploi) {
        this.niveau = niveau;
        this.className = className;
        this.emploi = emploi;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClass=" + idClass +
                ", niveau=" + niveau +
                ", className='" + className + '\'' +
                ", emploi='" + emploi + '\'' +
                '}';
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getString() {
        return niveau;
    }

    public void setString(String niveau) {
        this.niveau = niveau;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmploi() {
        return emploi;
    }

    public void setEmploi(String emploi) {
        this.emploi = emploi;
    }
}
