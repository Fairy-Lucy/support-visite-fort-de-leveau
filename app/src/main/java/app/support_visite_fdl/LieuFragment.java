package app.support_visite_fdl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import app.support_visite_fdl.data.AppDatabase;
import app.support_visite_fdl.data.AppDatabaseInstance;
import app.support_visite_fdl.data.entities.LieuEntity;

public class LieuFragment extends Fragment {

    private List<LieuEntity> lieux = new ArrayList<>();
    private ArrayAdapter<LieuEntity> adapter;

    public LieuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lieu, container, false);
        ListView listView = view.findViewById(R.id.listViewLieux);
        Button refreshButton = view.findViewById(R.id.refreshButton);

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, lieux);
        listView.setAdapter(adapter);

        loadLieuxFromDatabase();

        refreshButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Rechargement des lieux...", Toast.LENGTH_SHORT).show();
            loadLieuxFromDatabase();
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            DetailsLieuFragment fragment = DetailsLieuFragment.newInstance(lieux.get(position).id);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void loadLieuxFromDatabase() {
        new Thread(() -> {
            AppDatabase db = AppDatabaseInstance.getDatabase(getContext());
            lieux.clear();
            lieux.addAll(db.lieuDao().getAllLieux());

            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        }).start();
    }
}
