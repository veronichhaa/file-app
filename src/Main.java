import services.DataBaseHandler;
import services.FileService;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        //создаем файлы и заполняем их данными
        fileService.writeInfo();
        int choice = 0;
        while (choice!=4){
            System.out.println("\n1. Объединить файлы в один\n2. Импортировать в СУБД\n3. Сделать запрос в БД\n4. Выйти\n");
            choice = scanner.nextInt();
            switch (choice){
                case 1: {

                    System.out.println("Введите сочетание символов: ");
                    String str = scanner.next();
                    //объединяем файлы и удаляем строку с введенным сочетанием символов
                    fileService.mergeFiles(str);
                    break;
                }
                case 2:{
                    //импортируем данные из файлов в СУБД
                    fileService.importInfo();
                    break;
                }
                case 3:{
                    System.out.println("\n1. Найти сумму всех целых чисел\n2. Найти медиану всех дробных чисел");
                    int answer = scanner.nextInt();
                    try{
                    DataBaseHandler dbHandler = new DataBaseHandler();
                        switch (answer){
                            case 1:{
                                //получаем сумму всех целых чисел
                                System.out.println("Результат: " + dbHandler.getSum());
                                break;
                            }
                            case 2:{
                                //получаем медиану всех дробных чисел
                                System.out.println("Результат: " + dbHandler.getMedian());
                                break;
                            }
                        }
                    }
                    catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                }

            }
        }


    }

}
