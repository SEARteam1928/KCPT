package com.team.sear.kcpt.timetablePackage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.MyCallback
import com.team.sear.kcpt.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RecyclerTimeTable : Fragment() {
    private lateinit var v: View
    private lateinit var lessonRecycler: RecyclerView

    private lateinit var lessonList: ArrayList<Lesson?>
    private lateinit var lessons: Array<Lesson?>
    private var idLessons: ArrayList<String?>? = null


    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    var childrenInt = 0
    var count = 0
    var setAdapterCount = 0
    var addLessonCount = 0
    var teacherNameStr = ""
    var dayOfWeekStr = ""
    var groupStr = ""
    var groupOrSubGroupStr = ""
    var lessonStr = ""
    var lessonNumString = ""
    var lessonTimeStr = ""
    var roomStr = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.recycler_time_table, container, false)
        lessonRecycler = v.findViewById(R.id.lessonRecycler)
        lessonRecycler.layoutManager = LinearLayoutManager(context)

        auth = FirebaseAuth.getInstance()
        authComplete()


        return v
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
                Toast.makeText(activity, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
            runGetThenSetTimeTable()
/*
            setFakeLessons()
*/
            /*setFeedbackView()*/
        }
    }

    private fun runGetThenSetTimeTable() {
        var status: String?
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        ref = database!!.getReference("users").child(user!!.uid).child("status")
        database
        user
        ref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                status = dataSnapshot.getValue(String::class.java)
                if (status == null || status == "") {
                    Toast.makeText(context, "Error on getting Status", Toast.LENGTH_SHORT).show()
                } else {
                    getNotSortedKeys(status!!)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error on getting Status", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getNotSortedKeys(status: String) {
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        if (status == "STUDENT") {
            getLessonForStudentOrTeacher("group", groupName(), status)
        }
        if (status == "TEACHER") {
            getLessonForStudentOrTeacher("TeacherName", teacherName(), status)
        }
    }

    private fun getLessonForStudentOrTeacher(studentOrTeacher: String, name: String, status: String) {
        childrenInt = 0
        val todayStr = getToday()
        database!!.reference
                .child("newTimeTable")
                .orderByChild(studentOrTeacher)
                .equalTo(name)
                .addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (keysOfToday in dataSnapshot.children) {
                                    childrenInt++
                                }
                                idLessons = ArrayList(childrenInt + 1)
                                lessonList = ArrayList(childrenInt + 1)
                                for (keysOfToday in dataSnapshot.children) {
                                    getTimeTable(keysOfToday, todayStr, status)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                            }
                        }
                )
    }

    private fun getTimeTable(keysOfToday: DataSnapshot, todayStr: String, status: String) {
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        ref = database!!.getReference("newTimeTable").child(keysOfToday.key!!).child("dayOfWeek")
        database
        user
        ref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val lessonDay = dataSnapshot.getValue(String::class.java)

                if (lessonDay == todayStr) {
                    idLessons!!.add(keysOfToday.key)
                }
                setAdapterCount++

                if (setAdapterCount == childrenInt) {
                    for (idS in idLessons!!) {
                        ref = database!!.getReference("newTimeTable").child(idS!!)
                        ref!!.addValueEventListener(
                                object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        val lesson = Lesson()
                                        count = 0
                                        ref = database!!.getReference("newTimeTable").child(idS).child("TeacherName")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                teacherNameStr = value
                                            }
                                        }, ref!!)
/*
                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        teacherNameStr = dataSnapshot.value.toString()
                                                        lesson.teacherName = teacherNameStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })
*/



                                        ref = database!!.getReference("newTimeTable").child(idS).child("dayOfWeek")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                dayOfWeekStr = value
                                            }
                                        }, ref!!)
/*
                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        dayOfWeekStr = dataSnapshot.value.toString()
                                                        lesson.dayofweek = dayOfWeekStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })
*/

                                        ref = database!!.getReference("newTimeTable").child(idS).child("group")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                groupStr = value
                                            }
                                        }, ref!!)
/*                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        groupStr = dataSnapshot.value.toString()
                                                        lesson.groupName = groupStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })*/
                                        ref = database!!.getReference("newTimeTable").child(idS).child("groupOrSubGroup")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                groupOrSubGroupStr = value
                                            }
                                        }, ref!!)
  /*                                      ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        groupOrSubGroupStr = dataSnapshot.value.toString()
                                                        lesson.groupOrSubGroup = groupOrSubGroupStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })*/
                                        ref = database!!.getReference("newTimeTable").child(idS).child("lesson")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                lessonStr = value
                                            }
                                        }, ref!!)
                        /*                ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        lessonStr = dataSnapshot.value.toString()
                                                        lesson.lesson = lessonStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })*/
                                        ref = database!!.getReference("newTimeTable").child(idS).child("lessonNum")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                lessonNumString = value
                                            }
                                        }, ref!!)
