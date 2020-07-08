package simulacion;

public class Funciones {

    double rnd1;
    double rnd2;
    double tiempo;
    double prox;
    double a;
    double b;
    double reloj;
    double n1, n2;
    public Funciones(double a, double b, double reloj)
    {
        this.a =a;
        this.b =b;
        this.reloj =reloj;
    }

    public Funciones()
    {}

    public boolean generarEventoEntregaPedido() {
        do{
            rnd1 =getRND();
        }while(rnd1 <(double)0.001 || rnd1 >(double)0.999);
        double nroUniforme= a +((b - a)* rnd1);
        tiempo =nroUniforme;
        prox = reloj + tiempo;
        return true;
    }

    //Boxx Muller
    public void generarEventoLlegada() {
        do{
            rnd1 =getRND();
        }while(rnd1 <(double)0.001 || rnd1 >(double)0.999);

        do{
            rnd2 =getRND();
        }while(rnd2 <(double)0.001 || rnd2 >(double)0.999);

        n1 = (Math.sqrt(-2*Math.log(rnd1))*Math.cos(2*Math.PI*rnd2))*b+a;
        n2 = (Math.sqrt(-2*Math.log(rnd1))*Math.sin(2*Math.PI*rnd2))*b+a;

        //Porque la primera vez siempre uso n1
        tiempo = n1;
        prox = reloj + tiempo;

    }

    public boolean generarEstadoDestinatario() {
        do{
            rnd1 =getRND();
        }while(rnd1 <(double)0.001 || rnd1 >(double)0.999);

        boolean destinatario;
        if (rnd1 > 0 && rnd1 < 0.3){
            destinatario = false;
        }else{
            destinatario = true;
        }
        return destinatario;
    }

    public double redondear(double num) {
        int decimales = 4;
        double valor = num;
        String auxS = "1";
        for(int i = 0; i < decimales; i ++) {
            auxS += "0";
        }
        int auxI = Integer.parseInt(auxS);
        valor *= auxI;
        valor = Math.round(valor);
        valor /= auxI;
        return valor;
    }

    public double getRND() {
        double rnd = Math.random() * 1;
        return rnd;
    }


}
