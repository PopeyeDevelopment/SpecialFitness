package uk.co.pped.specialfitness.components.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 10/10/2017.
 */

public class PPEDListPreferences extends ListPreference implements Preference.OnPreferenceChangeListener {


    static private class Styleable {
        static final int PPEDLISTPREFERNCE[] = R.styleable.PPEDListPreferences;
        static final int TITLE_DISABLED_TEXT_COLOR = R.styleable.PPEDListPreferences_titleDisabledTextColour;
        static final int SUMMARY_DISABLED_TEXT_COLOR = R.styleable.PPEDListPreferences_summaryDisabledTextColour;
        static final int OVERRIDING_DEFAULT_VALUE = R.styleable.PPEDListPreferences_overridingDefaultValue;
    }

    private static final int defVal = 0;

    private TextView summaryTextView;
    private TextView titleTextView;
    private final TypedArray styleables;
    private int disabledTitleTextColor;
    private int disabledSummaryTextColor;
    private String overridingDefaultValue;

    public PPEDListPreferences(Context context, AttributeSet attrs)  {
        super(context, attrs);
        this.styleables = context.obtainStyledAttributes(Styleable.PPEDLISTPREFERNCE);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PPEDListPreferences(Context context) {
        this(context, null);
    }

    private void initialize() {
        if (styleables.hasValue(Styleable.SUMMARY_DISABLED_TEXT_COLOR)) {
            this.disabledSummaryTextColor = styleables.getColor(Styleable.SUMMARY_DISABLED_TEXT_COLOR, defVal);
        }
        if (styleables.hasValue(Styleable.TITLE_DISABLED_TEXT_COLOR)) {
            this.disabledTitleTextColor = styleables.getColor(Styleable.TITLE_DISABLED_TEXT_COLOR, defVal);
        }
        if (styleables.hasValue(Styleable.OVERRIDING_DEFAULT_VALUE)) {
            this.overridingDefaultValue = styleables.getString(Styleable.OVERRIDING_DEFAULT_VALUE);
        }
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        this.summaryTextView = (TextView) view.findViewById(android.R.id.summary);
        this.titleTextView = (TextView) view.findViewById(android.R.id.title);
    }

    public TextView getSummaryTextView() {
        return this.summaryTextView;
    }

    public TextView getTitleTextView() {
        return this.titleTextView;
    }

    /**
     * Convenience method for resetting options and UI to
     * it's defaulted state. Should only be used when using
     * a switch to default it's values.
     */
    public void resetToDefaults() {
        this.titleTextView.setTextColor(this.disabledTitleTextColor);
        this.summaryTextView.setTextColor(this.disabledSummaryTextColor);

        if (StringUtils.isNotEmpty((String) overridingDefaultValue)) {
            int defaultValIndex = this.findIndexOfValue(overridingDefaultValue);
            this.setValueIndex(defaultValIndex);
        }
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {

        int defaultValIndex = ((PPEDListPreferences) preference).findIndexOfValue((String)o);
        ((PPEDListPreferences) preference).setValueIndex(defaultValIndex);
        return true;
    }
}
