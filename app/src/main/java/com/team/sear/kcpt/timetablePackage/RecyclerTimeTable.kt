package com.team.sear.kcpt.timetablePackage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.R
import com.team.sear.kcpt.objects.ChangesParser
import com.team.sear.kcpt.timetablefragments.ChangesFrag
import com.team.sear.kcpt.timetablefragments.SelectTimeTableForApp
/*
import com.team.sear.kcpt.objects.Style
*/
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("SimpleDateFormat")
class RecyclerTimeTable : Fragment() {
    @SuppressLint("StaticFieldLeak")
    private lateinit var v: View

    @SuppressLint("StaticFieldLeak")
    private lateinit var lessonRecycler: RecyclerView
    private lateinit var lessons: ArrayList<Lesson?>
    private lateinit var ttDownloader: TimeTableInOneDayDownloader
    @SuppressLint("StaticFieldLeak")
    private lateinit var webChanges: WebView

    private var noDataTv: TextView? = null
    private lateinit var changesParser: ChangesParser
    private lateinit var changesHTML: String
    private lateinit var groupName: String

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.recycler_time_table, container, false)
        noDataTv = v.findViewById(R.id.text_no_data)
        ttDownloader = TimeTableInOneDayDownloader()
        lessons = ArrayList()
        changesParser = ChangesParser()

        lessonRecycler = v.findViewById(R.id.lessonRecycler)
        lessonRecycler.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        lessonRecycler.layoutManager = llm


        auth = FirebaseAuth.getInstance()
        authComplete()
