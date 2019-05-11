package com.team.sear.kcpt.timetablefragments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.team.sear.kcpt.Navigate
import com.team.sear.kcpt.objects.SearchModel
import com.team.sear.kcpt.objects.SelectTimeTable
import com.team.sear.kcpt.R
import com.google.firebase.auth.FirebaseAuth
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.select_time_table_for_app.*

class SelectTimeTableForApp : AppCompatActivity(), View.OnClickListener {
    private var AT1609: Button? = null
    private var AT1709: Button? = null
    private var AT1711: Button? = null
    private var AT1811: Button? = null
    private var ATPiP1509: Button? = null
    private var ATPiP1609: Button? = null
    private var ATPiP1611: Button? = null
    private var DO15091: Button? = null
    private var DO15092: Button? = null
    private var DO1611: Button? = null
    private var DO17111: Button? = null
    private var DO17112: Button? = null
    private var DO18111: Button? = null
    private var DO18112: Button? = null
    private var KP16111: Button? = null
    private var KP16112: Button? = null
    private var KP1709: Button? = null
    private var KP17111: Button? = null
    private var KP17112: Button? = null
    private var KP17113: Button? = null
    private var KP18111: Button? = null
    private var KP18112: Button? = null
    private var KS1611: Button? = null
    private var OSATPiP1711: Button? = null
    private var OSATPiP18111: Button? = null
    private var OSATPiP18112: Button? = null
    private var PDOTT1509: Button? = null
    private var PDOTT1609: Button? = null
    private var PDOTT1709: Button? = null
    private var PDOTT18111: Button? = null
    private var PDOTT18112: Button? = null
    private var SSA1711: Button? = null
    private var SSA18111: Button? = null
    private var SSA18112: Button? = null
    private var SHO15091: Button? = null
    private var SHO15092: Button? = null
    private var studentStatusBt: Button? = null
    private var teacherStatusBt: Button? = null
    private var LisinaEVbt: Button? = null
    private var BorodinaSVbt: Button? = null
    private var LisinAAbt: Button? = null
    private var KalinovskayaSAbt: Button? = null
    private var ArefyevEAbt: Button? = null
    private var AyzyatovaGGbt: Button? = null
    private var GolenduhinaTRbt: Button? = null
    private var SandakovaDNbt: Button? = null
    private var CheymetovaTVbt: Button? = null
    private var SaharitovaNNbt: Button? = null
    private var VohmencevaTNbt: Button? = null
    private var LagohinAPbt: Button? = null
    private var BekshenevaGHbt: Button? = null
    private var RusakovMYUbt: Button? = null
    private var UrusovAAbt: Button? = null
    private var NugmanovVNbt: Button? = null
    private var FisenkoEMbt: Button? = null
    private var RomanenkoSVbt: Button? = null
    private var AphadzeNAbt: Button? = null
    private var PosohovaMAbt: Button? = null
    private var MosolCVbt: Button? = null
    private var ShestopalovaEAbt: Button? = null
    private var KlimovichNPbt: Button? = null
    private var SizovaKNbt: Button? = null
    private var VtorushinaYUAbt: Button? = null
    private var KaluginaSVbt: Button? = null
    private var ProsverennikovaSAbt: Button? = null
    private var MiheevaLVbt: Button? = null
    private var KuroedovaTAbt: Button? = null
    private var GorshunovaSVbt: Button? = null
    private var ShipunovaOVbt: Button? = null
    private var UzhanovaTLbt: Button? = null
    private var TimofeevPNbt: Button? = null
    private var PermyakovaLPbt: Button? = null
    private var RagozinaTMbt: Button? = null
    private var MuhamedzhanovaZBbt: Button? = null
    private var TulinaNBbt: Button? = null
    private var VergunovaTZbt: Button? = null
    private var PavlovaNGbt: Button? = null
    private var SushkovaAAbt: Button? = null
    private var LitvinovaAVbt: Button? = null
    private var NorinaNNbt: Button? = null
    private var LitusAAbt: Button? = null
    private var AlerskayaNVbt: Button? = null
    private var ZvonarevaIMbt: Button? = null
    private var IgnatovaSMbt: Button? = null
    private var RashevskayaSFbt: Button? = null
    private var BaykinaILbt: Button? = null
    private var GulyaevIPbt: Button? = null
    private var PolishyukAAbt: Button? = null
    private var PopovANbt: Button? = null
    private var GeyerARbt: Button? = null
    private var AymetdinovBIbt: Button? = null
    private var GurulyovIAbt: Button? = null
    private var PetrovAMbt: Button? = null
    private var KuznetsovASbt: Button? = null
    private var PodkovirkinaVLbt: Button? = null
    private var SmirnovAGbt: Button? = null
    private var ShvetsovEVbt: Button? = null

