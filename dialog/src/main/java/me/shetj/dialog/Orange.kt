package me.shetj.dialog

import android.content.Context
import androidx.annotation.DrawableRes

@JvmOverloads
fun OrangeDialogBuilder(context: Context,
                        type: Int =OrangeDialog.DIALOG_TYPE_MESSAGE,
                        title: String ?= null,
                        content:String?=null,
                        negativeText :String ? = null,
                        positiveText :String ? = null,
                        needInput :Boolean  = false,
                        inputValue :String ?= null,
                        inputFocus :Boolean = true,
                        inputMax :Int = -1,
                        items : Array<String>?=null,
                        @DrawableRes positiveBackground:  Int ?=R.drawable.orange_dialog_btn_error_positive_selector,
                        @DrawableRes negativeBackground:  Int ?=R.drawable.orange_dialog_btn_negative_selector,
                        selectIndex :Int = -1,
                        itemsCallbackSingleChoice : OrangeDialog.ListCallbackSingleChoice? =null,
                        selectIndexs: Array<Int> ?= null,
                        itemsCallbackMultiChoice: OrangeDialog.ListCallbackMultiChoice?=null,
                        onPositive : OrangeDialog.SingleButtonCallback ?=null,
                        onNegative : OrangeDialog.SingleButtonCallback ?=null

):OrangeDialog.Builder{
    val builder = OrangeDialog.Builder(context)
    return  builder.apply {
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
            itemsCallbackSingleChoice(selectIndex,itemsCallbackSingleChoice)
        }
        itemsCallbackMultiChoice?.let {
            itemsCallbackMultiChoice(selectIndexs,itemsCallbackMultiChoice)
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