package app.support_visite_fdl.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import app.support_visite_fdl.data.dao.*;
import app.support_visite_fdl.data.entities.*;
import app.support_visite_fdl.data.relations.*;

@Database(
        entities = {
                LieuEntity.class,
                ImageEntity.class,
                MotCleEntity.class,
                ImageLieuCrossRef.class,
                ImageMotCleCrossRef.class
        },
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LieuDao lieuDao();
    public abstract ImageDao imageDao();
    public abstract MotCleDao motCleDao();
}
