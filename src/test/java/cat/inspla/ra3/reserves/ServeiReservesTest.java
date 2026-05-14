package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesTest {

    private ServeiReserves servei;

    @BeforeEach
    void setUp() {
        servei = new ServeiReserves();
    }

    // ----------------------------
    // ✅ TESTS BÀSICS
    // ----------------------------

    @Test
    void afegirRecurs_valid() {
        Aula aula = new Aula("A1", 30);

        servei.afegirRecurs(aula);

        assertEquals(1, servei.getRecursos().size());
    }

    @Test
    void afegirRecurs_null_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> servei.afegirRecurs(null));
    }

    @Test
    void getRecursos_esImmutable() {
        servei.afegirRecurs(new Aula("A1", 30));

        List<Reservable> llista = servei.getRecursos();

        assertThrows(UnsupportedOperationException.class,
                () -> llista.add(new Aula("A2", 20)));
    }

    // ----------------------------
    // 💰 COSTOS
    // ----------------------------

    @Test
    void calcularCostTotal_correcte() {
        servei.afegirRecurs(new Aula("A1", 30));
        servei.afegirRecurs(new Aula("A2", 20));

        double total = servei.calcularCostTotal(2);

        assertEquals(48.0, total);
    }

    @Test
    void calcularCostTotal_senseRecursos() {
        double total = servei.calcularCostTotal(3);

        assertEquals(0.0, total);
    }

    @Test
    void calcularCostTotal_horesInvalides() {
        assertThrows(IllegalArgumentException.class,
                () -> servei.calcularCostTotal(0));
    }

    // ----------------------------
    // 📊 DISPONIBILITAT
    // ----------------------------

    @Test
    void comptarDisponibles_totsDisponibles() {
        servei.afegirRecurs(new Aula("A1", 30));
        servei.afegirRecurs(new Aula("A2", 20));

        assertEquals(2, servei.comptarDisponibles());
    }

    @Test
    void comptarDisponibles_algunsReservats() {
        Aula a1 = new Aula("A1", 30);
        Aula a2 = new Aula("A2", 20);
        a2.reservar();

        servei.afegirRecurs(a1);
        servei.afegirRecurs(a2);

        assertEquals(1, servei.comptarDisponibles());
    }

    // ----------------------------
    // 🔍 CERCA
    // ----------------------------

    @Test
    void buscarPerNom_existeix() {
        Aula aula = new Aula("A1", 30);
        servei.afegirRecurs(aula);

        assertEquals(aula, servei.buscarPerNom("a1"));
    }

    @Test
    void buscarPerNom_noExisteix() {
        servei.afegirRecurs(new Aula("A1", 30));

        assertNull(servei.buscarPerNom("A2"));
    }

    @Test
    void buscarPerNom_nomInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> servei.buscarPerNom(" "));
    }

    // ----------------------------
    // 🔤 ORDENACIÓ
    // ----------------------------

    @Test
    void obtenirRecursosOrdenatsPerNom() {
        servei.afegirRecurs(new Aula("Zeta", 30));
        servei.afegirRecurs(new Aula("Alpha", 20));
        servei.afegirRecurs(new Aula("Beta", 25));

        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();

        assertEquals("Alpha", ordenats.get(0).getNom());
        assertEquals("Beta", ordenats.get(1).getNom());
        assertEquals("Zeta", ordenats.get(2).getNom());
    }

    @Test
    void obtenirRecursosOrdenats_noModificaOriginal() {
        servei.afegirRecurs(new Aula("B", 30));
        servei.afegirRecurs(new Aula("A", 20));

        servei.obtenirRecursosOrdenatsPerNom();

        assertEquals("B", servei.getRecursos().get(0).getNom());
    }

    // ----------------------------
    // 📄 INFORME
    // ----------------------------

    @Test
    void generarInformeRecursos_formatCorrecte() {
        Aula a1 = new Aula("A1", 30);
        Aula a2 = new Aula("A2", 20);
        a2.reservar();

        servei.afegirRecurs(a1);
        servei.afegirRecurs(a2);

        String informe = servei.generarInformeRecursos();

        assertTrue(informe.contains("A1 - AULA - Disponible"));
        assertTrue(informe.contains("A2 - AULA - Reservat"));
    }

    // ----------------------------
    // 🔁 PARAMETRITZAT (OBLIGATORI)
    // ----------------------------

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void calcularCostTotal_parametritzat(int hores) {
        servei.afegirRecurs(new Aula("A1", 30));

        double resultat = servei.calcularCostTotal(hores);

        assertEquals(hores * 12.0, resultat);
    }
}