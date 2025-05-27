package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String chemin; // ex: "file:///storage/emulated/0/..."
    public String date; // ex: "2023-08-17"
}

