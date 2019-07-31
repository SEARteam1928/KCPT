package com.team.sear.kcpt.timetablePackage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.R

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
            setFakeLessons()
            /*setFeedbackView()*/
        }
    }


    private fun groupName(): String {
        return "ССА 18-11-2"
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
                                                                if (sortInt==childrenInt){


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
                                                            setAdapter(lessonList)
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

    private fun setFakeLessons(){
        lessonList = ArrayList(13)
        lessonList.add(setLesson("1. Инженерная и компьютерная графика\n 2. Инженерная и компьютерная графика","1","mn","ССА 18-11-2","Литус А.А.\nПопов кто-то","999\n777","8:00\n8:01","allGroup"))
        lessonList.add(setLesson("1. Инженерная и компьютерная графика\n 2. Инженерная и компьютерная графика","2","mn","ССА 18-11-2","Литус А.А.\nПопов кто-то","999\n777","9:00\n9:01","allGroup"))
        lessonList.add(setLesson("Программирование","3","mn","ССА 18-11-2","Гуляев И. П.","414","10:00\n10:01","allGroup"))
        lessonList.add(setLesson("Программирование","4","mn","ССА 18-11-2","Гуляев И.П.","414","11:00\n11:01","allGroup"))
        lessonList.add(setLesson("Технологии физического уровня передачи данных","5","mn","ССА 18-11-2","Бородина С.В.","105","12:00\n12:01","allGroup"))
        lessonList.add(setLesson("Технологии физического уровня передачи данных","6","mn","ССА 18-11-2","Бородина С.В.","105","13:00\n13:01","allGroup"))
        lessonList.add(setLesson("Технические средства информатизации","7","mn","ССА 18-11-2","Полищук А.А.","415","14:00\n14:01","allGroup"))
        lessonList.add(setLesson("Технические средства информатизации","8","mn","ССА 18-11-2","Полищук А.А.","415","15:00\n15:01","allGroup"))
        lessonList.add(setLesson("Физическая культура","9","mn","ССА 18-11-2","Кто-то там","Спорт. Зал","16:00\n16:01","allGroup"))
        lessonList.add(setLesson("Физическая культура","10","mn","ССА 18-11-2","Еще кто-то там","Спорт. Зал","17:00\n17:01","allGroup"))
        lessonList.add(setLesson("Основы Теории Информации","11","mn","ССА 18-11-2","Полищук А. А.","415","18:00\n18:01","allGroup"))
        lessonList.add(setLesson("Основы Теории Информации","12","mn","ССА 18-11-2","Полищук А. А","415","19:00\n19:01","allGroup"))
        setAdapter(lessonList)
    }

    private fun setLesson(lesson: String, lessonNum: String, dayOfWeek: String, groupName: String, teacherName: String, roomNum: String, lessonTime: String, groupOrSubGroup: String): Lesson{
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

    private fun setAdapter(lessons: ArrayList<Lesson?>) {
        lessonRecycler.adapter = LessonAdapter(lessons)
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