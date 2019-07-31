package com.team.sear.kcpt.timetablePackage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.*
import com.team.sear.kcpt.R


class LessonAdapter(private val lessons: ArrayList<Lesson?>) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {
    private lateinit var itemView: View
    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null



    override fun getItemCount() = lessons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
        if (getAnybodyName() == "STUDENT") {
            holder.lessonNumTV!!.text = lessons[p]!!.lessonNum
            holder.lessonTV!!.text = lessons[p]!!.lesson
            holder.roomNumTV!!.text = lessons[p]!!.roomNum
            holder.anybodyNameTV!!.text = lessons[p]!!.teacherName
            holder.lessonTimeTV!!.text = lessons[p]!!.lessonTime
        }
        if (getAnybodyName() == "TEACHER") {
            holder.lessonNumTV!!.text = lessons[p]!!.lessonNum
            holder.lessonTV!!.text = lessons[p]!!.lesson
            holder.roomNumTV!!.text = lessons[p]!!.roomNum
            holder.anybodyNameTV!!.text = lessons[p]!!.groupName
            holder.lessonTimeTV!!.text = lessons[p]!!.lessonTime
        }
        //Xijwiodfaskfoen334
        /* database = FirebaseDatabase.getInstance()

         ref = database!!.getReference("newTimeTable").child("Xijwiodfaskfoen334").child("lesson")
         ref!!.addValueEventListener(
                 object : ValueEventListener {
                     override fun onDataChange(dataSnapshot: DataSnapshot) {
                         holder.titletv?.text  = dataSnapshot.getValue(String::class.java)
                     }

                     override fun onCancelled(error: DatabaseError) {
                     }
                 })
         //holder.titletv?.text = lessons[position]?.lessonNum*/
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lessonNumTV: TextView? = null
        var lessonTV: TextView? = null
        var roomNumTV: TextView? = null
        var anybodyNameTV: TextView? = null
        var lessonTimeTV: TextView? = null
        init {
            lessonNumTV = itemView.findViewById(R.id.lessonNumTV)
            lessonTV = itemView.findViewById(R.id.lessonTV)
            roomNumTV = itemView.findViewById(R.id.roomNumTV)
            anybodyNameTV = itemView.findViewById(R.id.anybodyNameTV)
            lessonTimeTV = itemView.findViewById(R.id.lessonTimeTV)
        }
    }
    private fun getAnybodyName(): String {
        return "STUDENT"
    }

}