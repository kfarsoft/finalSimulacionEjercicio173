package simulacion.admin_pedido;

import simulacion.repartidor.Repartidor;

public class Pedido {

    private int id;
    private String estado;
    private double horaInicioEspera;
    private double proximaEntrega;
    private double proximoReenvio; //Cuando el destinatario no esta se espera 1 hora mas
    private boolean reenviado;
    private double horaLlegada;
    private double proximaLLegadaNuevamente;
    private double tiempoEntreLlegada; //Me va a permitir saber el tiempo si el pedido paso los 45 minutos esperando
    private boolean realizarNuevamente;
    private Repartidor repartidor;


    public Pedido()
    {
        this.id=0;
        this.estado = "Nulo";
        this.reenviado = false;
        this.realizarNuevamente = false;
        this.horaInicioEspera =0;
        this.proximaEntrega =0;
        this.proximaLLegadaNuevamente =0;
        this.tiempoEntreLlegada =0;
        this.proximoReenvio =0;
        this.horaLlegada =0;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setReenviado(boolean reenv) {
        this.reenviado = reenv;
    }

    public boolean getReenviado() {
        return reenviado;
    }
    public boolean getRealizarNuevamente() {
        return realizarNuevamente;
    }
    public double getProximaLLegadaNuevamente() {
        return proximaLLegadaNuevamente;
    }
    public double getTiempoEntreLlegada() {
        return tiempoEntreLlegada;
    }


    public void setRealizarNuevamente(boolean realizar) {
        this.realizarNuevamente = realizar;
    }
    public void setTiempoEntreLlegada(double tiempo) {
        this.tiempoEntreLlegada = tiempo;
    }
    public void setProximaLLegadaNuevamente(double tiempo) {
        this.proximaLLegadaNuevamente = tiempo;
    }


    public double getHoraInicioEspera()
    {
        return horaInicioEspera;
    }
    public double getHoraLlegada()
    {
        return horaLlegada;
    }
    public void setHoraInicioEspera(double horaInicioEspera)
    {
        this.horaInicioEspera = horaInicioEspera;
    }
    public double getProximaEntrega()
    {
        return proximaEntrega;
    }
    public void setProximaEntrega(double proximaEntrega)
    {
        this.proximaEntrega = proximaEntrega;
    }
    public void setHoraLlegada(double horaLlegada)
    {
        this.horaLlegada = horaLlegada;
    }
    public void setProximoReenvio(double horaProximoReenvio)
    {
        this.proximoReenvio = horaProximoReenvio;
    }
    public double getProximoReenvio()
    {
        return proximoReenvio;
    }
    @Override
    public String toString() {
        return "\n\tPedido: #" + id + "\tHora llegada: " + horaLlegada + "\tEstado: " + estado;
    }


}
