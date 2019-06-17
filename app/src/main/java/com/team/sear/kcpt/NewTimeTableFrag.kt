package com.team.sear.kcpt


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.team.sear.kcpt.databinding.FragmentNewTimeTableBinding
import com.team.sear.kcpt.objects.TimeTableViewsInit
import java.text.SimpleDateFormat
import java.util.*

class NewTimeTableFrag : Fragment() {

    internal lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var initTT: TimeTableViewsInit
    private lateinit var b: FragmentNewTimeTableBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater,R.layout.fragment_new_time_table,container,false)
        initTT = TimeTableViewsInit()



        try {
            cl()
            mAuth = FirebaseAuth.getInstance()
            mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
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
               /* setTimeTable()*/
            } catch (e: Exception) {
                Toast.makeText(context, "Обновите или выберите расписание", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

        Handler().postDelayed({ showHideFromDate() }, 100)
        return b.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun showHideFromDate() {
        val dform = SimpleDateFormat("EEE")
        when (dform.format(Calendar.getInstance().time)) {
            "вс" -> getTimeTableMn()
            "Sun" -> getTimeTableMn()
            "пн" -> getTimeTableMn()
            "Mon" -> getTimeTableMn()
            "вт" -> getTimeTableTy()
            "Tues" -> getTimeTableTy()
            "ср" -> getTimeTableWd()
            "Wed" -> getTimeTableWd()
            "чт" -> getTimeTableTh()
            "Thurs" -> getTimeTableTh()
            "пт" -> getTimeTableFr()
            "Fri" -> getTimeTableFr()
            "сб" -> getTimeTableSt()
            "Sat" -> getTimeTableSt()
            else -> {
                getTimeTableMn()
                b.dayName1 = "Приложение не смогло определить день недели на вашем телефоне"
                b.dayName2 = "Приложение не смогло определить день недели на вашем телефоне"
            }
        }
    }

    private fun getTimeTableMn(){
        try {
            b.dayName1 = "Понедельник"
            b.dayName2 = "Понедельник"
            initTT.getTT("mn", "mn1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("mn", "mn2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("mn", "mn3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("mn", "mn4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("mn", "mn5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("mn", "mn6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("mn", "mn7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("mn", "mn8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("mn", "mn9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("mn", "mn10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("mn", "mn11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("mn", "mn12", b.lesson12 , b.ln12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableTy(){
        try {
            b.dayName1 = "Вторник"
            b.dayName2 = "Вторник"
            initTT.getTT("ty", "ty1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("ty", "ty2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("ty", "ty3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("ty", "ty4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("ty", "ty5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("ty", "ty6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("ty", "ty7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("ty", "ty8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("ty", "ty9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("ty", "ty10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("ty", "ty11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("ty", "ty12", b.lesson12 , b.ln12tv,mAuth)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableWd(){
        try {
            b.dayName1 = "Среда"
            b.dayName2 = "Среда"
            initTT.getTT("wd", "wd1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("wd", "wd2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("wd", "wd3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("wd", "wd4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("wd", "wd5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("wd", "wd6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("wd", "wd7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("wd", "wd8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("wd", "wd9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("wd", "wd10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("wd", "wd11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("wd", "wd12", b.lesson12 , b.ln12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableTh(){
        try {
            b.dayName1 = "Четверг"
            b.dayName2 = "Четверг"
            initTT.getTT("th", "th1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("th", "th2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("th", "th3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("th", "th4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("th", "th5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("th", "th6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("th", "th7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("th", "th8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("th", "th9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("th", "th10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("th", "th11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("th", "th12", b.lesson12 , b.ln12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableFr(){
        try {
            b.dayName1 = "Пятница"
            b.dayName2 = "Пятница"
            initTT.getTT("fr", "fr1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("fr", "fr2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("fr", "fr3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("fr", "fr4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("fr", "fr5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("fr", "fr6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("fr", "fr7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("fr", "fr8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("fr", "fr9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("fr", "fr10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("fr", "fr11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("fr", "fr12", b.lesson12 , b.ln12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableSt(){
        try {
            b.dayName1 = "Суббота"
            b.dayName2 = "Суббота"
            initTT.getTT("st", "st1", b.lesson1 , b.ln1tv,mAuth)
            initTT.getTT("st", "st2", b.lesson2 , b.ln2tv,mAuth)
            initTT.getTT("st", "st3", b.lesson3 , b.ln3tv,mAuth)
            initTT.getTT("st", "st4", b.lesson4 , b.ln4tv,mAuth)
            initTT.getTT("st", "st5", b.lesson5 , b.ln5tv,mAuth)
            initTT.getTT("st", "st6", b.lesson6 , b.ln6tv,mAuth)
            initTT.getTT("st", "st7", b.lesson7 , b.ln7tv,mAuth)
            initTT.getTT("st", "st8", b.lesson8 , b.ln8tv,mAuth)
            initTT.getTT("st", "st9", b.lesson9 , b.ln9tv,mAuth)
            initTT.getTT("st", "st10", b.lesson10 , b.ln10tv,mAuth)
            initTT.getTT("st", "st11", b.lesson11 , b.ln11tv,mAuth)
            initTT.getTT("st", "st12", b.lesson12 , b.ln12tv,mAuth)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun cl() {
        try {
            initTT.setShowCl(b.tvShowHide, b.showHide)
            initTT.setHideCl(b.tv, b.showHide)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun setInvisibleAll() {
        initTT.invisView(b.showHide)
        initTT.invisView(b.ln1tv)
        initTT.invisView(b.ln2tv)
        initTT.invisView(b.ln3tv)
        initTT.invisView(b.ln4tv)
        initTT.invisView(b.ln5tv)
        initTT.invisView(b.ln6tv)
        initTT.invisView(b.ln7tv)
        initTT.invisView(b.ln8tv)
        initTT.invisView(b.ln9tv)
        initTT.invisView(b.ln10tv)
        initTT.invisView(b.ln11tv)
        initTT.invisView(b.ln12tv)
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
