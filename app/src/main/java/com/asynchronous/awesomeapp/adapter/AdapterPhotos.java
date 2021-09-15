package com.asynchronous.awesomeapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asynchronous.awesomeapp.Mode;
import com.asynchronous.awesomeapp.R;
import com.asynchronous.awesomeapp.activity.MainActivity;
import com.asynchronous.awesomeapp.model.PexelsModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewHolder> {

    private final Context context;
    private final List<PexelsModel> listItem;
    private final String layoutManagerType;
    private AdapterPhotos.OnItemClickListener onItemClickListener;

    public AdapterPhotos(Context context, List<PexelsModel> listItem, String layoutManagerType) {
        this.context = context;
        this.listItem = listItem;
        this.layoutManagerType = layoutManagerType;
    }

    @NonNull
    @Override
    public AdapterPhotos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        LinearLayout linearParent = view.findViewById(R.id.linearParent);
        if (layoutManagerType.equals(Mode.LINEAR_LAYOUT_MANAGER)) {
            linearParent.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            linearParent.setOrientation(LinearLayout.VERTICAL);
        }
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterPhotos.ViewHolder holder, int position) {

        holder.txtIdImage.setText(context.getString(R.string.photoId) + listItem.get(position).getId());
        holder.txtPhotographName.setText(listItem.get(position).getPhotographer());
        final String url = listItem.get(position).getSrc().getOriginal();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.imgPhotos);

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterPhotos.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgPhotos;
        private final TextView txtIdImage;
        private final TextView txtPhotographName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotos = itemView.findViewById(R.id.imgPhotos);
            txtIdImage = itemView.findViewById(R.id.txtIdImage);
            txtPhotographName = itemView.findViewById(R.id.txtPhotographName);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
