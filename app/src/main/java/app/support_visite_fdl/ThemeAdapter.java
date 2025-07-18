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

import app.support_visite_fdl.data.relations.ThemeDocuments;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private final Context context;
    private final List<ThemeDocuments> themeDocumentsList;

    public ThemeAdapter(Context context, List<ThemeDocuments> themeDocumentsList) {
        this.context = context;
        this.themeDocumentsList = themeDocumentsList;
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
        ThemeDocuments themeDocuments = themeDocumentsList.get(position);
        holder.themeName.setText(themeDocuments.theme.getNom());

        List<Document> documents = DocumentMapper.fromEntities(themeDocuments.documents);
        DocumentAdapter documentAdapter = new DocumentAdapter(context, documents);
        holder.documentsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.documentsRecyclerView.setAdapter(documentAdapter);
    }

    @Override
    public int getItemCount() {
        return themeDocumentsList.size();
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
