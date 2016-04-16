import urllib2
import urllib
from bs4 import BeautifulSoup as BS
import unicodedata
import sys
#import pyttsx

#engine = pyttsx.init()
#engine.setProperty('rate',140)

def india(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for i in bs.find_all("div", {"class": "search_pg_bg"}):
        for j in i.find_all("h2"):
            tmp = j.text
            cnt += 1
            if cnt < 6:
                new.write('"'+str(cnt)+". ")
                temp = unicodedata.normalize('NFKD', tmp).encode('ascii','ignore')
                new.write(str(temp)[1:len(str(temp))-1]+'"'+"\n")
                #engine.say(temp)

def jp(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for n in bs.find_all("div", {"class": "main_content"}):
        for i in n.find_all("h1"):
            tmp = i.string
            cnt += 1
            if cnt < 6:
                new.write('"'+str(cnt)+". ")
                new.write(str(tmp)+'"'+"\n")
                #engine.say(tmp)

def aus(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for n in bs.find_all("div", {"class": "story-block "}):
        for i in n.find_all("a"):
            tmp = i.string
            cnt += 1
            if cnt < 6:
                new.write('"'+str(cnt)+". ")
                new.write(str(tmp)+'"'+"\n")
                #engine.say(tmp)

def usa(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for n in bs.find_all("div", {"class": "listing-standard-lead "}):
        for i in n.find_all("h3"):
            tmp = i.string
            temp = unicodedata.normalize('NFKD', tmp).encode('ascii','ignore')
            cnt += 1
            if cnt < 6:
                new.write('"'+str(cnt)+". ")
                new.write(str(temp)+'"'+"\n")
                #engine.say(temp)

def uk(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for n in bs.find_all("span", {"class": "headline headline--normal headline--section-top-stories"}):
            tmp = n.string
            temp = unicodedata.normalize('NFKD', tmp).encode('ascii','ignore')
            cnt += 1
            if cnt < 6:
                new.write('"'+str(cnt)+". ")
                new.write(str(temp)+'"'+"\n")
                #engine.say(temp)
def europe(url):
    src = urllib2.urlopen(url).read()
    bs = BS(src,"lxml")
    cnt = 0
    for n in bs.find_all("h2"):
            tmp = n.find("a").text
            temp = unicodedata.normalize('NFKD', tmp).encode('ascii','ignore')
            cnt += 1
            if cnt < 6:
		new.write('"'+str(cnt)+". ")
                new.write(str(temp).strip()+'"'+"\n")
    		#print temp

if __name__  == '__main__':
    new = open('./news.txt','w')
    country = sys.argv[1]
    new.write("A very Good Morning Sir! The headlines for today are:\n")	
    if country == "India":
        url = "http://www.hindustantimes.com/top-news"
        new.write("India : "+"\n")
        #engine.say("India")
        india(url)
	
    elif country == "Japan":
        url = "http://www.japantimes.co.jp/news/national/"
        new.write( "Japan : "+"\n")
        #engine.say("Japan")
        jp(url)
	
    elif country == "Australia":
        url = "http://www.news.com.au/national/breaking-news"
        new.write("Australia : " + "\n")
        #engine.say("Australia")
        aus(url)

    elif country == "USA":
        url = "http://www.cbsnews.com/us/"
        new.write("USA : " + "\n")
        #engine.say("USA")
        usa(url)

    elif country=="UK":
        url = "http://news.sky.com/uk"
        new.write("UK : " + "\n")
        #engine.say("UK")
        uk(url)
    else:
	url = "http://www.euronews.com/news/europe/"
	new.write("Europe :\n")
	europe(url)
    
