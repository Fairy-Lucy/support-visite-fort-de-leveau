package app.support_visite_fdl.data;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseInstance {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "ma_bdd_images"
                    ).fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

