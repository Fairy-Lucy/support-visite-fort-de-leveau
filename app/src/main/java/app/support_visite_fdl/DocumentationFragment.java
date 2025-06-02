package app.support_visite_fdl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

public class DocumentationFragment extends Fragment {

    private RecyclerView recyclerView;
    private DocumentAdapter adapter;

    public DocumentationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documentation, container, false);
        recyclerView = view.findViewById(R.id.documents_recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2)); // grille 2 colonnes
        adapter = new DocumentAdapter(getDummyDocuments()); // à remplacer par une vraie source
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Document> getDummyDocuments() {
        List<Document> list = new ArrayList<>();
        list.add(new Document("Fiche technique", Uri.parse("file:///.../fiche.pdf")));
        list.add(new Document("Plan d’intervention", Uri.parse("file:///.../plan.pdf")));
        return list;
    }
}
