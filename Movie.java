import java.util.ArrayList;
import java.util.Scanner;

public class Movie {

    double price;
    int capacity;
    int numTickets;
    boolean soldOut;
    boolean isIMAX;
    String name;



    String review = "";
    int rating=5;

    public Movie(String movieName, boolean IMAX) {
        isIMAX = IMAX;
        if (isIMAX) {
            price = CONSTANTS.ticketPriceIMAX;
            capacity = CONSTANTS.capacityIMAX;

        } else {
            price = CONSTANTS.ticketPriceNormal;
            capacity = CONSTANTS.capacityNormal;
        }

        numTickets = 0;
        soldOut = false;

        name = movieName;
    }


    // BUG: returns 0.0 for the last ticket bought if it was the last
    public double buyTickets(int tixPurchased) {
        double purchasePrice = 0.0;
        if (!this.soldOut) {
            this.numTickets += tixPurchased;
            purchasePrice = this.getPrice() * tixPurchased;
        }

        return purchasePrice;
    }

    public void printTix() {
        System.out.println("Your tickets for " + this.getName() + " are being printed. Please enjoy!");
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public int isSoldOut(int tix) {
        return this.getCapacity() - (tix + this.getNumTickets());
    }

    public boolean isIMAX() {
        return isIMAX;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }


    public void setIMAX(boolean IMAX) {
        isIMAX = IMAX;
    }

    public double swapTix(Movie swap, int tix)
    {
        if(swap.isSoldOut(tix)>=0)
        {
            swap.buyTickets(tix);
            if(swap.price==this.price)
                System.out.println("Tickets swapped! No further payment is required! ");
            else if(swap.price>this.price)
                System.out.println("Tickets Swapped! You will need to pay an additional $"+((swap.price-this.price)*tix));
            else
                System.out.println("Tickets Swapped! You will be refunded $"+((this.price-swap.price)*tix));
            return Math.abs((swap.price-this.price)*tix);
        }
        else
        {
            if(swap.isSoldOut(tix)+tix!=0) {
                System.out.println("Sorry! The showing you'd like to swap your tickets for doesn't have enough available tickets!\n" +
                        "you are welcome to retry the swap with " + Math.abs(swap.isSoldOut(tix)) + " tickets instead.");
            }
            else
            {
                System.out.println("Sorry! The show you'd like to swap your tickets for is sold out!");
            }
        }

        return 0.0;
    }

    public void reviewMovie(Scanner reader)
    {
        System.out.println("What would you rate the movie 1-10: ");
        this.rating = Integer.parseInt(reader.nextLine());
        System.out.print("Would you like to leave a written review? (Y/N): ");
        String answer = reader.nextLine();
        if(answer.equalsIgnoreCase("y"))
        {
            System.out.println("Please leave your review below and hit enter when done.\n\n");
            this.review=reader.nextLine();
        }
        System.out.println("Thank you for your input!");
    }

    public static void userInterface(Scanner reader, ArrayList<Movie> movies)
    {
        String input="";
        System.out.println("What movie would you like to see?\n");
        for (int i = 0; i < movies.size(); i++) {
            if (!movies.get(i).soldOut)
                System.out.println((i + 1) + ". " + movies.get(i).getName() + ": " + movies.get(i).getNumTickets() + "/" + movies.get(i).getCapacity());
        }
        boolean correctInput = false;
        String tempInput = "";
        int movieNumber = 0;
        int tix = 0;
        System.out.print("\nEnter Movie #: ");
        tempInput = reader.nextLine();



        if(tempInput.equalsIgnoreCase("swap"))
    {
        System.out.println("Which movie would you like to swap tickets for?");
        int swapIndex = Integer.parseInt(reader.nextLine());
        Movie toSwap = movies.get(swapIndex);
        movies.get(movieNumber).swapTix(toSwap, tix);
    }
        else if(tempInput.equalsIgnoreCase("review"))
        {
            System.out.println("Which movie would you like to review?: ");
            int reviewIndex = Integer.parseInt(reader.nextLine());
            movies.get(reviewIndex).reviewMovie(reader);

        }



        else {
            movieNumber = Integer.parseInt(tempInput);
            Movie chosen = movies.get(movieNumber - 1);
            System.out.print("Enter # of Tickets: ");
            tix = Integer.parseInt(reader.nextLine());
            if (chosen.isSoldOut(tix) >= 0) {
                if (chosen.isSoldOut(tix) == 0) {
                    chosen.setSoldOut(true);
                }
                System.out.println("The total cost of your " + tix + " tickets to " + chosen.getName() + " is $" + chosen.buyTickets(tix));
                chosen.printTix();

                System.out.println("-----END OF SALE------ \nPLEASE PICK UP TICKETS FROM TOP OF KIOSK\n\n");
            } else if (chosen.isSoldOut(tix) < 0) {
                System.out.println("Sorry! This Showing is nearly sold out and you would only be \nable to purchase " + (Math.abs(chosen.getCapacity() - chosen.getNumTickets())) + " tickets.");
                System.out.print("Would you like to purchase these tickets? (y/n): ");
                input = reader.nextLine();
                if (input.equals("y") || input.equals("Y") || input.equalsIgnoreCase("yes")) {
                    chosen.buyTickets((Math.abs(chosen.getCapacity() - chosen.getNumTickets())));
                    chosen.printTix();
                    chosen.setSoldOut(true);
                    System.out.println("-----END OF SALE------ \nPLEASE PICK UP TICKETS FROM TOP OF KIOSK\n\n");
                } else {
                    System.out.println("-----RESTARTING SALE-----");
                }
            }
        }
    }
}

