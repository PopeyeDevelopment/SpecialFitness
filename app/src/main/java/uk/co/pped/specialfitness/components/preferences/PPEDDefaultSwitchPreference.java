package uk.co.pped.specialfitness.components.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by matthewi on 10/10/2017.
 */

public class PPEDDefaultSwitchPreference extends SwitchPreference {

    private List<Preference> preferencesContainer;

    public PPEDDefaultSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceChangeListener(preferenceChangeListener);
    }

    public PPEDDefaultSwitchPreference(Context context) {
        this(context, null);
    }

    public void setPreferencesContainer(List<Preference> preferencesContainer) {
        this.preferencesContainer = preferencesContainer;
    }

    public List<Preference> getPreferencesContainer() {
        return this.preferencesContainer;
    }

    OnPreferenceChangeListener preferenceChangeListener = new OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newVal) {
            if (!isChecked()) {
                for (Preference pref : getPreferencesContainer()) {
                    if (pref instanceof PPEDListPreferences) {

                        ((PPEDListPreferences) pref).resetToDefaults();
                    }
                }
            }
            return true;
        }
    };
}
