package app.support_visite_fdl.data.dao;

import androidx.room.*;

import app.support_visite_fdl.FilterAdapter;
import app.support_visite_fdl.data.entities.ImageEntity;
import app.support_visite_fdl.data.entities.MotCleEntity;

import java.util.List;

@Dao
public interface MotCleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMotCle(MotCleEntity motCle);

    @Query("SELECT libelle FROM mot_cle")
    List<String> getAllMotsCles();

    @Query("SELECT m.libelle, COUNT(*) as count FROM mot_cle m " +
            "JOIN ImageMotCleCrossRef imc ON m.id = imc.motCleId " +
            "WHERE imc.imageId IN (:imageIds) " +
            "GROUP BY m.libelle")
    List<FilterAdapter.MotCleWithCount> getMotsClesWithCount(List<Long> imageIds);
    @Delete
    void deleteMotCle(MotCleEntity motCle);
}

