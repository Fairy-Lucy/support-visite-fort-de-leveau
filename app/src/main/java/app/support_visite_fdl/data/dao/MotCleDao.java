package app.support_visite_fdl.data.dao;

import androidx.room.*;

import app.support_visite_fdl.data.entities.MotCleEntity;

import java.util.List;

@Dao
public interface MotCleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMotCle(MotCleEntity motCle);

    @Query("SELECT * FROM mot_cle")
    List<MotCleEntity> getAllMotsCles();

    @Delete
    void deleteMotCle(MotCleEntity motCle);
}

