package app.support_visite_fdl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThemeButtonAdapter extends RecyclerView.Adapter<ThemeButtonAdapter.ThemeButtonViewHolder> {

    private final List<Theme> themes;
    private final Context context;

    public ThemeButtonAdapter(Context context, List<Theme> themes) {
        this.context = context;
        this.themes = themes;
    }

    @NonNull
    @Override
    public ThemeButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theme_button, parent, false);
        return new ThemeButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeButtonViewHolder holder, int position) {
        Theme theme = themes.get(position);
        holder.themeButton.setText(theme.getName());

        holder.themeButton.setOnClickListener(v -> {
            // Action à effectuer lorsque le bouton est cliqué
            // Par exemple, ouvrir une nouvelle activité ou un fragment avec les documents du thème
            Toast.makeText(context, "Thème sélectionné: " + theme.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    static class ThemeButtonViewHolder extends RecyclerView.ViewHolder {
        Button themeButton;

        public ThemeButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            themeButton = itemView.findViewById(R.id.theme_button);
        }
    }
}

