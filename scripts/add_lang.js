var langs = ['de_DE', 'fr_FR', 'es_ES', 'en_GB'];
langs.forEach( function (lang) {
    var iso = lang.split('_')[0];
    print(iso, 'DW-'+lang);
    print(db.content_items.update({source:'DW-'+lang}, {$set: {'meta.original.inLanguage': iso}}, {multi: true}));
});