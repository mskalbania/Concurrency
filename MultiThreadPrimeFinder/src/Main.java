import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000000; i++) {
            numbers.add(i);
        }

        long start = System.currentTimeMillis();
        long end;

        PrimeNumbersService primeNumbersService = new PrimeNumbersService(numbers, 3);
        List<Integer> primes = primeNumbersService.getPrimeNumbers();

        end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(primes.size());

    }
}
