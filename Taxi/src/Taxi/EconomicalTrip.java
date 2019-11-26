package Taxi;

public class EconomicalTrip extends Trip{
    static {
        baseCost = 5000;
        RainyCoefficient = 1.2;
        HighTrafficCoefficient = 1.2;
        RainyHighTrafficCoefficient = 1.4;
    }

    public EconomicalTrip(City source, City destination) {
        super(source, destination);
    }
}
