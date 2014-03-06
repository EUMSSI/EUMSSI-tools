#!/usr/bin/env python

def main():
    import requests
    batch_size=1000
    cores=['core_de_DE','core_fr_FR','core_es_ES','core_en_GB']
    for core in cores:
        start_id='*'
        done=False
        while not done:
            r=requests.get('http://localhost:8080/Solr_EUMSSI_DW/{core}/select'.format(core=core),params={'q':'*:*','sort':'ID asc','wt':'json','rows':batch_size,'fq':'{nocache}ID:{{{id} TO *]'.format(nocache='{!cache=false}',id=start_id),'indent':'true'})
            parsed=r.json()
            if parsed['response']['numFound'] == 0:
                done = True
                break
            start_id=parsed['response']['docs'][-1]['ID']
            with open('dump-'+core+'-'+start_id,'wb') as f:
                f.write(r.content)

if __name__ == '__main__':
    main()