const fs = require("fs");
var firebase = require("firebase");
var config = {
    apiKey: "AIzaSyBcwAn5xQ1eeiFiV6TpJnehlMpADCwskIU",
    authDomain: "kcpt-1928.firebaseapp.com",
    databaseURL: "https://kcpt-1928.firebaseio.com",
    projectId: "kcpt-1928",
    storageBucket: "kcpt-1928.appspot.com",
    messagingSenderId: "846506539351"
};
firebase.initializeApp(config);

var AT1609 = "AT1609";
var AT1709 = "AT1709";
var AT1711 = "AT1711";
var AT1811 = "AT1811";
var AT1911 = "AT1911";

var ATPiP1609 = "ATPiP1609";

var DO17111 = "DO17111";
var DO17112 = "DO17112";
var DO18111 = "DO18111";
var DO18112 = "DO18112";
var DO19111 = "DO19111";
var DO19112 = "DO19112";

var KP1709 = "KP1709";
var KP17111 = "KP17111";
var KP17112 = "KP17112";
var KP17113 = "KP17113";
var KP18111 = "KP18111";
var KP18112 = "KP18112";
var KP19111 = "KP19111";
var KP19112 = "KP19112";
var KP19113 = "KP19113";

var ISiP19111 = "ISiP19111";
var ISiP19112 = "ISiP19112";

var OSATPiP1711 = "OSATPiP1711";
var OSATPiP18111 = "OSATPiP18111";
var OSATPiP18112 = "OSATPiP18112";
var OSATPiP19111 = "OSATPiP19111";
var OSATPiP19112 = "OSATPiP19112";


var PDOTT1609 = "PDOTT1609";
var PDOTT1709 = "PDOTT1709";
var PDOTT18111 = "PDOTT18111";
var PDOTT18112 = "PDOTT18112";
var PDOTT1911 = "PDOTT1911";

var SSA1711 = "SSA1711";
var SSA18111 = "SSA18111";
var SSA18112 = "SSA18112";
var SSA19111 = "SSA19111";
var SSA19112 = "SSA19112";
var SSA19113 = "SSA19113";

var lineReader;
var dayOfWeek;
var lessonNum;
var lessonName;
var teacherName;
var roomNum;
var subGroup;
var lessonStr;
var teacher;
var teacherLessonStr;
var NameFile;


var Lesson = function (
    dayofweekStr,
    groupNameStr,
    groupOrSubroupStr,
    lessonStr,
    lessonNumStr,
    lessonTimeStr,
    roomNumStr,
    teacherNameStr) {

    this.dayofweekStr = dayofweekStr;
    this.groupNameStr = groupNameStr;
    this.groupOrSubroupStr = groupOrSubroupStr;
    this.lessonStr = lessonStr;
    this.lessonNumStr = lessonNumStr;
    this.lessonTimeStr = lessonTimeStr;
    this.roomNumStr = roomNumStr;
    this.teacherNameStr = teacherNameStr;
}

var lesson;

startMainFunctions();

function main(fileName) {
    deleteDataOnFirebase("Расписание");
    sendGroupNameInFirebase(fileName);
    readFile(fileName);
    parseDataOnFile(fileName);
}

function startMainFunctions() {
    deleteDataOnFirebase("Группы");
    deleteDataOnFirebase("Преподаватели");

    main(AT1609);
    main(AT1709);
    main(AT1711);
    main(AT1811);
    main(AT1911);

    main(ATPiP1609);

    main(DO17111);
    main(DO17112);
    main(DO18111);
    main(DO18112);
    main(DO19111);
    main(DO19112);

    main(KP1709);
    main(KP17111);
    main(KP17112);
    main(KP17113);
    main(KP18111);
    main(KP18112);
    main(KP19111);
    main(KP19112);
    main(KP19113);

    main(ISiP19111);
    main(ISiP19112);

    main(OSATPiP1711);
    main(OSATPiP18111);
    main(OSATPiP18112);
    main(OSATPiP19111);
    main(OSATPiP19112);

    main(PDOTT1609);
    main(PDOTT1709);
    main(PDOTT18111);
    main(PDOTT18112);
    main(PDOTT1911);

    main(SSA1711);
    main(SSA18111);
    main(SSA18112);
    main(SSA19111);
    main(SSA19112);
    main(SSA19113);
}

