    package app.support_visite_fdl.data.relations;

    import androidx.room.Embedded;
    import androidx.room.Junction;
    import androidx.room.Relation;

    import java.util.List;

    import app.support_visite_fdl.data.entities.ImageEntity;
    import app.support_visite_fdl.data.entities.ImageMotCleCrossRef;
    import app.support_visite_fdl.data.entities.MotCleEntity;


    public class ImagesMotsCles {
        @Embedded
        public ImageEntity image;

        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                associateBy = @Junction(value = ImageMotCleCrossRef.class,
                        parentColumn = "imageId",
                        entityColumn = "motCleId")
        )
        public List<MotCleEntity> motsCles;
    }

