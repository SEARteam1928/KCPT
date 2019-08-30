@file:Suppress("NAME_SHADOWING")

package com.team.sear.kcpt.oldclasses

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.team.sear.kcpt.NewTimeTableFrag
import com.team.sear.kcpt.R
import com.team.sear.kcpt.WeatherFrag
import com.team.sear.kcpt.objects.SearchModel
import com.team.sear.kcpt.objects.SelectTimeTable
import com.team.sear.kcpt.timetablefragments.*
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener


class Navigate : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var ttFrag: TimeTableFragment
    private lateinit var ttFragNew: NewTimeTableFrag
    private lateinit var zvonkiFrag: ZvonkiFrag
    private lateinit var changesFrag: ChangesFrag
    private lateinit var feedbackFrag: FeedbackFrag
    private lateinit var dfrag: DevelopersFrag
    private lateinit var weatherFrag: WeatherFrag
    private lateinit var newsFrag: NewsFrag


    private lateinit var selectTimeTable: SelectTimeTable
    /*    private lateinit var fab: FloatingActionButton
        */
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var mAuth: FirebaseAuth? = null
    internal var user: FirebaseUser? = null
    private lateinit var navigateIntent: Intent
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigate)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navigateIntent = Intent(this, Navigate::class.java)
        try {
            ttFrag = TimeTableFragment()
            ttFragNew = NewTimeTableFrag()
            val ftrans = supportFragmentManager.beginTransaction()
            ftrans.replace(R.id.navigate, ttFragNew)
            ftrans.commit()
        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }
        try {
            zvonkiFrag = ZvonkiFrag()
            changesFrag = ChangesFrag()
            feedbackFrag = FeedbackFrag()
            dfrag = DevelopersFrag()
            selectTimeTable = SelectTimeTable()
            weatherFrag = WeatherFrag()
            newsFrag = NewsFrag()

        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Неизвестная ошибка!", Toast.LENGTH_SHORT).show()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
            } else {

            }
            this@Navigate.updateUI(user)
        }

        /*  fab = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "В разработке...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigate, menu)
        return true
    }

    private fun intentNavigate() {
        val navigateIntent = Intent(this, Navigate::class.java)
        startActivity(navigateIntent)
        finish()
    }

    private fun setSItem(groupStr: String) {
        selectTimeTable.setStudent(groupStr, mAuth)
        intentNavigate()
    }

    private fun setTItem(teacherName: String) {
        selectTimeTable.setTeacher(teacherName, mAuth)
        intentNavigate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                val navigateIntent = Intent(this, Navigate::class.java)
                startActivity(navigateIntent)
                finish()
            }
            R.id.timetableItem -> {
                val ttIntent = Intent(this@Navigate,MainActivity::class.java)
                startActivity(ttIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData(): ArrayList<SearchModel> {
        return ArrayList<SearchModel>().also {
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

            it.add(SearchModel("Айзятова Г. Г."))
            it.add(SearchModel("Айметдинов Б. И."))
            it.add(SearchModel("Алерская Н. В."))
            it.add(SearchModel("Апхадзе Н. А."))
            it.add(SearchModel("Арефьев Е. А."))
            it.add(SearchModel("Байкина И. Л."))
            it.add(SearchModel("Бекшенева Г. Х."))
            it.add(SearchModel("Бородина С. В."))
            it.add(SearchModel("Вергунова Т. З."))
            it.add(SearchModel("Вохменцева Т. Н."))
            it.add(SearchModel("Вторушина Ю. А."))
            it.add(SearchModel("Гейер А. Р."))
            it.add(SearchModel("Голендухина Т. Р."))
            it.add(SearchModel("Горшунова С. В."))
            it.add(SearchModel("Гуляев И. П."))
            it.add(SearchModel("Гурулев И. А."))
            it.add(SearchModel("Звонарева И. М."))
            it.add(SearchModel("Игнатова С. М."))
            it.add(SearchModel("Калиновская С. А."))
            it.add(SearchModel("Калугина С. В."))
            it.add(SearchModel("Климович Н. П."))
            it.add(SearchModel("Кузнецов А. С."))
            it.add(SearchModel("Куроедова Т. А."))
            it.add(SearchModel("Лагохин А. П."))
            it.add(SearchModel("Лисин А. А."))
            it.add(SearchModel("Лисина Е. В."))
            it.add(SearchModel("Литвинова А. В."))
            it.add(SearchModel("Литус А. А."))
            it.add(SearchModel("Михеева Л. В."))
            it.add(SearchModel("Мосол С. В."))
            it.add(SearchModel("Мухамеджанова З. Б."))
            it.add(SearchModel("Норина Н. Н."))
            it.add(SearchModel("Нугманов В. Н."))
            it.add(SearchModel("Павлова Н. Г."))
            it.add(SearchModel("Пермякова Л. П."))
            it.add(SearchModel("Петров А. М."))
            it.add(SearchModel("Подковыркина В. Л."))
            it.add(SearchModel("Полищук А. А."))
            it.add(SearchModel("Попов А. Н."))
            it.add(SearchModel("Посохова М. А."))
            it.add(SearchModel("Просверенникова С. А."))
            it.add(SearchModel("Рагозина Т. М."))
            it.add(SearchModel("Рашевская С. Ф."))
            it.add(SearchModel("Романенко С. В."))
            it.add(SearchModel("Русаков М. Ю."))
            it.add(SearchModel("Сандакова Д. Н."))
            it.add(SearchModel("Сахаритова Н. Н."))
            it.add(SearchModel("Сизова К. Н."))
            it.add(SearchModel("Смирнов А. Г."))
            it.add(SearchModel("Сушкова А. А."))
            it.add(SearchModel("Тимофеев П. Н."))
            it.add(SearchModel("Тулина Н. Б."))
            it.add(SearchModel("Урусов А. А."))
            it.add(SearchModel("Ужанова Т. Л."))
            it.add(SearchModel("Фисенко Е. М."))
            it.add(SearchModel("Чейметова Т. В."))
            it.add(SearchModel("Швецов Е.В."))
            it.add(SearchModel("Шестопалова Е. А."))
            it.add(SearchModel("Шипунова О. В."))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val ftrans = supportFragmentManager.beginTransaction()

        when (id) {
            R.id.changesItem -> ftrans.replace(R.id.navigate, changesFrag)
            R.id.zvonkiItem -> ftrans.replace(R.id.navigate, zvonkiFrag)
            R.id.feedbackItem -> ftrans.replace(R.id.navigate, feedbackFrag)
            R.id.developersItem -> ftrans.replace(R.id.navigate, dfrag)
            R.id.weatherItem -> ftrans.replace(R.id.navigate, weatherFrag)
            R.id.newsItem -> ftrans.replace(R.id.navigate, newsFrag)
            R.id.action_select_timetable -> {
                SimpleSearchDialogCompat(this@Navigate, "Поиск", "Что вы хотите найти?", null,
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
                    Toast.makeText(this@Navigate, item.title, Toast.LENGTH_SHORT).show()
                    baseSearchDialogCompat.dismiss()
                }).show()
            }
        }

        ftrans.commit()

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun signOut() {
        try {
            database = FirebaseDatabase.getInstance()
            user = mAuth!!.currentUser
            myRef = database.getReference("exitInAccount").child(user!!.uid)
            myRef.setValue("SIGNEDOUT")

            database = FirebaseDatabase.getInstance()
            user = mAuth!!.currentUser
            myRef = database.getReference("users").child(user!!.uid).child("signedIn")
            myRef.setValue("SIGNEDOUT")

            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            mAuth!!.signOut()

            finish()
        } catch (e: Exception) {
            Toast.makeText(this@Navigate, "Undefinded error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            val registrationIntent = Intent(this, Registration::class.java)
            startActivity(registrationIntent)
            finish()
        }
    }
}
