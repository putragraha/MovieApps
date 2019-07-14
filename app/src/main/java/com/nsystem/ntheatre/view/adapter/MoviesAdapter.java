package com.nsystem.ntheatre.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public interface OnItemClickListener {
        void onMovieItemClicked(MovieModel movieModel);
    }

    private List<MovieModel> moviesCollection;
    private OnItemClickListener onItemClickListener;

    private final LayoutInflater layoutInflater;

    @Inject
    public MoviesAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.moviesCollection = Collections.emptyList();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieModel movieModel = this.moviesCollection.get(position);
        Picasso.get().load(movieModel.getPosterPath()).into(holder.imageViewPoster);
        holder.imageViewPoster.setOnClickListener(view -> {
            if (MoviesAdapter.this.onItemClickListener != null) {
                MoviesAdapter.this.onItemClickListener.onMovieItemClicked(movieModel);
            }
        });
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.moviesCollection != null) ? this.moviesCollection.size() : 0;
    }

    public void setMoviesCollection(Collection<MovieModel> moviesModelCollection) {
        this.validateUsersCollection(moviesModelCollection);
        this.moviesCollection = (List<MovieModel>) moviesModelCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<MovieModel> moviesModelCollection) {
        if (moviesModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView imageViewPoster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
