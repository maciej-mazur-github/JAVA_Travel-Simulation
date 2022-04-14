package com.linkedLists.travel;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Travel {
    private LinkedList<String> places = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    private ListIterator<String> travelIterator;
    private boolean goingForward = true;  // meaning towards the end of the list

    public void startTraveling() {
        System.out.println("Welcome to our traveling agency. Choose one of below actions:\n");
        printActions();

        boolean quit = false;
        int choice;

        while (!quit) {
            System.out.print("\nWhat is your choice? (6 to print the available options): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("You have decided to finish your travel");
                    quit = true;
                    break;
                case 1:
                    goToNextCity();   // to the one closer to the end of the list
                    break;
                case 2:
                    goToPreviousCity();  // to the one closer to the beginning of the list
                    break;
                case 3:
                    addCityInOrder();
                    break;
                case 4:
                    removeCity();
                    break;
                case 5:
                    printList();
                    break;
                case 6:
                    printActions();
                    break;
                default:
                    System.out.println("Invalid choice number. Try again.");
            }
        }
    }

    private void printList() {
        if(places.isEmpty()) {
            System.out.println("\nThere is no city to print yet. Try adding some cities to the list first.");
            return;
        }

        System.out.println("\n************************************");
        System.out.println("This is the current status of your travel list:");
        System.out.println();

        for(int i = 0; i < places.size(); i++) {
            System.out.println((i + 1) + ": " + places.get(i));
        }
        System.out.println("\n************************************");
    }

    private void goToNextCity() {   // to the one closer to the end of the list
        if(places.isEmpty()) {
            System.out.println("There is no city added to your list yet. Try adding some first");
            return;
        }
        
        ListIterator<String> tempIterator;
        
        if(travelIterator == null) {      // to make sure this method "remembers" what city to point at each time this method is called
            tempIterator = places.listIterator();   // then set the iterator to the first element of list
        } else {
            tempIterator = travelIterator;    // otherwise use the position remembered by travelIterator
        }

        if(goingForward) {
            if(tempIterator.hasNext()) {
                System.out.println("Going forward, now visiting " + tempIterator.next());
            } else {
                System.out.println(tempIterator.previous() + " was the last one in your list. You can't go any further. Start traveling backwards now.");
                tempIterator.next();
            }
        } else {   // meaning going backwards; this will yield a change of direction of the travel
            String city = tempIterator.next();   // according to the applied logic goingForward flag could only be false when the iterator in question has at least one "next" element
                                                    // Therefore no need to use the IF clause to check this condition

            if(tempIterator.hasNext()) {
                city = tempIterator.next();
                System.out.println("Changed direction, now going forward and visiting " + city);
            } else {
                System.out.println(city + " was the last one in your list. You can't go any further. Start traveling backwards now.");
            }

            goingForward = true;  // the actual change of direction of the travel happens now
        }
        
        travelIterator = tempIterator;  // save the result tempIterator position in travelIterator to let the object later know what city it should point at
    }

    private void goToPreviousCity() {    // to the one closer to the beginning of the list
        if(places.isEmpty()) {
            System.out.println("There is no city added to your list yet. Try adding some first");
            return;
        }

        ListIterator<String> tempIterator;

        if(travelIterator == null) {      // To make sure this method "remembers" what city to point at each time this method is called
            tempIterator = places.listIterator();   // set the iterator to the first element of list
        } else {
            tempIterator = travelIterator;    // otherwise use the position remembered by travelIterator
        }

        if(!goingForward) {
            if(tempIterator.hasPrevious()) {
                System.out.println("Going backwards, now visiting " + tempIterator.previous());
            } else {
                System.out.println(travelIterator.next() + " was the first city in your list, hence you have just reached the beginning of your list." +
                                    " You can now start moving forward");
                travelIterator.previous();  // go back to what travelIterator pointed at before printing out which city was the firs one
            }
        } else {
            if(tempIterator.hasPrevious()) {
                String city = tempIterator.previous();

                if(tempIterator.hasPrevious()) {
                    city = tempIterator.previous();
                    System.out.println("Changed direction, now going backwards and visiting " + city);
                } else {
                    System.out.println(city + " was the first one in your list. You can't go any further towards the beginning of the list. " +
                                        "Start traveling forward now.");
                    //  goingForward flag already in true status in this case
                }


            } else {
                System.out.println(tempIterator.next() + " was the first one in your list. You can't go any further. Start traveling backwards now.");
                tempIterator.previous();
            }

            goingForward = false;
        }

        travelIterator = tempIterator;
    }

    private void printActions() {
        System.out.println("\nAvailable options. Press: ");
        System.out.println("\t0 - to quit the program");
        System.out.println("\t1 - to go to the next city");
        System.out.println("\t2 - to go to the previous city");
        System.out.println("\t3 - to add a new city in ascending order");
        System.out.println("\t4 - to remove the city");
        System.out.println("\t5 - to print the current status of your travel list");
        System.out.println("\t6 - to print the available options");
    }


    private void removeCity() {
        if(places.isEmpty()) {
            System.out.println("\nNothing to remove yet. No city has been added to your travel list yet");
            return;
        }

        System.out.print("Which city would like to remove? ");
        String cityToRemove = scanner.nextLine();

        if(places.contains(cityToRemove)) {
            places.remove(cityToRemove);
            System.out.println(cityToRemove + " successfully removed.");
        } else {
            System.out.println("There is no such city in the list like " + cityToRemove);
        }
    }

    private void addCityInOrder() {
        System.out.print("What city would you like to add? ");
        String cityToAdd = scanner.nextLine();

        if(places.contains(cityToAdd)) {
            System.out.println(cityToAdd + " already exists in the list. No duplicates allowed.");
            return;
        }

        ListIterator<String> addingIterator = places.listIterator();
        int compareResult;

        while (addingIterator.hasNext()) {
            compareResult = addingIterator.next().compareTo(cityToAdd);

            if(compareResult > 0) {
                addingIterator.previous();
                break;
            }
        }

        addingIterator.add(cityToAdd);
        System.out.println(cityToAdd + " added successfully");
    }

}
