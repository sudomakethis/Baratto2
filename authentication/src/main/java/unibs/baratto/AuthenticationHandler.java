package unibs.baratto;

import unibs.baratto.authentication.Configurator;
import unibs.baratto.authentication.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationHandler {

    private final File credentialsFile;
    private String status;
    private Map<String , String> credentials = new HashMap<>();
    private String userLogged;
    public AuthenticationHandler(File credentials) {
        this.credentialsFile = credentials;
        getCredentialsFromFile();
    }

    public boolean isValid(String username, String password){
        if (!credentials.containsKey(username))
            return false;
        var passed = credentials.get(username).equals(password);
        if (passed)
            userLogged = username;
        return passed;
    }

    public void addNewCredential(String username, String password) {
        if (credentials.containsKey(username))
            status = "Questo nome utente esiste già";
        credentials.put(username, password);
    }

    public User getUser(){
        return new Configurator(userLogged);
    }


    public void removeCredential(String username){
        credentials.remove(username);
    }

    private void getCredentialsFromFile(){
        try (CredentialsFileReader reader = new CredentialsFileReader(new FileReader(credentialsFile))) {
           credentials = reader.getCredentials();
           status = "Ready";

        } catch (FileNotFoundException e) {
            status = "File credenziali non trovato";

        } catch (IOException e) {
            status = "Errore di lettura del file";

        }
    }

    public String getStatus(){
        return status;
    }

}