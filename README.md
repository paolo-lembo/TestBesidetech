# TestBesidetech
IN UN MODULO SPRING BOOT SVILUPPA UN CRUD INTERCONNESSO A DELLE TABELLE SU UN DB RELAZIONALE. TABELLE UTENTI , RICERCHE , E CANALI. RELAZIONE 1aM UTENTE-RICERCHE E RELAZIONE MaM NELLA RELAZIONE RICERCHE-CANALI. UTENTI= ID,NOME,COGNOME,EMAIL. RICERCHE=ID,DATARICERCA,SOGGETTODIRICERCA. CANALI=ID,DESCRIZIONE. DOCKERIZZARE L'APPLICAZIONE.

Prequisiti:
java 8
docker
postman

Per installare database:


Per installare l'applicazione springboot
da terminale lanciare il comando: "mvn install"
dalla root del progetto da terminale lanciare: 
      "docker build -t DockerWebServer.jar ."
      "docker run -p 5051:5051 dockerwebserver.jar"