function sendLesson(Lesson, anybodyName, day, num) {
    var database = firebase.database();
    var sendLessonVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Расписание")
        .child(anybodyName)
        .child(day)
        .child("lesson0" + num)
        .set({
            dayofweek: Lesson.dayofweekStr,
            groupName: Lesson.groupNameStr,
            groupOrSubGroup: Lesson.groupOrSubroupStr,
            lesson: Lesson.lessonStr,
            lessonNum: Lesson.lessonNumStr,
            lessonTime: Lesson.lessonTimeStr,
            roomNum: Lesson.roomNumStr,
            teacherName: Lesson.teacherNameStr
        });
}

function sendLessonOnAllWeek(Lesson, anybodyName, day, num) {
    var database = firebase.database();
    var sendLessonVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Расписание")
        .child(anybodyName)
        .child("AllWeek")
        .child(numOfDay(day) + "lesson0" + num)
        .set({
            dayofweek: Lesson.dayofweekStr,
            groupName: Lesson.groupNameStr,
            groupOrSubGroup: Lesson.groupOrSubroupStr,
            lesson: Lesson.dayofweekStr + "\n\n" + Lesson.lessonStr,
            lessonNum: Lesson.lessonNumStr,
            lessonTime: Lesson.lessonTimeStr,
            roomNum: Lesson.roomNumStr,
            teacherName: Lesson.teacherNameStr
        });
}


function sendTeacherLesson(Lesson, anybodyName, day, num) {
    var database = firebase.database();
    var sendLessonVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Расписание")
        .child(anybodyName)
        .child(day)
        .child("lesson0" + num)
        .set({
            dayofweek: Lesson.dayofweekStr,
            groupName: Lesson.groupNameStr,
            groupOrSubGroup: Lesson.groupOrSubroupStr,
            lesson: Lesson.lessonStr,
            lessonNum: Lesson.lessonNumStr,
            lessonTime: Lesson.lessonTimeStr,
            roomNum: Lesson.roomNumStr,
            teacherName: Lesson.teacherNameStr
        });
}

function sendTeacherLessonOnAllWeek(Lesson, anybodyName, day, num) {
    var database = firebase.database();
    var sendLessonVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Расписание")
        .child(anybodyName)
        .child("AllWeek")
        .child(numOfDay(day) + "lesson0" + num)
        .set({
            dayofweek: Lesson.dayofweekStr,
            groupName: Lesson.groupNameStr,
            groupOrSubGroup: Lesson.groupOrSubroupStr,
            lesson: Lesson.dayofweekStr + "\n\n" + Lesson.lessonStr,
            lessonNum: Lesson.lessonNumStr,
            lessonTime: Lesson.lessonTimeStr,
            roomNum: Lesson.roomNumStr,
            teacherName: Lesson.teacherNameStr
        });
}


function readFile(fileName) {
    lineReader = require('readline').createInterface({
        input: require('fs').createReadStream(fileName + '.txt')
    });
}


