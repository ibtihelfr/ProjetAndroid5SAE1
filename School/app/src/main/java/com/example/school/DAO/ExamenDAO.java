package com.example.school.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.school.models.Examen;

import java.util.List;
@Dao
public interface ExamenDAO {
    @Insert
    void createExamen(Examen examen );
    @Delete
    void DeleteExamen(Examen examen);
    @Update
    void updateExamen(Examen examen);
    @Query("SELECT * FROM examen ")
    List<Examen> getAllExamen();
    @Query("SELECT * FROM examen WHERE idExamen = :id")
    Examen getExamenById(int id);

}
