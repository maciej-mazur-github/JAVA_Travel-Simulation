package com.linkedLists.travel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Travel {
    private LinkedList<String> places = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    private ListIterator<String> travelIterator = places.listIterator();
    private boolean goingForward = true;  // meaning towards the end of the list

    public void startTraveling() {
        System.out.println("Welcome to our traveling agency. Choose one of below actions:\n");
        printActions();

        boolean quit = false;
        int choice;

        while (!quit) {
            System.out.print("\nWhat is your choice? (5 to print the available options) ");
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
                    printActions();
                case 6:
                    printList();
                default:
                    System.out.println("Invalid choice number. Try again.");
            }
        }
    }

    private void goToNextCity() {   // to the one closer to the end of the list
        if(places.isEmpty()) {
            System.out.println("There is no city added to your list yet. Try adding some first");
            return;
        }

        if(goingForward) {
            if(travelIterator.hasNext()) {
                System.out.println("Going forward, now visiting " + travelIterator.next());
            } else {
                System.out.println("You have reached the end of the list. You can now start moving backwards");
                goingForward = false;
            }
        } else {
            if(travelIterator.hasPrevious()) {
                System.out.println("Going backwards, now visiting " + travelIterator.previous());
            } else {
                System.out.println("You have reached the beginning of the list. You can now start moving forward");
                goingForward = true;
            }
        }
    }

    private void goToPreviousCity() {    // to the one closer to the beginning of the list
        if(places.isEmpty()) {
            System.out.println("There is no city added to your list yet. Try adding some first");
            return;
        }

        if(!goingForward) {
            if(travelIterator.hasNext()) {
                System.out.println("Going backwards, now visiting " + travelIterator.next());
            } else {
                System.out.println("You have reached the end of the list. You can now start moving backwards");
                goingForward = false;
            }
        } else {
            if(travelIterator.hasPrevious()) {
                System.out.println("Going backwards, now visiting " + travelIterator.previous());
            } else {
                System.out.println("You have reached the beginning of the list. You can now start moving forward");
                goingForward = true;
            }
        }
    }

    private void printActions() {
        System.out.println("Available options. Press: ");
        System.out.println("\t0 - to quit the program");
        System.out.println("\t1 - to go to the next city");
        System.out.println("\t2 - to go to the previous city");
        System.out.println("\t3 - to add a new city in ascending order");
        System.out.println("\t4 - to remove the city");
        System.out.println("\t5 - to print the available options");
    }


    private void removeCity() {
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