/*                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        lessonNumString = dataSnapshot.value.toString()
                                                        lesson.lessonNum = lessonNumString

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })*/
                                        ref = database!!.getReference("newTimeTable").child(idS).child("lessonTime")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                lessonTimeStr = value
                                            }
                                        }, ref!!)
/*                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        lessonTimeStr = dataSnapshot.value.toString()
                                                        lesson.lessonTime = lessonTimeStr

                                                        countPlus()
                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {}
                                                })*/

                                        ref = database!!.getReference("newTimeTable").child(idS).child("room")
                                        getLessonParameter(object : MyCallback {
                                            override fun onCallback(value: String) {
                                                roomStr = value
                                            }
                                        }, ref!!)

/*                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        roomStr = dataSnapshot.value.toString()
                                                        lesson.roomNum = roomStr

                                                        countPlus()

                                                        if (count == 8) {
                                                            lessonList.add(lesson)
                                                            addLessonCount++
                                                            teacherNameStr = ""
                                                            dayOfWeekStr = ""
                                                            groupStr = ""
                                                            groupOrSubGroupStr = ""
                                                            lessonStr = ""
                                                            lessonNumString = ""
                                                            lessonTimeStr = ""
                                                            roomStr = ""

                                                            if (addLessonCount == idLessons!!.size) {
                                                                setAdapter(status, lessonList)
                                                            }
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {
                                                    }
                                                })*/
                                        lesson.teacherName = teacherNameStr
                                        lesson.dayofweek = dayOfWeekStr
                                        lesson.groupName = groupStr
                                        lesson.groupOrSubGroup = groupOrSubGroupStr
                                        lesson.lesson = lessonStr
                                        lesson.lessonNum = lessonNumString
                                        lesson.lessonTime = lessonTimeStr
                                        lesson.roomNum = roomStr

                                        if (addLessonCount == idLessons!!.size) {
                                            setAdapter(status, lessonList)
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                    }
                                })
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Error on getting Status", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getLessonParameter(callback: MyCallback, ref: DatabaseReference) {
        ref.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.value.toString()
                        callback.onCallback(value)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    private fun getLessonKeys(dataSnapshot: DataSnapshot) {
        idLessons!!.add(dataSnapshot.key)
    }

    private fun countPlus() {
        count++
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

    private fun setFeedbackView() {
        try {
            //Xijwiodfaskfoen334
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            database!!.reference
                    .child("newTimeTable")
                    .orderByChild("group")
                    .equalTo(groupName())
                    .addListenerForSingleValueEvent(
                            object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    var childrenInt = 0
                                    for (childInt in dataSnapshot.children) {
                                        childrenInt++
                                    }
                                    idLessons = ArrayList(childrenInt)
                                    lessonList = ArrayList(childrenInt)
                                    for (childSnap in dataSnapshot.children) {
                                        idLessons!!.add(childSnap.key)
                                    }
                                    var setAdapterInt = 0
                                    var sortInt = 0
                                    for (sortedInt in 0..childrenInt) {
                                        database!!.reference.child("newTimeTable")
                                                .orderByChild("lessonNum")
                                                .equalTo(sortedInt.toString()).addValueEventListener(
                                                        object : ValueEventListener {
                                                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                                sortInt++
                                                                if (sortInt == childrenInt) {


                                                                }
                                                            }

                                                            override fun onCancelled(error: DatabaseError) {
                                                            }
                                                        })
                                    }
                                    for (idS in idLessons!!) {
                                        ref = database!!.getReference("newTimeTable").child(idS!!).child("lessonNum")
                                        ref!!.addValueEventListener(
                                                object : ValueEventListener {
                                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                        val lessonNumStr = dataSnapshot.getValue(String::class.java)
                                                        val lesson = Lesson()
                                                        lesson.lessonNum = lessonNumStr!!
                                                        lessonList.add(lesson)
                                                        setAdapterInt++
                                                        if (setAdapterInt == childrenInt) {
/*
                                                            setAdapter(lessonList)
*/
                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {
                                                    }
                                                })
                                    }

                                }

                                override fun onCancelled(error: DatabaseError) {
                                }
                            }
                    )
            /*       ref!!.addValueEventListener(
                           object : ValueEventListener {
                               override fun onDataChange(dataSnapshot: DataSnapshot) {
                                   val str = dataSnapshot.getValue(String::class.java)
                                   tv.text = str
                               }

                               override fun onCancelled(error: DatabaseError) {
                               }
                           })*/

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    private fun setFakeLessons() {
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
    }

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