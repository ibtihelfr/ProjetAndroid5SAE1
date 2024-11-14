package com.example.school.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "evenement")
public class Evenement {
    @PrimaryKey(autoGenerate = true)
    private int idEvent;
    private String heure;
    private float prix;
    private String description;
    private String type;

    public Evenement() {
    }
    @Ignore

    public Evenement(String heure, float prix, String description, String type) {
        this.heure = heure;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "idEvent=" + idEvent +
                ", heure='" + heure + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
    @Ignore

    public Evenement(int idEvent, String heure, String description, float prix, String type) {
        this.idEvent = idEvent;
        this.heure = heure;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
