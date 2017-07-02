import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class LetterCounter implements Callable<List<Letter>> {

    private List<String> inputWords;
    private Integer currentCountedLetters;

    public LetterCounter(List<String> inputWords) {
        this.inputWords = inputWords;
        this.currentCountedLetters = 0;
    }

    private void updateFrequencies(List<Letter> letterFrequencies){
        for(Letter lf : letterFrequencies){
            lf.updateFrequency(currentCountedLetters);
        }
    }

    private List<Letter> countLetters() {
        List<Letter> output = new ArrayList<>();
        for (String word : inputWords) {
            String[] splitWord = word.split("");
            currentCountedLetters += splitWord.length;
            for (String character : splitWord) {
                Letter temp = new Letter(character);
                int index = output.indexOf(temp);
                if (index == -1) {
                    output.add(temp);
                }else {
                    output.get(index).incrementTimesAppeared();
                }
            }
        }
        updateFrequencies(output);
        return output.stream()
                .sorted(Comparator.comparing(Letter::getLetter))
                .collect(Collectors.toList());
    }

    @Override
    public List<Letter> call() throws Exception {
        return countLetters();
    }
}
