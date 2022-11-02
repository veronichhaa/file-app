package models;

//класс отображает данные, представленные в таблице Info в БД
public class Info {

    private String date;
    private String latin;
    private String russian;
    private int randomInt;
    private double randomFloat;

    public Info(String [] values) {
        this.date = values[0];
        this.latin = values[1];
        this.russian = values[2];
        this.randomInt = Integer.valueOf(values[3]);
        this.randomFloat = Double.parseDouble(values[4]);
    }

    public String getDate() {
        return date;
    }

    public String getLatin() {
        return latin;
    }

    public String getRussian() {
        return russian;
    }

    public int getRandomInt() {
        return randomInt;
    }

    public double getRandomFloat() {
        return randomFloat;
    }
}
