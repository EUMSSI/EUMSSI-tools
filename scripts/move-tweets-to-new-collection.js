db.content_items.aggregate([{$match: {source: "Twitter"}}, {$out: "twitter_temp"}])
db.twitter_temp.copyTo("tweets")
db.twitter_temp.drop()
db.content_items.remove({source: "Twitter"})db.content_items.remove({source: "Twitter"})

db.content_items.aggregate([{$match: {source: "Twitter-DW"}}, {$out: "twitter_temp"}])
db.twitter_temp.copyTo("tweets")
db.twitter_temp.drop()
db.content_items.remove({source: "Twitter-DW"})
