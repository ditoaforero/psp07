
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

            ArrayList<Double> EstimatedProxySizePersonal = new ArrayList<Double>(Arrays.asList(125.0,51.3,108.0,61.1));
            ArrayList<Double> ActualAddedandModifiedSizePersonal = new ArrayList<Double>(Arrays.asList(0.0, 324.0, 566.0, 53.0));
            ArrayList<Double> ActualDevelopmentHoursPersonal = new ArrayList<Double>(Arrays.asList(5.77,3.77,8.03,3.82));

            String resultadoTest1= Estadistica.calculoPrograma7(EstimatedProxySize,ActualAddedandModifiedSize, 386);
            String resultadoTest2= Estadistica.calculoPrograma7(EstimatedProxySize,ActualDevelopmentHours, 386);
            String resultadoTest3= Estadistica.calculoPrograma7(EstimatedProxySizePersonal,ActualAddedandModifiedSizePersonal,139);
            String resultadoTest4= Estadistica.calculoPrograma7(EstimatedProxySizePersonal,ActualDevelopmentHoursPersonal,139);


            Map<String, Object> atributes = new HashMap<>();
            atributes.put("test1", resultadoTest1);
            atributes.put("test2", resultadoTest2);
            atributes.put("test3", resultadoTest3);
            atributes.put("test4", resultadoTest4);
            return new ModelAndView(atributes, "taller07.ftl");

        }, new FreeMarkerEngine());

        get("/hello", (req, res) -> "Hello World");
    
    }
}
