
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dijkstra {

    public static HashMap<String, ArrayList<String>> adjecentAirportMap = new HashMap<String, ArrayList<String>>();
    public static HashMap<String, ArrayList<Integer>> adjecentAirportDistanceMap = new HashMap<String, ArrayList<Integer>>();
    public static HashMap<String, Integer> knownVertexMap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> dvMap = new HashMap<String, Integer>();
    public static HashMap<String, String> pvMap = new HashMap<String, String>();
    public static ArrayList<String> airportList = new ArrayList<String>();
    public static String source = "";
    public static String destination = "";
    public static ArrayList<String> unknownList = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner S = new Scanner(new File("airports.txt"));
        while (S.hasNextLine()) {
            String line = S.nextLine();
            StringTokenizer token = new StringTokenizer(line, " ");
            int count = 0;
            String airport = "";
            while (token.hasMoreTokens()) {
                //System.out.println(count);
                if (count == 0) {
                    airport = token.nextToken();
                    System.out.println(airport);
                    airportList.add(airport);
                    unknownList.add(airport);
                    adjecentAirportMap.put(airport, new ArrayList<String>());
                    adjecentAirportDistanceMap.put(airport, new ArrayList<Integer>());
                }
                if (count % 2 != 0) {
                    //System.out.println("here");
                    adjecentAirportMap.get(airport).add(token.nextToken());
                }
                if (count % 2 == 0 && count != 0) {
                    adjecentAirportDistanceMap.get(airport).add(Integer.parseInt(token.nextToken()));
                }
                count++;
            }
        }

        source = "DFW";
        destination = "SFO";

        //Initializing all the data structures
        for (int i = 0; i < airportList.size(); i++) {
            String airport = airportList.get(i);
            knownVertexMap.put(airport, 0);
            if (airport.equals(source)) {
                dvMap.put(airport, 0);
            } else {
                dvMap.put(airport, 10000);
            }

            pvMap.put(airport, "aaa");
        }

        //Make all the vertex known
       // int size = 0;
        String cur_airport = source;
        
        String nearest_airport = "";
        for (int i = 0; i < airportList.size(); i++) {
            int min_cost = 10000;
            knownVertexMap.put(cur_airport, 1);
            unknownList.remove(cur_airport);
            
            for (int j = 0; j < adjecentAirportMap.get(cur_airport).size(); j++) {

                String intermediate_airport = adjecentAirportMap.get(cur_airport).get(j);
                
                //check if vertex is known or not!
                if (knownVertexMap.get(intermediate_airport) != 1) {
                    int cost = adjecentAirportDistanceMap.get(cur_airport).get(j);
                    int totalCost = cost + dvMap.get(cur_airport);
                    
                    //check if this path is more cheaper than existing
                    if (totalCost < dvMap.get(intermediate_airport)) {
                        
                        dvMap.put(intermediate_airport, totalCost);
                        pvMap.put(intermediate_airport, cur_airport);
                    }

                }

            }
            
            for(int k=0;k<unknownList.size();k++)
            {
                if(dvMap.get(unknownList.get(k)) < min_cost)
                {
                    cur_airport = unknownList.get(k);
                    min_cost = dvMap.get(unknownList.get(k));
                }
            }
        }

        //--------------------------------
        System.out.println("-------------");
        Iterator i = knownVertexMap.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry pairs = (Map.Entry) i.next();
            String s = (String)pairs.getKey();
            System.out.println(s+" "+knownVertexMap.get(s)+" "+dvMap.get(s)+" "+pvMap.get(s));
        }
        System.out.println("-----------------");
        //------------------------------------
        //compute cheapest fair
        cur_airport = destination;
        int stop=0;
        int price = dvMap.get(destination);
       // System.out.println(price);
        while(!(cur_airport.equals(source)))
        {
            System.out.print(cur_airport+"-->");
            cur_airport = pvMap.get(cur_airport);
            stop++;
        }
        
        System.out.println(source);
        System.out.println("Price: "+price);
        System.out.println("Connections: "+(stop-1));



    }
}
