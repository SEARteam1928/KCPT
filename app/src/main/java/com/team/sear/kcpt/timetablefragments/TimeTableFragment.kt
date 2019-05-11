@file:Suppress("DEPRECATION")

package com.team.sear.kcpt.timetablefragments

import android.annotation.SuppressLint
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
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.team.sear.kcpt.R
import com.team.sear.kcpt.objects.StatusDetermination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class TimeTableFragment : Fragment(), View.OnClickListener {

    private lateinit var v: View
    internal lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var getTimeTable: StatusDetermination
    private lateinit var mnSHtv: TextView
    private lateinit var tySHtv: TextView
    private lateinit var wdSHtv: TextView
    private lateinit var thSHtv: TextView
    private lateinit var frSHtv: TextView
    private lateinit var stSHtv: TextView
    private lateinit var mnSHln: LinearLayout
    private lateinit var tySHln: LinearLayout
    private lateinit var wdSHln: LinearLayout
    private lateinit var thSHln: LinearLayout
    private lateinit var frSHln: LinearLayout
    private lateinit var stSHln: LinearLayout
    private lateinit var mnTv: TextView
    private lateinit var mn1tv: TextView
    private lateinit var mn2tv: TextView
    private lateinit var mn3tv: TextView
    private lateinit var mn4tv: TextView
    private lateinit var mn5tv: TextView
    private lateinit var mn6tv: TextView
    private lateinit var mn7tv: TextView
    private lateinit var mn8tv: TextView
    private lateinit var mn9tv: TextView
    private lateinit var mn10tv: TextView
    private lateinit var mn11tv: TextView
    private lateinit var mn12tv: TextView
    private lateinit var tyTv: TextView
    private lateinit var ty1tv: TextView
    private lateinit var ty2tv: TextView
    private lateinit var ty3tv: TextView
    private lateinit var ty4tv: TextView
    private lateinit var ty5tv: TextView
    private lateinit var ty6tv: TextView
    private lateinit var ty7tv: TextView
    private lateinit var ty8tv: TextView
    private lateinit var ty9tv: TextView
    private lateinit var ty10tv: TextView
    private lateinit var ty11tv: TextView
    private lateinit var ty12tv: TextView
    private lateinit var wdTv: TextView
    private lateinit var wd1tv: TextView
    private lateinit var wd2tv: TextView
    private lateinit var wd3tv: TextView
    private lateinit var wd4tv: TextView
    private lateinit var wd5tv: TextView
    private lateinit var wd6tv: TextView
    private lateinit var wd7tv: TextView
    private lateinit var wd8tv: TextView
    private lateinit var wd9tv: TextView
    private lateinit var wd10tv: TextView
    private lateinit var wd11tv: TextView
    private lateinit var wd12tv: TextView
    private lateinit var thTv: TextView
    private lateinit var th1tv: TextView
    private lateinit var th2tv: TextView
    private lateinit var th3tv: TextView
    private lateinit var th4tv: TextView
    private lateinit var th5tv: TextView
    private lateinit var th6tv: TextView
    private lateinit var th7tv: TextView
    private lateinit var th8tv: TextView
    private lateinit var th9tv: TextView
    private lateinit var th10tv: TextView
    private lateinit var th11tv: TextView
    private lateinit var th12tv: TextView
    private lateinit var frTv: TextView
    private lateinit var fr1tv: TextView
    private lateinit var fr2tv: TextView
    private lateinit var fr3tv: TextView
    private lateinit var fr4tv: TextView
    private lateinit var fr5tv: TextView
    private lateinit var fr6tv: TextView
    private lateinit var fr7tv: TextView
    private lateinit var fr8tv: TextView
    private lateinit var fr9tv: TextView
    private lateinit var fr10tv: TextView
    private lateinit var fr11tv: TextView
    private lateinit var fr12tv: TextView
    private lateinit var stTv: TextView
    private lateinit var st1tv: TextView
    private lateinit var st2tv: TextView
    private lateinit var st3tv: TextView
    private lateinit var st4tv: TextView
    private lateinit var st5tv: TextView
    private lateinit var st6tv: TextView
    private lateinit var st7tv: TextView
    private lateinit var st8tv: TextView
    private lateinit var st9tv: TextView
    private lateinit var st10tv: TextView
    private lateinit var st11tv: TextView
    private lateinit var st12tv: TextView
    private lateinit var lnmn1tv: LinearLayout
    private lateinit var lnmn2tv: LinearLayout
    private lateinit var lnmn3tv: LinearLayout
    private lateinit var lnmn4tv: LinearLayout
    private lateinit var lnmn5tv: LinearLayout
    private lateinit var lnmn6tv: LinearLayout
    private lateinit var lnmn7tv: LinearLayout
    private lateinit var lnmn8tv: LinearLayout
    private lateinit var lnmn9tv: LinearLayout
    private lateinit var lnmn10tv: LinearLayout
    private lateinit var lnmn11tv: LinearLayout
    private lateinit var lnmn12tv: LinearLayout
    private lateinit var lnty1tv: LinearLayout
    private lateinit var lnty2tv: LinearLayout
    private lateinit var lnty3tv: LinearLayout
    private lateinit var lnty4tv: LinearLayout
    private lateinit var lnty5tv: LinearLayout
    private lateinit var lnty6tv: LinearLayout
    private lateinit var lnty7tv: LinearLayout
    private lateinit var lnty8tv: LinearLayout
    private lateinit var lnty9tv: LinearLayout
    private lateinit var lnty10tv: LinearLayout
    private lateinit var lnty11tv: LinearLayout
    private lateinit var lnty12tv: LinearLayout
    private lateinit var lnwd1tv: LinearLayout
    private lateinit var lnwd2tv: LinearLayout
    private lateinit var lnwd3tv: LinearLayout
    private lateinit var lnwd4tv: LinearLayout
    private lateinit var lnwd5tv: LinearLayout
    private lateinit var lnwd6tv: LinearLayout
    private lateinit var lnwd7tv: LinearLayout
    private lateinit var lnwd8tv: LinearLayout
    private lateinit var lnwd9tv: LinearLayout
    private lateinit var lnwd10tv: LinearLayout
    private lateinit var lnwd11tv: LinearLayout
    private lateinit var lnwd12tv: LinearLayout
    private lateinit var lnth1tv: LinearLayout
    private lateinit var lnth2tv: LinearLayout
    private lateinit var lnth3tv: LinearLayout
    private lateinit var lnth4tv: LinearLayout
    private lateinit var lnth5tv: LinearLayout
    private lateinit var lnth6tv: LinearLayout
    private lateinit var lnth7tv: LinearLayout
    private lateinit var lnth8tv: LinearLayout
    private lateinit var lnth9tv: LinearLayout
    private lateinit var lnth10tv: LinearLayout
    private lateinit var lnth11tv: LinearLayout
    private lateinit var lnth12tv: LinearLayout
    private lateinit var lnfr1tv: LinearLayout
    private lateinit var lnfr2tv: LinearLayout
    private lateinit var lnfr3tv: LinearLayout
    private lateinit var lnfr4tv: LinearLayout
    private lateinit var lnfr5tv: LinearLayout
    private lateinit var lnfr6tv: LinearLayout
    private lateinit var lnfr7tv: LinearLayout
    private lateinit var lnfr8tv: LinearLayout
    private lateinit var lnfr9tv: LinearLayout
    private lateinit var lnfr10tv: LinearLayout
    private lateinit var lnfr11tv: LinearLayout
    private lateinit var lnfr12tv: LinearLayout
    private lateinit var lnst1tv: LinearLayout
    private lateinit var lnst2tv: LinearLayout
    private lateinit var lnst3tv: LinearLayout
    private lateinit var lnst4tv: LinearLayout
    private lateinit var lnst5tv: LinearLayout
    private lateinit var lnst6tv: LinearLayout
    private lateinit var lnst7tv: LinearLayout
    private lateinit var lnst8tv: LinearLayout
    private lateinit var lnst9tv: LinearLayout
    private lateinit var lnst10tv: LinearLayout
    private lateinit var lnst11tv: LinearLayout
    private lateinit var lnst12tv: LinearLayout

    private lateinit var mAdView: AdView


    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.timetable_fragment, container, false)
        findView()

        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-4313901539584225/9098143829"

        mAdView = v.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


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
        }

        Handler().postDelayed({ showHideFromDate() }, 300)


        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }


        return v
    }

    private fun getTT(day: String, lesson: String, dayTv: TextView, dayLn: LinearLayout) {
        try {
            getTimeTable.getTimeTable(day, lesson, dayTv, dayLn, mAuth)
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTimeTable() {
        try {
            getTT("mn", "mn1", mn1tv, lnmn1tv)
            getTT("mn", "mn2", mn2tv, lnmn2tv)
            getTT("mn", "mn3", mn3tv, lnmn3tv)
            getTT("mn", "mn4", mn4tv, lnmn4tv)
            getTT("mn", "mn5", mn5tv, lnmn5tv)
            getTT("mn", "mn6", mn6tv, lnmn6tv)
            getTT("mn", "mn7", mn7tv, lnmn7tv)
            getTT("mn", "mn8", mn8tv, lnmn8tv)
            getTT("mn", "mn9", mn9tv, lnmn9tv)
            getTT("mn", "mn10", mn10tv, lnmn10tv)
            getTT("mn", "mn11", mn11tv, lnmn11tv)
            getTT("mn", "mn12", mn12tv, lnmn12tv)
            getTT("ty", "ty1", ty1tv, lnty1tv)
            getTT("ty", "ty2", ty2tv, lnty2tv)
            getTT("ty", "ty3", ty3tv, lnty3tv)
            getTT("ty", "ty4", ty4tv, lnty4tv)
            getTT("ty", "ty5", ty5tv, lnty5tv)
            getTT("ty", "ty6", ty6tv, lnty6tv)
            getTT("ty", "ty7", ty7tv, lnty7tv)
            getTT("ty", "ty8", ty8tv, lnty8tv)
            getTT("ty", "ty9", ty9tv, lnty9tv)
            getTT("ty", "ty10", ty10tv, lnty10tv)
            getTT("ty", "ty11", ty11tv, lnty11tv)
            getTT("ty", "ty12", ty12tv, lnty12tv)
            getTT("wd", "wd1", wd1tv, lnwd1tv)
            getTT("wd", "wd2", wd2tv, lnwd2tv)
            getTT("wd", "wd3", wd3tv, lnwd3tv)
            getTT("wd", "wd4", wd4tv, lnwd4tv)
            getTT("wd", "wd5", wd5tv, lnwd5tv)
            getTT("wd", "wd6", wd6tv, lnwd6tv)
            getTT("wd", "wd7", wd7tv, lnwd7tv)
            getTT("wd", "wd8", wd8tv, lnwd8tv)
            getTT("wd", "wd9", wd9tv, lnwd9tv)
            getTT("wd", "wd10", wd10tv, lnwd10tv)
            getTT("wd", "wd11", wd11tv, lnwd11tv)
            getTT("wd", "wd12", wd12tv, lnwd12tv)
            getTT("th", "th1", th1tv, lnth1tv)
            getTT("th", "th2", th2tv, lnth2tv)
            getTT("th", "th3", th3tv, lnth3tv)
            getTT("th", "th4", th4tv, lnth4tv)
            getTT("th", "th5", th5tv, lnth5tv)
            getTT("th", "th6", th6tv, lnth6tv)
            getTT("th", "th7", th7tv, lnth7tv)
            getTT("th", "th8", th8tv, lnth8tv)
            getTT("th", "th9", th9tv, lnth9tv)
            getTT("th", "th10", th10tv, lnth10tv)
            getTT("th", "th11", th11tv, lnth11tv)
            getTT("th", "th12", th12tv, lnth12tv)
            getTT("fr", "fr1", fr1tv, lnfr1tv)
            getTT("fr", "fr2", fr2tv, lnfr2tv)
            getTT("fr", "fr3", fr3tv, lnfr3tv)
            getTT("fr", "fr4", fr4tv, lnfr4tv)
            getTT("fr", "fr5", fr5tv, lnfr5tv)
            getTT("fr", "fr6", fr6tv, lnfr6tv)
            getTT("fr", "fr7", fr7tv, lnfr7tv)
            getTT("fr", "fr8", fr8tv, lnfr8tv)
            getTT("fr", "fr9", fr9tv, lnfr9tv)
            getTT("fr", "fr10", fr10tv, lnfr10tv)
            getTT("fr", "fr11", fr11tv, lnfr11tv)
            getTT("fr", "fr12", fr12tv, lnfr12tv)
            getTT("st", "st1", st1tv, lnst1tv)
            getTT("st", "st2", st2tv, lnst2tv)
            getTT("st", "st3", st3tv, lnst3tv)
            getTT("st", "st4", st4tv, lnst4tv)
            getTT("st", "st5", st5tv, lnst5tv)
            getTT("st", "st6", st6tv, lnst6tv)
            getTT("st", "st7", st7tv, lnst7tv)
            getTT("st", "st8", st8tv, lnst8tv)
            getTT("st", "st9", st9tv, lnst9tv)
            getTT("st", "st10", st10tv, lnst10tv)
            getTT("st", "st11", st11tv, lnst11tv)
            getTT("st", "st12", st12tv, lnst12tv)
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showHideFromDate() {
        val dform = SimpleDateFormat("EEE")
        val date = dform.format(Calendar.getInstance().time)
        when (date) {
            "вс" -> show(mnSHtv, mnSHln)
            "Sun" -> show(mnSHtv, mnSHln)
            "пн" -> show(mnSHtv, mnSHln)
            "Mon" -> show(mnSHtv, mnSHln)
            "вт" -> show(tySHtv, tySHln)
            "Tues" -> show(tySHtv, tySHln)
            "ср" -> show(wdSHtv, wdSHln)
            "Wed" -> show(wdSHtv, wdSHln)
            "чт" -> show(thSHtv, thSHln)
            "Thurs" -> show(thSHtv, thSHln)
            "пт" -> show(frSHtv, frSHln)
            "Fri" -> show(frSHtv, frSHln)
            "сб" -> show(stSHtv, stSHln)
            "Sat" -> show(stSHtv, stSHln)
            else -> {
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mnTvShowHide -> show(mnSHtv, mnSHln)
            R.id.tyTvShowHide -> show(tySHtv, tySHln)
            R.id.wdTvShowHide -> show(wdSHtv, wdSHln)
            R.id.thTvShowHide -> show(thSHtv, thSHln)
            R.id.frTvShowHide -> show(frSHtv, frSHln)
            R.id.stTvShowHide -> show(stSHtv, stSHln)
            R.id.mnTv -> hide(mnSHtv, mnSHln)
            R.id.tyTv -> hide(tySHtv, tySHln)
            R.id.wdTv -> hide(wdSHtv, wdSHln)
            R.id.thTv -> hide(thSHtv, thSHln)
            R.id.frTv -> hide(frSHtv, frSHln)
            R.id.stTv -> hide(stSHtv, stSHln)
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
        mnSHtv.setOnClickListener(this)
        tySHtv.setOnClickListener(this)
        wdSHtv.setOnClickListener(this)
        thSHtv.setOnClickListener(this)
        frSHtv.setOnClickListener(this)
        stSHtv.setOnClickListener(this)
        mnTv.setOnClickListener(this)
        tyTv.setOnClickListener(this)
        wdTv.setOnClickListener(this)
        thTv.setOnClickListener(this)
        frTv.setOnClickListener(this)
        stTv.setOnClickListener(this)
        st12tv.setOnClickListener(this)

    }

    private fun setInvisibleAll() {
        mnSHln.visibility = View.GONE
        tySHln.visibility = View.GONE
        wdSHln.visibility = View.GONE
        thSHln.visibility = View.GONE
        frSHln.visibility = View.GONE
        stSHln.visibility = View.GONE
        lnmn1tv.visibility = View.GONE
        lnmn2tv.visibility = View.GONE
        lnmn3tv.visibility = View.GONE
        lnmn4tv.visibility = View.GONE
        lnmn5tv.visibility = View.GONE
        lnmn6tv.visibility = View.GONE
        lnmn7tv.visibility = View.GONE
        lnmn8tv.visibility = View.GONE
        lnmn9tv.visibility = View.GONE
        lnmn10tv.visibility = View.GONE
        lnmn11tv.visibility = View.GONE
        lnmn12tv.visibility = View.GONE
        lnty1tv.visibility = View.GONE
        lnty2tv.visibility = View.GONE
        lnty3tv.visibility = View.GONE
        lnty4tv.visibility = View.GONE
        lnty5tv.visibility = View.GONE
        lnty6tv.visibility = View.GONE
        lnty7tv.visibility = View.GONE
        lnty8tv.visibility = View.GONE
        lnty9tv.visibility = View.GONE
        lnty10tv.visibility = View.GONE
        lnty11tv.visibility = View.GONE
        lnty12tv.visibility = View.GONE
        lnwd1tv.visibility = View.GONE
        lnwd2tv.visibility = View.GONE
        lnwd3tv.visibility = View.GONE
        lnwd4tv.visibility = View.GONE
        lnwd5tv.visibility = View.GONE
        lnwd6tv.visibility = View.GONE
        lnwd7tv.visibility = View.GONE
        lnwd8tv.visibility = View.GONE
        lnwd9tv.visibility = View.GONE
        lnwd10tv.visibility = View.GONE
        lnwd11tv.visibility = View.GONE
        lnwd12tv.visibility = View.GONE
        lnth1tv.visibility = View.GONE
        lnth2tv.visibility = View.GONE
        lnth3tv.visibility = View.GONE
        lnth4tv.visibility = View.GONE
        lnth5tv.visibility = View.GONE
        lnth6tv.visibility = View.GONE
        lnth7tv.visibility = View.GONE
        lnth8tv.visibility = View.GONE
        lnth9tv.visibility = View.GONE
        lnth10tv.visibility = View.GONE
        lnth11tv.visibility = View.GONE
        lnth12tv.visibility = View.GONE
        lnfr1tv.visibility = View.GONE
        lnfr2tv.visibility = View.GONE
        lnfr3tv.visibility = View.GONE
        lnfr4tv.visibility = View.GONE
        lnfr5tv.visibility = View.GONE
        lnfr6tv.visibility = View.GONE
        lnfr7tv.visibility = View.GONE
        lnfr8tv.visibility = View.GONE
        lnfr9tv.visibility = View.GONE
        lnfr10tv.visibility = View.GONE
        lnfr11tv.visibility = View.GONE
        lnfr12tv.visibility = View.GONE
        lnst1tv.visibility = View.GONE
        lnst2tv.visibility = View.GONE
        lnst3tv.visibility = View.GONE
        lnst4tv.visibility = View.GONE
        lnst5tv.visibility = View.GONE
        lnst6tv.visibility = View.GONE
        lnst7tv.visibility = View.GONE
        lnst8tv.visibility = View.GONE
        lnst9tv.visibility = View.GONE
        lnst10tv.visibility = View.GONE
        lnst11tv.visibility = View.GONE
        lnst12tv.visibility = View.GONE
    }

    private fun findView() {
        try {
            getTimeTable = StatusDetermination()
            mnSHtv = v.findViewById(R.id.mnTvShowHide)
            tySHtv = v.findViewById(R.id.tyTvShowHide)
            wdSHtv = v.findViewById(R.id.wdTvShowHide)
            thSHtv = v.findViewById(R.id.thTvShowHide)
            frSHtv = v.findViewById(R.id.frTvShowHide)
            stSHtv = v.findViewById(R.id.stTvShowHide)
            mnSHln = v.findViewById(R.id.mnShowHide)
            tySHln = v.findViewById(R.id.tyShowHide)
            wdSHln = v.findViewById(R.id.wdShowHide)
            thSHln = v.findViewById(R.id.thShowHide)
            frSHln = v.findViewById(R.id.frShowHide)
            stSHln = v.findViewById(R.id.stShowHide)
            mnTv = v.findViewById(R.id.mnTv)
            tyTv = v.findViewById(R.id.tyTv)
            wdTv = v.findViewById(R.id.wdTv)
            thTv = v.findViewById(R.id.thTv)
            frTv = v.findViewById(R.id.frTv)
            stTv = v.findViewById(R.id.stTv)
            lnmn1tv = v.findViewById(R.id.lnmn1tv)
            lnmn2tv = v.findViewById(R.id.lnmn2tv)
            lnmn3tv = v.findViewById(R.id.lnmn3tv)
            lnmn4tv = v.findViewById(R.id.lnmn4tv)
            lnmn5tv = v.findViewById(R.id.lnmn5tv)
            lnmn6tv = v.findViewById(R.id.lnmn6tv)
            lnmn7tv = v.findViewById(R.id.lnmn7tv)
            lnmn8tv = v.findViewById(R.id.lnmn8tv)
            lnmn9tv = v.findViewById(R.id.lnmn9tv)
            lnmn10tv = v.findViewById(R.id.lnmn10tv)
            lnmn11tv = v.findViewById(R.id.lnmn11tv)
            lnmn12tv = v.findViewById(R.id.lnmn12tv)
            lnty1tv = v.findViewById(R.id.lnty1tv)
            lnty2tv = v.findViewById(R.id.lnty2tv)
            lnty3tv = v.findViewById(R.id.lnty3tv)
            lnty4tv = v.findViewById(R.id.lnty4tv)
            lnty5tv = v.findViewById(R.id.lnty5tv)
            lnty6tv = v.findViewById(R.id.lnty6tv)
            lnty7tv = v.findViewById(R.id.lnty7tv)
            lnty8tv = v.findViewById(R.id.lnty8tv)
            lnty9tv = v.findViewById(R.id.lnty9tv)
            lnty10tv = v.findViewById(R.id.lnty10tv)
            lnty11tv = v.findViewById(R.id.lnty11tv)
            lnty12tv = v.findViewById(R.id.lnty12tv)
            lnwd1tv = v.findViewById(R.id.lnwd1tv)
            lnwd2tv = v.findViewById(R.id.lnwd2tv)
            lnwd3tv = v.findViewById(R.id.lnwd3tv)
            lnwd4tv = v.findViewById(R.id.lnwd4tv)
            lnwd5tv = v.findViewById(R.id.lnwd5tv)
            lnwd6tv = v.findViewById(R.id.lnwd6tv)
            lnwd7tv = v.findViewById(R.id.lnwd7tv)
            lnwd8tv = v.findViewById(R.id.lnwd8tv)
            lnwd9tv = v.findViewById(R.id.lnwd9tv)
            lnwd10tv = v.findViewById(R.id.lnwd10tv)
            lnwd11tv = v.findViewById(R.id.lnwd11tv)
            lnwd12tv = v.findViewById(R.id.lnwd12tv)
            lnth1tv = v.findViewById(R.id.lnth1tv)
            lnth2tv = v.findViewById(R.id.lnth2tv)
            lnth3tv = v.findViewById(R.id.lnth3tv)
            lnth4tv = v.findViewById(R.id.lnth4tv)
            lnth5tv = v.findViewById(R.id.lnth5tv)
            lnth6tv = v.findViewById(R.id.lnth6tv)
            lnth7tv = v.findViewById(R.id.lnth7tv)
            lnth8tv = v.findViewById(R.id.lnth8tv)
            lnth9tv = v.findViewById(R.id.lnth9tv)
            lnth10tv = v.findViewById(R.id.lnth10tv)
            lnth11tv = v.findViewById(R.id.lnth11tv)
            lnth12tv = v.findViewById(R.id.lnth12tv)
            lnfr1tv = v.findViewById(R.id.lnfr1tv)
            lnfr2tv = v.findViewById(R.id.lnfr2tv)
            lnfr3tv = v.findViewById(R.id.lnfr3tv)
            lnfr4tv = v.findViewById(R.id.lnfr4tv)
            lnfr5tv = v.findViewById(R.id.lnfr5tv)
            lnfr6tv = v.findViewById(R.id.lnfr6tv)
            lnfr7tv = v.findViewById(R.id.lnfr7tv)
            lnfr8tv = v.findViewById(R.id.lnfr8tv)
            lnfr9tv = v.findViewById(R.id.lnfr9tv)
            lnfr10tv = v.findViewById(R.id.lnfr10tv)
            lnfr11tv = v.findViewById(R.id.lnfr11tv)
            lnfr12tv = v.findViewById(R.id.lnfr12tv)
            lnst1tv = v.findViewById(R.id.lnst1tv)
            lnst2tv = v.findViewById(R.id.lnst2tv)
            lnst3tv = v.findViewById(R.id.lnst3tv)
            lnst4tv = v.findViewById(R.id.lnst4tv)
            lnst5tv = v.findViewById(R.id.lnst5tv)
            lnst6tv = v.findViewById(R.id.lnst6tv)
            lnst7tv = v.findViewById(R.id.lnst7tv)
            lnst8tv = v.findViewById(R.id.lnst8tv)
            lnst9tv = v.findViewById(R.id.lnst9tv)
            lnst10tv = v.findViewById(R.id.lnst10tv)
            lnst11tv = v.findViewById(R.id.lnst11tv)
            lnst12tv = v.findViewById(R.id.lnst12tv)

            mn1tv = v.findViewById(R.id.mn1tv)
            mn2tv = v.findViewById(R.id.mn2tv)
            mn3tv = v.findViewById(R.id.mn3tv)
            mn4tv = v.findViewById(R.id.mn4tv)
            mn5tv = v.findViewById(R.id.mn5tv)
            mn6tv = v.findViewById(R.id.mn6tv)
            mn7tv = v.findViewById(R.id.mn7tv)
            mn8tv = v.findViewById(R.id.mn8tv)
            mn9tv = v.findViewById(R.id.mn9tv)
            mn10tv = v.findViewById(R.id.mn10tv)
            mn11tv = v.findViewById(R.id.mn11tv)
            mn12tv = v.findViewById(R.id.mn12tv)
            ty1tv = v.findViewById(R.id.ty1tv)
            ty2tv = v.findViewById(R.id.ty2tv)
            ty3tv = v.findViewById(R.id.ty3tv)
            ty4tv = v.findViewById(R.id.ty4tv)
            ty5tv = v.findViewById(R.id.ty5tv)
            ty6tv = v.findViewById(R.id.ty6tv)
            ty7tv = v.findViewById(R.id.ty7tv)
            ty8tv = v.findViewById(R.id.ty8tv)
            ty9tv = v.findViewById(R.id.ty9tv)
            ty10tv = v.findViewById(R.id.ty10tv)
            ty11tv = v.findViewById(R.id.ty11tv)
            ty12tv = v.findViewById(R.id.ty12tv)
            wd1tv = v.findViewById(R.id.wd1tv)
            wd2tv = v.findViewById(R.id.wd2tv)
            wd3tv = v.findViewById(R.id.wd3tv)
            wd4tv = v.findViewById(R.id.wd4tv)
            wd5tv = v.findViewById(R.id.wd5tv)
            wd6tv = v.findViewById(R.id.wd6tv)
            wd7tv = v.findViewById(R.id.wd7tv)
            wd8tv = v.findViewById(R.id.wd8tv)
            wd9tv = v.findViewById(R.id.wd9tv)
            wd10tv = v.findViewById(R.id.wd10tv)
            wd11tv = v.findViewById(R.id.wd11tv)
            wd12tv = v.findViewById(R.id.wd12tv)
            th1tv = v.findViewById(R.id.th1tv)
            th2tv = v.findViewById(R.id.th2tv)
            th3tv = v.findViewById(R.id.th3tv)
            th4tv = v.findViewById(R.id.th4tv)
            th5tv = v.findViewById(R.id.th5tv)
            th6tv = v.findViewById(R.id.th6tv)
            th7tv = v.findViewById(R.id.th7tv)
            th8tv = v.findViewById(R.id.th8tv)
            th9tv = v.findViewById(R.id.th9tv)
            th10tv = v.findViewById(R.id.th10tv)
            th11tv = v.findViewById(R.id.th11tv)
            th12tv = v.findViewById(R.id.th12tv)
            fr1tv = v.findViewById(R.id.fr1tv)
            fr2tv = v.findViewById(R.id.fr2tv)
            fr3tv = v.findViewById(R.id.fr3tv)
            fr4tv = v.findViewById(R.id.fr4tv)
            fr5tv = v.findViewById(R.id.fr5tv)
            fr6tv = v.findViewById(R.id.fr6tv)
            fr7tv = v.findViewById(R.id.fr7tv)
            fr8tv = v.findViewById(R.id.fr8tv)
            fr9tv = v.findViewById(R.id.fr9tv)
            fr10tv = v.findViewById(R.id.fr10tv)
            fr11tv = v.findViewById(R.id.fr11tv)
            fr12tv = v.findViewById(R.id.fr12tv)
            st1tv = v.findViewById(R.id.st1tv)
            st2tv = v.findViewById(R.id.st2tv)
            st3tv = v.findViewById(R.id.st3tv)
            st4tv = v.findViewById(R.id.st4tv)
            st5tv = v.findViewById(R.id.st5tv)
            st6tv = v.findViewById(R.id.st6tv)
            st7tv = v.findViewById(R.id.st7tv)
            st8tv = v.findViewById(R.id.st8tv)
            st9tv = v.findViewById(R.id.st9tv)
            st10tv = v.findViewById(R.id.st10tv)
            st11tv = v.findViewById(R.id.st11tv)
            st12tv = v.findViewById(R.id.st12tv)
            cl()
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
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