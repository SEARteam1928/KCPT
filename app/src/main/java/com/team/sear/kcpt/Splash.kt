package com.team.sear.kcpt

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class Splash : AppCompatActivity() {

    private lateinit var slide: Animation
    private lateinit var alpha: Animation
    private lateinit var slideSecond: Animation
    private lateinit var authAlpha: Animation
    private var navigateIntent: Intent? = null
    private var registrationIntent: Intent? = null
    internal var user: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    var binding: com.team.sear.kcpt.databinding.SplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.splash)

        navigateIntent = Intent(this, Navigate::class.java)
        registrationIntent = Intent(this, RegistrationNew::class.java)

        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        } catch (e: Exception) {
        }
        binding!!.splashBackgroundFirst.visibility = View.GONE
        binding!!.splashBackgroundSecond.visibility = View.GONE
        binding!!.splashTcpLogo.visibility = View.GONE
        binding!!.splashKcptText.visibility = View.GONE
        binding!!.splashStartWorking.visibility = View.GONE
        binding!!.splashStartWorking.text = "Загрузка..."
        slide = AnimationUtils.loadAnimation(this, R.anim.slide)
        alpha = AnimationUtils.loadAnimation(this, R.anim.splash_alpha)
        slideSecond = AnimationUtils.loadAnimation(this, R.anim.slide_second)
        authAlpha = AnimationUtils.loadAnimation(this, R.anim.aplha_0_1)
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            try {
                val user = firebaseAuth.currentUser
                if (user != null) {
                    startAnimInAuth()
                } else {
                    startAnimInNotAuth()
                }
            } catch (e: Exception) {
                Toast.makeText(this@Splash, "Undefinded error!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startAnimInAuth() {
        binding!!.splashBackgroundFirst.startAnimation(authAlpha)
        binding!!.splashBackgroundSecond.startAnimation(authAlpha)
        binding!!.splashStartWorking.startAnimation(authAlpha)
        binding!!.splashKcptText.startAnimation(authAlpha)
        binding!!.splashTcpLogo.startAnimation(authAlpha)
        Handler().postDelayed({
            binding!!.splashBackgroundFirst.visibility = View.VISIBLE
            binding!!.splashBackgroundFirst.visibility = View.VISIBLE
            binding!!.splashBackgroundSecond.visibility = View.VISIBLE
            binding!!.splashTcpLogo.visibility = View.VISIBLE
            binding!!.splashKcptText.visibility = View.VISIBLE
            binding!!.splashStartWorking.visibility = View.VISIBLE
            startActivity(navigateIntent)
            finish()
        }, 800)
    }

    private fun startAnimInNotAuth() {
        binding!!.splashBackgroundFirst.startAnimation(slide)
        binding!!.splashBackgroundFirst.visibility = View.VISIBLE
        Handler().postDelayed({
            binding!!.splashBackgroundSecond.startAnimation(slideSecond)
            binding!!.splashBackgroundSecond.visibility = View.VISIBLE
        }, 500)
        Handler().postDelayed({
            binding!!.splashTcpLogo.startAnimation(alpha)
            binding!!.splashKcptText.startAnimation(alpha)
            binding!!.splashStartWorking.startAnimation(alpha)
            binding!!.splashTcpLogo.visibility = View.VISIBLE
            binding!!.splashKcptText.visibility = View.VISIBLE
            binding!!.splashStartWorking.visibility = View.VISIBLE
            Handler().postDelayed({
                Handler().postDelayed({
                    startActivity(registrationIntent)
                    finish()
                }, 100)
            }, 800)
        }, 500)
    }

    public override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener!!)
        }
    }
}
