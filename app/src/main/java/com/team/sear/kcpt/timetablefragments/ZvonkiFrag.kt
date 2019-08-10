package com.team.sear.kcpt.timetablefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.team.sear.kcpt.R

class ZvonkiFrag : Fragment() {

    private lateinit var v: View
    private lateinit var mnFrTvFrag: TextView
    private lateinit var stTvFrag: TextView
    internal lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.zvonki_fragment, container, false)
        mnFrTvFrag = v.findViewById(R.id.mn_fr_tv_frag)
        stTvFrag = v.findViewById(R.id.st_tv_frag)
        getZvonkiTimetable("mn_fr_zvonki", mnFrTvFrag)
        getZvonkiTimetable("st_zvonki", stTvFrag)
        return v
    }

    private fun getZvonkiTimetable(day: String, tv: TextView) {
        try {
            database = FirebaseDatabase.getInstance()
            ref = database.getReference("timetable").child("zvonki").child(day)
            ref.addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val raspStr = dataSnapshot.getValue(String::class.java)
                            tv.text = raspStr
                        }

                        override fun onCancelled(error: DatabaseError) {
                            val raspStr = "Нет подключения к интернету или ошибка загрузки!"
                            tv.text = raspStr
                        }
                    })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

