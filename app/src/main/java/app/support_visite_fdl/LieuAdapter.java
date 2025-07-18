package app.support_visite_fdl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.support_visite_fdl.data.entities.LieuEntity;

public class LieuAdapter extends ArrayAdapter<LieuEntity> {

    public LieuAdapter(Context context, List<LieuEntity> lieux) {
        super(context, 0, lieux);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LieuEntity lieu = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lieu, parent, false);
        }

        TextView textViewLieu = convertView.findViewById(R.id.textViewLieu);
        textViewLieu.setText(lieu.getNom());

        return convertView;
    }
}
