import xlrd
import re
from functools import *

def hash(string):
	string = string.replace(" ","")
	string = string.replace(".","")
	string = string.replace("-","")
	string = string.upper()
	return string

mask_section = "\[([a-zA-Z]*)\]"
mask_section_compile = re.compile(mask_section)
mask_option = "([a-z]*)\s*=\s*([a-zA-Z\.]*)"
mask_option_compile = re.compile(mask_option)
mask_groups = "(Группы)[\s\-]*|([А-Я][а-яА-Я\s]*[А-Я]\s?[0-9][0-9\-]*[0-9])"
mask_groups_compile = re.compile(mask_groups)
mask_day = "(День)[\s\-]*|([А-Я][а-яА-Я]*),\w*"
mask_day_compile = re.compile(mask_day)
mask_teacher = "[вВ]акансия\w*|[А-Я][а-я]+\s+[А-Я]\.\s*[А-Я]\.?"
mask_teacher_compile = re.compile(mask_teacher)
mask_lesson = "([0-9]?)\.?([а-яА-Яa-zA-Z0-9\s\.\-\,]+)"
mask_lesson_compile = re.compile(mask_lesson)
mask_group_ext = "([А-Я][а-яА-Я\s]*[А-Я])\s?([0-9][0-9])\-([0-9][0-9])\-?([0-9]?)"
mask_group_ext_compile = re.compile(mask_group_ext)
dic_cells = {}
dic_cells['groups'] = []
dic_cells['lessons'] = []
dic_groups = {}
dic_lessons = {}
dic_teachers = {}
dic_rooms = {}
list_timetable = []
cur_day = 0
cur_lesson = 0

try:
	file_ini = open("options.ini")
except:
	print("!!!не могу найти файл настроек!!!")
	exit()

str_exec = ""
current_section = ""
for e in file_ini:
	result_section = mask_section_compile.findall(e)
	result_option = mask_option_compile.findall(e)
	if result_section != []:
		current_section = result_section[0]
	elif result_option != []:
		str_exec += "{}_{} = '{}'{}".format(current_section, result_option[0][0], result_option[0][1], "\n")


exec(str_exec)

def openFiles(groupNameStr):
	groupName = open("/root/Документы/KCPT/exelFiles/groups/" + groupNameStr + ".txt", "w")
	return groupName

AT1609 = openFiles("AT1609")
AT1709=openFiles("AT1709")
AT1711=openFiles("AT1711")
AT1811=openFiles("AT1811")
AT1911=openFiles("AT1911")

ATPiP1609=openFiles("ATPiP1609")

DO17111=openFiles("DO17111")
DO17112=openFiles("DO17112")
DO18111=openFiles("DO18111")
DO18112=openFiles("DO18112")
DO19111=openFiles("DO19111")
DO19112=openFiles("DO19112")

ISiP19111=openFiles("ISiP19111")
ISiP19112=openFiles("ISiP19112")

KP1709=openFiles("KP1709")
KP17111=openFiles("KP17111")
KP17112=openFiles("KP17112")
KP17113=openFiles("KP17113")
KP18111=openFiles("KP18111")
KP18112=openFiles("KP18112")
KP19111=openFiles("KP19111")
KP19112=openFiles("KP19112")
KP19113=openFiles("KP19113")

OSATPiP1711= openFiles("OSATPiP1711")
OSATPiP18111=openFiles("OSATPiP18111")
OSATPiP18112=openFiles("OSATPiP18112")
OSATPiP19111=openFiles("OSATPiP19111")
OSATPiP19112=openFiles("OSATPiP19112")

PDOTT1609=openFiles("PDOTT1609")
PDOTT1709=openFiles("PDOTT1709")
PDOTT18111=openFiles("PDOTT18111")
PDOTT18112=openFiles("PDOTT18112")
PDOTT1911=openFiles("PDOTT1911")

SSA1711=openFiles("SSA1711")
SSA18111=openFiles("SSA18111")
SSA18112=openFiles("SSA18112")
SSA19111=openFiles("SSA19111")
SSA19112=openFiles("SSA19112")
SSA19113=openFiles("SSA19113")

def wr(name, str):
	name.write(str)

def lNum(num):
	n = num
	if num=="10" or num=="11" or num=="12" :
		return n
	else:
		return "0" + n


work_book = xlrd.open_workbook(file_path)

