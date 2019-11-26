package Taxi;

public class MotorcycleTrip extends Trip{
    static{
        baseCost = 4000;
        RainyCoefficient = 0.8;
        HighTrafficCoefficient = 2;
        RainyHighTrafficCoefficient = 1.5;
    }

    public MotorcycleTrip(City source, City destination){
        super(source, destination);
    }
}
