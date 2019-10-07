package com.team.sear.kcpt


import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.databinding.FragmentNewTimeTableBinding
import com.team.sear.kcpt.timetablePackage.Lesson
import com.team.sear.kcpt.timetablePackage.TimeTableInOneDayDownloader
import com.team.sear.kcpt.timetablePackage.TimeTableInOneDayOnAllWeekDownloader
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("StaticFieldLeak, SimpleDateFormat")
class NewTimeTableFrag : Fragment() {

    internal lateinit var database: FirebaseDatabase
    internal var user: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var b: FragmentNewTimeTableBinding
    private var ref: DatabaseReference? = null

    private lateinit var lessonsMn: ArrayList<Lesson?>
    private lateinit var ttDownloaderMn: TimeTableInOneDayOnAllWeekDownloader

    private lateinit var lessonsTy: ArrayList<Lesson?>
    private lateinit var ttDownloaderTy: TimeTableInOneDayOnAllWeekDownloader

    private lateinit var lessonsWd: ArrayList<Lesson?>
    private lateinit var ttDownloaderWd: TimeTableInOneDayOnAllWeekDownloader

    private lateinit var lessonsTh: ArrayList<Lesson?>
    private lateinit var ttDownloaderTh: TimeTableInOneDayOnAllWeekDownloader

    private lateinit var lessonsFr: ArrayList<Lesson?>
    private lateinit var ttDownloaderFr: TimeTableInOneDayOnAllWeekDownloader

    private lateinit var lessonsSt: ArrayList<Lesson?>
    private lateinit var ttDownloaderSt: TimeTableInOneDayOnAllWeekDownloader


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_new_time_table, container, false)
        lessonsMn = ArrayList()
        lessonsTy = ArrayList()
        lessonsWd = ArrayList()
        lessonsTh = ArrayList()
        lessonsFr = ArrayList()
        lessonsSt = ArrayList()

        ttDownloaderMn = TimeTableInOneDayOnAllWeekDownloader()
        ttDownloaderTy = TimeTableInOneDayOnAllWeekDownloader()
        ttDownloaderWd = TimeTableInOneDayOnAllWeekDownloader()
        ttDownloaderTh = TimeTableInOneDayOnAllWeekDownloader()
        ttDownloaderFr = TimeTableInOneDayOnAllWeekDownloader()
        ttDownloaderSt = TimeTableInOneDayOnAllWeekDownloader()

        b.lessonRecyclerMn.setHasFixedSize(true)
        b.lessonRecyclerTy.setHasFixedSize(true)
        b.lessonRecyclerWd.setHasFixedSize(true)
        b.lessonRecyclerTh.setHasFixedSize(true)
        b.lessonRecyclerFr.setHasFixedSize(true)
        b.lessonRecyclerSt.setHasFixedSize(true)

        val lessonRecyclerMnLlm = LinearLayoutManager(context)
        val lessonRecyclerTyLlm = LinearLayoutManager(context)
        val lessonRecyclerWdLlm = LinearLayoutManager(context)
        val lessonRecyclerThLlm = LinearLayoutManager(context)
        val lessonRecyclerFrLlm = LinearLayoutManager(context)
        val lessonRecyclerStLlm = LinearLayoutManager(context)

        lessonRecyclerMnLlm.orientation = LinearLayoutManager.VERTICAL
        lessonRecyclerTyLlm.orientation = LinearLayoutManager.VERTICAL
        lessonRecyclerWdLlm.orientation = LinearLayoutManager.VERTICAL
        lessonRecyclerThLlm.orientation = LinearLayoutManager.VERTICAL
        lessonRecyclerFrLlm.orientation = LinearLayoutManager.VERTICAL
        lessonRecyclerStLlm.orientation = LinearLayoutManager.VERTICAL

        b.lessonRecyclerMn.layoutManager = lessonRecyclerMnLlm
        b.lessonRecyclerTy.layoutManager = lessonRecyclerTyLlm
        b.lessonRecyclerWd.layoutManager = lessonRecyclerWdLlm
        b.lessonRecyclerTh.layoutManager = lessonRecyclerThLlm
        b.lessonRecyclerFr.layoutManager = lessonRecyclerFrLlm
        b.lessonRecyclerSt.layoutManager = lessonRecyclerStLlm

        auth = FirebaseAuth.getInstance()
        authComplete()


        return b.root
    }
    private fun transact(fragment: Fragment) {
        val ftrans = activity!!.supportFragmentManager.beginTransaction()
        ftrans.replace(R.id.bottom_nav_container, fragment)
        ftrans.commit()
    }
    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            user = firebaseAuth.currentUser
            if (user != null) {
                user = firebaseAuth.currentUser

                ttDownloaderMn.enable(lessonsMn, "Понедельник", b.lessonRecyclerMn, auth, user!!)

                ttDownloaderTy.enable(lessonsTy, "Вторник", b.lessonRecyclerTy, auth, user!!)

                ttDownloaderWd.enable(lessonsWd, "Среда", b.lessonRecyclerWd, auth, user!!)

                ttDownloaderTh.enable(lessonsTh, "Четверг", b.lessonRecyclerTh, auth, user!!)

                ttDownloaderFr.enable(lessonsFr, "Пятница", b.lessonRecyclerFr, auth, user!!)

                ttDownloaderSt.enable(lessonsSt, "Суббота", b.lessonRecyclerSt, auth, user!!)

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
