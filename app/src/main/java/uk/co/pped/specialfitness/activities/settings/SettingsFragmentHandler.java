package uk.co.pped.specialfitness.activities.settings;

import static uk.co.pped.specialfitness.fragments.settings.SettingsFragment.SettingsFragmentTypes;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.AbstractBaseActivity;
import uk.co.pped.specialfitness.fragments.settings.AbstractBaseSettingsFragment;
import uk.co.pped.specialfitness.fragments.settings.SettingsFragment;

/**
 * Created by matthewi on 29/09/2017.
 */

public class SettingsFragmentHandler extends AbstractBaseActivity implements AbstractBaseSettingsFragment.OnFragmentInteractionListener {

    /** Default value to be used. */
    public static final int defVal = -1;

    public static final int[][] STATES = new int[][] {
            new int[] { android.R.attr.state_enabled}, // enabled
            new int[] {-android.R.attr.state_enabled}, // disabled
    };

    public SettingsFragmentHandler() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragement_handler);
        setSupportedActionBar();
        setUpFragment();
    }

    private void setUpFragment() {
        int fragmentType = getIntent().getIntExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOWN);
        Fragment fragment = null;
        switch (fragmentType) {
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOWN:
                throw new IllegalStateException("getPreferenceIdToLoad: Attempted to load an unknown Settings Fragment.");
            // If we have any custom Fragment requires then we can load them here.
            default:
                fragment = new SettingsFragment();
                break;
        }

        FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}
