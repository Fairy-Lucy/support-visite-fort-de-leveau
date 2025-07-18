package app.support_visite_fdl;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import app.support_visite_fdl.Document;
import app.support_visite_fdl.data.entities.DocumentEntity;

public class DocumentMapper {
    public static List<Document> fromEntities(List<DocumentEntity> entities) {
        List<Document> documents = new ArrayList<>();
        for (DocumentEntity entity : entities) {
            documents.add(new Document(
                    entity.getTitre(),
                    entity.getUri() != null ? Uri.parse(entity.getUri()) : null,
                    entity.getTheme()
            ));
        }
        return documents;
    }
}

