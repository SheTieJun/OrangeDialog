package me.shetj.dialog

import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object DialogUtils {


    @JvmStatic
    fun showKeyboard(dialog: OrangeDialog) {
        val editText = dialog.inputEditText ?: return
        editText.post {
            viewRequestFocus(editText)
            val imm =
                dialog.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    @JvmStatic
    fun hideKeyboard(dialog: OrangeDialog) {
        val editText = dialog.inputEditText
        val secondEditText = dialog.secondInputEditText
        if (editText == null) {
            return
        }
        if (dialog.isHasSecondInput && secondEditText == null) {
            return
        }
        val imm =
            dialog.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (imm != null) {
            val currentFocus = dialog.currentFocus
            var windowToken: IBinder? = null
            if (currentFocus != null && currentFocus.windowToken != null) {
                windowToken = currentFocus.windowToken
            } else if (editText.applicationWindowToken != null) {
                windowToken = editText.applicationWindowToken
            } else if (dialog.isHasSecondInput && secondEditText!!.applicationWindowToken != null) {
                windowToken = secondEditText.applicationWindowToken
            }
            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken, 0)
            }
        }
        viewClearFocus(editText)
    }


    /**
     * 控件失去焦点
     * <P>同时会让父布局获得焦点</P>
     */

    private fun viewClearFocus(view: View) {
        view.clearFocus()
        viewRequestFocus(view.parent as View)
    }

    /**
     * 控件获取焦点
     */
    private fun viewRequestFocus(view: View) {
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }

}
