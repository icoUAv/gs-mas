package parque;

public class Veiculo {

    private String matricula;

    public Veiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return matricula != null ? matricula.equals(veiculo.matricula) : veiculo.matricula == null;
    }

    @Override
    public int hashCode() {
        return matricula != null ? matricula.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "[" + matricula + "]";


    }
}
