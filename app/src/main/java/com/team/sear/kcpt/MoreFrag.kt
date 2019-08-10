package com.team.sear.kcpt

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.sear.kcpt.databinding.FragmentMoreBinding

class MoreFrag : Fragment(), View.OnClickListener {

    private var bind: FragmentMoreBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false)


        return bind!!.root
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.timetableITV -> onTimetableITVClick()
            R.id.changesITV -> onChangesITVClick()
            R.id.zvonkiITV -> onZvonkiITVClick()
            R.id.weatherITV -> onWeatherITVClick()
            R.id.newsITV -> onNewsITVClick()
            R.id.developersITV -> onDevelopersITVClick()
            R.id.feedbackITV -> onFeedbackITVClick()
            else -> {
            }
        }
    }

    private fun onTimetableITVClick() {

    }

    private fun onChangesITVClick() {

    }

    private fun onZvonkiITVClick() {

    }

    private fun onWeatherITVClick() {

    }

    private fun onNewsITVClick() {

    }

    private fun onDevelopersITVClick() {

    }

    private fun onFeedbackITVClick() {
        startIntent(OneMinuteGame())
    }

    private fun startIntent(activity: AppCompatActivity){
        val intent = Intent(context,activity::class.java)
        startActivity(intent)
    }
}

