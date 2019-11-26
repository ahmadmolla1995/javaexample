package Taxi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("first city ID:");
            int firstCityID = scanner.nextInt();
            System.out.print("second city ID:");
            int secondCityID = scanner.nextInt();

            if (firstCityID < 0 || firstCityID > 4 || secondCityID < 0 || secondCityID > 4) {
                System.out.println("Out of range city index! Enter again.");
                continue;
            }

            System.out.print("Trip type:");
            String tripType = scanner.next();
            System.out.print("Weather situation:");
            String weatherSituation = scanner.next();
            System.out.print("Traffic volume:");
            String trafficVolume = scanner.next();

            Trip trip;
            if (tripType.equalsIgnoreCase("Economical"))
                trip = new EconomicalTrip(new City(firstCityID), new City(secondCityID));
            else if (tripType.equalsIgnoreCase("Motorcycle"))
                trip = new MotorcycleTrip(new City(firstCityID), new City(secondCityID));
            else if (tripType.equalsIgnoreCase("VIP"))
                trip = new VIPTrip(new City(firstCityID), new City(secondCityID));
            else {
                System.out.println("The trip type is not valid! Enter again.");
                continue;
            }

            System.out.printf("Trip cost: %f\n================\n\n", trip.getTripCost(weatherSituation, trafficVolume));
        }
    }
}
