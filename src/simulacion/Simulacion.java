package simulacion;
import simulacion.admin_pedido.Pedido;
import simulacion.repartidor.EstadoRep;
import simulacion.repartidor.Repartidor;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.List;

public class Simulacion {
    private DefaultTableModel modelo;
    public int cantidadRepartidores;
    public int cantPedidos;
    public int nroFila;
    public double reloj;
    Vector<Pedido> vectorPed;
    double tiempoInicioSimulacion;
    double tiempoFinSimulacion;
    int getMaxColaEspera;
    //Llegada del pedido
    double rnd1LlegadaPedido;
    double rnd2LlegadaPedido;
    double tiempoLlegadaPedido;
    double proximaLlegadaPedido;
    //Entrega del pedido
    double rndEntregaPedido;
    double tiempoEntregaPedido;
    double proximaEntregaPedido;
    //Destinatario
    double rndDestinatario;
    String estadoDestinatario;

    double n1, n2;

    //Colas
    int colaPedidoEnEspera;
    int colaPedidoEnEsperaReenvio;
    int colaPedidosEnviados;
    int colaPedidosEntregados;
    int colaPedidosDesechados;
    double colaGananciaPerdida;
    Pedido[] vectorPedidos;
    int tamFila;
    double cantidadMinutos;
    int desdeMinutos;
    int hastaMinutos;
    int col;
    Vector<Double> orden;
    boolean termino;
    // Tiempo de llegada del pedido - N(5,20)
    double mediaTiempoLlegada;
    double desviacionTiempoLlegada;

    // Tiempo de entrega del pedido - N(7,23)
    double valorATiempoEntrega;
    double valorBTiempoEntrega;
    private List<Repartidor> repartidorList;
    TipoEvento eventoActual;
    Funciones util;
    public int getCantidadRepartidores() {
        return cantidadRepartidores;
    }
    public double getColaGananciaPerdida() {
        return colaGananciaPerdida;
    }



    public Simulacion(int cantidadRep, int cantMin, int desde, int hasta){
        this.cantidadRepartidores = cantidadRep;
        this.cantidadMinutos = cantMin;
        this.desdeMinutos = desde;
        this.hastaMinutos = hasta;
        asignarParametros();
        inicializarVariables();
    }

    //<editor-fold desc="InicializarVariables">
    public void inicializarVariables() {
        n1 = -1;
        n2 = -1;
        cantPedidos = 0;
        nroFila = 0;
        reloj = tiempoInicioSimulacion;
        eventoActual = TipoEvento.INICIARSIMULACION;
        orden=new Vector<Double>();
        estadoDestinatario = "";
        getMaxColaEspera = 0;
        //Inicializamos las colas
        colaGananciaPerdida = (double) 0;
        colaPedidoEnEspera = 0;
        colaPedidoEnEsperaReenvio = 0;
        colaPedidosEnviados = 0;
        colaPedidosEntregados = 0;
        colaPedidosDesechados = 0;
        util = new Funciones();
        termino = false;
        vectorPedidos = new Pedido[300];
        vectorPed = new Vector<Pedido>(Arrays.asList(vectorPedidos));
        repartidorList = new ArrayList<>();
        for (int i = 0; i < cantidadRepartidores; i++) {
            Repartidor repartidor = new Repartidor(i+1);
            repartidorList.add(repartidor);
        }
        for(int i = 0; i < vectorPedidos.length; i ++) {
            vectorPedidos[i] = new Pedido();
        }
    }
    //</editor-fold>

