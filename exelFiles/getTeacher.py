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


def wr(name, str):
	name.write(str)

teachers = open("/root/Документы/KCPT/exelFiles/teachers.txt", "w")
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
		cur_group += 1
		

for e in dic_teachers:
	print(dic_teachers[e][1])
	teachers.write(dic_teachers[e][1]+"\n")
	
teachers.close()
