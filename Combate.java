package magic;

import java.util.Random;

public class Combate {

    private boolean ronda = true;//será true en la ronda del usuario y false en la ronda enemiga
    Carta carta;
    Jugador[] jugador;

    /**
     *
     * @param cartaAtacante La carta que va a atacar
     * @param cartaDefensora La carta que recibe el ataque
     */
    public void ataque(Carta cartaAtacante, Carta cartaDefensora, Jugador aJugador) {
        cartaDefensora.setRevelada(true);
        cartaAtacante.setRevelada(true);
        cartaDefensora.setVida(cartaDefensora.vida - cartaAtacante.ataque);

        if (cartaDefensora.vida <= 0) {
            cartaDefensora.viva = false;
            aJugador.cartaPerdida++;
        }
        if (aJugador.cartaPerdida == 4) {
            aJugador.vivo = false;
        }
    }

    /**
     *
     * @param aJugador Jugador referido para conocer el estado de su carta
     * @return nos dará 2 numeros en 2 posiciones, el 1º será usado para elegir
     * un atacante y el 2º para elegir un defensor
     */
    public int[] atacanteIA(Jugador[] aJugador) {
        Random random = new Random();
        if (aJugador[1].cartaPerdida == 4) {
            aJugador[1].vivo = false;
        }
        int cartaAtacante;
        do {
            cartaAtacante = random.nextInt(4);
        } while (!aJugador[1].aMano[cartaAtacante].viva && aJugador[1].vivo);
        int cartaDefensora;
        do {
            cartaDefensora = random.nextInt(4);
        } while (!aJugador[0].aMano[cartaDefensora].viva && aJugador[1].vivo);

        int[] eleccionIA = {cartaAtacante, cartaDefensora};
        return eleccionIA;
    }

    /**
     *
     * @param cartaDefensoraS Sera la carta que va a recibir el ataque
     * @return Nos dará false si no está viva para poder cancelar el ataque
     */
    public boolean ataquePosible(Carta cartaDefensora) {
        if (!cartaDefensora.viva) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getRonda() {
        return ronda;
    }

    public void setRonda(boolean ronda) {
        this.ronda = ronda;
    }

}
