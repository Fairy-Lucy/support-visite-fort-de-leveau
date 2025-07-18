package app.support_visite_fdl;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemeDocumentsFragment extends Fragment {

    private String themeName;
    private RecyclerView recyclerView;
    private DocumentAdapter documentAdapter;

    public static ThemeDocumentsFragment newInstance(String themeName) {
        ThemeDocumentsFragment fragment = new ThemeDocumentsFragment();
        Bundle args = new Bundle();
        args.putString("themeName", themeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            themeName = getArguments().getString("themeName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme_documents, container, false);

        TextView themeTitle = view.findViewById(R.id.theme_title);
        themeTitle.setText(themeName);

        recyclerView = view.findViewById(R.id.recycler_view_documents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Document> documents = getDocumentsForTheme(themeName);
        documentAdapter = new DocumentAdapter(getContext(), documents);
        recyclerView.setAdapter(documentAdapter);

        return view;
    }

    private List<Document> getDocumentsForTheme(String themeName) {
        List<Document> documents = new ArrayList<>();

        if (themeName.equals("Réunion")) {
            documents.add(new Document("Réunion fortif n°3", Uri.parse("réunion/réunion fortif n°3.pdf"), themeName));
            documents.add(new Document("Réunion fortif n°4", Uri.parse("réunion/réunion fortif n°4.pdf"), themeName));
        } else if (themeName.equals("Histoire")) {
            documents.add(new Document("Réunion fortif n°5", Uri.parse("réunion/réunion fortif n°5.pdf"), themeName));
            documents.add(new Document("Réunion fortif n°6", Uri.parse("réunion/réunion fortif n°6.pdf"), themeName));
        } else if (themeName.equals("Guerre")) {
            documents.add(new Document("Réunion fortif n°7", Uri.parse("réunion/réunion fortif n°7.pdf"), themeName));
            documents.add(new Document("Réunion fortif n°8", Uri.parse("réunion/réunion fortif n°8.pdf"), themeName));
        }

        return documents;
    }
}
