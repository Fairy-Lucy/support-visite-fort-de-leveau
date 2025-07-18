package app.support_visite_fdl;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.ThemeEntity;
import app.support_visite_fdl.data.relations.ThemeDocuments;

public class DocumentationFragment extends Fragment {
    private RecyclerView themesRecyclerView;
    private ThemeAdapter themeAdapter;
    private List<ThemeDocuments> themeDocumentsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documentation, container, false);

        themesRecyclerView = view.findViewById(R.id.themes_recycler_view);
        themesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        themeAdapter = new ThemeAdapter(requireContext(), themeDocumentsList);
        themesRecyclerView.setAdapter(themeAdapter);

        loadThemesWithDocuments();

        return view;
    }

    private void loadThemesWithDocuments() {
        new Thread(() -> {
            AppDatabase db = AppDatabaseInstance.getDatabase(getContext());
            List<ThemeEntity> themes = db.themeDao().getAllThemes();

            for (ThemeEntity theme : themes) {
                ThemeDocuments themeDocuments = db.themeDao().getThemeWithDocuments(theme.id);
                themeDocumentsList.add(themeDocuments);
            }

            requireActivity().runOnUiThread(() -> themeAdapter.notifyDataSetChanged());
        }).start();
    }
}
