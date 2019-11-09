teachers = open("/root/Документы/KCPT/exelFiles/teachers.txt", "r")
teachersWithIf = open("/root/Документы/KCPT/exelFiles/teachersWithIf.txt", "w")

for line in teachers:
	teach = 'if (teacherName === \"'+line.replace("\n","")+'\") {\nreturn \"'+((line.replace("\n","")).replace(".","")).replace(" ","")+'\";\n}\n'
	teachersWithIf.write(teach)
