package app.support_visite_fdl;

import android.net.Uri;

public class Document {
    private final String title;
    private final Uri uri;

    public Document(String title, Uri uri) {
        this.title = title;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public Uri getUri() {
        return uri;
    }
}
