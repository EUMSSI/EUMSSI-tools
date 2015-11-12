db.content_items.update({},
  {$rename: {
    'meta.extracted.text.dbpedia':'meta.extracted.text_nerl.dbpedia',
    'meta.extracted.text.ner':'meta.extracted.text_nerl.ner',
    'processing.results.text.ocr-nerl':'processing.results.text_ocr-nerl'
  }},
{multi:true})

db.tweets.update({},
  {$rename: {
    'meta.extracted.text.dbpedia':'meta.extracted.text_nerl.dbpedia',
    'meta.extracted.text.ner':'meta.extracted.text_nerl.ner',
    'meta.extracted.text.polarity':'meta.extracted.text_polarity'
  }},
  {multi:true})

db.content_items.update({'processing.available_data':'segments-fakeasr'},
  {
    $addToSet: {'processing.available_data':'segments_fakeasr'}
  },
  {multi:true}
)
db.content_items.update({'processing.available_data':'segments-fakeasr'},
  {
    $pull: {'processing.available_data':'segments-fakeasr'},
  },
  {multi:true}
)

db.content_items.update({'processing.available_data':'ocr-nerl'},
  {
    $rename: {'processing.results.text.ocr-nerl':'processing.results.text_ocr-nerl'},
    $$addToSet: {'processing.available_data':'text_ocr-nerl'}
  },
  {multi:true}
)
db.content_items.update({'processing.available_data':'ocr-nerl'},
  {
    $pull: {'processing.available_data':'ocr-nerl'},
  },
  {multi:true}
)
