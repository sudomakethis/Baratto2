package unibs.baratto.authentication;

import unibs.baratto.users.Configurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BasicAuthenticator implements AuthenticationHandler{

    private final File credentialsFile;
    private String status;
    private Map<String , String> credentials = new HashMap<>();


    public BasicAuthenticator(File credentials) {
        this.credentialsFile = credentials;
        getCredentialsFromFile();
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
    public AuthenticationResult requestAccess(String username, String password) {
        if (!credentials.containsKey(username))
            return new AuthenticationResult(false, null);

        var passed = credentials.get(username).equals(password);
        if (passed)
            return new AuthenticationResult(true, new Configurator(username));
        return new AuthenticationResult(false, null);
    }

    @Override
    public void setNextHandler(AuthenticationHandler handler) {

    }

    @Override
    public String getStatus() {
        return null;
    }
}