    //<editor-fold desc="AsignarParametros">
    public boolean asignarParametros() {
        tiempoInicioSimulacion =(double)0;
        tiempoFinSimulacion =(double)100;
        mediaTiempoLlegada = 20;
        desviacionTiempoLlegada = 5;
        valorATiempoEntrega = 15;
        valorBTiempoEntrega = 25;
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Simular">
    public DefaultTableModel Simular(){
        modelo = new DefaultTableModel();
        crearTabla();
        Object[] vector=new Object[tamFila];
        //---------------------------------------------------------Seteamos Reloj y Evento
        int col = 0;
        agregarNumI(vector, col, nroFila);
//        vector[col] = nroFila;
        sumarColumna(1);
//        vector[col] = util.redondear(reloj);
        agregarNumD(vector, col, util.redondear(reloj));
        sumarColumna(1);
//        vector[col] = eventoActual;
        agregarEvento(vector, col, eventoActual);
        sumarColumna(1);

        //---------------------------------------------------------Seteamos el primer evento de llegada
        Funciones funcLlegada = new Funciones(mediaTiempoLlegada, desviacionTiempoLlegada, reloj);
        funcLlegada.generarEventoLlegada(); //Seteamos el evento de llegada como TRUE
//        vector[col] = util.redondear(funcLlegada.rnd1); //rndLlegadaPedido
        agregarNumD(vector, col, util.redondear(funcLlegada.rnd1));
        sumarColumna(1);
//        vector[col] = util.redondear(funcLlegada.rnd2); //rndLlegadaPedido
        agregarNumD(vector, col, util.redondear(funcLlegada.rnd2));
        sumarColumna(1);
//        vector[col] = util.redondear(funcLlegada.tiempo); //tiempoLlegadaPedido
        agregarNumD(vector, col, util.redondear(funcLlegada.tiempo));
        sumarColumna(1);
//        vector[col] = util.redondear(funcLlegada.prox); //proximaLlegadaPedido
        agregarNumD(vector, col, util.redondear(funcLlegada.prox));
        sumarColumna(1);

            //La primera vez siempre los obtengo y seteo solo al n2 porque el n1 ya lo utilice
        n1 = -1;
        n2 = funcLlegada.n2;



        //---------------------------------------------------------Salteamos la entrega y destinatario
        sumarColumna(5);

        //---------------------------------------------------------Seteamos las 6 colas
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);
        agregarNumI(vector, col, 0);
//        vector[col] = 0;
        sumarColumna(1);

        //---------------------------------------------------------Seteamos el estado de los 3 repartidores

        for (int i = 0; i < repartidorList.size(); i++) {
//            vector[col] = repartidorList.get(i).getEstado();
            agregarEstadoRep(vector, col, repartidorList.get(i).getEstado());
            sumarColumna(1);
        }

        //---------------------------------------------------------Agregamos y Seteamos el vector
        modelo.addRow(vector);
        vector=new Object[tamFila];

        //---------------------------------------------------------Agregamos la proxima llegada dado que es la primera simulacion
        agregarAlVectorEventos(funcLlegada.prox);
        proximaLlegadaPedido = funcLlegada.prox;

        //---------------------------------------------------------Filas siguientes
        while (reloj < cantidadMinutos){
            nroFila++;
            simularFilaSigueinte(vector);
            modelo.addRow(vector);
            vector=new Object[tamFila];
        }
        return modelo;
    }
    //</editor-fold>

    private void agregarNumD(Object[] vector, int col, double valor){
        if(reloj >= desdeMinutos && reloj < hastaMinutos){
            vector[col] = valor;
        }
    }
    private void agregarNumI(Object[] vector, int col, int valor){
        if(reloj >= desdeMinutos && reloj < hastaMinutos){
            vector[col] = valor;
        }
    }
    private void agregarString(Object[] vector, int col, String valor){
        if(reloj >= desdeMinutos && reloj < hastaMinutos){
            vector[col] = valor;
        }
    }
    private void agregarEvento(Object[] vector, int col, TipoEvento valor){
        if(reloj >= desdeMinutos && reloj < hastaMinutos){
            vector[col] = valor;
        }
    }
    private void agregarEstadoRep(Object[] vector, int col, EstadoRep valor){
        if(reloj >= desdeMinutos && reloj < hastaMinutos){
            vector[col] = valor;
        }
    }

