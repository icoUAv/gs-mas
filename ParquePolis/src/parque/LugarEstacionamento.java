package parque;

public class LugarEstacionamento {
    private int numero;
    private boolean ocupado;
    private Veiculo veiculoAtual;

    public LugarEstacionamento(int numero) {
        this.numero = numero;
        this.ocupado = false;
    }

    public void ocupar(Veiculo v) {
        if (!ocupado) {
            this.veiculoAtual = v;
            this.ocupado = true;
        }
    }

    public void libertar() {
        this.veiculoAtual = null;
        this.ocupado = false;
    }

    public boolean isOcupado() { return ocupado; }
    public int getNumero() { return numero; }
    public Veiculo getVeiculoAtual() { return veiculoAtual; }
}
