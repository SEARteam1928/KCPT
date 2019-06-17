package com.team.sear.kcpt.objects

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class TimeTableViewsInit{

    private lateinit var getTimeTable: StatusDetermination

   fun getTT(day: String, lesson: String, dayTv: TextView, dayLn: LinearLayout, auth: FirebaseAuth) {
        try {
            getTimeTable.getTimeTable(day, lesson, dayTv, dayLn, auth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

     fun show(tv: TextView, ln: LinearLayout) {
        tv.visibility = View.GONE
        ln.visibility = View.VISIBLE
    }

     fun hide(tv: TextView, ln: LinearLayout) {
        tv.visibility = View.VISIBLE
        ln.visibility = View.GONE
    }

    fun invisView(v: View){
        v.visibility = View.GONE
    }

    fun setShowCl(v1: TextView, v2: LinearLayout){
        v1.setOnClickListener { show(v1, v2) }
    }

    fun setHideCl(v1: TextView, v2: LinearLayout){
        v1.setOnClickListener { hide(v1, v2) }
    }
}