    //<editor-fold desc="SimularFilas">
    private void simularFilaSigueinte(Object[] vector){

        //---------------------------------------------------------Agregamos al vector evento los pedidos que necesitan volver a realizarse
        double proximaLlegadaNuevamente = 0;
        for (int i=0; i<vectorPedidos.length; i++){
            if (vectorPedidos[i].getEstado().equals("EE") && reloj - vectorPedidos[i].getHoraInicioEspera() >= 45 && !vectorPedidos[i].getRealizarNuevamente()){
                proximaLlegadaNuevamente = reloj+vectorPedidos[i].getTiempoEntreLlegada();
                vectorPedidos[i].setProximaLLegadaNuevamente(proximaLlegadaNuevamente);
                vectorPedidos[i].setRealizarNuevamente(true);
                agregarAlVectorEventos(proximaLlegadaNuevamente);
                colaGananciaPerdida-=2;
            }
        }
        double getProxEvento = 0;
        Collections.sort(orden);

        //---------------------------------------------------------Si no hay mas proximos eventos TERMINAMOS
        getProxEvento = orden.firstElement();
        orden.remove(getProxEvento);
        Pedido pedido = new Pedido();

        //---------------------------------------------------------Obtener pedido que corresponda al proximo evento
        boolean encontroEvento = false;
        for (int i=0; i<vectorPedidos.length; i++){
            if (!vectorPedidos[i].getEstado().equals("Finalizado") && !vectorPedidos[i].getEstado().equals("Desechado")){
                if (getProxEvento == vectorPedidos[i].getProximoReenvio()){
                    encontroEvento = true;
                    eventoActual = TipoEvento.REENVIAR_PEDIDO;
                    pedido = vectorPedidos[i];
                    pedido.setProximoReenvio(0);
                    break;
                }else if (getProxEvento == vectorPedidos[i].getProximaEntrega()){
                    encontroEvento = true;
                    pedido = vectorPedidos[i];
                    eventoActual = TipoEvento.FIN_ENTREGA;
                    break;
                }else if (vectorPedidos[i].getProximaLLegadaNuevamente() == getProxEvento && vectorPedidos[i].getRealizarNuevamente()){
                    encontroEvento = true;
                    vectorPedidos[i].setRealizarNuevamente(false);
                    eventoActual = TipoEvento.REALIZANDO_PEDIDO;
                    break;
                }
            }
        }
        if(!encontroEvento){
            eventoActual = TipoEvento.LLEGADA_PEDIDO;
            System.out.println(reloj
            );
        }


        //---------------------------------------------------------Seteamos Reloj y Evento actual
        col = 0;
        reloj = getProxEvento;
        agregarNumI(vector, col, nroFila);
//        vector[col] = nroFila;
        sumarColumna(1);
        agregarNumD(vector, col, util.redondear(reloj));
//        vector[col] = util.redondear(reloj);
        sumarColumna(1);
        agregarEvento(vector, col, eventoActual);
//        vector[col] = eventoActual;
        sumarColumna(1);


        //---------------------------------------------------------Decidimos cual es el evento actual
        switch (eventoActual){
            case REALIZANDO_PEDIDO: realizarNuevamente(vector);
                break;
            case LLEGADA_PEDIDO: pedido = llegadaPedido(vector);
                break;
            case FIN_ENTREGA: entregaPedido(pedido, vector);
                break;
            case REENVIAR_PEDIDO: reenviarPedido(pedido, vector);
                break;
        }

        //---------------------------------------------------------Asignamos este nuevo pedido si y solo si el evento es Llegada pedido
        if (eventoActual == TipoEvento.LLEGADA_PEDIDO){
            for (int i=0; i< vectorPedidos.length; i++){
                if (vectorPedidos[i].getEstado().equals("Nulo")){
                    vectorPedidos[i] = pedido;
                    break;
                }
            }
        }

        //---------------------------------------------------------Obtenemos resultados
        if (getMaxColaEspera < colaPedidoEnEspera){
            getMaxColaEspera = colaPedidoEnEspera;
        }


        agregarNumD(vector, col, colaGananciaPerdida);
//        vector[col] = colaGananciaPerdida;
        sumarColumna(1);
        agregarNumD(vector, col, colaPedidoEnEspera);
//        vector[col] = colaPedidoEnEspera;
        sumarColumna(1);
        agregarNumD(vector, col, colaPedidoEnEsperaReenvio);
//        vector[col] = colaPedidoEnEsperaReenvio;
        sumarColumna(1);
        agregarNumD(vector, col, colaPedidosEnviados);
//        vector[col]= colaPedidosEnviados;
        sumarColumna(1);
        agregarNumD(vector, col, colaPedidosEntregados);
//        vector[col]= colaPedidosEntregados;
        sumarColumna(1);
        agregarNumD(vector, col, colaPedidosDesechados);
//        vector[col] = colaPedidosDesechados;
        sumarColumna(1);

        for (int i = 0; i < repartidorList.size(); i++) {
            agregarEstadoRep(vector, col, repartidorList.get(i).getEstado());
//            vector[col] = repartidorList.get(i).getEstado();
            sumarColumna(1);
        }

        if (reloj>= desdeMinutos && reloj < hastaMinutos ){
            for (int i=0; i<vectorPedidos.length; i++){
                if (!vectorPedidos[i].getEstado().equals("Nulo")){
                    vector[col]=vectorPedidos[i].getId();
                    sumarColumna(1);
                    vector[col]=vectorPedidos[i].getEstado();
                    sumarColumna(1);
                    vector[col]=util.redondear(vectorPedidos[i].getHoraLlegada());
                    sumarColumna(1);
                    vector[col]=util.redondear(vectorPedidos[i].getProximaEntrega());
                    sumarColumna(1);
                    vector[col]=util.redondear(vectorPedidos[i].getProximoReenvio());
                    sumarColumna(1);
                }
            }
        }

    }
    //</editor-fold>

