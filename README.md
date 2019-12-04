## OrangeDialog

### v 0.0.1

need :
```groovy
    implementation "com.github.SheTieJun:OrangeDialog:0.0.1"
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.49-androidx'
```

```kotlin
      OrangeDialogBuilder(
            this, DIALOG_TYPE_IMAGE,
            title = "提示2",
            content = "确定要取消下载所选内容吗?2",
            onPositive = SingleButtonCallback { _, _ ->
                Toast.makeText(this@MainActivity,"确定",Toast.LENGTH_SHORT).show()
            }).show()
```


``` java
  OrangeDialog.Builder(this)
            .title("提示")
            .dialogType(DIALOG_TYPE_IMAGE)
            .content("确定要取消下载所选内容吗？")
            .negativeText("取消")
            .positiveText("确定删除")
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive { _, _ ->

            }
            .show()

        OrangeDialog.Builder(this)
            .title("提示")
            .content("确定要取消下载所选内容吗？")
            .negativeText("取消")
            .positiveText("确定删除")
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive { _, _ ->

            }
            .show()

        OrangeDialog.Builder(this)
            .title("提示")
            .dialogType(DIALOG_TYPE_MESSAGE)
            .content("确定要取消下载所选内容吗？")
            .negativeText("取消")
            .positiveText("确定删除")
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive { _, _ ->

            }
            .show()

        OrangeDialog.Builder(this)
            .title("提示")
            .dialogType(DIALOG_TYPE_SINGLE_CHOICE)
            .negativeText("取消")
            .positiveText("确定删除")
            .items(arrayOf("哈哈","嘿嘿","嘿哈"))
            .itemsCallbackSingleChoice(-1) { dialog, itemView, which, text ->
                Toast.makeText(this,"选择：$text",Toast.LENGTH_SHORT).show()
                return@itemsCallbackSingleChoice true
            }
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive { _, _ ->

            }
            .show()


        OrangeDialog.Builder(this)
            .title("提示")
            .dialogType(DIALOG_TYPE_MULTI_CHOICE)
            .negativeText("取消")
            .positiveText("确定删除")
            .items(arrayOf("哈哈","嘿嘿","嘿哈","嘿嘿哈哈"))
            .itemsCallbackMultiChoice(null) { dialog, which, text ->
                Toast.makeText(this,"选择：${text.size}个",Toast.LENGTH_SHORT).show()
                return@itemsCallbackMultiChoice true
            }
            .setPositiveBackground(R.drawable.orange_dialog_btn_error_positive_selector)
            .onPositive { _, _ ->
            }
            .show()

        OrangeDialog.Builder(this)
            .title("下载提示")
            .content("非WIFI情况下暂停下载，如需继续，请到设置中开启限制")
            .negativeText("取消")
            .positiveText("去设置")
            .onPositive { _, _ ->
                Toast.makeText(this,"去设置",Toast.LENGTH_SHORT).show()
            }
            .show()

        OrangeDialog.Builder(this)
            .title("未完成认证，无法上传视频！")
            .positiveText("确定")
            .setPositiveBackground(R.drawable.orange_dialog_btn_positive_selector)
            .onPositive { dialog, dialogAction -> dialog.dismiss() }
            .show()

        OrangeDialog.Builder(this)
            .title("提示")
            .content("视频出了一点小问题")
            .build()
            .show()
        OrangeDialog.Builder(this)
            .dialogType(DIALOG_TYPE_INPUT)
            .title("输入名字")
            .content("名字最长为15个字")
            .inputMax(15)
            .inputValue("OrangeDialog")
            .inputFocus(true)
            .needInput(true)
            .negativeText("取消")
            .positiveText("保存")
            .onPositive { dialog, dialogAction ->
                val name = dialog.inputEditText?.text.toString().trim()
                Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
            }
            .show()

```
