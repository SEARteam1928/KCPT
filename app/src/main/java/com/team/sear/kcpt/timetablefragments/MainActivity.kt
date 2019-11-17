package com.team.sear.kcpt.timetablefragments

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.*
import com.team.sear.kcpt.R
import com.team.sear.kcpt.timetablePackage.RecyclerTimeTable
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    private lateinit var recyclerTimeTable: RecyclerTimeTable
    private lateinit var changesFrag: ChangesFrag
    private lateinit var userChanges: UserChanges
    private lateinit var moreFrag: MoreFrag
    private lateinit var profileFrag: ProfileFrag

    private lateinit var mainFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        try {
            auth = FirebaseAuth.getInstance()
            authComplete()
            try {
                recyclerTimeTable = RecyclerTimeTable()
                changesFrag = ChangesFrag()
                userChanges = UserChanges()
                moreFrag = MoreFrag()
                profileFrag = ProfileFrag()

                mainFab = findViewById(R.id.mainFab)

            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
            }

            try {
                val ftrans = supportFragmentManager.beginTransaction()
                ftrans.replace(R.id.bottom_nav_container, recyclerTimeTable)
                ftrans.commit()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
            }

            val navBottom = findViewById<BottomNavigationView>(R.id.bottom_nav)
            navBottom.setOnNavigationItemSelectedListener(this)

            mainFab.setOnClickListener {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Обновляем", Toast.LENGTH_SHORT).show()
                finish()
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            user = firebaseAuth.currentUser
            if (user != null) {
                sendToday()
            } else {
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()
        when (id) {
            R.id.bottom_today_item -> ftrans.replace(R.id.bottom_nav_container, recyclerTimeTable)
            R.id.bottom_changes_item -> ftrans.replace(R.id.bottom_nav_container, userChanges)
/*
            R.id.bottom_changes_item -> ftrans.replace(R.id.bottom_nav_container, changesFrag)
*/
            R.id.bottom_main_item -> ftrans.replace(R.id.bottom_nav_container, moreFrag)
            R.id.bottom_profile_item -> ftrans.replace(R.id.bottom_nav_container, profileFrag)
            else -> {
            }
        }
        ftrans.commit()
        return true
    }
    private fun sendToday() {
        try {
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            ref = database!!.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("today")
            ref!!.setValue(getToday())
        } catch (e: Exception) {
            Toast.makeText(this, "Undefinded error!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getToday(): String {
        val dform = SimpleDateFormat("EEE")
        return when (dform.format(Calendar.getInstance().time)) {
            "вс" -> "Понедельник"
            "Sun" -> "Понедельник"
            "пн" -> "Понедельник"
            "Mon" -> "Понедельник"
            "вт" -> "Вторник"
            "Tues" -> "Вторник"
            "ср" -> "Среда"
            "Wed" -> "Среда"
            "чт" -> "Четверг"
            "Thurs" -> "Четверг"
            "пт" -> "Пятница"
            "Fri" -> "Пятница"
            "сб" -> "Суббота"
            "Sat" -> "Суббота"
            else -> {
                ""
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
