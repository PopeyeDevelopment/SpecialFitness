package uk.co.pped.specialfitness.utility;

import android.util.AttributeSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.pped.specialfitness.Conf;

/**
 * Created by matthewi on 17/10/2017.
 */

public enum SettingsMultiSelectListTypes {
    WEEKDAYS() {

        @Override
        public Comparator<String> getComparator() {
            Comparator<String> comparator = new Comparator<String>() {

                @Override
                public int compare(String s1, String t1) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("EEE");
                        Date d1 = format.parse(s1);
                        Date d2 = format.parse(t1);


                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTime(d1);
                        cal2.setTime(d2);

                        return cal1.get(Calendar.DAY_OF_WEEK) - cal2.get(Calendar.DAY_OF_WEEK);
                    } catch(ParseException pe){
                        throw new RuntimeException(pe);
                    }
                }
            };
            return comparator;
        }

        @Override
        public int listTypeInt() {
            return 0;
        }
    };

    SettingsMultiSelectListTypes() {}
    public abstract Comparator<String> getComparator();
    public abstract int listTypeInt();

    public static final SettingsMultiSelectListTypes getListTypeFromInt(int listTypeInt) {
        for (SettingsMultiSelectListTypes val : SettingsMultiSelectListTypes.values()) {
            if (val.listTypeInt() == listTypeInt) {
                return val;
            }
        }

        return null;
    }
}
