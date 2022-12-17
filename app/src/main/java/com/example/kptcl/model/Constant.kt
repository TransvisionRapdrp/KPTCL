package com.example.kptcl.model

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Constant {

    var CUSTINFOSERVICE = "http://bc_service.hescomtrm1.com/CUSTINFOSERVICE.asmx/"
    fun getHours(input: String?): String? {
        //Date/time pattern of input date
        val df: DateFormat = SimpleDateFormat("HH:mm")
        //Date/time pattern of desired output date
        val outputformat: DateFormat = SimpleDateFormat("hh:mm aa")
        var date: Date? = null
        var output: String? = null
        try {
            //Conversion of input String to date
            date = df.parse(input)
            //old date format to new date format
            output = outputformat.format(date)
            println(output)
        } catch (pe: ParseException) {
            return "NA"
        }
        return output
    }

    fun getDays(day: Int): String? {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH] + 1
        val input = "$day-$month-$year"

        //Date/time pattern of input date
        @SuppressLint("SimpleDateFormat") val df: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        //Date/time pattern of desired output date
        @SuppressLint("SimpleDateFormat") val outputformat: DateFormat = SimpleDateFormat("E")
        var date: Date? = null
        var output: String? = null
        try {
            //Conversion of input String to date
            date = df.parse(input)
            //old date format to new date format
            output = outputformat.format(date)
            Log.d("SUdh",output)
        } catch (pe: ParseException) {
            return "NA"
        }
        return output
    }

    fun showprogressdialog(Message: String?, dialog: ProgressDialog, Title: String?) {
        dialog.setTitle(Title)
        dialog.setMessage(Message)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun showtoast(context: Context?, Message: String?) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
    }

    fun getCurrentDate(): String? {
        val c = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(c)
    }
}