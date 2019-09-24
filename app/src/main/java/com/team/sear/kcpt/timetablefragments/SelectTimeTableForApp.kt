package com.team.sear.kcpt.timetablefragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.team.sear.kcpt.oldclasses.Navigate
import com.team.sear.kcpt.objects.SearchModel
import com.team.sear.kcpt.objects.SelectTimeTable
import com.team.sear.kcpt.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.timetablePackage.RecyclerTimeTable
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.select_time_table_for_app.*

class SelectTimeTableForApp : AppCompatActivity(), View.OnClickListener {

    private var groupSV: ScrollView? = null
    private lateinit var selectTimeTable: SelectTimeTable
    private lateinit var navigateIntent: Intent

    private var studentProfileBt: Button? = null
    private var teacherProfileBt: Button? = null

    var arr: ArrayList<String>? = null

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_time_table_for_app)
        selectTimeTable = SelectTimeTable()
        navigateIntent = Intent(this, Navigate::class.java)
        auth = FirebaseAuth.getInstance()
        arr = ArrayList()
        authComplete()
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                studentProfileBt = findViewById(R.id.studentStatusBt)
                studentProfileBt!!.setOnClickListener(this)
                teacherProfileBt = findViewById(R.id.teacherStatusBt)
                teacherProfileBt!!.setOnClickListener(this)
            } else {
            }
        }
    }

    private fun setSItem(groupStr: String) {
        selectTimeTable.setStudent(groupStr, auth)
        intentNavigate()
    }

    private fun setTItem(teacherName: String) {
        selectTimeTable.setTeacher(teacherName, auth)
        intentNavigate()
    }

    private fun initData(): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            it.add(SearchModel("Арефьев Е. А."))

        }
    }


    private fun intentNavigate() {
        val navigateIntent = Intent(this, Navigate::class.java)
        startActivity(navigateIntent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.studentStatusBt -> {
                arr!!.clear()
                setFeedbackView("Группы")
            }
            R.id.teacherStatusBt -> {
                arr!!.clear()
                setFeedbackView("Преподаватели")
            }
            else -> {
            }
        }
    }

    private fun intentOnRecycler(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener(authListener!!)
    }

    private fun setFeedbackView(status: String) {
        try {
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child(status)

            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(datasnapshot: DataSnapshot) {
                    for (i in datasnapshot.children) {
                        val str = i.getValue(String::class.java)
                        arr!!.add(str!!)
                    }
                    searchDialog(initData(arr!!), status)
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    private fun searchDialog(data: ArrayList<SearchModel>, status: String) {
        SimpleSearchDialogCompat(this, "Поиск", "Что вы хотите найти?", null,
                data, SearchResultListener { baseSearchDialogCompat, item, _ ->
            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("status")
            ref!!.setValue(status)

            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("groupOrTeacherName")
            ref!!.setValue(item.title)
            intentOnRecycler()
            finish()
            baseSearchDialogCompat.dismiss()
        }).show()
    }


    private fun initData(array: ArrayList<String>): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            for (i in array) {
                it.add(SearchModel(i))
            }
        }
    }
    public override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth!!.removeAuthStateListener(authListener!!)
        }
    }
}
