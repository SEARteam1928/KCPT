package com.team.sear.kcpt.timetablefragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.team.sear.kcpt.R
import com.team.sear.kcpt.objects.ChangesParser

class UserChanges : Fragment() {

    @SuppressLint("StaticFieldLeak")
    private lateinit var v: View
    @SuppressLint("StaticFieldLeak")
    private var webChanges: WebView? = null
    @SuppressLint("StaticFieldLeak")
    private lateinit var userChangesTv: TextView
    private lateinit var changesParser: ChangesParser
    private lateinit var changesHTML: String
    private lateinit var webSettings: WebSettings
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var database: FirebaseDatabase
    private var myRef: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private lateinit var groupName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.user_changes_frag, container, false)

        try {
            changesParser = ChangesParser()
            userChangesTv = v.findViewById(R.id.userChangesTv)
            webChanges = v.findViewById(R.id.userWebChanges)
            webChanges!!.settings.javaScriptEnabled
            webChanges!!.settings.builtInZoomControls
            webChanges!!.settings.supportZoom()
            webChanges!!.settings.displayZoomControls
            webChanges!!.settings.loadWithOverviewMode
            webChanges!!.settings.defaultFixedFontSize = 15
            webChanges!!.settings.setAppCacheMaxSize(20 * 1024 * 1024)
            webChanges!!.settings.setAppCachePath(context!!.cacheDir.absolutePath)
            webChanges!!.settings.allowFileAccess
            webChanges!!.settings.setAppCacheEnabled(true)
            webChanges!!.settings.cacheMode = WebSettings.LOAD_DEFAULT

            webSettings = webChanges!!.settings
            webSettings.defaultTextEncodingName = "utf-8"

            mAuth = FirebaseAuth.getInstance()
            authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                } else {
                }
            }

            if (!DetectConnection.checkInternetConnection(this.context)) {
                Toast.makeText(context, "Отсутствует подключение!", Toast.LENGTH_SHORT).show()
                webChanges!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            }else {
                UserChangesParser().execute()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        }
        return v
    }

    object DetectConnection {
        fun checkInternetConnection(context: Context?): Boolean {

            val conManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return (conManager.activeNetworkInfo != null
                    && conManager.activeNetworkInfo!!.isAvailable
                    && conManager.activeNetworkInfo!!.isConnected)
        }
    }

    private fun getGroupName() {
        database = FirebaseDatabase.getInstance()
        user = mAuth!!.currentUser
        try {
            myRef = database.getReference("Учреждения")
                    .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                    .child("users")
                    .child(user!!.uid)
                    .child("groupOrTeacherName")
            myRef!!.addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            groupName = dataSnapshot.getValue(String::class.java)!!
                            GetGroupName().execute()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            groupName = ""
                            GetGroupName().execute()
                        }
                    })
        } catch (e: Exception) {
            groupName = ""
            GetGroupName().execute()
        }

    }

    private fun setGroupName(): String {
        return when (groupName) {
            "AT1609" -> "АТ 16-09"
            "AT1709" -> "АТ 17-09"
            "AT1711" -> "АТ 17-11"
            "AT1811" -> "АТ 18-11"
            "ATPiP1509" -> "АТПиП 15-09"
            "ATPiP1609" -> "АТПиП 16-09"
            "ATPiP1611" -> "АТПиП 16-11"
            "DO15091" -> "ДО 15-09-1"
            "DO15092" -> "ДО 15-09-2"
            "DO1611" -> "ДО 16-11"
            "DO17111" -> "ДО 17-11-1"
            "DO17112" -> "ДО 17-11-2"
            "DO18111" -> "ДО 18-11-1"
            "DO18112" -> "ДО 18-11-2"
            "KP16111" -> "КП 16-11-1"
            "KP16112" -> "КП 16-11-2"
            "KP1709" -> "КП 17-09"
            "KP17111" -> "КП 17-11-1"
            "KP17112" -> "КП 17-11-2"
            "KP17113" -> "КП 17-11-3"
            "KP18111" -> "КП 18-11-1"
            "KP18112" -> "КП 18-11-2"
            "KS1611" -> "КС 16-11"
            "OSATPiP1711" -> "ОСАТПиП 17-11"
            "OSATPiP18111" -> "ОСАТПиП 18-11-1"
            "OSATPiP18112" -> "ОСАТПиП 18-11-2"
            "PDOTT1509" -> "ПДО ТТ 15-09"
            "PDOTT1609" -> "ПДО ТТ 16-09"
            "PDOTT1709" -> "ПДО ТТ 17-09"
            "PDOTT18111" -> "ПДО ТТ 18-11-1"
            "PDOTT18112" -> "ПДО ТТ 18-11-2"
            "SSA1711" -> "ССА 17-11"
            "SSA18111" -> "ССА 18-11-1"
            "SSA18112" -> "ССА 18-11-2"
            "SHO15091" -> "ШО 15-09-1"
            "SHO15092" -> "ШО 15-09-2"
            else -> {
                groupName
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class GetGroupName : AsyncTask<String, Void, String>() {
        @SuppressLint("SetTextI18n", "WrongThread")
        override fun doInBackground(vararg result: String?): String? {
            return try {
                changesParser.selectGroup(setGroupName())
                changesHTML = changesParser.parseChanges()!!
                changesHTML
            } catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
                Toast.makeText(context, "Не удалось", Toast.LENGTH_SHORT).show()
                userChangesTv.text = "Произошла неизвестная ошибка!"
                null
            }
        }

        override fun onPostExecute(result: String?) {
            try {
                webChanges!!.loadDataWithBaseURL(null,changesHTML,"text/html","utf-8",null)
                userChangesTv.visibility = View.INVISIBLE
            } catch (e: Exception) {
                userChangesTv.text = "Произошла неизвестная ошибка!"
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class UserChangesParser : AsyncTask<Void, Void, Void>() {
        @SuppressLint("SetTextI18n")
        override fun doInBackground(vararg result: Void?): Void? {
            try {
                getGroupName()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
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
}
