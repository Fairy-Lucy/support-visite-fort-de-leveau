package app.support_visite_fdl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DocumentViewerActivity extends AppCompatActivity {

    private static final String FILENAME = "report.pdf";

    private ImageView imageViewPdf;
    private FloatingActionButton prePageButton;
    private FloatingActionButton nextPageButton;

    private int pageIndex;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pdf_viewer);

        imageViewPdf = findViewById(R.id.pdf_image);
        prePageButton = findViewById(R.id.button_pre_doc);
        nextPageButton = findViewById(R.id.button_next_doc);

        pageIndex = 0;

        prePageButton.setOnClickListener(v -> onPreviousDocClick());
        nextPageButton.setOnClickListener(v -> onNextDocClick());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStart() {
        super.onStart();
        try {
            openRenderer(getApplicationContext());
            showPage(pageIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStop() {
        try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onPreviousDocClick() {
        showPage(currentPage.getIndex() - 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onNextDocClick() {
        showPage(currentPage.getIndex() + 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer(Context context) throws IOException {
        File file = new File(context.getCacheDir(), FILENAME);
        if (!file.exists()) {
            try (InputStream asset = context.getAssets().open(FILENAME);
                 FileOutputStream output = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int size;
                while ((size = asset.read(buffer)) != -1) {
                    output.write(buffer, 0, size);
                }
            }
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        if (parcelFileDescriptor != null) {
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeRenderer() throws IOException {
        if (currentPage != null) {
            currentPage.close();
        }
        if (pdfRenderer != null) {
            pdfRenderer.close();
        }
        if (parcelFileDescriptor != null) {
            parcelFileDescriptor.close();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showPage(int index) {
        if (pdfRenderer.getPageCount() <= index || index < 0) return;

        if (currentPage != null) {
            currentPage.close();
        }
        currentPage = pdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(), Bitmap.Config.ARGB_8888);
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        imageViewPdf.setImageBitmap(bitmap);
        updateUi();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUi() {
        int index = currentPage.getIndex();
        int pageCount = pdfRenderer.getPageCount();
        prePageButton.setEnabled(index > 0);
        nextPageButton.setEnabled(index + 1 < pageCount);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getPageCount() {
        return pdfRenderer.getPageCount();
    }
}
