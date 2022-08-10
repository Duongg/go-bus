package com.example.gobus.utils

import android.app.Activity
import android.app.Dialog
import android.view.ViewGroup
import android.widget.TextView
import com.example.gobus.R

class CustomDialog {
    companion object{
        fun showDialogNoInternetConnection(activity: Activity, title: String, description: String, textBtn: String){
            val dialogMain = Dialog(activity)
            dialogMain.setContentView(R.layout.custom_dialog_1)
            dialogMain.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialogMain.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            dialogMain.window?.setDimAmount(0.7f)
            dialogMain.show()

            val titleDialog = dialogMain.findViewById<TextView>(R.id.titleDialog)
            val desDialog = dialogMain.findViewById<TextView>(R.id.desDialog)
            val textClose = dialogMain.findViewById<TextView>(R.id.btnClose)

            titleDialog.text = title
            desDialog.text = description
            textClose.text = textBtn

            textClose.setOnClickListener {
                dialogMain.dismiss()
            }
        }
    }
}