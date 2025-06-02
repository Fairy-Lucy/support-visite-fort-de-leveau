package app.support_visite_fdl.data.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"imageId", "lieuId"})
public class ImageLieuCrossRef {
    public long imageId;
    public long lieuId;
}
