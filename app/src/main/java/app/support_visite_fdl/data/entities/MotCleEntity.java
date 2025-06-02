package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mot_cle")
public class MotCleEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String libelle;
}

