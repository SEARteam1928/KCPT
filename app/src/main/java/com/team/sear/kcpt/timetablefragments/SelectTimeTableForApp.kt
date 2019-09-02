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
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.select_time_table_for_app.*

class SelectTimeTableForApp : AppCompatActivity(), View.OnClickListener {
    private var AT1609: Button? = null


    private var groupSV: ScrollView? = null
    private var auth: FirebaseAuth? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var selectTimeTable: SelectTimeTable
    private lateinit var navigateIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_time_table_for_app)
        selectTimeTable = SelectTimeTable()
        navigateIntent = Intent(this, Navigate::class.java)
        findViewByID()
        setInvisible()
        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
            }
        }

        searchBt.setOnClickListener {
            SimpleSearchDialogCompat(this@SelectTimeTableForApp, "Поиск", "Что вы хотите найти?", null,
                    initData(), SearchResultListener { baseSearchDialogCompat, item, _ ->
                when {
                    item.title == "Арефьев Е. А." -> setTItem("ArefyevEA")

                }
                Toast.makeText(this@SelectTimeTableForApp, item.title, Toast.LENGTH_SHORT).show()
                baseSearchDialogCompat.dismiss()
            }).show()
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

    private fun findViewByID() {

        groupSV = findViewById(R.id.groupSVselect)
        AT1609 = findViewById(R.id.AT1609select)

    }

    private fun intentNavigate() {
        val navigateIntent = Intent(this, Navigate::class.java)
        startActivity(navigateIntent)
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.studentStatusBt -> setGroupVisible()
            R.id.AT1609select -> setSItem("AT1609")

        }
    }

    private fun setClickListen() {

        cL(AT1609!!)

    }

    private fun cL(bt: Button) {
        bt.setOnClickListener(this)
    }

    private fun setInvisible() {

        AT1609!!.visibility = View.GONE

    }


    private fun setGroupVisible() {
        AT1609!!.visibility = View.VISIBLE

    }

    public override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener(authListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth!!.removeAuthStateListener(authListener!!)
        }
    }
}
