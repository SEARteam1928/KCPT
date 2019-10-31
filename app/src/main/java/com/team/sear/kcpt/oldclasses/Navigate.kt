@file:Suppress("NAME_SHADOWING")

package com.team.sear.kcpt.oldclasses

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.team.sear.kcpt.NewTimeTableFrag
import com.team.sear.kcpt.R
import com.team.sear.kcpt.WeatherFrag
import com.team.sear.kcpt.objects.SearchModel
import com.team.sear.kcpt.objects.SelectTimeTable
import com.team.sear.kcpt.timetablefragments.*
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


class Navigate : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var selectTimeTable: SelectTimeTable
    /*    private lateinit var fab: FloatingActionButton
        */
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var mAuth: FirebaseAuth? = null
    internal var user: FirebaseUser? = null
    private lateinit var navigateIntent: Intent
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigate)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navigateIntent = Intent(this, Navigate::class.java)
        try {
            val ftrans = supportFragmentManager.beginTransaction()
            ftrans.commit()
        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }
        try {
            selectTimeTable = SelectTimeTable()

        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {

            }
            this@Navigate.updateUI(user)
        }

        /*  fab = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "В разработке...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigate, menu)
        return true
    }

    private fun intentNavigate() {
        val navigateIntent = Intent(this, Navigate::class.java)
        startActivity(navigateIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                val navigateIntent = Intent(this, Navigate::class.java)
                startActivity(navigateIntent)
                finish()
            }
            R.id.timetableItem -> {
                val ttIntent = Intent(this@Navigate,MainActivity::class.java)
                startActivity(ttIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData(): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            it.add(SearchModel("АТ 16-09"))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()

        when (id) {

            R.id.action_select_timetable -> {
                SimpleSearchDialogCompat(this@Navigate, "Поиск", "Что вы хотите найти?", null,
                        initData(), SearchResultListener { baseSearchDialogCompat, item, _ ->
                    when {
                        item.title == "Арефьев Е. А." -> {  selectTimeTable.setTeacher("Arefyef", mAuth)
                            intentNavigate()}

                    }
                    Toast.makeText(this@Navigate, item.title, Toast.LENGTH_SHORT).show()
                    baseSearchDialogCompat.dismiss()
                }).show()
            }
        }

        ftrans.commit()

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun signOut() {
        try {
            database = FirebaseDatabase.getInstance()
            user = mAuth!!.currentUser
            myRef = database.getReference("exitInAccount").child(user!!.uid)
            myRef.setValue("SIGNEDOUT")

            database = FirebaseDatabase.getInstance()
            user = mAuth!!.currentUser
            myRef = database.getReference("users").child(user!!.uid).child("signedIn")
            myRef.setValue("SIGNEDOUT")

            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            mAuth!!.signOut()

            finish()
        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            val registrationIntent = Intent(this, Registration::class.java)
            startActivity(registrationIntent)
            finish()
        }
    }
}