    //<editor-fold desc="AgregarAlVectorEventos">
    private void agregarAlVectorEventos(double hora){
        if (hora > reloj){
            orden.add(hora);
        }
    }
    //</editor-fold>

    //<editor-fold desc="RealizarNuevamente">
    //---------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------Estamos en un evento de REALIZAR PEDIDO NUEVAMENTE
    //---------------------------------------------------------------------------------------------------------------------------------------
    public void realizarNuevamente(Object[] vector){

        //---------------------------------------------------------Generamos evento de llegada
//        generarEventoLlegada(vector);
        sumarColumna(4);
        Pedido pedido = new Pedido();

        for(int i=0; i< vectorPedidos.length; i++){
            if (vectorPedidos[i].getProximaLLegadaNuevamente() == reloj){
                pedido = vectorPedidos[i];
                break;
            }
        }

        Repartidor rep = null;
        for (int i = 0; i < repartidorList.size(); i++) {
            Repartidor repartidor = repartidorList.get(i);
            if (repartidor.getEstado() == EstadoRep.LIBRE){
                rep = repartidor;
                generarDatosEntregaPedido(pedido, vector);
                break;
            }
        }

        //---------------------------------------------------------Como el estado actual es llegada entonces calculamos la entrega
        if(rep == null){
            //---------------------------------------------------------Estan todos los repartidores ocupados sumamos cola de espera y salteo entrega y destinatario
            sumarColumna(5);
            colaPedidoEnEspera++;
            pedido.setHoraInicioEspera(reloj); //Para verificar los 45 minutos esperando
            pedido.setEstado("EE");
        }
    }
    //</editor-fold>

