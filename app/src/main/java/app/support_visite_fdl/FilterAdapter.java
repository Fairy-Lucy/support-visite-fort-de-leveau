package app.support_visite_fdl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

    private List<MotCleWithCount> filters;
    private Set<String> selectedFilters = new HashSet<>();

    public FilterAdapter(List<MotCleWithCount> filters) {
        this.filters = filters;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        MotCleWithCount filter = filters.get(position);
        holder.textView.setText(filter.getLibelle() + " (" + filter.getCount() + ")");
        holder.checkBox.setChecked(selectedFilters.contains(filter.getLibelle()));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedFilters.add(filter.getLibelle());
            } else {
                selectedFilters.remove(filter.getLibelle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public Set<String> getSelectedFilters() {
        return selectedFilters;
    }

    public void updateFilters(List<MotCleWithCount> newFilters) {
        filters.clear();
        filters.addAll(newFilters);
        notifyDataSetChanged();
    }

    static class FilterViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public static class MotCleWithCount {
        private String libelle;
        private int count;

        public MotCleWithCount(String libelle, int count) {
            this.libelle = libelle;
            this.count = count;
        }

        public String getLibelle() {
            return libelle;
        }

        public int getCount() {
            return count;
        }
    }
}
