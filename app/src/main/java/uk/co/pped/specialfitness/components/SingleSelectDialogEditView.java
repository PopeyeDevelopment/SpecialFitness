package uk.co.pped.specialfitness.components;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.model.UserModel;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 18/10/2017.
 */

public class SingleSelectDialogEditView extends AppCompatSpinner {

    private static final int GENDER_SPINNER_TYPE = 0;
    private static final int GYM_SPINNER_TYPE = 1;

    private final UserModel user = UserModel.getInstance();

    private static final int DEF_VAL = -1;
    private ArrayAdapter<String> arrayAdapter;
    private int type;
    private ArrayList<String> entries;
    private final TypedArray styleables;

    public SingleSelectDialogEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.styleables = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectDialogEditView);
        setEntries();
        setType();
        spinnerSetup();
    }

    /**
     * Setup our spinner based on the attributes passed in
     */
    private void spinnerSetup() {
        String userValForType = getValueFromUserModel();
        this.arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.gender_spinner_text_view, entries) {

            /**
             * We want to be able to set the items we want to be disabled so we
             * have to make it so all items in our list or can be disabled by
             * setting this option to false.
             */
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            /**
             * Disable the very first option in the list. Assumption is
             * that this will always be something like "Please Select".
             */
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                }
                return super.isEnabled(position);
            }
        };
        this.setAdapter(arrayAdapter);
        if (StringUtils.isNotEmpty(userValForType)) {
            int userValPos = arrayAdapter.getPosition(userValForType);
            this.setSelection(userValPos);
        }

        this.setOnItemSelectedListener(new OnItemSelectListener());
    }

    /**
     * Based on the type of Spinner we are obtain the current value from
     * the UserModel and return.
     */
    private String getValueFromUserModel() {
        switch (type) {
            case GENDER_SPINNER_TYPE:
                return user.getGender();
            case GYM_SPINNER_TYPE:
                return user.getGymMember();
            default:
                return null;
        }
    }

    /**
     * Based on the type of Spinner we are set the value on the user model.
     */
    private void setValueOnUserModel(String val) {
        switch (type) {
            case GENDER_SPINNER_TYPE:
                 user.setGender(val);
                 break;
            case GYM_SPINNER_TYPE:
                 user.setGymMember(val);
                 break;
            default:
                break;
        }
    }

    /**
     * Set all the entries into our local collections list.
     */
    private void setEntries() {
        if (entries == null) {
            entries = new ArrayList<String>();
        }

        int resourceId = styleables.getResourceId(R.styleable.SingleSelectDialogEditView_entries, DEF_VAL);
        if (resourceId != DEF_VAL) {
            String[] tempEntries = getContext().getResources().getStringArray(resourceId);
            Collections.addAll(this.entries, tempEntries);
        }
    }

    private void setType() {
        if (styleables.hasValue(R.styleable.SingleSelectDialogEditView_spinnerType)) {
            type = styleables.getInt(R.styleable.SingleSelectDialogEditView_spinnerType, DEF_VAL);
        }
    }

    private class OnItemSelectListener extends Activity implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            final String selectedVal = parent.getItemAtPosition(pos).toString();
            setValueOnUserModel(selectedVal);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }

}
