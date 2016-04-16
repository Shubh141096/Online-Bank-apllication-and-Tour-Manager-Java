import urllib
import sys
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time
frm = sys.argv[1]
to = sys.argv[2]
#print frm,to
url = "https://www.google.co.in/flights/#search;f="+frm+";t="+to+";tt=o"

driver = webdriver.Firefox()
driver.get(url)
time.sleep(20)
we = open("price.txt",'w')
we.write(driver.find_elements(By.XPATH, "//div[@class='MHNSJI-d-zb']")[0].text)
we.close()
driver.close()

