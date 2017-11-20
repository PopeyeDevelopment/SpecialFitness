package uk.co.pped.specialfitness.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 08/11/2017.
 */

public class NextPreviousControl extends LinearLayoutCompat {

    private static final Calendar today = Calendar.getInstance(TimeZone.getDefault());
    private static final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    private final DatePickerDialog datePickerDialog;
    private final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            resetCalendar(year, monthOfYear, dayOfMonth);
            updateLabel(calendar.getTime());
        }
    };

    private ImageButton nextBtn;
    private ImageButton previousBtn;
    private TextView label;
    private OnChangeLisener listener;

    public NextPreviousControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        today.set(Calendar.HOUR_OF_DAY, 0);
        resetCalendar(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH));

        // Setup our date picker
        datePickerDialog = new DatePickerDialog(context,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void init() {
        inflate(getContext(), R.layout.next_previous_control, this);
        setupNextBtn();
        setupPreviousBtn();
        setupLabel();
    }

    private void resetCalendar(final int year, final int month, final int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
    }

    private void setupNextBtn() {
        this.nextBtn = (ImageButton) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!calendar.getTime().after(today.getTime())) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    updateLabel(calendar.getTime());
                }
            }
        });
    }

    private void setupPreviousBtn() {
        this.previousBtn = (ImageButton) findViewById(R.id.previousBtn);
        previousBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                updateLabel(calendar.getTime());
            }
        });
    }

    private void setupLabel() {
        this.label = (TextView) findViewById(R.id.label);
        // Set the Date to the label on initial load of the component
        updateLabel(today.getTime());

        label.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    public void setOnChangeListener(OnChangeLisener listener) {
        this.listener = listener;
    }

    public OnChangeLisener getOnChangeListener() {
        return listener;
    }

    public final Date getCurrentDate() {
        return calendar.getTime();
    }

    public final String getDisplayDayName() {
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    private void updateLabel(Date newValue) {
        label.setText(Conf.SIMPLE_DATE_FORMAT.format(newValue));
        if (listener != null) {
            listener.onChange();
        }
    }

    public interface OnChangeLisener {
        void onChange();
    }
}
