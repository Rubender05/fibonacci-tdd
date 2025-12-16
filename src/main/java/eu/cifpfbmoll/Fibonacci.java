package eu.cifpfbmoll;

public class Fibonacci {

    /**
     * Calcula l'enèsim nombre de Fibonacci (F(n)).
     *
     * @param n L'índex (ha de ser major o igual que 0).
     * @return El nombre de Fibonacci corresponent a l'índex n.
     * @throws IllegalArgumentException si n és negatiu.
     */
    public int calculate(int n) {
        // 1. Validació
        if (n < 0) {
            throw new IllegalArgumentException("Només s'accepten enters iguals o majors que zero");
        }

        // 2. Casos base (F(0) = 0, F(1) = 1)
        if (n <= 1) {
            return n;
        }

        // 3. Implementació iterativa
        int previous = 0;
        int current = 1;

        // Comencem des de i=2, ja que F(0) i F(1) ja estan coberts
        for (int i = 2; i <= n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }

        return current;
    }
}