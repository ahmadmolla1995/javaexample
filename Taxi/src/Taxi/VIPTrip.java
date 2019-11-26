package Taxi;

public class VIPTrip extends Trip {
    static {
        baseCost = 10000;
        RainyCoefficient = 2;
        HighTrafficCoefficient = 2;
        RainyHighTrafficCoefficient = 3;
    }

    public VIPTrip(City source, City destination) {
        super(source, destination);
    }
}
