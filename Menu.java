package magic;

import java.util.Scanner;
import java.util.Random;

public class Menu {

    public Menu() {
    }
    Jugador jugador;
    static Scanner teclado = new Scanner(System.in);

    public static String nombreJugador1() {
        System.out.println("Bienvenido invocador, dame tu nombre:");
        String nombre = teclado.nextLine();
        return nombre;
    }

    public static int personalizarCartaInicio() {
        System.out.println("Bienvenido a la personalizacion de cartas, aqui vas a poder cambiar la vida, ataque o nombre de cualquiera de ellas");
        System.out.println("Pero recuera que seran cambiadas antes de barajar y dar las manos, eso significa que pueden acabar en las manos del enemigo,\nel balance es importante");
        System.out.println("Lo has entendido?\n1-Si");
        int eleccion = teclado.nextInt();

        return eleccion;
    }

    /**
     *
     * @param carta Aqui colocaremos la carta que el usuario va a modificar
     * @param atributoCambiar en el metodo se crea un switch que varia entre
     * 1-Ataque, 2-Hp
     * @param nuevoNumAtributo el nuevo numero del atributo
     */
    public static void cambioDeCartaInt(Carta carta, int atributoCambiar, int nuevoNumAtributo) {
        switch (atributoCambiar) {
            case 1:
                carta.setAtaque(nuevoNumAtributo);
                System.out.println("Muy bien el ataque de " + carta.nombre + " ahora es de: " + nuevoNumAtributo);
                break;
            case 2:
                carta.setVida(nuevoNumAtributo);
                System.out.println("Muy bien la vida de " + carta.nombre + " ahora es de: " + nuevoNumAtributo);
                break;
        }
    }

    /**
     * He decidido usar dos metodos diferentes ya que se me complicaba el
     * modificar String dentro del anterior metodo de ints pero funciona de la
     * misma manera que "cambioDeCartaInt"
     *
     * @param carta la carta que modificaremos
     */
    public static void cambioDeCartaString(Carta carta) {
        System.out.println("Dime el nuevo nombre de la carta");
        String viejoNombre = carta.nombre;
        teclado.nextLine();//Me leía el enter por algún fallo, esta línea extra parece solucionar el problema
        String nuevoNombre = teclado.nextLine();
        carta.setNombre(nuevoNombre);
        System.out.println("Muy bien, la carta anteriormente conocida como " + viejoNombre + " se pasara a llamar: " + nuevoNombre);

    }

    public static int inicio(String nombre) {
        System.out.format("""
                          Va a comenzar el enfrentamiento del siglo %s
                          dime, que deseas hacer?:
                          1-Combate
                          2-Personalizar carta
                          3-Salir
                           """, nombre);
        return teclado.nextInt();

    }

    /**
     * Este metodo aparecera cada vez que el jugador ataque o pase turno
     *
     * @return nos devuelve la eleccion del usuario
     */
    public static int ronda() {
        System.out.format("""
                         Que deseas hacer?
                         1-Mirar tus cartas 
                         2-Atacar
                         3-Ver cartas del enemigo
                         4-Observar tablero
                         5-Info
                         """);
        int eleccion = teclado.nextInt();
        return eleccion;
    }

