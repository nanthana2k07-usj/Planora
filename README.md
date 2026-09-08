# Planora
## Unified Event Intelligence & Resource Optimization Platform

Planora is a Java-based console application designed to simplify event planning by helping users manage and optimize important event requirements such as venues, vendors, resources, volunteers, and budgets.

The application takes event details from the user, analyzes the available options, and generates a suitable event plan based on attendee count, venue capacity, availability, vendor rating, resource requirements, and budget.

## Features

### Event Management
- Accepts event name and event type
- Accepts expected number of attendees
- Accepts event date
- Accepts total event budget
- Displays a complete event summary

### Smart Venue Selection
- Maintains multiple venue options
- Checks venue availability
- Compares venue capacity with expected attendees
- Selects a suitable venue
- Considers venue cost during selection

### Vendor Recommendation
- Maintains vendor information
- Stores vendor service details
- Stores vendor ratings
- Compares available vendors
- Recommends a suitable vendor

### Resource Management
- Maintains resource inventory
- Tracks chairs, tables, and projectors
- Calculates required resources based on attendee count
- Checks resource availability
- Identifies resource shortages

### Volunteer Management
- Maintains volunteer information
- Stores volunteer skills
- Checks volunteer availability
- Calculates the required number of volunteers
- Displays the available volunteer team

### Budget Analysis
- Calculates estimated event expenses
- Includes venue, catering, decoration, security, and transportation costs
- Compares estimated cost with the user's budget
- Displays remaining budget
- Provides a warning when the estimated cost exceeds the budget

### Optimization Engine
The OptimizationEngine analyzes the event requirements and generates recommendations for:
- Best available venue
- Recommended vendor
- Required resources
- Required volunteers
- Estimated event cost
- Budget status

### Background Processing
The application uses Java multithreading to perform background analysis for:
- Venue availability
- Resource allocation
- Scheduling conflicts

## Java Concepts Used

The project is designed using concepts from the Core Java syllabus.

| Concept | Usage in Planora |
|---|---|
| Classes and Objects | Event, Venue, Vendor, Resource, Volunteer |
| Constructors | Used to initialize objects |
| Encapsulation | Private attributes with getter methods |
| Inheritance | Vendor and Volunteer inherit from Person |
| Abstraction | Person is an abstract class |
| Interface | Displayable and Optimizable |
| Polymorphism | Method overriding in Vendor and Volunteer |
| Method Overloading | Multiple venue, vendor, and budget methods |
| Arrays | Volunteer collection |
| ArrayList | Events, venues, vendors, and resources |
| HashMap | Resource inventory |
| HashSet | Event types |
| Strings | Event names, types, and dates |
| StringBuilder | Final event plan generation |
| Regular Expressions | Event name and date validation |
| Exception Handling | Input and application error handling |
| Custom Exception | PlanoraException |
| Multithreading | EventProcessor for background analysis |
| static | Shared application data |
| final | Application constant |

## Project Structure

```text
java project
│
├── Planora.java
├── Person.java
├── Event.java
├── Venue.java
├── Vendor.java
├── Resource.java
├── Volunteer.java
├── OptimizationEngine.java
├── Displayable.java
├── Optimizable.java
├── EventValidator.java
├── EventProcessor.java
└── PlanoraException.java
```

Console Output

When the program is executed, the user is first asked to enter the event details.

Input


============================================================
                         PLANORA
              Unified Event Intelligence Platform
============================================================

Welcome to Planora.
Let's create your optimized event plan.

ENTER EVENT DETAILS
------------------------------
Event Name             : Asha Wedding
Event Type             : Wedding
Expected Attendees     : 500
Total Budget           : Rs.400000
Event Date DD-MM-YYYY  : 15-12-2026

EVENT SUMMARY
------------------------------
Event Name       : Asha Wedding
Event Type       : Wedding
Expected Guests  : 500
Total Budget     : Rs.400000.00
Event Date       : 15-12-2026

AVAILABLE VENUES
------------------------------
Community Hall            Capacity: 600   Cost: Rs.30000.00    Status: Available
City Convention Hall      Capacity: 1200  Cost: Rs.55000.00    Status: Available
Grand Convention Centre   Capacity: 2000  Cost: Rs.90000.00    Status: Available

AVAILABLE VENDORS
------------------------------
Fresh Feast Catering      Service: Catering     Cost: Rs.70000.00    Rating: 4.3/5
Royal Caterers            Service: Catering     Cost: Rs.90000.00    Rating: 4.7/5
Grand Feast Events        Service: Catering     Cost: Rs.120000.00   Rating: 4.9/5

RESOURCE INVENTORY
------------------------------
Chairs               : 1500
Tables               : 300
Projectors           : 5

PLANORA OPTIMIZATION ENGINE
------------------------------
Analyzing event requirements...

RESOURCE REQUIREMENTS
------------------------------
Chairs Required     : 550
Tables Required     : 100
Projectors Required : 2

RESOURCE AVAILABILITY
------------------------------
Chairs              : Available
Tables              : Available
Projectors          : Available

OPTIMIZED RECOMMENDATION
------------------------------
Best Venue          : Community Hall
Recommended Vendor  : Grand Feast Events
Volunteers Required : 10
Estimated Cost      : Rs.192500.00
Budget Status       : Within Budget
Remaining Budget    : Rs.207500.00

VOLUNTEER TEAM
------------------------------
Arun            Skill: Registration          Status: Available
Priya           Skill: Crowd Management      Status: Available
Rahul           Skill: Technical Support     Status: Available
Meena           Skill: First Aid             Status: Available

SYSTEM ANALYSIS
------------------------------
Processing: Checking venue availability
Processing: Checking resource allocation
Processing: Checking scheduling conflicts
Completed: Checking venue availability
Completed: Checking resource allocation
Completed: Checking scheduling conflicts

============================================================
                  PLANORA FINAL EVENT PLAN
============================================================
Event Name          : Asha Wedding
Event Type          : Wedding
Event Date          : 15-12-2026
Expected Guests     : 500
------------------------------------------------------------
Selected Venue      : Community Hall
Venue Capacity      : 600
Recommended Vendor  : Grand Feast Events
Vendor Rating       : 4.9/5
Volunteers Required : 10
------------------------------------------------------------
Available Budget    : Rs.400000.00
Estimated Cost      : Rs.192500.00
Remaining Budget    : Rs.207500.00
------------------------------------------------------------
Event Status        : READY TO PROCEED
Risk Level          : LOW
============================================================
          Planora has prepared your event plan.
============================================================
