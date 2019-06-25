package com.team.sear.kcpt.timetablefragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.team.sear.kcpt.R
import com.team.sear.kcpt.WeatherFrag
import com.team.sear.kcpt.objects.SelectTimeTable

class TimeTableActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var dfrag: DevelopersFrag
    private lateinit var weatherFrag: WeatherFrag
    private lateinit var newsFrag: NewsFrag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)


        dfrag = DevelopersFrag()
        weatherFrag = WeatherFrag()
        newsFrag = NewsFrag()

        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBottom.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()
        when(id){
            R.id.bottom_dev_item -> ftrans.replace(R.id.bottom_nav_container, dfrag)
            R.id.bottom_weather_item -> ftrans.replace(R.id.bottom_nav_container, weatherFrag)
            R.id.bottom_news_item -> ftrans.replace(R.id.bottom_nav_container, newsFrag)
            else ->{

            }
        }
        ftrans.commit()
        return true
    }

}
