package app.support_visite_fdl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsLieuFragment extends Fragment {

    private static final String ARG_LIEU_INDEX = "lieu_index";

    public static DetailsLieuFragment newInstance(int index) {
        DetailsLieuFragment fragment = new DetailsLieuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LIEU_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsLieuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_lieu, container, false);
        LinearLayout containerImages = view.findViewById(R.id.containerImages);

        int index = getArguments().getInt(ARG_LIEU_INDEX, 0);

        int[][] lieuImages = {
                {R.drawable.app_img_scenes_2_11, R.drawable.app_img_scenes_16_2},
                {R.drawable.app_img_scenes_2_12, R.drawable.app_img_scenes_2_17},
                {R.drawable.app_img_scenes_2_18},
        };

        if (index < lieuImages.length) {
            for (int imageRes : lieuImages[index]) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(imageRes);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 16, 0, 16);
                imageView.setLayoutParams(layoutParams);

                // 👇 Ajouter le clic pour afficher en plein écran
                imageView.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), FullScreenImageActivity.class);
                    intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_RES_ID, imageRes);
                    startActivity(intent);
                });

                containerImages.addView(imageView);
            }
        }

        return view;
    }
}
