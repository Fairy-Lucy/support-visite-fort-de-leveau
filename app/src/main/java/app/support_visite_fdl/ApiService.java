package app.support_visite_fdl;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import app.support_visite_fdl.data.entities.DocumentEntity;
import app.support_visite_fdl.data.entities.ImageEntity;
import app.support_visite_fdl.data.entities.LieuEntity;
import app.support_visite_fdl.data.entities.MotCleEntity;
import app.support_visite_fdl.data.entities.ThemeEntity;

public interface ApiService {


    @GET("lieux")
    Call<List<LieuEntity>> getLieux();

    @POST("lieux")
    Call<LieuEntity> createLieu(@Body LieuEntity lieu);

    @PUT("lieux/{id}")
    Call<LieuEntity> updateLieu(@Path("id") long id, @Body LieuEntity lieu);

    @DELETE("lieux/{id}")
    Call<Void> deleteLieu(@Path("id") long id);


    @GET("images")
    Call<List<ImageEntity>> getImages();

    @POST("images")
    Call<ImageEntity> createImage(@Body ImageEntity image);

    @PUT("images/{id}")
    Call<ImageEntity> updateImage(@Path("id") long id, @Body ImageEntity image);

    @DELETE("images/{id}")
    Call<Void> deleteImage(@Path("id") long id);


    @GET("motscles")
    Call<List<MotCleEntity>> getMotsCles();

    @POST("motscles")
    Call<MotCleEntity> createMotCle(@Body MotCleEntity motCle);

    @PUT("motscles/{id}")
    Call<MotCleEntity> updateMotCle(@Path("id") long id, @Body MotCleEntity motCle);

    @DELETE("motscles/{id}")
    Call<Void> deleteMotCle(@Path("id") long id);


    @GET("themes")
    Call<List<ThemeEntity>> getThemes();

    @POST("themes")
    Call<ThemeEntity> createTheme(@Body ThemeEntity theme);

    @PUT("themes/{id}")
    Call<ThemeEntity> updateTheme(@Path("id") long id, @Body ThemeEntity theme);

    @DELETE("themes/{id}")
    Call<Void> deleteTheme(@Path("id") long id);


    @GET("documents")
    Call<List<DocumentEntity>> getDocuments();

    @POST("documents")
    Call<DocumentEntity> createDocument(@Body DocumentEntity document);

    @PUT("documents/{id}")
    Call<DocumentEntity> updateDocument(@Path("id") long id, @Body DocumentEntity document);

    @DELETE("documents/{id}")
    Call<Void> deleteDocument(@Path("id") long id);
}