/*        try {
            if (!ChangesFrag.DetectConnection.checkInternetConnection(this.context)) {
                webChanges.loadData("Отсутствует подключение!", "text/html; charset=UTF-8", null)
                Toast.makeText(context, "Отсутствует подключение!", Toast.LENGTH_SHORT).show()
            } else {
                putChangesInWebView()
                UserChangesParser().execute()
            }
        } catch (e: Exception) {

        }*/
        return v
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

            checkIfEmptyStatus()

            user = firebaseAuth.currentUser


            if (user != null) {
                user = firebaseAuth.currentUser
                ttDownloader.enable(lessons, getToday(), lessonRecycler, noDataTv!!, auth!!,user!!)
                Handler().postDelayed({ttDownloader.enable(lessons, getToday(), lessonRecycler, noDataTv!!, auth!!,user!!)},500)
                sendMySignedIn()
            } else {
                Toast.makeText(activity, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendMySignedIn() {
        try {
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            val df = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z ")
            val date = df.format(Calendar.getInstance().time)
            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("signedIn")
            ref!!.setValue(date + "SIGNEDIN")
        } catch (e: Exception) {
            Toast.makeText(context, "Undefinded error!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getToday(): String {
        val dform = SimpleDateFormat("EEE")
        return when (dform.format(Calendar.getInstance().time)) {
            "вс" -> "Понедельник"
            "Sun" -> "Понедельник"
            "пн" -> "Понедельник"
            "Mon" -> "Понедельник"
            "вт" -> "Вторник"
            "Tues" -> "Вторник"
            "ср" -> "Среда"
            "Wed" -> "Среда"
            "чт" -> "Четверг"
            "Thurs" -> "Четверг"
            "пт" -> "Пятница"
            "Fri" -> "Пятница"
            "сб" -> "Суббота"
            "Sat" -> "Суббота"
            else -> {
                ""
            }
        }
    }

    private fun checkIfEmptyStatus() {
        user = auth!!.currentUser
        database = FirebaseDatabase.getInstance()

        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user!!.uid)
                .child("groupOrTeacherName")

        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val groupOrTeacherName = dataSnapshot.getValue(String::class.java)
                        if ((groupOrTeacherName == "") || (groupOrTeacherName == null)) {
                            val intent = Intent(context, SelectTimeTableForApp::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener(authListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth!!.removeAuthStateListener(authListener!!)
        }
    }

    private fun getGroupName() {
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        try {
            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("groupOrTeacherName")
            ref!!.addValueEventListener(
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

    @SuppressLint("StaticFieldLeak")
    internal inner class GetGroupName : AsyncTask<String, Void, String>() {
        @SuppressLint("SetTextI18n")
        override fun doInBackground(vararg result: String?): String? {
            return try {
                changesParser.selectGroup(groupName)
                changesHTML = changesParser.parseChanges()!!
                changesHTML
            } catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
                null
            }
        }

        override fun onPostExecute(result: String?) {
            try {
                webChanges.loadData(changesHTML, "text/html; charset=UTF-8", null)
            } catch (e: Exception) {
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class UserChangesParser : AsyncTask<Void, Void, Void>() {
        @SuppressLint("SetTextI18n", "WrongThread")
        override fun doInBackground(vararg result: Void?): Void? {
            try {
/*
                getGroupName()
*/
                webChanges!!.loadUrl("https://docs.google.com/document/d/e/2PACX-1vS2ehAErYyAWY-cm247Pt4oT2YVAkEMwiYXhFu0pxGexUne1PTWNiWS0ktvlglRQqNpLtolGzJjIlvc/pub")

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    private fun putChangesInWebView() {
/*        val style = Style()
        val styleStr = style.style*/
        webChanges = v.findViewById(R.id.userChangesWebView)
        webChanges.settings.javaScriptEnabled
        webChanges.settings.builtInZoomControls
        webChanges.settings.supportZoom()
        webChanges.settings.displayZoomControls
        webChanges.settings.loadWithOverviewMode
        webChanges.settings.defaultFixedFontSize = 15
/*
        webChanges.settings.setAppCacheMaxSize(20 * 1024 * 1024)
*/
        webChanges.settings.setAppCachePath(context!!.cacheDir.absolutePath)
        webChanges.settings.allowFileAccess
        webChanges.settings.setAppCacheEnabled(true)
        webChanges.settings.cacheMode = WebSettings.LOAD_DEFAULT
/*        val html = "<!Doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
*//*
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
                "<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +"<br>\n" +
*//*

                "<p>ЗДЕСЬ БУДУТ \nИЗМЕНЕНИЯ</p>" +
                "</body>\n" +
                "</html>"
        webChanges.loadData(html, "text/html; charset=UTF-8", null)*/
    }

}
/*
ref = database!!.getReference("Учреждения").child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"").child("users").child(user!!.uid).child("status")
ref!!.setValue(status)
ref = database!!.getReference("Учреждения").child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"").child("users").child(user!!.uid).child("groupOrTeacherName")
ref!!.setValue(item.title)*/

/*    private fun setFakeLessons() {
        lessonList = ArrayList(13)
        lessonList.add(setLesson("1. Инженерная и компьютерная графика\n 2. Инженерная и компьютерная графика", "1", "mn", "ССА 18-11-2", "Литус А.А.\nПопов кто-то", "999\n777", "8:00\n8:01", "allGroup"))
        lessonList.add(setLesson("1. Инженерная и компьютерная графика\n 2. Инженерная и компьютерная графика", "2", "mn", "ССА 18-11-2", "Литус А.А.\nПопов кто-то", "999\n777", "9:00\n9:01", "allGroup"))
        lessonList.add(setLesson("Программирование", "3", "mn", "ССА 18-11-2", "Гуляев И. П.", "414", "10:00\n10:01", "allGroup"))
        lessonList.add(setLesson("Программирование", "4", "mn", "ССА 18-11-2", "Гуляев И.П.", "414", "11:00\n11:01", "allGroup"))
        lessonList.add(setLesson("Технологии физического уровня передачи данных", "5", "mn", "ССА 18-11-2", "Бородина С.В.", "105", "12:00\n12:01", "allGroup"))
        lessonList.add(setLesson("Технологии физического уровня передачи данных", "6", "mn", "ССА 18-11-2", "Бородина С.В.", "105", "13:00\n13:01", "allGroup"))
        lessonList.add(setLesson("Технические средства информатизации", "7", "mn", "ССА 18-11-2", "Полищук А.А.", "415", "14:00\n14:01", "allGroup"))
        lessonList.add(setLesson("Технические средства информатизации", "8", "mn", "ССА 18-11-2", "Полищук А.А.", "415", "15:00\n15:01", "allGroup"))
        lessonList.add(setLesson("Физическая культура", "9", "mn", "ССА 18-11-2", "Кто-то там", "Спорт. Зал", "16:00\n16:01", "allGroup"))
        lessonList.add(setLesson("Физическая культура", "10", "mn", "ССА 18-11-2", "Еще кто-то там", "Спорт. Зал", "17:00\n17:01", "allGroup"))
        lessonList.add(setLesson("Основы Теории Информации", "11", "mn", "ССА 18-11-2", "Полищук А. А.", "415", "18:00\n18:01", "allGroup"))
        lessonList.add(setLesson("Основы Теории Информации", "12", "mn", "ССА 18-11-2", "Полищук А. А", "415", "19:00\n19:01", "allGroup"))
        setAdapter("STUDENT", lessonList)
    }*/
/*
    private fun setLesson(lesson: String, lessonNum: String, dayOfWeek: String, groupName: String, teacherName: String, roomNum: String, lessonTime: String, groupOrSubGroup: String): Lesson {
        val l = Lesson()
        l.lesson = lesson
        l.lessonNum = lessonNum
        l.dayofweek = dayOfWeek
        l.groupName = groupName
        l.teacherName = teacherName
        l.roomNum = roomNum
        l.lessonTime = lessonTime
        l.groupOrSubGroup = groupOrSubGroup
        return l
    }
    */
/*    private fun removeLesson(position: Int){
        ref!!.child(lessons.get(position).lesson).removeValue()
    }*/

/* private fun changeLesson(position: Int){
        var lesson = lessons[position]
        lesson!!.lesson = "ИЗМЕНЁН"

        var lessonValues: Map<String, Any> = lesson.toMap()
        var newLesson: Map<String, Any> = HashMap()

        newLesson.put(lesson.lesson, lessonValues)

        ref!!.updateChildren(newLesson)
    }*/