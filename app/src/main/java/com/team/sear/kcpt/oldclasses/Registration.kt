package com.team.sear.kcpt.oldclasses


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.team.sear.kcpt.PrivacyPolytics
import com.team.sear.kcpt.R
import com.team.sear.kcpt.timetablefragments.SelectTimeTableForApp

class Registration : AppCompatActivity(), View.OnClickListener {

    private var regBt: Button? = null
    private var signInBt: Button? = null
    private var loginEd: EditText? = null
    private var passwordEd: EditText? = null
    private var auth: FirebaseAuth? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    private lateinit var seePrivacyPolytics: TextView
    private lateinit var checkSeePrivacyPolytics: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        findView()
        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
                Toast.makeText(this@Registration, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
            this@Registration.updateUI(user)
        }
        isSeePrivacyPolyticsChecked()
        checkSeePrivacyPolytics.setOnCheckedChangeListener { _, _ ->
            isSeePrivacyPolyticsChecked()
        }
    }

    private fun isSeePrivacyPolyticsChecked() {
        if (checkSeePrivacyPolytics.isChecked) {
            regBt!!.visibility = View.VISIBLE
            signInBt!!.visibility = View.VISIBLE
        } else {
            regBt!!.visibility = View.GONE
            signInBt!!.visibility = View.GONE
        }
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        regBt!!.visibility = View.INVISIBLE
        signInBt!!.visibility = View.INVISIBLE
        Toast.makeText(this, "Регистрируем вас как: $email", Toast.LENGTH_SHORT).show()
        auth!!.createUserWithEmailAndPassword("$email@gmail.com", password)
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(
                                this@Registration, "Не получилось. Проверьте данные или соединение",
                                Toast.LENGTH_SHORT
                        ).show()
                        regBt!!.visibility = View.VISIBLE
                        signInBt!!.visibility = View.VISIBLE
                    }
                }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        regBt!!.visibility = View.INVISIBLE
        signInBt!!.visibility = View.INVISIBLE
        Toast.makeText(this, "Выполняется вход как:  $email", Toast.LENGTH_SHORT).show()
        auth!!.signInWithEmailAndPassword("$email@gmail.com", password)
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(
                                this@Registration, "Не получилось. Проверьте данные или соединение",
                                Toast.LENGTH_SHORT
                        ).show()
                        regBt!!.visibility = View.VISIBLE
                        signInBt!!.visibility = View.VISIBLE
                    }
                }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.regBt -> createAccount(loginEd!!.text.toString(), passwordEd!!.text.toString())
            R.id.signIn -> signIn(loginEd!!.text.toString(), passwordEd!!.text.toString())
            R.id.seePrivacyPolytics -> {
                val privacyPolyticsIntent = Intent(this@Registration, PrivacyPolytics::class.java)
                startActivity(privacyPolyticsIntent)
            }
            else -> {
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        val email = loginEd!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            loginEd!!.error = "Неверно!"
            valid = false
        } else {
            loginEd!!.error = null
        }
        val password = passwordEd!!.text.toString()
        if (TextUtils.isEmpty(password)) {
            passwordEd!!.error = "Неверно!"
            valid = false
        } else {
            passwordEd!!.error = null
        }
        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val regGroupIntent = Intent(this, SelectTimeTableForApp::class.java)
            startActivity(regGroupIntent)
            finish()
        } else {
        }
    }

    private fun clickListen(button: Button) {
        button.setOnClickListener(this)
    }

    private fun clickListen(textView: TextView) {
        textView.setOnClickListener(this)
    }

    private fun findView() {
        regBt = findViewById(R.id.regBt)
        clickListen(regBt!!)
        signInBt = findViewById(R.id.signIn)
        clickListen(signInBt!!)
        loginEd = findViewById(R.id.loginEd)
        passwordEd = findViewById(R.id.passwordEd)
        seePrivacyPolytics = findViewById(R.id.seePrivacyPolytics)
        clickListen(seePrivacyPolytics)
        checkSeePrivacyPolytics = findViewById(R.id.checkSeePrivacyPolytics)
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