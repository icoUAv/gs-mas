import parque.Parque;
import parque.Ticket;
import parque.Veiculo;
import util.LogUtil;
import util.WaitUtil;

public class ParqueDemoMain {


    public static void main(String[] args){
        final String MSG_VEICULO_ENTRAR = "Veículo {0} a entrar...";
        final String MSG_TICKET_EMITIDO = "\tTicket #{0} emitido para {1} . Lugares disponíveis: {2}";
        final String MSG_VEICULO_SAIR = "Veículo {0} a sair (Ticket #{1})...";
        final String MSG_TARIFA = "\tDuracao: {0} ; tarifa a pagar: {1} €";

        // Ativa modo de simulação para cálculo acelerado de duração no Ticket.
        System.setProperty("parque.modoSimulacao", "true");
        System.setProperty("parque.precoUnidadeTempo", "0.50");

        // Criar parque com 3 lugares
        Parque parque = new Parque("ParquePolis Central", 3);
        LogUtil.info("Parque \"{0}\" iniciado com 3 lugares.", parque.getNomeDoParque());

        // Criar veículos
        Veiculo v1 = new Veiculo("AA-12-BB");
        Veiculo v2 = new Veiculo("CC-34-DD");
        Veiculo v3 = new Veiculo("EE-56-FF");
        Veiculo v4 = new Veiculo("GG-78-HH");

        // --- Entradas ---
        LogUtil.info("--- ENTRADAS ---");

        LogUtil.info(MSG_VEICULO_ENTRAR, v1);
        Ticket t1 = parque.registarEntrada(v1);
        LogUtil.info(MSG_TICKET_EMITIDO, t1.getId(), t1.getVeiculo().getMatricula(), parque.lugaresDisponiveis());

        WaitUtil.waitNoLongerThan(0.5);

        LogUtil.info(MSG_VEICULO_ENTRAR, v2);
        Ticket t2 = parque.registarEntrada(v2);
        LogUtil.info(MSG_TICKET_EMITIDO, t2.getId(), t2.getVeiculo().getMatricula(), parque.lugaresDisponiveis());

        WaitUtil.waitNoLongerThan(0.5);

        LogUtil.info(MSG_VEICULO_ENTRAR, v3);
        Ticket t3 = parque.registarEntrada(v3);
        LogUtil.info(MSG_TICKET_EMITIDO, t3.getId(), t3.getVeiculo().getMatricula(), parque.lugaresDisponiveis());

        // Tentar entrar com parque cheio
        LogUtil.info("Veículo {0} a tentar entrar (parque cheio?)...", v4);
        Ticket t4 = null;
        try {
            t4 = parque.registarEntrada(v4);
        } catch (IllegalStateException e) {
            LogUtil.warning("  Entrada recusada ao veiculo {0}: {1}", v4.getMatricula(), e.getMessage());
        }

        // --- Saídas ---
        LogUtil.info("--- SAÍDAS ---");

        // Simular tempo de estacionamento (2 minutos → tarifa mínima de 1 hora)
        LogUtil.info("A simular tempo de estacionamento...");
        WaitUtil.waitNoLongerThan(3);

        LogUtil.info(MSG_VEICULO_SAIR, v1, t1.getId());
        double tarifa1 = parque.registarSaida(t1);
        LogUtil.info(MSG_TARIFA, t1.getDuracao(), String.format("%.2f", tarifa1));

        WaitUtil.waitNoLongerThan(1);

        LogUtil.info(MSG_VEICULO_SAIR, v2, t2.getId());
        double tarifa2 = parque.registarSaida(t2);
        LogUtil.info(MSG_TARIFA, t2.getDuracao(), String.format("%.2f", tarifa2));
        LogUtil.info("  Lugares disponíveis: {0}", parque.lugaresDisponiveis());

        WaitUtil.waitNoLongerThan(1);

        // v4 tenta entrar agora que há lugar
        LogUtil.info("Veículo {0} a tentar entrar novamente...", v4);
        try {
            t4 = parque.registarEntrada(v4);
            LogUtil.info(MSG_TICKET_EMITIDO, t4.getId(), t4.getVeiculo().getMatricula(), parque.lugaresDisponiveis());

        } catch (IllegalStateException e) {
            LogUtil.warning("  Entrada recusada — {0}", e.getMessage());
        }

        WaitUtil.waitNoLongerThan(2);
        LogUtil.info(MSG_VEICULO_SAIR, v3, t3.getId());
        double tarifa3 = parque.registarSaida(t3);
        LogUtil.info(MSG_TARIFA, t3.getDuracao(), String.format("%.2f", tarifa3));

        if (t4 != null) {
            LogUtil.info(MSG_VEICULO_SAIR, v4, t4.getId());
            double tarifa4 = parque.registarSaida(t4);
            LogUtil.info(MSG_TARIFA, t4.getDuracao(), String.format("%.2f", tarifa4));
        }

    }
}
