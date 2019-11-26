package Taxi;

public class Trip {
    private City source;
    private City destination;
    protected static double baseCost;
    protected static double RainyCoefficient;
    protected static double HighTrafficCoefficient;
    protected static double RainyHighTrafficCoefficient;

    public Trip(City source, City destination) {
        this.source = source;
        this.destination = destination;
    }

    public double getTripCost(String weatherSituation, String trafficVolume){
        double distCoefficient = DistanceCoefficientMatrix.getCoefficient(source.getCityID(), destination.getCityID());

        if (weatherSituation.equalsIgnoreCase("Rainy") && trafficVolume.equalsIgnoreCase("Low"))
            return RainyCoefficient * baseCost * distCoefficient;
        else if (weatherSituation.equalsIgnoreCase("NotRainy") && trafficVolume.equalsIgnoreCase("High"))
            return HighTrafficCoefficient * distCoefficient * baseCost;
        else if (weatherSituation.equalsIgnoreCase("Rainy") && trafficVolume.equalsIgnoreCase("High"))
            return RainyHighTrafficCoefficient * distCoefficient * baseCost;
        else
            return 0;
    }
}
