package uk.co.pped.specialfitness.components;

import static com.github.clans.fab.FloatingActionButton.SIZE_MINI;
import static com.github.clans.fab.FloatingActionButton.SIZE_NORMAL;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.content.Context;
import android.view.ViewGroup;
import android.support.annotation.IntDef;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.lang.annotation.Retention;
import java.util.List;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 22/11/2017.
 */

public class FloatingActionMenuHelper {

    public static void addAllMenuButtons(List<FloatingActionButton> buttons, FloatingActionMenu fab) {
        fab.removeAllMenuButtons();
        for (FloatingActionButton button : buttons) {
            fab.addMenuButton(button);
        }
    }

    public static ButtonBuilder getButtonBuilder(Context context) {
        return new ButtonBuilder(context);
    }

    public static final class ButtonBuilder {

        /*
            Interface for restricting the available values
            that can be provided into the setSize Method.
        */
        @Retention(SOURCE)
        @IntDef({SIZE_MINI, SIZE_NORMAL})
        private @interface Size {}

        private Context context;
        private int id;
        private int colorNormal;
        private int colorPressed;
        private int colorDisabled;
        private int imageSource;
        private FloatingActionButton.OnClickListener onClickListener;

        private boolean allowBtnDisablability = false;

        /** Field to hold the size value as an int, default is 0 */
        private int size = SIZE_MINI;

        private String text;

        public ButtonBuilder(Context context) {
            this.context = context;
            this.colorNormal = context.getColor(R.color.actionBtnNormal);
            this.colorPressed = context.getColor(R.color.actionBtnPressed);
        }

        public ButtonBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ButtonBuilder setSize(@Size int size) {
            this.size = size;
            return this;
        }

        public ButtonBuilder setLabelText(String text) {
            this.text = text;
            return this;
        }

        public ButtonBuilder setButtonNormalColor(final int colorNormal) {
            this.colorNormal = colorNormal;
            return this;
        }

        public ButtonBuilder setButtonPressedColor(final int colorPressed) {
            this.colorPressed = colorPressed;
            return this;
        }

        public ButtonBuilder setButtonDisabledColor(final int colorDisabled) {
            this.colorDisabled = colorDisabled;
            this.allowBtnDisablability = true;
            return this;
        }

        public ButtonBuilder setButtonImageSource(final int imageSource) {
            this.imageSource = imageSource;
            return this;
        }

        public ButtonBuilder setOnClickListener(final FloatingActionButton.OnClickListener listener) {
            this.onClickListener = listener;
            return this;
        }

        public FloatingActionButton build() {
            FloatingActionButton btn = new FloatingActionButton(context);

            btn.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if (this.id != 0) {
                btn.setId(this.id);
            }


            btn.setButtonSize(this.size);
            btn.setLabelText(this.text);
            btn.setColorNormal(this.colorNormal);
            btn.setColorPressed(this.colorPressed);

            if (this.allowBtnDisablability) {
                btn.setColorDisabled(this.colorDisabled);
            }

            if (this.imageSource != 0) {
                btn.setImageResource(this.imageSource);
            }

            btn.setOnClickListener(onClickListener);
            return btn;
        }
    }

}
