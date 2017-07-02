import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrimeNumbersService {

    private List<Integer> numbersToCheck;
    private List<Integer> primeNumbers;
    private int threadsNumber;
    private List<PrimesSingleThread> threads;


    public PrimeNumbersService(List<Integer> numbersToCheck, int threadsNumber) {
        this.numbersToCheck = numbersToCheck;
        this.primeNumbers = new ArrayList<>();
        this.threadsNumber = threadsNumber;
        this.threads = new ArrayList<>();
    }

    public List<Integer> getPrimeNumbers() {
        configureThreads();
        startThreads();
        getPrimesFromThreads();
        return primeNumbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private void configureThreads() {

        int startingThreadElement = 0;
        int elementsInThread = numbersToCheck.size() / threadsNumber;

        for (int i = 0; i < threadsNumber; i++) {
            List<Integer> subList = numbersToCheck.subList(startingThreadElement,
                    startingThreadElement + elementsInThread);

            threads.add(new PrimesSingleThread(subList));
            startingThreadElement += elementsInThread;
        }
    }

    private void startThreads() {
        threads.forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void getPrimesFromThreads() {
        for (PrimesSingleThread thread : threads) {
            primeNumbers.addAll(thread.getPrimeNumbers());
        }
    }
}
