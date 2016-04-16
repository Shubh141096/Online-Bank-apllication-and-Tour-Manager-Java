import os
import datetime

def tts(text):
      print text
      return os.system("espeak  -s 120 -a 500 "+text+" " )

fil = open('./news.txt','r')
i=0
lines = fil.read().split("\n")
lines = lines[0:len(lines)-1]
for line in lines:
	tts(line)	