function parseDataOnFile(fileName) {
    var i = 0;
    var lineName = "";

    lineReader.on('line', function (line) {
        i++;
        if (i === 7) {
            i = 1;
            NameFile = fileName;
            lesson = new Lesson(dayOfWeek, selectGroupName(), subGroup, lessonName, lessonNum, lessonTimeSet(lessonNum, dayOfWeek), roomNum, teacherName);

            sendLesson(lesson, selectGroupName(), dayOfWeek, lessonNum);
            sendLessonOnAllWeek(lesson, selectGroupName(), dayOfWeek, lessonNum);

            sendTeacherNameInFirebase(teacherName);

            sendTeacherLesson(lesson, teacherName, dayOfWeek, lessonNum);
            sendTeacherLessonOnAllWeek(lesson, teacherName, dayOfWeek, lessonNum);

/*
            console.log(lesson);
*/
        }
        if (i === 1) {
            lineName = "subGroup";
            subGroup = line;
        }
        if (i === 2) {
            lineName = "dayweek";
            dayOfWeek = line;
        }
        if (i === 3) {
            lineName = "lessonNum";
            lessonNum = line;
        }
        if (i === 4) {
            lineName = "lessonName";
            lessonName = line;
        }
        if (i === 5) {
            lineName = "teacherName";
            teacherName = line;
            teacher = selectTeacher(teacherName);
            teacherName = teacherName.split(".").join(' ');
        }
        if (i === 6) {
            lineName = "roomNum";
            roomNum = line;
        }
    });

}

function numOfDay(day) {
    if (day === "Понедельник") {
        return "day01";
    }
    if (day === "Вторник") {
        return "day02";
    }
    if (day === "Среда") {
        return "day03";
    }
    if (day === "Четверг") {
        return "day04";
    }
    if (day === "Пятница") {
        return "day05";
    }
    if (day === "Суббота") {
        return "day06";
    }
}

function lessonTimeSet(lessonN, dayofweek) {
    if (dayofweek === "Суббота") {

        if (lessonN === "01") {
            return "8:15\n9:00";
        }
        if (lessonN === "02") {
            return "9:00\n9:45";
        }
        if (lessonN === "03") {
            return "09:50\n10:35";
        }
        if (lessonN === "04") {
            return "10:35\n11:20";
        }
        if (lessonN === "05") {
            return "11:50\n12:35";
        }
        if (lessonN === "06") {
            return "12:35\n13:20";
        }
        if (lessonN === "07") {
            return "13:30\n14:15";
        }
        if (lessonN === "08") {
            return "14:15\n15:00";
        }
        if (lessonN === "09") {
            return "15:10\n15:55";
        }
        if (lessonN === "10") {
            return "15:55\n16:40";
        }
        if (lessonN === "11") {
            return "16:45+\n17:30";
        }
        if (lessonN === "12") {
            return "17:30\n18:15";
        }

    } else {

        if (lessonN === "01") {
            return "8:15\n9:00";
        }
        if (lessonN === "02") {
            return "9:00\n9:45";
        }
        if (lessonN === "03") {
            return "10:00\n10:45";
        }
        if (lessonN === "04") {
            return "10:45\n11:30";
        }
        if (lessonN === "05") {
            return "12:00\n12:45";
        }
        if (lessonN === "06") {
            return "12:45\n13:30";
        }
        if (lessonN === "07") {
            return "14:00\n14:45";
        }
        if (lessonN === "08") {
            return "14:45\n15:30";
        }
        if (lessonN === "09") {
            return "15:45\n16:30";
        }
        if (lessonN === "10") {
            return "16:30\n17:15";
        }
        if (lessonN === "11") {
            return "17:25\n18:10";
        }
        if (lessonN === "12") {
            return "18:10\n18:55";
        }
    }
}

function deleteDataOnFirebase(child) {
    var database = firebase.database();
    var deleteDataOnFirebaseVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child(child)
        .set({
            deleteData: "deleted"
        });
}

function sendGroupNameInFirebase(fileName) {
    NameFile = fileName;
    var database = firebase.database();
    var deleteDataOnFirebaseVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Группы")
        .child(fileName)
        .set(selectGroupName());
}

function sendTeacherNameInFirebase(teacherName) {
    NameFile = teacherName;
    var database = firebase.database();
    var deleteDataOnFirebaseVar = database
        .ref("Учреждения")
        .child("ГАПОУ ТО \"Колледж цифровых и педагогических технологий\"\"")
        .child("Преподаватели")
        .child(teacher)
        .set(teacherName);
}

