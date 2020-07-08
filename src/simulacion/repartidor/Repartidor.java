package simulacion.repartidor;

public class Repartidor {

    int id;
    EstadoRep estado;

    public Repartidor(int index) {
        this.id = index;
        this.estado = EstadoRep.LIBRE;
    }

    public void setEstado(EstadoRep estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoRep getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }
}
