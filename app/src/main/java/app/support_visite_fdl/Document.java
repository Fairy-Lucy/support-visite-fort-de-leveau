package app.support_visite_fdl;

import android.net.Uri;

public class Document {
    private final String title;
    private final Uri uri;
    private final String theme;

    public Document(String title, Uri uri, String theme) {
        this.title = title;
        this.uri = uri;
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public Uri getUri() {
        return uri;
    }

    public String getTheme() {
        return theme;
    }
}
