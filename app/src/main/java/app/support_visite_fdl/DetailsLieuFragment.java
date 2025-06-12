package app.support_visite_fdl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.ImageEntity;
import app.support_visite_fdl.data.relations.LieuImages;

public class DetailsLieuFragment extends Fragment {

    private static final String ARG_LIEU_ID = "lieu_id";

    public static DetailsLieuFragment newInstance(long lieuId) {
        DetailsLieuFragment fragment = new DetailsLieuFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_LIEU_ID, lieuId);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsLieuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_lieu, container, false);
        TextView lieuNameTextView = view.findViewById(R.id.lieuNameTextView);
        LinearLayout containerImages = view.findViewById(R.id.containerImages);

        long lieuId = getArguments().getLong(ARG_LIEU_ID, 0);

        new Thread(() -> {
            AppDatabase db = AppDatabaseInstance.getDatabase(getContext());
            LieuImages lieuImages = db.lieuDao().getLieuAvecImages(lieuId);

            requireActivity().runOnUiThread(() -> {
                lieuNameTextView.setText(lieuImages.lieu.nom);

                for (ImageEntity image : lieuImages.images) {
                    ImageView imageView = new ImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(0, 16, 0, 16);
                    imageView.setLayoutParams(layoutParams);

                    Glide.with(getContext())
                            .load(image.getUri())
                            .into(imageView);

                    imageView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), FullScreenImageActivity.class);
                        intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_URI, image.getUri());
                        intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_DESCRIPTION, image.description);
                        startActivity(intent);
                    });
                    containerImages.addView(imageView);
                }
            });
        }).start();

        return view;
    }
}

