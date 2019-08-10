package com.team.sear.kcpt.timetablefragments


import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.team.sear.kcpt.R
import java.io.UnsupportedEncodingException
import com.team.sear.kcpt.objects.news.News
import com.team.sear.kcpt.objects.news.NewsAdapter
import com.team.sear.kcpt.objects.news.NewsPage
import com.team.sear.kcpt.objects.news.NewsParser
import org.jsoup.nodes.Document
import java.util.*


class NewsFrag : Fragment(), View.OnClickListener {


    private lateinit var v: View

    private var arrayElementInt = 0
    private var elementInt = 0
    private var newsParser: NewsParser? = null

    private val newsList = ArrayList<News>()

    private lateinit var news: Array<News?>

    private var title: String? = null
    private var imageLink: String? = null
    private var date: String? = null
    private var description: String? = null
    private var moreLink: String? = null
    private var newsCount: Int = 5

    private lateinit var newsPage: NewsPage
    private lateinit var newsDoc: Document
    private lateinit var newsRecyclerView: RecyclerView

    private lateinit var nextNewsBt: Button
    private lateinit var prevNewsBt: Button
    private lateinit var scrollViewNews: ScrollView


    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_news, container, false)
        news = arrayOfNulls<News?>(newsCount)
        Toast.makeText(context, "Загрузка новостей, пожалуйста подождите...", Toast.LENGTH_LONG).show()
        newsRecyclerView = v.findViewById(R.id.news_recycler_view)
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        nextNewsBt = v.findViewById(R.id.nextNews)
        nextNewsBt.setOnClickListener(this)
        prevNewsBt = v.findViewById(R.id.prevNews)
        prevNewsBt.setOnClickListener(this)
        scrollViewNews = v.findViewById(R.id.newsScroll)

        setPrevBtVisibility()
        nextNewsBt.visibility = View.GONE

        NewsTask().execute()
        return v
    }

    @Throws(UnsupportedEncodingException::class)
    private fun setNewsList() {

        newsPage = NewsPage()
        newsDoc = newsPage.getPage()
        newsParser = NewsParser()

        while (arrayElementInt < newsCount) {

            getNewsList()

            news[arrayElementInt] = News()

            news[arrayElementInt]?.title = "  " + title!!
            news[arrayElementInt]?.imageLink = imageLink!!
            news[arrayElementInt]?.date = date!!
            news[arrayElementInt]?.description = description!!
            news[arrayElementInt]?.moreLink = moreLink!!

            newsList.add(news[arrayElementInt]!!)
            elementInt++
            arrayElementInt++
        }
        arrayElementInt = 0
    }

    private fun getNewsList() {
        title = newsParser!!.title(newsDoc, elementInt)
        imageLink = newsParser!!.imageLink(newsDoc, elementInt)
        date = newsParser!!.date(newsDoc, elementInt)
        description = newsParser!!.description(newsDoc, elementInt)
        moreLink = newsParser!!.moreLink(newsDoc, elementInt)
    }

    private fun clickPrevNewsButton() {
        elementInt -= 10
        NewsTask().execute()
        prevNewsBt.visibility = View.GONE
    }

    private fun clickNextNewsButton() {
        NewsTask().execute()
    }

    private fun setPrevBtVisibility() {
        when (elementInt) {
            0 -> prevNewsBt.visibility = View.GONE
            1 -> prevNewsBt.visibility = View.GONE
            2 -> prevNewsBt.visibility = View.GONE
            3 -> prevNewsBt.visibility = View.GONE
            4 -> prevNewsBt.visibility = View.GONE
            5 -> prevNewsBt.visibility = View.GONE
            else -> {
                prevNewsBt.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.prevNews -> clickPrevNewsButton()
            R.id.nextNews -> clickNextNewsButton()
            else -> {
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class NewsTask : AsyncTask<ArrayList<News>, Void, ArrayList<News>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            prevNewsBt.visibility = View.GONE
            nextNewsBt.visibility = View.GONE
            newsRecyclerView.visibility = View.GONE
        }

        @SuppressLint("SetTextI18n")
        override fun doInBackground(vararg result: ArrayList<News>?): ArrayList<News>? {
            try {
                setNewsList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return newsList
        }

        override fun onPostExecute(result: ArrayList<News>?) {
            try {
                setAdapter(news)
                newsRecyclerView.visibility = View.VISIBLE
                nextNewsBt.visibility = View.VISIBLE
                setPrevBtVisibility()
            } catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
            }
        }
    }

    private fun setAdapter(news: Array<News?>) {
        newsRecyclerView.adapter = NewsAdapter(news)
    }
}
