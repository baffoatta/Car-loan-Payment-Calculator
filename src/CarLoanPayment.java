import java.util.Scanner;


public class CarLoanPayment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double loanAmount = 0;
        double interestRate = 0;
        int loanDuration = 0;
        double monthlyPayment;
        double totalCostOfLoan;
        double totalInterestPaid;


        try {
            System.out.print("Enter the Amount of Loan you took:");
            loanAmount = Double.parseDouble(String.valueOf(scanner.nextDouble()));

            System.out.print("Enter the Interest Rate of the Loan (Eaxample: 11.1):");
            interestRate = Double.parseDouble(String.valueOf(scanner.nextDouble()));

//        loan duration has to be in months
            System.out.print("Enter the loan duration(Months):");
            loanDuration = Integer.parseInt(String.valueOf(scanner.nextInt()));
        } catch (NumberFormatException e){
            System.out.println("Invalid Input, Please enter a valid number");
            return;
        }


//        calculated monthly payment method
        monthlyPayment = calculateLoanMonthlyPayment(loanAmount, interestRate, loanDuration);
        totalCostOfLoan = calculateTotalLoanCost(monthlyPayment, loanDuration);
        totalInterestPaid = calculateTotalInterestPaid(totalCostOfLoan, loanAmount);

        System.out.printf("Your Monthly Loan Payment will be $%.2f\n", monthlyPayment);
        System.out.printf("Your Total Cost of Loan is: $%.2f\n", totalCostOfLoan);
        System.out.printf("Total interest paid is: $%.2f\n", totalInterestPaid);

        System.out.printf("Do you want to see your amortization table (Y/N): ");
        String userAnswer = scanner.next();
        if(userAnswer.equalsIgnoreCase("Y"))
        {
            System.out.println();
            System.out.println("Month\tPayment\t\tinterest\tPricipal\tBalance");
            printUserAmortizationTable(loanAmount, interestRate, monthlyPayment, loanDuration);
        }
        scanner.close();

    }


    //        Calculate monthy payment using the foolowing formula
//        monthly_payment = (loan_amount * interest_rate * (1 + interest_rate)^loan_duration) / ((1 + interest_rate)^loan_duration - 1)
    public static double calculateLoanMonthlyPayment(double loanAmount, double interestRate, int loanDuration){
        double monthlyInterest = interestRate/100/12;
        double payment = loanAmount * monthlyInterest / (1- 1/Math.pow((1+monthlyInterest), loanDuration));
        return payment;
    }
//    calculating total loan amount
    public static double calculateTotalLoanCost(double monthlyPayment, int loanDuration){
       return monthlyPayment * loanDuration;

    }
    public static double calculateTotalInterestPaid(double totalCostOfLoan, double loanAmount){
        return totalCostOfLoan - loanAmount;
    }

//    creating amortization table if the user wants to see
    public static void printUserAmortizationTable( double loanAmount, double interestRate, double monthlyPayment, int loanDuration){
        double balance = loanAmount;
        double monthlyInterest = interestRate/1200;
        for ( int month = 1; month <= loanDuration; month++){
            double interest = balance * monthlyInterest;
            double principalAmount = monthlyPayment - interest;
            balance -= principalAmount;
            System.out.printf("%d\t\t$%.2f\t\t$%.2f\t\t$%.2f\t\t$%.2f\n", month, monthlyPayment, interest, principalAmount,balance);
        }
    }

}