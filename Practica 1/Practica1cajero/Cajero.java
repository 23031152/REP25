import java.util.ArrayList;

public class Cajero {
    private ArrayList<Usuario> usuarios;

    public Cajero() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("1234", "Juan", 1000.0));
        usuarios.add(new Usuario("5678", "Maria", 2500.0));
    }

    public Usuario login(String pin) {
        for (Usuario u : usuarios) {
            if (u.getPin().equals(pin)) {
                return u;
            }
        }
        return null;
    }
}