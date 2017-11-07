package uk.co.pped.specialfitness.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.activities.settings.SettingsFragmentHandler;

/**
 * Created by matthewi on 31/10/2017.
 */

public class StyleableHelper {

    public static final class StyleableKeys {
        public static final String TITLE_DISABLED_TEXT_COLOR_KEY = "styleables.title_disabled_text_color";
        public static final String OVERRIDING_DEFAULT_VALUE_KEY = "styleables.overriding_default_value";
        public static final String LIST_TYPE_KEY = "styleables.list_type";
    }

    public static final int[] LIST_PREFERENCES = R.styleable.SettingsListPreferences;
    public static final int[] MULTISELECTLIST_PREFERENCES = R.styleable.SettingsMultiSelectListPreference;
    public static final int[] EDITTEXT_PREFERENCE = R.styleable.SettingsEditTextPreference;

    /** Default value to be used. */
    private static final int DEF_VAL = -1;

    protected final TypedArray styleables;
    private final Context context;
    static final Map<String, Integer> styleablesMap = new HashMap<String, Integer>();

    private Integer disabledTitleTextColor;
    private String overridingDefaultValue;
    private String[] overridingDefaultValues;

    protected StyleableHelper(Context context, AttributeSet set, int[] attrs) {
        this.styleables = context.obtainStyledAttributes(set, attrs);
        this.context = context;
    }

    public static StyleableHelper getInstance(Context context, AttributeSet set, int[] attrs) {
        if (attrs == LIST_PREFERENCES) {
            return new ListPreferenceStyleables(context, set, attrs);
        } else if (attrs == MULTISELECTLIST_PREFERENCES) {
            return new MultiSelectListPreferenceStyleables(context, set, attrs);
        } else if (attrs == EDITTEXT_PREFERENCE) {
            return new EditTextPreferenceStyleables(context, set, attrs);
        } else {
            return null;
        }
    }

    public int getDisabledTitleTextColor() {
        if (disabledTitleTextColor == null
                && styleablesMap.containsKey(StyleableKeys.TITLE_DISABLED_TEXT_COLOR_KEY)) {
            int resourceId = styleables.getResourceId( styleablesMap.get(StyleableKeys.TITLE_DISABLED_TEXT_COLOR_KEY), DEF_VAL);
            this.disabledTitleTextColor = context.getResources().getColor(resourceId);
        }

        return disabledTitleTextColor;
    }

    public String getOverridingDefaultValue() {
        if (StringUtils.isEmpty(overridingDefaultValue)
                && styleablesMap.containsKey(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY)) {
            int resourceId = styleables.getResourceId(styleablesMap.get(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY), DEF_VAL);
            if (resourceId != DEF_VAL) {
                this.overridingDefaultValue = context.getResources().getString(resourceId);
            }
        }

        return overridingDefaultValue;
    }

    public String[] getOverridingDefaultValues() {
        if (overridingDefaultValues == null
                && styleablesMap.containsKey(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY)) {
            int resourceId = styleables.getResourceId(styleablesMap.get(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY), DEF_VAL);
            if (resourceId != DEF_VAL) {
                this.overridingDefaultValues = context.getResources().getStringArray(resourceId);
            }
        }

        return overridingDefaultValues;
    }

    public Map<String, Integer> getStyleableMap() {
            return styleablesMap;
        }

    public static final class ListPreferenceStyleables extends StyleableHelper {
        static {
            styleablesMap.put(StyleableKeys.TITLE_DISABLED_TEXT_COLOR_KEY, R.styleable.SettingsEditTextPreference_titleDisabledTextColour);
            styleablesMap.put(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY, R.styleable.SettingsListPreferences_overridingDefaultValue);
        }

        protected ListPreferenceStyleables(Context context, AttributeSet set, int[] attrs) {
            super(context, set, attrs);
        }
    }

    public static final class MultiSelectListPreferenceStyleables extends StyleableHelper {
        static {
            styleablesMap.put(StyleableKeys.TITLE_DISABLED_TEXT_COLOR_KEY, R.styleable.SettingsMultiSelectListPreference_titleDisabledTextColour);
            styleablesMap.put(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY, R.styleable.SettingsMultiSelectListPreference_overridingDefaultValues);
            styleablesMap.put(StyleableKeys.LIST_TYPE_KEY, R.styleable.SettingsMultiSelectListPreference_listType);
        }

        protected MultiSelectListPreferenceStyleables(Context context, AttributeSet set, int[] attrs) {
            super(context, set, attrs);
        }
    }

    public static final class EditTextPreferenceStyleables extends StyleableHelper {
        static {
            styleablesMap.put(StyleableKeys.TITLE_DISABLED_TEXT_COLOR_KEY, R.styleable.SettingsEditTextPreference_titleDisabledTextColour);
            styleablesMap.put(StyleableKeys.OVERRIDING_DEFAULT_VALUE_KEY, R.styleable.SettingsEditTextPreference_overridingDefaultValue);
        }

        protected EditTextPreferenceStyleables(Context context, AttributeSet set, int[] attrs) {
            super(context, set, attrs);
        }
    }


}


