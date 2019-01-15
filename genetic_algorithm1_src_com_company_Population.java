package com.company;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

    private ArrayList<Route> routes = new ArrayList<Route>(Genetic_algorithms.Population_Size);

    public  Population ( int populationSiz , Genetic_algorithms genetic_algorithm ){
        IntStream.range(0, populationSiz).forEach(x -> routes.add(new Route(genetic_algorithm.getInitialRoute())));
    }

    public  Population ( int populationSiz , ArrayList<City> cities ){
        IntStream.range(0 , populationSiz).forEach(x -> routes.add(new Route(cities)));
    }
    public ArrayList<Route> getRoutes () { return routes ;}

    public void Sort_Route_By_Fitnees (){
        routes.sort((route1 , route2 ) ->{
            int flag =0;
            if ( route1.getFitnees() > route2.getFitnees()) flag =-1;
            else if ( route1.getFitnees() < route2.getFitnees()) flag =1 ;
            return flag ;
        });
    }


}