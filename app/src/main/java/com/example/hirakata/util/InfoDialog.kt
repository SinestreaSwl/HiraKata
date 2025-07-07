package com.example.hirakata.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.view.Window
import android.widget.Button
import com.example.hirakata.R

object InfoDialog {

       fun show(context: Context, title: String, content: String) {

           val dialog = Dialog(context)
           val view = LayoutInflater.from(context).inflate(R.layout.dialog_info, null)

           val tvTitle = view.findViewById<TextView>(R.id.infoTitle)
           val tvContent = view.findViewById<TextView>(R.id.infoContent)
           val btnClose = view.findViewById<Button>(R.id.btnCloseInfo)

           tvTitle.text = title
           tvContent.text = content

           btnClose.setOnClickListener {
               dialog.dismiss()
           }

           dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
           dialog.setContentView(view)
           dialog.setCancelable(true)
           dialog.show()

       }
}