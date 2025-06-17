package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "image")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "chemin")
    public String chemin;

    @ColumnInfo(name = "date")
    public long date;

    @ColumnInfo(name = "description")
    public String description;

    public String getUri() {
        return "file://" + chemin;
    }
}

