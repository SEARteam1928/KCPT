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
var AT1709 ="AT1709";
var AT1711 = "AT1711";
var AT1811 ="AT1811";
var ATPiP1509 ="ATPiP1509";
var ATPiP1609 ="ATPiP1609";
var ATPiP1611 ="ATPiP1611";
var DO15091="DO15091";
var DO15092="DO15092";
var DO1611 ="DO1611";
var DO17111="DO17111";
var DO17112="DO17112";
var DO18111="DO18111";
var DO18112="DO18112";
var KP16111="KP16111";
var KP16112="KP16112";
var KP1709 ="KP1709";
var KP17111="KP17111";
var KP17112="KP17112";
var KP17113="KP17113";
var KP18111="KP18111";
var KP18112="KP18112";
var KS1611 ="KS1611";
var OSATPiP1711 ="OSATPiP1711";
var OSATPiP18111="OSATPiP18111";
var OSATPiP18112="OSATPiP18112";
var PDOTT1509="PDOTT1509";
var PDOTT1609="PDOTT1609";
var PDOTT1709="PDOTT1709";
var PDOTT18111="PDOTT18111";
var PDOTT18112="PDOTT18112";
var SSA1711="SSA1711";
var SSA18111="SSA18111";
var SSA18112="SSA18112";
var SHO15091="SHO15091";
var SHO15092="SHO15092";
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


startMainFunctions();

function readFile(fileName){
lineReader = require('readline').createInterface({
  input: require('fs').createReadStream(fileName+'.txt')
});
}


function parseDataOnFile(fileName){
var i=0;
var lineName="";

lineReader.on('line', function (line) {
	i++;
	if(i==7){
		i=1;
		NameFile = fileName;
		lessonStr = lessonStrFun();
		teacherLessonStr = teacherLessonStrFun();
				 sendGroupsDataOnFirebase(fileName, dayOfWeek,lessonNum,lessonStr,subGroup);
				 sendTeacherDataOnFirebase(teacher,dayOfWeek,lessonNum,teacherLessonStr);
	}
if(i==1){
	lineName ="subGroup";
	subGroup = line;
}
if(i==2){
	lineName ="dayweek";
	dayOfWeek = line;
}
if(i==3){
	lineName = "lessonNum";
	lessonNum = line;
}
if(i==4){
	lineName ="lessonName";
	lessonName = line;
}
if(i==5){
	lineName ="teacherName";
	teacherName = line;
	teacher = selectTeacher(teacherName);
}
if(i==6){
	lineName ="roomNum";
	roomNum = line;
}
});

}

function sendGroupsDataOnFirebase(fileName,dayOfWeek,lessonNum,lessonStr, subGroup){
var database = firebase.database();
var exampleNodeDBSet = database.ref("timetableNew").child("groups").child(fileName).child(dayOfWeek).child(dayOfWeek+lessonNum).child(subGroup).set({
            lesson: lessonStr
        });
}


