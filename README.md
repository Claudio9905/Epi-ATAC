----------
|EPI-ATAC|
----------

Progetto basato sulla creazione di un gestionale di un azienda di trasporti pubblici. 

DESCRIZIONE: 
Un utente è in grado in acquistare un biglietto da uno specifico punto di emissione (distributore automatico, rivenditore automatico) o un abbonamento (settimanle o mensile) di tipo nominativo ad utenti dotati di tessera.
La tessera ha una validità annuale e deve essere rinnovata alla scadenza. Ogni biglietto e abbonamento è identificato da un codice univoco.
Viene tenuto traccia del numero dei biglietti e/o abbonamenti emessi in un dato periodo di tempo totale e per punto di emissione. 
I distributori automatici possono essere attivi o fuori servizio.
Viene verificato la rapida validità di un abbonamento in base al numero di tessera dell'utente controllato.

Il sistema ha anche la gestione del parco mezzi. I mezzi possono essere tram o autobus ed avere una certa capienza in base al tipo di mezzo. Ogni mezzo può essere in servizio o in manutenzione ed occorre tenere traccia dei periodi di servizio o manutenzione di ogni mezzo.
Quando un biglietto viene obliterato su un mezzo, esso deve essere annulato e deve essere possibile acquisire il numero di biglietti obliterati su un particolare mezzo o in totale in un periodo di tempo.

Ogni mezzo in servizio può essere assegnato ad una tratta, che è caratterizzata da una zona di partenza, un capolinea ed unn tempo previsto di percorrenza. 
Viene tenuta traccia del numero di volte che un mezzo percorre una tratta e del tempo effettivo di percorrenza di esse. Un amministratore del sistema deve poter calcolare il tempo medio effettivo di percorrenza di esse. Un amministratore del sistema calcola il tempo medio effettivo di percorrenza di una tratta da parte di un mezzo.

---------------------------------------------------------------------------------------------

MODELLO ER: (Tutte le relazioni hanno un id autogenerativo che indica )
- UTENTE(id, amministratore, cognome, nome, data_nascita, e-mail)
- TESSERA_UTENTE(id, data_inizio, data_scadenza, utente_id)
- ABBONAMENTO(id, data_scadenza, tipo_abbonamento, tessera)
- GESTIONE_VENDITA(id, data_acquisto, mezzo, punto_emissione)
- PUNTO EMISSIONE(id, posizione)
- DISTRIBUTORE_AUTOMATICO(id, In_servizio)
- RIVENDITORE_AUTORIZZATO(id, nome_locale, orario_apertura, orario_chiusura, nome_rivenditore, nome_rivenditore)
- MEZZO(id, capienza, tipo_mezzo)
- STATO_MEZZO(id, data_inizio, data_fine, motivo_manutenzione, tipo_stato, mezzo_id)
- MEZZO_TRATTO(id, orario_arrivo, orario_partenza, percorrenza_effettiva, mezzo_id, tratta_id)
- TRATTA(id, zona_partenza, capolinea, percorrenza_effettiva)

---------------------------------------------------------------------------------------------

TECNOLOGIE UTILIZZATE:
Per la creazione del progetto, sono stati implementati varie tecnologie:
- Progetto creato con MAVEN (Catalog: Maven Central / Archetype:   com.dominikcebula.archetypes:java21-basic-archetype)
- JPA (Java Persistence API)
- POSTGRES (Per creazione del database)
- PG-ADMIN 4 (interfaccia grafica per amministrare e gestire il database SQL) 
- framework HIBERNATE

Collaboratori del progetto :
- Thoma Galbignani
- Cristina Mastellaro
- Mattia Pastorini
- Claudio Postiglione
