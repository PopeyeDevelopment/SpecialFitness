package uk.co.pped.specialfitness.activities.settings;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;


import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.AbstractBaseActivity;
import uk.co.pped.specialfitness.fragments.settings.AbstractBaseSettingsFragment;
import uk.co.pped.specialfitness.fragments.settings.SettingsUnitsFragment;

/**
 * Created by matthewi on 29/09/2017.
 */

public class SettingsFragmentHandler extends AbstractBaseActivity implements AbstractBaseSettingsFragment.OnFragmentInteractionListener {

    public static class SettingsFragmentTypes {
        public static final String FRAGMENT_TYPE_KEY = "fragment_type";

        public static final int FRAGMENT_TYPE_UNKNOW = -1;
        public static final int FRAGMENT_TYPE_UNITS = 0;
        public static final int FRAGMENT_TYPE_FIRST_WEEKDAY = 1;
    }

    public SettingsFragmentHandler() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_unit_activity);
        setSupportedActionBar();
        setUpFragment();
    }

    private void setUpFragment() {
        int fragmentType = getIntent().getIntExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOW);
        Fragment fragment = null;
        switch (fragmentType) {
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNITS:
                fragment = new SettingsUnitsFragment();
                break;
            default:
                break;
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}