function selectTeacher(teacherName) {
       if(teacherName === "Арефьев Е. А."||teacherName === "Арефьев Е. А. "){
               return  "ArefyevEA";
               }
       if(teacherName === "Лисина Е. В."){
               return  "LisinaEV";
               }
       if(teacherName === "Бородина С. В."){
               return  "BorodinaSV";
               }
       if(teacherName === "Лисин А. А."){
               return  "LisinAA";
               }
       if(teacherName === "Калиновская С. А."){
               return  "KalinovskayaSA";
               }
       if(teacherName === "Айзятова Г. Г."){
               return  "AyzyatovaGG";
               }
       if(teacherName === "Голендухина Т. Р."){
               return  "GolenduhinaTR";
               }
       if(teacherName === "Сандакова Д. Н."){
               return  "SandakovaDN";
               }
       if(teacherName === "Чейметова Т. В."){
               return  "CheymetovaTV";
               }
       if(teacherName === "Сахаритова Н. Н."){
               return  "SaharitovaNN";
               }
       if(teacherName === "Вохменцева Т. Н."){
               return  "VohmencevaTN";
               }
       if(teacherName === "Лагохин А. П."){
               return  "LagohinAP";
               }
       if(teacherName === "Бекшенева Г. Х."    ){
               return  "BekshenevaGH";
               }
       if(teacherName === "Русаков М. Ю."    ){
               return  "RusakovMYU";
               }
       if(teacherName === "Урусов А. А."    ){
               return  "UrusovAA";
               }
       if(teacherName === "Нугманов В. Н."    ){
               return  "NugmanovVN";
               }
       if(teacherName === "Фисенко Е. М."    ){
               return  "FisenkoEM";
               }
       if(teacherName === "Романенко С. В."    ){
               return  "RomanenkoSV";
               }
       if(teacherName === "Апхадзе Н. А."    ){
               return  "AphadzeNA";
               }
       if(teacherName === "Посохова М. А."    ){
               return  "PosohovaMA";
               }
       if(teacherName === "Мосол С. В."    ){
               return  "MosolCV";
               }
       if(teacherName === "Шестопалова Е. А."    ){
               return  "ShestopalovaEA";
               }
       if(teacherName === "Климович Н. П."    ){
               return  "KlimovichNP";
               }
       if(teacherName === "Сизова К. Н."    ){
               return  "SizovaKN";
               }
       if(teacherName === "Вторушина Ю. А."    ){
               return  "VtorushinaYUA";
               }
       if(teacherName === "Калугина С. В."    ){
               return  "KaluginaSV";
               }
       if(teacherName === "Просверенникова С. А."    ){
               return  "ProsverennikovaSA";
               }
       if(teacherName === "Михеева Л. В."    ){
               return  "MiheevaLV";
               }
       if(teacherName === "Куроедова Т. А."    ){
               return  "KuroedovaTA";
               }
       if(teacherName === "Горшунова С. В."    ){
               return  "GorshunovaSV";
               }
       if(teacherName === "Шипунова О. В." ||teacherName === "Шипунова О.М."   ){
               return  "ShipunovaOV";
               }
       if(teacherName === "Ужанова Т. Л."    ){
               return  "UzhanovaTL";
               }
       if(teacherName === "Тимофеев П. Н."    ){
               return  "TimofeevPN";
               }
       if(teacherName === "Пермякова Л. П."    ){
               return  "PermyakovaLP";
               }
       if(teacherName === "Рагозина Т. М."    ){
               return  "RagozinaTM";
               }
       if(teacherName === "Мухамеджанова З. Б."    ){
               return  "MuhamedzhanovaZB";
               }
       if(teacherName === "Тулина Н. Б."    ){
               return  "TulinaNB";
               }
       if(teacherName === "Вергунова Т. З."    ){
               return  "VergunovaTZ";
               }
       if(teacherName === "Павлова Н. Г."    ){
               return  "PavlovaNG";
               }
       if(teacherName === "Сушкова А. А."    ){
               return  "SushkovaAA";
               }
       if(teacherName === "Литвинова А. В."    ){
               return  "LitvinovaAV";
               }
       if(teacherName === "Норина Н. Н."    ){
               return  "NorinaNN";
               }
       if(teacherName === "Литус А. А."    ){
               return  "LitusAA";
               }
       if(teacherName === "Алерская Н. В."    ){
               return  "AlerskayaNV";
               }
       if(teacherName === "Звонарева И. М."    ){
               return  "ZvonarevaIM";
               }
       if(teacherName === "Игнатова С. М."   ||teacherName === "Игнатова С.М." ){
               return  "IgnatovaSM";
               }
       if(teacherName === "Рашевская С. Ф."    ){
               return  "RashevskayaSF";
               }
       if(teacherName === "Байкина И. Л."    ){
               return  "BaykinaIL";
               }
       if(teacherName === "Гуляев И. П."    ){
               return  "GulyaevIP";
               }
       if(teacherName === "Полищук А. А."    ){
               return  "PolishyukAA";
               }
       if(teacherName === "Попов А.Н."    ){
               return  "PopovAN";
               }
       if(teacherName === "Гейер А.Р."    ){
               return  "GeyerAR";
               }
       if(teacherName === "Айметдинов Б. И."    ){
               return  "AymetdinovBI";
               }
       if(teacherName === "Гурулев И.А."    ){
               return  "GurulyovIA";
               }
       if(teacherName === "Петров А.М."  ||teacherName === "Петров А.Н."  ){
               return  "PetrovAM";
               }
       if(teacherName === "Кузнецов А.С."    ){
               return  "KuznetsovAS";
               }
       if(teacherName === "Подковыркина В. Л."){
               return  "PodkovirkinaVL";
               }
       if(teacherName === "Смирнов А. Г."){
               return  "SmirnovAG";
               }
       if(teacherName === "Швецов Е. В."){
               return  "ShvetsovEV";
               }
        else {
                           return "nothing";
                       }
    }

