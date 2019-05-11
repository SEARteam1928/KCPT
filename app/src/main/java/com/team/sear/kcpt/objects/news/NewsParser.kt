package com.team.sear.kcpt.objects.news

import org.jsoup.nodes.Document
import java.net.URLEncoder

class NewsParser {

    private fun getTitle(page: Document, elementInt: Int): String {
        return try {
            page.select("h4[class=ib]")[elementInt].text()
        } catch (e: Exception) {
            "notitle"
        }
    }

    fun title(page: Document, elementInt: Int): String {
        return getTitle(page, elementInt)
    }

    private fun getImageLink(page: Document, elementInt: Int): String {
        var splitedImageLink: String? = null
        var urle: String
        try {
            val imageLink = page.select("div[class=pull-left n_img_pref]").select("img")[elementInt].attr("src")
            for (splited in imageLink.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                splitedImageLink = splited
            }
            val firstUrl = imageLink.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]
            val secondUrl = imageLink.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[4]
            assert(splitedImageLink != null)
            splitedImageLink = URLEncoder.encode(splitedImageLink, "UTF-8")

            urle = "http://tpk-1.ru/wp-content/uploads/$firstUrl/$secondUrl/$splitedImageLink"
            return urle
        } catch (e: Exception) {
            urle = "noImage"
            return urle
        }

    }

    fun imageLink(page: Document, elementInt: Int): String {
        return getImageLink(page, elementInt)
    }

    private fun getDate(page: Document, elementInt: Int): String? {
        var date: String
        return try {
            date = page.select("div[style=padding-top: 10px;]")[elementInt].text()
            date = date.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            date
        } catch (e: Exception) {
            date = "noDate"
            date
        }
    }

    fun date(page: Document, elementInt: Int): String? {
        return getDate(page, elementInt)
    }

    private fun getDescription(page: Document, elementInt: Int): String? {
        return try {
            page.select("div[class=entry]").select("p")[elementInt].text()
        } catch (e: Exception) {
            "nodesciption"
        }
    }

    fun description(page: Document, elementInt: Int): String? {
        return getDescription(page, elementInt)
    }

    private fun getMoreLink(page: Document, elementInt: Int): String? {
        return try {
            page.select("div[class=entry]").select("a")[elementInt].attr("href")
        } catch (e: Exception) {
            "nomorelink"
        }
    }

    fun moreLink(page: Document, elementInt: Int): String? {
        return getMoreLink(page, elementInt)
    }


}