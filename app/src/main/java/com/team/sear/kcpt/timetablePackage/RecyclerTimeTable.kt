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
            setFeedbackView()
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

    private fun setAdapter(lessons: ArrayList<Lesson?>) {
        lessonRecycler.adapter = LessonAdapter(lessons)
    }
}