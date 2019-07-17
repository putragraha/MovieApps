package com.nsystem.ntheatre.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nsystem.data.repository.source.network.RestApi;
import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.model.FavouriteModel;
import com.nsystem.ntheatre.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteMoviesAdapter extends RecyclerView.Adapter<FavouriteMoviesAdapter.FavouriteMovieViewHolder> {

    public interface OnItemClickListener {
        void onFavouriteMovieItemClicked(FavouriteModel favouriteModel);
    }

    private List<FavouriteModel> favouriteMoviesCollection;
    private OnItemClickListener onItemClickListener;

    private final LayoutInflater layoutInflater;

    @Inject
    public FavouriteMoviesAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.favouriteMoviesCollection = Collections.emptyList();
    }

    @NonNull
    @Override
    public FavouriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_movie, parent, false);
        return new FavouriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMovieViewHolder holder, int position) {
        final FavouriteModel favouriteModel = this.favouriteMoviesCollection.get(position);
        Picasso.get().load(favouriteModel.getPosterPath()).into(holder.imageViewPoster);

        holder.imageViewPoster.setOnClickListener(view -> {
            if (FavouriteMoviesAdapter.this.onItemClickListener != null) {
                FavouriteMoviesAdapter.this.onItemClickListener.onFavouriteMovieItemClicked(favouriteModel);
            }
        });
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.favouriteMoviesCollection != null) ? this.favouriteMoviesCollection.size() : 0;
    }

    public void setFavouriteMoviesCollection(Collection<FavouriteModel> favouriteModelCollection) {
        this.validateFavouriteMoviesCollection(favouriteModelCollection);
        this.favouriteMoviesCollection = (List<FavouriteModel>) favouriteModelCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateFavouriteMoviesCollection(Collection<FavouriteModel> moviesModelCollection) {
        if (moviesModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class FavouriteMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView imageViewPoster;

        FavouriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
