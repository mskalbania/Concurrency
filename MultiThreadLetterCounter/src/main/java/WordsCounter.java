import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class WordsCounter implements Callable<Map<Integer, Integer>> {

    private List<String> inputWordList;
    private int countedWords;

    public WordsCounter(List<String> inputWordList) {
        this.inputWordList = inputWordList;
        countedWords = 0;
    }

    //key -> word length; value -> times appeared;
    private Map<Integer, Integer> countWords() {
        Map<Integer, Integer> output = new HashMap<>();
        for (String s : inputWordList) {
            int stringLength = s.length();
            int timesAppeared = output.getOrDefault(stringLength, 0);
            output.put(stringLength, ++timesAppeared);
            countedWords++;
        }
        return output;
    }

    public int getCountedWordsAmount() {
        return countedWords;
    }

    @Override
    public Map<Integer, Integer> call() throws Exception {
        return countWords();
    }
}
