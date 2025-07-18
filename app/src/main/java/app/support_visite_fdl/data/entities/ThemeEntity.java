package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "theme")
public class ThemeEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String nom;

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
