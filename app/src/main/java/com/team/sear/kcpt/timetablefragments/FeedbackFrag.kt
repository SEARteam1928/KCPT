package com.team.sear.kcpt.timetablefragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.team.sear.kcpt.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.team.sear.kcpt.Registration

@Suppress("UNUSED_EXPRESSION")
class FeedbackFrag : Fragment() {

    private var sendFeedback: Button? = null
    private var feedbackEd: EditText? = null
    private var feedbackTv: TextView? = null
    private var feedbackStr: String? = null
    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private var sayHello: String? = null
    private var randomHello: Int? = null

    private lateinit var v: View

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.feedback_fragment, container, false)
        feedbackEd = v.findViewById(R.id.feedbackEd)
        feedbackTv = v.findViewById(R.id.feedbackTV)
        sendFeedback = v.findViewById(R.id.sendFeedback)
        auth = FirebaseAuth.getInstance()
        authComplete()
        sendFeedback!!.setOnClickListener {
            sendFeedbackVoid()
            setFeedbackView()
        }
        try {
            val user = auth!!.currentUser
            if (user!!.email == "halitulla@gmail.com") {
                randomHello = (Math.random() * 5 + 1).toInt()
                sayHello = when (randomHello) {
                    1 -> "Привет, Газинурушка!!"
                    2 -> "Рад тебя видеть, Газинур!!"
                    3 -> "Слушаюсь, Хозяин!"
                    4 -> "Ты сегодня очень красивый, Газинур))"
                    else -> "Улыбнись, всё будет хорошо :З"
                }
                Toast.makeText(
                        context,
                        sayHello,
                        Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return v
    }

    private fun authComplete() {
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
                Toast.makeText(activity, "Вам нужно войти или зарегистрироваться", Toast.LENGTH_SHORT).show()
            }
            setFeedbackView()
        }
    }

    private fun sendFeedbackVoid() {
        database = FirebaseDatabase.getInstance()
        feedbackStr = feedbackEd!!.text.toString().trim { it <= ' ' }
        try {
            if(feedbackStr=="signOutOnlyAdminRoot1928"){
                database = FirebaseDatabase.getInstance()
                user = auth!!.currentUser
                ref = database!!.getReference("exitInAccount").child(user!!.uid)
                ref!!.setValue("SIGNEDOUT")

                database = FirebaseDatabase.getInstance()
                user = auth!!.currentUser
                ref = database!!.getReference("users").child(user!!.uid).child("signedIn")
                ref!!.setValue("SIGNEDOUT")

                auth!!.signOut()
                val regIntent = Intent(context, Registration::class.java)
                startActivity(regIntent)
                activity!!.finish()
            }else {
                user = auth!!.currentUser
                ref = database!!.getReference("users").child(user!!.uid).child("feedback")
                ref!!.setValue(feedbackStr)
                feedbackEd!!.setText("")
                Toast.makeText(activity, "Отзыв сохранён или изменён!", Toast.LENGTH_SHORT).show()
            }} catch (e: Exception) {
            e.printStackTrace()
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

    private fun setFeedbackView() {
        try {
            database = FirebaseDatabase.getInstance()
            user = auth!!.currentUser
            ref = database!!.getReference("users").child(user!!.uid).child("feedback")
            ref!!.addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            feedbackStr = dataSnapshot.getValue(String::class.java)
                            feedbackTv!!.text = feedbackStr
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}
