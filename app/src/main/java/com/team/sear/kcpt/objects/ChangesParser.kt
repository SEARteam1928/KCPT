package com.team.sear.kcpt.objects

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URL

class ChangesParser {
    private var result: String? = null
    private var html: String? = null
    private var groupSelectInt = 0
    private var groupName: String? = null
    private val groupsArray = ArrayList<String>()

    @Throws(UnsupportedEncodingException::class)
    fun parseChanges(): String? {
        return changesPrivate
    }

    @Throws(IOException::class)
    internal fun selectGroup(groupNameStr: String) {
        val page = getPage("https://docs.google.com/document/d/e/" +
                "2PACX-1vS2ehAErYyAWY-cm247Pt4oT2YVAkEMwiYXhFu0pxGexUne1PTWNiWS0ktvlglRQqNpLtolGzJjIlvc/pub")


        for(i in page.select("table")[0].select("tr")){
            groupsArray.add(i.select("td")[0].text())
        }

        groupSelectInt = if(groupsArray.indexOf(groupNameStr)==-1){
            0
        }else{
            groupsArray.indexOf(groupNameStr)
        }
    }


    private val changesPrivate: String?
        @Throws(UnsupportedEncodingException::class)
        get() {
            parseWeather()
            return result
        }

    @Throws(UnsupportedEncodingException::class)
    private fun parseWeather() {
        try {
            val page = getPage("https://docs.google.com/document/d/e/2PACX-1vS2ehAErYyAWY-cm247Pt4oT2YVAkEMwiYXhFu0pxGexUne1PTWNiWS0ktvlglRQqNpLtolGzJjIlvc/pub")

            val style = Style()
            val styleStr = style.style
            var pClass: String
            var date: String
            var tableClass: String
            var tr: String
            var trClass: String

            var td1: String
            var td2: String
            var td3: String
            var td4: String

            try {
                pClass = page.select("p").select("span")[0].attr("class")
                date = page.select("p").select("span")[1].text()
                tableClass = page.select("table")[0].attr("class")
                trClass = page.select("table")[0].select("tr")[groupSelectInt].attr("class")
                tr = page.select("table")[0].select("tr")[groupSelectInt].html()
                groupName = page.select("tr")[groupSelectInt].select("td")[0].text()

                td1 = page.select("tr")[groupSelectInt].select("td")[1].html()
                td2 = page.select("tr")[groupSelectInt].select("td")[2].html()
                td3 = page.select("tr")[groupSelectInt].select("td")[3].html()
                td4 = page.select("tr")[groupSelectInt].select("td")[4].html()
            } catch (e: Exception) {
                pClass = ""
                date = ""
                tr = ""
                trClass = ""
                td1 = ""
                td2 = ""
                td3 = ""
                td4 = ""
                tableClass = ""
                groupName = ""
            }

            tr.replace("&nbsp;", "")
            html = "<!Doctype html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style type=\"text/css\">\n" + styleStr + "</style>" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div id=\"contents\">\n" +
                    "<p class=\"c23\"><span class=\"" + pClass + "\">ИЗМЕНЕНИЯ В РАСПИСАНИИ" +
                    "<br>Для группы: " + groupName + "</span></p>\n" +
                    "<p class=\"c23\"><span class=\"" + pClass + "\">" + date + "</span></p>\n" +
                    "<a id=\"t.c2b36a5da1b66dcaba8ffe014c445801fd593697\"></a>" +
                    "<a id=\"t.0\"></a>" +
                    "<br>\n" +
                    "<table class=\"" +
                    tableClass + "\">\n<tbody>" +
                    "\n<tr class=\"" +
                    trClass + "\">" +
                    "<td class=\"td1\" colspan=\"1\" rowspan=\"1\">" + td1 + "</td>" +
                    "<td class=\"td2\" colspan=\"1\" rowspan=\"1\">" + td2 + "</td>" +
                    "<td class=\"td3\" colspan=\"1\" rowspan=\"1\">" + td3 + "</td>" +
                    "<td class=\"td4\" colspan=\"1\" rowspan=\"1\">" + td4 + "</td>" +
                    "</tr></tbody>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>"



            if (groupSelectInt != 0) {
                result = html
                groupSelectInt = 0
            } else {
                result = "<!Doctype html><html><head><style type=\"text/css\">\n$styleStr</style></head><body><p class=\"c23\">Изменений нет! но лучше проверьте, могут быть ошибки))</p><body></html>"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "<!Doctype html><html><head><style type=\"text/css\">\n</style></head><body><p class=\"c23\">Изменений нет! но лучше проверьте, могут быть ошибки))</p><body></html>"
        }
    }

    @Throws(IOException::class, UnsupportedEncodingException::class)
    private fun getPage(url: String): Document {

        return Jsoup.parse(URL(url), 25000)

    }

}
