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

public class DocumentationFragment extends Fragment {

    private RecyclerView themesRecyclerView;
    private ThemeButtonAdapter themeButtonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documentation, container, false);

        themesRecyclerView = view.findViewById(R.id.themes_recycler_view);
        themesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Theme> themes = getThemesFromAssets();
        themeButtonAdapter = new ThemeButtonAdapter(requireContext(), themes);
        themesRecyclerView.setAdapter(themeButtonAdapter);

        return view;
    }   

    private List<Theme> getThemesFromAssets() {
        List<Theme> themeList = new ArrayList<>();
        themeList.add(new Theme("Thème 1", new ArrayList<Document>()));
        themeList.add(new Theme("Thème 2", new ArrayList<Document>()));
        return themeList;
    }
}
