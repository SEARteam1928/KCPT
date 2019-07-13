package com.team.sear.kcpt.timetablefragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.widget.Toast
import com.team.sear.kcpt.NewTimeTableFrag
import com.team.sear.kcpt.R
import com.team.sear.kcpt.WeatherFrag

class TimeTableActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var ttFragNew: NewTimeTableFrag
    private lateinit var zvonkiFrag: ZvonkiFrag
    private lateinit var changesFrag: ChangesFrag
    private lateinit var feedbackFrag: FeedbackFrag
    private lateinit var dfrag: DevelopersFrag
    private lateinit var weatherFrag: WeatherFrag
    private lateinit var newsFrag: NewsFrag


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)
        ttFragNew = NewTimeTableFrag()
     /*   try {

            val ftrans = supportFragmentManager.beginTransaction()
            ftrans.replace(R.id.navigate, ttFragNew)
            ftrans.commit()
        } catch (e: Exception) {
            Toast.makeText(this@TimeTableActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }*/
        try {
            zvonkiFrag = ZvonkiFrag()
            changesFrag = ChangesFrag()
            feedbackFrag = FeedbackFrag()
            dfrag = DevelopersFrag()
            weatherFrag = WeatherFrag()
            newsFrag = NewsFrag()

        } catch (e: Exception) {
            Toast.makeText(this@TimeTableActivity, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }

        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBottom.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()
        when(id){
            R.id.bottom_dev_item -> ftrans.replace(R.id.bottom_nav_container, dfrag)
            R.id.bottom_weather_item -> ftrans.replace(R.id.bottom_nav_container, ttFragNew)
            R.id.bottom_news_item -> ftrans.replace(R.id.bottom_nav_container, zvonkiFrag)
            else ->{

            }
        }
        ftrans.commit()
        return true
    }

}
