package com.debtapp.javafx.interfaces.impl;

import com.debtapp.javafx.interfaces.PersonBook;
import com.debtapp.javafx.objects.Debtor;
import com.debtapp.javafx.objects.Person;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Arizel on 25.12.2016.
 */
public class DebtorBook implements PersonBook {

    private static final String URL = "jdbc:mysql://localhost:3306/redactordb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INSERT_NEW = "INSERT INTO debtor VALUES (?,?,?,?,?,?)";
    private static final String GET_MAX_ID = "SELECT max(id) FROM debtor";
    private static final String DELETE = "DELETE FROM debtor WHERE id=?";
    private static final String UPDATE = "UPDATE debtor set Name = ?, Phone = ?, Address = ?, Vk = ?, Ip = ? where id = ?";

    private ObservableList<Debtor> debtors = FXCollections.observableArrayList();
    private Driver mysqlDriver;


    public void fillData() {
        try {
            mysqlDriver = new FabricMySQLDriver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from debtor");

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String phone = resultSet.getString("Phone");
                String address = resultSet.getString("Address");
                String vk = resultSet.getString("Vk");
                String ip = resultSet.getString("Ip");

                Debtor debtor = new Debtor(name, phone, address, vk, ip);
                debtor.setId(resultSet.getInt("id"));
                debtors.add(debtor);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(Person person) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW);
            Statement statement1 = connection.createStatement()) {

            String name = person.getName();
            String phone = person.getPhone();
            String address = person.getAddress();
            String vk = person.getVkId();
            String ip = person.getIp();

            ResultSet set = statement1.executeQuery(GET_MAX_ID);
            set.next();
            int id = set.getInt(1) + 1;

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setString(5, vk);
            statement.setString(6, ip);

            statement.execute();

            person.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        debtors.add((Debtor) person);
    }

    @Override
    public void delete(Person person) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, person.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        debtors.remove(person);
    }

    @Override
    public void update(Person person) {
        //for DataBase impl
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            String name = person.getName();
            String phone = person.getPhone();
            String address = person.getAddress();
            String vk = person.getVkId();
            String ip = person.getIp();


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, vk);
            preparedStatement.setString(5, ip);
            preparedStatement.setInt(6, person.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Debtor> getDebtors() {
        return debtors;
    }
}
