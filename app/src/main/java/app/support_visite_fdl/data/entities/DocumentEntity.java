package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "document")
public class DocumentEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String titre;
    public String uri;
    public String theme;

    public String getTitre() {
        return titre;
    }

    public String getUri() {
        return uri;
    }

    public String getTheme() {
        return theme;
    }
}
