package com.team.sear.kcpt


import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.databinding.FragmentNewTimeTableBinding
import com.team.sear.kcpt.timetablePackage.Lesson
import com.team.sear.kcpt.timetablePackage.TimeTableInOneDayDownloader
import com.team.sear.kcpt.timetablePackage.TimeTableInOneDayOnAllWeekDownloader
import java.util.*

@SuppressLint("StaticFieldLeak, SimpleDateFormat")
class NewTimeTableFrag : Fragment() {

    internal lateinit var database: FirebaseDatabase
    internal var user: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var b: FragmentNewTimeTableBinding
    private var ref: DatabaseReference? = null

    private lateinit var lessons: ArrayList<Lesson?>
    private lateinit var ttDownloader: TimeTableInOneDayOnAllWeekDownloader

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_new_time_table, container, false)
        try {
            lessons = ArrayList()

            auth = FirebaseAuth.getInstance()
            ttDownloader = TimeTableInOneDayOnAllWeekDownloader()

            val lessonRecyclerLlm = LinearLayoutManager(context)

            lessonRecyclerLlm.orientation = LinearLayoutManager.VERTICAL

            b.lessonRecyclerAllWeek.layoutManager = lessonRecyclerLlm


            authComplete()
        } catch (e: Exception) {
        }
        return b.root
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            user = firebaseAuth.currentUser
            if (user != null) {
                user = firebaseAuth.currentUser

                ttDownloader.enable(lessons, "AllWeek", b.lessonRecyclerAllWeek, b.textnodataallweek, auth, user!!)
                Handler().postDelayed({ ttDownloader.enable(lessons, "AllWeek", b.lessonRecyclerAllWeek, b.textnodataallweek, auth, user!!) }, 500)
            } else {
                Toast.makeText(activity, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth.removeAuthStateListener(authListener!!)
        }
    }
}
