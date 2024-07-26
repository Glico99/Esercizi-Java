## Java Chat App

L'app è costituita da 4 file:
-`ChatServer`: Implementa la logica del Server, gestendo le connessioni e la creazione dei threads
-`ChatClient`: Crea un socket client e permette la connessione al server 
-`ChatClientGUI`: Crea una semplice GUI utilizzando le librerie standard
-`App`: Permette l'esecuzione del client

##Utilizzo

-`Avvia il server eseguendo il file ChatServer.java`
-`Avvia l'app tramite il file App.java`

## Struttura cartelle

- `src`: Contiene il file App.java ed il package com.chatapp
- `lib`: Librerie standard
- `bin`: Contiene i files di output compilati

## Dependency Management

Il resto delle dipendenze è gestito dall'opzione [JAVA PROJECTS](https://github.com/microsoft/vscode-java-dependency#manage-dependencies) di VScode
