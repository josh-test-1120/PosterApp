package com.example.posterapp;

/**
 * This is the Poster interface that defines the actions Posters should have
 * for listeners
 */
public interface PosterListener {
    /**
     * This is the onPosterAction method
     * @param isSelected A boolean that reflects the current state of selection
     * for the poster
     */
    void onPosterAction(Boolean isSelected);
}