    //<editor-fold desc="GenerarEventoLlegada">
    private void generarEventoLlegada(Object[] vector){
        if (n1 == -1 && n2 == -1){
            Funciones funcLlegada = new Funciones(mediaTiempoLlegada, desviacionTiempoLlegada, reloj);
            funcLlegada.generarEventoLlegada();
            n1 = funcLlegada.n1;
            n2 = funcLlegada.n2;
            rnd1LlegadaPedido = funcLlegada.rnd1;
            rnd2LlegadaPedido = funcLlegada.rnd2;
            tiempoLlegadaPedido = funcLlegada.tiempo;
            proximaLlegadaPedido = funcLlegada.prox;
            agregarAlVectorEventos(proximaLlegadaPedido); //Cargamos en el vector el nuevo evento
//            vector[col] = util.redondear(rnd1LlegadaPedido);
            agregarNumD(vector, col, util.redondear(rnd1LlegadaPedido));
            sumarColumna(1);
//            vector[col] = util.redondear(rnd2LlegadaPedido);
            agregarNumD(vector, col, util.redondear(rnd2LlegadaPedido));
            sumarColumna(1);
//            vector[col] = util.redondear(tiempoLlegadaPedido);
            agregarNumD(vector, col, util.redondear(tiempoLlegadaPedido));
            sumarColumna(1);
//            vector[col] = util.redondear(proximaLlegadaPedido);
            agregarNumD(vector, col, util.redondear(proximaLlegadaPedido));
            sumarColumna(1);
            n1 = -1;
        }else if(n1 == -1 && n2 != -1){
            tiempoLlegadaPedido = n2;
            proximaLlegadaPedido = n2 + reloj;
            agregarAlVectorEventos(proximaLlegadaPedido); //Cargamos en el vector el nuevo evento
            agregarString(vector, col, "");
//            vector[col] = "";
            sumarColumna(1);
            agregarString(vector, col, "");
//            vector[col] = "";
            sumarColumna(1);
            agregarNumD(vector, col, util.redondear(n2));
//            vector[col] = util.redondear(n2);
            sumarColumna(1);
            agregarNumD(vector, col, util.redondear(proximaLlegadaPedido));
//            vector[col] = util.redondear(proximaLlegadaPedido);
            sumarColumna(1);
            n2 = -1;
        }
    }
    //</editor-fold>

    //<editor-fold desc="LlegadaPedido">
    //---------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------Estamos en un evento de LLEGADA DE PEDIDO
    //---------------------------------------------------------------------------------------------------------------------------------------
    public Pedido llegadaPedido(Object[] vector){

        //---------------------------------------------------------Vamos a necesitar un contador de pedidos
        cantPedidos++;

        //---------------------------------------------------------Como no encontro pedido pendiente a realizar genero otra llegada normal
        //---------------------------------------------------------Generamos evento de llegada
        generarEventoLlegada(vector);

        //---------------------------------------------------------Vamos creando el pedido con los valores que voy teniendo
        Pedido pedido = new Pedido();
        pedido.setId(cantPedidos);
        pedido.setHoraLlegada(reloj);
        pedido.setTiempoEntreLlegada(tiempoLlegadaPedido);

        Repartidor rep = null;
        for (int i = 0; i < repartidorList.size(); i++) {
            Repartidor repartidor = repartidorList.get(i);
            if (repartidor.getEstado() == EstadoRep.LIBRE){
                rep = repartidor;
                generarDatosEntregaPedido(pedido, vector);
                break;
            }
        }
        //---------------------------------------------------------Como el estado actual es llegada entonces calculamos la entrega
        if(rep == null){
            //---------------------------------------------------------Estan todos los repartidores ocupados sumamos cola de espera y salteo entrega y destinatario
            sumarColumna(5);
            colaPedidoEnEspera++;
            pedido.setHoraInicioEspera(reloj); //Para verificar los 45 minutos esperando
            pedido.setEstado("EE");
        }
        return pedido;
    }
    //</editor-fold>

