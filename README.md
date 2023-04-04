# TestBesidetech
IN UN MODULO SPRING BOOT SVILUPPA UN CRUD INTERCONNESSO A DELLE TABELLE SU UN DB RELAZIONALE. TABELLE UTENTI , RICERCHE , E CANALI. RELAZIONE 1aM UTENTE-RICERCHE E RELAZIONE MaM NELLA RELAZIONE RICERCHE-CANALI. UTENTI= ID,NOME,COGNOME,EMAIL. RICERCHE=ID,DATARICERCA,SOGGETTODIRICERCA. CANALI=ID,DESCRIZIONE. DOCKERIZZARE L'APPLICAZIONE.

Prequisiti:
java 8,
docker desktop,
postman,

Per installare database:
da src/resources/database/ lanciare il comando: "docker-compose up".
da browser http://localhost/login ed effettuare l'accesso con le credenziali presenti nel file yml.
Creare un server denominato "DockerAppDB" specificando come host "172.21.0.2", la porta "5432", username "postgres" e password "admin".
Creare un nuovo database all'interno del server appena creato chiamato "SearchChannel" ed utilizzare il file db.sql contenuto nella cartella database per fare il restore del db.

Per installare l'applicazione springboot:
posizionarsi nella root del progetto e lanciare il comando "mvn install" da cmd o ide
successivamente lanciare: "docker build -t DockerWebServer.jar ."

Per avviare l'applicazione:
lanciare il comando: "docker run -p 5051:5051 dockerwebserver.jar"

Per effettuare una chiamata alle diverse api implementante ho introdotto in "src/resources/postman/" una collection postman



