package services;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//Класс содержит методы для генерации необходимой информации для заполнения файловв
public class InfoGenerator {

    public InfoGenerator() {}

    //массив info заполняется полученными данными
    public String[] generateInfo(){
        String[] info = {generateDate(), generateLatin(), generateRussian(), generateRandomInt(), generateRandomFloat()};
        return info;
    }

    //метод возвращает случайную дату за последние 5 лет
    private String generateDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Random rand = new Random();
        Calendar calendar = new GregorianCalendar(2017, 0, 1);
        long startMillis = calendar.getTimeInMillis();
        calendar = new GregorianCalendar(2022, 9,26);
        long endMillis = calendar.getTimeInMillis();
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis+1);
        Date randomDate = new Date(randomMillis);

        return formatter.format(randomDate);
    }

    //метод возвращает случайный набор 10 латинских символов
    private String generateLatin(){
        String latinAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        char[] array = new char[10];
        for(int i=0; i<10; i++){
            array[i]=latinAlphabet.charAt(random.nextInt(latinAlphabet.length()));
        }
        String latin = new String(array);
        return latin;
    }

    //метод возвращает случайный набор 10 русских символов
    private String generateRussian(){
        String russianAlphabet = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
        Random random = new Random();
        char[] array = new char[10];
        for(int i=0; i<10; i++){
            array[i]=russianAlphabet.charAt(random.nextInt(russianAlphabet.length()));
        }
        String russian = new String(array);
        return russian;
    }

    //метод возвращает случацное целочисленное число от 1 до 100 000 000
    private String generateRandomInt(){
        Random random = new Random();
        int randomInt=random.nextInt(100000000)+1;
        while(randomInt%2!=0){
            randomInt=random.nextInt(100000000)+1;
        }
        return String.valueOf(randomInt);
    }

    //метод возвращает случайное положительное число  с 8 знаками после запятой от 1 до 20
    private String generateRandomFloat(){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat formattedDouble = new DecimalFormat("#0.00000000", otherSymbols);
        Random random = new Random();
        double randomFloat=(1 + random.nextDouble()*(20-1));
        return formattedDouble.format(randomFloat);
    }
}
