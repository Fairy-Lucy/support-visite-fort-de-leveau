package app.support_visite_fdl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private final List<Document> documents;

    public DocumentAdapter(List<Document> documents) {
        this.documents = documents;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document doc = documents.get(position);
        holder.title.setText(doc.getTitle());

        // Pour les PDF, on peut afficher une icône ou une miniature avec Glide si tu as un aperçu en image
        Glide.with(holder.image.getContext())
                .load(R.drawable.ic_pdf_icon) // ou une image générée du PDF
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.document_preview);
            title = itemView.findViewById(R.id.document_title);
        }
    }
}
