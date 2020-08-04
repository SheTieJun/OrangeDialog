package me.shetj.dialog

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.annotation.DrawableRes
import androidx.collection.ArraySet
import androidx.recyclerview.widget.RecyclerView


@JvmOverloads
fun orangeMsgDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {
    OrangeDialog.Builder(context)
        .setDialogType(DialogType.MESSAGE)
        .setTitle(title)
        .setContent(content)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}


@JvmOverloads
fun orangeImageDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    @DrawableRes iconResId: Int = -1,
    negativeText: String? = null,
    positiveText: String? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {
    OrangeDialog.Builder(context)
        .setDialogType(DialogType.IMAGE)
        .setTitle(title)
        .setIconResId(iconResId)
        .setContent(content)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}

fun orangeCustomDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    customLayoutId: Int = -1,
    onCustomViewCallback: OrangeDialog.CustomViewCallBack? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {
    OrangeDialog.Builder(context)
        .setDialogType(DialogType.CUSTOM)
        .setTitle(title)
        .setContent(content)
        .setCustomView(customLayoutId, onCustomViewCallback)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}

@JvmOverloads
fun orangeInputDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    needInput: Boolean = true,
    inputValue: String? = null,
    inputFocus: Boolean = true,
    inputMax: Int = -1,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {
    OrangeDialog.Builder(context)
        .setDialogType(DialogType.INPUT)
        .setTitle(title)
        .setContent(content)
        .setInputMax(15)
        .setInputFocus(inputFocus)
        .setNeedInput(needInput)
        .setInputValue(inputValue)
        .setInputMax(inputMax)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}

@JvmOverloads
fun orangeSingeDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    items: Array<String>? = null,
    selectIndex: Int = -1,
    singleChoiceCallBack: SingleChoiceCallback? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {

    OrangeDialog.Builder(context)
        .setDialogType(DialogType.SINGLE_CHOICE)
        .setTitle(title)
        .setContent(content)
        .setItems(items)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setItemsCallbackSingleChoice(selectIndex, singleChoiceCallBack)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}

@JvmOverloads
fun orangeMultiDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    items: Array<String>? = null,
    selectedIndices: ArraySet<Int>? = null,
    multiChoiceCallback: MultiChoiceCallback? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {

    OrangeDialog.Builder(context)
        .setDialogType(DialogType.MULTI_CHOICE)
        .setTitle(title)
        .setContent(content)
        .setItems(items)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .setItemsCallbackMultiChoice(selectedIndices, multiChoiceCallback)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .show()
}


@JvmOverloads
fun orangeListDialog(
    context: Context,
    title: String? = null,
    content: String? = null,
    negativeText: String? = null,
    positiveText: String? = null,
    adapter: RecyclerView.Adapter<*>? = null,
    layoutManager: RecyclerView.LayoutManager? = null,
    onPositiveCallback: SingleButtonCallback? = null,
    onNegativeCallback: SingleButtonCallback? = null,
    @DrawableRes positiveBackground: Int = 0,
    @DrawableRes negativeBackground: Int = 0,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true
) {
    OrangeDialog.Builder(context)
        .setDialogType(DialogType.LIST)
        .setTitle(title)
        .setContent(content)
        .setAdapter(adapter, layoutManager)
        .setNegativeText(negativeText)
        .setPositiveText(positiveText)
        .setonPositiveCallBack(onPositiveCallback)
        .setOnNegativeCallBack(onNegativeCallback)
        .setAutoDismiss(autoDismiss)
        .setCancelable(cancelable)
        .setPositiveBackground(positiveBackground)
        .setNegativeBackground(negativeBackground)
        .show()
}


@JvmOverloads
fun orangeDialog(
    context: Context,
    type: DialogType = DialogType.MESSAGE,
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
    singleChoiceCallBack: SingleChoiceCallback? = null,
    multiChoiceCallback: MultiChoiceCallback? = null,
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
        singleChoiceCallBack,
        multiChoiceCallback,
        onPositive,
        onNegative
    ).show()
}


@JvmOverloads
fun OrangeDialogBuilder(
    context: Context,
    type: DialogType = DialogType.MESSAGE,
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
    singleChoiceCallBack: SingleChoiceCallback? = null,
    multiChoiceCallback: MultiChoiceCallback? = null,
    onPositive: SingleButtonCallback? = null,
    onNegative: SingleButtonCallback? = null,
    adapter: RecyclerView.Adapter<*>? = null,
    layoutManager: RecyclerView.LayoutManager? = null,
    customLayoutId: Int = -1,
    onCustomViewCallback: OrangeDialog.CustomViewCallBack? = null,
    secondInputVisible: Boolean = false,
    inputHeight: Int = -1,
    autoDismiss: Boolean = true,
    cancelable: Boolean = true,
    lines: Int = -1,
    where: TextUtils.TruncateAt? = null
): OrangeDialog.Builder {
    val builder = OrangeDialog.Builder(context)
    return builder.apply {
        setDialogType(type)
        setNeedInput(needInput)
        setInputMax(inputMax)
        setInputValue(inputValue)
        setInputFocus(inputFocus)
        title?.let {
            builder.setTitle(title)
        }
        content?.let {
            setContent(content)
        }
        items?.let {
            setItems(items)
        }
        setContentLinesAndEllipsize(lines, where)
        setSelectedIndexMultiChoice(selectedIndices)
        setSelectedIndexSingleChoice(selectedIndex)
        setInputHeight(inputHeight)
        setAutoDismiss(autoDismiss)
        setCancelable(cancelable)
        singleChoiceCallBack?.let {
            setItemsCallbackSingleChoice(selectIndex, singleChoiceCallBack)
        }
        multiChoiceCallback?.let {
            setItemsCallbackMultiChoice(selectIndexs, multiChoiceCallback)
        }

        negativeText?.let {
            builder.setNegativeText(negativeText)
        }
        positiveText?.let {
            builder.setPositiveText(positiveText)
        }
        positiveBackground?.let {
            setPositiveBackground(positiveBackground)
        }
        negativeBackground?.let {
            setNegativeBackground(negativeBackground)
        }
        onPositive?.let {
            this.setonPositiveCallBack(onPositive)
        }
        onNegative?.let {
            this.setOnNegativeCallBack(onNegative)
        }
        this.setSecondInputVisible(secondInputVisible)
        this.setAdapter(adapter, layoutManager)
        this.setCustomView(customLayoutId, onCustomViewCallback)
    }
}

//region 点击接口部分
typealias OnItemClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Unit

typealias OnItemLongClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Boolean


typealias OnItemChildClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Unit

typealias OnItemChildLongClickListener = (adapter: OrangeAdapter<*>, view: View, position: Int) -> Boolean


typealias SingleButtonCallback = (dialog: OrangeDialog, dialogAction: String) -> Unit


typealias SingleChoiceCallback = (dialog: OrangeDialog, itemView: View, which: Int, text: CharSequence?) -> Boolean


typealias MultiChoiceCallback = (dialog: OrangeDialog, which: ArraySet<Int>?, texts: Array<CharSequence?>?) -> Boolean
//endregion



