package app.support_visite_fdl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenImageActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_RES_ID = "imageResId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView imageView = findViewById(R.id.fullscreen_image);

        int imageResId = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, -1);
        if (imageResId != -1) {
            imageView.setImageResource(imageResId);
        }

        // Fermer l’activité au clic
        imageView.setOnClickListener(v -> finish());
    }
}
