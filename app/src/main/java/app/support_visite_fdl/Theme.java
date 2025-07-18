package app.support_visite_fdl;

import java.util.List;

public class Theme {
    private final String name;
    private final List<Document> documents;

    public Theme(String name, List<Document> documents) {
        this.name = name;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}

