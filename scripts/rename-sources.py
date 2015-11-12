#!/usr/bin/env python

import pymongo

mongo_client = pymongo.MongoClient()
db = mongo_client['eumssi_db']
col = db['content_items']


def source_map(item):
    mapping = {
        'Twitter':'Twitter',
        'Twitter-DW':'Twitter',
        'DW-de_DE':'DW article',
        'DW-en_GB':'DW article',
        'DW-es_ES':'DW article',
        'DW-fr_FR':'DW article',
        'DW-vid_EN':'DW video',
        'Eumssi-News-Crawler':'News',
        'Youtube-video-GeneralChannel':'Youtube',
        'Youtube-video-dwEnglishChannel':'DW (Youtube)',
        'Youtube-video-theguardianChannel':'Guardian (Youtube)',
        'DW-en':'DW ?',
        'DW-DE':'DW ?',
        'DW-de':'DW ?',
        'DW-EN':'DW ?',
        'DW-es':'DW ?',
        'DW-ES':'DW ?',
        'DW-FR':'DW ?'
    }
    new_source = item['source']
    try:
        new_source = mapping[item['source']]
    except Exception as e:
        print 'missing source', e
    if (new_source == "News"):
        try:
            new_source = item['meta']['source']['publisher'].title() # initial cap version of news source
        except Exception as e:
            print 'missing publisher', e
    if (new_source == "DW ?"):
        try:
            new_source = 'DW video' if (item['meta']['original_format'] == 'DW-video-may-release') else \
                         'DW article' if item['meta']['original_format'] == 'DW-news-may-release' else \
                         new_source
        except Exception as e:
            print 'missing original_format', e
    return new_source


def main():
    for item in col.find({},projection=['_id','source','meta.source.publisher','meta.original_format','old_source']):
        if ('old_source' in item): # don't overwrite old_source if already set
            res = col.update({'_id': item['_id']},{'$set': {'source': source_map(item)}})
        else:
            res = col.update({'_id': item['_id']},{'$set': {'source': source_map(item),'old_source':item['source']}})
        print 'switched item', item['_id'], 'from', item['source'], 'to', source_map(item)


if __name__ == '__main__':
  main()
