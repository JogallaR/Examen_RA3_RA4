package cat.inspla.ra3.reserves;

public class Laboratori extends Aula {
    private final boolean necessitaSupervisio;

    public Laboratori(String nom, int capacitat, boolean necessitaSupervisio) {
        super(nom, capacitat);
        this.necessitaSupervisio = necessitaSupervisio;
    }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.LABORATORI; }

    public boolean necessitaSupervisio() { return necessitaSupervisio; }

    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        double base = hores * 20.0;
        return necessitaSupervisio ? base + 15.0 : base;
    }
}
