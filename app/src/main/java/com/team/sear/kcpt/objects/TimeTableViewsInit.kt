package com.team.sear.kcpt.objects

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class TimeTableViewsInit{

    private lateinit var getTimeTable: StatusDetermination

   fun getTT(day: String, lesson: String,dayLn: LinearLayout, auth: FirebaseAuth, tv: TextView) {
        try {
            getTimeTable = StatusDetermination()
            getTimeTable.getTimeTable(day, lesson,  dayLn, auth, tv)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun invisView(v: View){
        v.visibility = View.GONE
    }
}