function selectGroupName() {
    if (NameFile === AT1709) {
        return "АТ 17-09"
    }
    if (NameFile === AT1711) {
        return "АТ 17-11"
    }
    if (NameFile === AT1811) {
        return "АТ 18-11"
    }
    if (NameFile === AT1911) {
        return "АТ 19-11"
    }
    if (NameFile === AT1609) {
        return "АТ 16-09"
    }

    if (NameFile === ATPiP1609) {
        return "АТПиП 16-09"
    }

    if (NameFile === DO17111) {
        return "ДО 17-11-1"
    }
    if (NameFile === DO17112) {
        return "ДО 17-11-2"
    }
    if (NameFile === DO18111) {
        return "ДО 18-11-1"
    }
    if (NameFile === DO18112) {
        return "ДО 18-11-2"
    }
    if (NameFile === DO19111) {
        return "ДО 19-11-1"
    }
    if (NameFile === DO19112) {
        return "ДО 19-11-2"
    }

    if (NameFile === ISiP19111) {
        return "ИСиП 19-11-1"
    }
    if (NameFile === ISiP19112) {
        return "ИСиП 19-11-2"
    }

    if (NameFile === KP1709) {
        return "КП 17-09"
    }
    if (NameFile === KP17111) {
        return "КП 17-11-1"
    }
    if (NameFile === KP17112) {
        return "КП 17-11-2"
    }
    if (NameFile === KP17113) {
        return "КП 17-11-3"
    }
    if (NameFile === KP18111) {
        return "КП 18-11-1"
    }
    if (NameFile === KP18112) {
        return "КП 18-11-2"
    }
    if (NameFile === KP19111) {
        return "КП 19-11-1"
    }
    if (NameFile === KP19112) {
        return "КП 19-11-2"
    }
    if (NameFile === KP19113) {
        return "КП 19-11-3"
    }

    if (NameFile === OSATPiP1711) {
        return "ОСАТПиП 17-11"
    }
    if (NameFile === OSATPiP18111) {
        return "ОСАТПиП 18-11-1"
    }
    if (NameFile === OSATPiP18112) {
        return "ОСАТПиП 18-11-2"
    }
    if (NameFile === OSATPiP19111) {
        return "ОСАТПиП 19-11-1"
    }
    if (NameFile === OSATPiP19112) {
        return "ОСАТПиП 19-11-2"
    }

    if (NameFile === PDOTT1609) {
        return "ПДО ТТ 16-09"
    }
    if (NameFile === PDOTT1709) {
        return "ПДО ТТ 17-09"
    }
    if (NameFile === PDOTT18111) {
        return "ПДО ТТ 18-11-1"
    }
    if (NameFile === PDOTT18112) {
        return "ПДО ТТ 18-11-2"
    }
    if (NameFile === PDOTT1911) {
        return "ПДО ТТ 19-11"
    }


    if (NameFile === SSA1711) {
        return "ССА 17-11"
    }
    if (NameFile === SSA18111) {
        return "ССА 18-11-1"
    }
    if (NameFile === SSA18112) {
        return "ССА 18-11-2"
    }
    if (NameFile === SSA19111) {
        return "ССА 19-11-1"
    }
    if (NameFile === SSA19112) {
        return "ССА 19-11-2"
    }
    if (NameFile === SSA19113) {
        return "ССА 19-11-3"
    }
}

