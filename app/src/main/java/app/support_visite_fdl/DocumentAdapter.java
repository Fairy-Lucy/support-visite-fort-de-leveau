package app.support_visite_fdl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private static final String MIME_TYPE_PDF = "application/pdf";
    private final List<Document> documents;
    private final Context context;

    public DocumentAdapter(Context context, List<Document> documents) {
        this.context = context;
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

        generatePdfPreview(doc.getUri(), holder.image);

        holder.itemView.setOnClickListener(v -> {
            String fileName = doc.getTitle();
            if (!fileName.endsWith(".pdf")) {
                fileName += ".pdf";
            }

            Log.d("DocumentAdapter", "Trying to open file: " + fileName);

            File file = new File(context.getCacheDir(), fileName);

            try {
                try (InputStream in = context.getAssets().open("documentation/reunion/" + fileName);
                     FileOutputStream out = new FileOutputStream(file)) {

                    byte[] buffer = new byte[1024];
                    int read;
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }

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
                    Log.e("DocumentAdapter", "Error opening file: " + fileName, e);
                    Toast.makeText(context, "Erreur lors de la préparation du fichier PDF.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("DocumentAdapter", "Error with file: " + fileName, e);
            }
        });
    }

    private void generatePdfPreview(Uri pdfUri, ImageView imageView) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                String filePath = "documentation/reunion/" + pdfUri.getLastPathSegment();
                InputStream inputStream = context.getAssets().open(filePath);
                File tempFile = File.createTempFile("temp", ".pdf", context.getCacheDir());

                try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }

                ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY);
                PdfRenderer pdfRenderer = new PdfRenderer(fileDescriptor);
                PdfRenderer.Page page = pdfRenderer.openPage(0);

                int width = page.getWidth() / 4;
                int height = page.getHeight() / 4;
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                int cropHeight = height / 2;
                Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, cropHeight);

                handler.post(() -> {
                    imageView.setImageBitmap(croppedBitmap);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                });

                page.close();
                pdfRenderer.close();
                fileDescriptor.close();
            } catch (IOException e) {
                e.printStackTrace();
                handler.post(() -> Glide.with(context).load(R.drawable.ic_pdf_icon).into(imageView));
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


