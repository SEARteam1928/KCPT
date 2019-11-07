package com.team.sear.kcpt

import android.annotation.SuppressLint
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

@SuppressLint("Registered")
class OneMinuteGame : AppCompatActivity(), View.OnClickListener {

    private lateinit var bt: Array<Button?>

    private lateinit var tv: TextView
    private lateinit var time_tv: TextView

    private lateinit var count_str: String
    private lateinit var time_str: String
    private var authListener: FirebaseAuth.AuthStateListener? = null

    var a: Int = 0
    var count: Int = 0
    private var time_int: Int = 0
    private lateinit var maxScoreUser: String

    private lateinit var database: FirebaseDatabase
    private var myRef: DatabaseReference? = null

    private var mAuth: FirebaseAuth? = null
    internal var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_minute_game)
        try {
            fVbyId()
            setAllInvis()
            setCl()

            time_int = 60
            time_str = time_int.toString()
            time_tv.text = time_str
            startTime()
            vis()

            count = 0
            count_str = count.toString()
            tv.text = count_str
            mAuth = FirebaseAuth.getInstance()
            authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                } else {
                }

            }
        } catch (e: Exception) {
            Toast.makeText(this@OneMinuteGame, "Игра еще не работает", Toast.LENGTH_SHORT).show()
        }
