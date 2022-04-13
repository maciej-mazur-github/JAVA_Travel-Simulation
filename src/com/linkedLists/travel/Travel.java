package com.linkedLists.travel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Travel {
    private LinkedList<String> places = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    private ListIterator<String> travelIterator = places.listIterator();
    private boolean goingForward = true;

    public void startTraveling() {
        System.out.println("Welcome to our traveling agency. Choose one of below actions:\n");
        printActions();

        boolean quit = false;
        int choice;

        while (!quit) {
            System.out.print("\nWhat is your choice? (5 to print the available options) ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if(choice < 0 || choice > 5) {
                System.out.println("Invalid choice number. Try again.");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("You have decided to finish your travel");
                    quit = true;
                    break;
                case 1:

            }
        }
    }

    private void removeCity(String cityName) {
        String nextCityName = cityName;

        if(goingForward) {
            if(travelIterator.hasNext()) {
                String currentCityName = travelIterator.next();

                if(currentCityName.equals(cityName)) {
                    nextCityName = travelIterator.next();
                } else {
                    travelIterator.previous();
                }
            }

        } else {
            if(travelIterator.hasPrevious()) {
                String currentCityName = travelIterator.previous();

                if(currentCityName.equals(cityName)) {
                    nextCityName = travelIterator.previous();
                } else {
                    travelIterator.previous();
                }
            }
        }

        places.remove(cityName);
        System.out.println(cityName + " successfully removed. You are now in the next city which is " + nextCityName);
    }

    private void removeCity() {
        System.out.print("Which city would like to remove? ");
        String cityToRemove = scanner.nextLine();

        if(places.contains(cityToRemove)) {
            removeCity(cityToRemove);
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

            if()
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

}
