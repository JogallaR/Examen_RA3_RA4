package cat.inspla.ra3.reserves;

public interface Reservable {
    String getNom();
    TipusRecurs getTipus();
    int getCapacitat();
    boolean estaDisponible();
    void reservar();
    void alliberar();
    double calcularCostReserva(int hores);
}