/*
        getMaxScore()
*/

    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(authListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            mAuth!!.removeAuthStateListener(authListener!!)
        }
    }

    private fun getMaxScore() {

        getUserMaxScore()
    }

    private fun getUserMaxScore() {
        this.database = FirebaseDatabase.getInstance()
        user = mAuth!!.currentUser
        try {
            myRef = database.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users").child(user!!.uid).child("oneMinuteGameScore")
            myRef!!.addValueEventListener(
                    object : ValueEventListener {
                        @SuppressLint("SetTextI18n")
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            try {
                                maxScoreUser = dataSnapshot.getValue(String::class.java)!!
                                time_tv.text = "Pекорд: $maxScoreUser"
                            } catch (e: KotlinNullPointerException) {
                                try {
                                    myRef!!.setValue("0")

                                } catch (e: Exception) {
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
        } catch (e: Exception) {
            myRef = database.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users").child(user!!.uid).child("oneMinuteGameScore")
            myRef!!.setValue("0")
        }
    }


    @SuppressLint("SetTextI18n")
    private fun startTime() {
        Handler().postDelayed({
            if (time_int <= 0) {
                /*  bt[a]!!.visibility = View.INVISIBLE
                  database = FirebaseDatabase.getInstance()
                  user = mAuth!!.currentUser
                  try {
                      getMaxScore()
                      Handler().postDelayed({
                          try {
                              if (count_str.toInt() > maxScoreUser.toInt()) {
                                  myRef = this.database.getReference("Учреждения")
                                          .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                                          .child("users").child(user!!.uid).child("oneMinuteGameScore")
                                  myRef!!.setValue(count_str)
                              }
                          } catch (e: Exception) {
                              myRef = database.getReference("Учреждения")
                                      .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                                      .child("users").child(user!!.uid).child("oneMinuteGameScore")
                              myRef!!.setValue("0")
                              if (count_str.toInt() > maxScoreUser.toInt()) {
                                  myRef = this.database.getReference("Учреждения")
                                          .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                                          .child("users").child(user!!.uid).child("oneMinuteGameScore")
                                  myRef!!.setValue(count_str)
                              }
                          }
                          getMaxScore()
                          time_tv.text = "Pекорд: $maxScoreUser"
                      }, 300)
                  } catch (e: Exception) {
                  }*/
            } else {
                time_int--
                time_str = time_int.toString()
                time_tv.text = String.format("%ssec", time_str)
                startTime()
            }
        }, 1000)
    }

    private fun setCount() {
        count++
        count_str = count.toString()
        tv.text = count_str
    }

    private fun vis() {
        a = (Math.random() * 56).toInt()
        bt[a]!!.visibility = View.VISIBLE
    }

    private fun clickedBt(btNum: Int) {
        bt[btNum]!!.visibility = View.INVISIBLE
        vis()
        setCount()
    }

    override fun onClick(v: View) {
        when (v.id) {
            /*     case R.id.one_minute_game_count_tv:
                Intent intent = new Intent(this,Chat.class);
                startActivity(intent);
                break;*/

            R.id.button57 -> clickedBt(55)

            R.id.button58 -> clickedBt(0)

            R.id.button59 -> clickedBt(1)

            R.id.button60 -> clickedBt(2)

            R.id.button61 -> clickedBt(3)

            R.id.button62 -> clickedBt(4)

            R.id.button63 -> clickedBt(5)

            R.id.button64 -> clickedBt(6)

            R.id.button65 -> clickedBt(7)

            R.id.button66 -> clickedBt(8)

            R.id.button67 -> clickedBt(9)

            R.id.button68 -> clickedBt(10)

            R.id.button69 -> clickedBt(11)

            R.id.button70 -> clickedBt(12)

            R.id.button71 -> clickedBt(13)

            R.id.button72 -> clickedBt(14)

            R.id.button73 -> clickedBt(15)

            R.id.button74 -> clickedBt(16)

            R.id.button75 -> clickedBt(17)

            R.id.button76 -> clickedBt(18)

            R.id.button77 -> clickedBt(19)

            R.id.button78 -> clickedBt(20)

            R.id.button79 -> clickedBt(21)

            R.id.button80 -> clickedBt(22)

            R.id.button81 -> clickedBt(23)

            R.id.button82 -> clickedBt(24)

            R.id.button83 -> clickedBt(25)

            R.id.button84 -> clickedBt(26)

            R.id.button85 -> clickedBt(27)

            R.id.button86 -> clickedBt(28)

            R.id.button87 -> clickedBt(29)

            R.id.button88 -> clickedBt(30)

            R.id.button89 -> clickedBt(31)

            R.id.button90 -> clickedBt(32)

            R.id.button91 -> clickedBt(33)

            R.id.button92 -> clickedBt(34)

            R.id.button93 -> clickedBt(35)

            R.id.button94 -> clickedBt(36)

            R.id.button95 -> clickedBt(37)

            R.id.button96 -> clickedBt(38)

            R.id.button97 -> clickedBt(39)

            R.id.button98 -> clickedBt(40)

            R.id.button99 -> clickedBt(41)

            R.id.button100 -> clickedBt(42)

            R.id.button101 -> clickedBt(43)

            R.id.button102 -> clickedBt(44)

            R.id.button103 -> clickedBt(45)

            R.id.button104 -> clickedBt(46)

            R.id.button105 -> clickedBt(47)

            R.id.button106 -> clickedBt(48)

            R.id.button107 -> clickedBt(49)

            R.id.button108 -> clickedBt(50)

            R.id.button109 -> clickedBt(51)

            R.id.button110 -> clickedBt(52)

            R.id.button111 -> clickedBt(53)

            R.id.button112 -> clickedBt(54)


            else -> {
            }
        }
    }

    private fun doInvis(btNum: Int) {
        bt[btNum]!!.visibility = View.INVISIBLE
    }

    private fun setAllInvis() {
        doInvis(0)
        doInvis(1)
        doInvis(2)
        doInvis(3)
        doInvis(4)
        doInvis(5)
        doInvis(6)
        doInvis(7)
        doInvis(8)
        doInvis(9)
        doInvis(10)
        doInvis(11)
        doInvis(12)
        doInvis(13)
        doInvis(14)
        doInvis(15)
        doInvis(16)
        doInvis(17)
        doInvis(18)
        doInvis(19)
        doInvis(20)
        doInvis(21)
        doInvis(22)
        doInvis(23)
        doInvis(24)
        doInvis(25)
        doInvis(26)
        doInvis(27)
        doInvis(28)
        doInvis(29)
        doInvis(30)
        doInvis(31)
        doInvis(32)
        doInvis(33)
        doInvis(34)
        doInvis(35)
        doInvis(36)
        doInvis(37)
        doInvis(38)
        doInvis(39)
        doInvis(40)
        doInvis(41)
        doInvis(42)
        doInvis(43)
        doInvis(44)
        doInvis(45)
        doInvis(46)
        doInvis(47)
        doInvis(48)
        doInvis(49)
        doInvis(50)
        doInvis(51)
        doInvis(52)
        doInvis(53)
        doInvis(54)
        doInvis(55)
    }

    private fun doCl(btNum: Int) {
        bt[btNum]!!.setOnClickListener(this)
    }

    private fun setCl() {
        doCl(0)
        doCl(1)
        doCl(2)
        doCl(3)
        doCl(4)
        doCl(5)
        doCl(6)
        doCl(7)
        doCl(8)
        doCl(9)
        doCl(10)
        doCl(11)
        doCl(12)
        doCl(13)
        doCl(14)
        doCl(15)
        doCl(16)
        doCl(17)
        doCl(18)
        doCl(19)
        doCl(20)
        doCl(21)
        doCl(22)
        doCl(23)
        doCl(24)
        doCl(25)
        doCl(26)
        doCl(27)
        doCl(28)
        doCl(29)
        doCl(30)
        doCl(31)
        doCl(32)
        doCl(33)
        doCl(34)
        doCl(35)
        doCl(36)
        doCl(37)
        doCl(38)
        doCl(39)
        doCl(40)
        doCl(41)
        doCl(42)
        doCl(43)
        doCl(44)
        doCl(45)
        doCl(46)
        doCl(47)
        doCl(48)
        doCl(49)
        doCl(50)
        doCl(51)
        doCl(52)
        doCl(53)
        doCl(54)
        doCl(55)

        tv.setOnClickListener(this)
    }

    private fun fVbyId() {
        tv = findViewById(R.id.one_minute_game_count_tv)
        time_tv = findViewById(R.id.time_one_minute_game_tv)

        bt = arrayOfNulls<Button?>(58)
        bt[0] = findViewById(R.id.button58)
        bt[1] = findViewById(R.id.button59)
        bt[2] = findViewById(R.id.button60)
        bt[3] = findViewById(R.id.button61)
        bt[4] = findViewById(R.id.button62)
        bt[5] = findViewById(R.id.button63)
        bt[6] = findViewById(R.id.button64)
        bt[7] = findViewById(R.id.button65)
        bt[8] = findViewById(R.id.button66)
        bt[9] = findViewById(R.id.button67)
        bt[10] = findViewById(R.id.button68)
        bt[11] = findViewById(R.id.button69)
        bt[12] = findViewById(R.id.button70)
        bt[13] = findViewById(R.id.button71)
        bt[14] = findViewById(R.id.button72)
        bt[15] = findViewById(R.id.button73)
        bt[16] = findViewById(R.id.button74)
        bt[17] = findViewById(R.id.button75)
        bt[18] = findViewById(R.id.button76)
        bt[19] = findViewById(R.id.button77)
        bt[20] = findViewById(R.id.button78)
        bt[21] = findViewById(R.id.button79)
        bt[22] = findViewById(R.id.button80)
        bt[23] = findViewById(R.id.button81)
        bt[24] = findViewById(R.id.button82)
        bt[25] = findViewById(R.id.button83)
        bt[26] = findViewById(R.id.button84)
        bt[27] = findViewById(R.id.button85)
        bt[28] = findViewById(R.id.button86)
        bt[29] = findViewById(R.id.button87)
        bt[30] = findViewById(R.id.button88)
        bt[31] = findViewById(R.id.button89)
        bt[32] = findViewById(R.id.button90)
        bt[33] = findViewById(R.id.button91)
        bt[34] = findViewById(R.id.button92)
        bt[35] = findViewById(R.id.button93)
        bt[36] = findViewById(R.id.button94)
        bt[37] = findViewById(R.id.button95)
        bt[38] = findViewById(R.id.button96)
        bt[39] = findViewById(R.id.button97)
        bt[40] = findViewById(R.id.button98)
        bt[41] = findViewById(R.id.button99)
        bt[42] = findViewById(R.id.button100)
        bt[43] = findViewById(R.id.button101)
        bt[44] = findViewById(R.id.button102)
        bt[45] = findViewById(R.id.button103)
        bt[46] = findViewById(R.id.button104)
        bt[47] = findViewById(R.id.button105)
        bt[48] = findViewById(R.id.button106)
        bt[49] = findViewById(R.id.button107)
        bt[50] = findViewById(R.id.button108)
        bt[51] = findViewById(R.id.button109)
        bt[52] = findViewById(R.id.button110)
        bt[53] = findViewById(R.id.button111)
        bt[54] = findViewById(R.id.button112)
        bt[55] = findViewById(R.id.button57)
    }

}
