package com.team.sear.kcpt.timetablePackage

import java.util.*
import kotlin.collections.HashMap

class Lesson {
    var lesson: String? = null
    var lessonNum: String? = null
    var dayofweek: String? = null
    var groupName: String? = null
    var teacherName: String? = null
    var roomNum: String? = null
    var lessonTime: String? = null
    var groupOrSubGroup: String? = null
    constructor(){}

   constructor(lesson: String,
                 lessonNum: String,
                 dayofweek: String,
                 groupName: String,
                 teacherName: String,
                 roomNum: String,
                 lessonTime: String,
                 groupOrSubGroup: String){
        this.lesson = lesson
        this.lessonNum = lessonNum
        this.dayofweek = dayofweek
        this.groupName = groupName
        this.teacherName = teacherName
        this.roomNum = roomNum
        this.lessonTime = lessonTime
        this.groupOrSubGroup = groupOrSubGroup
    }
}
/*    fun toMap(): Map<String, Any>{
        var result: HashMap<String, Any> = HashMap()

        result["lesson"] = lesson!!
        result["lessonNum"] = lessonNum!!
        result["dayofweek"] = dayofweek!!
        result["groupName"] = groupName!!
        result["teacherName"] = teacherName!!
        result["roomNum"] = roomNum!!
        result["lessonTime"] = lessonTime!!
        result["groupOrSubGroup"] = groupOrSubGroup!!

        return result
    }*/