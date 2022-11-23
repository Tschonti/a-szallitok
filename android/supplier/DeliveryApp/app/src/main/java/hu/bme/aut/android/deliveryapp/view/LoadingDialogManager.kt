package hu.bme.aut.android.deliveryapp.view

import android.content.Context
import com.devhoony.lottieproegressdialog.LottieProgressDialog

object LoadingDialogManager {
    fun getLoadingDialog(context: Context): LottieProgressDialog {
        return LottieProgressDialog(
            context = context,
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_8,
            title = null,
            titleVisible = null)
    }
}