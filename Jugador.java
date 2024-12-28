package magic;

public class Jugador {

    int cartaPerdida; //cantidad de cartas que el jugador ha perdido
    boolean vivo;     //sera true cuando el jugador este vivo
    Carta[] aMano;      //Un array que contendrá las cartas del jugador

    public Jugador(int cartaPerdida, boolean vivo, Carta[] aMano) {
        this.cartaPerdida = cartaPerdida;
        this.vivo = vivo;
        this.aMano = new Carta[4];

    }

    public int getCartaPerdida() {
        return cartaPerdida;
    }

    public boolean getVivo() {
        return vivo;
    }

    public Carta[] getCarta() {
        return aMano;
    }

    public void setCartaPerdida(int cartaPerdida) {
        this.cartaPerdida = cartaPerdida;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setCarta(Carta[] aMano) {
        this.aMano = aMano;
    }

    public void mostrarInfo(Jugador[] aJugadorUsuario, Jugador[] aJugadorEnemigo) {
        System.out.format("""
                          Has perdido: %d cartas
                          El enemigo ha perdido: %d cartas
                          """, aJugadorUsuario[0].cartaPerdida, aJugadorEnemigo[1].cartaPerdida);
    }
}
