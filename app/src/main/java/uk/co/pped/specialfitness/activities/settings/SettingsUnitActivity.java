package uk.co.pped.specialfitness.activities.settings;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;



import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.AbstractBaseActivity;
import uk.co.pped.specialfitness.fragments.settings.AbstractBaseSettingsFragment;

/**
 * Created by matthewi on 29/09/2017.
 */

public class SettingsUnitActivity extends AbstractBaseActivity implements AbstractBaseSettingsFragment.OnFragmentInteractionListener {

    public SettingsUnitActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_unit_activity);
        setSupportedActionBar();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}
