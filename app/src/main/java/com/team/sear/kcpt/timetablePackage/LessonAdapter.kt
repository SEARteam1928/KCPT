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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Xijwiodfaskfoen334
        database = FirebaseDatabase.getInstance()

        ref = database!!.getReference("newTimeTable").child("Xijwiodfaskfoen334").child("lesson")
        ref!!.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        holder.titletv?.text  = dataSnapshot.getValue(String::class.java)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        //holder.titletv?.text = lessons[position]?.lessonNum
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titletv: TextView? = null
        init {
            titletv = itemView.findViewById(R.id.timetableTitle)
        }
    }
}