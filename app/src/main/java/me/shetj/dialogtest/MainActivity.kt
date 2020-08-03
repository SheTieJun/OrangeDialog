package me.shetj.dialogtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.shetj.dialog.*
import me.shetj.dialog.OrangeDialog.Companion.DIALOG_TYPE_MULTI_CHOICE
import me.shetj.dialog.OrangeDialog.Companion.DIALOG_TYPE_SINGLE_CHOICE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_type_1.setOnClickListener {
            show()
        }
    }

    /**
     * 展示是否删除所选
     */
    private fun show() {
//
//        OrangeDialogBuilder(
//            this, DIALOG_TYPE_IMAGE,
//            title = "温馨提示",
//            content = "确定要取消下载所选内容吗?",
//            positiveText = "确定删除",
//            onPositive = object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
//                }
//            }).apply {
//
//            //可以继续设置相关参数
//        }.show()
//
//        OrangeDialog.Builder(this)
//            .title("提示")
//            .dialogType(DIALOG_TYPE_IMAGE)
//            .content("确定要取消下载所选内容吗？")
//            .negativeText("取消")
//            .positiveText("确定删除")
//            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .show()
//
//
//        orangeDialog(
//            this,
//            title = "提示",
//            content = "确定要取消下载所选内容吗?",
//            positiveText = "确定删除",
//            onPositive = object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//
//
//
//        OrangeDialog.Builder(this)
//            .title("提示")
//            .content("确定要取消下载所选内容吗？")
//            .negativeText("取消")
//            .positiveText("确定删除")
//            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .show()
//
//        OrangeDialog.Builder(this)
//            .title("提示")
//            .dialogType(DIALOG_TYPE_MESSAGE)
//            .content("确定要取消下载所选内容吗？")
//            .negativeText("取消")
//            .positiveText("确定删除")
//            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .show()

//        OrangeDialog.Builder(this)
//            .title("提示")
//            .dialogType(DIALOG_TYPE_SINGLE_CHOICE)
//            .negativeText("取消")
//            .positiveText("确定删除")
//            .items(arrayOf("哈哈", "嘿嘿", "嘿哈"))
//            .itemsCallbackSingleChoice(-1, object : ListCallbackSingleChoice {
//                override fun invoke(
//                    dialog: OrangeDialog,
//                    itemView: View,
//                    which: Int,
//                    text: CharSequence?
//                ): Boolean {
//                    return true
//                }
//            })
//            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//
//                }
//            })
//            .show()


        OrangeDialog.Builder(this)
            .title("提示")
            .dialogType(DIALOG_TYPE_MULTI_CHOICE)
            .negativeText("取消")
            .positiveText("确定删除")
            .items(arrayOf("哈哈", "嘿嘿", "嘿哈", "嘿嘿哈哈"))
            .itemsCallbackMultiChoice(null, object : ListCallbackMultiChoice {

                override fun invoke(
                    dialog: OrangeDialog,
                    which: androidx.collection.ArraySet<Int>?,
                    texts: Array<CharSequence?>?
                ): Boolean {
                    return true
                }
            })
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive(object : SingleButtonCallback {
                override fun invoke(dialog: OrangeDialog, dialogAction: String) {

                }
            })
            .show()
//
//        OrangeDialog.Builder(this)
//            .title("下载提示")
//            .content("非WIFI情况下暂停下载，如需继续，请到设置中开启限制")
//            .negativeText("取消")
//            .positiveText("去设置")
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    Toast.makeText(this@MainActivity, "去设置", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .show()
//
//        OrangeDialog.Builder(this)
//            .title("未完成认证，无法上传视频！")
//            .positiveText("确定")
//            .setPositiveBackground(R.drawable.orange_dialog_btn_one_positive_selector)
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//
//                }
//            })
//            .show()
//
//        OrangeDialog.Builder(this)
//            .title("提示")
//            .content("视频出了一点小问题")
//            .build()
//            .show()
//        OrangeDialog.Builder(this)
//            .dialogType(DIALOG_TYPE_INPUT)
//            .title("输入名字")
//            .content("名字最长为15个字")
//            .inputMax(15)
//            .inputValue("OrangeDialog")
//            .inputFocus(true)
//            .needInput(true)
//            .negativeText("取消")
//            .positiveText("保存")
//            .onPositive(object : SingleButtonCallback {
//                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
//                    val name = dialog.inputEditText?.text.toString().trim()
//                    Toast.makeText(this@MainActivity, name, Toast.LENGTH_SHORT).show()
//                }
//            })
//            .show()

    }
}
