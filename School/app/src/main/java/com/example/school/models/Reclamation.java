package com.example.school.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import org.jetbrains.annotations.NotNull;

@Entity(tableName = "reclamation")
public class Reclamation {
    @PrimaryKey(autoGenerate = true)
    private int idRec;
    private String typeReclamation;
    private String message;
    private boolean status;



    public Reclamation(@NotNull String typeReclamation, @NotNull String message, boolean status) {
        this.typeReclamation = typeReclamation;
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idRec=" + idRec +
                ", typeReclamation=" + typeReclamation +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public Reclamation(int idRec, String typeReclamation, String message, boolean status) {
        this.idRec = idRec;
        this.typeReclamation = typeReclamation;
        this.message = message;
        this.status = status;
    }

    public Reclamation() {
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
