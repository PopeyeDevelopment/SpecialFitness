package uk.co.pped.specialfitness.components.preferences;


import android.content.Context;
import android.content.res.TypedArray;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 30/10/2017.
 */

public class SettingsEditTextPreference extends EditTextPreference {

    private final TypedArray styleables;
    private int disabledTitleTextColor;
    private String overridingDefaultValue;

    public SettingsEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.styleables = getContext().obtainStyledAttributes(attrs, R.styleable.SettingsEditTextPreference);
    }
}
