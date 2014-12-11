#!/usr/bin/env python

import pymongo
import time
import os
import codecs

mongo_client = pymongo.MongoClient()
db = mongo_client['eumssi_test']
col = db['content_items']

def main():
  for lang in ['en']:
    col.ensure_index([('source_meta.original.entities.hashtags.text', pymongo.ASCENDING)])
    col.ensure_index([('source_meta.original.user.screen_name', pymongo.ASCENDING)])
    match = {'$match':{'source_meta.format':'twitter-api-1.1', 'source_meta.original.lang':lang}}#, 'source_meta.original.entities.user_mentions.screen_name': {'$exists':1}}}
    limit = {'$limit' : 100000}
    unwind = {'$unwind' : "$source_meta.original.entities.hashtags" }
    group = {'$group' : {
        '_id' : {'user':'$source_meta.original.user.screen_name', 'mention' : '$source_meta.original.entities.hashtags.text'},
        'weight' : {'$sum' : 1}
        }}
    sort = {'$sort' : {'weight' : pymongo.DESCENDING}}
    limit_output = {'$limit' : 1000}
    #r = col.aggregate([match, limit, unwind, group])
    r = col.aggregate([match, unwind, group, sort, limit_output])
    print ';'.join(('Source','Target','Weight'))
    for edge in r['result']:
      print ';'.join((edge['_id']['user'],edge['_id']['mention'],str(edge['weight'])))

if __name__ == '__main__':
  main()
