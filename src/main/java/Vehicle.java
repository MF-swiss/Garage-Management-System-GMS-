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
/*	public FlugzeugBuilder setGeschwindigkeit(int geschwindigkeit) {
		this.geschwindigkeit = Optional.of(geschwindigkeit);
		return this;
	}
	
	public FlugzeugBuilder setPassagiere(int passagiere) {
		this.passagiere = Optional.of(passagiere);
		return this;
	}
	
	public FlugzeugBuilder setReichweite(int reichweite) {
		this.reichweite = Optional.of(reichweite);
		return this;
	}
	
	public Flugzeug build() {
		return new Flugzeug(this);
	}*/
