package unibs.baratto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unibs.baratto.authentication.FirstAuthenticator;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthenticatorTest {

    FirstAuthenticator handler = new FirstAuthenticator(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\test\\resources\\credentials.test"));

    @Test
    @DisplayName("Should read config file")
    void fileTest(){
        assertEquals("Ready", handler.getStatus());
    }

    @Test
    @DisplayName("Should return true When credentials are valid")
    void validCredentialsTest(){
        assertTrue(handler.requestAccess("tester", "tester").isPassed());
    }

    @Test
    @DisplayName("Should return false When credentials are not valid")
    void notValidCredentialsTest(){
        assertFalse(handler.requestAccess("beta", "tester").isPassed());
        assertFalse(handler.requestAccess("tester", "teste").isPassed());
    }

    @Test
    @DisplayName("Should work When new credentials added")
    void credentialsAdditionTest(){
        handler.addNewCredential("ciao", "ciao");
        assertTrue(handler.requestAccess("ciao", "ciao").isPassed());
        assertFalse(handler.requestAccess("ciao", "tester").isPassed());
        handler.removeCredential("ciao");
    }

    @Test
    @DisplayName("Should avoid duplication When adding new user")
    void credentialsDuplicationTest(){
        handler.addNewCredential("ciao", "ciao");
        handler.addNewCredential("ciao","ciao");
        assertEquals("Questo nome utente esiste già", handler.getStatus());
    }

}