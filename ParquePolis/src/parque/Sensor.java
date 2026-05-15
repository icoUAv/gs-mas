package parque;

public class Sensor {
    private boolean ativo;

    public Sensor() {
        this.ativo = false;
    }

    public boolean detectarVeiculo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
