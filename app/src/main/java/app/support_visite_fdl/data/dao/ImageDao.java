package app.support_visite_fdl.data.dao;

import androidx.room.*;

import app.support_visite_fdl.data.entities.ImageEntity;
import app.support_visite_fdl.data.entities.ImageLieuCrossRef;
import app.support_visite_fdl.data.entities.ImageMotCleCrossRef;
import app.support_visite_fdl.data.relations.ImagesMotsCles;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertImage(ImageEntity image);

    @Insert
    void insertImageLieuCrossRef(ImageLieuCrossRef crossRef);

    @Insert
    void insertImageMotCleCrossRef(ImageMotCleCrossRef crossRef);

    @Transaction
    @Query("SELECT * FROM image WHERE id = :imageId")
    ImagesMotsCles getImageAvecMotsCles(long imageId);

    @Query("SELECT * FROM image")
    List<ImageEntity> getAllImages();

    @Delete
    void deleteImage(ImageEntity image);
}
