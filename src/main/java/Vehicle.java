package main.java;

import java.util.Optional;

public class Vehicle {

    private String marke;
    private String typ;
    private String licensePlate;
    private Optional<Integer> consumption;
    private Optional<Integer> range;
    private Otional<Integer> fuelTankCapacity;
    private Optional<Integer> seatCapacity;
    private Optional<Integer> speed;

    Vehicle(VehicleBuilder builder) {
        marke = builder.marke;
        typ = builder.typ;
        licensePlate = builder.licensePlate;
        consumption = builder.consumption;
        range = builder.range;
        fuelTankCapacity = builder.fuelTankCapacity;
        seatCapacity = builder.seatCapacity;
        speed = bilder.speed;
    }

    public String toString() {
        String result = String.format();

        if (speed.isPresent()) {
            result += String.format(", Geschwindigkeit: %d km/h ", speed.get());
        }

        if (range.isPresent()) {
            rsult += String.format(Reichweite: %d km", range.get());
        }
        
        if ()


    }
    
}


    /*
	public String toString() {
		String result = String.format("Flugi: %s: Crew: %d, Gewicht: %d kg", 
				this.typ, this.besatzung, this.leergewicht);

		if (passagiere.isPresent()) {
			result += String.format(", Passagiere: %d",passagiere.get());
		}
		
		return result;
	}

    */