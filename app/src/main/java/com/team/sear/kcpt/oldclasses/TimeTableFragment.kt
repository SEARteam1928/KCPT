@file:Suppress("DEPRECATION")

package com.team.sear.kcpt.oldclasses

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.sear.kcpt.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.team.sear.kcpt.objects.TimeTableViewsInit

class TimeTableFragment : Fragment() {

    internal lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: AuthStateListener? = null
    private lateinit var initTT: TimeTableViewsInit
    var b: com.team.sear.kcpt.databinding.TimetableFragmentBinding? = null



    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.timetable_fragment, container, false)
        initTT = TimeTableViewsInit()
/*
        try {
            cl()
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
*//*
                setTimeTable()
*//*
            } catch (e: Exception) {
                Toast.makeText(context, "Обновите или выберите расписание", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

        Handler().postDelayed({ showHideFromDate() }, 100)*/
        return b!!.root
    }
/*

    private fun setTimeTable() {
        try {
            initTT.getTT("mn", "mn1", b!!.mn1tv, b!!.lnmn1tv,mAuth)
            initTT.getTT("mn", "mn2", b!!.mn2tv, b!!.lnmn2tv,mAuth)
            initTT.getTT("mn", "mn3", b!!.mn3tv, b!!.lnmn3tv,mAuth)
            initTT.getTT("mn", "mn4", b!!.mn4tv, b!!.lnmn4tv,mAuth)
            initTT.getTT("mn", "mn5", b!!.mn5tv, b!!.lnmn5tv,mAuth)
            initTT.getTT("mn", "mn6", b!!.mn6tv, b!!.lnmn6tv,mAuth)
            initTT.getTT("mn", "mn7", b!!.mn7tv, b!!.lnmn7tv,mAuth)
            initTT.getTT("mn", "mn8", b!!.mn8tv, b!!.lnmn8tv,mAuth)
            initTT.getTT("mn", "mn9", b!!.mn9tv, b!!.lnmn9tv,mAuth)
            initTT.getTT("mn", "mn10", b!!.mn10tv, b!!.lnmn10tv,mAuth)
            initTT.getTT("mn", "mn11", b!!.mn11tv, b!!.lnmn11tv,mAuth)
            initTT.getTT("mn", "mn12", b!!.mn12tv, b!!.lnmn12tv,mAuth)
            initTT.getTT("ty", "ty1", b!!.ty1tv, b!!.lnty1tv,mAuth)
            initTT.getTT("ty", "ty2", b!!.ty2tv, b!!.lnty2tv,mAuth)
            initTT.getTT("ty", "ty3", b!!.ty3tv, b!!.lnty3tv,mAuth)
            initTT.getTT("ty", "ty4", b!!.ty4tv, b!!.lnty4tv,mAuth)
            initTT.getTT("ty", "ty5", b!!.ty5tv, b!!.lnty5tv,mAuth)
            initTT.getTT("ty", "ty6", b!!.ty6tv, b!!.lnty6tv,mAuth)
            initTT.getTT("ty", "ty7", b!!.ty7tv, b!!.lnty7tv,mAuth)
            initTT.getTT("ty", "ty8", b!!.ty8tv, b!!.lnty8tv,mAuth)
            initTT.getTT("ty", "ty9", b!!.ty9tv, b!!.lnty9tv,mAuth)
            initTT.getTT("ty", "ty10", b!!.ty10tv, b!!.lnty10tv,mAuth)
            initTT.getTT("ty", "ty11", b!!.ty11tv, b!!.lnty11tv,mAuth)
            initTT.getTT("ty", "ty12", b!!.ty12tv, b!!.lnty12tv,mAuth)
            initTT.getTT("wd", "wd1", b!!.wd1tv, b!!.lnwd1tv,mAuth)
            initTT.getTT("wd", "wd2", b!!.wd2tv, b!!.lnwd2tv,mAuth)
            initTT.getTT("wd", "wd3", b!!.wd3tv, b!!.lnwd3tv,mAuth)
            initTT.getTT("wd", "wd4", b!!.wd4tv, b!!.lnwd4tv,mAuth)
            initTT.getTT("wd", "wd5", b!!.wd5tv, b!!.lnwd5tv,mAuth)
            initTT.getTT("wd", "wd6", b!!.wd6tv, b!!.lnwd6tv,mAuth)
            initTT.getTT("wd", "wd7", b!!.wd7tv, b!!.lnwd7tv,mAuth)
            initTT.getTT("wd", "wd8", b!!.wd8tv, b!!.lnwd8tv,mAuth)
            initTT.getTT("wd", "wd9", b!!.wd9tv, b!!.lnwd9tv,mAuth)
            initTT.getTT("wd", "wd10", b!!.wd10tv, b!!.lnwd10tv,mAuth)
            initTT.getTT("wd", "wd11", b!!.wd11tv, b!!.lnwd11tv,mAuth)
            initTT.getTT("wd", "wd12", b!!.wd12tv, b!!.lnwd12tv,mAuth)
            initTT.getTT("th", "th1", b!!.th1tv, b!!.lnth1tv,mAuth)
            initTT.getTT("th", "th2", b!!.th2tv, b!!.lnth2tv,mAuth)
            initTT.getTT("th", "th3", b!!.th3tv, b!!.lnth3tv,mAuth)
            initTT.getTT("th", "th4", b!!.th4tv, b!!.lnth4tv,mAuth)
            initTT.getTT("th", "th5", b!!.th5tv, b!!.lnth5tv,mAuth)
            initTT.getTT("th", "th6", b!!.th6tv, b!!.lnth6tv,mAuth)
            initTT.getTT("th", "th7", b!!.th7tv, b!!.lnth7tv,mAuth)
            initTT.getTT("th", "th8", b!!.th8tv, b!!.lnth8tv,mAuth)
            initTT.getTT("th", "th9", b!!.th9tv, b!!.lnth9tv,mAuth)
            initTT.getTT("th", "th10", b!!.th10tv, b!!.lnth10tv,mAuth)
            initTT.getTT("th", "th11", b!!.th11tv, b!!.lnth11tv,mAuth)
            initTT.getTT("th", "th12", b!!.th12tv, b!!.lnth12tv,mAuth)
            initTT.getTT("fr", "fr1", b!!.fr1tv, b!!.lnfr1tv,mAuth)
            initTT.getTT("fr", "fr2", b!!.fr2tv, b!!.lnfr2tv,mAuth)
            initTT.getTT("fr", "fr3", b!!.fr3tv, b!!.lnfr3tv,mAuth)
            initTT.getTT("fr", "fr4", b!!.fr4tv, b!!.lnfr4tv,mAuth)
            initTT.getTT("fr", "fr5", b!!.fr5tv, b!!.lnfr5tv,mAuth)
            initTT.getTT("fr", "fr6", b!!.fr6tv, b!!.lnfr6tv,mAuth)
            initTT.getTT("fr", "fr7", b!!.fr7tv, b!!.lnfr7tv,mAuth)
            initTT.getTT("fr", "fr8", b!!.fr8tv, b!!.lnfr8tv,mAuth)
            initTT.getTT("fr", "fr9", b!!.fr9tv, b!!.lnfr9tv,mAuth)
            initTT.getTT("fr", "fr10", b!!.fr10tv, b!!.lnfr10tv,mAuth)
            initTT.getTT("fr", "fr11", b!!.fr11tv, b!!.lnfr11tv,mAuth)
            initTT.getTT("fr", "fr12", b!!.fr12tv, b!!.lnfr12tv,mAuth)
            initTT.getTT("st", "st1", b!!.st1tv, b!!.lnst1tv,mAuth)
            initTT.getTT("st", "st2", b!!.st2tv, b!!.lnst2tv,mAuth)
            initTT.getTT("st", "st3", b!!.st3tv, b!!.lnst3tv,mAuth)
            initTT.getTT("st", "st4", b!!.st4tv, b!!.lnst4tv,mAuth)
            initTT.getTT("st", "st5", b!!.st5tv, b!!.lnst5tv,mAuth)
            initTT.getTT("st", "st6", b!!.st6tv, b!!.lnst6tv,mAuth)
            initTT.getTT("st", "st7", b!!.st7tv, b!!.lnst7tv,mAuth)
            initTT.getTT("st", "st8", b!!.st8tv, b!!.lnst8tv,mAuth)
            initTT.getTT("st", "st9", b!!.st9tv, b!!.lnst9tv,mAuth)
            initTT.getTT("st", "st10", b!!.st10tv, b!!.lnst10tv,mAuth)
            initTT.getTT("st", "st11", b!!.st11tv, b!!.lnst11tv,mAuth)
            initTT.getTT("st", "st12", b!!.st12tv, b!!.lnst12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }
*/
/*
    @SuppressLint("SimpleDateFormat")
    private fun showHideFromDate() {
        val dform = SimpleDateFormat("EEE")
        when (dform.format(Calendar.getInstance().time)) {*/
