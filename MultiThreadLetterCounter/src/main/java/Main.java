public class Main {
    public static void main(String[] args) throws Exception {

        WordsCounterService wordsCounterService = new WordsCounterService(new FileReader(
                "/home/mateusz/Desktop/Concurrency/MultiThreadLetterCounter/src/main/resources/",
                "slowa.txt")
        );

        wordsCounterService.printResultsToConsole();
        wordsCounterService.writeResultsToFile("/home/mateusz/Desktop/Concurrency/MultiThreadLetterCounter/src/main");

    }
}
