package unibs.baratto.users;

public class Configurator extends User{

    public Configurator(String username) {
        super(username);
    }

    @Override
    public Runnable login() {
        return ()->System.out.print("ciao");
    }
}
