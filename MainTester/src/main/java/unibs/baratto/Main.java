package unibs.baratto;

import unibs.baratto.authentication.AuthenticationHandler;
import unibs.baratto.authentication.AuthenticationResult;
import unibs.baratto.authentication.BasicAuthenticator;
import unibs.baratto.authentication.FirstAuthenticator;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Benvenuto!\n-----------------");
        login();
    }

    private static void login() {
        AuthenticationHandler configsAuthenticator = new BasicAuthenticator(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\main\\resources\\credentials"));
        AuthenticationHandler defaultAuthenticator = new FirstAuthenticator(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\main\\resources\\credentials"));

        configsAuthenticator.setNextHandler(defaultAuthenticator);

        System.out.print("Inserisci nome utente: ");
        var nome = in.next();
        System.out.print("Inserisci password: ");
        var pass = in.next();

        AuthenticationResult result = configsAuthenticator.requestAccess(nome, pass);
        if (result.isPassed())
            System.out.println(result.getUser().getUsername());
        else
            System.out.println("Error");

    }
}