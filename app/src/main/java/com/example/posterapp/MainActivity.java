package com.example.posterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Main Activity view that implements PosterListener
 */
public class MainActivity extends AppCompatActivity implements PosterListener {
    // Private variables
    private Button buttonWatchList;

    /**
     * This is an override of the onCreate method
     * @param savedInstanceState the current saved state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind the variables to the view IDs
        RecyclerView postersView = findViewById(R.id.postersRecycleView);
        buttonWatchList = findViewById(R.id.buttonWatchList);

        // Prepare the data
        List<Poster> posters = new ArrayList<>();

        // Create the posters
        Poster poster1 = new Poster("Civil War","Alex Garland",
                "Movie about modern Civil War in America",R.drawable.civilwar,5f);

        Poster poster2 = new Poster("Avengers","Anthony Russo, Joe Russo, and Joss Whedon",
                "Another installment in the Avengers series",R.drawable.avengers,4f);

        Poster poster3 = new Poster("Elemental","Peter Sohn",
                "Animated series about a world of Elemental creatures",R.drawable.elemental,4f);

        Poster poster4 = new Poster("Gladiator II","Ridley Scott",
                "The sequel to the Roman story of a soldier turned gladiator",R.drawable.gladiator,5f);

        Poster poster5 = new Poster("Thor: Love and Thunder","Taika Waititi",
                "An unusual adaptation of the Thor storyline",R.drawable.thorlovethunder,3f);

        Poster poster6 = new Poster("Guardians of the Galaxy","James Gunn",
                "A story about a human born to an intergalactic destiny",R.drawable.gotg,4f);

        Poster poster7 = new Poster("New Mutants","Josh Boone",
                "A story of teenage mutants discovering who and what they are",R.drawable.newmutants,4f);

        Poster poster8 = new Poster("Inside Out 2","Pete Docter",
                "A story of a teenage girl coming of age through the lens of her inner mind",R.drawable.insideout,5f);

        Poster poster9 = new Poster("Furiosa","George Miller",
                "Another installment in the Mad Max saga",R.drawable.furiosa,4f);

        Poster poster10 = new Poster("Wild Robot","Chris Sanders",
                "A story of a shipwrecked robot that must adapt to survive",R.drawable.wildrobot,3f);

        // Add all the movie posters
        posters.add(poster1);
        posters.add(poster2);
        posters.add(poster3);
        posters.add(poster4);
        posters.add(poster5);
        posters.add(poster6);
        posters.add(poster7);
        posters.add(poster8);
        posters.add(poster9);
        posters.add(poster10);

        // Set the adaptor with the current posters
        final PosterAdapter posterAdapter = new PosterAdapter(posters,this);
        postersView.setAdapter(posterAdapter);
        //
        /**
         * Bind the onClickListener to the buttonWatchList object
         */
        buttonWatchList.setOnClickListener(new View.OnClickListener() {
            /**
             * This is an override of the onClick method
             * @param view The view that was clicked on
             */
            @Override
            public void onClick(View view) {
                // Get the selected posters
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();
                // Create a built string object
                StringBuilder posterNames = new StringBuilder();
                // Iterate through the selected posters and append their name
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i == 0) posterNames.append(selectPosters.get(i).name);
                    else posterNames.append("\n").append(selectPosters.get(i).name);
                }
                // Present the toast with the build string of names
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This is an override of the onPosterAction method from the interface
     * Will update the add Watch List button visibility
     * @param isSelected A boolean that reflects the current state of selection
     * for the poster
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected) buttonWatchList.setVisibility(View.VISIBLE);
        else buttonWatchList.setVisibility(View.GONE);
    }
}