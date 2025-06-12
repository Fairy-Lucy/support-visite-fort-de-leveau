package app.support_visite_fdl.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lieu")
public class LieuEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String nom;

    @Override
    public String toString() {
        return nom; // Retourne le nom du lieu pour l'affichage
    }
}
