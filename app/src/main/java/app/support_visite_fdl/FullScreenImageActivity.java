package app.support_visite_fdl;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_URI = "imageUri";
    public static final String EXTRA_IMAGE_DESCRIPTION = "imageDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView imageView = findViewById(R.id.fullscreen_image);
        TextView descriptionTextView = findViewById(R.id.image_description);
        ImageView backArrow = findViewById(R.id.back_arrow);

        String imageUri = getIntent().getStringExtra(EXTRA_IMAGE_URI);
        String imageDescription = getIntent().getStringExtra(EXTRA_IMAGE_DESCRIPTION);

        if (imageUri != null) {
            Glide.with(this)
                    .load(imageUri)
                    .into(imageView);
        }

        if (imageDescription != null) {
            descriptionTextView.setText(imageDescription);
        }

        backArrow.setOnClickListener(v -> finish());
    }
}