/*            "вс" -> initTT.show(b!!.mnTvShowHide, b!!.mnShowHide)
            "Sun" -> initTT.show(b!!.mnTvShowHide, b!!.mnShowHide)
            "пн" -> initTT.show(b!!.mnTvShowHide, b!!.mnShowHide)
            "Mon" -> initTT.show(b!!.mnTvShowHide, b!!.mnShowHide)
            "вт" -> initTT.show(b!!.tyTvShowHide, b!!.tyShowHide)
            "Tues" -> initTT.show(b!!.tyTvShowHide, b!!.tyShowHide)
            "ср" -> initTT.show(b!!.wdTvShowHide, b!!.wdShowHide)
            "Wed" -> initTT.show(b!!.wdTvShowHide, b!!.wdShowHide)
            "чт" -> initTT.show(b!!.thTvShowHide, b!!.thShowHide)
            "Thurs" -> initTT.show(b!!.thTvShowHide, b!!.thShowHide)
            "пт" -> initTT.show(b!!.frTvShowHide, b!!.frShowHide)
            "Fri" -> initTT.show(b!!.frTvShowHide, b!!.frShowHide)
            "сб" -> initTT.show(b!!.stTvShowHide, b!!.stShowHide)
            "Sat" -> initTT.show(b!!.stTvShowHide, b!!.stShowHide)*/
