package uk.co.pped.specialfitness.components.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;

/**
 * Created by matthewi on 10/10/2017.
 */

public class SettingsListPreferences extends ListPreference implements Preference.OnPreferenceChangeListener {

    private final TypedArray styleables;
    private int disabledTitleTextColor;
    private String overridingDefaultValue;

    public SettingsListPreferences(Context context, AttributeSet attrs)  {
        super(context, attrs);
        this.styleables = getContext().obtainStyledAttributes(attrs, R.styleable.SettingsListPreferences);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SettingsListPreferences(Context context) {
        this(context, null);
    }

    private void initialize() {
        setOverridingDefaultValue(null);
        setDisabledTitleTextColor();
        setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        final int[] colorsTitle = new int[] {
                Color.BLACK,
                this.disabledTitleTextColor,
        };
        ((TextView) view.findViewById(android.R.id.title))
                .setTextColor(new ColorStateList(SettingsFragmentHandler.STATES, colorsTitle));
    }

    /**
     * Set the overridingDefaultValue for the ListPreference, if a value is passed
     * in then this will be the value that will be used; if no value is passed in
     * then we'll see if any value has been set on the attributes.
     *
     * @param overridingDefaultValue1
     */
    private void setOverridingDefaultValue(String overridingDefaultValue1) {
        if (StringUtils.isEmpty(overridingDefaultValue1)) {
            int resourceId = styleables.getResourceId(R.styleable.SettingsListPreferences_overridingDefaultValue, SettingsFragmentHandler.defVal);
            if (resourceId != SettingsFragmentHandler.defVal) {
                overridingDefaultValue1 = getContext().getResources().getString(resourceId);
            }
        }

        this.overridingDefaultValue = overridingDefaultValue1;
    }

    /**
     * Set the default value for disabledTitleTextColour from the attribute set in the xml.
     */
    private void setDisabledTitleTextColor() {
        int resourceId = styleables.getResourceId(R.styleable.SettingsListPreferences_titleDisabledTextColour, SettingsFragmentHandler.defVal);
        if (resourceId != SettingsFragmentHandler.defVal) {
            this.disabledTitleTextColor = getContext().getResources().getColor(resourceId);
        }
    }

    /**
     * Convenience method for resetting options and UI to
     * it's defaulted state. Should only be used when using
     * a switch to default it's values.
     */
    public void resetToDefaults() {
        if (StringUtils.isNotEmpty(overridingDefaultValue)) {
            int defaultValIndex = this.findIndexOfValue(overridingDefaultValue);
            this.setValueIndex(defaultValIndex);
        }

        // We need to notify as we've changed the data.
        notifyChanged();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        int defaultValIndex = ((SettingsListPreferences) preference).findIndexOfValue((String)o);
        ((SettingsListPreferences) preference).setValueIndex(defaultValIndex);
        return true;
    }
}
