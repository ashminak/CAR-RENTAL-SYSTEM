import java.util.ArrayList;
import java.util.List;

class  Car{
    private int carId;
    private String carBrand;
    private String carModel;
    private double carPricePerDay;
    private boolean isAvailable;

    public Car(int carId, String carBrand, String carModel, double carPricePerDay, boolean isAvailable) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carPricePerDay = carPricePerDay;
        this.isAvailable = isAvailable;
    }

    public int getCarId() {
        return carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public double getCarPricePerDay(int rentday) {
        return carPricePerDay * rentday;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent(){
        isAvailable= false;
    }

    public void returnCar(){
        isAvailable=true;
    }
}

class Customer{
    private String customerName;
    private int customerId;

    public Customer(String customerName, int customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }
}

class Rental{
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem{
    private List<Car> cars;
    private  List<Customer> customers;
    private  List<Rental> rentals;

   public CarRentalSystem(){
        cars= new ArrayList<>();
        customers= new ArrayList<>();
        rentals= new ArrayList<>();
   }

   public void addCar(Car car){
       cars.add(car);
   }

   public void addCustomer(Customer customer){
       customers.add(customer);
   }

   public void rentCar(Car car,Customer customer,int days){
       if(car.isAvailable()){
           car.rent();
           rentals.add(new Rental(car,customer,days));
       }else {
           System.out.println("Car is not available for Rent.");
       }
   }

   public void returnCar(Car car,Customer customer,Rental rental){
       car.returnCar();
       Rental rentalToRemove = null;
       for(Rental rental1: rentals){
           if(rental1.getCar()==car){
               rentalToRemove =rental1;
               break;
           }
       }
       if(rentalToRemove !=null){
           rentals.remove(rentalToRemove);
           System.out.println("Car is removed successfully.");
       }else {
           System.out.println("Car was not rented.");
       }
   }
}

public class Main {

    static void main(String[] args) {

    }
}