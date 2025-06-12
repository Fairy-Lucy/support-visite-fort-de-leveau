package app.support_visite_fdl.data;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AppDatabaseInstance {

    private static final String TAG = "AppDatabaseInstance";
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "ma_bdd_images")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d(TAG, "Database created, loading initial data...");
                                    loadInitialData(context, db);
                                }

                                @Override
                                public void onOpen(SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    Log.d(TAG, "Database opened, checking data...");
                                    checkDatabaseContents(db);
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void loadInitialData(Context context, SupportSQLiteDatabase db) {
        try {
            InputStream inputStream = context.getAssets().open("database_init.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder builder = new StringBuilder();

            Log.d(TAG, "Reading SQL file...");
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                Log.d(TAG, "Read line: " + line);
            }

            String[] queries = builder.toString().split(";");
            Log.d(TAG, "Number of queries: " + queries.length);
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    Log.d(TAG, "Executing query: " + query);
                    db.execSQL(query);
                }
            }

            reader.close();
            inputStream.close();
            Log.d(TAG, "Initial data loaded successfully.");
        } catch (Exception e) {
            Log.e(TAG, "Error loading initial data: ", e);
        }
    }

    private static void checkDatabaseContents(SupportSQLiteDatabase db) {
        try {
            String query = "SELECT * FROM lieu";
            android.database.Cursor cursor = db.query(query);
            int count = cursor.getCount();
            Log.d(TAG, "Number of rows in 'lieu' table: " + count);

            if (count > 0) {
                cursor.moveToFirst();
                do {
                    String nom = cursor.getString(cursor.getColumnIndex("nom"));
                    Log.d(TAG, "Lieu: " + nom);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Error checking database contents: ", e);
        }
    }
}
