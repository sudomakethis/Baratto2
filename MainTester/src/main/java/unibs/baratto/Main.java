package unibs.baratto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Benvenuto!\n-----------------");
        login();
    }

    private static void login() {
        AuthenticationHandler defaultAuthenticator = new AuthenticationHandler(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\main\\resources\\credentials"));
        AuthenticationHandler authenticator = new AuthenticationHandler(new File("D:\\Enrico\\InellijWorkspace\\Baratto\\authentication\\src\\main\\resources\\credentials"));

        List<AuthenticationHandler> authenticationHandlersChain = new ArrayList<>();
        authenticationHandlersChain.add(defaultAuthenticator);
        authenticationHandlersChain.add(authenticator);

        System.out.print("Inserisci nome utente: ");
        var nome = in.next();
        System.out.print("Inserisci password: ");
        var pass = in.next();

        for (AuthenticationHandler handler : authenticationHandlersChain)
            if (handler.isValid(nome, pass)) {
                System.out.println("Benvenuto " + handler.getUser().getUsername());
                return;
            }
            else
                System.out.println("accesso non eseguito:\n" + handler.getStatus());

    }
}