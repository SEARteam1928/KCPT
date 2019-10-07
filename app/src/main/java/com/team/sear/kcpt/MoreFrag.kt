package com.team.sear.kcpt

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.sear.kcpt.databinding.FragmentMoreBinding
import com.team.sear.kcpt.timetablefragments.*

class MoreFrag : Fragment(), View.OnClickListener {

    private var bind: FragmentMoreBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false)

        bind!!.changesITV.setOnClickListener(this)
        bind!!.timetableITV.setOnClickListener(this)
        bind!!.changesITV.setOnClickListener(this)
        bind!!.zvonkiITV.setOnClickListener(this)
        bind!!.weatherITV.setOnClickListener(this)
        bind!!.newsITV.setOnClickListener(this)
        bind!!.developersITV.setOnClickListener(this)
        bind!!.feedbackITV.setOnClickListener(this)
        return bind!!.root
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.timetableITV -> transact(NewTimeTableFrag())
            R.id.changesITV -> transact(ChangesFrag())
            R.id.zvonkiITV -> transact(ZvonkiFrag())
            R.id.weatherITV -> transact(WeatherFrag())
            R.id.newsITV -> transact(NewsFrag())
            R.id.developersITV -> transact(DevelopersFrag())
            R.id.feedbackITV -> transact(FeedbackFrag())
            else -> {
            }
        }
    }

    private fun transact(fragment: Fragment) {
        val ftrans = activity!!.supportFragmentManager.beginTransaction()
        ftrans.replace(R.id.bottom_nav_container, fragment)
        ftrans.commit()
    }

    private fun onTimetableITVClick() {
        transact(NewTimeTableFrag())
    }

    private fun onChangesITVClick() {
        transact(ChangesFrag())
    }

    private fun onZvonkiITVClick() {
        transact(ZvonkiFrag())
    }

    private fun onWeatherITVClick() {
        transact(WeatherFrag())
    }

    private fun onNewsITVClick() {
        transact(NewsFrag())
    }

    private fun onDevelopersITVClick() {
        transact(DevelopersFrag())
    }

    private fun onFeedbackITVClick() {
/*
        transact(FeedbackFrag())
*/
    }
}

