package com.team.sear.kcpt


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.objects.SearchModel
import com.team.sear.kcpt.timetablefragments.MainActivity
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


class ProfileFrag : Fragment(), View.OnClickListener {
    private var v: View? = null
    private var studentProfileBt: ImageView? = null
    private var teacherProfileBt: ImageView? = null
    private var setDateBt: Button? = null
    private var anybodyNameTv: TextView? = null
    var arr: ArrayList<String>? = null

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        try{
        anybodyNameTv = v!!.findViewById(R.id.anybodyNameTv)
        studentProfileBt = v!!.findViewById(R.id.studentProfileBt)
        studentProfileBt!!.setOnClickListener(this)
        teacherProfileBt = v!!.findViewById(R.id.teacherProfileBt)
        teacherProfileBt!!.setOnClickListener(this)
        setDateBt = v!!.findViewById(R.id.setDateBt)
        setDateBt!!.setOnClickListener(this)
        arr = ArrayList()
        auth = FirebaseAuth.getInstance()
        authComplete()
        } catch (e: Exception) {
        }
        return v
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                updateAnybodyName()
            } else {
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.studentProfileBt -> {
                arr!!.clear()
                setFeedbackView("Группы")
            }
            R.id.teacherProfileBt -> {
                arr!!.clear()
                setFeedbackView("Преподаватели")
            }
            R.id.setDateBt ->{
                searchDialog(initDate())
            }
            else -> {
            }
        }
    }

    private fun sendDay(item: String){
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user!!.uid)
                .child("today")
        ref!!.setValue(item)
    }

    private fun updateAnybodyName(){
        database = FirebaseDatabase.getInstance()
        user = auth!!.currentUser
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user!!.uid)
                .child("groupOrTeacherName")

        ref!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                anybodyNameTv!!.text = datasnapshot.getValue(String::class.java)
            }

        })
    }

    private fun searchDialog(data: ArrayList<SearchModel>, status: String) {
        SimpleSearchDialogCompat(context, "Поиск", "Что вы хотите найти?", null,
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
            activity!!.finish()
            baseSearchDialogCompat.dismiss()
        }).show()
    }

    private fun searchDialog(data: ArrayList<SearchModel>) {
        SimpleSearchDialogCompat(context, "Поиск", "Что вы хотите найти?", null,
                data, SearchResultListener { baseSearchDialogCompat, item, _ ->
            sendDay(item!!.title)
            intentOnRecycler()
            activity!!.finish()
            baseSearchDialogCompat.dismiss()
        }).show()
    }

    private fun intentOnRecycler(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
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

    private fun getItemIndex(arr: ArrayList<String>): Int {
        return arr.size
    }
    private fun initDate(): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            it.add(SearchModel("Понедельник"))
            it.add(SearchModel("Вторник"))
            it.add(SearchModel("Среда"))
            it.add(SearchModel("Четверг"))
            it.add(SearchModel("Пятница"))
            it.add(SearchModel("Суббота"))
        }
    }

    private fun initData(array: ArrayList<String>): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            for (i in array) {
                it.add(SearchModel(i))
            }
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
