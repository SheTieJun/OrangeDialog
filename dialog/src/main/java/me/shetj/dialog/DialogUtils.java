package me.shetj.dialog;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author Aidan Follestad (afollestad)
 */
public class DialogUtils {



    public static void showKeyboard(final OrangeDialog dialog) {
        final EditText editText = dialog.getInputEditText();
        if (editText == null) {
            return;
        }
        editText.post(new Runnable() {
            @Override
            public void run() {
                viewRequestFocus(editText);
                InputMethodManager imm = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }

    public static void hideKeyboard(final OrangeDialog dialog) {
        EditText editText = dialog.getInputEditText();
        EditText secondEditText = dialog.getSecondInputEditText();
        if (editText == null) {
            return;
        }
        if (dialog.isHasSecondInput() && secondEditText == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            final View currentFocus = dialog.getCurrentFocus();
            IBinder windowToken = null;
            if (currentFocus != null && currentFocus.getWindowToken() != null) {
                windowToken = currentFocus.getWindowToken();
            } else if (editText.getApplicationWindowToken() != null) {
                windowToken = editText.getApplicationWindowToken();
            } else if (dialog.isHasSecondInput() && secondEditText.getApplicationWindowToken() != null) {
                windowToken = secondEditText.getApplicationWindowToken();
            }
            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken, 0);
            }
        }
        viewClearFocus(editText);
    }


    /**
     * 控件失去焦点
     * <P>同时会让父布局获得焦点</P>
     */
    public static void viewClearFocus(View view) {
        view.clearFocus();
        viewRequestFocus((View) view.getParent());
    }

    /**
     * 控件获取焦点
     */
    private static void viewRequestFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

}
