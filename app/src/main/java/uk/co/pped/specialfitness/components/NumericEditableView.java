package uk.co.pped.specialfitness.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import org.apache.commons.lang3.StringUtils;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.model.UserModel;
import uk.co.pped.specialfitness.utility.Unit;
import uk.co.pped.specialfitness.utility.UnitConverter;
import uk.co.pped.specialfitness.utils.KeyUtils;
import uk.co.pped.specialfitness.utils.KeyboardUtils;

/**
 * Created by matthewi on 23/10/2017.
 */

public class NumericEditableView extends AppCompatEditText {

    private static final int WEIGHT_TYPE = 0;
    private static final int HEIGHT_TYPE = 1;

    private static final int DEF_VAL = -1;

    private final UserModel user = UserModel.getInstance();
    private final TypedArray styleables;
    private final SharedPreferences preferences;
    private Unit<String, String, Double> currentUnit;

    public NumericEditableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.styleables = context.obtainStyledAttributes(attrs, R.styleable.NumericEditableView);
        onLoad();
    }

    private void onLoad() {

        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String text = getText().toString();
                    text = StringUtils.remove(text, currentUnit.getDescription());
                    text = StringUtils.deleteWhitespace(text);
                    currentUnit.setValue(Double.valueOf(text));
                    updateText(currentUnit.toDisplayString());
                    KeyboardUtils.getInstance().hide(getView());
                    return true;
                }
                return false;
            }
        });

        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    updateText(currentUnit.toValueString());
                }
            }
        });

        final int fieldType = retrieveFieldType();
        final String prefUnitType = retrieveTypePreferenceValue(fieldType);
        this.currentUnit = retrieveCurrentUnit(fieldType);
        final Unit<String, String, Double> newUnit = convert(currentUnit, prefUnitType);
        updateText(newUnit.toDisplayString());
    }

    private void updateText(String val) {
        this.setText(val);
    }

    private Unit<String, String, Double> convert(Unit<String, String, Double> currentUnit, String newDescription) {
        Unit<String, String, Double> newUnit = currentUnit;
        if (currentUnit != null
                && !StringUtils.equals(currentUnit.getDescription(), newDescription)) {
            double convertedValue = 0.0;
            String type = currentUnit.getType();
            if (StringUtils.equals(type, Unit.TYPE_WEIGHT)) {
                convertedValue = UnitConverter.convertWeight(newUnit.getValue(), newUnit.getDescription(), newDescription);
            } else if(StringUtils.equals(type, Unit.TYPE_HEIGHT)) {
                convertedValue = UnitConverter.convertHeight(newUnit.getValue(), newUnit.getDescription(), newDescription);
            }

            newUnit.setDescription(newDescription);
            newUnit.setValue(convertedValue);
        }

        return newUnit;
    }

    private String retrieveTypePreferenceValue(int type) {
        String key = null;
        int defaultResInt = DEF_VAL;
        switch(type) {
            case WEIGHT_TYPE:
                key = KeyUtils.UNITS_WEIGHT_KEY;
                defaultResInt = R.string.weight_default;
                break;
            case HEIGHT_TYPE:
                key = KeyUtils.UNITS_HEIGHT_KEY;
                defaultResInt = R.string.height_default;
                break;
            default:
                break;
        }
        return preferences.getString(key, getContext().getResources().getString(defaultResInt));
    }

    private Unit<String, String, Double> retrieveCurrentUnit(int type) {
        switch(type) {
            case WEIGHT_TYPE:
                return getUserWeight();
            case HEIGHT_TYPE:
                return getUserHeight();
            default:
                return new Unit<String, String, Double>();
        }
    }

    private int retrieveFieldType() {
        if (styleables.hasValue(R.styleable.NumericEditableView_fieldType)) {
            return styleables.getInt(R.styleable.NumericEditableView_fieldType, DEF_VAL);
        }

        return DEF_VAL;
    }

    private Unit<String, String, Double> getUserWeight() {
        Unit<String, String, Double> weight = user.getWeight();
        if (weight == null) {
            weight = Unit.emptyUnitForType(Unit.TYPE_WEIGHT);
        }
        return weight;
    }

    private Unit<String, String, Double> getUserHeight() {
        Unit<String, String, Double> height = user.getHeight();;
        if (height == null) {
            height = Unit.emptyUnitForType(Unit.TYPE_HEIGHT);
        }
        return height;
    }

    private View getView() {
        return this;
    }
}
