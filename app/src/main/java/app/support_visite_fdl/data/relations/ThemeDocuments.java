package app.support_visite_fdl.data.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import app.support_visite_fdl.data.entities.DocumentEntity;
import app.support_visite_fdl.data.entities.DocumentThemeCrossRef;
import app.support_visite_fdl.data.entities.ThemeEntity;

public class ThemeDocuments {
    @Embedded
    public ThemeEntity theme;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value = DocumentThemeCrossRef.class,
                    parentColumn = "themeId",
                    entityColumn = "documentId"
            )
    )
    public List<DocumentEntity> documents;
}
