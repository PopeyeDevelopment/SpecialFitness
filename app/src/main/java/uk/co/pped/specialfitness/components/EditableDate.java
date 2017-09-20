package uk.co.pped.specialfitness.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.model.UserModel;

/**
 * Created by matthewi on 08/09/2017.
 */

public class EditableDate extends AppCompatEditText{

    private static Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    private UserModel user = UserModel.getInstance();

    public EditableDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        calendar.setTime(user.getDateOfBirth());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(v.getContext(), date,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                dialog.show();
            }
        });
    }


    private void updateLabel() {
        this.setText(Conf.SIMPLE_DATE_FORMAT.format(calendar.getTime()));
    }

    private void updateUser() {
        user.setDateOfBirth(calendar.getTime());
    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            updateUser();
        }
    };

}