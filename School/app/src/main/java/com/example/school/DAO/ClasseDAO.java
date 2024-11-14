package com.example.school.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.school.models.Classe;

import java.util.List;
@Dao
public interface ClasseDAO {
    @Insert
    void createClasse(Classe classe );
    @Delete
    void DeleteClasse(Classe classe);
    @Update
    void updateClasse(Classe classe);
    @Query("SELECT * FROM classe ")
    List<Classe> getAllClasse();
    @Query("SELECT * FROM classe WHERE idClass = :id")
    Classe getClasseById(int id);

<<<<<<< HEAD
=======
    @Query("SELECT * FROM classe WHERE niveau LIKE '%' || :niveau || '%'")
    List<Classe> searchClassesByNiveau(String niveau);

>>>>>>> 7c819f0 (crud classe)
}
