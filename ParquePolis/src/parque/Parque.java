package parque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parque {

    private String nomeDoParque;
    private List<LugarEstacionamento> lugares;
    private List<Ticket> ticketsAtivos;
    private Cancela cancela;

    public Parque(String nome, int capacidade) {
        this.nomeDoParque = nome;
        this.lugares = new ArrayList<>();
        this.ticketsAtivos = new ArrayList<>();
        this.cancela = new Cancela();
        for (int i = 1; i <= capacidade; i++) {
            lugares.add(new LugarEstacionamento(i));
        }
    }

    public Ticket registarEntrada(Veiculo veiculo) throws IllegalStateException {
        if (estaCheio()) {
            throw new IllegalStateException("Não é possível registar a entrada: o parque está cheio.");
        }
        Ticket ticket = new Ticket(veiculo);
        ticketsAtivos.add(ticket);
        LugarEstacionamento lugar = procurarLugarLivre()
                .orElseThrow(() -> new IllegalStateException("Não foi possível encontrar um lugar livre."));
        lugar.ocupar(veiculo);
        cancela.abrir();
        return ticket;
    }

    public double registarSaida(Ticket ticket) throws IllegalStateException {
        if (!ticket.estaAtivo()) {
            throw new IllegalStateException("Não é possível registar a saída: o ticket #" + ticket.getId() + " já foi fechado.");
        }
        ticket.registarSaida();
        ticketsAtivos.remove(ticket);
        libertarLugar(ticket.getVeiculo());
        double tarifa = ticket.calcularTarifa();
        cancela.abrir();
        return tarifa;
    }

    private Optional<LugarEstacionamento> procurarLugarLivre()  {
        for (LugarEstacionamento lugar : lugares) {
            if (!lugar.isOcupado()) return Optional.of(lugar);
        }
        return Optional.empty();
    }


    private void libertarLugar(Veiculo veiculo) {
        for (LugarEstacionamento lugar : lugares) {
            if (lugar.isOcupado() &&
                lugar.getVeiculoAtual().equals(veiculo)) {
                lugar.libertar();
                return;
            }
        }
    }

    public boolean estaCheio() {
        return lugaresDisponiveis() == 0;
    }

    public int lugaresDisponiveis() {
        int contador = 0;
        for (LugarEstacionamento lugar : lugares) {
            if (!lugar.isOcupado()) contador++;
        }
        return contador;
    }

    public String getNomeDoParque() { return nomeDoParque; }
}
