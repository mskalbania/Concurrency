import java.util.ArrayList;
import java.util.List;

public class PrimesSingleThread extends Thread {

    private List<Integer> numbersToCheck;
    private List<Integer> primeNumbers;

    public PrimesSingleThread(List<Integer> numbersToCheck) {
        this.numbersToCheck = numbersToCheck;
        this.primeNumbers = new ArrayList<>();
    }

    public List<Integer> getPrimeNumbers(){
        List<Integer> output = new ArrayList<>(primeNumbers);
        primeNumbers.clear();
        return output;
    }

    private void performCalculations() {
        for (Integer i : numbersToCheck) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
    }

    private boolean isPrime(int number) {
        if (number == 1) return false;
        if (number == 2) return true;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        performCalculations();
    }
}
