package com.company;
public class City {
    private  static final double EARTH_EQUATORIAL_RADIUS =6378.137D;
    private  static final double CONVERT_DEGREE_TO_RADIANS = Math.PI/180D;

    private double longitude;
    private double latitude ;
    private  String  name ;

    public City ( String name  , double longitude , double latitude){
        this.name = name;
        this.longitude = longitude * CONVERT_DEGREE_TO_RADIANS ;
        this.latitude = latitude * CONVERT_DEGREE_TO_RADIANS  ;
    }

    public String getName (){ return  name ;}
    public double getLongitude (){ return this.longitude;}
    public double getLatitude () { return  this.latitude ;}
    public String toString (){ return getName(); }
    public double measureDistance ( City city ){
        double deltaLongtude = city.getLongitude() - this.getLongitude();
        double deltaLatitude  = city.getLatitude() - this.getLatitude();
                double a =Math.pow( Math.sin( deltaLatitude / 2D) , 2D) +
                Math.cos(this.getLatitude()) * Math.cos(city.getLatitude()) * Math.pow(Math.sin(deltaLongtude/2D) , 2D);
       return EARTH_EQUATORIAL_RADIUS *2D*Math.atan2(Math.sqrt(a), Math.sqrt(1D-a));
    }
}
