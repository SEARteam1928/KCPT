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

AT1609 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\AT1609.txt", "w")
AT1709 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\AT1709.txt", "w")
AT1711 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\AT1711.txt", "w")
AT1811 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\AT1811.txt", "w")
ATPiP1509 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\ATPiP1509.txt", "w")
ATPiP1609 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\ATPiP1609.txt", "w")
ATPiP1611 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\ATPiP1611.txt", "w")
DO15091 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO15091.txt", "w")
DO15092 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO15092.txt", "w")
DO1611 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO1611.txt", "w")
DO17111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO17111.txt", "w")
DO17112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO17112.txt", "w")
DO18111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO18111.txt", "w")
DO18112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\DO18112.txt", "w")
KP16111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP16111.txt", "w")
KP16112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP16112.txt", "w")
KP1709 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP1709.txt", "w")
KP17111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP17111.txt", "w")
KP17112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP17112.txt", "w")
KP17113 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP17113.txt", "w")
KP18111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP18111.txt", "w")
KP18112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KP18112.txt", "w")
KS1611 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\KS1611.txt", "w")
OSATPiP1711 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\OSATPiP1711.txt", "w")
OSATPiP18111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\OSATPiP18111.txt", "w")
OSATPiP18112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\OSATPiP18112.txt", "w")
PDOTT1509 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\PDOTT1509.txt", "w")
PDOTT1609 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\PDOTT1609.txt", "w")
PDOTT1709 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\PDOTT1709.txt", "w")
PDOTT18111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\PDOTT18111.txt", "w")
PDOTT18112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\PDOTT18112.txt", "w")
SSA1711 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\SSA1711.txt", "w")
SSA18111 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\SSA18111.txt", "w")
SSA18112 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\SSA18112.txt", "w")
SHO15091 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\SHO15091.txt", "w")
SHO15092 = open(r"C:\Users\User\Desktop\KCPT\exelFiles\groups\SHO15092.txt", "w")

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
						day_of_week="mn"
					elif dic_cells[index][0] == 2:
						day_of_week = "ty"
					elif dic_cells[index][0] == 3:
						day_of_week = "wd"
					elif dic_cells[index][0] == 4:
						day_of_week = "th"
					elif dic_cells[index][0] == 5:
						day_of_week = "fr"
					elif dic_cells[index][0] == 6:
						day_of_week = "st"

					timetableInFileStr =("allGroup" if result_lesson[0][0] == "" else "subGroup"+result_lesson[0][0])+'\n'+day_of_week + '\n'+str(dic_cells[index][1]) + '\n'+dic_lessons[hash(result_lesson[0][1])][1]+ '\n'+dic_teachers[hash(result_teacher[0])][1]+ '\n'+dic_rooms[hash(result_room)][1]+ '\n'


					if(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТ 16-09"):
						AT1609.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТ 17-09"):
						AT1709.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТ 17-11"):
						AT1711.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТ 18-11"):
						AT1811.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТПиП 15-09"):
						ATPiP1509.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТПиП 16-09"):
						ATPiP1609.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "АТПиП 16-11"):
						ATPiP1611.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 15-09-1"):
						DO15091.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 15-09-2"):
						DO15092.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 16-11"):
						DO1611.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 17-11-1"):
						DO17111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 17-11-2"):
						DO17112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 18-11-1"):
						DO18111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ДО 18-11-2"):
						DO18112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 16-11-1"):
						KP16111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 16-11-2"):
						KP16112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 17-09"):
						KP1709.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 17-11-1"):
						KP17111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 17-11-2"):
						KP17112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 17-11-3"):
						KP17113.write(timetableInFileStr)


					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 18-11-1"):
						KP18111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КП 18-11-2"):
						KP18112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "КС 16-11"):
						KS1611.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ОСАТПиП 17-11"):
						OSATPiP1711.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ОСАТПиП 18-11-1"):
						OSATPiP18111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ОСАТПиП 18-11-2"):
						OSATPiP18112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ПДО ТТ 15-09"):
						PDOTT1509.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ПДО ТТ 16-09"):
						PDOTT1609.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ПДО ТТ 17-09"):
						PDOTT1709.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ПДО ТТ 18-11-1"):
						PDOTT18111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ПДО ТТ18-11-2"):
						PDOTT18112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ССА 17-11"):
						SSA1711.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ССА 18-11-1"):
						SSA18111.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ССА 18-11-2"):
						SSA18112.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ШО 15-09-1"):
						SHO15091.write(timetableInFileStr)

					elif(dic_groups[hash(dic_cells['groups'][cur_group])][1] == "ШО 15-09-2"):
						SHO15092.write(timetableInFileStr)
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
ATPiP1509.close()
ATPiP1609.close()
ATPiP1611.close()
DO15091.close()
DO15092.close()
DO1611.close()
DO17111.close()
DO17112.close()
DO18111.close()
DO18112.close()
KP16111.close()
KP16112.close()
KP1709.close()
KP17111.close()
KP17112.close()
KP17113.close()
KP18111.close()
KP18112.close()
KS1611.close()
OSATPiP1711.close()
OSATPiP18111.close()
OSATPiP18112.close()
PDOTT1509.close()
PDOTT1609.close()
PDOTT1709.close()
PDOTT18111.close()
PDOTT18112.close()
SSA1711.close()
SSA18111.close()
SSA18112.close()
SHO15091.close()
SHO15092.close()