package uk.co.pped.specialfitness.components.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;
import uk.co.pped.specialfitness.utility.SettingsMultiSelectListTypes;

/**
 * Created by matthewi on 16/10/2017.
 */

public class SettingsMultiSelectListPreference extends MultiSelectListPreference implements Preference.OnPreferenceChangeListener {

    private final TypedArray styleables;
    private int disabledTitleTextColor;
    private Set<String> overridingDefaultValues = new HashSet<String>();
    private SettingsMultiSelectListTypes listType;

    public SettingsMultiSelectListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.styleables = getContext().obtainStyledAttributes(attrs, R.styleable.SettingsMultiSelectListPreference);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SettingsMultiSelectListPreference(Context context) {
        this(context, null);
    }

    private void initialize() {
        setOverridingDefaultValues(null);
        setDisabledTitleTextColor();
        setListType();
        setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        final int[] colorsTitle = new int[] {
                Color.BLACK,
                this.disabledTitleTextColor
        };
        ((TextView) view.findViewById(android.R.id.title))
                .setTextColor(new ColorStateList(SettingsFragmentHandler.STATES, colorsTitle));
        ((TextView) view.findViewById(android.R.id.summary))
                .setText(getSortedValues().toString());
    }

    /**
     * Set the overridingDefaultValue for the MultiSelectListPreference, if a value is passed
     * in then this will be the value that will be used; if no value is passed in
     * then we'll see if any value has been set on the attributes.
     *
     * @param overridingDefaultValues1
     */
    private void setOverridingDefaultValues(String[] overridingDefaultValues1) {
        if (overridingDefaultValues1 == null) {
            int resourceId = styleables.getResourceId(R.styleable.SettingsMultiSelectListPreference_overridingDefaultValues, SettingsFragmentHandler.defVal);
            if (resourceId != SettingsFragmentHandler.defVal) {
                overridingDefaultValues1 = getContext().getResources().getStringArray(resourceId);
            }
        }

        this.overridingDefaultValues.clear();
        Collections.addAll(this.overridingDefaultValues, overridingDefaultValues1);
    }

    /**
     * Set the default value for disabledTitleTextColour from the attribute set in the xml.
     */
    private void setDisabledTitleTextColor() {
        int resourceId = styleables.getResourceId(R.styleable.SettingsMultiSelectListPreference_titleDisabledTextColour, SettingsFragmentHandler.defVal);
        if (resourceId != SettingsFragmentHandler.defVal) {
            this.disabledTitleTextColor = getContext().getResources().getColor(resourceId);
        }
    }

    /**
     * Set the listType specified in the xml, uses the attribute listType which
     * is a custom attribute declared in attr.xml
     */
    private void setListType() {
        int enumId = styleables.getInt(R.styleable.SettingsMultiSelectListPreference_listType, SettingsFragmentHandler.defVal);
        this.listType = SettingsMultiSelectListTypes.getListTypeFromInt(enumId);
    }

    /**
     * @return returns a sorted list of values.
     */
    private List<String> getSortedValues() {
        List<String> tempValuesList = new ArrayList<String>(this.getValues());
        Collections.sort(tempValuesList, listType.getComparator());
        return tempValuesList;
    }

    /**
     * Convenience method for resetting options and UI to
     * it's defaulted state. Should only be used when using
     * a switch to default it's values.
     */
    public void resetToDefaults() {
        if (!overridingDefaultValues.isEmpty()) {
            this.setValues(overridingDefaultValues);
            this.setSummary(getSortedValues().toString());
        }
        notifyChanged();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        List<String> tempValuesList = new ArrayList<String>((Set<String>)o);
        Collections.sort(tempValuesList, listType.getComparator());
        this.setSummary(getSortedValues().toString());
        return true;
    }
}
