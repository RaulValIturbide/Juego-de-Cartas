package magic;

public class Carta {

    boolean revelada; //cuando sea false no se verá
    boolean viva;   //será true cuando esté viva y false cuando esté muerta
    int vida;       //la vida de la carta, si llega a cero morirá
    int ataque;     //el ataque de la carta
    String nombre;  //el nombre de la carta

    public Carta(boolean revelada, boolean viva, int vida, int ataque, String nombre) {
        this.revelada = revelada;
        this.viva = viva;
        this.vida = vida;
        this.ataque = ataque;
        this.nombre = nombre;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getRevelada() {
        return revelada;
    }

    public boolean getViva() {
        return viva;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public static void verCartasEnemigas(Jugador[] aJugador) {
        for (int i = 0; i < aJugador[1].aMano.length; i++) {
            if (aJugador[1].aMano[i].revelada) {
                System.out.format("""
                                      carta %d 
                                      ********************
                                      %s
                                      HP: %d
                                      Atk: %d
                                      ********************
                                      """, i + 1, aJugador[1].aMano[i].nombre, aJugador[1].aMano[i].vida, aJugador[1].aMano[i].ataque);
            } else {
                System.out.format("""
                                  carta %d esta oculta
                                  """, i + 1);
            }
        }

    }

    public static void mostrarCartas(Jugador[] aJugador) {
        System.out.println("Estas son tus cartas:");
        for (int i = 0; i < 4; i++) {
            System.out.format("""
                                carta %d 
                                ********************
                                %s
                                HP: %d
                                Atk: %d
                                ********************
                                """, i + 1, aJugador[0].aMano[i].nombre, aJugador[0].aMano[i].vida, aJugador[0].aMano[i].ataque);
        }
    }

    public void mostrarInfo() {
        System.out.format("""
                          ****************************************
                          El nombre de esta carta es: %s
                          La vida de esta carta es de: %d
                          El ataque de esta carta es de: %d
                          """, nombre, vida, ataque);
    }

}
