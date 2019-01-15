package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public ArrayList<City> initialRoute = new ArrayList<City>(Arrays.asList(

            new City ("Tripoli",13.2011,32.8913),
            new City ("Janzur",13.0089,32.8175),
            new City ("alZawia",12.7342,32.7620),
            new City("Sorman",12.5724,32.7479),
            new City("Zuwara",12.0828,32.9381),
            new City("Zintan",12.2953,31.7989),
            new City("Rayyna",12.3969,32.0655),
            new City("alasabah",12.8680,32.0380),
            new City("gerian",13.0205,32.1711),
            new City("Aziziyah",13.0222,32.5326),
            new City ("Zliten",14.5185,32.2754),
            new City ("Misurata",15.1005,32.3691),
            new City ("Bani Walid",14.0625,31.7917),
            new City ("Alalous",13.9715,32.7095),
            new City("Tajoura",13.3877,32.8279),
            new City("Al Jamial",12.0515,32.8552),
            new City("Nalut",10.9699,31.8711),
            new City("Kabao",11.3031,31.8129),
            new City("Tiji",11.3554,32.0069),
             new City("Jado",12.0249,31.9682)

            ));
    public static void main(String[] args) {


        Main main = new Main();
        Population population = new Population(Genetic_algorithms.Population_Size , main.initialRoute);
        population.Sort_Route_By_Fitnees();
        Genetic_algorithms genetic_algorithm = new Genetic_algorithms(main.initialRoute);
        int genarationNumber = 0;
        main.printHeadin(genarationNumber ++);
        main.printPopulation(population);
        while ( genarationNumber < Genetic_algorithms.NUMB_OF_GENERATIONS){
            main.printHeadin(genarationNumber ++);
            population = genetic_algorithm.evolve(population);
            population.Sort_Route_By_Fitnees();
            main.printPopulation(population);
        }
        System.out.println("Beast Route Found So far :"+ population.getRoutes().get(0));
        System.out.println(" distance of :" +String.format("%.2f",population.getRoutes().get(0).calculatTotalDistans())+" Km");
    }

    public void printPopulation ( Population population ){

        population.getRoutes().forEach(x -> {

            System.out.println( Arrays.toString(x.getCities().toArray()) + "  |  " +
                    String.format("%.4f", x.getFitnees())+"  |  "+String.format("%.2f",x.calculatTotalDistans()));
        });
        System.out.println(" ");
    }
    public void printHeadin ( int genarationNumber ) {

        System.out.println( " > Genaration # " + genarationNumber );
        String headinculumon = "Route";
        String remainHeadinCulmon = "fitnees   |  Distance (in Km)";
        int cityNumberLength = 0;
        for ( int x=0 ; x<initialRoute.size() ; x++) cityNumberLength += initialRoute.get(x).getName().length();
        int arrayLength = cityNumberLength + initialRoute.size() *2 ;
        int partlength = (arrayLength - headinculumon.length()) /2 ;
        for ( int x =0;  x <partlength ; x++ ) System.out.print(" ");
        System.out.print(headinculumon);
        for ( int x =0 ; x<partlength ; x++ )System.out.print(" ");
        if ((arrayLength %2) ==0 )System.out.print(" ");
        System.out.println(" | " + remainHeadinCulmon);
        cityNumberLength += remainHeadinCulmon.length() +3 ;
        for ( int x =0 ; x< cityNumberLength + initialRoute.size()*2 ; x++)System.out.print("-");
        System.out.println("");

    }
}
