package app.support_visite_fdl.data.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"imageId", "motCleId"})
public class ImageMotCleCrossRef {
    public long imageId;
    public long motCleId;
}