    public static void tablero(Carta cartaAliada1, Carta cartaAliada2, Carta cartaAliada3, Carta cartaAliada4, Carta cartaEnemiga1, Carta cartaEnemiga2, Carta cartaEnemiga3, Carta cartaEnemiga4) {
        if (cartaEnemiga1.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaEnemiga2.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaEnemiga3.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaEnemiga4.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        System.out.println("");
        System.out.println("");
        if (cartaAliada1.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaAliada2.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaAliada3.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        if (cartaAliada4.viva) {
            System.out.print("-0-");
        } else {
            System.out.print("-X-");
        }
        System.out.println("");

    }

    public static int[] eleccionDeAtaque() {
        int[] aEleccion = new int[2];
        System.out.println("Con que carta quieres atacar?");
        aEleccion[0] = teclado.nextInt() - 1;
        System.out.println("A que carta quieres atacar?");
        aEleccion[1] = teclado.nextInt() - 1;

        return aEleccion;
    }

    /**
     * Para hacerlo mas ameno introduciremos unas cuantas frases y haremos que
     * se reproduzcan de manera aleatoria para darle un poco más de dinamismo al
     * juego y al narrador
     *
     * @return
     */
    public static String[] aleatoriedadComentarista() {
        Random random = new Random();
        int numFraseInicio = random.nextInt(1, 5);
        String fraseInicio = "";
        switch (numFraseInicio) {
            case 1:
                fraseInicio = "Comienza la batalla con ";
                break;
            case 2:
                fraseInicio = "Empieza la matanza con ";
                break;
            case 3:
                fraseInicio = "Se le pusieron los ojos rojos, ahi va ";
                break;
            case 4:
                fraseInicio = "Es una mala bestia observad a ";
                break;
        }
        int numFraseIntermedia = random.nextInt(1, 5);
        String fraseIntermedia = "";
        switch (numFraseIntermedia) {
            case 1:
                fraseIntermedia = " embistiendo hacia ";
                break;
            case 2:
                fraseIntermedia = " cargando contra ";
                break;
            case 3:
                fraseIntermedia = " destruyendo a ";
                break;
            case 4:
                fraseIntermedia = " rebanando a ";
                break;
        }

        int numFraseFinal = random.nextInt(1, 5);
        String fraseFinal = "";
        switch (numFraseFinal) {
            case 1:
                fraseFinal = " agarraos a los asientos muchachos ";
                break;
            case 2:
                fraseFinal = " la sangre empapa la arena ";
                break;
            case 3:
                fraseFinal = " eso le va a dejar marca ";
                break;
            case 4:
                fraseFinal = " uff no me gustaria ser el, amigos ";
                break;

        }
        int numEscenarioInicial = random.nextInt(1, 4);
        String escenarioInicial = "";
        switch (numEscenarioInicial) {
            case 1:
                escenarioInicial = " El humo se dispersa y tenemos a ";
                break;
            case 2:
                escenarioInicial = " Tras el duro golpe vemos a ";
                break;
            case 3:
                escenarioInicial = " Veamos que ha quedado de el ";
                break;
        }
        int numEscenarioIntermedio = random.nextInt(1, 4);
        String escenarioIntermedio = "";
        switch (numEscenarioInicial) {
            case 1:
                escenarioIntermedio = " a ";
                break;
            case 2:
                escenarioIntermedio = " en ";
                break;
            case 3:
                escenarioIntermedio = " con ";
                break;
        }
        int numEscenarioFinal = random.nextInt(1, 3);
        String escenarioFinal = "";
        switch (numEscenarioFinal) {
            case 1:
                escenarioFinal = " hp ";
                break;
            case 2:
                escenarioFinal = " puntos de vida ";
                break;
        }
        String[] aFrase = {fraseInicio, fraseIntermedia, fraseFinal, escenarioInicial, escenarioIntermedio, escenarioFinal};
        return aFrase;
    }

    public static void escenaAtaque(Carta cartaAtacante, Carta cartaDefensora) {
        System.out.println("##################################");
        System.out.println("");
        System.out.println(aleatoriedadComentarista()[0] + cartaAtacante.nombre + aleatoriedadComentarista()[1] + cartaDefensora.nombre + aleatoriedadComentarista()[2]);
        System.out.println("");
        System.out.println(aleatoriedadComentarista()[3] + cartaDefensora.nombre + aleatoriedadComentarista()[4] + cartaDefensora.getVida() + aleatoriedadComentarista()[5]);
        System.out.println("");
        System.out.println("##################################");

    }

    public static int transicionEnemigo() {
        System.out.println("Veamos que va a hacer el enemigo, preparado?\n 1-adelante");
        int eleccion = teclado.nextInt();
        return eleccion;
    }

    public static void resultadoFinal(Jugador usuario, Jugador jugador2, String nombreUsuario) {
        if (!usuario.vivo) {
            System.out.println("HAS PERDIDO");
            System.out.println("Lo sentimos mucho " + nombreUsuario + " has sido derrotado, ahora tu alma es nuestra");
            System.out.println("Tus oidos te fallan, tus ojos se apagan, el fin se acerca");
        } else {
            System.out.println("HAS GANADO");
            System.out.println("*Escuchas una voz que viene del invocador vencido frente a ti*");
            System.out.println("Je, la suerte del novato,pero no te confies " + nombreUsuario + " esto no ha terminado aun, nos volveremos a ver");
        }
    }

    public static void despedida(String nombre) {
        System.out.format("""
                          Muchas gracias por venir, y mucha suerte en tus futuras batallas %s
                          """, nombre);

    }

}
