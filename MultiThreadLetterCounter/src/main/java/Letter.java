public class Letter {

    private String letter;
    private int timesAppeared;
    private double frequency;

    public Letter(String letter) {
        this.letter = letter;
        this.timesAppeared = 1;
    }

    public void incrementTimesAppeared() {
        timesAppeared++;
    }

    public String getLetter() {
        return letter;
    }

    public Integer getTimesAppeared() {
        return timesAppeared;
    }

    public double getFrequency() {
        return frequency;
    }

    public void updateFrequency(Integer allNumbers) {
        //rounding numbers to fixed decimal places
        double freq = ((double) (timesAppeared) / (double) allNumbers) * 10000;
        freq = Math.round(freq);
        freq = freq / 100;
        this.frequency = freq;
    }

    @Override
    public String toString() {
        return this.letter + " appeared -> " + timesAppeared + " times, "
                + "frequency -> " + this.frequency + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Letter) {
            return ((Letter) o).letter.equals(this.letter);
        } else return false;
    }

}
