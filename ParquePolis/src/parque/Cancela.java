package parque;


import util.LogUtil;

public class Cancela {
    private boolean aberta;
    private Sensor sensor;

    public Cancela() {
        this.aberta = false;
        this.sensor = new Sensor();
    }

    public void abrir() {
        if (!aberta) {
            LogUtil.info("Cancela a abrir...");
            aberta = true;
        }
    }

    public void fechar() {
        if (aberta && !sensor.detectarVeiculo()) {
            LogUtil.info("Cancela a fechar...");
            aberta = false;
        }
    }

    public boolean isAberta() { return aberta; }
    public Sensor getSensor() { return sensor; }
}