    //<editor-fold desc="GenerarDatosEntregaPedido">
    //---------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------Como hay repartidores libre entonces seteamos datos
    //---------------------------------------------------------------------------------------------------------------------------------------
    public void generarDatosEntregaPedido(Pedido pedido, Object[] vector){

        //---------------------------------------------------------Si el pedido estaba en espera actualizamos la cola
        if (pedido.getHoraInicioEspera() != 0){
            pedido.setHoraInicioEspera(0);
            colaPedidoEnEspera--;
        }

        //---------------------------------------------------------Calculamos los valores de la entrega del pedido
        Funciones funcEntrega = new Funciones(valorATiempoEntrega, valorBTiempoEntrega, reloj);
        funcEntrega.generarEventoEntregaPedido();
        rndEntregaPedido = funcEntrega.rnd1;
        tiempoEntregaPedido = funcEntrega.tiempo;
        proximaEntregaPedido = funcEntrega.prox;
        agregarAlVectorEventos(proximaEntregaPedido); //Cargamos en el vector el nuevo evento
        agregarNumD(vector, col, util.redondear(rndEntregaPedido));
//        vector[col] = util.redondear(rndEntregaPedido);
        sumarColumna(1);
        agregarNumD(vector, col, util.redondear(tiempoEntregaPedido));
//        vector[col] = util.redondear(tiempoEntregaPedido);
        sumarColumna(1);
        agregarNumD(vector, col, util.redondear(proximaEntregaPedido));
//        vector[col] = util.redondear(proximaEntregaPedido);
        sumarColumna(1);

        //---------------------------------------------------------Sabemos que no hay simulacion destinatario asique lo salteamos
        sumarColumna(2);

        //---------------------------------------------------------Vamos seteando el pedido con los valores que voy teniendo
        pedido.setProximaEntrega(proximaEntregaPedido);

        //---------------------------------------------------------Seteamos el estado del pedido y del simulacion.repartidor
        for (int i = 0; i < repartidorList.size(); i++) {
            Repartidor repartidor = repartidorList.get(i);
            if (repartidor.getEstado() == EstadoRep.LIBRE && repartidor.getId() == i+1){
                pedido.setEstado("SE_R"+repartidor.getId());
                repartidor.setEstado(EstadoRep.OCUPADO);
                pedido.setRepartidor(repartidor);
                break;
            }
        }

        //---------------------------------------------------------Se esta entregando un pedido seteamos cola de enviados y de ganancia
        colaPedidosEnviados++;
        colaGananciaPerdida-=3;
    }
    //</editor-fold>

