package com.team.sear.kcpt.objects


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SelectTimeTable {
    private var ref: DatabaseReference? = null
    private lateinit var status: String
    private fun selectTimeTableTeacherPrivate(
            name: String,
            auth: FirebaseAuth?
    ) {
        var database: FirebaseDatabase?
        var user: FirebaseUser? = auth!!.currentUser
        status = "TEACHER"
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("users").child(user!!.uid).child("status")
        ref!!.setValue(status)

        database = FirebaseDatabase.getInstance()
        user = auth.currentUser
        ref = database.getReference("users").child(user!!.uid).child("teacherName")
        ref!!.setValue(name)
    }

    fun setTeacher(
            name: String,
            auth: FirebaseAuth?
    ) {
        selectTimeTableTeacherPrivate(name, auth)
    }

    private fun selectTimeTableStudentPrivate(
            group: String,
            auth: FirebaseAuth?
    ) {
        var database: FirebaseDatabase?
        var user: FirebaseUser? = auth!!.currentUser
        status = "STUDENT"
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("users").child(user!!.uid).child("status")
        ref!!.setValue(status)

        database = FirebaseDatabase.getInstance()
        user = auth.currentUser
        ref = database.getReference("users").child(user!!.uid).child("group")
        ref!!.setValue(group)
    }

    fun setStudent(
            group: String,
            auth: FirebaseAuth?
    ) {
        selectTimeTableStudentPrivate(group, auth)
    }
}
