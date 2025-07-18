package app.support_visite_fdl.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import app.support_visite_fdl.data.entities.DocumentEntity;

@Dao
public interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertDocument(DocumentEntity document);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDocuments(List<DocumentEntity> documents);

    @Query("SELECT * FROM document")
    List<DocumentEntity> getAllDocuments();

    @Delete
    void deleteDocument(DocumentEntity document);
}
