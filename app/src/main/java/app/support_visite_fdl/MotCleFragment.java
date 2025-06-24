package app.support_visite_fdl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.ImageEntity;

public class MotCleFragment extends Fragment {

    private AutoCompleteTextView searchBar;
    private RecyclerView recyclerViewResults;
    private RecyclerView recyclerViewFilters;
    private ImageAdapter adapter;
    private FilterAdapter filterAdapter;
    private AppDatabase db;
    private ArrayAdapter<String> motsCleAdapter;
    private FloatingActionButton filterFab;
    private Button applyFiltersButton;

    public MotCleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mot_cle, container, false);

        searchBar = view.findViewById(R.id.search_bar);
        recyclerViewResults = view.findViewById(R.id.recycler_view_results);
        recyclerViewFilters = view.findViewById(R.id.recycler_view_filters);
        filterFab = view.findViewById(R.id.filter_fab);
        applyFiltersButton = view.findViewById(R.id.apply_filters_button);

        db = AppDatabaseInstance.getDatabase(requireContext());

        motsCleAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line);
        searchBar.setAdapter(motsCleAdapter);

        new Thread(() -> {
            List<String> motsCles = db.motCleDao().getAllMotsCles();
            requireActivity().runOnUiThread(() -> {
                motsCleAdapter.addAll(motsCles);
                filterAdapter = new FilterAdapter(motsCles);
                recyclerViewFilters.setAdapter(filterAdapter);
                recyclerViewFilters.setLayoutManager(new LinearLayoutManager(requireContext()));
            });
        }).start();

        recyclerViewResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ImageAdapter();
        recyclerViewResults.setAdapter(adapter);

        filterFab.setOnClickListener(v -> {
            if (recyclerViewFilters.getVisibility() == View.GONE) {
                recyclerViewFilters.setVisibility(View.VISIBLE);
                applyFiltersButton.setVisibility(View.VISIBLE);
            } else {
                recyclerViewFilters.setVisibility(View.GONE);
                applyFiltersButton.setVisibility(View.GONE);
            }
        });

        applyFiltersButton.setOnClickListener(v -> {
            Set<String> selectedFilters = filterAdapter.getSelectedFilters();
            new Thread(() -> {
                List<ImageEntity> resultats = db.imageDao().chercherImagesParMotsCle(new ArrayList<>(selectedFilters), selectedFilters.size());
                requireActivity().runOnUiThread(() -> {
                    adapter.setImages(resultats);
                    recyclerViewFilters.setVisibility(View.GONE);
                    applyFiltersButton.setVisibility(View.GONE);
                });
            }).start();
        });

        return view;
    }
}
