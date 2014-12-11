#!/usr/bin/env python

import pymongo
import time
import os
import codecs

mongo_client = pymongo.MongoClient()
db = mongo_client['eumssi_test']
col = db['content_items']

def get_items(limit=1000,lang='en'):
  return col.find({'source_meta.format':'twitter-api-1.1','source_meta.original.lang':lang},fields=['source_meta.original'],limit=limit)

def main():
  for lang in ['en','de','es','fr']:
    try: os.mkdir(lang)
    except OSError:
      print "directory",lang,"probably already exists"
    items = get_items(lang=lang)
    for item in items:
      fname = os.path.join(lang,str(item['_id']))
      print fname
      with codecs.open(fname,'w',encoding='utf8') as f:
        f.write(item['source_meta']['original']['text'])

if __name__ == '__main__':
  main()
