package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "image")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "chemin")
    public String chemin; // ← Chemin local du fichier image (ex: /storage/emulated/0/... )

    @ColumnInfo(name = "date")
    public long date;

    public String getUri() {
        return "file://" + chemin;
    }
}

