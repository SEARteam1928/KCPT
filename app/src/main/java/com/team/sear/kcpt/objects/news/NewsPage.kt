package com.team.sear.kcpt.objects.news

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

class NewsPage {
    fun getPage(): Document {
        val url = "http://tpk-1.ru/%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D0%B8"
        return Jsoup.parse(URL(url), 25000)
    }
}