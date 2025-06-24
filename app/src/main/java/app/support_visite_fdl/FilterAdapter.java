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

    private List<String> filters;
    private Set<String> selectedFilters = new HashSet<>();

    public FilterAdapter(List<String> filters) {
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
        String filter = filters.get(position);
        holder.textView.setText(filter);
        holder.checkBox.setChecked(selectedFilters.contains(filter));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedFilters.add(filter);
            } else {
                selectedFilters.remove(filter);
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

    static class FilterViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
