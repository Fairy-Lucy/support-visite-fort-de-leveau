package app.support_visite_fdl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private final Context context;
    private final List<Theme> themes;

    public ThemeAdapter(Context context, List<Theme> themes) {
        this.context = context;
        this.themes = themes;
    }
    
    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theme, parent, false);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        Theme theme = themes.get(position);
        holder.themeName.setText(theme.getName());

        holder.documentsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.documentsRecyclerView.setAdapter(new DocumentAdapter(context, theme.getDocuments()));
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    static class ThemeViewHolder extends RecyclerView.ViewHolder {
        TextView themeName;
        RecyclerView documentsRecyclerView;

        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);
            themeName = itemView.findViewById(R.id.theme_name);
            documentsRecyclerView = itemView.findViewById(R.id.documents_recycler_view);
        }
    }
}

