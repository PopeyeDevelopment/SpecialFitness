package uk.co.pped.specialfitness.components.widgets;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import uk.co.pped.specialfitness.R;

import java.util.List;

/**
 * Created by matthewi on 03/10/2017.
 */

public class HeaderAdapter extends ArrayAdapter<Header> {

    private static final String TAG = HeaderAdapter.class.getName();

    static final int HEARDER_TYPE_SUPER_HEADER = 0;
    static final int HEADER_TYPE_NORMAL = 1;
    static final int HEARDER_TYPE_CATERGORY = 0;
    static final int HEADER_TYPE_OTHER = -1;

    public static final String HEADER_TYPE_KEY = "type";
    public static final String HEADER_TYPE_HEADER = "header";
    public static final String HEADER_TYPE_INTENT = "intent";

    private LayoutInflater inflater;
    private boolean removeIconIfEmpty;

    public HeaderAdapter(Context context, List<Header> headers) {
        super(context, 0, headers);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Header header = getItem(position);
        int headerType = getHeaderType(header);

        // Reset the convertView to null as we have our own to use.
        convertView = null;
        if (convertView == null) {

            switch (headerType) {
                case HEARDER_TYPE_SUPER_HEADER:
                    convertView = inflater.inflate(R.layout.settings_header_layout, parent, false);
                    ((TextView) convertView.findViewById(android.R.id.title)).setText(header.getTitle(getContext().getResources()));
                    break;
                default:
                    convertView = inflater.inflate(R.layout.preferences_basic_layout, parent, false);
                    ((TextView) convertView.findViewById(android.R.id.title)).setText(header.getTitle(getContext()
                            .getResources()));
                    ((TextView) convertView.findViewById(android.R.id.summary)).setText(header
                            .getSummary(getContext().getResources()));
                    break;
            }
        }

        return convertView;
    }

    private static int getHeaderType(Header header) {
        final Bundle extras = header.fragmentArguments;

        if ((extras != null && !extras.containsKey(HEADER_TYPE_KEY) || extras == null)) {
            throw new IllegalStateException("getHeaderType: header does not contain extra element with name of type in.");
        }

        if (isSuperHeader((String) extras.get(HEADER_TYPE_KEY))) {
            if ((header.fragment != null || header.intent != null)) {
                Log.w(TAG, "getHeaderType: header can not have type of 'header' and have a fragment or intent. This header will be treated as a normal header.");
                return HEADER_TYPE_NORMAL;
            }
            return HEARDER_TYPE_SUPER_HEADER;
        } else {
            return HEADER_TYPE_NORMAL;
        }
    }

    private static boolean isSuperHeader(String type) {
        if (StringUtils.equalsIgnoreCase(type, HEADER_TYPE_HEADER)) {
            return true;
        }

        return false;
    }
}
