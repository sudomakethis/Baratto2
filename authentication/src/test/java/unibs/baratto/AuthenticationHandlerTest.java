package unibs.baratto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationHandlerTest {

    AuthenticationHandler handler = new AuthenticationHandler(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\test\\resources\\credentials.test"));

    @Test
    @DisplayName("Should read config file")
    void fileTest(){
        assertEquals("Ready", handler.getStatus());
    }

    @Test
    @DisplayName("Should return true When credentials are valid")
    void validCredentialsTest(){
        assertTrue(handler.isValid("tester", "tester"));
    }

    @Test
    @DisplayName("Should return false When credentials are not valid")
    void notValidCredentialsTest(){
        assertFalse(handler.isValid("beta", "tester"));
        assertFalse(handler.isValid("tester", "teste"));
    }

    @Test
    @DisplayName("Should work When new credentials added")
    void credentialsAdditionTest(){
        handler.addNewCredential("ciao", "ciao");
        assertTrue(handler.isValid("ciao", "ciao"));
        assertFalse(handler.isValid("ciao", "tester"));
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