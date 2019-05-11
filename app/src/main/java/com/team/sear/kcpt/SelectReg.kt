package com.team.sear.kcpt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SelectReg : AppCompatActivity(), View.OnClickListener {

    private lateinit var newRegBt: Button
    private lateinit var oldRegBt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_reg)

        newRegBt = findViewById(R.id.newReg)
        oldRegBt = findViewById(R.id.oldReg)

        newRegBt.setOnClickListener(this@SelectReg)
        oldRegBt.setOnClickListener(this@SelectReg)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.oldReg -> {
                val oldIntent: Intent = Intent(this@SelectReg, Registration::class.java)
                startActivity(oldIntent)
            }
            R.id.newReg -> {
                val newIntent: Intent = Intent(this@SelectReg, RegistrationNew::class.java)
                startActivity(newIntent)
            }
            else->{
            }
        }
    }
}
