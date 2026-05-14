package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    // ----------------------------
    // 🟢 CONSTRUCTOR
    // ----------------------------

    @Test
    void constructor_valid() {
        Aula aula = new Aula("A1", 30);

        assertEquals("A1", aula.getNom());
        assertEquals(30, aula.getCapacitat());
        assertTrue(aula.estaDisponible());
        assertEquals(TipusRecurs.AULA, aula.getTipus());
    }

    @Test
    void constructor_nomNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula(null, 20));
    }

    @Test
    void constructor_nomBuit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("   ", 20));
    }

    @Test
    void constructor_capacitatZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("A1", 0));
    }

    @Test
    void constructor_capacitatNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("A1", -5));
    }

    // ----------------------------
    // 🔐 RESERVES
    // ----------------------------

    @Test
    void reservar_canviaEstat() {
        Aula aula = new Aula("A1", 30);

        aula.reservar();

        assertFalse(aula.estaDisponible());
    }

    @Test
    void reservar_dosCops_llencaExcepcio() {
        Aula aula = new Aula("A1", 30);

        aula.reservar();

        assertThrows(IllegalStateException.class, aula::reservar);
    }

    @Test
    void alliberar_tornaDisponible() {
        Aula aula = new Aula("A1", 30);

        aula.reservar();
        aula.alliberar();

        assertTrue(aula.estaDisponible());
    }

    @Test
    void alliberar_senseReservar_esMantéDisponible() {
        Aula aula = new Aula("A1", 30);

        aula.alliberar();

        assertTrue(aula.estaDisponible());
    }

    // ----------------------------
    // 💰 COSTOS
    // ----------------------------

    @Test
    void calcularCostReserva_correcte() {
        Aula aula = new Aula("A1", 30);

        double cost = aula.calcularCostReserva(3);

        assertEquals(36.0, cost);
    }

    @Test
    void calcularCostReserva_hores1() {
        Aula aula = new Aula("A1", 30);

        assertEquals(12.0, aula.calcularCostReserva(1));
    }

    @Test
    void calcularCostReserva_horesInvalides_zero() {
        Aula aula = new Aula("A1", 30);

        assertThrows(IllegalArgumentException.class,
                () -> aula.calcularCostReserva(0));
    }

    @Test
    void calcularCostReserva_horesInvalides_negatives() {
        Aula aula = new Aula("A1", 30);

        assertThrows(IllegalArgumentException.class,
                () -> aula.calcularCostReserva(-3));
    }

    // ----------------------------
    // 🔁 PARAMETRITZAT
    // ----------------------------

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void calcularCostReserva_parametritzat(int hores) {
        Aula aula = new Aula("A1", 30);

        double resultat = aula.calcularCostReserva(hores);

        assertEquals(hores * 12.0, resultat);
    }
}