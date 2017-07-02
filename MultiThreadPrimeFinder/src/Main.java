import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Adding 10M numbers to list
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }

        long start = System.currentTimeMillis();
        long end;

        PrimeNumbersService primeNumbersService = new PrimeNumbersService(numbers, 4);// <- working on
        List<Integer> primes = primeNumbersService.getPrimeNumbers();                 //    5 threads

        end = System.currentTimeMillis();

        WriterService ws = new WriterService("/home/mateusz/Desktop/Concurrency/MultiThreadPrimeFinder",
                "OUTPUT");
        ws.writeToFile(primes);

        System.out.println("EXECUTION TIME: " + (end - start));

    }
}