    private var groupSV: ScrollView? = null
    private var auth: FirebaseAuth? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private lateinit var selectTimeTable: SelectTimeTable
    private lateinit var navigateIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_time_table_for_app)
        selectTimeTable = SelectTimeTable()
        navigateIntent = Intent(this, Navigate::class.java)
        findViewByID()
        setInvisible()
        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {
            }
        }

        searchBt.setOnClickListener {
            SimpleSearchDialogCompat(this@SelectTimeTableForApp, "Поиск", "Что вы хотите найти?", null,
                    initData(), SearchResultListener { baseSearchDialogCompat, item, _ ->
                when {
                    item.title == "Арефьев Е. А." -> setTItem("ArefyevEA")
                    item.title == "Лисина Е. В." -> setTItem("LisinaEV")
                    item.title == "Бородина С. В." -> setTItem("BorodinaSV")
                    item.title == "Лисин А. А." -> setTItem("LisinAA")
                    item.title == "Калиновская С. А." -> setTItem("KalinovskayaSA")
                    item.title == "Айзятова Г. Г." -> setTItem("AyzyatovaGG")
                    item.title == "Голендухина Т. Р." -> setTItem("GolenduhinaTR")
                    item.title == "Сандакова Д. Н." -> setTItem("SandakovaDN")
                    item.title == "Чейметова Т. В." -> setTItem("CheymetovaTV")
                    item.title == "Сахаритова Н. Н." -> setTItem("SaharitovaNN")
                    item.title == "Вохменцева Т. Н." -> setTItem("VohmencevaTN")
                    item.title == "Лагохин А. П." -> setTItem("LagohinAP")
                    item.title == "Бекшенева Г. Х." -> setTItem("BekshenevaGH")
                    item.title == "Русаков М. Ю." -> setTItem("RusakovMYU")
                    item.title == "Урусов А. А." -> setTItem("UrusovAA")
                    item.title == "Нугманов В. Н." -> setTItem("NugmanovVN")
                    item.title == "Фисенко Е. М." -> setTItem("FisenkoEM")
                    item.title == "Романенко С. В." -> setTItem("RomanenkoSV")
                    item.title == "Апхадзе Н. А." -> setTItem("AphadzeNA")
                    item.title == "Посохова М. А." -> setTItem("PosohovaMA")
                    item.title == "Мосол С. В." -> setTItem("MosolCV")
                    item.title == "Шестопалова Е. А." -> setTItem("ShestopalovaEA")
                    item.title == "Климович Н. П." -> setTItem("KlimovichNP")
                    item.title == "Сизова К. Н." -> setTItem("SizovaKN")
                    item.title == "Вторушина Ю. А." -> setTItem("VtorushinaYUA")
                    item.title == "Калугина С. В." -> setTItem("KaluginaSV")
                    item.title == "Просверенникова С. А." -> setTItem("ProsverennikovaSA")
                    item.title == "Михеева Л. В." -> setTItem("MiheevaLV")
                    item.title == "Куроедова Т. А." -> setTItem("KuroedovaTA")
                    item.title == "Горшунова С. В." -> setTItem("GorshunovaSV")
                    item.title == "Шипунова О. В." -> setTItem("ShipunovaOV")
                    item.title == "Ужанова Т. Л." -> setTItem("UzhanovaTL")
                    item.title == "Тимофеев П. Н." -> setTItem("TimofeevPN")
                    item.title == "Пермякова Л. П." -> setTItem("PermyakovaLP")
                    item.title == "Рагозина Т. М." -> setTItem("RagozinaTM")
                    item.title == "Мухамеджанова З. Б." -> setTItem("MuhamedzhanovaZB")
                    item.title == "Тулина Н. Б." -> setTItem("TulinaNB")
                    item.title == "Вергунова Т. З." -> setTItem("VergunovaTZ")
                    item.title == "Павлова Н. Г." -> setTItem("PavlovaNG")
                    item.title == "Сушкова А. А." -> setTItem("SushkovaAA")
                    item.title == "Литвинова А. В." -> setTItem("LitvinovaAV")
                    item.title == "Норина Н. Н." -> setTItem("NorinaNN")
                    item.title == "Литус А. А." -> setTItem("LitusAA")
                    item.title == "Алерская Н. В." -> setTItem("AlerskayaNV")
                    item.title == "Звонарева И. М." -> setTItem("ZvonarevaIM")
                    item.title == "Игнатова С. М." -> setTItem("IgnatovaSM")
                    item.title == "Рашевская С. Ф." -> setTItem("RashevskayaSF")
                    item.title == "Байкина И. Л." -> setTItem("BaykinaIL")
                    item.title == "Гуляев И. П." -> setTItem("GulyaevIP")
                    item.title == "Полищук А. А." -> setTItem("PolishyukAA")
                    item.title == "Попов А. Н." -> setTItem("PopovAN")
                    item.title == "Гейер А. Р." -> setTItem("GeyerAR")
                    item.title == "Айметдинов Б. И." -> setTItem("AymetdinovBI")
                    item.title == "Гурулев И. А." -> setTItem("GurulyovIA")
                    item.title == "Петров А. М." -> setTItem("PetrovAM")
                    item.title == "Кузнецов А. С." -> setTItem("KuznetsovAS")
                    item.title == "Подковыркина В. Л." -> setTItem("PodkovirkinaVL")
                    item.title == "Смирнов А. Г." -> setTItem("SmirnovAG")
                    item.title == "Швецов Е.В." -> setTItem("ShvetsovEV")

                    item.title == "АТ 16-09" -> setSItem("AT1609")
                    item.title == "АТ 17-09" -> setSItem("AT1709")
                    item.title == "АТ 17-11" -> setSItem("AT1711")
                    item.title == "АТ 18-11" -> setSItem("AT1811")
                    item.title == "АТПиП 15-09" -> setSItem("ATPiP1509")
                    item.title == "АТПиП 16-09" -> setSItem("ATPiP1609")
                    item.title == "АТПиП 16-11" -> setSItem("ATPiP1611")
                    item.title == "ДО 15-09-1" -> setSItem("DO15091")
                    item.title == "ДО 15-09-2" -> setSItem("DO15092")
                    item.title == "ДО 16-11" -> setSItem("DO1611")
                    item.title == "ДО 17-11-1" -> setSItem("DO17111")
                    item.title == "ДО 17-11-2" -> setSItem("DO17112")
                    item.title == "ДО 18-11-1" -> setSItem("DO18111")
                    item.title == "ДО 18-11-2" -> setSItem("DO18112")
                    item.title == "КП 16-11-1" -> setSItem("KP16111")
                    item.title == "КП 16-11-2" -> setSItem("KP16112")
                    item.title == "КП 17-09" -> setSItem("KP1709")
                    item.title == "КП 17-11-1" -> setSItem("KP17111")
                    item.title == "КП 17-11-2" -> setSItem("KP17112")
                    item.title == "КП 17-11-3" -> setSItem("KP17113")
                    item.title == "КП 18-11-1" -> setSItem("KP18111")
                    item.title == "КП 18-11-2" -> setSItem("KP18112")
                    item.title == "КС 16-11" -> setSItem("KS1611")
                    item.title == "ОСАТПиП 17-11" -> setSItem("OSATPiP1711")
                    item.title == "ОСАТПиП 18-11-1" -> setSItem("OSATPiP18111")
                    item.title == "ОСАТПиП 18-11-2" -> setSItem("OSATPiP18112")
                    item.title == "ПДО ТТ 15-09" -> setSItem("PDOTT1509")
                    item.title == "ПДО ТТ 16-09" -> setSItem("PDOTT1609")
                    item.title == "ПДО ТТ 17-09" -> setSItem("PDOTT1709")
                    item.title == "ПДО ТТ 18-11-1" -> setSItem("PDOTT18111")
                    item.title == "ПДО ТТ 18-11-2" -> setSItem("PDOTT18112")
                    item.title == "ССА 17-11" -> setSItem("SSA1711")
                    item.title == "ССА 18-11-1" -> setSItem("SSA18111")
                    item.title == "ССА 18-11-2" -> setSItem("SSA18112")
                    item.title == "ШО 15-09-1" -> setSItem("SHO15091")
                    item.title == "ШО 15-09-2" -> setSItem("SHO15092")
                }
                Toast.makeText(this@SelectTimeTableForApp, item.title, Toast.LENGTH_SHORT).show()
                baseSearchDialogCompat.dismiss()
            }).show()
        }
    }

    private fun setSItem(groupStr: String) {
        selectTimeTable.setStudent(groupStr, auth)
        intentNavigate()
    }

    private fun setTItem(teacherName: String) {
        selectTimeTable.setTeacher(teacherName, auth)
        intentNavigate()
    }

    private fun initData(): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
            it.add(SearchModel("Арефьев Е. А."))
            it.add(SearchModel("Лисина Е. В."))
            it.add(SearchModel("Бородина С. В."))
            it.add(SearchModel("Лисин А. А."))
            it.add(SearchModel("Калиновская С. А."))
            it.add(SearchModel("Айзятова Г. Г."))
            it.add(SearchModel("Голендухина Т. Р."))
            it.add(SearchModel("Сандакова Д. Н."))
            it.add(SearchModel("Чейметова Т. В."))
            it.add(SearchModel("Сахаритова Н. Н."))
            it.add(SearchModel("Вохменцева Т. Н."))
            it.add(SearchModel("Лагохин А. П."))
            it.add(SearchModel("Бекшенева Г. Х."))
            it.add(SearchModel("Русаков М. Ю."))
            it.add(SearchModel("Урусов А. А."))
            it.add(SearchModel("Нугманов В. Н."))
            it.add(SearchModel("Фисенко Е. М."))
            it.add(SearchModel("Романенко С. В."))
            it.add(SearchModel("Апхадзе Н. А."))
            it.add(SearchModel("Посохова М. А."))
            it.add(SearchModel("Мосол С. В."))
            it.add(SearchModel("Шестопалова Е. А."))
            it.add(SearchModel("Климович Н. П."))
            it.add(SearchModel("Сизова К. Н."))
            it.add(SearchModel("Вторушина Ю. А."))
            it.add(SearchModel("Калугина С. В."))
            it.add(SearchModel("Просверенникова С. А."))
            it.add(SearchModel("Михеева Л. В."))
            it.add(SearchModel("Куроедова Т. А."))
            it.add(SearchModel("Горшунова С. В."))
            it.add(SearchModel("Шипунова О. В."))
            it.add(SearchModel("Ужанова Т. Л."))
            it.add(SearchModel("Тимофеев П. Н."))
            it.add(SearchModel("Пермякова Л. П."))
            it.add(SearchModel("Рагозина Т. М."))
            it.add(SearchModel("Мухамеджанова З. Б."))
            it.add(SearchModel("Тулина Н. Б."))
            it.add(SearchModel("Вергунова Т. З."))
            it.add(SearchModel("Павлова Н. Г."))
            it.add(SearchModel("Сушкова А. А."))
            it.add(SearchModel("Литвинова А. В."))
            it.add(SearchModel("Норина Н. Н."))
            it.add(SearchModel("Литус А. А."))
            it.add(SearchModel("Алерская Н. В."))
            it.add(SearchModel("Звонарева И. М."))
            it.add(SearchModel("Игнатова С. М."))
            it.add(SearchModel("Рашевская С. Ф."))
            it.add(SearchModel("Байкина И. Л."))
            it.add(SearchModel("Гуляев И. П."))
            it.add(SearchModel("Полищук А. А."))
            it.add(SearchModel("Попов А. Н."))
            it.add(SearchModel("Гейер А. Р."))
            it.add(SearchModel("Айметдинов Б. И."))
            it.add(SearchModel("Гурулев И. А."))
            it.add(SearchModel("Петров А. М."))
            it.add(SearchModel("Кузнецов А. С."))
            it.add(SearchModel("Подковыркина В. Л."))
            it.add(SearchModel("Смирнов А. Г."))
            it.add(SearchModel("Швецов Е.В."))

            it.add(SearchModel("АТ 16-09"))
            it.add(SearchModel("АТ 17-09"))
            it.add(SearchModel("АТ 17-11"))
            it.add(SearchModel("АТ 18-11"))
            it.add(SearchModel("АТПиП 15-09"))
            it.add(SearchModel("АТПиП 16-09"))
            it.add(SearchModel("АТПиП 16-11"))
            it.add(SearchModel("ДО 15-09-1"))
            it.add(SearchModel("ДО 15-09-2"))
            it.add(SearchModel("ДО 16-11"))
            it.add(SearchModel("ДО 17-11-1"))
            it.add(SearchModel("ДО 17-11-2"))
            it.add(SearchModel("ДО 18-11-1"))
            it.add(SearchModel("ДО 18-11-2"))
            it.add(SearchModel("КП 16-11-1"))
            it.add(SearchModel("КП 16-11-2"))
            it.add(SearchModel("КП 17-09"))
            it.add(SearchModel("КП 17-11-1"))
            it.add(SearchModel("КП 17-11-2"))
            it.add(SearchModel("КП 17-11-3"))
            it.add(SearchModel("КП 18-11-1"))
            it.add(SearchModel("КП 18-11-2"))
            it.add(SearchModel("КС 16-11"))
            it.add(SearchModel("ОСАТПиП 17-11"))
            it.add(SearchModel("ОСАТПиП 18-11-1"))
            it.add(SearchModel("ОСАТПиП 18-11-2"))
            it.add(SearchModel("ПДО ТТ 15-09"))
            it.add(SearchModel("ПДО ТТ 16-09"))
            it.add(SearchModel("ПДО ТТ 17-09"))
            it.add(SearchModel("ПДО ТТ 18-11-1"))
            it.add(SearchModel("ПДО ТТ 18-11-2"))
            it.add(SearchModel("ССА 17-11"))
            it.add(SearchModel("ССА 18-11-1"))
            it.add(SearchModel("ССА 18-11-2"))
            it.add(SearchModel("ШО 15-09-1"))
            it.add(SearchModel("ШО 15-09-2"))
        }
    }

    private fun findViewByID() {
        studentStatusBt = findViewById(R.id.studentStatusBt)
        teacherStatusBt = findViewById(R.id.teacherStatusBt)
        LisinaEVbt = findViewById(R.id.LisinaEVbt)
        BorodinaSVbt = findViewById(R.id.BorodinaSVbt)
        LisinAAbt = findViewById(R.id.LisinAAbt)
        KalinovskayaSAbt = findViewById(R.id.KalinovskayaSAbt)
        ArefyevEAbt = findViewById(R.id.ArefyevEAbt)
        AyzyatovaGGbt = findViewById(R.id.AyzyatovaGGbt)
        GolenduhinaTRbt = findViewById(R.id.GolenduhinaTRbt)
        SandakovaDNbt = findViewById(R.id.SandakovaDNbt)
        CheymetovaTVbt = findViewById(R.id.CheymetovaTVbt)
        SaharitovaNNbt = findViewById(R.id.SaharitovaNNbt)
        VohmencevaTNbt = findViewById(R.id.VohmencevaTNbt)
        LagohinAPbt = findViewById(R.id.LagohinAPbt)
        BekshenevaGHbt = findViewById(R.id.BekshenevaGHbt)
        RusakovMYUbt = findViewById(R.id.RusakovMYUbt)
        UrusovAAbt = findViewById(R.id.UrusovAAbt)
        NugmanovVNbt = findViewById(R.id.NugmanovVNbt)
        FisenkoEMbt = findViewById(R.id.FisenkoEMbt)
        RomanenkoSVbt = findViewById(R.id.RomanenkoSVbt)
        AphadzeNAbt = findViewById(R.id.AphadzeNAbt)
        PosohovaMAbt = findViewById(R.id.PosohovaMAbt)
        MosolCVbt = findViewById(R.id.MosolCVbt)
        ShestopalovaEAbt = findViewById(R.id.ShestopalovaEAbt)
        KlimovichNPbt = findViewById(R.id.KlimovichNPbt)
        SizovaKNbt = findViewById(R.id.SizovaKNbt)
        VtorushinaYUAbt = findViewById(R.id.VtorushinaYUAbt)
        KaluginaSVbt = findViewById(R.id.KaluginaSVbt)
        ProsverennikovaSAbt = findViewById(R.id.ProsverennikovaSAbt)
        MiheevaLVbt = findViewById(R.id.MiheevaLVbt)
        KuroedovaTAbt = findViewById(R.id.KuroedovaTAbt)
        GorshunovaSVbt = findViewById(R.id.GorshunovaSVbt)
        ShipunovaOVbt = findViewById(R.id.ShipunovaOVbt)
        UzhanovaTLbt = findViewById(R.id.UzhanovaTLbt)
        TimofeevPNbt = findViewById(R.id.TimofeevPNbt)
        PermyakovaLPbt = findViewById(R.id.PermyakovaLPbt)
        RagozinaTMbt = findViewById(R.id.RagozinaTMbt)
        MuhamedzhanovaZBbt = findViewById(R.id.MuhamedzhanovaZBbt)
        TulinaNBbt = findViewById(R.id.TulinaNBbt)
        VergunovaTZbt = findViewById(R.id.VergunovaTZbt)
        PavlovaNGbt = findViewById(R.id.PavlovaNGbt)
        SushkovaAAbt = findViewById(R.id.SushkovaAAbt)
        LitvinovaAVbt = findViewById(R.id.LitvinovaAVbt)
        NorinaNNbt = findViewById(R.id.NorinaNNbt)
        LitusAAbt = findViewById(R.id.LitusAAbt)
        AlerskayaNVbt = findViewById(R.id.AlerskayaNVbt)
        ZvonarevaIMbt = findViewById(R.id.ZvonarevaIMbt)
        IgnatovaSMbt = findViewById(R.id.IgnatovaSMbt)
        RashevskayaSFbt = findViewById(R.id.RashevskayaSFbt)
        BaykinaILbt = findViewById(R.id.BaykinaILbt)
        GulyaevIPbt = findViewById(R.id.GulyaevIPbt)
        PolishyukAAbt = findViewById(R.id.PolishyukAAbt)
        PopovANbt = findViewById(R.id.PopovANbt)
        GeyerARbt = findViewById(R.id.GeyerARbt)
        AymetdinovBIbt = findViewById(R.id.AymetdinovBIbt)
        GurulyovIAbt = findViewById(R.id.GurulyovIAbt)
        PetrovAMbt = findViewById(R.id.PetrovAMbt)
        KuznetsovASbt = findViewById(R.id.KuznetsovASbt)
        PodkovirkinaVLbt = findViewById(R.id.PodkovirkinaVLbt)
        SmirnovAGbt = findViewById(R.id.SmirnovAGbt)
        ShvetsovEVbt = findViewById(R.id.ShvetsovEVbt)

        groupSV = findViewById(R.id.groupSVselect)
        AT1609 = findViewById(R.id.AT1609select)
        AT1709 = findViewById(R.id.AT1709select)
        AT1711 = findViewById(R.id.AT1711select)
        AT1811 = findViewById(R.id.AT1811select)
        ATPiP1509 = findViewById(R.id.ATPiP1509select)
        ATPiP1609 = findViewById(R.id.ATPiP1609select)
        ATPiP1611 = findViewById(R.id.ATPiP1611select)
        DO15091 = findViewById(R.id.DO15091select)
        DO15092 = findViewById(R.id.DO15092select)
        DO1611 = findViewById(R.id.DO1611select)
        DO17111 = findViewById(R.id.DO17111select)
        DO17112 = findViewById(R.id.DO17112select)
        DO18111 = findViewById(R.id.DO18111select)
        DO18112 = findViewById(R.id.DO18112select)
        KP16111 = findViewById(R.id.KP16111select)
        KP16112 = findViewById(R.id.KP16112select)
        KP1709 = findViewById(R.id.KP1709select)
        KP17111 = findViewById(R.id.KP17111select)
        KP17112 = findViewById(R.id.KP17112select)
        KP17113 = findViewById(R.id.KP17113select)
        KP18111 = findViewById(R.id.KP18111select)
        KP18112 = findViewById(R.id.KP18112select)
        KS1611 = findViewById(R.id.KS1611select)
        OSATPiP1711 = findViewById(R.id.OSATPiP1711select)
        OSATPiP18111 = findViewById(R.id.OSATPiP18111select)
        OSATPiP18112 = findViewById(R.id.OSATPiP18112select)
        PDOTT1509 = findViewById(R.id.PDOTT1509select)
        PDOTT1609 = findViewById(R.id.PDOTT1609select)
        PDOTT1709 = findViewById(R.id.PDOTT1709select)
        PDOTT18111 = findViewById(R.id.PDOTT18111select)
        PDOTT18112 = findViewById(R.id.PDOTT18112select)
        SSA1711 = findViewById(R.id.SSA1711select)
        SSA18111 = findViewById(R.id.SSA18111select)
        SSA18112 = findViewById(R.id.SSA18112select)
        SHO15091 = findViewById(R.id.SHO15091select)
        SHO15092 = findViewById(R.id.SHO15092select)
        setClickListen()
    }

    private fun intentNavigate() {
        val navigateIntent = Intent(this, Navigate::class.java)
        startActivity(navigateIntent)
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.studentStatusBt -> setGroupVisible()
            R.id.teacherStatusBt -> setTeacherVisible()
            R.id.AT1609select -> setSItem("AT1609")
            R.id.AT1709select -> setSItem("AT1709")
            R.id.AT1711select -> setSItem("AT1711")
            R.id.AT1811select -> setSItem("AT1811")
            R.id.ATPiP1509select -> setSItem("ATPiP1509")
            R.id.ATPiP1609select -> setSItem("ATPiP1609")
            R.id.ATPiP1611select -> setSItem("ATPiP1611")
            R.id.DO15091select -> setSItem("DO15091")
            R.id.DO15092select -> setSItem("DO15092")
            R.id.DO1611select -> setSItem("DO1611")
            R.id.DO17111select -> setSItem("DO17111")
            R.id.DO17112select -> setSItem("DO17112")
            R.id.DO18111select -> setSItem("DO18111")
            R.id.DO18112select -> setSItem("DO18112")
            R.id.KP16111select -> setSItem("KP16111")
            R.id.KP16112select -> setSItem("KP16112")
            R.id.KP1709select -> setSItem("KP1709")
            R.id.KP17111select -> setSItem("KP17111")
            R.id.KP17112select -> setSItem("KP17112")
            R.id.KP17113select -> setSItem("KP17113")
            R.id.KP18111select -> setSItem("KP18111")
            R.id.KP18112select -> setSItem("KP18112")
            R.id.KS1611select -> setSItem("KS1611")
            R.id.OSATPiP1711select -> setSItem("OSATPiP1711")
            R.id.OSATPiP18111select -> setSItem("OSATPiP18111")
            R.id.OSATPiP18112select -> setSItem("OSATPiP18112")
            R.id.PDOTT1509select -> setSItem("PDOTT1509")
            R.id.PDOTT1609select -> setSItem("PDOTT1609")
            R.id.PDOTT1709select -> setSItem("PDOTT1709")
            R.id.PDOTT18111select -> setSItem("PDOTT18111")
            R.id.PDOTT18112select -> setSItem("PDOTT18112")
            R.id.SSA1711select -> setSItem("SSA1711")
            R.id.SSA18111select -> setSItem("SSA18111")
            R.id.SSA18112select -> setSItem("SSA18112")
            R.id.SHO15091select -> setSItem("SHO15091")
            R.id.SHO15092select -> setSItem("SHO15092")

            R.id.LisinaEVbt -> setTItem("LisinaEV")
            R.id.BorodinaSVbt -> setTItem("BorodinaSV")
            R.id.LisinAAbt -> setTItem("LisinAA")
            R.id.KalinovskayaSAbt -> setTItem("KalinovskayaSA")
            R.id.ArefyevEAbt -> setTItem("ArefyevEA")
            R.id.AyzyatovaGGbt -> setTItem("AyzyatovaGG")
            R.id.GolenduhinaTRbt -> setTItem("GolenduhinaTR")
            R.id.SandakovaDNbt -> setTItem("SandakovaDN")
            R.id.CheymetovaTVbt -> setTItem("CheymetovaTV")
            R.id.SaharitovaNNbt -> setTItem("SaharitovaNN")
            R.id.VohmencevaTNbt -> setTItem("VohmencevaTN")
            R.id.LagohinAPbt -> setTItem("LagohinAP")
            R.id.BekshenevaGHbt -> setTItem("BekshenevaGH")
            R.id.RusakovMYUbt -> setTItem("RusakovMYU")
            R.id.UrusovAAbt -> setTItem("UrusovAA")
            R.id.NugmanovVNbt -> setTItem("NugmanovVN")
            R.id.FisenkoEMbt -> setTItem("FisenkoEM")
            R.id.RomanenkoSVbt -> setTItem("RomanenkoSV")
            R.id.AphadzeNAbt -> setTItem("AphadzeNA")
            R.id.PosohovaMAbt -> setTItem("PosohovaMA")
            R.id.MosolCVbt -> setTItem("MosolCV")
            R.id.ShestopalovaEAbt -> setTItem("ShestopalovaEA")
            R.id.KlimovichNPbt -> setTItem("KlimovichNP")
            R.id.SizovaKNbt -> setTItem("SizovaKN")
            R.id.VtorushinaYUAbt -> setTItem("VtorushinaYUA")
            R.id.KaluginaSVbt -> setTItem("KaluginaSV")
            R.id.ProsverennikovaSAbt -> setTItem("ProsverennikovaSA")
            R.id.MiheevaLVbt -> setTItem("MiheevaLV")
            R.id.KuroedovaTAbt -> setTItem("KuroedovaTA")
            R.id.GorshunovaSVbt -> setTItem("GorshunovaSV")
            R.id.ShipunovaOVbt -> setTItem("ShipunovaOV")
            R.id.UzhanovaTLbt -> setTItem("UzhanovaTL")
            R.id.TimofeevPNbt -> setTItem("TimofeevPN")
            R.id.PermyakovaLPbt -> setTItem("PermyakovaLP")
            R.id.RagozinaTMbt -> setTItem("RagozinaTM")
            R.id.MuhamedzhanovaZBbt -> setTItem("MuhamedzhanovaZB")
            R.id.TulinaNBbt -> setTItem("TulinaNB")
            R.id.VergunovaTZbt -> setTItem("VergunovaTZ")
            R.id.PavlovaNGbt -> setTItem("PavlovaNG")
            R.id.SushkovaAAbt -> setTItem("SushkovaAA")
            R.id.LitvinovaAVbt -> setTItem("LitvinovaAV")
            R.id.NorinaNNbt -> setTItem("NorinaNN")
            R.id.LitusAAbt -> setTItem("LitusAA")
            R.id.AlerskayaNVbt -> setTItem("AlerskayaNV")
            R.id.ZvonarevaIMbt -> setTItem("ZvonarevaIM")
            R.id.IgnatovaSMbt -> setTItem("IgnatovaSM")
            R.id.RashevskayaSFbt -> setTItem("RashevskayaSF")
            R.id.BaykinaILbt -> setTItem("BaykinaIL")
            R.id.GulyaevIPbt -> setTItem("GulyaevIP")
            R.id.PolishyukAAbt -> setTItem("PolishyukAA")
            R.id.PopovANbt -> setTItem("PopovAN")
            R.id.GeyerARbt -> setTItem("GeyerAR")
            R.id.AymetdinovBIbt -> setTItem("AymetdinovBI")
            R.id.GurulyovIAbt -> setTItem("GurulyovIA")
            R.id.PetrovAMbt -> setTItem("PetrovAM")
            R.id.KuznetsovASbt -> setTItem("KuznetsovAS")
            R.id.PodkovirkinaVLbt -> setTItem("PodkovirkinaVL")
            R.id.SmirnovAGbt -> setTItem("SmirnovAG")
            R.id.ShvetsovEVbt -> setTItem("ShvetsovEV")
        }
    }

    private fun setClickListen() {
        cL(studentStatusBt!!)
        cL(teacherStatusBt!!)
        cL(LisinaEVbt!!)
        cL(BorodinaSVbt!!)
        cL(LisinAAbt!!)
        cL(KalinovskayaSAbt!!)
        cL(ArefyevEAbt!!)
        cL(AyzyatovaGGbt!!)
        cL(GolenduhinaTRbt!!)
        cL(SandakovaDNbt!!)
        cL(CheymetovaTVbt!!)
        cL(SaharitovaNNbt!!)
        cL(VohmencevaTNbt!!)
        cL(LagohinAPbt!!)
        cL(BekshenevaGHbt!!)
        cL(RusakovMYUbt!!)
        cL(UrusovAAbt!!)
        cL(NugmanovVNbt!!)
        cL(FisenkoEMbt!!)
        cL(RomanenkoSVbt!!)
        cL(AphadzeNAbt!!)
        cL(PosohovaMAbt!!)
        cL(MosolCVbt!!)
        cL(ShestopalovaEAbt!!)
        cL(KlimovichNPbt!!)
        cL(SizovaKNbt!!)
        cL(VtorushinaYUAbt!!)
        cL(KaluginaSVbt!!)
        cL(ProsverennikovaSAbt!!)
        cL(MiheevaLVbt!!)
        cL(KuroedovaTAbt!!)
        cL(GorshunovaSVbt!!)
        cL(ShipunovaOVbt!!)
        cL(UzhanovaTLbt!!)
        cL(TimofeevPNbt!!)
        cL(PermyakovaLPbt!!)
        cL(RagozinaTMbt!!)
        cL(MuhamedzhanovaZBbt!!)
        cL(TulinaNBbt!!)
        cL(VergunovaTZbt!!)
        cL(PavlovaNGbt!!)
        cL(SushkovaAAbt!!)
        cL(LitvinovaAVbt!!)
        cL(NorinaNNbt!!)
        cL(LitusAAbt!!)
        cL(AlerskayaNVbt!!)
        cL(ZvonarevaIMbt!!)
        cL(IgnatovaSMbt!!)
        cL(RashevskayaSFbt!!)
        cL(BaykinaILbt!!)
        cL(GulyaevIPbt!!)
        cL(PolishyukAAbt!!)
        cL(PopovANbt!!)
        cL(GeyerARbt!!)
        cL(AymetdinovBIbt!!)
        cL(GurulyovIAbt!!)
        cL(PetrovAMbt!!)
        cL(KuznetsovASbt!!)
        cL(PodkovirkinaVLbt!!)
        cL(SmirnovAGbt!!)
        cL(ShvetsovEVbt!!)
        cL(AT1609!!)
        cL(AT1709!!)
        cL(AT1711!!)
        cL(AT1811!!)
        cL(ATPiP1509!!)
        cL(ATPiP1609!!)
        cL(ATPiP1611!!)
        cL(DO15091!!)
        cL(DO15092!!)
        cL(DO1611!!)
        cL(DO17111!!)
        cL(DO17112!!)
        cL(DO18111!!)
        cL(DO18112!!)
        cL(KP16111!!)
        cL(KP16112!!)
        cL(KP1709!!)
        cL(KP17111!!)
        cL(KP17112!!)
        cL(KP17113!!)
        cL(KP18111!!)
        cL(KP18112!!)
        cL(KS1611!!)
        cL(OSATPiP1711!!)
        cL(OSATPiP18111!!)
        cL(OSATPiP18112!!)
        cL(PDOTT1509!!)
        cL(PDOTT1609!!)
        cL(PDOTT1709!!)
        cL(PDOTT18111!!)
        cL(PDOTT18112!!)
        cL(SSA1711!!)
        cL(SSA18111!!)
        cL(SSA18112!!)
        cL(SHO15091!!)
        cL(SHO15092!!)
    }

    private fun cL(bt: Button) {
        bt.setOnClickListener(this)
    }

    private fun setInvisible() {
        LisinaEVbt!!.visibility = View.GONE
        BorodinaSVbt!!.visibility = View.GONE
        LisinAAbt!!.visibility = View.GONE
        KalinovskayaSAbt!!.visibility = View.GONE
        ArefyevEAbt!!.visibility = View.GONE
        AyzyatovaGGbt!!.visibility = View.GONE
        GolenduhinaTRbt!!.visibility = View.GONE
        SandakovaDNbt!!.visibility = View.GONE
        CheymetovaTVbt!!.visibility = View.GONE
        SaharitovaNNbt!!.visibility = View.GONE
        VohmencevaTNbt!!.visibility = View.GONE
        LagohinAPbt!!.visibility = View.GONE
        BekshenevaGHbt!!.visibility = View.GONE
        RusakovMYUbt!!.visibility = View.GONE
        UrusovAAbt!!.visibility = View.GONE
        NugmanovVNbt!!.visibility = View.GONE
        FisenkoEMbt!!.visibility = View.GONE
        RomanenkoSVbt!!.visibility = View.GONE
        AphadzeNAbt!!.visibility = View.GONE
        PosohovaMAbt!!.visibility = View.GONE
        MosolCVbt!!.visibility = View.GONE
        ShestopalovaEAbt!!.visibility = View.GONE
        KlimovichNPbt!!.visibility = View.GONE
        SizovaKNbt!!.visibility = View.GONE
        VtorushinaYUAbt!!.visibility = View.GONE
        KaluginaSVbt!!.visibility = View.GONE
        ProsverennikovaSAbt!!.visibility = View.GONE
        MiheevaLVbt!!.visibility = View.GONE
        KuroedovaTAbt!!.visibility = View.GONE
        GorshunovaSVbt!!.visibility = View.GONE
        ShipunovaOVbt!!.visibility = View.GONE
        UzhanovaTLbt!!.visibility = View.GONE
        TimofeevPNbt!!.visibility = View.GONE
        PermyakovaLPbt!!.visibility = View.GONE
        RagozinaTMbt!!.visibility = View.GONE
        MuhamedzhanovaZBbt!!.visibility = View.GONE
        TulinaNBbt!!.visibility = View.GONE
        VergunovaTZbt!!.visibility = View.GONE
        PavlovaNGbt!!.visibility = View.GONE
        SushkovaAAbt!!.visibility = View.GONE
        LitvinovaAVbt!!.visibility = View.GONE
        NorinaNNbt!!.visibility = View.GONE
        LitusAAbt!!.visibility = View.GONE
        AlerskayaNVbt!!.visibility = View.GONE
        ZvonarevaIMbt!!.visibility = View.GONE
        IgnatovaSMbt!!.visibility = View.GONE
        RashevskayaSFbt!!.visibility = View.GONE
        BaykinaILbt!!.visibility = View.GONE
        GulyaevIPbt!!.visibility = View.GONE
        PolishyukAAbt!!.visibility = View.GONE
        PopovANbt!!.visibility = View.GONE
        GeyerARbt!!.visibility = View.GONE
        AymetdinovBIbt!!.visibility = View.GONE
        GurulyovIAbt!!.visibility = View.GONE
        PetrovAMbt!!.visibility = View.GONE
        KuznetsovASbt!!.visibility = View.GONE
        PodkovirkinaVLbt!!.visibility = View.GONE
        SmirnovAGbt!!.visibility = View.GONE
        ShvetsovEVbt!!.visibility = View.GONE
        AT1609!!.visibility = View.GONE
        AT1709!!.visibility = View.GONE
        AT1711!!.visibility = View.GONE
        AT1811!!.visibility = View.GONE
        ATPiP1509!!.visibility = View.GONE
        ATPiP1609!!.visibility = View.GONE
        ATPiP1611!!.visibility = View.GONE
        DO15091!!.visibility = View.GONE
        DO15092!!.visibility = View.GONE
        DO1611!!.visibility = View.GONE
        DO17111!!.visibility = View.GONE
        DO17112!!.visibility = View.GONE
        DO18111!!.visibility = View.GONE
        DO18112!!.visibility = View.GONE
        KP16111!!.visibility = View.GONE
        KP16112!!.visibility = View.GONE
        KP1709!!.visibility = View.GONE
        KP17111!!.visibility = View.GONE
        KP17112!!.visibility = View.GONE
        KP17113!!.visibility = View.GONE
        KP18111!!.visibility = View.GONE
        KP18112!!.visibility = View.GONE
        KS1611!!.visibility = View.GONE
        OSATPiP1711!!.visibility = View.GONE
        OSATPiP18111!!.visibility = View.GONE
        OSATPiP18112!!.visibility = View.GONE
        PDOTT1509!!.visibility = View.GONE
        PDOTT1609!!.visibility = View.GONE
        PDOTT1709!!.visibility = View.GONE
        PDOTT18111!!.visibility = View.GONE
        PDOTT18112!!.visibility = View.GONE
        SSA1711!!.visibility = View.GONE
        SSA18111!!.visibility = View.GONE
        SSA18112!!.visibility = View.GONE
        SHO15091!!.visibility = View.GONE
        SHO15092!!.visibility = View.GONE
    }

    private fun setTeacherVisible() {
        LisinaEVbt!!.visibility = View.VISIBLE
        BorodinaSVbt!!.visibility = View.VISIBLE
        LisinAAbt!!.visibility = View.VISIBLE
        KalinovskayaSAbt!!.visibility = View.VISIBLE
        ArefyevEAbt!!.visibility = View.VISIBLE
        AyzyatovaGGbt!!.visibility = View.VISIBLE
        GolenduhinaTRbt!!.visibility = View.VISIBLE
        SandakovaDNbt!!.visibility = View.VISIBLE
        CheymetovaTVbt!!.visibility = View.VISIBLE
        SaharitovaNNbt!!.visibility = View.VISIBLE
        VohmencevaTNbt!!.visibility = View.VISIBLE
        LagohinAPbt!!.visibility = View.VISIBLE
        BekshenevaGHbt!!.visibility = View.VISIBLE
        RusakovMYUbt!!.visibility = View.VISIBLE
        UrusovAAbt!!.visibility = View.VISIBLE
        NugmanovVNbt!!.visibility = View.VISIBLE
        FisenkoEMbt!!.visibility = View.VISIBLE
        RomanenkoSVbt!!.visibility = View.VISIBLE
        AphadzeNAbt!!.visibility = View.VISIBLE
        PosohovaMAbt!!.visibility = View.VISIBLE
        MosolCVbt!!.visibility = View.VISIBLE
        ShestopalovaEAbt!!.visibility = View.VISIBLE
        KlimovichNPbt!!.visibility = View.VISIBLE
        SizovaKNbt!!.visibility = View.VISIBLE
        VtorushinaYUAbt!!.visibility = View.VISIBLE
        KaluginaSVbt!!.visibility = View.VISIBLE
        ProsverennikovaSAbt!!.visibility = View.VISIBLE
        MiheevaLVbt!!.visibility = View.VISIBLE
        KuroedovaTAbt!!.visibility = View.VISIBLE
        GorshunovaSVbt!!.visibility = View.VISIBLE
        ShipunovaOVbt!!.visibility = View.VISIBLE
        UzhanovaTLbt!!.visibility = View.VISIBLE
        TimofeevPNbt!!.visibility = View.VISIBLE
        PermyakovaLPbt!!.visibility = View.VISIBLE
        RagozinaTMbt!!.visibility = View.VISIBLE
        MuhamedzhanovaZBbt!!.visibility = View.VISIBLE
        TulinaNBbt!!.visibility = View.VISIBLE
        VergunovaTZbt!!.visibility = View.VISIBLE
        PavlovaNGbt!!.visibility = View.VISIBLE
        SushkovaAAbt!!.visibility = View.VISIBLE
        LitvinovaAVbt!!.visibility = View.VISIBLE
        NorinaNNbt!!.visibility = View.VISIBLE
        LitusAAbt!!.visibility = View.VISIBLE
        AlerskayaNVbt!!.visibility = View.VISIBLE
        ZvonarevaIMbt!!.visibility = View.VISIBLE
        IgnatovaSMbt!!.visibility = View.VISIBLE
        RashevskayaSFbt!!.visibility = View.VISIBLE
        BaykinaILbt!!.visibility = View.VISIBLE
        GulyaevIPbt!!.visibility = View.VISIBLE
        PolishyukAAbt!!.visibility = View.VISIBLE
        PopovANbt!!.visibility = View.VISIBLE
        GeyerARbt!!.visibility = View.VISIBLE
        AymetdinovBIbt!!.visibility = View.VISIBLE
        GurulyovIAbt!!.visibility = View.VISIBLE
        PetrovAMbt!!.visibility = View.VISIBLE
        KuznetsovASbt!!.visibility = View.VISIBLE
        PodkovirkinaVLbt!!.visibility = View.VISIBLE
        SmirnovAGbt!!.visibility = View.VISIBLE
        ShvetsovEVbt!!.visibility = View.VISIBLE
    }

    private fun setGroupVisible() {
        AT1609!!.visibility = View.VISIBLE
        AT1709!!.visibility = View.VISIBLE
        AT1711!!.visibility = View.VISIBLE
        AT1811!!.visibility = View.VISIBLE
        ATPiP1509!!.visibility = View.VISIBLE
        ATPiP1609!!.visibility = View.VISIBLE
        ATPiP1611!!.visibility = View.VISIBLE
        DO15091!!.visibility = View.VISIBLE
        DO15092!!.visibility = View.VISIBLE
        DO1611!!.visibility = View.VISIBLE
        DO17111!!.visibility = View.VISIBLE
        DO17112!!.visibility = View.VISIBLE
        DO18111!!.visibility = View.VISIBLE
        DO18112!!.visibility = View.VISIBLE
        KP16111!!.visibility = View.VISIBLE
        KP16112!!.visibility = View.VISIBLE
        KP1709!!.visibility = View.VISIBLE
        KP17111!!.visibility = View.VISIBLE
        KP17112!!.visibility = View.VISIBLE
        KP17113!!.visibility = View.VISIBLE
        KP18111!!.visibility = View.VISIBLE
        KP18112!!.visibility = View.VISIBLE
        KS1611!!.visibility = View.VISIBLE
        OSATPiP1711!!.visibility = View.VISIBLE
        OSATPiP18111!!.visibility = View.VISIBLE
        OSATPiP18112!!.visibility = View.VISIBLE
        PDOTT1509!!.visibility = View.VISIBLE
        PDOTT1609!!.visibility = View.VISIBLE
        PDOTT1709!!.visibility = View.VISIBLE
        PDOTT18111!!.visibility = View.VISIBLE
        PDOTT18112!!.visibility = View.VISIBLE
        SSA1711!!.visibility = View.VISIBLE
        SSA18111!!.visibility = View.VISIBLE
        SSA18112!!.visibility = View.VISIBLE
        SHO15091!!.visibility = View.VISIBLE
        SHO15092!!.visibility = View.VISIBLE
    }

    public override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener(authListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth!!.removeAuthStateListener(authListener!!)
        }
    }
}
