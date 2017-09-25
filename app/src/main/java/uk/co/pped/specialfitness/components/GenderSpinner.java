package uk.co.pped.specialfitness.components;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.AppCompatSpinner;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

import uk.co.pped.specialfitness.BuildConfig;
import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.model.UserModel;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 07/09/2017.
 */

public class GenderSpinner extends AppCompatSpinner  {

    private final String[] genderOptions = getResources().getStringArray(R.array.gender_option);

    private final ArrayAdapter arrayAdapter = new ArrayAdapter(this.getContext(), R.layout.gender_spinner_text_view, genderOptions);;

    private final UserModel user = UserModel.getInstance();

    public GenderSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        spinnerSetup();
    }


    private void spinnerSetup() {
        this.setAdapter(arrayAdapter);
        int userValuePosition = arrayAdapter.getPosition(user.getGender());
        this.setSelection(userValuePosition);
        this.setOnItemSelectedListener(new OnItemSelectListener());

    }

    public class OnItemSelectListener extends Activity implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String genderVal = parent.getItemAtPosition(pos).toString();
            user.setGender(genderVal);
            Toast.makeText(view.getContext(), genderVal, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
