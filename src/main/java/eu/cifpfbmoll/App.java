package eu.cifpfbmoll;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(" java -jar fibonacci.jar <n>");
            System.exit(1);
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error: El parametre ha de ser un numero enter.");
            System.exit(2);
            return;
        }

        if (n < 0) {
            System.err.println("Error: El parametre ha de ser major o igual que 0");
            System.exit(2);
        }

        try {
            Fibonacci fib = new Fibonacci();
            System.out.println(fib.calculate(n));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }
}