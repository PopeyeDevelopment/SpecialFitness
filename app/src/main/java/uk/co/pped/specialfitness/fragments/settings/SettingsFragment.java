package uk.co.pped.specialfitness.fragments.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;
import uk.co.pped.specialfitness.components.preferences.SettingsDefaultSwitchPreference;
import uk.co.pped.specialfitness.components.preferences.SettingsListPreferences;
import uk.co.pped.specialfitness.components.preferences.SettingsMultiSelectListPreference;
import uk.co.pped.specialfitness.utility.ApplicationHelper;
import uk.co.pped.specialfitness.utility.SettingsMultiSelectListTypes;
import uk.co.pped.specialfitness.utils.KeyUtils;

/**
 * Created by matthewi on 26/09/2017.
 */

public class SettingsFragment extends AbstractBaseSettingsFragment {

    public static class SettingsFragmentTypes {
        public static final String FRAGMENT_TYPE_KEY = "uk.co.pped.specialfitness.fragment_type";

        public static final int FRAGMENT_TYPE_UNKNOWN = -1;
        public static final int FRAGMENT_TYPE_UNITS_PREFERENCES = 0;
        public static final int FRAGMENT_TYPE_WEEK_PREFERENCES = 1;
    }

    private ActionBar actionBar;
    private SettingsFragmentHandler baseActivity;
    private int fragmentType;
    private  SharedPreferences sharedPreferences;

    public SettingsFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileActivity.
     */
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    private void initialize() {
        this.baseActivity = (SettingsFragmentHandler) getActivity();
        if (baseActivity != null) {
            this.actionBar = baseActivity.getSupportActionBar();
            if (baseActivity.getIntent() != null) {
                fragmentType = baseActivity.getIntent()
                        .getIntExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOWN);
            }
        }

        PreferenceManager.setDefaultValues(baseActivity.getBaseContext(), R.xml.week_preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationHelper.getContext());

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        addPreferencesFromResource(getPreferenceIdToLoad());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch(fragmentType) {
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOWN:
                // We should never make it here as SettingsFragmentHandler should catch this but just in case.
                throw new IllegalStateException("getPreferenceIdToLoad: Attempted to load an unknown Settings Fragment.");
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNITS_PREFERENCES:
                break;
            case SettingsFragmentTypes.FRAGMENT_TYPE_WEEK_PREFERENCES:
                setupWeeklyPreferenceConfig();
                break;
            default:
                break;
        }
    }

    private Integer getPreferenceIdToLoad() {
        switch(fragmentType) {
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNKNOWN:
                // We should never make it here as SettingsFragmentHandler should catch this but just in case.
                throw new IllegalStateException("getPreferenceIdToLoad: Attempted to load an unknown Settings Fragment.");
            case SettingsFragmentTypes.FRAGMENT_TYPE_UNITS_PREFERENCES:
                return R.xml.units_preferences;
            case SettingsFragmentTypes.FRAGMENT_TYPE_WEEK_PREFERENCES:
                return R.xml.week_preferences;
            default:
                return null;
        }
    }


    private void setupWeeklyPreferenceConfig() {
        final SettingsDefaultSwitchPreference defaultSwitch = (SettingsDefaultSwitchPreference) findPreference(KeyUtils.WEEK_DEFAULTS_KEY);
        final SettingsListPreferences weekdayStarts = (SettingsListPreferences) findPreference(KeyUtils.WEEKDAY_STARTS_KEY);
        final SettingsMultiSelectListPreference daysAbleToWorkoutList = (SettingsMultiSelectListPreference) findPreference(KeyUtils.AVAILABLE_WORKOUT_DAYS_KEY);

        List<Preference> preferencesContainer = new ArrayList<Preference>();

        preferencesContainer.add(weekdayStarts);
        preferencesContainer.add(daysAbleToWorkoutList);
        defaultSwitch.setPreferencesContainer(preferencesContainer);
    }
}
