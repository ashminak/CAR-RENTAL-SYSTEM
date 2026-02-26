import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class  Car{
    private String carId;
    private String carBrand;
    private String carModel;
    private double carPricePerDay;
    private boolean isAvailable = true;

    public Car(String carId, String carBrand, String carModel, double carPricePerDay) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carPricePerDay = carPricePerDay;

    }

    public String getCarId() {
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
    private String customerId;

    public Customer(String customerName,String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
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

   public void returnCar(Car car){
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
//           System.out.println("Car is removed successfully.");
       }else {
           System.out.println("Car was not rented.");
       }
   }
   public void menu(){
       Scanner sc = new Scanner(System.in);
       while(true){
           System.out.println("\n===Car Rental System===\n");
           System.out.println("1. Rent a Car.");
           System.out.println("2. Return a Car.");
           System.out.println("3.Exit.");
           System.out.print("Enter your Choice: ");
           int choice = sc.nextInt();
           sc.nextLine();
           if (choice==1){
               System.out.println("\n==Rent a Car==\n");
               System.out.print("Enter your Name: ");
               String CustomerName = sc.nextLine();
               System.out.println("\n=Available Cars=\n");
               for(Car car: cars){
                   if(car.isAvailable()){
                       System.out.println(car.getCarId()+"-"+car.getCarBrand()+" "+car.getCarModel());
                   }
               }
               System.out.print("\nEnter the Car ID you want to rent: ");
               String carId = sc.nextLine();
               System.out.print("Enter the number of Days for Rental: ");
               int days = sc.nextInt();
               sc.nextLine();
               Customer newcustomer = new Customer(CustomerName,"CUS"+(customers.size()+1));
               Car selectedCar = null;
               for(Car car:cars){
                   if(car.getCarId().equals(carId) && car.isAvailable()){
                       selectedCar =car;
                       break;
                   }
               }

               if(selectedCar!=null){
                   double totalPrice =selectedCar.getCarPricePerDay(days);
                   System.out.println("\nRental Information");
                   System.out.println("Customer ID - "+newcustomer.getCustomerId());
                   System.out.println("Customer Name - "+newcustomer.getCustomerName());
                   System.out.println("Car - "+selectedCar.getCarBrand()+" "+selectedCar.getCarModel());
                   System.out.println("Rental Days - "+days);
                   System.out.println("Price - "+totalPrice);

                   System.out.println("\nConfirm Rental (Y/N)");
                   String confirmation = sc.next();

                   if(confirmation.equalsIgnoreCase("Y")){
                       rentCar(selectedCar,newcustomer,days);
                       System.out.println("Car Rented Successfully.");
                   }else{
                       System.out.println("Rental Cancel.");
                   }
               }else{
                   System.out.println("\n Invalid Car selection or Car is not available for rent.");
               }

           } else if (choice==2){
               System.out.println("==Return a Car==");
               System.out.print("\nEnter Car Id you want to return: ");
               String carId =sc.nextLine();

                Car carToBeReturn = null;
               for(Car car:cars){
                   if(car.getCarId().equals(carId) && !car.isAvailable()){
                       carToBeReturn=car;
                       break;
                   }
               }
               if(carToBeReturn != null){
                    Customer customer = null;
                    for (Rental rental: rentals){
                        if(rental.getCar()== carToBeReturn){
                            customer =rental.getCustomer();
                            break;
                        }
                   }

              if(customer!=null){
                    returnCar(carToBeReturn);
                       System.out.println("\nCar Returned Successfully by "+customer.getCustomerName()+".");
                   }else{
                       System.out.println("Car was not rented or rental information is missing.");
                   }
               }else{
                   System.out.println("Invalid car ID or car is not rented.");
               }

           } else if (choice ==3) {
               break;
           }else{
               System.out.println("Invalid choice. Please enter a valid option.");
           }
       }
       System.out.println("\nThank you for using the Car Rental System!");
   }
}


public class Main {

    static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);
        carRentalSystem.menu();
    }
}