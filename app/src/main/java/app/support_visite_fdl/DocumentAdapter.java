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
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(doc.getUri(), MIME_TYPE_PDF);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                v.getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(v.getContext(), "Aucune application pour lire les PDF n'est disponible.", Toast.LENGTH_SHORT).show();
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
