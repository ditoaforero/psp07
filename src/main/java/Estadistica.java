/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.special.Gamma;

/**
 * Esta clase se utiliza para realizar las operaciones matematicas y
 * estadisticas
 *
 * @author Eduardo
 */
public class Estadistica {

    public final static String MUY_PEQUENIO = "Very Small";
    public final static String PEQUENIO = "Small";
    public final static String MEDIANO = "Medium";
    public final static String GRANDE = "Larger";
    public final static String MUY_GRANDE = "Very Larger";

    /**
     * Metodo: Calcula la media de un listado de registros pasados en un List
     *
     * @param listadoValores
     * @return Valor de la media del listado de valores recibido por parametro
     */
    public static double calcularMedia(List<Double> listadoValores) {
        double sumatoria = 0;
        sumatoria = sumatoria(listadoValores);
        return (double) sumatoria / listadoValores.size();
    }

    /**
     * Metodo: Calcula la desviacion standart a partir de los valres recibidos
     * en un List
     *
     * @param listadoValores
     * @return Desviacion estandart del listado de valores recibido por
     * parametro
     */
    public static double calcularDesviacionEstandar(List listadoValores) {
        ArrayList ll = (ArrayList) listadoValores;
        double varianza = Estadistica.calcularVarianza(ll);
        return (double) Math.pow(varianza, 0.5);
    }

    /**
     * Metodo: Calcular varianza, calcula la varianza a partir de los valores
     * recibidos en un List
     *
     * @param listadoValores
     * @return La varianza del listado de valores recibido por parametro
     */
    public static double calcularVarianza(List listadoValores) {
        ArrayList ll = (ArrayList) listadoValores;
        int cantidadNumeros = 0;
        double media = Estadistica.calcularMedia(ll);
        double base = 0;
        double sumatoria = 0;
        for (Object valor : ll) {
            if (esNumerico(valor)) {
                cantidadNumeros++;
                base = (double) Double.valueOf((String) valor) - media;
                sumatoria = (double) sumatoria + Math.pow(base, 2);
            }
        }
        return (double) sumatoria / (cantidadNumeros - 1);
    }

    /**
     * Metodo: Calcula la sumatoria de una lista de double
     *
     * @param listadoValores
     * @return Sumatoria del listado de valores recibido por parametro
     */
    public static double sumatoria(List<Double> listadoValores) {
        double sumatoria = 0;
        sumatoria = listadoValores.stream().map((valor) -> valor).reduce(sumatoria, (accumulator, _item) -> accumulator + _item);
        return sumatoria;
    }

    /**
     * Metodo: Calcula la sumatoria de los numeros elevados al cuadrado de un
     * List
     *
     * @param listadoValores
     * @return El valor de sumatoria de los cuadrados de los valores recibidos
     * por paramtros
     */
    public static double sumatoriaDelCuadrado(List<Double> listadoValores) {
        double sumatoria = 0;
        sumatoria = listadoValores.stream().map((valor) -> Math.pow(valor, 2)).reduce(sumatoria, (accumulator, _item) -> accumulator + _item);
        return sumatoria;
    }

    /**
     * Metodo: Calcula la sumatoria de los valores multiplicados de los listas
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @return La sumatoria de la multiplicacion de cada uno de los valores
     * respectivamente por pocision entre las listas recibidas por parametro
     */
    public static double sumatoriaListaXListaDouble(List<Double> listadoValoresIzq, List<Double> listadoValoresDer) {
        double sumatoria = 0;
        for (int i = 0; i < listadoValoresIzq.size(); i++) {
            sumatoria += listadoValoresIzq.get(i) * listadoValoresDer.get(i);
        }
        return sumatoria;
    }

    /**
     * Metodo: Calcula B1
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @return El valor B1 de la tarea04
     */
    public static double calcularB1(List<Double> listadoValoresIzq, List<Double> listadoValoresDer) {
        double b1 = 0;
        double sumatoriaXY = 0;
        double mediaX = 0;
        double mediaY = 0;
        double sumatoriax2;
        double dividendo = 0;
        double divisor = 0;
        int numeroRegistros = 0;

        sumatoriaXY = sumatoriaListaXListaDouble(listadoValoresIzq, listadoValoresDer);
        numeroRegistros = listadoValoresIzq.size();
        mediaX = calcularMedia(listadoValoresIzq);
        mediaY = calcularMedia(listadoValoresDer);
        sumatoriax2 = sumatoriaDelCuadrado(listadoValoresIzq);

        dividendo = sumatoriaXY - (numeroRegistros * mediaX * mediaY);
        divisor = sumatoriax2 - (numeroRegistros * Math.pow(mediaX, 2));

        return dividendo / divisor;
    }

