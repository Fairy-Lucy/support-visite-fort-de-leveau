package app.support_visite_fdl.data.dao;

import androidx.room.*;
import app.support_visite_fdl.data.entities.LieuEntity;
import app.support_visite_fdl.data.relations.LieuImages;

import java.util.List;

@Dao
public interface LieuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLieu(LieuEntity lieu);

    @Query("SELECT * FROM lieu")
    List<LieuEntity> getAllLieux();

    @Transaction
    @Query("SELECT * FROM lieu WHERE id = :lieuId")
    LieuImages getLieuAvecImages(long lieuId);

    @Delete
    void deleteLieu(LieuEntity lieu);
}
