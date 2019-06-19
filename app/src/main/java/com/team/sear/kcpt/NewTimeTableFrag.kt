package com.team.sear.kcpt


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.databinding.FragmentNewTimeTableBinding
import com.team.sear.kcpt.objects.ChangesParser
import com.team.sear.kcpt.objects.TimeTableViewsInit
import com.team.sear.kcpt.timetablefragments.ChangesFrag
import java.text.SimpleDateFormat
import java.util.*

class NewTimeTableFrag : Fragment() {

    internal lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var initTT: TimeTableViewsInit
    private lateinit var b: FragmentNewTimeTableBinding

    private lateinit var changesParser: ChangesParser
    private lateinit var changesHTML: String
    private lateinit var groupName: String


    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater,R.layout.fragment_new_time_table,container,false)
        initTT = TimeTableViewsInit()

        setInvisibleAll()

        try {

            mAuth = FirebaseAuth.getInstance()
            authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
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

        try {
            changesParser = ChangesParser()
            mAuth = FirebaseAuth.getInstance()
            authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                } else {
                }
            }

            if (!ChangesFrag.DetectConnection.checkInternetConnection(this.context)) {
                b.userWebChangesOnMainScreen.loadData("Отсутствует подключение!", "text/html; charset=UTF-8", null)
                Toast.makeText(context, "Отсутствует подключение!", Toast.LENGTH_SHORT).show()
            } else {
                UserChangesParser().execute()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        }

        cl()
        Handler().postDelayed({ showHideFromDate() }, 100)
        b.todayBtStr = setDateOnTextViewsTodayTomorrow(0)
        b.tomorrowBtStr = setDateOnTextViewsTodayTomorrow(1)
        return b.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDateOnTextViewsTodayTomorrow(sw: Int): String{
        val mount = SimpleDateFormat("dd")
        val d =  mount.format(Calendar.getInstance().time)
        return when(sw){
            0 -> d
            1 -> {
                val d1 = Calendar.getInstance()
                d1.time = mount.parse(d)
                d1.add(Calendar.DATE,1)
                val d1Str = mount.format(d1.time).toString()
                d1Str
            }
            else -> {
                ""
            }
        }
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
                b.dayName1 = "Приложение не смогло определить день недели на вашем телефоне, обратитесь к разработчику"
                b.dayName2 = "Приложение не смогло определить день недели на вашем телефоне, обратитесь к разработчику"
            }
        }
    }

    private fun getTimeTableMn(){
        try {
            setInvisibleAll()
            b.dayName1 = "Понедельник"
            b.dayName2 = "Понедельник"
            initTT.getTT("mn", "mn1",  b.ln1tv,mAuth, b.tv1)
            initTT.getTT("mn", "mn2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("mn", "mn3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("mn", "mn4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("mn", "mn5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("mn", "mn6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("mn", "mn7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("mn", "mn8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("mn", "mn9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("mn", "mn10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("mn", "mn11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("mn", "mn12", b.ln12tv,mAuth, b.tv12)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableTy(){
        try {
            setInvisibleAll()
            b.dayName1 = "Вторник"
            b.dayName2 = "Вторник"
            initTT.getTT("ty", "ty1", b.ln1tv,mAuth, b.tv1)
            initTT.getTT("ty", "ty2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("ty", "ty3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("ty", "ty4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("ty", "ty5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("ty", "ty6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("ty", "ty7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("ty", "ty8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("ty", "ty9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("ty", "ty10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("ty", "ty11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("ty", "ty12", b.ln12tv,mAuth, b.tv12)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableWd(){
        try {
            setInvisibleAll()
            b.dayName1 = "Среда"
            b.dayName2 = "Среда"
            initTT.getTT("wd", "wd1", b.ln1tv,mAuth, b.tv1)
            initTT.getTT("wd", "wd2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("wd", "wd3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("wd", "wd4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("wd", "wd5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("wd", "wd6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("wd", "wd7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("wd", "wd8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("wd", "wd9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("wd", "wd10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("wd", "wd11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("wd", "wd12", b.ln12tv,mAuth, b.tv12)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableTh(){
        try {
            setInvisibleAll()
            b.dayName1 = "Четверг"
            b.dayName2 = "Четверг"
            initTT.getTT("th", "th1", b.ln1tv,mAuth, b.tv1)
            initTT.getTT("th", "th2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("th", "th3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("th", "th4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("th", "th5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("th", "th6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("th", "th7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("th", "th8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("th", "th9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("th", "th10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("th", "th11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("th", "th12", b.ln12tv,mAuth, b.tv12)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableFr(){
        try {
            setInvisibleAll()
            b.dayName1 = "Пятница"
            b.dayName2 = "Пятница"
            initTT.getTT("fr", "fr1", b.ln1tv,mAuth, b.tv1)
            initTT.getTT("fr", "fr2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("fr", "fr3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("fr", "fr4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("fr", "fr5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("fr", "fr6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("fr", "fr7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("fr", "fr8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("fr", "fr9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("fr", "fr10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("fr", "fr11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("fr", "fr12", b.ln12tv,mAuth, b.tv12)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }

    private fun getTimeTableSt(){
        try {
            setInvisibleAll()
            b.dayName1 = "Суббота"
            b.dayName2 = "Суббота"
            initTT.getTT("st", "st1", b.ln1tv,mAuth, b.tv1)
            initTT.getTT("st", "st2", b.ln2tv,mAuth, b.tv2)
            initTT.getTT("st", "st3", b.ln3tv,mAuth, b.tv3)
            initTT.getTT("st", "st4", b.ln4tv,mAuth, b.tv4)
            initTT.getTT("st", "st5", b.ln5tv,mAuth, b.tv5)
            initTT.getTT("st", "st6", b.ln6tv,mAuth, b.tv6)
            initTT.getTT("st", "st7", b.ln7tv,mAuth, b.tv7)
            initTT.getTT("st", "st8", b.ln8tv,mAuth, b.tv8)
            initTT.getTT("st", "st9", b.ln9tv,mAuth, b.tv9)
            initTT.getTT("st", "st10", b.ln10tv,mAuth, b.tv10)
            initTT.getTT("st", "st11", b.ln11tv,mAuth, b.tv11)
            initTT.getTT("st", "st12", b.ln12tv,mAuth, b.tv12)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
        }


    private fun cl() {
        try {

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun setInvisibleAll() {
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
        mAuth.addAuthStateListener(authListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            mAuth.removeAuthStateListener(authListener!!)
        }
    }

    private fun getGroupName() {
        database = FirebaseDatabase.getInstance()
        user = mAuth.currentUser
        try {
            myRef = database.getReference("users").child(user!!.uid).child("group")
            myRef.addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            groupName = dataSnapshot.getValue(String::class.java)!!
                            GetGroupName().execute()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            groupName = ""
                            GetGroupName().execute()
                        }
                    })
        } catch (e: Exception) {
            groupName = ""
            GetGroupName().execute()
        }

    }

    private fun setGroupName(): String {
        return when (groupName) {
            "AT1609" -> "АТ 16-09"
            "AT1709" -> "АТ 17-09"
            "AT1711" -> "АТ 17-11"
            "AT1811" -> "АТ 18-11"
            "ATPiP1509" -> "АТПиП 15-09"
            "ATPiP1609" -> "АТПиП 16-09"
            "ATPiP1611" -> "АТПиП 16-11"
            "DO15091" -> "ДО 15-09-1"
            "DO15092" -> "ДО 15-09-2"
            "DO1611" -> "ДО 16-11"
            "DO17111" -> "ДО 17-11-1"
            "DO17112" -> "ДО 17-11-2"
            "DO18111" -> "ДО 18-11-1"
            "DO18112" -> "ДО 18-11-2"
            "KP16111" -> "КП 16-11-1"
            "KP16112" -> "КП 16-11-2"
            "KP1709" -> "КП 17-09"
            "KP17111" -> "КП 17-11-1"
            "KP17112" -> "КП 17-11-2"
            "KP17113" -> "КП 17-11-3"
            "KP18111" -> "КП 18-11-1"
            "KP18112" -> "КП 18-11-2"
            "KS1611" -> "КС 16-11"
            "OSATPiP1711" -> "ОСАТПиП 17-11"
            "OSATPiP18111" -> "ОСАТПиП 18-11-1"
            "OSATPiP18112" -> "ОСАТПиП 18-11-2"
            "PDOTT1509" -> "ПДО ТТ 15-09"
            "PDOTT1609" -> "ПДО ТТ 16-09"
            "PDOTT1709" -> "ПДО ТТ 17-09"
            "PDOTT18111" -> "ПДО ТТ 18-11-1"
            "PDOTT18112" -> "ПДО ТТ 18-11-2"
            "SSA1711" -> "ССА 17-11"
            "SSA18111" -> "ССА 18-11-1"
            "SSA18112" -> "ССА 18-11-2"
            "SHO15091" -> "ШО 15-09-1"
            "SHO15092" -> "ШО 15-09-2"
            else -> {
                "noChangesOrNotStudent"
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class GetGroupName : AsyncTask<String, Void, String>() {
        @SuppressLint("SetTextI18n")
        override fun doInBackground(vararg result: String?): String? {
            return try {
                changesParser.selectGroup(setGroupName())
                changesHTML = changesParser.parseChanges()!!
                changesHTML
            } catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
                Toast.makeText(context, "Не удалось", Toast.LENGTH_SHORT).show()
                null
            }
        }

        override fun onPostExecute(result: String?) {
            try {
                b.userWebChangesOnMainScreen.loadData(changesHTML, "text/html; charset=UTF-8", null)
            } catch (e: Exception) {
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class UserChangesParser : AsyncTask<Void, Void, Void>() {
        @SuppressLint("SetTextI18n")
        override fun doInBackground(vararg result: Void?): Void? {
            try {
                getGroupName()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}