    /**
     * Metodo: Calcula B0
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @return Valor calculado de B0, para la tarea 04
     */
    public static double calcularB0(List<Double> listadoValoresIzq, List<Double> listadoValoresDer) {
        double mediaX = 0;
        double mediaY = 0;
        double b1 = 0;
        mediaX = calcularMedia(listadoValoresIzq);
        mediaY = calcularMedia(listadoValoresDer);
        b1 = calcularB1(listadoValoresIzq, listadoValoresDer);

        return mediaY - (b1 * mediaX);
    }

    /**
     * Metodo: Calcula R
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @return El valor R para la tarea 04
     */
    public static double calcularR(List<Double> listadoValoresIzq, List<Double> listadoValoresDer) {
        double numeroRegistros = 0;
        double sumatoriaXY = 0;
        double sumatoriaX = 0;
        double sumatoriaY = 0;
        double sumatoriax2 = 0;
        double sumatoriay2 = 0;
        double dividendo = 0;
        double divisorP1 = 0;
        double divisorP2 = 0;
        double divisor = 0;

        numeroRegistros = listadoValoresIzq.size();
        sumatoriaXY = sumatoriaListaXListaDouble(listadoValoresIzq, listadoValoresDer);
        sumatoriaX = sumatoria(listadoValoresIzq);
        sumatoriaY = sumatoria(listadoValoresDer);
        sumatoriax2 = sumatoriaDelCuadrado(listadoValoresIzq);
        sumatoriay2 = sumatoriaDelCuadrado(listadoValoresDer);

        dividendo = (numeroRegistros * sumatoriaXY) - (sumatoriaX * sumatoriaY);
        divisorP1 = (numeroRegistros * sumatoriax2) - (Math.pow(sumatoriaX, 2));
        divisorP2 = (numeroRegistros * sumatoriay2) - (Math.pow(sumatoriaY, 2));
        divisor = divisorP1 * divisorP2;
        divisor = Math.sqrt(divisor);

        return dividendo / divisor;
    }

    /**
     * Metodo: Calcula R al cuadrado
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @return Valor de R al cuadrado de la Tarea 04
     */
    public static double calcularR2(List<Double> listadoValoresIzq, List<Double> listadoValoresDer) {
        double numeroRegistros = 0;
        double sumatoriaXY = 0;
        double sumatoriaX = 0;
        double sumatoriaY = 0;
        double sumatoriax2 = 0;
        double sumatoriay2 = 0;
        double dividendo = 0;
        double divisorP1 = 0;
        double divisorP2 = 0;
        double divisor = 0;

        numeroRegistros = listadoValoresIzq.size();
        sumatoriaXY = sumatoriaListaXListaDouble(listadoValoresIzq, listadoValoresDer);
        sumatoriaX = sumatoria(listadoValoresIzq);
        sumatoriaY = sumatoria(listadoValoresDer);
        sumatoriax2 = sumatoriaDelCuadrado(listadoValoresIzq);
        sumatoriay2 = sumatoriaDelCuadrado(listadoValoresDer);

        dividendo = (numeroRegistros * sumatoriaXY) - (sumatoriaX * sumatoriaY);
        divisorP1 = (numeroRegistros * sumatoriax2) - (Math.pow(sumatoriaX, 2));
        divisorP2 = (numeroRegistros * sumatoriay2) - (Math.pow(sumatoriaY, 2));
        divisor = divisorP1 * divisorP2;
        divisor = Math.sqrt(divisor);

        return Math.pow(dividendo / divisor, 2);
    }

