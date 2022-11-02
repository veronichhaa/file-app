package services;

import models.Info;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

//класс содержит методы для работы с файлами
public class FileService {

    public FileService(){}


    //метод импортирует данные из текстовых фалов в таблицу БД
    public void importInfo(){
        try{
            DataBaseHandler dbHandler = new DataBaseHandler();
            File dir = new File("C://Program Files//MySQL//MySQL Server 8.0//data//");
            //получаем список текстовых файлов
            List<File> fileList = Arrays.asList(dir.listFiles());
            int importCounter = 0;
            //проходим по каждому файлу и отправляем его в БД
            for (File file : fileList) {
                dbHandler.addToDB(file.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //метод объединяет текстовые файлы в 1 и удаляет строки с сочетанием символов str
    public void mergeFiles(String str){
        try{

            int counter=0;

            File dir = new File("C://Program Files//MySQL//MySQL Server 8.0//data//");
            List<File> fileList = Arrays.asList(dir.listFiles());
            File mergedData = new File("resources/mergedData/mergedData.txt");
            //создаем файл, в который будем записывать данные
            mergedData.createNewFile();
            FileWriter fileWriter = new FileWriter(mergedData);
            //проходим по списку файлов
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();
                while (line != null) {
                    //если строка содержит сочетание символов str, то не записываем её в файл
                    if(line.contains(str)){
                        counter++;
                    }
                    else{
                        //записываем в новый файл каждую строку
                        fileWriter.write(line+"\n");
                    }
                    line = reader.readLine();
                }
            }
            fileWriter.close();
            System.out.println("Файлы успешно объединены");
            System.out.println("Удалено строк, содержащих "+str+": "+ counter);
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }

    }

    //создает текстовые файлы и заполняет их сгенерированными данными
    public void writeInfo(){
        int counter = 1;
        InfoGenerator infoGenerator = new InfoGenerator();
        InfoFormatter infoFormatter = new InfoFormatter();
        try{
            //создаем в ходе цикла 100 файлов
            while (counter<101){
                String fileName = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\data\\data_"+counter+".txt";
                File file = new File(fileName);
                file.createNewFile();
                PrintWriter pw = new PrintWriter(file);
                //заполняем каждый файл 100 000 строками
                for(int i=0; i<100000; i++){
                    //создаем объект Info и сразу заполняем его новыми данными
                    Info info = new Info(infoGenerator.generateInfo());
                    //преобразуем данные объекта в строку и записываем в файл
                    pw.println(infoFormatter.getFormatInfo(info));
                }
                counter++;
                pw.close();
            }
            System.out.println("Файлы созданы и данные записаны");
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
    }
}
