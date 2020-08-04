package me.shetj.dialogtest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.shetj.base.ktx.toJson
import me.shetj.dialog.*

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

        OrangeDialogBuilder(
            this, DialogType.IMAGE,
            title = "温馨提示",
            content = "确定要取消下载所选内容吗?",
            positiveText = "确定删除",
            onPositive = object : SingleButtonCallback {
                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
                }
            }).show()

        orangeDialog(
            this,
            title = "提示",
            content = "确定要取消下载所选内容吗?",
            positiveText = "确定删除",
            onPositive = object : SingleButtonCallback {
                override fun invoke(dialog: OrangeDialog, dialogAction: String) {
                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
                }
            })


        orangeImageDialog(
            context = this,
            title = "orangeImageDialog",
            content = "确定要取消下载所选内容吗",
            negativeText = "取消",
            positiveText = "保存"
        )

        orangeMsgDialog(
            context = this,
            title = "orangeMsgDialog",
            content = "确定要取消下载所选内容吗",
            positiveText = "确定删除"
        )

        orangeSingeDialog(
            context = this,
            title = "orangeSingeDialog",
            content = "这是测试orangeSingeDialog",
            items = arrayOf("哈哈", "嘿嘿", "嘿哈"),
            selectIndex = -1,
            negativeText = "取消",
            positiveText = "保存",
            singleChoiceCallBack = object : SingleChoiceCallback {
                override fun invoke(
                    dialog: OrangeDialog,
                    itemView: View,
                    which: Int,
                    text: CharSequence?
                ): Boolean {
                    Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
                    return true
                }
            }
        )

        orangeMultiDialog(context = this,
            title = "orangeMultiDialog",
            content = "这是测试orangeMultiDialog",
            items = arrayOf("哈哈", "嘿嘿", "嘿哈", "嘿嘿哈哈"),
            multiChoiceCallback = object : MultiChoiceCallback {
                override fun invoke(
                    dialog: OrangeDialog,
                    which: androidx.collection.ArraySet<Int>?,
                    texts: Array<CharSequence?>?
                ): Boolean {
                    Toast.makeText(this@MainActivity, texts.toJson(), Toast.LENGTH_SHORT).show()
                    return true
                }
            })



        orangeInputDialog(
            context = this,
            title = "orangeInputDialog",
            content = "名字最长为15个字",
            inputValue = "OrangeDialog",
            negativeText = "取消",
            positiveText = "保存"
        )

        orangeCustomDialog(
            context = this,
            title = "orangeCustomDialog",
            content = "这是测试orangeCustomDialog",
            negativeText = "取消",
            positiveText = "保存",
            customLayoutId = R.layout.test_layout
        )
    }
}
