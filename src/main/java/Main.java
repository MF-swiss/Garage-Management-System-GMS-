package main.java;


public class Main {

    public static void main(String[] args) {

        java.util.List<Vehicle> vehicles = new java.util.ArrayList<>();
        vehicles.add(new VehicleBuilder("Cupra", "Ateca", "SG 1")
                .setSpeed(120)
                .setRange(540)
                .build());
        vehicles.add(new VehicleBuilder("MINI", "Cooper", "SG 4")
                .setSeatCapacity(5)
                .setSpeed(220)
                .setRange(580)
                .build());
        vehicles.add(new VehicleBuilder("Ford", "Tourneo Custom", "SG 3")
                .setSeatCapacity(8)
                .setRange(1000)
                .setFuelTankCapacity(75)
                .setConsumption(8)
                .build());

        javax.swing.SwingUtilities.invokeLater(() -> new GUI(vehicles));
    }
    
}