package info.izumin.android.dialogfragmentbuilder;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by izumin on 6/19/13.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ProgressDialogFragment extends DialogFragment {

    private static final String KEY_ICON = "key_icon",
            KEY_TITLE_ID = "key_title_id", KEY_TITLE = "key_title",
            KEY_MESSAGE = "key_message", KEY_STYLE = "key_progress_style",
            KEY_INDETERMINATE = "key_indeterminate",
            KEY_CANCELABLE = "key_cancelable";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressDialog dialog = new ProgressDialog(getActivity());
        Bundle args = getArguments();

        if (args.containsKey(KEY_ICON)) dialog.setIcon(args.getInt(KEY_ICON));

        if (args.containsKey(KEY_TITLE_ID)) {
            dialog.setTitle(args.getInt(KEY_TITLE_ID));
        } else if (args.containsKey(KEY_TITLE)) {
            dialog.setTitle(args.getString(KEY_TITLE));
        }

        if (args.containsKey(KEY_MESSAGE)) {
            dialog.setMessage(args.getString(KEY_MESSAGE));
        }

        if (args.containsKey(KEY_STYLE)) {
            dialog.setProgressStyle(args.getInt(KEY_STYLE));
        }

        dialog.setIndeterminate(args.getBoolean(KEY_INDETERMINATE));
        this.setCancelable(args.getBoolean(KEY_CANCELABLE));

        return dialog;
    }

    public static class Builder {

        private int iconId, titleId, progressStyle;
        private String title, message;
        private boolean indeterminate = true, cancelable = true;

        private boolean hasIcon, hasTitle, hasMessage, hasStyle;

        /**
         * Set the resource id of the Drawable to be used in the title.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setIcon(int resourceId) {
            this.iconId = resourceId;
            hasIcon = true;
            return this;
        }

        /**
         * Set the title displayed in the Dialog.
         * @param title
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setTitle(String title) {
            this.title = title;
            hasTitle = true;
            return this;
        }

        /**
         * Set the tilte using the given resource id.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setTitle(int resourceId) {
            this.titleId = resourceId;
            hasTitle = true;
            return this;
        }

        /**
         * Set the message to display.
         * @param message
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setMessage(String message) {
            this.message = message;
            hasMessage = true;
            return this;
        }

        /**
         * Set whether the dialog is indeterminate or not. Default is true.
         * @param indeterminate
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setIndeterminate(boolean indeterminate) {
            this.indeterminate = indeterminate;
            return this;
        }

        /**
         * Set the style of the progress bar in the dialog. Default is STYLE_SPINNER.
         * @param style
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setProgressStyle(int style) {
            this.progressStyle = style;
            hasStyle = true;
            return this;
        }

        /**
         * Set whether the dialog is cancelable or not. Default is true.
         * @param cancelable
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * Create a SupoprtAlertDialogFragment with the arguments supplied to this builder.
         * @return Created AlertDialogFragment object.
         */
        public ProgressDialogFragment create() {
            ProgressDialogFragment fragment = new ProgressDialogFragment();

            Bundle args = new Bundle();

            if (hasIcon) args.putInt(KEY_ICON, iconId);

            if (hasTitle) {
                if (title == null) args.putInt(KEY_TITLE_ID, titleId);
                else args.putString(KEY_TITLE, title);
            }

            if (hasMessage) args.putString(KEY_MESSAGE, message);

            if (hasStyle) args.putInt(KEY_STYLE, progressStyle);

            args.putBoolean(KEY_INDETERMINATE, indeterminate);
            args.putBoolean(KEY_CANCELABLE, cancelable);

            fragment.setArguments(args);
            return fragment;
        }
    }
}
