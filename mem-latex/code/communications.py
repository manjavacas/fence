#!/usr/bin/env python
# -*- coding: utf-8 -*-

'''
Random communication activities generator
Required packages:
	- dnspython
	- faker
Antonio Manjavacas. FENCE. ESI UCLM. 2020.
'''

import pymongo as pm
import datetime as dt

from faker import Faker
from random import randint


URI = 'mongodb+srv://username:fenceapp@app-httxx.mongodb.net/'

sources = ['JIRA', 'EMAIL', 'PHONE', 'SLACK', 'TEAMS', 'GITHUB', 'SKYPE', 'OTHER']
MAX = 8

client = pm.MongoClient(URI)
db = client['fencedb']

def random_date():
  # year, month, day, hour, minute, second, microsecond
  return dt.datetime(2020, randint(1,5), randint(1,28), randint(8,20), randint(0,59), randint(0,59))


tasks_ids = []
for document in db.tasks.find():
  tasks_ids.append(document['reference'])

employees_ids = []
for document in db.employees.find():
  employees_ids.append(document['dni'])

# delete previous
db.drop_collection('communications')

comm_list = []

requirements = db['CR']
for req in requirements.find():
  n = randint(0,MAX)
  for i in range(0,n):
    communication = {}
    communication['user1'] = req['user1']
    communication['user2'] = req['user2']
    communication['taskRef'] = req['task']
    
    source = sources[randint(0,len(sources) - 1)]   
    communication['source'] = source

    if source in ['PHONE', 'TEAMS', 'SKYPE', 'OTHER']:
      communication['duration'] = float(randint(20,600))
    else:
      communication['duration'] = 0

    communication['date'] = random_date()
    communication['project'] = req['project']

    comm_list.append(communication)

communications = db['communications']
for c in comm_list:
  communications.insert_one(c)