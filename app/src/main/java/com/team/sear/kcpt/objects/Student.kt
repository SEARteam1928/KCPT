package com.team.sear.kcpt.objects

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Student {
    internal var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    private var myRef2: DatabaseReference? = null
    private var myRef1: DatabaseReference? = null
    private var currentGroup: String? = null
    private var timetable: String? = null
    private var allGroupTimtetable: String? = null
    private var firstSubGroupTimetable: String? = null
    private var secondSubGroupTimtetable: String? = null

    private fun getTimeTablePrivate(
            day: String,
            lesson: String,
            ln: LinearLayout,
            mAuth: FirebaseAuth,
            tv: TextView
    ) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        myRef = database.getReference("users").child(user!!.uid).child("group")
        val finalDatabase = arrayOf(database)
        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                currentGroup = dataSnapshot.getValue(String::class.java)

                finalDatabase[0] = FirebaseDatabase.getInstance()
                myRef = finalDatabase[0].getReference("timetableNew").child("groups").child(currentGroup!!).child(day)
                        .child(lesson).child("allGroup").child("lesson")
                myRef!!.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        allGroupTimtetable = dataSnapshot.getValue(String::class.java)

                        if (allGroupTimtetable != null && allGroupTimtetable != "") {
                            tv.text = allGroupTimtetable as String
                            ln.visibility = View.VISIBLE
                        } else {
                            finalDatabase[0] = FirebaseDatabase.getInstance()
                            myRef1 = finalDatabase[0].getReference("timetableNew").child("groups").child(currentGroup!!).child(day)
                                    .child(lesson).child("subGroup1").child("lesson")
                            myRef1!!.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot1: DataSnapshot) {
                                    firstSubGroupTimetable = dataSnapshot1.getValue(String::class.java)


                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    tv.text = "Ошибка загрузки"
                                    ln.visibility = View.VISIBLE
                                }
                            })
                            myRef2 = finalDatabase[0].getReference("timetableNew").child("groups").child(currentGroup!!).child(day)
                                    .child(lesson).child("subGroup2").child("lesson")
                            myRef2!!.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot2: DataSnapshot) {
                                    secondSubGroupTimtetable = dataSnapshot2.getValue(String::class.java)

                                    if (firstSubGroupTimetable != null && firstSubGroupTimetable != "" && secondSubGroupTimtetable != null && secondSubGroupTimtetable != "") {
                                        timetable = "1. $firstSubGroupTimetable\n2. $secondSubGroupTimtetable"
                                        tv.text = timetable
                                        ln.visibility = View.VISIBLE
                                    } else {
                                        if (firstSubGroupTimetable != null && firstSubGroupTimetable != "") {
                                            timetable = "1. $firstSubGroupTimetable"
                                            tv.text = timetable
                                            ln.visibility = View.VISIBLE
                                        }
                                        if (secondSubGroupTimtetable != null && secondSubGroupTimtetable != "") {
                                            timetable = "2. $secondSubGroupTimtetable"
                                            tv.text = timetable
                                            ln.visibility = View.VISIBLE
                                        }
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    tv.text = "Ошибка загрузки"
                                    ln.visibility = View.VISIBLE
                                }
                            })
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        tv.text  = "Ошибка загрузки"
                        ln.visibility = View.VISIBLE
                    }
                })


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun getTimeTableStudent(day: String, lesson: String, ln: LinearLayout, mAuth: FirebaseAuth, tv: TextView) {
        getTimeTablePrivate(day, lesson,  ln, mAuth, tv)
    }
}