    /**
     * Metodo: Calcula P de la tarea 04
     *
     * @param listadoValoresIzq
     * @param listadoValoresDer
     * @param esperanza
     * @return El valor de P
     */
    public static double calcularP(List<Double> listadoValoresIzq, List<Double> listadoValoresDer, double esperanza) {
        double b0;
        double b1;

        b0 = calcularB0(listadoValoresIzq, listadoValoresDer);
        b1 = calcularB1(listadoValoresIzq, listadoValoresDer);

        return b0 + b1 * esperanza;
    }

    /**
     * Metodo: verifica si el valor recibido es numerico
     *
     * @param valor Valor a verificar
     * @return true o false dependiendo si el valor recibido por parametro es
     * numerico
     */
    private static boolean esNumerico(Object valor) {
        try {
            Double.valueOf((String) valor);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Metodo: Calculate the add of natural logarithm
     *
     * @param listadoValores
     * @return
     */
    public static double calcularSumatoriaLogaritoNatural(List<Double> listadoValores) {
        double sumatoria = 0;
        for (int i = 0; i < listadoValores.size(); i++) {
            sumatoria += Math.log(listadoValores.get(i));
        }
        return sumatoria;
    }

    /**
     * Metodo: Calcula la varianza con el logaritmo natural
     *
     * @param listadoValores
     * @return El valor e la varianza obtenida con logaritmo natural
     */
    public static double calcularVarianzaLogaritmoNatural(List<Double> listadoValores) {
        double sumatoria = 0;
        double media = 0;
        media = calcularMediaLogaritmoNatural(listadoValores);
        for (int i = 0; i < listadoValores.size(); i++) {
            sumatoria += Math.pow(Math.log(listadoValores.get(i)) - media, 2);
        }
        return sumatoria / (listadoValores.size() - 1);
    }

    /**
     * Metodo: Calcula la media con el logaritmo natural de un listado de
     * valores
     *
     * @param listadoValores
     * @return El valor de la media a partir del logaritmo natural
     */
    public static double calcularMediaLogaritmoNatural(List<Double> listadoValores) {
        if (listadoValores == null) {
            return 0;
        }
        double sumatoria = 0;
        for (int i = 0; i < listadoValores.size(); i++) {
            sumatoria += Math.log(listadoValores.get(i));
        }
        return sumatoria / listadoValores.size();
    }

    /**
     * Metodo: Calcular la desviacion estandar con logarito natural
     *
     * @param listadoValores el listado de valores sobre el que se va a calcular
     * la desviacion estandart
     * @return Desviacion estandar del listado de valores que se recibe
     */
    public static double calcularDesviacionStandartLogaritmoNatural(List<Double> listadoValores) {
        return Math.sqrt(calcularVarianzaLogaritmoNatural(listadoValores));
    }

    /**
     * Metodo: Retorna un Mapa con los valores de rangos algorimicos con los
     * valores todavia transformados
     *
     * @param media valor de la media
     * @param desviacion valor de la desviacion estandart
     * @return Map con los valores a presentar en la tarea 04
     */
    public static Map calcularRangosAlgoritimicos(double media, double desviacion) {
        Map<String, Double> rangosAlgoritimicos = new HashMap<>();
        rangosAlgoritimicos.put(MUY_PEQUENIO, media - (2 * desviacion));
        rangosAlgoritimicos.put(PEQUENIO, media - desviacion);
        rangosAlgoritimicos.put(MEDIANO, media);
        rangosAlgoritimicos.put(GRANDE, media + desviacion);
        rangosAlgoritimicos.put(MUY_GRANDE, media + (2 * desviacion));
        return rangosAlgoritimicos;
    }

    /**
     * Metodo: Retorna un Mapa con los valores de rangos algorimicos con los
     * valores a presentar
     *
     * @param media valor de la media
     * @param desviacion valor de la desviacion estandart
     * @return Map ocn los valores para presentar de la tarea 04
     */
    public static Map calcularRangosAlgoritimicosBackType(double media, double desviacion) {
        Map<String, Double> rangosAlgoritimicos = new HashMap<>();
        rangosAlgoritimicos.put(MUY_PEQUENIO, Math.exp(media - (2 * desviacion)));
        rangosAlgoritimicos.put(PEQUENIO, Math.exp(media - desviacion));
        rangosAlgoritimicos.put(MEDIANO, Math.exp(media));
        rangosAlgoritimicos.put(GRANDE, Math.exp(media + desviacion));
        rangosAlgoritimicos.put(MUY_GRANDE, Math.exp(media + (2 * desviacion)));
        return rangosAlgoritimicos;
    }

    /**
     * Metodo: Calcular los valores correspodientes a Fx sobre cada iteracion
     * dado un numero de segmentos especificos
     *
     * @param x Corresponde al valor limite para el calculo de la integral
     * @param dof Grados de libertad
     * @param num_Segmentos Numero de segmentos en los que se repartira la
     * integral
     * @return Retorna el valor de la distribucion T generada a partir de los
     * segmentos recibidos
     */
    public static double calcularListadoFxConSegmentos(double x, int dof, int num_Segmentos) {
        List listFx = new ArrayList();
        List listDistribucionT = new ArrayList();
        double w = x / num_Segmentos;
        double primeraParte = 0;
        double segundaParte = 0;
        double valor = 0;
        double xi = 0;

        for (int i = 0; i <= num_Segmentos; i++) {
            xi = i * w;
            primeraParte = Estadistica.calcularFxPrimeraParte(dof);
            segundaParte = Estadistica.calcularFxSegundaParte(dof, xi);
            listFx.add(primeraParte * segundaParte);
        }
        listDistribucionT = Estadistica.calcularListadoDistribucionT(listFx, x, num_Segmentos);
        valor = Estadistica.sumatoria(listDistribucionT);
        return valor;
    }

    /**
     * Metodo: Calcula la distribucion T
     *
     * @param x el valor de X con el que se va a calcular
     * @param dof los grados de libertad que se van a utilizar
     * @return Retorna el valor de la distribucion T
     */
    public static double calcularDistribucionT(double x, int dof) {
        int numeroSegmentos = 10;
        double valorDiferencia = 1;
        double valorComparacion01 = 0;
        double valorComparacion02 = 0;
        valorComparacion01 = Estadistica.calcularListadoFxConSegmentos(x, dof, numeroSegmentos);
        while (valorDiferencia > 0.00001) {
            numeroSegmentos += 5;
            valorComparacion02 = Estadistica.calcularListadoFxConSegmentos(x, dof, numeroSegmentos);
            valorDiferencia = Math.abs(valorComparacion01 - valorComparacion02);
        }
        return valorComparacion02;
    }

    /**
     * Metodo: To list the values of the T distribution
     *
     * @param valoresFx value of fx
     * @param x value of x
     * @param num_Segmentos number of segments that it has been distributed
     * @return A list with the values of T Distributions for each iteration
     */
    public static List<Double> calcularListadoDistribucionT(List<Double> valoresFx, double x, int num_Segmentos) {
        List listDistribucionT = new ArrayList();
        for (int i = 0; i < valoresFx.size(); i++) {
            listDistribucionT.add(Estadistica.calcularValorDistribucionTParaIteracion(i, valoresFx.get(i), num_Segmentos, x));
        }
        return listDistribucionT;
    }

    /**
     * Metodo: Calcula el valor distribucion para una iteracion unicamente
     *
     * @param iteracion number of the iteration that we test
     * @param valorFx value of function
     * @param num_Segmentos number of segments that it has been distributed
     * @param x vale of x
     * @return the value calculated for the T distribution in an interation
     */
    public static double calcularValorDistribucionTParaIteracion(int iteracion, double valorFx, int num_Segmentos, double x) {
        int multiplicador = 0;
        double w = x / num_Segmentos;
        if (iteracion == 0 || iteracion == num_Segmentos) {
            multiplicador = 1;
        } else if (iteracion % 2 == 0) {
            multiplicador = 2;
        } else {
            multiplicador = 4;
        }
        return (w / 3) * multiplicador * valorFx;
    }

    /**
     * Metodo: Calcula el valor del primer factor de Fx, corresponde a la
     * tarea05
     *
     * @param dof grados de libertad
     * @return El valor calculado del primer factor de Fx para ser utiliza en el
     * calculo total
     */
    public static double calcularFxPrimeraParte(int dof) {
        float valorCalcularGamma = 0;
        double numerador = 0;
        double denominador = 0;
        valorCalcularGamma = ((float) (dof + 1) / 2);
        numerador = Gamma.gamma(valorCalcularGamma);
        valorCalcularGamma = ((float) dof / 2);
        denominador = Math.pow(dof * Math.PI, 0.5) * Gamma.gamma(valorCalcularGamma);
        return numerador / denominador;
    }

    /**
     * Metodo: Calcula el valor del segundo factor de Fx, corresponde a la
     * tarea05
     *
     * @param valorIteracion valor de la iteraci?n i
     * @param dof grados de libertad
     * @return El valor calculado del segundo factor de Fx para ser utilizado en
     * el calculo total
     */
    public static double calcularFxSegundaParte(int dof, double valorIteracion) {
        double base = 1 + (Math.pow(valorIteracion, 2) / dof);
        double potencia = (-((double) (dof + 1) / 2));
        return Math.pow(base, potencia);
    }

    /**
     * Metodo: CalcularXDistribucionT, se encarga de calcular el valor X a
     * partir de los valores de distribucion T obtenidos al ir recorriendo X, X
     * se inicializa con el valor 1.0
     *
     * @param p El valor de la distribucion T del valor X que se quiere
     * averiguar
     * @param dof Grados de libertad para el calculo
     * @return Se retorna el valor calculado de X para un valor P y unos grados de libertad
     *  recibidos por parametros.
     */
    public static double calcularXDistribucionT(double p, int dof) {
        double valor = 0;
        double xEvaluado = 1;
        double diferencia = 0;
        double rangoError = 0.00001;
        double d = 0.5;
        boolean banderaSuma = false;
        boolean banderaResta = false;
        while (true) {
            valor = Estadistica.calcularDistribucionT(xEvaluado, dof);
            diferencia = valor - p;
            System.out.println("Valor:"+valor+" xEvaluado:"+xEvaluado+" diferencia:"+diferencia+" d:"+d);
            if (Math.abs(diferencia)<rangoError){
                break;
            } else if(diferencia < 0){
                if(banderaSuma && banderaResta){
                    d = d/2;
                    banderaSuma = false;
                    banderaResta = false;
                }
                xEvaluado += d;
                banderaSuma = true;
            } else if(diferencia > 0){
                if(banderaSuma && banderaResta){
                    d = d/2;
                    banderaSuma = false;
                    banderaResta = false;
                }
                xEvaluado -= d;
                banderaResta = true;
            }
        }

        return xEvaluado;
    }


   /**
    * Metodo: Multiplica los valores de las listas
    * @param xArrayList Listado de valores 01
    * @param yArrayList Listado de valores 02
    * @return Valor multilplicado 1 a 1 de las listas
    */
    public static double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
        double cal = 0;
        for (int i = 0; i < xArrayList.size(); i++){
            cal += xArrayList.get(i)*yArrayList.get(i);
        }
        return cal;
    }
    
    
    /**
     * Calcula el valor de funcion
     * @param x valor x
     * @param dof grados de libertad 
     * @return valor de f
     */
    public static double f(double x,double dof){
        double cal = 0;
        cal = Math.pow((1 + x * x / dof), -(dof + 1) / 2) * Gamma.gamma((dof + 1) / 2);
        cal = cal / (Math.sqrt(dof * Math.PI) * Gamma.gamma(dof / 2));
        return cal;
    }
    
    /**
     * Metodo: Calcula el tail area para el ejercicio del taller 07
     * @param r recibe r
     * @param arrayList un listado de valores
     * @return valor del tail area
     */
    public static double getTailArea(double r, List<Double> arrayList){
        double x = Math.abs(r) * Math.sqrt(arrayList.size() - 2) / Math.sqrt(1 - r * r);
        return 1 - 2 * cal(x, arrayList.size() - 2);
    }
    
    /**
     * Metodo 
     * @param x
     * @param dof
     * @return 
     */
    public static double cal(double x,double dof){
        int num_seg = 10;
        final double E = 0.00001;
        double answer_old = 0, answer_new = 0;
        do{
            double w = x / num_seg;
            answer_old = answer_new;
            answer_new = 0;
            for (int i = 1; i < num_seg; i++){
                if (i % 2 == 0){
                    answer_new += 2 * f(i*w,dof);
                }else{
                    answer_new += 4 * f(i*w,dof);
                }
            }
            answer_new += f(0,dof) + f(x,dof);
            answer_new *= w / 3;
            num_seg *= 2;
        }while(Math.abs(answer_old - answer_new) > E);
        return answer_new;
    }
    /**
     * Metodo obtiene el valor de X a partir del p y dof en una sola funcion 
     * @param p valor p
     * @param dof grados de libertad
     * @return valor de X
     */
    public static double getX(double p,double dof){
        double x = 1.0;
        double d = 0.5;
        boolean isBig = false;
        int num_seg = 1000;
        final double E = 0.00000001;
        double answer = 0;
        while(Math.abs(answer - p) > E){
            double w = x / num_seg;
            answer = 0;
            for (int i = 1; i < num_seg; i++){
                if (i % 2 == 0){
                    answer += 2 * f(i*w,dof);
                }else{
                    answer += 4 * f(i*w,dof);
                }
            }
            answer += f(0,dof) + f(x,dof);
            answer *= w / 3;
            if (Math.abs(answer - p) > E){
                if (isBig){
                    if (answer <= p) {
                        d /= 2;
                        isBig = !isBig;
                    }
                    x -= d;
                }else{
                    if (answer >= p) {
                        d /= 2;
                        isBig = !isBig;
                    }
                    x += d;
                }
            }
        }
        return x;
    }
    
    /**
     * Metodo que calcula el sigma
     * @param xArrayList Listado de valores 01
     * @param yArrayList Listado de valores 02
     * @param b0 valor beta 00
     * @param b1 valor beta 01
     * @return Retorna el Sigma calculado
     */
    public static double getSigma(List<Double> xArrayList, List<Double> yArrayList,double b0, double b1){
        ArrayList<Double> cal = new ArrayList<Double>();
        for (int i = 0; i < xArrayList.size(); i++){
            cal.add(yArrayList.get(i) - b0 - b1 * xArrayList.get(i));
        }
        return Math.sqrt(multiple(cal,cal) / (xArrayList.size() - 2));
    }
    
    
    /**
     * Metodo obtiene el rango
     * @param xArrayList Listado de valores 01
     * @param yArrayList Listado de valores 02
     * @param b0 Valor beta 00
     * @param b1 Valor beta 01
     * @param xk Valor xk con el que se realiza la operacion
     * @return El valor del rango
     */
    public static double obtenerRango(List<Double> xArrayList, List<Double> yArrayList,double b0, double b1,double xk){
        double range = getX(0.35, xArrayList.size() - 2) * getSigma(xArrayList, yArrayList, b0, b1);
        ArrayList<Double> cal = new ArrayList<Double>();
        for (int i = 0; i < xArrayList.size(); i++){
            cal.add(xArrayList.get(i) - calcularMedia(xArrayList));
        }
        return range * Math.sqrt( 1 + (1.0 / xArrayList.size()) + ((xk - calcularMedia(xArrayList)) * (xk - calcularMedia(xArrayList)) / multiple(cal,cal)));
    }
    
    /**
     * Metodo: Solicitado por el taller 07 de PSP
     * @param xArrayList Listado de valores 01
     * @param yArrayList Listado de valores 02
     * @return Un cadena de texto con todos los valores respectivos
     */
    public static String calculoPrograma7(List<Double> xArrayList, List<Double> yArrayList, double xk){
        
        double beta01=0;
        double beta00=0;
        double r=0;
        double rango=0;
        
        beta01 = Estadistica.calcularB1(xArrayList, yArrayList);
        beta00 = Estadistica.calcularB0(xArrayList, yArrayList);
        
        calcularR(xArrayList, yArrayList);
        r = Estadistica.calcularR(xArrayList,yArrayList);

        double y = beta00 + beta01 * xk;
        rango = Estadistica.obtenerRango(xArrayList, yArrayList, beta00, beta01, xk);
        double tailArea = Estadistica.getTailArea(r, xArrayList);


        String resultado = "r: "+r + ",  r^2: "+ r*r + ",  tailArea: "+ tailArea + ",  beta0: "+beta00 +",  beta1: "+beta01+",  yk: "+y+",  Range: "+rango+",  UPI:"+(y+rango)+",  LPI:"+(y-rango);
        return  resultado;
    }

}
        