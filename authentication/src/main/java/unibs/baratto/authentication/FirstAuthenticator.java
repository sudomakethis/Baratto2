package unibs.baratto.authentication;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FirstAuthenticator implements AuthenticationHandler{

    private final File credentialsFile;
    private String status;
    private Map<String , String> credentials = new HashMap<>();
    private String userLogged;

    private AuthenticationHandler next;

    public FirstAuthenticator(File credentials) {
        this.credentialsFile = credentials;
        getCredentialsFromFile();
    }

    @Override
    public AuthenticationResult requestAccess(String username, String password){
        if (performCheck(username,password))
            //create new user by userbuilder
        else if (next != null)
            return next.requestAccess(username, password);
        return new AuthenticationResult(false, null); //exception?
    }

    private boolean performCheck(String username, String password) {
        if (!credentials.containsKey(username))
            return false;

        return credentials.get(username).equals(password);

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

    @Override
    public void setNextHandler(AuthenticationHandler handler){
        next = handler;
    }

    @Override
    public String getStatus(){
        return status;
    }

}