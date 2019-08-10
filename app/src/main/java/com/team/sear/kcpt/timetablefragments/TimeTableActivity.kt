package com.team.sear.kcpt.timetablefragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.widget.Toast
import com.team.sear.kcpt.*
import com.team.sear.kcpt.timetablePackage.RecyclerTimeTable

class TimeTableActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var recyclerTimeTable: RecyclerTimeTable
    private lateinit var moreFrag: MoreFrag
    private lateinit var profileFrag: ProfileFrag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        try {
            recyclerTimeTable = RecyclerTimeTable()
            moreFrag = MoreFrag()
            profileFrag = ProfileFrag()

        } catch (e: Exception) {
            Toast.makeText(this@TimeTableActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }

        try {
            val ftrans = supportFragmentManager.beginTransaction()
            ftrans.replace(R.id.bottom_nav_container, moreFrag)
            ftrans.commit()
        } catch (e: Exception) {
            Toast.makeText(this@TimeTableActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }

        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBottom.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()
        when (id) {
            R.id.bottom_today_item -> ftrans.replace(R.id.bottom_nav_container, recyclerTimeTable)
            R.id.bottom_main_item -> ftrans.replace(R.id.bottom_nav_container, moreFrag)
            R.id.bottom_profile_item -> ftrans.replace(R.id.bottom_nav_container, profileFrag)
            else -> {
            }
        }
        ftrans.commit()
        return true
    }

}
