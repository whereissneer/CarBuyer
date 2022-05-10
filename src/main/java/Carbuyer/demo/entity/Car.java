package Carbuyer.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String carTitle;
	private double price;
	private int yearModel;
	private int mileage;
	private String engineType;
	private float engineCapacity;
	private int enginePower;
	private String gearboxType;
	private String driveType;
	private String colour;
	private boolean isDamaged;
	
	public Car() {
		super();
	}
	public Car(String carTitle, double price, int yearModel, int mileage, String engineType, float engineCapacity,
			int enginePower, String gearboxType, String driveType, String colour, boolean isDamaged) {
		super();
		this.carTitle = carTitle;
		this.price = price;
		this.yearModel = yearModel;
		this.mileage = mileage;
		this.engineType = engineType;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.gearboxType = gearboxType;
		this.driveType = driveType;
		this.colour = colour;
		this.isDamaged = isDamaged;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCarTitle() {
		return carTitle;
	}
	public void setCarTitle(String carTitle) {
		this.carTitle = carTitle;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getYearModel() {
		return yearModel;
	}
	public void setYearModel(int yearModel) {
		this.yearModel = yearModel;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public float getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(float engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public int getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(int enginePower) {
		this.enginePower = enginePower;
	}
	public String getGearboxType() {
		return gearboxType;
	}
	public void setGearboxType(String gearboxType) {
		this.gearboxType = gearboxType;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public boolean isDamaged() {
		return isDamaged;
	}
	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}
	
	
	
}
