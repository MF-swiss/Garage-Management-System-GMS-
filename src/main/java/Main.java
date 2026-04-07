package main.java;


public class Main {

    public static void main(String[] args) {

        Vehicle cupra = new VehicleBuilder("Cupra", "Ateca", "SG 1")
                .setSpeed(120)
                .setRange(540)
                .build();
        System.out.println(cupra);



        Vehicle mini = new VehicleBuilder("MINI", "Cooper", "SG 4")
                .setSeatCapacity(5)
                .setSpeed(220)
                .setRange(580)
                .build();
        System.out.println(mini);



        Vehicle ford = new VehicleBuilder("Ford", "Tourneo Custom", "SG 3")
                .setSeatCapacity(8)
                .setRange(1000)
                .setFuelTankCapacity(75)
                .setConsumption(8)
                .build();
        System.out.println(ford);
    }
    
}