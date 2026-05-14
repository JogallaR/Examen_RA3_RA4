package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesOptimitzacioTest {

    @Test
    void recursosOrdenatsPerNomRetornaCopiaOrdenada() {
        ServeiReserves servei = new ServeiReserves();

        Aula a1 = new Aula("Zeta", 30);
        Aula a2 = new Aula("Alpha", 20);
        Aula a3 = new Aula("Beta", 25);

        servei.afegirRecurs(a1);
        servei.afegirRecurs(a2);
        servei.afegirRecurs(a3);

        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();

        // ✔ Comprovar que està ordenat
        assertEquals("Alpha", ordenats.get(0).getNom());
        assertEquals("Beta", ordenats.get(1).getNom());
        assertEquals("Zeta", ordenats.get(2).getNom());

        // ✔ Comprovar que NO modifica l’original
        List<Reservable> originals = servei.getRecursos();
        assertEquals("Zeta", originals.get(0).getNom());
        assertEquals("Alpha", originals.get(1).getNom());
        assertEquals("Beta", originals.get(2).getNom());
    }

    @Test
    void generarInformeInclouNomTipusIEstat() {
        ServeiReserves servei = new ServeiReserves();

        Aula a1 = new Aula("A1", 30);
        Aula a2 = new Aula("A2", 20);

        a2.reservar(); // un disponible i un reservat

        servei.afegirRecurs(a1);
        servei.afegirRecurs(a2);

        String informe = servei.generarInformeRecursos();

        // ✔ Comprovar contingut
        assertTrue(informe.contains("A1 - AULA - Disponible"));
        assertTrue(informe.contains("A2 - AULA - Reservat"));
    }
}