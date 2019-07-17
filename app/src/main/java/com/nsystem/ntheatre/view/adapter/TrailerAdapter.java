package com.nsystem.ntheatre.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.model.TrailerModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    public interface OnItemClickListener {
        void onTrailerItemClicked(TrailerModel trailerModel);
    }

    private List<TrailerModel> trailerCollection;
    private OnItemClickListener onItemClickListener;

    private final LayoutInflater layoutInflater;

    @Inject
    public TrailerAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.trailerCollection = Collections.emptyList();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        final TrailerModel trailerModel = this.trailerCollection.get(position);
        holder.textViewTrailer.setText(trailerModel.getName());
        holder.textViewTrailer.setOnClickListener(view -> {
            this.onItemClickListener.onTrailerItemClicked(trailerModel);
        });
    }

    public void setTrailerCollection(Collection<TrailerModel> moviesModelCollection) {
        this.validateTrailersCollection(moviesModelCollection);
        this.trailerCollection = (List<TrailerModel>) moviesModelCollection;
        this.notifyDataSetChanged();
    }

    private void validateTrailersCollection(Collection<TrailerModel> moviesModelCollection) {
        if (moviesModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (this.trailerCollection != null) ? this.trailerCollection.size() : 0;
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_row_trailer)
        TextView textViewTrailer;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
