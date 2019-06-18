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

@Suppress("UNUSED_EXPRESSION")
class StatusDetermination {
    private var myRef: DatabaseReference? = null
    private var statusStr: String? = null
    private var student: Student? = null
    private var teacher: Teacher? = null

    private fun getUserStatusPrivate(
            day: String,
            lesson: String,
            ln: LinearLayout,
            mAuth: FirebaseAuth,
            tv: TextView
    ) {
        val database: FirebaseDatabase
        val user: FirebaseUser?
        student = Student()
        teacher = Teacher()
        try {
            database = FirebaseDatabase.getInstance()
            user = mAuth.currentUser
            myRef = database
                    .getReference("users")
                    .child(user!!.uid)
                    .child("status")
            database
            user
            myRef!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    statusStr = dataSnapshot.getValue(String::class.java)
                    if (statusStr == null || statusStr == "") {
                    }
                    if (statusStr == "STUDENT") {
                        student!!.getTimeTableStudent(day, lesson, ln, mAuth, tv)
                    }
                    if (statusStr == "TEACHER") {
                        teacher!!.getTimeTable(day, lesson, ln, mAuth, tv)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    tv.text = "Ошибка загрузки"
                    ln.visibility = View.VISIBLE
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getTimeTable(day: String, lesson: String,  ln: LinearLayout, mAuth: FirebaseAuth, tv: TextView) {
        getUserStatusPrivate(day, lesson,  ln, mAuth, tv)
    }
}