package app.support_visite_fdl;

import android.content.Context;
import android.util.Log;

import java.util.List;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSynchronizer {
    private final ApiService apiService;
    private final AppDatabase db;

    public DataSynchronizer(Context context) {
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.db = AppDatabaseInstance.getDatabase(context);
    }

    public void synchronizeLieux() {
        Call<List<LieuEntity>> call = apiService.getLieux();
        call.enqueue(new Callback<List<LieuEntity>>() {
            @Override
            public void onResponse(Call<List<LieuEntity>> call, Response<List<LieuEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> db.lieuDao().insertAllLieux(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<LieuEntity>> call, Throwable t) {
                Log.e("Sync", "Erreur synchronisation lieux", t);
            }
        });
    }

    public void synchronizeImages() {
        Call<List<ImageEntity>> call = apiService.getImages();
        call.enqueue(new Callback<List<ImageEntity>>() {
            @Override
            public void onResponse(Call<List<ImageEntity>> call, Response<List<ImageEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> db.imageDao().insertAllImages(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<ImageEntity>> call, Throwable t) {
                Log.e("Sync", "Erreur synchronisation images", t);
            }
        });
    }

    public void synchronizeMotsCles() {
        Call<List<MotCleEntity>> call = apiService.getMotsCles();
        call.enqueue(new Callback<List<MotCleEntity>>() {
            @Override
            public void onResponse(Call<List<MotCleEntity>> call, Response<List<MotCleEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> db.motCleDao().insertAllMotCle(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<MotCleEntity>> call, Throwable t) {
                Log.e("Sync", "Erreur synchronisation mots-clés", t);
            }
        });
    }

    public void synchronizeThemes() {
        Call<List<ThemeEntity>> call = apiService.getThemes();
        call.enqueue(new Callback<List<ThemeEntity>>() {
            @Override
            public void onResponse(Call<List<ThemeEntity>> call, Response<List<ThemeEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> db.themeDao().insertAllThemes(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<ThemeEntity>> call, Throwable t) {
                Log.e("Sync", "Erreur synchronisation thèmes", t);
            }
        });
    }

    public void synchronizeDocuments() {
        Call<List<DocumentEntity>> call = apiService.getDocuments();
        call.enqueue(new Callback<List<DocumentEntity>>() {
            @Override
            public void onResponse(Call<List<DocumentEntity>> call, Response<List<DocumentEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> db.documentDao().insertAllDocuments(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<DocumentEntity>> call, Throwable t) {
                Log.e("Sync", "Erreur synchronisation documents", t);
            }
        });
    }
}
