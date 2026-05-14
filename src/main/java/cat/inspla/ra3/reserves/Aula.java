package cat.inspla.ra3.reserves;

/**
 * Representa una aula que es pot reservar dins del sistema de reserves.
 * Nom Alumne: Joel Ogalla Ramos 1DAM
 * <p>
 * Una aula té un nom identificatiu, una capacitat màxima i un estat de disponibilitat.
 * Implementa la interfície {@link Reservable}, permetent gestionar reserves
 * i calcular el cost en funció del nombre d'hores.
 */
public class Aula implements Reservable {
    private final String nom;
    private final int capacitat;
    private boolean disponible;

    /**
     * Crea una nova aula amb el nom i la capacitat indicats.
     *
     * @param nom nom identificatiu de l'aula (no pot ser nul ni buit)
     * @param capacitat capacitat màxima de l'aula (ha de ser positiva)
     * @throws IllegalArgumentException si el nom és nul o buit
     * @throws IllegalArgumentException si la capacitat és menor o igual a zero
     */
    public Aula(String nom, int capacitat) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de l'aula és obligatori");
        }
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser positiva");
        }
        this.nom = nom;
        this.capacitat = capacitat;
        this.disponible = true;
    }

    @Override
    public String getNom() { return nom; }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.AULA; }

    @Override
    public int getCapacitat() { return capacitat; }

    @Override
    public boolean estaDisponible() { return disponible; }

    /**
     * Reserva l'aula si està disponible.
     * <p>
     * Si l'aula ja està reservada (no disponible), es llança una excepció.
     *
     * @throws IllegalStateException si l'aula ja està reservada
     */
    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    /**
     * Allibera l'aula, marcant-la com a disponible.
     * <p>
     * Aquest mètode no llança excepcions encara que l'aula ja estigui disponible.
     */
    @Override
    public void alliberar() {
        disponible = true;
    }

    /**
     * Calcula el cost de la reserva de l'aula en funció de les hores indicades.
     * <p>
     * El cost es calcula multiplicant el nombre d'hores per una tarifa fixa de 12.0 unitats monetàries per hora.
     *
     * @param hores nombre d'hores de la reserva (ha de ser positiu)
     * @return cost total de la reserva
     * @throws IllegalArgumentException si el nombre d'hores és menor o igual a zero
     */
    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        return hores * 12.0;
    }

    /**
     * Valida que el nombre d'hores sigui positiu.
     *
     * @param hores nombre d'hores a validar
     * @throws IllegalArgumentException si hores és menor o igual a zero
     */
    protected void validarHores(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
    }
}