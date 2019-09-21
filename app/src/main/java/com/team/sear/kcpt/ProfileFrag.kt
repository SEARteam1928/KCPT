package com.team.sear.kcpt


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.objects.SearchModel
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


class ProfileFrag : Fragment(), View.OnClickListener {
    private var v: View? = null
    private var searchBt: Button? = null
    var arr: ArrayList<String>? = null

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        searchBt = v!!.findViewById(R.id.searchProfileBt)
        searchBt!!.setOnClickListener(this)
        arr = ArrayList()
        auth = FirebaseAuth.getInstance()
        authComplete()

        return v
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
            }
        }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.searchProfileBt -> {
                arr!!.clear()
                setFeedbackView("Группы")
/*
                putDataInArrayList()
*/
            }
            else -> {

            }
        }
    }

    private fun setSItem(groupStr: String) {
        /*      selectTimeTable.setStudent(groupStr, auth)
              intentNavigate()*/
    }

    private fun setTItem(teacherName: String) {
/*        selectTimeTable.setTeacher(teacherName, auth)
        intentNavigate()*/
    }

/*    private fun putDataInArrayList() {
        arr = ArrayList(8)
        arr!!.add("10")
        arr!!.add("SGDGDSF")
        searchDialog(initData())
    }*/

    private fun searchDialog(data: ArrayList<SearchModel>) {
        SimpleSearchDialogCompat(context, "Поиск", "Что вы хотите найти?", null,
                data, SearchResultListener { baseSearchDialogCompat, item, _ ->
            Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
            baseSearchDialogCompat.dismiss()
        }).show()
    }

    private fun setFeedbackView(status: String) {
        try {
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            ref = database!!.getReference("Учреждения").child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"").child(status)
            ref!!.addChildEventListener(object : ChildEventListener{
                override fun onChildAdded(datasnapshot: DataSnapshot, p1: String?) {
                    val str = datasnapshot.getValue(String::class.java)

                    arr!!.add(str!!)
                    searchDialog(initData(arr!!))
                }

                override fun onChildChanged(datasnapshot: DataSnapshot, p1: String?) {
                    val str = datasnapshot.getValue(String::class.java)
                    val index: Int = getItemIndex(arr!!)

                    arr!![index] = str!!
                }

                override fun onChildRemoved(datasnapshot: DataSnapshot) {
                }

                override fun onChildMoved(datasnapshot: DataSnapshot, p1: String?) {
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
    private fun getItemIndex(arr: ArrayList<String>): Int{ return arr.size}

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
