package app.support_visite_fdl;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentationFragment extends Fragment {

    private RecyclerView recyclerView;
    private DocumentAdapter adapter;

    public DocumentationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documentation, container, false);
        recyclerView = view.findViewById(R.id.documents_recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Passer le contexte à l'adaptateur
        adapter = new DocumentAdapter(requireContext(), getDocumentsFromAssets());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Document> getDocumentsFromAssets() {
        AssetManager assetManager = requireContext().getAssets();
        List<Document> documentList = new ArrayList<>();

        try {
            // Liste tous les fichiers dans le dossier assets
            String[] fileList = assetManager.list("");
            if (fileList != null) {
                for (String fileName : fileList) {
                    if (fileName.endsWith(".pdf")) {
                        // Ajoute uniquement les fichiers PDF
                        Uri fileUri = Uri.parse("file:///android_asset/" + fileName);
                        documentList.add(new Document(fileName.replace(".pdf", ""), fileUri));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return documentList;
    }
}
