package uk.co.pped.specialfitness.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uk.co.pped.specialfitness.fragments.ProfileFragment;
import uk.co.pped.specialfitness.R;

public class ProfileActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
