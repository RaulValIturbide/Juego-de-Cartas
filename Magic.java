package magic;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Magic {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        Combate combate = new Combate();
        Carta[] aCarta = new Carta[10];
        Jugador[] aJugador = new Jugador[2];
        creadorDeBaraja(aCarta);
        crearJugador(aJugador, aCarta);

        String nombreUsuario = Menu.nombreJugador1();
        int eleccion;
        do {
            eleccion = Menu.inicio(nombreUsuario);

            switch (eleccion) {
                case 1:
                    int[] cartasRandom = new int[8]; //La cantidad de cartas totales que será usadas en el juego
                    int contador = 0; //Un contador que usaremos para conocer la cantidad de cartas que llevamos introducidas
                    while (contador < cartasRandom.length) {
                        int aleatorio = random.nextInt(10);
                        if (!repetida(cartasRandom, aleatorio, contador)) {
                            cartasRandom[contador] = aleatorio;
                            contador++;
                        }
                    }
                    //Estos dos arrays tienen como objetivo guardar 8 numeros aleatorios entre 0 y 9 que no se repitan entre si
                    int[] aMano1 = new int[4];//Aqui se guardarán 4 números aleatorios que luego serán dados al jugador 1 para pasarle el número de la carta
                    int[] aMano2 = new int[4]; // Lo mismo pero en el jugador 2 

                    for (int i = 0; i < 4; i++) {
                        aMano1[i] = cartasRandom[i];
                        aMano2[i] = cartasRandom[i + 4];
                    }

                    //Las cartas de nuestro usuario
                    for (int j = 0; j < 4; j++) {
                        aJugador[0].aMano[j] = aCarta[aMano1[j]];
                    }

                    Carta.mostrarCartas(aJugador);//Este metodo le da las cartas al jugador para que pueda ver su nombre, vida, etc

                    // Estas son las cartas del enemigo
                    for (int i = 0; i < 4; i++) {
                        aJugador[1].aMano[i] = aCarta[aMano2[i]];
                    }
                    /**
                     * ********************************************************************************************************************
                     */
                    /*Esta será la logica constante del combate hasta que uno de los jugadores muera, es decir, su boolean vida sea false*/
                    /**
                     * ********************************************************************************************************************
                     */
                    do {
                        do {

                            int eleccionRonda = Menu.ronda();
                            switch (eleccionRonda) {
                                case 1:
                                    Carta.mostrarCartas(aJugador);
                                    break;
                                case 2:
                                    int[] aEleccionAtaque;
                                    do {
                                        aEleccionAtaque = Menu.eleccionDeAtaque();
                                        if (!aJugador[1].aMano[aEleccionAtaque[1]].viva) {
                                            System.out.println("Esa carta ya ha sido aniquilada " + nombreUsuario + " elige otra que no este muerta para darle el mismo final");
                                        }
                                        if (!aJugador[0].aMano[aEleccionAtaque[0]].viva) {
                                            System.out.println("Lo siento, tu carta " + aJugador[0].aMano[aEleccionAtaque[0]].nombre + " ya sufrio una muerte heroica y fue destruida, elige una que siga viva");
                                        }
                                    } while (!aJugador[1].aMano[aEleccionAtaque[1]].viva || !aJugador[0].aMano[aEleccionAtaque[0]].viva && aJugador[0].vivo);

                                    combate.ataque(aJugador[0].aMano[aEleccionAtaque[0]], aJugador[1].aMano[aEleccionAtaque[1]], aJugador[1]);
                                    Menu.escenaAtaque(aJugador[0].aMano[aEleccionAtaque[0]], aJugador[1].aMano[aEleccionAtaque[1]]);
                                    combate.setRonda(false);

                                    break;
                                case 3:
                                    Carta.verCartasEnemigas(aJugador);
                                    break;
                                case 4:
                                    Menu.tablero(aJugador[0].aMano[0], aJugador[0].aMano[1], aJugador[0].aMano[2], aJugador[0].aMano[3], aJugador[1].aMano[0], aJugador[1].aMano[1], aJugador[1].aMano[2], aJugador[1].aMano[3]);
                                    break;
                                case 5:
                                    aJugador[0].mostrarInfo(aJugador, aJugador);

                            }
                        } while (combate.getRonda() == true);
                        int transicion;//El objetivo de este metodo solo es transicionar el combate entre el usuario vs ia  al de ia vs usuario haciendo un paron para darle una mayor nitidez y mejor lectura
                        do {
                            transicion = Menu.transicionEnemigo();

                        } while (transicion != 1);
                        int atacanteIA = combate.atacanteIA(aJugador)[0];//Numero aleatorio de una carta del bando enemigo que no está muerta
                        int defensorIA = combate.atacanteIA(aJugador)[1];//Numero aleatorio de una carta de la "IA" que no está muerta

                        combate.ataque(aJugador[1].aMano[atacanteIA], aJugador[0].aMano[defensorIA], aJugador[0]);
                        Menu.escenaAtaque(aJugador[1].aMano[atacanteIA], aJugador[0].aMano[defensorIA]);
                        combate.setRonda(true);

                    } while (aJugador[0].vivo && aJugador[1].vivo);

                    Menu.resultadoFinal(aJugador[0], aJugador[1], nombreUsuario);

                    break;
                case 2:
                    buclePersonalizacion(aCarta);

                    break;

                case 3:
                    Menu.despedida(nombreUsuario);
                    break;

            }
        } while (eleccion != 3);
    }

    //Este metodo sirve para crear la baraja con todas sus cartas
    private static void creadorDeBaraja(Carta[] aCarta) {
        aCarta[0] = new Carta(false, true, 4, 6, "Carioca_Terrestre");
        aCarta[1] = new Carta(false, true, 5, 7, "Magmita_Tactica");
        aCarta[2] = new Carta(false, true, 7, 4, "Enano_Abuson");
        aCarta[3] = new Carta(false, true, 12, 2, "Fortaleza_Flotante");
        aCarta[4] = new Carta(false, true, 4, 6, "Templario_De_La_Luz_Solar");
        aCarta[5] = new Carta(false, true, 3, 6, "Goblin_Descontrolado");
        aCarta[6] = new Carta(false, true, 4, 4, "Kobold_Pescador");
        aCarta[7] = new Carta(false, true, 8, 8, "Lancero_Del_Cuarto_Ejercito");
        aCarta[8] = new Carta(false, true, 1, 1, "Eriasis");
        aCarta[9] = new Carta(false, true, 1, 32, "Pollo-Saurio");
    }

    /**
     * Este metodo nos permitirá ver todas las cartas con sus modificaciones
     * actualizadas
     *
     * @param aCarta La creacion de la baraja, en este caso solo hay una baraja
     * que es aCarta
     */
    public static void verTodasLasCartas(Carta[] aCarta) {
        for (int i = 0; i < aCarta.length; i++) {
            System.out.format("""
                              
                                     **** Carta %d ****
                                        Nombre: %s
                                        Vida:   %d
                                        Ataque: %d
                                      """, i + 1, aCarta[i].nombre, aCarta[i].vida, aCarta[i].ataque);
        }

    }

    //Este metodo crea jugadores y les da el array aCarta para crear su mazo
    private static void crearJugador(Jugador[] aJugador, Carta[] aCarta) {
        aJugador[0] = new Jugador(0, true, aCarta);
        aJugador[1] = new Jugador(0, true, aCarta);
    }

    /**
     *
     * @param cartasRandom La cantidad de cartas que vamos a usar en el juego
     * @param random el número aleatorio que comprobaremos si ya ha salido
     * @param contador
     * @return Nos dará un false si la carta no se ha repetido y un true si la
     * carta está repetida
     */
    public static boolean repetida(int[] cartasRandom, int random, int contador) {
        for (int i = 0; i < contador; i++) {
            if (cartasRandom[i] == random) {
                return true;
            }
        }
        return false;

    }

    public static void buclePersonalizacion(Carta[] aCarta) {
        int persUsuario;
        boolean salir = false;

        do {
            persUsuario = Menu.personalizarCartaInicio();//Un metodo para que se entienda lo que el usuario va a hacer, es decir, un resumen o presentación de la opcion
        } while (persUsuario != 1);
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Estas son las cartas que existen:");
            verTodasLasCartas(aCarta);
            System.out.println("Cual es el numero de la carta que deseas cambiar?:");
            int numeroCambioCarta;
            do {
                numeroCambioCarta = teclado.nextInt();
                if (numeroCambioCarta < 1 || numeroCambioCarta > 10) {
                    System.out.println("Numero del 1 al 10 por favor");
                }

            } while (numeroCambioCarta < 1 || numeroCambioCarta > 10);
            int numeroNuevoNumeroAtributo;
            System.out.println("Cual es el atributo que deseas cambiar?\n1-Ataque\n2-Vida\n3-Nombre");
            int numeroCambioAtributo = teclado.nextInt();
            switch (numeroCambioAtributo) {
                case 1:
                    System.out.println("Cual es el nuevo valor que quieres darle?:");
                    numeroNuevoNumeroAtributo = teclado.nextInt();
                    Menu.cambioDeCartaInt(aCarta[numeroCambioCarta - 1], numeroCambioAtributo, numeroNuevoNumeroAtributo);
                    break;
                case 2:
                    System.out.println("Cual es el nuevo valor que quieres darle?:");
                    numeroNuevoNumeroAtributo = teclado.nextInt();
                    Menu.cambioDeCartaInt(aCarta[numeroCambioCarta - 1], numeroCambioAtributo, numeroNuevoNumeroAtributo);
                    break;
                case 3:
                    Menu.cambioDeCartaString(aCarta[numeroCambioCarta - 1]);
                    break;

            }

            System.out.println("Todo listo, quieres salir al menu principal?\n1-Si\n2-No,quiero cambiar mas cosas");
            int eleccionSalir;
            do {
                eleccionSalir = teclado.nextInt();

            } while (eleccionSalir > 2 || eleccionSalir < 1);

            if (eleccionSalir == 1) {
                salir = true;
            }

        } while (!salir);

    }

}
