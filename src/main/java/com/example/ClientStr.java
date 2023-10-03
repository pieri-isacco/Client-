package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;


    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mioSocket;
    }
    public void comunica(){
        try {
            System.out.println("4 ... inserisci la stringa da trasmettere al server:"+'\n');
            stringaUtente = tastiera.readLine();
            System.out.println("5 ... invio la stampa al server e attendo ...");
            outVersoServer.writeBytes(stringaUtente+'\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 ... risposta dal server "+'\n'+stringaRicevutaDalServer);
            System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
            mioSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }
}