/*            else -> {
            }
        }
    }


    private fun cl() {
        try {
            initTT.setShowCl(b!!.mnTvShowHide, b!!.mnShowHide)
            initTT.setShowCl(b!!.tyTvShowHide, b!!.tyShowHide)
            initTT.setShowCl(b!!.wdTvShowHide, b!!.wdShowHide)
            initTT.setShowCl(b!!.thTvShowHide, b!!.thShowHide)
            initTT.setShowCl(b!!.frTvShowHide, b!!.frShowHide)
            initTT.setShowCl(b!!.stTvShowHide, b!!.stShowHide)
            initTT.setHideCl(b!!.mnTv, b!!.mnShowHide)
            initTT.setHideCl(b!!.tyTv, b!!.tyShowHide)
            initTT.setHideCl(b!!.wdTv, b!!.wdShowHide)
            initTT.setHideCl(b!!.thTv, b!!.thShowHide)
            initTT.setHideCl(b!!.frTv, b!!.frShowHide)
            initTT.setHideCl(b!!.stTv, b!!.stShowHide)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun setInvisibleAll() {
        initTT.invisView(b!!.mnShowHide)
        initTT.invisView(b!!.tyShowHide)
        initTT.invisView(b!!.wdShowHide)
        initTT.invisView(b!!.thShowHide)
        initTT.invisView(b!!.frShowHide)
        initTT.invisView(b!!.stShowHide)
        initTT.invisView(b!!.lnmn1tv)
        initTT.invisView(b!!.lnmn2tv)
        initTT.invisView(b!!.lnmn3tv)
        initTT.invisView(b!!.lnmn4tv)
        initTT.invisView(b!!.lnmn5tv)
        initTT.invisView(b!!.lnmn6tv)
        initTT.invisView(b!!.lnmn7tv)
        initTT.invisView(b!!.lnmn8tv)
        initTT.invisView(b!!.lnmn9tv)
        initTT.invisView(b!!.lnmn10tv)
        initTT.invisView(b!!.lnmn11tv)
        initTT.invisView(b!!.lnmn12tv)
        initTT.invisView(b!!.lnty1tv)
        initTT.invisView(b!!.lnty2tv)
        initTT.invisView(b!!.lnty3tv)
        initTT.invisView(b!!.lnty4tv)
        initTT.invisView(b!!.lnty5tv)
        initTT.invisView(b!!.lnty6tv)
        initTT.invisView(b!!.lnty7tv)
        initTT.invisView(b!!.lnty8tv)
        initTT.invisView(b!!.lnty9tv)
        initTT.invisView(b!!.lnty10tv)
        initTT.invisView(b!!.lnty11tv)
        initTT.invisView(b!!.lnty12tv)
        initTT.invisView(b!!.lnwd1tv)
        initTT.invisView(b!!.lnwd2tv)
        initTT.invisView(b!!.lnwd3tv)
        initTT.invisView(b!!.lnwd4tv)
        initTT.invisView(b!!.lnwd5tv)
        initTT.invisView(b!!.lnwd6tv)
        initTT.invisView(b!!.lnwd7tv)
        initTT.invisView(b!!.lnwd8tv)
        initTT.invisView(b!!.lnwd9tv)
        initTT.invisView(b!!.lnwd10tv)
        initTT.invisView(b!!.lnwd11tv)
        initTT.invisView(b!!.lnwd12tv)
        initTT.invisView(b!!.lnth1tv)
        initTT.invisView(b!!.lnth2tv)
        initTT.invisView(b!!.lnth3tv)
        initTT.invisView(b!!.lnth4tv)
        initTT.invisView(b!!.lnth5tv)
        initTT.invisView(b!!.lnth6tv)
        initTT.invisView(b!!.lnth7tv)
        initTT.invisView(b!!.lnth8tv)
        initTT.invisView(b!!.lnth9tv)
        initTT.invisView(b!!.lnth10tv)
        initTT.invisView(b!!.lnth11tv)
        initTT.invisView(b!!.lnth12tv)
        initTT.invisView(b!!.lnfr1tv)
        initTT.invisView(b!!.lnfr2tv)
        initTT.invisView(b!!.lnfr3tv)
        initTT.invisView(b!!.lnfr4tv)
        initTT.invisView(b!!.lnfr5tv)
        initTT.invisView(b!!.lnfr6tv)
        initTT.invisView(b!!.lnfr7tv)
        initTT.invisView(b!!.lnfr8tv)
        initTT.invisView(b!!.lnfr9tv)
        initTT.invisView(b!!.lnfr10tv)
        initTT.invisView(b!!.lnfr11tv)
        initTT.invisView(b!!.lnfr12tv)
        initTT.invisView(b!!.lnst1tv)
        initTT.invisView(b!!.lnst2tv)
        initTT.invisView(b!!.lnst3tv)
        initTT.invisView(b!!.lnst4tv)
        initTT.invisView(b!!.lnst5tv)
        initTT.invisView(b!!.lnst6tv)
        initTT.invisView(b!!.lnst7tv)
        initTT.invisView(b!!.lnst8tv)
        initTT.invisView(b!!.lnst9tv)
        initTT.invisView(b!!.lnst10tv)
        initTT.invisView(b!!.lnst11tv)
        initTT.invisView(b!!.lnst12tv)
    }*/

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
