package cat.inspla.ra3.reserves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServeiReserves {
    private final List<Reservable> recursos = new ArrayList<>();

    public void afegirRecurs(Reservable recurs) {
        if (recurs == null) {
            throw new IllegalArgumentException("El recurs no pot ser nul");
        }
        recursos.add(recurs);
    }

    public List<Reservable> getRecursos() {
        return Collections.unmodifiableList(recursos);
    }

    public double calcularCostTotal(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
        double total = 0;
        for (Reservable recurs : recursos) {
            total += recurs.calcularCostReserva(hores);
        }
        return total;
    }

    public long comptarDisponibles() {
        return recursos.stream().filter(Reservable::estaDisponible).count();
    }

    public Reservable buscarPerNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de cerca és obligatori");
        }
        return recursos.stream()
                .filter(r -> r.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * TODO RA4: aquest mètode funciona, però està fet expressament de manera poc eficient.
     * Cal optimitzar-lo utilitzant eines adequades del llenguatge Java.
     */
//    public List<Reservable> obtenirRecursosOrdenatsPerNom() {
//        List<Reservable> copia = new ArrayList<>(recursos);
//
//        for (int i = 0; i < copia.size(); i++) {
//            for (int j = 0; j < copia.size() - 1; j++) {
//                if (copia.get(j).getNom().compareToIgnoreCase(copia.get(j + 1).getNom()) > 0) {
//                    Reservable temporal = copia.get(j);
//                    copia.set(j, copia.get(j + 1));
//                    copia.set(j + 1, temporal);
//                }
//            }
//        }
//
//        return copia;
//    }

    public List<Reservable> obtenirRecursosOrdenatsPerNom() {
        List<Reservable> copia = new ArrayList<>(recursos);
        copia.sort((r1, r2) -> r1.getNom().compareToIgnoreCase(r2.getNom()));
        return copia;
    }

    /**
     * TODO RA4: aquest mètode concatena Strings dins d'un bucle.
//     * Cal optimitzar-lo sense canviar el resultat retornat.
//     */
//    public String generarInformeRecursos() {
//        String informe = "";
//
//        for (Reservable recurs : recursos) {
//            informe = informe + recurs.getNom() + " - " + recurs.getTipus() + " - "
//                    + (recurs.estaDisponible() ? "Disponible" : "Reservat") + System.lineSeparator();
//        }
//
//        return informe;
//    }

    public String generarInformeRecursos() {
        StringBuilder informe = new StringBuilder();

        for (Reservable recurs : recursos) {
            informe.append(recurs.getNom())
                    .append(" - ")
                    .append(recurs.getTipus())
                    .append(" - ")
                    .append(recurs.estaDisponible() ? "Disponible" : "Reservat")
                    .append(System.lineSeparator());
        }

        return informe.toString();
    }
}
