import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordsCounterService {

    private WordsCounter wordsCounter;
    private Writer writer;
    private Reader reader;

    public WordsCounterService(Reader reader, Writer writer) {
        this.writer = writer;
        this.reader = reader;
        this.wordsCounter = new WordsCounter(reader.readWords());
    }

    private Map<Integer, Integer> performCalculations() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Map<Integer, Integer>> futureResult = executorService.submit(wordsCounter);
        try {
            Map<Integer, Integer> result = futureResult.get();
            executorService.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private double calculateFrequency(Integer timesAppeared) {
        double result = timesAppeared.doubleValue() / ((double) wordsCounter.getCountedWordsAmount());
        return (Math.round(result * 10000d) / 100d);
    }

    public void printResultsToConsole() {
        Map<Integer, Integer> calculationsResult = performCalculations();
        System.out.println("WORDS AMOUNT : " + wordsCounter.getCountedWordsAmount());
        for (Map.Entry entry : calculationsResult.entrySet()) {
            System.out.println(entry.getKey() + " letter words appeared " + entry.getValue() + " time(s) with frequency " +
                    calculateFrequency(((Integer) entry.getValue())) + " %");
        }
    }

    public void writeResultsToFile(String path) {

    }
}
