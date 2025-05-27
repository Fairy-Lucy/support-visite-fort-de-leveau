package app.support_visite_fdl.data.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import app.support_visite_fdl.data.entities.ImageEntity;
import app.support_visite_fdl.data.entities.ImageLieuCrossRef;
import app.support_visite_fdl.data.entities.LieuEntity;

public class LieuImages {
    @Embedded
    public LieuEntity lieu;

    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(value = ImageLieuCrossRef.class,
                    parentColumn = "lieuId",
                    entityColumn = "imageId")
    )
    public List<ImageEntity> images;
}
