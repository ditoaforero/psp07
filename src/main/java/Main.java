
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import spark.Request;
import spark.Response;

/**
* Clase que contiene las ejecuciones principales del programa
 * @author Eduardo Arevalo Forero
 */
public class Main {
    
    /**
     * Metodo: Logica inicial y principal del programa
     * @param args 
     */
    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");


        get("/taller07", (Request request, Response response) -> {
            ArrayList<Double> EstimatedProxySize = new ArrayList<Double>(Arrays.asList(130.0,650.0,99.0,150.0,128.0,302.0,95.0,945.0,368.0,961.0));
            ArrayList<Double> PlanAddedandModifiedSize = new ArrayList<Double>(Arrays.asList(163.0,765.0,141.0,166.0,137.0, 355.0, 136.0, 1206.0, 433.0,1130.0));
            ArrayList<Double> ActualAddedandModifiedSize = new ArrayList<Double>(Arrays.asList(186.0,699.0,132.0,272.0,291.0,331.0,199.0,1890.0,788.0,1601.0));
            ArrayList<Double> ActualDevelopmentHours = new ArrayList<Double>(Arrays.asList(15.0,69.9,6.5,22.4,28.4,65.9,19.4,198.7,38.8,138.2));

            ArrayList<Double> xArrayList = new ArrayList<Double>(EstimatedProxySize);
            ArrayList<Double> yArrayList = new ArrayList<Double>(ActualAddedandModifiedSize);

            String resultadoTest1= Estadistica.calculoPrograma7(EstimatedProxySize,ActualAddedandModifiedSize);
            String resultadoTest2= Estadistica.calculoPrograma7(EstimatedProxySize,ActualDevelopmentHours);
            //String resultadoTest3= Estadistica.calculoPrograma7(EstimatedProxySize,ActualAddedandModifiedSize);
            //String resultadoTest4= Estadistica.calculoPrograma7(EstimatedProxySize,ActualDevelopmentHours);


            Map<String, Object> atributes = new HashMap<>();
            atributes.put("test1", resultadoTest1);
            atributes.put("test2", resultadoTest2);
            //atributes.put("test3", resultadoTest3);
            //atributes.put("test4", resultadoTest4);
            return new ModelAndView(atributes, "taller07.ftl");

        }, new FreeMarkerEngine());

        get("/hello", (req, res) -> "Hello World");
    
    }
}