function sendTeacherDataOnFirebase(teacher, dayOfWeek, lessonNum, lessonStr) {
        var database = firebase.database();
        var sendteacher = database.ref("timetableNew").child("teachers").child(teacher).child(dayOfWeek).child(dayOfWeek+lessonNum).set({
            lesson: lessonStr
        });

    }

function lessonStrFun() {

		lessonStr = lessonName+"\n"+ teacherName+" "+roomNum;

    return lessonStr;
}

function teacherLessonStrFun(){

        groupName = selectGroupName();

        teacherLessonStr = lessonName+"\n"+ groupName+" | "+roomNum;


            return teacherLessonStr;
}

function selectGroupName(){

						if(NameFile === AT1609){
						return "АТ 16-09"}

						if(NameFile === AT1709){
						return  "АТ 17-09"}

						if(NameFile === AT1711){
						return "АТ 17-11"}

						if(NameFile === AT1811){
						return "АТ 18-11"}

						if(NameFile === ATPiP1509){
						return  "АТПиП 15-09"}

						if(NameFile === ATPiP1609){
						return   "АТПиП 16-09"}

						if(NameFile === ATPiP1611){
						return  "АТПиП 16-11"}

						if(NameFile === DO15091){
						return  "ДО 15-09-1"}

						if(NameFile === DO15092){
						return  "ДО 15-09-2"}

						if(NameFile === DO1611){
						return   "ДО 16-11"}

						if(NameFile === DO17111){
						return  "ДО 17-11-1"}

						if(NameFile === DO17112){
						return  "ДО 17-11-2"}

						if(NameFile === DO18111){
						return  "ДО 18-11-1"}

						if(NameFile === DO18112){
						return  "ДО 18-11-2"}

						if(NameFile === KP16111){
						return  "КП 16-11-1"}

						if(NameFile === KP16112){
						return "КП 16-11-2"}

						if(NameFile === KP1709){
						return   "КП 17-09"}

						if(NameFile === KP17111){
						return  "КП 17-11-1"}

						if(NameFile === KP17112){
						return  "КП 17-11-2"}

						if(NameFile === KP17113){
						return  "КП 17-11-3"}

						if(NameFile === KP18111){
						return "КП 18-11-1"}

						if(NameFile === KP18112){
						return  "КП 18-11-2"}

						if(NameFile === KS1611){
						return   "КС 16-11"}

						if(NameFile === OSATPiP1711){
						return   "ОСАТПиП 17-11"}

						if(NameFile === OSATPiP18111){
						return  "ОСАТПиП 18-11-1"}

						if(NameFile === OSATPiP18112){
						return  "ОСАТПиП 18-11-2"}

						if(NameFile === PDOTT1509){
						return   "ПДО ТТ 15-09"}

						if(NameFile === PDOTT1609){
						return   "ПДО ТТ 16-09"}

						if(NameFile === PDOTT1709){
						return   "ПДО ТТ 17-09"}

						if(NameFile === PDOTT18111){
						return "ПДО ТТ 18-11-1"}

						if(NameFile === PDOTT18112){
						return  "ПДО ТТ 18-11-2"}

						if(NameFile === SSA1711){
						return   "ССА 17-11"}

						if(NameFile === SSA18111){
						return  "ССА 18-11-1"}

						if(NameFile === SSA18112){
						return  "ССА 18-11-2"}

						if(NameFile === SHO15091){
						return  "ШО 15-09-1"}

						if(NameFile === SHO15092){
						return  "ШО 15-09-2"}
}

function deleteDataOnFirebase(){
var database = firebase.database();
var deleteDataOnFirebaseVar = database.ref("timetableNew").set({
            deleteData: "deleted"
});}

function main (fileName){
    deleteDataOnFirebase();
    readFile(fileName);
    parseDataOnFile(fileName);
}

function startMainFunctions(){
main(AT1609);
main(AT1709);
main(AT1711);
main(AT1811);
main(ATPiP1509);
main(ATPiP1609);
main(ATPiP1611);
main(DO15091);
main(DO15092);
main(DO1611);
main(DO17111);
main(DO17112);
main(DO18111);
main(DO18112);
main(KP16111);
main(KP16112);
main(KP1709);
main(KP17111);
main(KP17112);
main(KP17113);
main(KP18111);
main(KP18112);
main(KS1611);
main(OSATPiP1711);
main(OSATPiP18111);
main(OSATPiP18112);
main(PDOTT1509);
main(PDOTT1609);
main(PDOTT1709);
main(PDOTT18111);
main(PDOTT18112);
main(SSA1711);
main(SSA18111);
main(SSA18112);
main(SHO15091);
main(SHO15092);
}
