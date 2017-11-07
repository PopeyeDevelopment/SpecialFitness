package uk.co.pped.specialfitness.components.preferences;


import android.content.Context;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;
import uk.co.pped.specialfitness.utility.StyleableHelper;

/**
 * Created by matthewi on 30/10/2017.
 */

public class SettingsEditTextPreference extends EditTextPreference implements Preference.OnPreferenceChangeListener {

    private final StyleableHelper styleables;

    public SettingsEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.styleables = StyleableHelper.getInstance(context, attrs, StyleableHelper.EDITTEXT_PREFERENCE);
        setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        final int[] colorsTitle = new int[] {
                Color.BLACK,
                styleables.getDisabledTitleTextColor()
        };

        ((TextView) view.findViewById(android.R.id.title))
                .setTextColor(new ColorStateList(SettingsFragmentHandler.STATES, colorsTitle));
    }

    /**
     * Convenience method for resetting options and UI to
     * it's defaulted state. Should only be used when using
     * a switch to default it's values.
     */
    public void resetToDefaults() {
        if (StringUtils.isNotEmpty(styleables.getOverridingDefaultValue())) {
            this.setText(styleables.getOverridingDefaultValue());
        }

        // We need to notify as we've changed the data.
        notifyChanged();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        ((SettingsEditTextPreference) preference).setText((String) o);
        return true;
    }
}
