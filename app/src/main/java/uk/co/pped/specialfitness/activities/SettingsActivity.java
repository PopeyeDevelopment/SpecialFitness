package uk.co.pped.specialfitness.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.fragments.AbstractBaseFragment;

/**
 * Created by matthewi on 08/09/2017.
 */
public class SettingsActivity extends AbstractBaseActivity implements AbstractBaseFragment.OnFragmentInteractionListener {

    public SettingsActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setSupportedActionBar();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
