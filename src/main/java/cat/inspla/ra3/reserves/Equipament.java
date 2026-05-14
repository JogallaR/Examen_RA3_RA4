package cat.inspla.ra3.reserves;

public class Equipament implements Reservable {
    private final String nom;
    private final int unitats;
    private boolean disponible;

    public Equipament(String nom, int unitats) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de l'equipament és obligatori");
        }
        if (unitats <= 0) {
            throw new IllegalArgumentException("Les unitats han de ser positives");
        }
        this.nom = nom;
        this.unitats = unitats;
        this.disponible = true;
    }

    @Override
    public String getNom() { return nom; }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.EQUIPAMENT; }

    @Override
    public int getCapacitat() { return unitats; }

    @Override
    public boolean estaDisponible() { return disponible; }

    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    @Override
    public void alliberar() { disponible = true; }

    @Override
    public double calcularCostReserva(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
        return hores * unitats * 3.5;
    }
}
