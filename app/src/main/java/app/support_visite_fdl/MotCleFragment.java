package app.support_visite_fdl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.ImageEntity;

public class MotCleFragment extends Fragment {

    private EditText searchBar;
    private RecyclerView recyclerView;
    private ImageAdapter adapter; // à créer
    private AppDatabase db;

    public MotCleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mot_cle, container, false);

        searchBar = view.findViewById(R.id.search_bar);
        recyclerView = view.findViewById(R.id.recycler_view_results);
        Button searchButton = view.findViewById(R.id.search_button);

        db = AppDatabaseInstance.getDatabase(requireContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ImageAdapter(); // Tu crées cette classe
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> {
            String texte = searchBar.getText().toString().trim();
            if (!texte.isEmpty()) {
                List<String> mots = Arrays.asList(texte.split("\\s+"));
                new Thread(() -> {
                    List<ImageEntity> resultats = db.imageDao().chercherImagesParMotsCle(mots);
                    requireActivity().runOnUiThread(() -> adapter.setImages(resultats));
                }).start();
            }
        });

        return view;
    }
}
