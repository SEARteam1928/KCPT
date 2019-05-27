@file:Suppress("DEPRECATION")

package com.team.sear.kcpt.timetablefragments

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.*
import com.team.sear.kcpt.R
import com.team.sear.kcpt.objects.StatusDetermination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.timetable_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class TimeTableFragment : Fragment() {

    internal lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: AuthStateListener? = null
    private lateinit var getTimeTable: StatusDetermination

    var binding: com.team.sear.kcpt.databinding.TimetableFragmentBinding? = null



    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.timetable_fragment, container, false)
        getTimeTable  = StatusDetermination()

        try {
            mAuth = getInstance()
            mAuthListener = AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                    setInvisibleAll()
                } else {
                }
            }
            database = FirebaseDatabase.getInstance()
            user = mAuth.currentUser
            myRef = database.getReference("users").child(user!!.uid).child("signedIn")
            val df = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z ")
            val date = df.format(Calendar.getInstance().time)
            myRef.setValue(date + "SIGNEDIN")
            try {
                setTimeTable()
            } catch (e: Exception) {
                Toast.makeText(context, "Обновите или выберите расписание", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

        Handler().postDelayed({ showHideFromDate() }, 100)

        cl()
        return binding!!.root
    }

    private fun getTT(day: String, lesson: String, dayTv: TextView, dayLn: LinearLayout) {
        try {
            getTimeTable.getTimeTable(day, lesson, dayTv, dayLn, mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTimeTable() {
        try {
            getTT("mn", "mn1", binding!!.mn1tv, binding!!.lnmn1tv)
            getTT("mn", "mn2", binding!!.mn2tv, binding!!.lnmn2tv)
            getTT("mn", "mn3", binding!!.mn3tv, binding!!.lnmn3tv)
            getTT("mn", "mn4", binding!!.mn4tv, binding!!.lnmn4tv)
            getTT("mn", "mn5", binding!!.mn5tv, binding!!.lnmn5tv)
            getTT("mn", "mn6", binding!!.mn6tv, binding!!.lnmn6tv)
            getTT("mn", "mn7", binding!!.mn7tv, binding!!.lnmn7tv)
            getTT("mn", "mn8", binding!!.mn8tv, binding!!.lnmn8tv)
            getTT("mn", "mn9", binding!!.mn9tv, binding!!.lnmn9tv)
            getTT("mn", "mn10", binding!!.mn10tv, binding!!.lnmn10tv)
            getTT("mn", "mn11", binding!!.mn11tv, binding!!.lnmn11tv)
            getTT("mn", "mn12", binding!!.mn12tv, binding!!.lnmn12tv)
            getTT("ty", "ty1", binding!!.ty1tv, binding!!.lnty1tv)
            getTT("ty", "ty2", binding!!.ty2tv, binding!!.lnty2tv)
            getTT("ty", "ty3", binding!!.ty3tv, binding!!.lnty3tv)
            getTT("ty", "ty4", binding!!.ty4tv, binding!!.lnty4tv)
            getTT("ty", "ty5", binding!!.ty5tv, binding!!.lnty5tv)
            getTT("ty", "ty6", binding!!.ty6tv, binding!!.lnty6tv)
            getTT("ty", "ty7", binding!!.ty7tv, binding!!.lnty7tv)
            getTT("ty", "ty8", binding!!.ty8tv, binding!!.lnty8tv)
            getTT("ty", "ty9", binding!!.ty9tv, binding!!.lnty9tv)
            getTT("ty", "ty10", binding!!.ty10tv, binding!!.lnty10tv)
            getTT("ty", "ty11", binding!!.ty11tv, binding!!.lnty11tv)
            getTT("ty", "ty12", binding!!.ty12tv, binding!!.lnty12tv)
            getTT("wd", "wd1", binding!!.wd1tv, binding!!.lnwd1tv)
            getTT("wd", "wd2", binding!!.wd2tv, binding!!.lnwd2tv)
            getTT("wd", "wd3", binding!!.wd3tv, binding!!.lnwd3tv)
            getTT("wd", "wd4", binding!!.wd4tv, binding!!.lnwd4tv)
            getTT("wd", "wd5", binding!!.wd5tv, binding!!.lnwd5tv)
            getTT("wd", "wd6", binding!!.wd6tv, binding!!.lnwd6tv)
            getTT("wd", "wd7", binding!!.wd7tv, binding!!.lnwd7tv)
            getTT("wd", "wd8", binding!!.wd8tv, binding!!.lnwd8tv)
            getTT("wd", "wd9", binding!!.wd9tv, binding!!.lnwd9tv)
            getTT("wd", "wd10", binding!!.wd10tv, binding!!.lnwd10tv)
            getTT("wd", "wd11", binding!!.wd11tv, binding!!.lnwd11tv)
            getTT("wd", "wd12", binding!!.wd12tv, binding!!.lnwd12tv)
            getTT("th", "th1", binding!!.th1tv, binding!!.lnth1tv)
            getTT("th", "th2", binding!!.th2tv, binding!!.lnth2tv)
            getTT("th", "th3", binding!!.th3tv, binding!!.lnth3tv)
            getTT("th", "th4", binding!!.th4tv, binding!!.lnth4tv)
            getTT("th", "th5", binding!!.th5tv, binding!!.lnth5tv)
            getTT("th", "th6", binding!!.th6tv, binding!!.lnth6tv)
            getTT("th", "th7", binding!!.th7tv, binding!!.lnth7tv)
            getTT("th", "th8", binding!!.th8tv, binding!!.lnth8tv)
            getTT("th", "th9", binding!!.th9tv, binding!!.lnth9tv)
            getTT("th", "th10", binding!!.th10tv, binding!!.lnth10tv)
            getTT("th", "th11", binding!!.th11tv, binding!!.lnth11tv)
            getTT("th", "th12", binding!!.th12tv, binding!!.lnth12tv)
            getTT("fr", "fr1", binding!!.fr1tv, binding!!.lnfr1tv)
            getTT("fr", "fr2", binding!!.fr2tv, binding!!.lnfr2tv)
            getTT("fr", "fr3", binding!!.fr3tv, binding!!.lnfr3tv)
            getTT("fr", "fr4", binding!!.fr4tv, binding!!.lnfr4tv)
            getTT("fr", "fr5", binding!!.fr5tv, binding!!.lnfr5tv)
            getTT("fr", "fr6", binding!!.fr6tv, binding!!.lnfr6tv)
            getTT("fr", "fr7", binding!!.fr7tv, binding!!.lnfr7tv)
            getTT("fr", "fr8", binding!!.fr8tv, binding!!.lnfr8tv)
            getTT("fr", "fr9", binding!!.fr9tv, binding!!.lnfr9tv)
            getTT("fr", "fr10", binding!!.fr10tv, binding!!.lnfr10tv)
            getTT("fr", "fr11", binding!!.fr11tv, binding!!.lnfr11tv)
            getTT("fr", "fr12", binding!!.fr12tv, binding!!.lnfr12tv)
            getTT("st", "st1", binding!!.st1tv, binding!!.lnst1tv)
            getTT("st", "st2", binding!!.st2tv, binding!!.lnst2tv)
            getTT("st", "st3", binding!!.st3tv, binding!!.lnst3tv)
            getTT("st", "st4", binding!!.st4tv, binding!!.lnst4tv)
            getTT("st", "st5", binding!!.st5tv, binding!!.lnst5tv)
            getTT("st", "st6", binding!!.st6tv, binding!!.lnst6tv)
            getTT("st", "st7", binding!!.st7tv, binding!!.lnst7tv)
            getTT("st", "st8", binding!!.st8tv, binding!!.lnst8tv)
            getTT("st", "st9", binding!!.st9tv, binding!!.lnst9tv)
            getTT("st", "st10", binding!!.st10tv, binding!!.lnst10tv)
            getTT("st", "st11", binding!!.st11tv, binding!!.lnst11tv)
            getTT("st", "st12", binding!!.st12tv, binding!!.lnst12tv)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showHideFromDate() {
        val dform = SimpleDateFormat("EEE")
        when (dform.format(Calendar.getInstance().time)) {
            "вс" -> show(binding!!.mnTvShowHide, binding!!.mnShowHide)
            "Sun" -> show(binding!!.mnTvShowHide, binding!!.mnShowHide)
            "пн" -> show(binding!!.mnTvShowHide, binding!!.mnShowHide)
            "Mon" -> show(binding!!.mnTvShowHide, binding!!.mnShowHide)
            "вт" -> show(binding!!.tyTvShowHide, binding!!.tyShowHide)
            "Tues" -> show(binding!!.tyTvShowHide, binding!!.tyShowHide)
            "ср" -> show(binding!!.wdTvShowHide, binding!!.wdShowHide)
            "Wed" -> show(binding!!.wdTvShowHide, binding!!.wdShowHide)
            "чт" -> show(binding!!.thTvShowHide, binding!!.thShowHide)
            "Thurs" -> show(binding!!.thTvShowHide, binding!!.thShowHide)
            "пт" -> show(binding!!.frTvShowHide, binding!!.frShowHide)
            "Fri" -> show(binding!!.frTvShowHide, binding!!.frShowHide)
            "сб" -> show(binding!!.stTvShowHide, binding!!.stShowHide)
            "Sat" -> show(binding!!.stTvShowHide, binding!!.stShowHide)
            else -> {
            }
        }
    }


    private fun show(tv: TextView, ln: LinearLayout) {
        tv.visibility = View.GONE
        ln.visibility = View.VISIBLE
    }

    private fun hide(tv: TextView, ln: LinearLayout) {
        tv.visibility = View.VISIBLE
        ln.visibility = View.GONE
    }

    private fun cl() {
        try {
            binding!!.mnShowHide.setOnClickListener { show(binding!!.mnTvShowHide, binding!!.mnShowHide) }
            binding!!.tyShowHide.setOnClickListener { show(binding!!.tyTvShowHide, binding!!.tyShowHide) }
            binding!!.wdShowHide.setOnClickListener { show(binding!!.wdTvShowHide, binding!!.wdShowHide) }
            binding!!.thShowHide.setOnClickListener { show(binding!!.thTvShowHide, binding!!.thShowHide) }
            binding!!.frShowHide.setOnClickListener { show(binding!!.frTvShowHide, binding!!.frShowHide) }
            binding!!.stShowHide.setOnClickListener { show(binding!!.stTvShowHide, binding!!.stShowHide) }
            binding!!.mnTv.setOnClickListener { hide(binding!!.mnTvShowHide, binding!!.mnShowHide) }
            binding!!.tyTv.setOnClickListener { hide(binding!!.tyTvShowHide, binding!!.tyShowHide) }
            binding!!.wdTv.setOnClickListener { hide(binding!!.wdTvShowHide, binding!!.wdShowHide) }
            binding!!.thTv.setOnClickListener { hide(binding!!.thTvShowHide, binding!!.thShowHide) }
            binding!!.frTv.setOnClickListener { hide(binding!!.frTvShowHide, binding!!.frShowHide) }
            binding!!.stTv.setOnClickListener { hide(binding!!.stTvShowHide, binding!!.stShowHide) }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun setInvisibleAll() {
        binding!!.mnShowHide.visibility = View.GONE
        binding!!.tyShowHide.visibility = View.GONE
        binding!!.wdShowHide.visibility = View.GONE
        binding!!.thShowHide.visibility = View.GONE
        binding!!.frShowHide.visibility = View.GONE
        binding!!.stShowHide.visibility = View.GONE
        binding!!.lnmn1tv.visibility = View.GONE
        binding!!.lnmn2tv.visibility = View.GONE
        binding!!.lnmn3tv.visibility = View.GONE
        binding!!.lnmn4tv.visibility = View.GONE
        binding!!.lnmn5tv.visibility = View.GONE
        binding!!.lnmn6tv.visibility = View.GONE
        binding!!.lnmn7tv.visibility = View.GONE
        binding!!.lnmn8tv.visibility = View.GONE
        binding!!.lnmn9tv.visibility = View.GONE
        binding!!.lnmn10tv.visibility = View.GONE
        binding!!.lnmn11tv.visibility = View.GONE
        binding!!.lnmn12tv.visibility = View.GONE
        binding!!.lnty1tv.visibility = View.GONE
        binding!!.lnty2tv.visibility = View.GONE
        binding!!.lnty3tv.visibility = View.GONE
        binding!!.lnty4tv.visibility = View.GONE
        binding!!.lnty5tv.visibility = View.GONE
        binding!!.lnty6tv.visibility = View.GONE
        binding!!.lnty7tv.visibility = View.GONE
        binding!!.lnty8tv.visibility = View.GONE
        binding!!.lnty9tv.visibility = View.GONE
        binding!!.lnty10tv.visibility = View.GONE
        binding!!.lnty11tv.visibility = View.GONE
        binding!!.lnty12tv.visibility = View.GONE
        binding!!.lnwd1tv.visibility = View.GONE
        binding!!.lnwd2tv.visibility = View.GONE
        binding!!.lnwd3tv.visibility = View.GONE
        binding!!.lnwd4tv.visibility = View.GONE
        binding!!.lnwd5tv.visibility = View.GONE
        binding!!.lnwd6tv.visibility = View.GONE
        binding!!.lnwd7tv.visibility = View.GONE
        binding!!.lnwd8tv.visibility = View.GONE
        binding!!.lnwd9tv.visibility = View.GONE
        binding!!.lnwd10tv.visibility = View.GONE
        binding!!.lnwd11tv.visibility = View.GONE
        binding!!.lnwd12tv.visibility = View.GONE
        binding!!.lnth1tv.visibility = View.GONE
        binding!!.lnth2tv.visibility = View.GONE
        binding!!.lnth3tv.visibility = View.GONE
        binding!!.lnth4tv.visibility = View.GONE
        binding!!.lnth5tv.visibility = View.GONE
        binding!!.lnth6tv.visibility = View.GONE
        binding!!.lnth7tv.visibility = View.GONE
        binding!!.lnth8tv.visibility = View.GONE
        binding!!.lnth9tv.visibility = View.GONE
        binding!!.lnth10tv.visibility = View.GONE
        binding!!.lnth11tv.visibility = View.GONE
        binding!!.lnth12tv.visibility = View.GONE
        binding!!.lnfr1tv.visibility = View.GONE
        binding!!.lnfr2tv.visibility = View.GONE
        binding!!.lnfr3tv.visibility = View.GONE
        binding!!.lnfr4tv.visibility = View.GONE
        binding!!.lnfr5tv.visibility = View.GONE
        binding!!.lnfr6tv.visibility = View.GONE
        binding!!.lnfr7tv.visibility = View.GONE
        binding!!.lnfr8tv.visibility = View.GONE
        binding!!.lnfr9tv.visibility = View.GONE
        binding!!.lnfr10tv.visibility = View.GONE
        binding!!.lnfr11tv.visibility = View.GONE
        binding!!.lnfr12tv.visibility = View.GONE
        binding!!.lnst1tv.visibility = View.GONE
        binding!!.lnst2tv.visibility = View.GONE
        binding!!.lnst3tv.visibility = View.GONE
        binding!!.lnst4tv.visibility = View.GONE
        binding!!.lnst5tv.visibility = View.GONE
        binding!!.lnst6tv.visibility = View.GONE
        binding!!.lnst7tv.visibility = View.GONE
        binding!!.lnst8tv.visibility = View.GONE
        binding!!.lnst9tv.visibility = View.GONE
        binding!!.lnst10tv.visibility = View.GONE
        binding!!.lnst11tv.visibility = View.GONE
        binding!!.lnst12tv.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener!!)
        }
    }
}
