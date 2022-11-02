package services;

import models.Info;

import java.sql.*;

//класс отвечает за работу с БД
public class DataBaseHandler {

    Connection dbConnection;

    public DataBaseHandler() throws SQLException, ClassNotFoundException {
        this.dbConnection = getDbConnection();
    }

    //подключаемся к БД
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/task1?allowLoadLocalInfile=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "root");
        return dbConnection;
    }

    //метод возвращает медиану всех дробных чисел
    public double getMedian(){
        double median=0;
        try {
            String insert = "SELECT round(AVG(randomFloat), 8) from\n" +
                    "(\n" +
                    " SELECT *, ROW_NUMBER() OVER (ORDER BY randomFloat desc) as desc_float,\n" +
                    "\t\t\tROW_NUMBER() OVER (ORDER BY randomFloat asc) as asc_float\n" +
                    " from info\n" +
                    ") AS a\n" +
                    "WHERE asc_float in (desc_float, desc_float+1, desc_float-1)";
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            ResultSet result = prSt.executeQuery();

            while (result.next())
            {
                median = result.getDouble(1);
            }

        }
        catch (SQLException e){
            System.out.println("Error: " + e);
        }
        return median;
    }

    //метод возвращает сумму всех целых чисел
    public long getSum(){
        long sum=0;
        try {
            String insert = "SELECT SUM(randomInt) FROM info";
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            ResultSet result = prSt.executeQuery();

            while (result.next())
            {
               sum = result.getLong(1);
            }

        }
        catch (SQLException e){
            System.out.println("Error: " + e);
        }
        return sum;
    }

    //метод импортирует данные из текстовых фалов в таблицу БД
    public void addToDB(String filename){
        try {
            String insert = "SET GLOBAL local_infile=1";
            PreparedStatement prSt1 = dbConnection.prepareStatement(insert);
            prSt1.executeUpdate();
            insert = "LOAD DATA LOCAL INFILE 'C://Program Files//MySQL//MySQL Server 8.0//data//"+
                    filename+"' INTO TABLE info FIELDS TERMINATED BY '||' LINES TERMINATED BY '\\n'" +
                    " (date, latin, russian, randomInt, randomFloat)";
            PreparedStatement prSt2 = dbConnection.prepareStatement(insert);
            prSt2.executeUpdate();
            System.out.println("Файл "+filename+" импортирован успешно!");
        }
        catch (SQLException | ClassCastException e){
            System.out.println("Error: " + e);
        }

    }

}

