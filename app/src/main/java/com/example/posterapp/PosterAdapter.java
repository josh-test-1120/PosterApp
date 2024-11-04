package com.example.posterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the PosterAdaptor that is used in the RecycleView Adaptor
 * This extends the RecycleView.Adaptor class
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    // These are the private variables
    private List<Poster> posters;
    private PosterListener posterListener;

    /**
     * This is the getSelectedPosters method that returns the Posters selected
     * @return A list of Posters
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster: this.posters) {
            if (poster.isSelected) selectedPosters.add(poster);
        }
        return selectedPosters;
    }

    /**
     * This is the constructor for the Adaptor
     * @param posters This is a List of Posters
     * @param posterListener This is an object that implements PosterListener
     */
    public PosterAdapter(List<Poster> posters, PosterListener posterListener) {
        this.posters = posters;
        this.posterListener = posterListener;
    }

    /**
     * This is an override of the onCreateViewHolder method
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new PosterViewHolder class that has its layout inflated
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_item_container,parent,false));
    }

    /**
     * This is an override of the onBindViewHolder method
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posters.get(position));
    }

    /**
     * This is an override of the getItemCount method
     * @return the size of the posters list
     */
    @Override
    public int getItemCount() {
        return posters.size();
    }

    /**
     * This is the PosterViewHolder class that extends the RecycleView.ViewHolder
     */
    public class PosterViewHolder extends RecyclerView.ViewHolder {
        // These are the objects in the view
        ConstraintLayout layoutPoster;
        View viewBackground;
        RoundedImageView posterView;
        TextView posterName, posterCreatedBy, posterDescrption;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * This is the PosterViewHolder constructor
         * @param itemView the view that is to be inflated
         */
        public PosterViewHolder(@NonNull View itemView) {
            // Run the parent class constructor
            super(itemView);
            // Bind the objects to the view IDs
            layoutPoster = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            posterView = itemView.findViewById(R.id.imagePoster);
            posterName = itemView.findViewById(R.id.posterName);
            posterCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            posterDescrption = itemView.findViewById(R.id.textDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * This is the bind Poster method that will bind actions
         * and listeners to the poster
         * @param poster this is the poster to bind actions to
         */
        void bindPoster(final Poster poster) {
            // Update the view with the poster information
            posterView.setImageResource(poster.image);
            posterName.setText(poster.name);
            posterCreatedBy.setText(poster.createdBy);
            posterDescrption.setText(poster.description);
            ratingBar.setRating(poster.rating);
            // If poster is selected update selected background
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }
            // Else update without selected background
            else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            /**
             * This is the setOnClickListener that Main onstraint Layout will have
             */
            layoutPoster.setOnClickListener(new View.OnClickListener() {
                /**
                 * Override the onClick method
                 * @param view The view that is presented from the click
                 */
                @Override
                public void onClick(View view) {
                    // If poster is selected update selected background
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        // Sanity check
                        if (getSelectedPosters().isEmpty()) posterListener.onPosterAction(false);
                    }
                    // Else update without selected background
                    else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        posterListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
