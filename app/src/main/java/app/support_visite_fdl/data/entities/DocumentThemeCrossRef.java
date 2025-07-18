package app.support_visite_fdl.data.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"documentId", "themeId"})
public class DocumentThemeCrossRef {
    public long documentId;
    public long themeId;
}
