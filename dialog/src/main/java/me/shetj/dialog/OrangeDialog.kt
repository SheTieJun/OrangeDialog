package me.shetj.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.text.TextUtils.TruncateAt
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.collection.ArraySet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import me.shetj.dialog.DialogUtils.hideKeyboard
import me.shetj.dialog.DialogUtils.showKeyboard
import me.shetj.dialog.ScreenUtil.dip2px

open class OrangeDialog(/*-----------------------------------DIALOG ACTION END-----------------------------------*/
    protected val builder: Builder
) :
    Dialog(builder.context!!, R.style.orange_DialogStyle) {
    var inputEditText: EditText? = null
    var secondInputEditText: EditText? = null
    var inputWarning: TextView? = null
    var dialogNegative: TextView? = null
    var dialogPositive: TextView? = null

    val inputValue: String?
        get() = if (inputEditText != null) {
            inputEditText!!.text.toString().trim { it <= ' ' }
        } else null

    val secondInputValue: String?
        get() = if (secondInputEditText != null) {
            secondInputEditText!!.text.toString().trim { it <= ' ' }
        } else null

    /**
     * 清空输入内容
     */
    fun clearInputValue() {
        if (inputEditText != null) {
            inputEditText!!.setText("")
        }
    }

    /**
     * 设置警告内容
     *
     * @param warning null就不显示
     */
    fun setInputWarning(warning: String?) {
        val inputWarning = inputWarning ?: return
        if (TextUtils.isEmpty(warning)) {
            if (inputWarning.visibility != View.GONE) {
                inputWarning.visibility = View.GONE
            }
        } else {
            if (inputWarning.visibility != View.VISIBLE) {
                inputWarning.visibility = View.VISIBLE
            }
            inputWarning.text = warning
        }
    }

    /**
     * 是否有第二个输入框
     */
    val isHasSecondInput: Boolean
        get() = builder.secondInputVisible

    /**
     * 获得选中的单选框内容
     *
     * @return null表示不符合
     */
    val singleChoiceText: String?
        get() {
            val length = itemsLength
            if (length == 0) {
                return null
            }
            return if (builder.selectedIndex > -1 && builder.selectedIndex < length) {
                if (builder.isNeedInput && builder.selectedIndex == length - 1) {
                    inputValue
                } else {
                    builder.items!![builder.selectedIndex]
                }
            } else null
        }

    /**
     * 获得选中的多选框内容
     *
     * @return null表示没有
     */
    val multiChoiceTexts: Array<String?>?
        get() {
            val length = itemsLength
            if (length == 0) {
                return null
            }
            if (builder.selectedIndices != null && builder.selectedIndices!!.isNotEmpty()) {
                val text =
                    arrayOfNulls<String>(builder.selectedIndices!!.size)
                builder.selectedIndices?.forEachIndexed { index, i ->
                    if (builder.isNeedInput && i == length - 1) {
                        text[index] = inputValue
                    } else {
                        text[index] = builder.items!![i]
                    }
                }
                return text
            }
            return null
        }

    /**
     * 获得选择的单选框索引
     *
     * @return -1表示没有设置
     */
    val singleChoiceIndex: Int
        get() = builder.selectedIndex

    /**
     * 获得选择的多选框索引
     *
     * @return null表示没有设置
     */
    val multiChoiceIndexs: Set<Int>?
        get() = builder.selectedIndices


    /**
     * An alternate way to define a custom-view callback.
     */
    interface CustomViewCallBack {
        fun onInitCustomView(
            dialog: OrangeDialog,
            customView: View?
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(builder.cancelable)
        setCanceledOnTouchOutside(builder.canceledOnTouchOutside)
        when (builder.dialogType) {
            DialogType.INPUT -> initInputLayout()
            DialogType.IMAGE -> initImageLayout()
            DialogType.SINGLE_CHOICE -> initSingleChoiceLayout()
            DialogType.MULTI_CHOICE -> initMultiChoiceLayout()
            DialogType.LIST -> initListLayout()
            DialogType.CUSTOM -> initCustomLayout()
            DialogType.MESSAGE -> initMessageLayout()
        }
        // 通用控件
        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)
        val dialogContent = findViewById<TextView>(R.id.dialogContent)
        dialogNegative = findViewById(R.id.dialogNegative)
        dialogPositive = findViewById(R.id.dialogPositive)
        if (builder.title != null) {
            dialogTitle.visibility = View.VISIBLE
            dialogTitle.text = builder.title
        } else {
            dialogTitle.visibility = View.GONE
        }
        if (builder.titleLines > 0) {
            if (builder.titleLines == 1) {
                dialogTitle.isSingleLine = true
            } else {
                dialogTitle.setLines(builder.titleLines)
            }
        }
        if (builder.titleEllipsize != null) {
            dialogTitle.ellipsize = builder.titleEllipsize
        }
        if (builder.content != null) {
            dialogContent.visibility = View.VISIBLE
            dialogContent.text = builder.content
        } else {
            dialogContent.visibility = View.GONE
        }
        if (builder.contentLines > 0) {
            if (builder.contentLines == 1) {
                dialogContent.isSingleLine = true
            } else {
                dialogContent.setLines(builder.contentLines)
            }
        }
        if (builder.contentEllipsize != null) {
            dialogContent.ellipsize = builder.contentEllipsize
        }
        if (builder.positiveText != null) {
            dialogPositive!!.visibility = View.VISIBLE
            dialogPositive!!.text = builder.positiveText
        } else {
            dialogPositive!!.visibility = View.GONE
        }

        if (builder.positiveTextColor != -1) {
            dialogPositive!!.setTextColor(
                ContextCompat.getColor(
                    context,
                    builder.positiveTextColor
                )
            )
        }
        if (builder.negativeText != null) {
            dialogNegative!!.visibility = View.VISIBLE
            dialogNegative!!.text = builder.negativeText
        } else {
            dialogNegative!!.visibility = View.GONE
        }

        //设置单个按钮颜色
        if (dialogNegative!!.visibility == View.VISIBLE && dialogPositive!!.visibility == View.GONE) {
            dialogNegative!!.setBackgroundResource(R.drawable.orange_dialog_btn_one_positive_selector)
        } else if (dialogPositive!!.visibility == View.VISIBLE && dialogNegative!!.visibility == View.GONE) {
            dialogPositive!!.setBackgroundResource(R.drawable.orange_dialog_btn_one_positive_selector)
        } else if (dialogPositive!!.visibility == View.VISIBLE && dialogNegative!!.visibility == View.VISIBLE) {
            if (builder.negativeBackground != -1) {
                dialogNegative!!.setBackgroundResource(builder.negativeBackground)
            }
            if (builder.positiveBackground != -1) {
                dialogPositive!!.setBackgroundResource(builder.positiveBackground)
            }
        }

        if (builder.negativeTextColor != -1) {
            dialogNegative!!.setTextColor(
                ContextCompat.getColor(
                    context,
                    builder.negativeTextColor
                )
            )
        }

        dialogPositive!!.setOnClickListener {
            builder.onPositiveCallback?.invoke(
                this@OrangeDialog,
                DIALOG_ACTION_POSITIVE
            )
            if (builder.autoDismiss) {
                dismiss()
            }
        }
        dialogNegative!!.setOnClickListener {
            builder.onNegativeCallback?.invoke(
                this@OrangeDialog,
                DIALOG_ACTION_NEGATIVE
            )
            if (builder.autoDismiss) {
                dismiss()
            }
        }
    }

    override fun dismiss() {
        if (inputEditText != null) { // 必须在dismiss前隐藏键盘，不然永远无法隐藏
            hideKeyboard(this)
        }
        super.dismiss()
    }

    private fun initMessageLayout() {
        setContentView(R.layout.orange_dialog_message)
    }

    private fun initInputLayout() {
        setContentView(R.layout.orange_dialog_input)
        // 输入框
        val dialogInput = findViewById<EditText>(R.id.dialogInput)
        val dialogSecondInput = findViewById<EditText>(R.id.dialogSecondInput)
        val dialogWarning = findViewById<TextView>(R.id.dialogWarning)
        inputEditText = dialogInput
        secondInputEditText = dialogSecondInput
        inputWarning = dialogWarning
        if (builder.inputHeight > 0) {
            val layoutParams = dialogInput.layoutParams
            layoutParams.height = builder.inputHeight
        } else {
            dialogInput.isSingleLine = true
        }
        if (builder.hint != null) {
            dialogInput.hint = builder.hint
        }
        if (builder.secondHint != null) {
            dialogSecondInput.hint = builder.secondHint
        }
        if (builder.inputValue != null) {
            dialogInput.setText(builder.inputValue)
            dialogInput.setSelection(builder.inputValue!!.length)
        }
        if (builder.secondInputValue != null) {
            dialogSecondInput.setText(builder.secondInputValue)
            dialogSecondInput.setSelection(builder.secondInputValue!!.length)
        }
        if (builder.secondInputVisible) {
            dialogSecondInput.visibility = View.VISIBLE
        } else {
            dialogSecondInput.visibility = View.GONE
        }
        if (builder.inputType != -1) { //设置EditText输入类型，但是代码设置，需要调用setSingleLine为true才能生效
            dialogInput.inputType = builder.inputType
            dialogInput.isSingleLine = true
        }
        if (builder.inputSize != -1) {
            dialogInput.filters = arrayOf<InputFilter>(LengthFilter(builder.inputSize))
            dialogInput.maxEms = builder.inputSize
        }
        if (builder.inputFocus) {
            showKeyboard(this)
        } else {
            hideKeyboard(this)
        }
    }

    private fun initImageLayout() {
        setContentView(R.layout.orange_dialog_image)
        val dialogIcon =
            findViewById<ImageView>(R.id.dialogIcon)
        if (builder.iconResId != -1) {
            dialogIcon.setImageResource(builder.iconResId)
        } else {
            dialogIcon.setImageResource(R.drawable.orange_dialog_base_image_icon)
        }
    }

    private fun initSingleChoiceLayout() {
        setContentView(R.layout.orange_dialog_choice)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val itemAdapter: OrangeAdapter<String> = object :
            OrangeAdapter<String>(
                R.layout.orange_item_single_choice,
                builder.items?.toMutableList()
            ) {
            override fun convert(
                holder: BaseViewHolder,
                data: String
            ) { // 标题
                holder.setText(R.id.singleName, data)
                // 选中&未选中
                if (builder.selectedIndex > -1) {
                    holder.getView<View>(R.id.singleChoice).isSelected =
                        builder.selectedIndex == getIndexByString(data)
                } else {
                    holder.getView<View>(R.id.singleChoice).isSelected = 0 == getIndexByString(data)
                }
            }
        }
        itemAdapter.setOnItemClickListener { adapter, view, position ->
            if (builder.selectedIndex == position) {
                return@setOnItemClickListener
            }
            val item = adapter.getItem(position) as String?
            // 刷新UI
            builder.setSelectedIndexSingleChoice(position)
            itemAdapter.notifyDataSetChanged()
            // 回调给外面
            builder.listCallbackSingleChoice?.invoke(
                this@OrangeDialog,
                view,
                position,
                item
            )
            // 显示或者隐藏键盘
            if (builder.isNeedInput && position == itemsLength - 1) {
                showKeyboard(this@OrangeDialog)
            } else {
                hideKeyboard(this@OrangeDialog)
            }
        }

        recyclerView.adapter = itemAdapter
        // “其他”选项的时候需要输入自定义内容
        val dialogInput = findViewById<EditText>(R.id.dialogInput)
        inputEditText = dialogInput
        // 输入框
        dialogInput.visibility = if (builder.isNeedInput) View.VISIBLE else View.GONE
        if (builder.isNeedInput) {
            hideKeyboard(this) // 默认不显示键盘
        }
    }

    private fun initMultiChoiceLayout() {
        setContentView(R.layout.orange_dialog_choice)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val itemAdapter: OrangeAdapter<String> = object :
            OrangeAdapter<String>(
                R.layout.orange_item_single_choice,
                builder.items?.toMutableList()
            ) {
            override fun convert(
                holder: BaseViewHolder,
                data: String
            ) { // 标题
                holder.setText(R.id.singleName, data)
                // 选中
                holder.getView<View>(R.id.singleChoice)
                    .isSelected = isSelectedByPosition(holder.layoutPosition)
            }
        }
        itemAdapter.setOnItemClickListener { _, _, position ->
            // 处理选择与不选择的逻辑
            if (isSelectedByPosition(position)) { // 已选择->未选择
                builder.selectedIndices?.remove(position)
            } else { // 未选择->已选择
                builder.selectedIndices?.add(position)
            }
            itemAdapter.notifyDataSetChanged()
            // 回调给外面
            if (builder.listCallbackMultiChoice != null) {
                val text: Array<CharSequence?>
                if (builder.selectedIndices != null && builder.selectedIndices!!.isNotEmpty()) {
                    text = arrayOfNulls(builder.selectedIndices!!.size)
                    builder.selectedIndices?.forEachIndexed { index, i ->
                        text[index] = builder.items!![i]
                    }
                } else {
                    text = arrayOf()
                }
                builder.listCallbackMultiChoice!!.invoke(
                    this@OrangeDialog,
                    builder.selectedIndices!!,
                    text
                )
            }
            // 显示或者隐藏键盘
            if (builder.isNeedInput && position == itemsLength - 1) {
                showKeyboard(this@OrangeDialog)
            } else {
                hideKeyboard(this@OrangeDialog)
            }
        }
        recyclerView.adapter = itemAdapter
        // “其他”选项的时候需要输入自定义内容
        val dialogInput = findViewById<EditText>(R.id.dialogInput)
        inputEditText = dialogInput
        // 输入框
        dialogInput.visibility = if (builder.isNeedInput) View.VISIBLE else View.GONE
        if (builder.isNeedInput) {
            hideKeyboard(this) // 默认不显示键盘
        }
    }

    private fun initListLayout() {
        setContentView(R.layout.orange_dialog_list)
        val recyclerView: MaxHeightRecyclerView = findViewById(R.id.recyclerView)
        if (builder.listMaxHeight > 0) {
            recyclerView.setMaxHeight(dip2px(builder.listMaxHeight.toFloat()))
        }
        if (builder.adapter != null) {
            recyclerView.adapter = builder.adapter
        }
        if (builder.layoutManager != null) {
            recyclerView.layoutManager = builder.layoutManager
        }
    }

    private fun initCustomLayout() {
        setContentView(R.layout.orange_dialog_custom)
        val dialogCustom = findViewById<FrameLayout>(R.id.dialogCustom)
        if (builder.customLayoutId != -1) {
            val customView = LayoutInflater.from(context)
                .inflate(builder.customLayoutId, dialogCustom, false)
                ?: return
            if (builder.onCustomViewCallback != null) {
                builder.onCustomViewCallback!!.onInitCustomView(this@OrangeDialog, customView)
            }
            dialogCustom.addView(customView)
        }
    }

    private val itemsLength: Int
        get() = if (builder.items != null && builder.items!!.isNotEmpty()) {
            builder.items!!.size
        } else 0

    /**
     * 是否被选中
     *
     * @return -1表示未选中
     */
    private fun isSelectedByPosition(position: Int): Boolean {
        return builder.selectedIndices?.contains(position) ?: false
    }

    private fun getIndexByString(content: String): Int {
        var index = -1
        if (!TextUtils.isEmpty(content)) {
            if (builder.items != null && builder.items!!.isNotEmpty()) {
                for (i in builder.items!!.indices) {
                    if (content == builder.items!![i]) {
                        index = i
                        break
                    }
                }
            }
        }
        return index
    }

    class Builder(var context: Context?) {
        var title: CharSequence? = null
            private set
        var content: CharSequence? = null
            private set
        var positiveText: CharSequence? = null
            private set
        var negativeText: CharSequence? = null
            private set

        @ColorRes
        var positiveTextColor = -1
            private set

        @ColorRes
        var negativeTextColor = -1
            private set

        @DrawableRes
        var positiveBackground = -1
            private set

        @DrawableRes
        var negativeBackground = -1
            private set
        var cancelable = true
            private set
        var canceledOnTouchOutside = true
            private set
        var dialogType: DialogType = DialogType.MESSAGE
            private set
        var onPositiveCallback: SingleButtonCallback? = null
            private set
        var onNegativeCallback: SingleButtonCallback? = null
            private set
        var autoDismiss = true
            private set
        var titleLines = -1
            private set
        var titleEllipsize: TruncateAt? = null
            private set
        var contentLines = -1
            private set
        var contentEllipsize: TruncateAt? = null
            private set

        /*-----------------------------------INPUT BEGIN-----------------------------------*/
        var hint: CharSequence? = null
            private set
        var secondHint: CharSequence? = null
            private set
        var inputValue: CharSequence? = null
            private set
        var secondInputValue: CharSequence? = null
            private set
        var secondInputVisible = false
            private set
        var inputHeight = -1
            private set
        var inputFocus = false
            private set
        var inputType = -1
            private set
        var inputSize = -1
            private set

        /*-----------------------------------INPUT END-----------------------------------*/ /*-----------------------------------IMAGE BEGIN-----------------------------------*/
        @DrawableRes
        var iconResId = -1
            private set

        /*-----------------------------------IMAGE END-----------------------------------*/ /*-----------------------------------CHOICE BEGIN-----------------------------------*/
        var items: Array<String>? = null
            private set
        var isNeedInput = false
            private set
        var selectedIndex = -1
            private set
        var selectedIndices: ArraySet<Int>? = ArraySet()
            private set
        var listCallbackSingleChoice: SingleChoiceCallback? = null
            private set
        var listCallbackMultiChoice: MultiChoiceCallback? = null
            private set

        /*-----------------------------------CHOICE END-----------------------------------*/ /*-----------------------------------LIST BEGIN-----------------------------------*/
        var adapter: RecyclerView.Adapter<*>? = null
            private set
        var layoutManager: RecyclerView.LayoutManager? = null
            private set
        var listMaxHeight = 0
            private set

        /*-----------------------------------LIST END-----------------------------------*/ /*-----------------------------------CUSTOM BEGIN-----------------------------------*/
        var customLayoutId = -1
            private set
        var onCustomViewCallback: CustomViewCallBack? = null
            private set
        /*-----------------------------------CUSTOM END-----------------------------------*/
        /**
         * 标题
         *
         * @param titleRes 资源id
         */
        fun setTitle(@StringRes titleRes: Int): Builder {
            setTitle(context!!.getText(titleRes))
            return this
        }

        /**
         * 标题
         */
        fun setTitle(title: CharSequence?): Builder {
            this.title = title
            return this
        }

        /**
         * 行数和省略号
         *
         * @param where [TextUtils.TruncateAt.START]
         * 、[TextUtils.TruncateAt.MIDDLE]
         * 、[TextUtils.TruncateAt.END]
         * 、[TextUtils.TruncateAt.MARQUEE]
         */
        fun setTitleLinesAndEllipsize(
            lines: Int,
            where: TruncateAt?
        ): Builder {
            titleLines = lines
            titleEllipsize = where
            return this
        }

        /**
         * 内容，一般提示性性质
         *
         * @param contentRes 资源id
         */
        fun setContent(@StringRes contentRes: Int): Builder {
            setContent(context!!.getText(contentRes))
            return this
        }

        /**
         * 内容，一般提示性性质
         */
        fun setContent(content: CharSequence?): Builder {
            this.content = content
            return this
        }

        /**
         * 行数和省略号
         *
         * @param where [TextUtils.TruncateAt.START]
         * 、[TextUtils.TruncateAt.MIDDLE]
         * 、[TextUtils.TruncateAt.END]
         * 、[TextUtils.TruncateAt.MARQUEE]
         */
        fun setContentLinesAndEllipsize(
            lines: Int,
            where: TruncateAt?
        ): Builder {
            contentLines = lines
            contentEllipsize = where
            return this
        }

        /**
         * 确定按钮
         *
         * @param positiveRes 资源id
         */
        fun setPositiveText(@StringRes positiveRes: Int): Builder {
            if (positiveRes == 0) {
                return this
            }
            setPositiveText(context!!.getText(positiveRes))
            return this
        }

        fun setPositiveBackground(@DrawableRes positiveBackground: Int): Builder {
            if (positiveBackground == 0) {
                return this
            }
            this.positiveBackground = positiveBackground
            return this
        }

        fun setNegativeBackground(negativeBackground: Int): Builder {
            if (negativeBackground == 0) {
                return this
            }
            this.negativeBackground = negativeBackground
            return this
        }

        /**
         * 确定按钮
         */
        fun setPositiveText(message: CharSequence?): Builder {
            positiveText = message
            return this
        }

        /**
         * 确定按钮颜色
         */
        fun setPositiveTextColor(@ColorRes positiveTextColor: Int): Builder {
            this.positiveTextColor = positiveTextColor
            return this
        }

        /**
         * 取消按钮
         *
         * @param negativeRes 资源id
         */
        fun setNegativeText(@StringRes negativeRes: Int): Builder {
            return if (negativeRes == 0) {
                this
            } else setNegativeText(context!!.getText(negativeRes))
        }

        /**
         * 取消按钮
         */
        fun setNegativeText(message: CharSequence?): Builder {
            negativeText = message
            return this
        }

        /**
         * 取消按钮颜色
         */
        fun setNegativeTextColor(@ColorRes negativeTextColor: Int): Builder {
            this.negativeTextColor = negativeTextColor
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            canceledOnTouchOutside = cancelable
            return this
        }

        fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): Builder {
            this.canceledOnTouchOutside = canceledOnTouchOutside
            return this
        }

        /**
         * 弹窗的方式，默认是MESSAGE
         *
         * @param dialogType = [DialogType]
         */
        fun setDialogType(dialogType: DialogType): Builder {
            this.dialogType = dialogType
            return this
        }

        /**
         * 确定按钮的callback
         */
        fun setonPositiveCallBack(callback: SingleButtonCallback?): Builder {
            onPositiveCallback = callback
            return this
        }

        /**
         * 取消按钮的callback
         */
        fun setOnNegativeCallBack(callback: SingleButtonCallback?): Builder {
            onNegativeCallback = callback
            return this
        }

        /**
         * 点击按钮是否自动触发dismiss，默认自动触发
         */
        fun setAutoDismiss(autoDismiss: Boolean): Builder {
            this.autoDismiss = autoDismiss
            return this
        }

        /**
         * 设置输入框高度
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setInputHeight(inputHeight: Int): Builder {
            this.inputHeight = inputHeight
            return this
        }

        /**
         * 设置输入框高度
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setInputFocus(inputFocus: Boolean): Builder {
            this.inputFocus = inputFocus
            return this
        }

        /**
         * 设置输入方式
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setInputType(inputType: Int): Builder {
            this.inputType = inputType
            return this
        }

        /**
         * 输入框提示内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         *
         * @param hintRes 资源id
         */
        fun setHint(@StringRes hintRes: Int): Builder {
            setHint(context!!.getText(hintRes))
            return this
        }

        /**
         * 输入框提示内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setHint(hint: CharSequence?): Builder {
            this.hint = hint
            return this
        }

        /**
         * 次级输入框提示内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         *
         * @param secondHintRes 资源id
         */
        fun setSecondHint(@StringRes secondHintRes: Int): Builder {
            setSecondHint(context!!.getText(secondHintRes))
            return this
        }

        /**
         * 次级输入框提示内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setSecondHint(secondHint: CharSequence?): Builder {
            this.secondHint = secondHint
            return this
        }

        /**
         * 输入框输入内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         *
         * @param inputValueRes 资源id
         */
        fun setInputValue(@StringRes inputValueRes: Int): Builder {
            setInputValue(context!!.getText(inputValueRes))
            return this
        }

        /**
         * 输入框输入内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setInputValue(inputValue: CharSequence?): Builder {
            this.inputValue = inputValue
            return this
        }

        /**
         * 次级输入框输入内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         *
         * @param secondInputValueRes 资源id
         */
        fun setSecondInputValue(@StringRes secondInputValueRes: Int): Builder {
            setSecondInputValue(context!!.getText(secondInputValueRes))
            return this
        }

        /**
         * 次级输入框输入内容
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setSecondInputValue(secondInputValue: CharSequence?): Builder {
            this.secondInputValue = secondInputValue
            return this
        }

        /**
         * 次级输入框是否显示
         * <P>dialogType为[DialogType.INPUT]才生效</P>
         */
        fun setSecondInputVisible(secondInputVisible: Boolean): Builder {
            this.secondInputVisible = secondInputVisible
            return this
        }

        /**
         * 图片
         * <P>dialogType为[DialogType.IMAGE]才生效</P>
         *
         * @param iconResId
         */
        fun setIconResId(@DrawableRes iconResId: Int): Builder {
            this.iconResId = iconResId
            return this
        }

        /**
         * 列表
         */
        fun setItems(items: Array<String>?): Builder {
            this.items = items
            return this
        }

        /**
         * 是否展示键盘
         */
        fun setNeedInput(isNeedInput: Boolean): Builder {
            this.isNeedInput = isNeedInput
            return this
        }

        /**
         * 默认单项选择
         */
        fun setSelectedIndexSingleChoice(selectedIndex: Int): Builder {
            this.selectedIndex = selectedIndex
            return this
        }

        /**
         * 默认多项选择
         */
        fun setSelectedIndexMultiChoice(selectedIndices: ArraySet<Int>?): Builder {
            this.selectedIndices = selectedIndices
            return this
        }

        /**
         * 设置单选的回调
         * <P>单选：dialogType为[DialogType.SINGLE_CHOICE]才生效</P>
         *
         * @param selectedIndex 选中的选择框
         * @param callback      单选回调
         */
        fun setItemsCallbackSingleChoice(
            selectedIndex: Int,
            callback: SingleChoiceCallback?
        ): Builder {
            this.selectedIndex = selectedIndex
            listCallbackSingleChoice = callback
            listCallbackMultiChoice = null
            return this
        }

        /**
         * 设置多选的回调
         * <P>多选：dialogType为[DialogType.MULTI_CHOICE]才生效</P>
         *
         * @param selectedIndices 选中的选择框
         * @param callback        多选回调
         */
        fun setItemsCallbackMultiChoice(
            selectedIndices: ArraySet<Int>?,
            callback: MultiChoiceCallback?
        ): Builder {
            selectedIndices?.let {
                this.selectedIndices = selectedIndices
            }
            listCallbackSingleChoice = null
            listCallbackMultiChoice = callback
            return this
        }

        /**
         * 设置列表
         * <P>Sets a custom [RecyclerView.Adapter] for the dialog's list</P>
         * <P>dialogType为[DialogType.LIST]才生效</P>
         */
        fun setAdapter(
            adapter: RecyclerView.Adapter<*>?,
            layoutManager: RecyclerView.LayoutManager?
        ): Builder {
            this.adapter = adapter
            this.layoutManager = layoutManager
            return this
        }

        /**
         * 设置列表最大高度，单位dp
         * <P>dialogType为[DialogType.LIST]才生效</P>
         */
        fun setListMaxHeight(maxHeight: Int): Builder {
            listMaxHeight = maxHeight
            return this
        }

        /**
         * 设置最多输入的文字
         */
        fun setInputMax(i: Int): Builder {
            inputSize = i
            return this
        }

        /**
         * 设置自定义布局
         * <P>dialogType为[DialogType.CUSTOM]才生效</P>
         */
        fun setCustomView(
            customLayoutId: Int,
            onCustomViewCallback: CustomViewCallBack?
        ): Builder {
            this.customLayoutId = customLayoutId
            this.onCustomViewCallback = onCustomViewCallback
            return this
        }

        @UiThread
        fun build(): OrangeDialog {
            return OrangeDialog(this)
        }

        @UiThread
        fun show(): OrangeDialog {
            val dialog = build()
            dialog.show()
            return dialog
        }


    }




    companion object {
        const val DIALOG_ACTION_POSITIVE = "positive"
        const val DIALOG_ACTION_NEGATIVE = "negative"
    }

    init {
        builder.context = null
    }
}