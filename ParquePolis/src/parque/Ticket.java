package parque;

import java.time.LocalDateTime;
import java.time.Duration;

public class Ticket {
    private static int contadorId = 1;

    private final boolean usarModoSimulacao;
    private final double precoUnidadeTempo;

    private int id;
    private Veiculo veiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private long duracao;
    private double tarifa;

    public Ticket(Veiculo veiculo) {
        this.id = contadorId++;
        this.veiculo = veiculo;
        this.horaEntrada = LocalDateTime.now();

        this.usarModoSimulacao = Boolean.parseBoolean(System.getProperty("parque.modoSimulacao", "false"));
        this.precoUnidadeTempo = Double.parseDouble(System.getProperty( "parque.precoUnidadeTempo", "0.50"));

    }


    public void registarSaida() {
        this.horaSaida = LocalDateTime.now();
        if (usarModoSimulacao) {
            this.duracao = Duration.between(horaEntrada, horaSaida).toMillis() / 500; // Simula 1 minuto a cada 500ms
        } else {
            this.duracao = Duration.between(horaEntrada, horaSaida).toMinutes();
        }

        this.tarifa = this.duracao * precoUnidadeTempo;
    }

    public double calcularTarifa() {
        if (horaSaida == null) {
            throw new IllegalStateException("Não é possível calcular a tarifa: o ticket #" + id + " ainda está ativo.");
        }
        tarifa = this.duracao * precoUnidadeTempo;
        return this.tarifa;
    }

    public long getDuracao() {
        if (horaSaida == null) {
            throw new IllegalStateException("Não é possível obter a duração: o ticket #" + id + " ainda está ativo.");
        }
        return this.duracao;
    }

    public double getTarifa() {
        return calcularTarifa();
    }

    public int getId() { return id; }
    public Veiculo getVeiculo() { return veiculo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public boolean estaAtivo() { return horaSaida == null; }
}
