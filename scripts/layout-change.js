db.content_items.dropIndexes() // platform components are in charge of correctly recreating the needed indexes
db.content_items.ensureIndex({source:1}) // for basic debugging needs
db.content_items.update({},{$rename: {'source_meta':'meta'}},{multi:true})
db.content_items.update({},{$rename: {'meta.eumssi':'meta.source','extracted_meta':'meta.extracted','meta.format':'meta.original_format'}},{multi:true})
