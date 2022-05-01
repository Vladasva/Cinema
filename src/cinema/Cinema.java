package cinema;

import java.util.Scanner;

public class Cinema {

    final static Scanner scanner = new Scanner(System.in);
    final static int premiumTicketPrice = 10;
    final static int secondTicketPrice = 8;
    final static int sixtySeats = 60;
    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();



        menu(rows, seats);
    }

    public static void menu(int rows, int seats) {
        boolean flag = true;

        char[][] cinema = new char[rows][seats];

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (flag) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int menuChoice = scanner.nextInt();

            switch (menuChoice) {

                case (1):
                    showTheSeats(rows, seats, cinema);
                    break;

                case 2:
                    buyATicket(rows, seats, cinema);
                    break;

                case (3):
                    statistics(rows, seats, cinema);
                    break;

                case 0:
                    flag = false;
                    break;
            }
        }
    }

    public static void showTheSeats(int rows, int seats, char[][] cinema) {
        int rowCounter = 0;

        System.out.println();

        System.out.println("Cinema:");

        System.out.print(" ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(" "  + i);
        }

        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            rowCounter++;
            System.out.print(rowCounter + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] buyATicket(int rows, int seats, char[][] cinema) {

        boolean flag = false;

        while (flag != true){
            System.out.println();
            System.out.println("Enter a row number:");
            int numberOfRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int numberOfSeat = scanner.nextInt();


            int totalNumberOfSeats = rows * seats;

            if (numberOfRow <= rows && numberOfRow > 0 && numberOfSeat <= seats && numberOfSeat > 0) {

                System.out.println();

                for (int i = 0; i < cinema.length; i++) {
                    for (int j = 0; j < cinema[i].length; j++) {
                        if (i == numberOfRow - 1 && j == numberOfSeat - 1) {
                            if (cinema[i][j] != 'B') {
                                cinema[i][j] = 'B';
                                if (totalNumberOfSeats <= sixtySeats) {
                                    System.out.println();
                                    System.out.print("Ticket price: $" + premiumTicketPrice);
                                    System.out.println();
                                } else {
                                    int firstHalf = rows / 2;
                                    if (numberOfRow <= firstHalf) {
                                        System.out.println();
                                        System.out.print("Ticket price: $" + premiumTicketPrice);
                                        System.out.println();
                                    } else {
                                        System.out.println();
                                        System.out.print("Ticket price: $" + secondTicketPrice);
                                        System.out.println();
                                    }
                                }
                                flag = true;
                            } else {
                                System.out.println("That ticket has already been purchased!");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Wrong input!");
            }
        }

        return cinema;
    }

    private static void statistics(int rows, int seats, char[][] cinema) {
        int numberOfTickets = 0;
        double percentage = 0.00;
        int currentIncome = 0;
        int totalIncome = 0;
        int totalNumberOfSeats = rows * seats;

        int seatCounter = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                seatCounter ++;
                if (cinema[i][j] == 'B') {
                    numberOfTickets++;
                    if (totalNumberOfSeats <= sixtySeats) {
                        currentIncome += premiumTicketPrice;
                    } else {
                        int firstHalf = rows / 2;
                        if (seatCounter <= (firstHalf * seats)) {
                            currentIncome += premiumTicketPrice;
                        } else {
                            currentIncome += secondTicketPrice;
                        }
                    }
                }
            }
        }

        percentage = (((double) numberOfTickets / (double) totalNumberOfSeats) * 100);

        if (totalNumberOfSeats <= sixtySeats) {
            totalIncome = totalNumberOfSeats * premiumTicketPrice;
        } else {
            int firstHalf = rows / 2;
            totalIncome = (firstHalf * seats * premiumTicketPrice) + ((rows - firstHalf) * seats * secondTicketPrice);
        }

        System.out.println();
        System.out.printf("Number of purchased tickets: %d", numberOfTickets);
        System.out.println();
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d", currentIncome);
        System.out.println();
        System.out.printf("Total income: $%d", totalIncome);
        System.out.println();
    }
}
