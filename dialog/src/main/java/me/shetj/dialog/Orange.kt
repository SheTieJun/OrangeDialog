package me.shetj.dialog

import android.content.Context
import androidx.annotation.DrawableRes

fun OrangeDialogBuilder(context: Context,
                         type: Int,
                         title: String ?= null,
                         content:String?=null,
                         negativeText :String ? = "取消",
                         positiveText :String ? = "确定",
                         items : Array<String>?=null,
                         @DrawableRes positiveBackground:  Int ?=R.drawable.orange_dialog_btn_error_positive_selector,
                         @DrawableRes negativeBackground:  Int ?=R.drawable.orange_dialog_btn_negative_selector,
                         selectIndex :Int = -1,
                         itemsCallbackSingleChoice : OrangeDialog.ListCallbackSingleChoice? =null,
                         onPositive : OrangeDialog.SingleButtonCallback ?=null,
                         onNegative : OrangeDialog.SingleButtonCallback ?=null

):OrangeDialog.Builder{
    val builder = OrangeDialog.Builder(context)
    return  builder.apply {
        builder.dialogType(type)
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