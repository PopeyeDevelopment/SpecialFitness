package uk.co.pped.specialfitness.components.preferences;

import android.content.Context;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by matthewi on 10/10/2017.
 */

public class SettingsDefaultSwitchPreference extends SwitchPreference {

    private List<Preference> preferencesContainer;

    public SettingsDefaultSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceChangeListener(preferenceChangeListener);
    }

    public SettingsDefaultSwitchPreference(Context context) {
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
                    if (pref instanceof SettingsListPreferences) {
                        ((SettingsListPreferences) pref).resetToDefaults();
                    } else if (pref instanceof SettingsMultiSelectListPreference) {
                        ((SettingsMultiSelectListPreference) pref).resetToDefaults();
                    } else if (pref instanceof SettingsEditTextPreference) {
                        ((SettingsEditTextPreference) pref).resetToDefaults();
                    }
                }
            }

            return true;
        }
    };
}
