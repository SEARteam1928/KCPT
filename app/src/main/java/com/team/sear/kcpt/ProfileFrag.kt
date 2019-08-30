package com.team.sear.kcpt


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.team.sear.kcpt.objects.SearchModel
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


class ProfileFrag : Fragment(),View.OnClickListener {
    private var v: View? = null
    private var searchBt: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        searchBt = v!!.findViewById(R.id.searchProfileBt)
        searchBt!!.setOnClickListener(this)
        return v
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.searchProfileBt ->{
                SimpleSearchDialogCompat(context, "Поиск", "Что вы хотите найти?", null,
                        initData(), SearchResultListener { baseSearchDialogCompat, item, _ ->
                    Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                    baseSearchDialogCompat.dismiss()
                }).show()
            }
            else ->{

            }
        }
    }
    private fun setSItem(groupStr: String) {
  /*      selectTimeTable.setStudent(groupStr, auth)
        intentNavigate()*/
    }

    private fun setTItem(teacherName: String) {
/*        selectTimeTable.setTeacher(teacherName, auth)
        intentNavigate()*/
    }

    private fun initData(): ArrayList<SearchModel> {
        var arr: ArrayList<String> = ArrayList(8)
        arr.add("10")
        arr.add("SGDGDSF")
        arr.add("fgsdg")
        arr.add("@))@)!")
        arr.add("vcvxc")
        arr.add("32431")
        arr.add("x3344")
        arr.add("7")
        Handler().postDelayed(Runnable { Toast.makeText(context,"sgdfsd",Toast.LENGTH_SHORT).show() },3000)
        return ArrayList<SearchModel>().also {
            for (i in arr){
                it.add(SearchModel(i))
            }
        }
    }


}
