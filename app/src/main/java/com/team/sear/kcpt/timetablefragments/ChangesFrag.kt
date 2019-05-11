package com.team.sear.kcpt.timetablefragments

import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import com.team.sear.kcpt.R
import android.content.Context
import android.webkit.WebSettings


class ChangesFrag : Fragment() {

    private lateinit var v: View
    private lateinit var webChanges: WebView
/*
    private lateinit var storage: FirebaseStorage
    private lateinit var storageRef: StorageReference
    private lateinit var changesImage: PhotoView
    private lateinit var changesLoadTv: TextView
*/

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.changes_fragment, container, false)
        Toast.makeText(context, "Загружаем изменения...", Toast.LENGTH_SHORT).show()
        try {

            webChanges = v.findViewById(R.id.webChanges)
            webChanges.settings.javaScriptEnabled
            webChanges.settings.builtInZoomControls
            webChanges.settings.supportZoom()
            webChanges.settings.displayZoomControls
            webChanges.settings.loadWithOverviewMode
            webChanges.settings.defaultFixedFontSize = 15
            webChanges.settings.setAppCacheMaxSize(20 * 1024 * 1024)
            webChanges.settings.setAppCachePath(context!!.cacheDir.absolutePath)
            webChanges.settings.allowFileAccess
            webChanges.settings.setAppCacheEnabled(true)
            webChanges.settings.cacheMode = WebSettings.LOAD_DEFAULT

            if (!DetectConnection.checkInternetConnection(this.context)) {
                Toast.makeText(context, "Отсутствует подключение!", Toast.LENGTH_SHORT).show()
                webChanges.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            }

            webChanges.loadUrl("https://docs.google.com/document/d/e/2PACX-1vS2ehAErYyAWY-cm247Pt4oT2YVAkEMwiYXhFu0pxGexUne1PTWNiWS0ktvlglRQqNpLtolGzJjIlvc/pub")


            /*changesImage = v.findViewById(R.id.changesImage_frag)
            changesImage.visibility = View.GONE
            changesLoadTv = v.findViewById(R.id.changesLoadTv)
             storage = FirebaseStorage.getInstance()
              storageRef = storage.getReferenceFromUrl("gs://kcpt-1928.appspot.com")
              storageRef.child("changett.jpg")
                      .downloadUrl
                      .addOnSuccessListener { uri ->
                          Picasso.with(activity).load(uri)
                                  .into(changesImage)
                          changesLoadTv.visibility = View.GONE
                          changesImage.visibility = View.VISIBLE

                      }.addOnFailureListener {
                          changesLoadTv.text = "Ошибка! Проверьте подключение к интернету"
                      }*/
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
/*
            changesLoadTv.text = "Ошибка! Проверьте подключение к интернету"
*/
        }
        return v
    }

    object DetectConnection {
        fun checkInternetConnection(context: Context?): Boolean {

            val conManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return (conManager.activeNetworkInfo != null
                    && conManager.activeNetworkInfo.isAvailable
                    && conManager.activeNetworkInfo.isConnected)
        }
    }

}