for sheet in range(work_book.nsheets):
	work_sheet = work_book.sheet_by_index(sheet)

	cur_day = 0
	for index in range(300):
		try:
			cell = work_sheet.cell(index, 0).value
			cell_type = work_sheet.cell_type(index, 0)
		except:
			break
		if cell_type == 1:
			result_group = mask_groups_compile.findall(cell)
			result_day = mask_day_compile.findall(cell)
			if result_group != []:
				dic_cells['groups'] = reduce(lambda l,e: l + [e[1]], result_group[1::], [])
			elif result_day != []:
				cur_day += 1
		elif cell_type == 2:
			cur_lesson = int(cell)
			dic_cells[index] = (cur_day, cur_lesson)
		elif cell_type == 0:
			dic_cells[index] = (cur_day, cur_lesson)

	for index in range(len(dic_cells['groups'])):
		hash_group = hash(dic_cells['groups'][index])
		group = dic_cells['groups'][index]
		if hash_group not in dic_groups:
			result_group_ext = mask_group_ext_compile.findall(group)
			abbreviation = result_group_ext[0][0]
			level = result_group_ext[0][1]
			count = result_group_ext[0][2]
			number = result_group_ext[0][3]
			dic_groups[hash_group] = (len(dic_groups) + 1, group, abbreviation, level, count, number)
			
	cur_group = 0
	for collumn in range(1, len(dic_cells['groups'])*2, 2):
		for index in range(300):
			try:
				cell = work_sheet.cell(index, collumn).value
				cell_type = work_sheet.cell_type(index, collumn)
			except:
				break
			if cell_type == 1:
				result_teacher = mask_teacher_compile.findall(cell)
				if result_teacher != []:
					hash_teacher = hash(result_teacher[0])
					teacher = result_teacher[0]
					if hash_teacher not in dic_teachers:
						dic_teachers[hash_teacher] = (len(dic_teachers) + 1, teacher)
					cur_day = dic_cells[index][1]
					result_lesson = []
					d = 1
					
					while True:
						if dic_cells[index - d][1] != cur_day:
							break
						cell_lesson = work_sheet.cell(index - d, collumn).value
						cell_type = work_sheet.cell_type(index - d, collumn)
						if cell_type == 1:
							result_lesson = mask_lesson_compile.findall(cell_lesson)
							if result_lesson != []:
								hash_lesson = hash(result_lesson[0][1])
								lesson = result_lesson[0][1]
								if hash_lesson not in dic_lessons:
									dic_lessons[hash_lesson] = (len(dic_lessons) + 1, lesson)
								break
						d += 1
					result_room = ""
					d = 0
					
					while True:
						if dic_cells[index - d][1] != cur_day:
							break
						cell_room = work_sheet.cell(index - d, collumn + 1).value
						cell_type = work_sheet.cell_type(index - d, collumn + 1)
						if cell_type == 1:
							result_room = cell_room
						elif cell_type == 2:
							result_room = str(int(cell_room))
						if result_room != "":
							break
						d += 1
					result_room = "-" if result_room == "" else result_room
					hash_room = hash(result_room)
					room = result_room
					if hash_room not in dic_rooms:
						dic_rooms[hash_room] = (len(dic_rooms) + 1, room)
					list_timetable.append(
						("All" if result_lesson[0][0] == "" else "subGroup"+result_lesson[0][0],
						dic_groups[hash(dic_cells['groups'][cur_group])][0],
						dic_groups[hash(dic_cells['groups'][cur_group])][1],
						dic_cells[index][0],
						dic_cells[index][1],
						dic_lessons[hash(result_lesson[0][1])][1],
						dic_lessons[hash(result_lesson[0][1])][0],
						dic_teachers[hash(result_teacher[0])][1],
						dic_teachers[hash(result_teacher[0])][0],
						dic_rooms[hash(result_room)][1],
						dic_rooms[hash(result_room)][0]
						))

					if dic_cells[index][0] == 1:
						day_of_week="Понедельник"
					elif dic_cells[index][0] == 2:
						day_of_week = "Вторник"
					elif dic_cells[index][0] == 3:
						day_of_week = "Среда"
					elif dic_cells[index][0] == 4:
						day_of_week = "Четверг"
					elif dic_cells[index][0] == 5:
						day_of_week = "Пятница"
					elif dic_cells[index][0] == 6:
						day_of_week = "Суббота"

					timetableInFileStr =("allGroup" if result_lesson[0][0] == "" else "subGroup"+result_lesson[0][0])+'\n'+day_of_week + '\n'+lNum(str(dic_cells[index][1])) + '\n'+dic_lessons[hash(result_lesson[0][1])][1]+ '\n'+dic_teachers[hash(result_teacher[0])][1]+ '\n'+dic_rooms[hash(result_room)][1]+ '\n'
					
					a = dic_groups[hash(dic_cells['groups'][cur_group])][1]
					b = timetableInFileStr
					
					if(a == "АТ 17-09"):
						wr(AT1709,b)
					elif(a == "АТ 17-11"):
						wr(AT1711,b)
					elif(a == "АТ 18-11"):
						wr(AT1811,b)
					elif(a== "АТ 19-11"):
						wr(AT1911,b)
					elif(a== "АТ 16-09"):
						wr(AT1609,b)
						
					elif(a == "АТПиП 16-09"):
						wr(ATPiP1609,b)
						
					elif(a== "ДО 17-11-1"):
						wr(DO17111,b)
					elif(a== "ДО 17-11-2"):
						wr(DO17112,b)
					elif(a == "ДО 18-11-1"):
						wr(DO18111,b)
					elif(a== "ДО 18-11-2"):
						wr(DO18112,b)
					elif(a== "ДО 19-11-1"):
						wr(DO19111,b)
					elif(a== "ДО 19-11-2"):
						wr(DO19112,b)
						
					elif(a == "ИСиП 19-11-1"):
						wr(ISiP19111,b)
					elif(a == "ИСиП 19-11-2"):
						wr(ISiP19112,b)
					
					elif(a == "КП 17-09"):
						wr(KP1709,b)
					elif(a== "КП 17-11-1"):
						wr(KP17111,b)
					elif(a == "КП 17-11-2"):
						wr(KP17112,b)
					elif(a== "КП 17-11-3"):
						wr(KP17113,b)
					elif(a== "КП 18-11-1"):
						wr(KP18111,b)
					elif(a== "КП 18-11-2"):
						wr(KP18112,b)
					elif(a== "КП 19-11-1"):
						wr(KP19111,b)
					elif(a== "КП 19-11-2"):
						wr(KP19112,b)
					elif(a== "КП 19-11-3"):
						wr(KP19113,b)
						
					elif(a== "ОСАТПиП 17-11"):
						wr(OSATPiP1711,b)
					elif(a == "ОСАТПиП 18-11-1"):
						wr(OSATPiP18111,b)
					elif(a == "ОСАТПиП 18-11-2"):
						wr(OSATPiP18112,b)
					elif(a == "ОСАТПиП 19-11-1"):
						wr(OSATPiP19111,b)
					elif(a == "ОСАТПиП 19-11-2"):
						wr(OSATPiP19112,b)
						
					elif(a == "ПДО ТТ 16-09"):
						wr(PDOTT1609,b)
					elif(a == "ПДО ТТ 17-09"):
						wr(PDOTT1709,b)
					elif(a == "ПДО ТТ 18-11-1"):
						wr(PDOTT18111,b)
					elif(a == "ПДО ТТ18-11-2"):
						wr(PDOTT18112,b)
					elif(a == "ПДО ТТ 19-11"):
						wr(PDOTT1911,b)
						
					elif(a == "ССА 17-11"):
						wr(SSA1711,b)
					elif(a== "ССА 18-11-1"):
						wr(SSA18111,b)
					elif(a == "ССА 18-11-2"):
						wr(SSA18112,b)
					elif(a == "ССА 19-11-1"):
						wr(SSA19111,b)
					elif(a== "ССА 19-11-2"):
						wr(SSA19112,b)
					elif(a == "ССА 19-11-3"):
						wr(SSA19113,b)
						
		cur_group += 1
		
"""
for e in dic_groups:
	t = dic_groups[e][1], dic_groups[e][5]
	print(e)

for e in dic_lessons:
	print(dic_lessons[e][1])


for e in dic_rooms:
	print(dic_rooms[e][1])

for e in dic_teachers:
	print(dic_teachers[e][1])

"""
for e in list_timetable:
	print(e)


AT1609.close()
AT1709.close()
AT1711.close()
AT1811.close()
AT1911.close()

ATPiP1609.close()

DO17111.close()
DO17112.close()
DO18111.close()
DO18112.close()
DO19111.close()
DO19112.close()

ISiP19111.close()
ISiP19112.close()

KP1709.close()
KP17111.close()
KP17112.close()
KP17113.close()
KP18111.close()
KP18112.close()
KP19111.close()
KP19112.close()
KP19113.close()

OSATPiP1711.close()
OSATPiP18111.close()
OSATPiP18112.close()
OSATPiP19111.close()
OSATPiP19112.close()

PDOTT1609.close()
PDOTT1709.close()
PDOTT18111.close()
PDOTT18112.close()
PDOTT1911.close()

SSA1711.close()
SSA18111.close()
SSA18112.close()
SSA19111.close()
SSA19112.close()
SSA19113.close()
