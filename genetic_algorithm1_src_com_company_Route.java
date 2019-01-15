package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route {

    private  boolean IsFitneesChanged = true;
    private double fitnees = 0;
    private ArrayList<City> cities = new ArrayList<City>();

    public Route(Genetic_algorithms genetic_algorithm){
        genetic_algorithm.getInitialRoute().forEach(x ->  cities.add(null));
    }

    public Route ( ArrayList<City> cities ){
        this.cities.addAll(cities);
       Collections.shuffle(this.cities);
    }

    public ArrayList<City> getCities (){
        IsFitneesChanged = true;
        return  cities ;
    }

    public double getFitnees(){
        if (IsFitneesChanged == true){
            fitnees = (1/calculatTotalDistans())* 10000;
            IsFitneesChanged = false ;
        }
        return fitnees;
    }

    public  double calculatTotalDistans(){
        int citiesSize = this.cities.size();
        return (int) (this.cities.stream().mapToDouble( x -> {
            int cityIndex = this.cities.indexOf(x);
            double returnValue =0;
            if ( cityIndex < citiesSize -1 ) returnValue = x.measureDistance( this.cities.get(cityIndex + 1));
            return  returnValue ;
        }).sum()+ this.cities.get(0).measureDistance(this.cities.get(citiesSize -1)));

    }

    public String toString(){ return Arrays.toString( cities.toArray()); }
}
