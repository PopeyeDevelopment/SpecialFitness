package uk.co.pped.specialfitness.components;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.AppCompatSpinner;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 07/09/2017.
 */

public class GenderSpinner extends AppCompatSpinner  {

    private final String[] genderOptions = getResources().getStringArray(R.array.gender_option);

    private final ArrayAdapter arrayAdapter = new ArrayAdapter(this.getContext(), R.layout.support_simple_spinner_dropdown_item, genderOptions);;

    public GenderSpinner(Context context) {
        this(context, null);
    }

    public GenderSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        spinnerSetup();
    }

    private void spinnerSetup() {
        this.setAdapter(arrayAdapter);
        this.setOnItemSelectedListener(new OnItemSelectListener());
    }

    public class OnItemSelectListener extends Activity implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String posValue = parent.getItemAtPosition(pos).toString();
            Toast.makeText(view.getContext(), genderOptions[pos], Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }
}
