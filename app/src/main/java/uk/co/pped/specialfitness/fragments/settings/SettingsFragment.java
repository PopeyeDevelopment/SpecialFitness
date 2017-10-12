package uk.co.pped.specialfitness.fragments.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.AbstractBaseActivity;
import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;
import uk.co.pped.specialfitness.components.preferences.PPEDDefaultSwitchPreference;
import uk.co.pped.specialfitness.utility.ApplicationHelper;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
        final PPEDDefaultSwitchPreference defaultSwitch = (PPEDDefaultSwitchPreference) findPreference(KeyUtils.WEEK_DEFAULTS_KEY);
        final ListPreference weekdayStarts = (ListPreference) findPreference(KeyUtils.WEEKDAY_STARTS_KEY);
        final MultiSelectListPreference daysAbleToWorkoutList = (MultiSelectListPreference) findPreference(KeyUtils.AVAILABLE_WORKOUT_DAYS_KEY);
        List<Preference> preferencesContainer = new ArrayList<Preference>();
        preferencesContainer.add(weekdayStarts);
        preferencesContainer.add(daysAbleToWorkoutList);
        defaultSwitch.setPreferencesContainer(preferencesContainer);

//        defaultSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object o) {
//                if (!((SwitchPreference)preference).isChecked()) {
////                    //sharedPreferences.edit().remove(KeyUtils.WEEKDAY_STARTS_KEY).commit();
////                    PreferenceManager.setDefaultValues(baseActivity.getBaseContext(), R.xml.week_preferences,true);
//                }
//                return true;
//            }
//        });
//
//        weekdayStarts.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                weekdayStarts.setSummary((String) newValue);
//                return true;
//            }
//        });
//
//        daysAbleToWorkoutList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                daysAbleToWorkoutList.setSummary(((Set<String>) newValue).toString());
//                return true;
//            }
//        });

    }

}
