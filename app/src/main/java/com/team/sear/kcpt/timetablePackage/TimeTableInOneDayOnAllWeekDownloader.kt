package com.team.sear.kcpt.timetablePackage

import android.annotation.SuppressLint
import android.os.Handler
import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class TimeTableInOneDayOnAllWeekDownloader {
    private lateinit var studentAdapter: StudentLessonAllWeekAdapter
    private lateinit var teacherAdapter: TeacherLessonAdapter

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null

    fun enable(lessons: ArrayList<Lesson?>, day: String, lessonRecycler: RecyclerView, noDataTv: TextView, auth: FirebaseAuth, user: FirebaseUser){
      try{  enableDownloader(lessons, day, lessonRecycler, noDataTv, auth,user)        } catch (e: Exception) {
      }
    }

    private fun enableDownloader(lessons: ArrayList<Lesson?>, day: String, lessonRecycler: RecyclerView, noDataTv: TextView, auth: FirebaseAuth, user: FirebaseUser) {
        database = FirebaseDatabase.getInstance()
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user.uid)
                .child("groupOrTeacherName")

        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val groupOrTeacherName = dataSnapshot.getValue(String::class.java)
                        database = FirebaseDatabase.getInstance()
                        ref = database!!.reference.child("Учреждения")
                                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                                .child("Расписание")
                                .child(groupOrTeacherName!!)
                                .child(day)
                        /*lessons.clear()*/
                        setAdapter(lessons,lessonRecycler,user)
                        updateList(groupOrTeacherName,lessons, day, lessonRecycler, noDataTv, user)
                        checkIfEmpty(lessonRecycler, noDataTv, lessons)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    private fun getItemIndex(lessons: ArrayList<Lesson?>): Int {
        return lessons.size
    }

    private fun setNotifyItemChanged(pos: Int, lessons: ArrayList<Lesson?>, user: FirebaseUser) {
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user.uid)
                .child("status")

        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val status = dataSnapshot.getValue(String::class.java)
                        if (status == "Группы") {
                            studentAdapter = StudentLessonAllWeekAdapter(lessons)
                            studentAdapter.notifyItemChanged(pos)
                        }
                        if (status == "Преподаватели") {
                            teacherAdapter = TeacherLessonAdapter(lessons)
                            teacherAdapter.notifyItemChanged(pos)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    private fun setAdapter(lessons: ArrayList<Lesson?>, lessonRecycler: RecyclerView, user: FirebaseUser) {
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user.uid)
                .child("status")

        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val status = dataSnapshot.getValue(String::class.java)
                        if (status == "Группы") {
                            lessonRecycler.adapter = StudentLessonAllWeekAdapter(lessons)
                        }
                        if (status == "Преподаватели") {
                            lessonRecycler.adapter = TeacherLessonAdapter(lessons)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    private fun updateList(groupOrTeacherName: String, lessons: ArrayList<Lesson?>, day: String, lessonRecycler: RecyclerView, noDataTv: TextView, user: FirebaseUser) {
        database = FirebaseDatabase.getInstance()
        ref = database!!.reference.child("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("Расписание")
                .child(groupOrTeacherName)
                .child(day)

        ref!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(datasnapshot: DataSnapshot, p1: String?) {
                val lesson = datasnapshot.getValue(Lesson::class.java)
                lessons.add(lesson)
                setNotifyDataSet(lessons,user)
                checkIfEmpty(lessonRecycler, noDataTv, lessons)
            }

            override fun onChildChanged(datasnapshot: DataSnapshot, p1: String?) {
                val lesson: Lesson? = datasnapshot.getValue(Lesson::class.java)
                val index: Int = getItemIndex(lessons)

                lessons[index] = lesson
                setNotifyItemChanged(index, lessons,user)
            }

            override fun onChildRemoved(datasnapshot: DataSnapshot) {
            }

            override fun onChildMoved(datasnapshot: DataSnapshot, p1: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    private fun setNotifyDataSet(lessons: ArrayList<Lesson?>, user: FirebaseUser) {
        ref = database!!.getReference("Учреждения")
                .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
                .child("users")
                .child(user.uid)
                .child("status")

        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val status = dataSnapshot.getValue(String::class.java)
                        if (status == "Группы") {
                            studentAdapter = StudentLessonAllWeekAdapter(lessons)
                            studentAdapter.notifyDataSetChanged()
                        }
                        if (status == "Преподаватели") {
                            teacherAdapter = TeacherLessonAdapter(lessons)
                            teacherAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
    }

    private fun checkIfEmpty(lessonRecycler: RecyclerView, noDataTv: TextView, lessons: ArrayList<Lesson?>) {
        if (lessons.isEmpty()) {
            lessonRecycler.visibility = View.INVISIBLE
            noDataTv.visibility = View.VISIBLE
        } else {
            lessonRecycler.visibility = View.VISIBLE
            noDataTv.visibility = View.INVISIBLE
        }
    }
}