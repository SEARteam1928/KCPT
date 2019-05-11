package com.team.sear.kcpt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.team.sear.kcpt.timetablefragments.SelectTimeTableForApp

class RegistrationNew : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_new)
        auth = FirebaseAuth.getInstance()

        Toast.makeText(
                this@RegistrationNew, "Подождите!",
                Toast.LENGTH_SHORT
        ).show()
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
            }
            this@RegistrationNew.updateUI(user)
        }
    }

    private fun createAccount() {
        auth!!.createUserWithEmailAndPassword(login(), password())
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        signIn()
                    }
                    Toast.makeText(
                            this@RegistrationNew, "Добро пожаловать!",
                            Toast.LENGTH_SHORT
                    ).show()
                }
    }

    private fun signIn() {
        auth!!.signInWithEmailAndPassword(login(), password())
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(
                                this@RegistrationNew, "обратитесь к разработчикам",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val regGroupIntent = Intent(this, SelectTimeTableForApp::class.java)
            startActivity(regGroupIntent)
            finish()
        } else {
            createAccount()

        }
    }

    private fun login():String {
        val loginString: String

        val firstInt: String= (Math.random()* 9999999).toInt().toString()
        val secondInt: String = (Math.random()* 99999999).toInt().toString()

        loginString = firstInt+secondInt+"reg@gmail.com"

        return loginString
    }

    private fun password():String {
        val passwordString: String

        val firstInt: String= (Math.random()* 9999999).toInt().toString()
        val secondInt: String = (Math.random()* 99999999).toInt().toString()

        passwordString = firstInt+secondInt+"password"

        return passwordString
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
