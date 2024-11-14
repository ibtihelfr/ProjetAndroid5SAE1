package com.example.school.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.school.models.Evenement;

import java.util.List;
@Dao
public interface EvenementDAO {
    @Insert
    void createEvenement(Evenement evenement );
    @Delete
    void DeleteEvenement(Evenement evenement);
    @Update
    void updateEvenement(Evenement evenement);
    @Query("SELECT * FROM evenement ")
    List<Evenement> getAllEvenement();
    @Query("SELECT * FROM evenement WHERE idEvent = :id")
    Evenement getEvenementById(int id);

    @Query("SELECT * FROM evenement WHERE description LIKE '%' || :name || '%'")
    List<Evenement> searchEvenementByName(String name);

}
