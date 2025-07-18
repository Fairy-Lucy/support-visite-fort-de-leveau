package app.support_visite_fdl.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import app.support_visite_fdl.data.entities.DocumentEntity;
import app.support_visite_fdl.data.entities.DocumentThemeCrossRef;
import app.support_visite_fdl.data.entities.ThemeEntity;
import app.support_visite_fdl.data.relations.ThemeDocuments;

@Dao
public interface ThemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTheme(ThemeEntity theme);

    @Insert
    void insertDocument(DocumentEntity document);

    @Insert
    void insertDocumentThemeCrossRef(DocumentThemeCrossRef crossRef);

    @Transaction
    @Query("SELECT * FROM theme WHERE id = :themeId")
    ThemeDocuments getThemeWithDocuments(long themeId);

    @Query("SELECT * FROM theme")
    List<ThemeEntity> getAllThemes();

    @Delete
    void deleteTheme(ThemeEntity theme);
}
