package info.izumin.android.dialogfragmentbuilder.support;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * Created by izumin on 6/19/13.
 */
public class SupportAlertDialogFragment extends DialogFragment {

    private static final String KEY_ICON = "key_icon",
            KEY_TITLE_ID = "key_title_id", KEY_TITLE = "key_title",
            KEY_MESSAGE_ID = "key_message_id", KEY_MESSAGE = "key_message",
            KEY_NEGATIVE_ID = "key_negative_id", KEY_NEGATIVE = "key_negative",
            KEY_POSITIVE_ID = "key_positive_id", KEY_POSITIVE = "key_positive",
            KEY_NEUTRAL_ID = "key_neutral_id", KEY_NEUTRAL = "key_neutral",
            KEY_CANCELABLE = "key_cancelable", KEY_LAYOUT_ID = "key_layout_id";

    private Callbacks callbacks;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && targetFragment instanceof Callbacks) {
            callbacks = (Callbacks) targetFragment;
        } else if (getActivity() instanceof Callbacks) {
            callbacks = (Callbacks) getActivity();
        } else {
            callbacks = new SupportAlertDialogFragment.Callbacks() {
                @Override
                public void onDialogClicked(DialogInterface dialog, int which, String tag) {}

                @Override
                public void onDialogCanceled(DialogInterface dialog, String tag) {}
            };
        }

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callbacks.onDialogClicked(dialogInterface, i, getTag());
                    }
                };

        Bundle args = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (args.containsKey(KEY_ICON)) builder.setIcon(args.getInt(KEY_ICON));

        if (args.containsKey(KEY_TITLE_ID)) {
            builder.setTitle(args.getInt(KEY_TITLE_ID));
        } else if (args.containsKey(KEY_TITLE)) {
            builder.setTitle(args.getString(KEY_TITLE));
        }

        if (args.containsKey(KEY_MESSAGE_ID)) {
            builder.setMessage(args.getInt(KEY_MESSAGE_ID));
        } else if (args.containsKey(KEY_MESSAGE)) {
            builder.setMessage(args.getString(KEY_MESSAGE));
        }

        if (args.containsKey(KEY_NEGATIVE_ID)) {
            builder.setNegativeButton(args.getInt(KEY_NEGATIVE_ID), listener);
        } else if (args.containsKey(KEY_NEGATIVE)) {
            builder.setNegativeButton(args.getString(KEY_NEGATIVE), listener);
        }

        if (args.containsKey(KEY_POSITIVE_ID)) {
            builder.setPositiveButton(args.getInt(KEY_POSITIVE_ID), listener);
        } else if (args.containsKey(KEY_POSITIVE)) {
            builder.setPositiveButton(args.getString(KEY_POSITIVE), listener);
        }

        if (args.containsKey(KEY_NEUTRAL_ID)) {
            builder.setNeutralButton(args.getInt(KEY_NEUTRAL_ID), listener);
        } else if (args.containsKey(KEY_NEUTRAL)) {
            builder.setNeutralButton(args.getString(KEY_NEUTRAL), listener);
        }

        if (args.containsKey(KEY_LAYOUT_ID)) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            builder.setView(inflater.inflate(args.getInt(KEY_LAYOUT_ID), null, false));
        }

        builder.setCancelable(args.getBoolean(KEY_CANCELABLE));

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        callbacks.onDialogCanceled(dialog, getTag());
    }

    public static class Builder {

        private int iconId, titleId, messageId,
                negativeId, positiveId, neutralId, layoutId;
        private String title, message, negative, positive, neutral;
        private boolean cancelable = true;

        private boolean hasIcon, hasTitle, hasMessage,
                hasNegative, hasPositive, hasNeutral, hasLayout;

        private Fragment targetFragment;
        private int requestCode;

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
         * Set the message to display using the given resource id.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setMessage(int resourceId) {
            this.messageId = resourceId;
            hasMessage = true;
            return this;
        }

        /**
         * Set the label to display in the negative button.
         * @param label
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setNegativeButtonLabel(String label) {
            this.negative = label;
            hasNegative = true;
            return this;
        }

        /**
         * Set the label to display in the negative button using the given resource id.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setNegativeButtonLabel(int resourceId) {
            this.negativeId = resourceId;
            hasNegative = true;
            return this;
        }

        /**
         * Set the label to display in the positive button.
         *
         *
         * @param label
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setPositiveButtonLabel(String label) {
            this.positive = label;
            hasPositive = true;
            return this;
        }

        /**
         * Set the label to display in the positive button using the given resource id.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setPositiveButtonLabel(int resourceId) {
            this.positiveId = resourceId;
            hasPositive = true;
            return this;
        }

        /**
         * Set the label to display in the neutral button.
         * @param label
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setNeutralButtonLabel(String label) {
            this.neutral = label;
            hasNeutral = true;
            return this;
        }

        /**
         * Set the label to display in the neutral button using the given resource id.
         * @param resourceId
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setNeutralButtonLabel(int resourceId) {
            this.neutralId = resourceId;
            hasNeutral = true;
            return this;
        }

        /**
         * Set the view to display in this dialog from resource.
         * @param resourceid
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setViewFromResource(int resourceid) {
            this.layoutId = resourceid;
            hasLayout = true;
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
         * Optional target for this fragment.
         * @param targetFragment
         * @param requestCode
         * @return This Builder object to allow for chaining of calls to set methods.
         */
        public Builder setTargetFragment(Fragment targetFragment, int requestCode) {
            this.targetFragment = targetFragment;
            this.requestCode = requestCode;
            return this;
        }

        /**
         * Create a SupoprtAlertDialogFragment with the arguments supplied to this builder.
         * @return Created AlertDialogFragment object.
         */
        public SupportAlertDialogFragment create() {
            SupportAlertDialogFragment fragment = new SupportAlertDialogFragment();

            if (targetFragment != null) {
                fragment.setTargetFragment(targetFragment, requestCode);
            }

            Bundle args = new Bundle();

            if (hasIcon) args.putInt(KEY_ICON, iconId);

            if (hasTitle) {
                if (title == null) args.putInt(KEY_TITLE_ID, titleId);
                else args.putString(KEY_TITLE, title);
            }

            if (hasMessage) {
                if (message == null) args.putInt(KEY_MESSAGE_ID, messageId);
                else args.putString(KEY_MESSAGE, message);
            }

            if (hasNegative) {
                if (negative == null) args.putInt(KEY_NEGATIVE_ID, negativeId);
                else args.putString(KEY_NEGATIVE, negative);
            }

            if (hasPositive) {
                if (positive == null) args.putInt(KEY_POSITIVE_ID, positiveId);
                else args.putString(KEY_POSITIVE, positive);
            }

            if (hasNeutral) {
                if (neutral == null) args.putInt(KEY_NEUTRAL_ID, neutralId);
                else args.putString(KEY_NEUTRAL, neutral);
            }

            if (hasLayout) args.putInt(KEY_LAYOUT_ID, layoutId);

            args.putBoolean(KEY_CANCELABLE, cancelable);

            fragment.setArguments(args);
            return fragment;
        }
    }

    public interface Callbacks {
        void onDialogClicked(DialogInterface dialog, int which, String tag);
        void onDialogCanceled(DialogInterface dialog, String tag);
    }
}
