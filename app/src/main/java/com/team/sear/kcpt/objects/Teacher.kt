package com.team.sear.kcpt.objects

import android.view.View
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Teacher {
    private var myRef: DatabaseReference? = null
    private var teacherName: String? = null
    private var timtetable: String? = null
    private var ttStr: String? = null

    private fun getTimeTablePrivate(day: String, lesson: String, ln: LinearLayout, mAuth: FirebaseAuth) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val user: FirebaseUser? = mAuth.currentUser

        myRef = database.getReference("users").child(user!!.uid).child("teacherName")

        val finalDatabase = arrayOf(database)

        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                teacherName = dataSnapshot.getValue(String::class.java)
                finalDatabase[0] = FirebaseDatabase.getInstance()
                myRef = finalDatabase[0].getReference("timetableNew").child("teachers").child(teacherName!!).child(day)
                        .child(lesson).child("lesson")
                myRef!!.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        timtetable = dataSnapshot.getValue(String::class.java)
                       when {
                            timtetable != null && timtetable != "" -> {

                                ln.visibility = View.VISIBLE
                               timtetable as String
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        ttStr = "Ошибка загрузки"
                        ln.visibility = View.VISIBLE
                    }
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    fun getTimeTable(day: String, lesson: String, ttStr: String, ln: LinearLayout, mAuth: FirebaseAuth) {
        getTimeTablePrivate(day, lesson, ln, mAuth)
    }
}