    //<editor-fold desc="EntregaPedido">
    //---------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------Estamos en un evento de FIN DE ENTREGA
    //---------------------------------------------------------------------------------------------------------------------------------------
    public void entregaPedido(Pedido pedido, Object[] vector){
        //---------------------------------------------------------Salteamos la llegada del cliente y entrega del pedido
        sumarColumna(7);

        //---------------------------------------------------------Obtenemos el pedido del vector para trabajarlo
        //---------------------------------------------------------Calculamos valores del destinatario y seteamos en el vector
        Funciones funcDest = new Funciones();
        estadoDestinatario = getEstadoDestinatario(funcDest.generarEstadoDestinatario());
        rndDestinatario = funcDest.rnd1;
        agregarNumD(vector, col, util.redondear(rndDestinatario));
//        vector[col] = util.redondear(rndDestinatario);
        sumarColumna(1);
        agregarString(vector, col, estadoDestinatario);
//        vector[col] = estadoDestinatario;
        sumarColumna(1);

        //---------------------------------------------------------Calculamos si el destinatario esta o no esta
        if (estadoDestinatario.equals("Si esta")){
            setEstadoRepartidor(pedido);
            pedido.setEstado("Finalizado");
            colaPedidosEntregados++;
            colaGananciaPerdida+=15;

        }else if (estadoDestinatario.equals("No esta")){
            setEstadoRepartidor(pedido);
            //---------------------------------------------------------Analizamos si el pedido previamente fue enviado o no
            if (!pedido.getReenviado()){
                agregarAlVectorEventos(reloj+60);
                pedido.setEstado("EER");
                pedido.setReenviado(true);
                pedido.setProximaEntrega(0);
                pedido.setProximoReenvio(reloj+60);
                colaPedidoEnEsperaReenvio++;
            }else{
                pedido.setEstado("Desechado");
                colaPedidosDesechados++;
                colaGananciaPerdida-=2;
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="EstadoRepartidor">
    private void setEstadoRepartidor(Pedido pedido){
        for (int i = 0; i < repartidorList.size(); i++) {
            if (pedido.getRepartidor().getId() == i+1){
                pedido.getRepartidor().setEstado(EstadoRep.LIBRE);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="ReenviarPedido">
    public void reenviarPedido(Pedido pedido, Object[] vector){
    //---------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------Estamos en un evento de PROXIMO REENVIO
    //---------------------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------Verificar disponibilidad repartidores
        Repartidor rep = null;
        for (int i = 0; i < repartidorList.size(); i++) {
            Repartidor repartidor = repartidorList.get(i);
            if (repartidor.getEstado() == EstadoRep.LIBRE){
                rep = repartidor;
                // Como estamos en un evento de Reenviar pedido salteamos las columnas de lleagda del pedido
                sumarColumna(4);
                colaPedidoEnEsperaReenvio--;
                generarDatosEntregaPedido(pedido, vector);
                break;
            }
        }

        if(rep==null){
            sumarColumna(9); // Como estamos en un evento de Reenviar pedido salteamos las columnas de lleagda del pedido
            colaPedidoEnEspera++;
            colaPedidoEnEsperaReenvio--;
            pedido.setHoraInicioEspera(reloj); //Para verificar los 45 minutos esperando
            pedido.setEstado("EE");
        }
    }
    //</editor-fold>

    //<editor-fold desc="SumarColumna">
    private void sumarColumna(int num){
        col += num;
    }
    //</editor-fold>

    //<editor-fold desc="EstadoDestinatario">
    public String getEstadoDestinatario(boolean estado){
        if (estado){
            return "Si esta";
        }else{
            return "No esta";
        }
    }
    //</editor-fold>

    //<editor-fold desc="CrearTabla">
    public void crearTabla(){
        modelo = new DefaultTableModel() {@Override
        public boolean isCellEditable(int fila, int columna) {
            if(columna >= 0) {
                return false;
            } else {
                return super.isCellEditable(fila, columna);
            }
        }
        };
        modelo.addColumn("Nro");
        modelo.addColumn("Reloj");
        modelo.addColumn("Evento");

        //Llegada del pedido
        modelo.addColumn("#1 Llegada");
        modelo.addColumn("#2 Llegada");
        modelo.addColumn("Tiempo LLegada");
        modelo.addColumn("Proxima LLegada");

        //Entrega del pedido
        modelo.addColumn("# Entrega");
        modelo.addColumn("Tiempo Entrega");
        modelo.addColumn("Fin Entrega");

        //Destinatario
        modelo.addColumn("# estado");
        modelo.addColumn("Estado destinatario");

        //Colas
        modelo.addColumn("Cola Ganancias");
        modelo.addColumn("Pedidos en espera");
        modelo.addColumn("Pedidos espera reenvio");
        modelo.addColumn("Pedidos enviados");
        modelo.addColumn("Pedidos entregados");
        modelo.addColumn("Pedidos desechados");

        for (int i = 0; i < cantidadRepartidores; i++) {
            modelo.addColumn("Repartidor "+(i+1));
        }

        for(int i = 0; i < vectorPedidos.length; i ++) {
            modelo.addColumn("Pedido ID");
            modelo.addColumn("Estado");
            modelo.addColumn("Hora Llegada");
            modelo.addColumn("Hora Fin Entrega");
            modelo.addColumn("Proximo reenvio");
        }
        tamFila = modelo.getColumnCount();
    }
    //</editor-fold>
}
