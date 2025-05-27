package app.support_visite_fdl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class LieuFragment extends Fragment {

    private String[] lieux = {
            " 1 - la défaite de 1870",
            " 2 - la place forte de Maubeuge",
            " 3 - Le fort de Leveau",
            " 4 - La bataille de Maubeuge",
            " 5 - Le tunnel des emmurés",
            " 6 - Le soldat français de 1914",
            " 7 - La chambrée",
            " 8 - L'armée bleu horizon",
            " 9 - Le corps expéditionnaire américain",
            " 10 - Le service de santé",
            " 11 - Les prisonniers de guerre",
            " 12 - L'artillerie",
            " 13 - Le tunnel central",
            " 14 - Le ravitaillement et l'alimentation",
            " 15 - Le monde des tranchées",
            " 16 - Le stockage des munitions",
            " 17 - La simple caponière",
            " 18 - La double caponière",
            " 19 - Le massif bétonné",
            " 20 - La stèle commémorative",
            " 21 - La matinée de 7 septembre",
            " 22 - Le premier obus",
            " 23 - Les chambrées effondrées",
            " 24 - La chute du fort",
            " A - Feignies et le bassin de la Sambre durant le second conflit mondial",
            " B - Mémorial W.W PATTON"
    };

    public LieuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lieu, container, false);
        ListView listView = view.findViewById(R.id.listViewLieux);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, lieux);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            DetailsLieuFragment fragment = DetailsLieuFragment.newInstance(position);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