function selectTeacher(teacherName) {
    console.log(teacherName)
    if (teacherName === "Садыков И.Р.") {
        return "СадыковИР";
    }
    if (teacherName === "вакансия") {
        return "вакансия";
    }
    if (teacherName === "Машкина В. А.") {
        return "МашкинаВА";
    }
    if (teacherName === "Тимофеев П. Н.") {
        return "ТимофеевПН";
    }
    if (teacherName === "Ложкина Е. Р.") {
        return "ЛожкинаЕР";
    }
    if (teacherName === "Ужанова Т. Л.") {
        return "УжановаТЛ";
    }
    if (teacherName === "Гейер А. Р.") {
        return "ГейерАР";
    }
    if (teacherName === "Арефьев Е. А.") {
        return "АрефьевЕА";
    }
    if (teacherName === "Никитина И.В.") {
        return "НикитинаИВ";
    }
    if (teacherName === "Айметдинов Б. И.") {
        return "АйметдиновБИ";
    }
    if (teacherName === "Михеева Л. В.") {
        return "МихееваЛВ";
    }
    if (teacherName === "Романенко С. В." || teacherName === "Романенко С.М.") {
        return "РоманенкоСВ";
    }
    if (teacherName === "Кузнецов А. С.") {
        return "КузнецовАС";
    }
    if (teacherName === "Пушкарев А. Н.") {
        return "ПушкаревАН";
    }
    if (teacherName === "Литус А. А.") {
        return "ЛитусАА";
    }
    if (teacherName === "Новоселов Д.А.") {
        return "НовоселовДА";
    }
    if (teacherName === "Бородина С. В.") {
        return "БородинаСВ";
    }
    if (teacherName === "Дьякова Е.А.") {
        return "ДьяковаЕА";
    }
    if (teacherName === "Гуляев И.П.") {
        return "ГуляевИП";
    }
    if (teacherName === "Калугина С. В.") {
        return "КалугинаСВ";
    }
    if (teacherName === "Шестопалова Е. А.") {
        return "ШестопаловаЕА";
    }
    if (teacherName === "Мокеева А. Г.") {
        return "МокееваАГ";
    }
    if (teacherName === "Мосол С. В.") {
        return "МосолСВ";
    }
    if (teacherName === "Сизова К. Н.") {
        return "СизоваКН";
    }
    if (teacherName === "Просверенникова С. А.") {
        return "ПросверенниковаСА";
    }
    if (teacherName === "Куроедова Т. А.") {
        return "КуроедоваТА";
    }
    if (teacherName === "Посохова М. А.") {
        return "ПосоховаМА";
    }
    if (teacherName === "Сушкова А. А.") {
        return "СушковаАА";
    }
    if (teacherName === "Климович Н. П.") {
        return "КлимовичНП";
    }
    if (teacherName === "Шипунова О. В.") {
        return "ШипуноваОВ";
    }
    if (teacherName === "Горшунова С. В.") {
        return "ГоршуноваСВ";
    }
    if (teacherName === "Ситникова К.И.") {
        return "СитниковаКИ";
    }
    if (teacherName === "Андреева С. Р.") {
        return "АндрееваСР";
    }
    if (teacherName === "Терехов В.И.") {
        return "ТереховВИ";
    }
    if (teacherName === "Пермякова Л. П.") {
        return "ПермяковаЛП";
    }
    if (teacherName === "Бекшенева Г.Х.") {
        return "БекшеневаГХ";
    }
    if (teacherName === "Рагозина Т. М.") {
        return "РагозинаТМ";
    }
    if (teacherName === "Тулина Н. Б.") {
        return "ТулинаНБ";
    }
    if (teacherName === "Вергунова Т. З.") {
        return "ВергуноваТЗ";
    }
    if (teacherName === "Звонарева И. М.") {
        return "ЗвонареваИМ";
    }
    if (teacherName === "Игнатова С. М.") {
        return "ИгнатоваСМ";
    }
    if (teacherName === "Проскурякова А.А.") {
        return "ПроскуряковаАА";
    }
    if (teacherName === "Гурулев И. А.") {
        return "ГурулевИА";
    }
    if (teacherName === "Апхадзе Н. А.") {
        return "АпхадзеНА";
    }
    if (teacherName === "Рашевская С. Ф.") {
        return "РашевскаяСФ";
    }
    if (teacherName === "Полищук А. А.") {
        return "ПолищукАА";
    }else {
        return "nothing";
    }
}
