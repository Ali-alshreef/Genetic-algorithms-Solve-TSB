package com.company;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Genetic_algorithms {

    public static final double MUTATION_RATE =0.25;
    public static final  int TOURNAMENT_SELECTION_SIZE = 5;
    public static final  int Population_Size = 10;
    public static final int NUMB_OF_ELITE_ROUTES =1;
    public static final int NUMB_OF_GENERATIONS = 2000;

    private ArrayList<City> initialRoute = null;
    public Genetic_algorithms ( ArrayList<City> initialRoute ){ this.initialRoute=initialRoute;}
    public ArrayList<City> getInitialRoute(){ return initialRoute;}

    public Population evolve ( Population population){ return mutationPopulation(crossoverPopulation(population));}

    Population crossoverPopulation ( Population population){
        Population crossoverPopulation = new Population(population.getRoutes().size(),this);
        IntStream.range(0,NUMB_OF_ELITE_ROUTES).forEach(x -> crossoverPopulation.getRoutes().set(x,population.getRoutes().get(x)));
        IntStream.range( NUMB_OF_ELITE_ROUTES ,crossoverPopulation.getRoutes().size()).forEach(x ->{
            Route route1 = selectTournamentPopulation(population).getRoutes().get(0);
            Route route2 = selectTournamentPopulation(population).getRoutes().get(0);
            crossoverPopulation.getRoutes().set(x,crossoverRoute(route1 , route2) );
        });
        return  crossoverPopulation;
    }

    Population mutationPopulation ( Population population){
        population.getRoutes().stream().filter(x -> population.getRoutes().indexOf(x) >=NUMB_OF_ELITE_ROUTES).forEach(x -> mutateRout(x));
        return  population ;
    }



    Route crossoverRoute ( Route route1 , Route route2){
        Route crossoverRoute = new Route(this);
        Route tempRoute1 = route1;
        Route tempRoute2 = route2 ;
        if (Math.random() <0.5){
            tempRoute1 = route2;
            tempRoute2 = route1;
        }
        for ( int x =0; x < crossoverRoute.getCities().size()/2 ; x++)
            crossoverRoute.getCities().set(x , tempRoute1.getCities().get(x));
        return  FillNillsInCrossoverRoute( crossoverRoute , tempRoute2);

    }

    private Route FillNillsInCrossoverRoute (Route crossoverRoute , Route route ){
        route.getCities().stream().filter(x ->  !crossoverRoute.getCities().contains(x)).forEach(cityx -> {
            for (int y =0; y <route.getCities().size(); y++ ){
                if ( crossoverRoute.getCities().get(y) == null){
                    crossoverRoute.getCities().set( y , cityx);
                    break;
                }
            }
        });

        return crossoverRoute;
    }

    //Example the mutation Cities in chromozom
    Route mutateRout( Route route) {
        route.getCities().stream().filter(x -> Math.random() < MUTATION_RATE).forEach( cityx-> {
            int y = (int) (route.getCities().size()*Math.random());
            City cityY = route.getCities().get(y);
            route.getCities().set(route.getCities().indexOf(cityx), cityY);
            route.getCities().set(y , cityx);
        });
        return route ;
    }

    Population selectTournamentPopulation ( Population population ){
        Population tournamentpopulation = new Population(TOURNAMENT_SELECTION_SIZE , this );
        IntStream.range(0,TOURNAMENT_SELECTION_SIZE).forEach(x ->tournamentpopulation .getRoutes().set(x,population.getRoutes().get((int)  (Math.random() * population.getRoutes().size()))));
        tournamentpopulation.Sort_Route_By_Fitnees();
        return tournamentpopulation;
    }

}
