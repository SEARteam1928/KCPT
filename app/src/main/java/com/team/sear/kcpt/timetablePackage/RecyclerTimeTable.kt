package com.team.sear.kcpt.timetablePackage

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.team.sear.kcpt.MyCallback
import com.team.sear.kcpt.R
import com.team.sear.kcpt.objects.Style
import java.lang.invoke.MethodHandleInfo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RecyclerTimeTable : Fragment() {
    private lateinit var v: View

    private lateinit var lessonRecycler: RecyclerView
    private lateinit var lessons: ArrayList<Lesson?>
    private lateinit var studentAdapter: StudentLessonAdapter
    private lateinit var teacherAdapter: TeacherLessonAdapter

    private lateinit var lessonList: ArrayList<Lesson?>
    private var idLessons: ArrayList<String?>? = null
    @SuppressLint("StaticFieldLeak")
    private lateinit var webChanges: WebView

    private var noDataTv: TextView? = null

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.recycler_time_table, container, false)

        noDataTv = v.findViewById(R.id.text_no_data)

        lessons = ArrayList()

        lessonRecycler = v.findViewById(R.id.lessonRecycler)
        lessonRecycler.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        lessonRecycler.layoutManager =llm

        putChangesInWebView()
        auth = FirebaseAuth.getInstance()
        authComplete()

        return v
    }

    private fun putChangesInWebView(){
        val style = Style()
        val styleStr = style.style
        webChanges = v.findViewById(R.id.userChangesWebView)
        webChanges.settings.javaScriptEnabled
        webChanges.settings.builtInZoomControls
        webChanges.settings.supportZoom()
        webChanges.settings.displayZoomControls
        webChanges.settings.loadWithOverviewMode
        webChanges.settings.defaultFixedFontSize = 15
        webChanges.settings.setAppCacheMaxSize(20 * 1024 * 1024)
        webChanges.settings.setAppCachePath(context!!.cacheDir.absolutePath)
        webChanges.settings.allowFileAccess
        webChanges.settings.setAppCacheEnabled(true)
        webChanges.settings.cacheMode = WebSettings.LOAD_DEFAULT
        val html = "<!Doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +

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

                "<p>ЗДЕСЬ БУДУТ \nИЗМЕНЕНИЯ</p>" +
                "</body>\n" +
                "</html>"
        webChanges.loadData(html, "text/html; charset=UTF-8", null)
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {

                database = FirebaseDatabase.getInstance()
                ref = database!!.reference.child("newTimeTable")
                var uId = auth!!.uid

                setAdapter("STUDENT",lessons)
                updateList()
            } else {
                Toast.makeText(activity, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getToday(): String {
        val dform = SimpleDateFormat("EEE")
        return when (dform.format(Calendar.getInstance().time)) {
            "вс" -> "mn"
            "Sun" -> "mn"
            "пн" -> "mn"
            "Mon" -> "mn"
            "вт" -> "ty"
            "Tues" -> "ty"
            "ср" -> "wd"
            "Wed" -> "wd"
            "чт" -> "th"
            "Thurs" -> "th"
            "пт" -> "fr"
            "Fri" -> "fr"
            "сб" -> "st"
            "Sat" -> "st"
            else -> {
                ""
            }
        }
    }

    private fun groupName(): String {
        return "ССА 18-11-2"
    }

    private fun teacherName(): String {
        return "Полищук А. А."
    }

    private fun updateList(){
        ref!!.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(datasnapshot: DataSnapshot, p1: String?) {
                lessons.add(datasnapshot.getValue(Lesson::class.java))
/*
                setNotify("STUDENT")
*/
            }

            override fun onChildChanged(datasnapshot: DataSnapshot, p1: String?) {
                val lesson: Lesson? = datasnapshot.getValue(Lesson::class.java)
                val index: Int = getItemIndex(lessons)

                lessons[index] = lesson
            }

            override fun onChildRemoved(datasnapshot: DataSnapshot) {
            }

            override fun onChildMoved(datasnapshot: DataSnapshot, p1: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    private fun getItemIndex(lessons: ArrayList<Lesson?>): Int{ return lessons.size}

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

    private fun setAdapter(status: String, lessons: ArrayList<Lesson?>) {
        if (status == "STUDENT") {
            lessonRecycler.adapter = StudentLessonAdapter(lessons)
        }
        if (status == "TEACHER") {
            lessonRecycler.adapter = TeacherLessonAdapter(lessons)
        }
    }

    private fun setNotify(status: String) {
        if (status == "STUDENT") {
            studentAdapter.notifyDataSetChanged()
        }
        if (status == "TEACHER") {
            teacherAdapter.notifyDataSetChanged()
        }
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

}


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