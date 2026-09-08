import java.util.*;
import java.util.regex.*;
interface Displayable{
    void display();
}
interface Optimizable{
    void optimize() throws PlanoraException;
}
abstract class Person implements Displayable{
    private String name;
    private String phone;
    public Person(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public abstract String getRole();
    public void display(){
        System.out.println(name+" | "+phone+" | "+getRole());
    }
}
class Event{
    private String name;
    private String type;
    private int attendees;
    private double budget;
    private String date;
    public Event(String name,String type,int attendees,double budget,String date){
        this.name=name;
        this.type=type;
        this.attendees=attendees;
        this.budget=budget;
        this.date=date;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public int getAttendees(){
        return attendees;
    }
    public double getBudget(){
        return budget;
    }
    public String getDate(){
        return date;
    }
    public void display(){
        System.out.println("Event Name       : "+name);
        System.out.println("Event Type       : "+type);
        System.out.println("Expected Guests  : "+attendees);
        System.out.println("Total Budget     : Rs."+String.format("%.2f",budget));
        System.out.println("Event Date       : "+date);
    }
}
class Venue implements Displayable{
    private String name;
    private int capacity;
    private double cost;
    private boolean available;
    public Venue(String name,int capacity,double cost,boolean available){
        this.name=name;
        this.capacity=capacity;
        this.cost=cost;
        this.available=available;
    }
    public String getName(){
        return name;
    }
    public int getCapacity(){
        return capacity;
    }
    public double getCost(){
        return cost;
    }
    public boolean isAvailable(){
        return available;
    }
    public void display(){
        System.out.printf("%-25s Capacity: %-5d Cost: Rs.%-10.2f Status: %s%n",name,capacity,cost,available?"Available":"Booked");
    }
}
class Resource implements Displayable{
    private String name;
    private int quantity;
    public Resource(String name,int quantity){
        this.name=name;
        this.quantity=quantity;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public void display(){
        System.out.printf("%-20s : %d%n",name,quantity);
    }
}
class Vendor extends Person{
    private String service;
    private double cost;
    private double rating;
    public Vendor(String name,String phone,String service,double cost,double rating){
        super(name,phone);
        this.service=service;
        this.cost=cost;
        this.rating=rating;
    }
    public String getService(){
        return service;
    }
    public double getCost(){
        return cost;
    }
    public double getRating(){
        return rating;
    }
    public String getRole(){
        return "Vendor";
    }
    @Override
    public void display(){
        System.out.printf("%-25s Service: %-12s Cost: Rs.%-10.2f Rating: %.1f/5%n",getName(),service,cost,rating);
    }
}
class Volunteer extends Person{
    private String skill;
    private boolean available;
    public Volunteer(String name,String phone,String skill,boolean available){
        super(name,phone);
        this.skill=skill;
        this.available=available;
    }
    public String getSkill(){
        return skill;
    }
    public boolean isAvailable(){
        return available;
    }
    public String getRole(){
        return "Volunteer";
    }
    @Override
    public void display(){
        System.out.printf("%-15s Skill: %-20s Status: %s%n",getName(),skill,available?"Available":"Unavailable");
    }
}
class PlanoraException extends Exception{
    public PlanoraException(String message){
        super(message);
    }
}
class EventValidator{
    public static boolean validateName(String name){
        return name.matches("[A-Za-z ]+");
    }
    public static boolean validateDate(String date){
        Pattern pattern=Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher matcher=pattern.matcher(date);
        return matcher.matches();
    }
    public static boolean validateAttendees(int attendees){
        return attendees>0;
    }
    public static boolean validateBudget(double budget){
        return budget>0;
    }
}
class OptimizationEngine implements Optimizable{
    private Event event;
    private ArrayList<Venue> venues;
    private ArrayList<Vendor> vendors;
    private ArrayList<Resource> resources;
    private Venue selectedVenue;
    private Vendor selectedVendor;
    public OptimizationEngine(Event event,ArrayList<Venue> venues,ArrayList<Vendor> vendors,ArrayList<Resource> resources){
        this.event=event;
        this.venues=venues;
        this.vendors=vendors;
        this.resources=resources;
    }
    public Venue findBestVenue(){
        Venue best=null;
        for(Venue venue:venues){
            if(venue.isAvailable()&&venue.getCapacity()>=event.getAttendees()){
                if(best==null||venue.getCost()<best.getCost()){
                    best=venue;
                }
            }
        }
        return best;
    }
    public Vendor findBestVendor(){
        Vendor best=null;
        for(Vendor vendor:vendors){
            if(best==null||vendor.getRating()>best.getRating()){
                best=vendor;
            }
        }
        return best;
    }
    public int calculateVolunteers(){
        return Math.max(5,(event.getAttendees()+49)/50);
    }
    public double calculateBudget(Venue venue,Vendor vendor){
        double decoration=event.getAttendees()*50;
        double security=event.getAttendees()*20;
        double transportation=event.getAttendees()*15;
        return venue.getCost()+vendor.getCost()+decoration+security+transportation;
    }
    public double calculateBudget(Venue venue,Vendor vendor,double decorationRate){
        double decoration=event.getAttendees()*decorationRate;
        double security=event.getAttendees()*20;
        double transportation=event.getAttendees()*15;
        return venue.getCost()+vendor.getCost()+decoration+security+transportation;
    }
    private void analyzeResources(){
        int chairsRequired=event.getAttendees()+50;
        int tablesRequired=(event.getAttendees()+4)/5;
        int projectorsRequired=Math.max(1,(event.getAttendees()+299)/300);
        System.out.println("\nRESOURCE REQUIREMENTS");
        System.out.println("------------------------------");
        System.out.println("Chairs Required     : "+chairsRequired);
        System.out.println("Tables Required     : "+tablesRequired);
        System.out.println("Projectors Required : "+projectorsRequired);
        System.out.println("\nRESOURCE AVAILABILITY");
        System.out.println("------------------------------");
        for(Resource resource:resources){
            if(resource.getName().equalsIgnoreCase("Chairs")){
                System.out.println("Chairs              : "+(resource.getQuantity()>=chairsRequired?"Available":"Shortage of "+(chairsRequired-resource.getQuantity())));
            }
            if(resource.getName().equalsIgnoreCase("Tables")){
                System.out.println("Tables              : "+(resource.getQuantity()>=tablesRequired?"Available":"Shortage of "+(tablesRequired-resource.getQuantity())));
            }
            if(resource.getName().equalsIgnoreCase("Projectors")){
                System.out.println("Projectors          : "+(resource.getQuantity()>=projectorsRequired?"Available":"Shortage of "+(projectorsRequired-resource.getQuantity())));
            }
        }
    }
    public void optimize() throws PlanoraException{
        System.out.println("\nPLANORA OPTIMIZATION ENGINE");
        System.out.println("------------------------------");
        System.out.println("Analyzing event requirements...");
        selectedVenue=findBestVenue();
        selectedVendor=findBestVendor();
        if(selectedVenue==null){
            throw new PlanoraException("No suitable venue is available.");
        }
        if(selectedVendor==null){
            throw new PlanoraException("No suitable vendor is available.");
        }
        analyzeResources();
        double total=calculateBudget(selectedVenue,selectedVendor);
        System.out.println("\nOPTIMIZED RECOMMENDATION");
        System.out.println("------------------------------");
        System.out.println("Best Venue          : "+selectedVenue.getName());
        System.out.println("Recommended Vendor  : "+selectedVendor.getName());
        System.out.println("Volunteers Required : "+calculateVolunteers());
        System.out.println("Estimated Cost      : Rs."+String.format("%.2f",total));
        if(total>event.getBudget()){
            System.out.println("Budget Status       : Warning");
            System.out.println("Amount Over Budget  : Rs."+String.format("%.2f",total-event.getBudget()));
        }else{
            System.out.println("Budget Status       : Within Budget");
            System.out.println("Remaining Budget    : Rs."+String.format("%.2f",event.getBudget()-total));
        }
    }
    public Venue getSelectedVenue(){
        return selectedVenue;
    }
    public Vendor getSelectedVendor(){
        return selectedVendor;
    }
}
class EventProcessor extends Thread{
    private String task;
    public EventProcessor(String task){
        this.task=task;
    }
    public void run(){
        System.out.println("Processing: "+task);
        try{
            Thread.sleep(700);
        }catch(InterruptedException e){
            System.out.println("Processing interrupted.");
        }
        System.out.println("Completed: "+task);
    }
}
public class Planora{
    static final String APP_NAME="PLANORA";
    static Scanner scanner=new Scanner(System.in);
    static ArrayList<Event> events=new ArrayList<>();
    static HashMap<String,Integer> resourceInventory=new HashMap<>();
    static HashSet<String> eventTypes=new HashSet<>();
    public static void main(String[] args){
        System.out.println("============================================================");
        System.out.println("                         PLANORA");
        System.out.println("              Unified Event Intelligence Platform");
        System.out.println("============================================================");
        System.out.println("\nWelcome to Planora.");
        System.out.println("Let's create your optimized event plan.");
        try{
            System.out.println("\nENTER EVENT DETAILS");
            System.out.println("------------------------------");
            System.out.print("Event Name             : ");
            String eventName=scanner.nextLine().trim();
            if(!EventValidator.validateName(eventName)){
                throw new PlanoraException("Event name should contain only letters and spaces.");
            }
            System.out.print("Event Type             : ");
            String eventType=scanner.nextLine().trim();
            System.out.print("Expected Attendees     : ");
            int attendees=scanner.nextInt();
            if(!EventValidator.validateAttendees(attendees)){
                throw new PlanoraException("Number of attendees must be greater than zero.");
            }
            System.out.print("Total Budget           : Rs.");
            double budget=scanner.nextDouble();
            if(!EventValidator.validateBudget(budget)){
                throw new PlanoraException("Budget must be greater than zero.");
            }
            scanner.nextLine();
            System.out.print("Event Date DD-MM-YYYY  : ");
            String date=scanner.nextLine().trim();
            if(!EventValidator.validateDate(date)){
                throw new PlanoraException("Invalid date format. Use DD-MM-YYYY.");
            }
            Event event=new Event(eventName,eventType,attendees,budget,date);
            events.add(event);
            eventTypes.add(eventType);
            resourceInventory.put("Chairs",1500);
            resourceInventory.put("Tables",300);
            resourceInventory.put("Projectors",5);
            resourceInventory.put("Speakers",10);
            ArrayList<Venue> venues=new ArrayList<>();
            venues.add(new Venue("Community Hall",600,30000,true));
            venues.add(new Venue("City Convention Hall",1200,55000,true));
            venues.add(new Venue("Grand Convention Centre",2000,90000,true));
            ArrayList<Vendor> vendors=new ArrayList<>();
            vendors.add(new Vendor("Fresh Feast Catering","9876543210","Catering",70000,4.3));
            vendors.add(new Vendor("Royal Caterers","9876501234","Catering",90000,4.7));
            vendors.add(new Vendor("Grand Feast Events","9876512345","Catering",120000,4.9));
            ArrayList<Resource> resources=new ArrayList<>();
            resources.add(new Resource("Chairs",resourceInventory.get("Chairs")));
            resources.add(new Resource("Tables",resourceInventory.get("Tables")));
            resources.add(new Resource("Projectors",resourceInventory.get("Projectors")));
            System.out.println("\nEVENT SUMMARY");
            System.out.println("------------------------------");
            event.display();
            System.out.println("\nAVAILABLE VENUES");
            System.out.println("------------------------------");
            for(Venue venue:venues){
                venue.display();
            }
            System.out.println("\nAVAILABLE VENDORS");
            System.out.println("------------------------------");
            for(Vendor vendor:vendors){
                vendor.display();
            }
            System.out.println("\nRESOURCE INVENTORY");
            System.out.println("------------------------------");
            for(Resource resource:resources){
                resource.display();
            }
            OptimizationEngine engine=new OptimizationEngine(event,venues,vendors,resources);
            engine.optimize();
            Volunteer[] volunteers={
                new Volunteer("Arun","9876543211","Registration",true),
                new Volunteer("Priya","9876543212","Crowd Management",true),
                new Volunteer("Rahul","9876543213","Technical Support",true),
                new Volunteer("Meena","9876543214","First Aid",true)
            };
            System.out.println("\nVOLUNTEER TEAM");
            System.out.println("------------------------------");
            for(Volunteer volunteer:volunteers){
                if(volunteer.isAvailable()){
                    volunteer.display();
                }
            }
            EventProcessor venueTask=new EventProcessor("Checking venue availability");
            EventProcessor resourceTask=new EventProcessor("Checking resource allocation");
            EventProcessor scheduleTask=new EventProcessor("Checking scheduling conflicts");
            System.out.println("\nSYSTEM ANALYSIS");
            System.out.println("------------------------------");
            venueTask.start();
            resourceTask.start();
            scheduleTask.start();
            venueTask.join();
            resourceTask.join();
            scheduleTask.join();
            Venue selectedVenue=engine.getSelectedVenue();
            Vendor selectedVendor=engine.getSelectedVendor();
            double totalCost=engine.calculateBudget(selectedVenue,selectedVendor);
            System.out.println("\n============================================================");
            System.out.println("                  PLANORA FINAL EVENT PLAN");
            System.out.println("============================================================");
            System.out.println("Event Name          : "+event.getName());
            System.out.println("Event Type          : "+event.getType());
            System.out.println("Event Date          : "+event.getDate());
            System.out.println("Expected Guests     : "+event.getAttendees());
            System.out.println("------------------------------------------------------------");
            System.out.println("Selected Venue      : "+selectedVenue.getName());
            System.out.println("Venue Capacity      : "+selectedVenue.getCapacity());
            System.out.println("Recommended Vendor  : "+selectedVendor.getName());
            System.out.println("Vendor Rating       : "+selectedVendor.getRating()+"/5");
            System.out.println("Volunteers Required : "+engine.calculateVolunteers());
            System.out.println("------------------------------------------------------------");
            System.out.println("Available Budget    : Rs."+String.format("%.2f",event.getBudget()));
            System.out.println("Estimated Cost      : Rs."+String.format("%.2f",totalCost));
            System.out.println("Remaining Budget    : Rs."+String.format("%.2f",Math.max(0,event.getBudget()-totalCost)));
            System.out.println("------------------------------------------------------------");
            if(totalCost<=event.getBudget()){
                System.out.println("Event Status        : READY TO PROCEED");
                System.out.println("Risk Level          : LOW");
            }else{
                System.out.println("Event Status        : BUDGET REVIEW REQUIRED");
                System.out.println("Risk Level          : MEDIUM");
            }
            System.out.println("============================================================");
            System.out.println("          Planora has prepared your event plan.");
            System.out.println("============================================================");
        }catch(InputMismatchException e){
            System.out.println("\nInput Error: Please enter a valid number.");
        }catch(PlanoraException e){
            System.out.println("\nPlanora Alert: "+e.getMessage());
        }catch(InterruptedException e){
            System.out.println("\nBackground processing was interrupted.");
        }finally{
            scanner.close();
        }
    }
}