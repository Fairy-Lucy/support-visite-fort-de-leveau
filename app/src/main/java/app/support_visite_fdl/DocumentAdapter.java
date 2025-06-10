package app.support_visite_fdl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private static final String MIME_TYPE_PDF = "application/pdf";
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

        Glide.with(holder.image.getContext())
                .load(R.drawable.ic_pdf_icon)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            File file = new File(context.getCacheDir(), doc.getTitle() + ".pdf");

            // Copier le fichier depuis les assets vers le cache
            try (InputStream in = context.getAssets().open(doc.getTitle() + ".pdf");
                 FileOutputStream out = new FileOutputStream(file)) {

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                // Utiliser FileProvider pour obtenir un URI sécurisé
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, MIME_TYPE_PDF);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "Aucune application pour lire les PDF n'est disponible.", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Erreur lors de la préparation du fichier PDF.", Toast.LENGTH_SHORT).show();
            }
        });
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

