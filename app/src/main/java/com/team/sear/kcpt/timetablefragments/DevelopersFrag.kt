package com.team.sear.kcpt.timetablefragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.team.sear.kcpt.OneMinuteGame
import com.team.sear.kcpt.R

class DevelopersFrag : Fragment() {

    private lateinit var alpha: Animation
    private var searlogo: ImageView? = null
    private var v: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.developers_fragment, container, false)
        searlogo = v!!.findViewById(R.id.sear_logo)
        alpha = AnimationUtils.loadAnimation(context, R.anim.splash_alpha)
        searlogo!!.visibility = View.INVISIBLE
        searlogo!!.startAnimation(alpha)
        Handler().postDelayed({
            searlogo!!.visibility = View.VISIBLE
        }, 800)
        searlogo!!.setOnClickListener {
            val gameIntent = Intent(context, OneMinuteGame::class.java)
            startActivity(gameIntent)
        }
        return v
    }
}
