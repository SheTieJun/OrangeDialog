package me.shetj.dialog

import android.content.Context
import android.view.View
import androidx.annotation.DrawableRes
import androidx.collection.ArraySet


typealias OnItemClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Unit

typealias OnItemLongClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Boolean


typealias OnItemChildClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Unit

typealias OnItemChildLongClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Boolean


typealias SingleButtonCallback = (dialog: OrangeDialog, dialogAction: String) -> Unit


typealias ListCallbackSingleChoice = (dialog: OrangeDialog, itemView: View, which: Int, text: CharSequence?) -> Boolean


typealias ListCallbackMultiChoice = (dialog: OrangeDialog, which: ArraySet<Int>?, texts: Array<CharSequence?>?) -> Boolean


@JvmOverloads
fun orangeDialog(
    context: Context,
    type: Int = OrangeDialog.DIALOG_TYPE_MESSAGE,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    needInput: Boolean = false,
    inputValue: String? = null,
    inputFocus: Boolean = true,
    inputMax: Int = -1,
    items: Array<String>? = null,
    @DrawableRes positiveBackground: Int? = R.drawable.orange_dialog_btn_error_positive_selector,
    @DrawableRes negativeBackground: Int? = R.drawable.orange_dialog_btn_negative_selector,
    selectIndex: Int = -1,
    selectIndexs: ArraySet<Int>? = null,
    itemsCallbackSingleChoice: ListCallbackSingleChoice? = null,
    itemsCallbackMultiChoice: ListCallbackMultiChoice? = null,
    onPositive: SingleButtonCallback? = null,
    onNegative: SingleButtonCallback? = null
) {
    OrangeDialogBuilder(
        context,
        type,
        title,
        content,
        negativeText,
        positiveText,
        needInput,
        inputValue,
        inputFocus,
        inputMax,
        items,
        positiveBackground,
        negativeBackground,
        selectIndex,
        selectIndexs,
        itemsCallbackSingleChoice,
        itemsCallbackMultiChoice,
        onPositive,
        onNegative
    ).show()
}



@JvmOverloads
fun OrangeDialogBuilder(
    context: Context,
    type: Int = OrangeDialog.DIALOG_TYPE_MESSAGE,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    needInput: Boolean = false,
    inputValue: String? = null,
    inputFocus: Boolean = true,
    inputMax: Int = -1,
    items: Array<String>? = null,
    @DrawableRes positiveBackground: Int? = null,
    @DrawableRes negativeBackground: Int? = null,
    selectIndex: Int = -1,
    selectIndexs: ArraySet<Int>? = null,
    itemsCallbackSingleChoice: ListCallbackSingleChoice? = null,
    itemsCallbackMultiChoice: ListCallbackMultiChoice? = null,
    onPositive: SingleButtonCallback? = null,
    onNegative: SingleButtonCallback? = null
): OrangeDialog.Builder {
    val builder = OrangeDialog.Builder(context)
    return builder.apply {
        dialogType(type)
        needInput(needInput)
        inputMax(inputMax)
        inputValue(inputValue)
        inputFocus(inputFocus)
        title?.let {
            builder.title = title
        }
        content?.let {
            content(content)
        }
        items?.let {
            items(items)
        }
        itemsCallbackSingleChoice?.let {
            itemsCallbackSingleChoice(selectIndex, itemsCallbackSingleChoice)
        }
        itemsCallbackMultiChoice?.let {
            itemsCallbackMultiChoice(selectIndexs, itemsCallbackMultiChoice)
        }
        negativeText?.let {
            builder.negativeText = negativeText
        }
        positiveText?.let {
            builder.positiveText = positiveText
        }
        positiveBackground?.let {
            setPositiveBackground(positiveBackground)
        }
        negativeBackground?.let {
            setNegativeBackground(negativeBackground)
        }
        onPositive?.let {
            this.onPositive(onPositive)
        }
        onNegative?.let {
            this.onPositive(onNegative)
        }
    }
}

