package com.team.sear.kcpt.objects.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.team.sear.kcpt.R
import uk.co.senab.photoview.PhotoView


class NewsAdapter(private val news: Array<News?>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private lateinit var itemView: View
    override fun getItemCount() = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titletv?.text = news[position]?.title
        holder.datetv?.text = news[position]?.date
        holder.descriptiontv?.text = news[position]?.description
        Picasso.with(itemView.context).load(news[position]?.imageLink)
                .into(holder.newsImageView)
        holder.webNews?.visibility = View.GONE
        holder.backBt?.visibility = View.GONE
        holder.moreLinktv!!.setOnClickListener {

            holder.webNews!!.visibility = View.VISIBLE
            holder.backBt!!.visibility = View.VISIBLE
            holder.webNews!!.settings.javaScriptEnabled
            holder.webNews!!.settings.builtInZoomControls
            holder.webNews!!.settings.supportZoom()
            holder.webNews!!.settings.displayZoomControls
            holder.webNews!!.settings.loadWithOverviewMode
            try {
                holder.webNews!!.loadUrl(news[position]!!.moreLink)
            } catch (e: Exception) {
            }

            holder.titletv?.visibility = View.GONE
            holder.datetv?.visibility = View.GONE
            holder.descriptiontv?.visibility = View.GONE
            holder.moreLinktv?.visibility = View.GONE
        }

        holder.backBt!!.setOnClickListener {
            holder.titletv?.visibility = View.VISIBLE
            holder.datetv?.visibility = View.VISIBLE
            holder.descriptiontv?.visibility = View.VISIBLE
            holder.moreLinktv?.visibility = View.VISIBLE
            holder.webNews!!.visibility = View.GONE
            holder.backBt!!.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titletv: TextView? = null
        var datetv: TextView? = null
        var newsImageView: PhotoView? = null
        var descriptiontv: TextView? = null
        var moreLinktv: TextView? = null
        var webNews: WebView? = null
        var backBt: Button? = null

        init {
            titletv = itemView.findViewById(R.id.newsTitle)
            datetv = itemView.findViewById(R.id.newsDate)
            newsImageView = itemView.findViewById(R.id.newsImageView)
            descriptiontv = itemView.findViewById(R.id.newsDescription)
            moreLinktv = itemView.findViewById(R.id.newsMoreLink)
            webNews = itemView.findViewById(R.id.webNews)
            backBt = itemView.findViewById(R.id.newsBack)
        }
    }
}