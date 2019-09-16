package com.company;

import java.sql.*;
import java.util.Scanner;


public class Main {
    static Connection co;

    static void output_logs() {
        String query = "SELECT * \n FROM logs";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1));
                System.out.print('|' + resultSet.getString(2) + '|');
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Logs output exception: " + e);
        }
    }

    static void logs(String query) {
        query = query.replace("\'", "\'\'");
        String query1 = "INSERT INTO logs(query,date) VALUES ('" + query + "',CURRENT_TIMESTAMP)";

        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            System.out.println("sql logs are wrong " + e);
        }
        return;
    }

    static void users() {
        String users = "Select name \n from users";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(users);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(users);

    }

    static void projects() {
        String projects = "Select name \n from projects";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(projects);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(projects);
    }

    static void tasks_of_project() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название проекта");
        String buffer = scanner.nextLine();
        String tasks = "Select t.theme, t.type, t.priority, u.name, p.name,t.description \n from projects p, tasks t, users u \n" +
                "where p.project_id=t.project_id \n and t.user_id=u.user_id \n and p.name='" + buffer + "'";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(tasks);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1));
                System.out.print('|' + resultSet.getString(2) + '|');
                System.out.print(resultSet.getInt(3));
                System.out.print('|' + resultSet.getString(4));
                System.out.print('|' + resultSet.getString(5));
                System.out.println('|' + resultSet.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(tasks);
    }

    static void tasks_of_user() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите имя пользователя");
        String buffer = scanner.nextLine();
        String tasks = "Select t.theme, t.type, t.priority, u.name, p.name,t.description \n from projects p, tasks t, users u \n" +
                "where p.project_id=t.project_id \n and t.user_id=u.user_id \n and u.name='" + buffer + "'";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(tasks);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.print('|' + resultSet.getString(2));
                System.out.print('|' + resultSet.getInt(3));
                System.out.print('|' + resultSet.getString(4));
                System.out.print('|' + resultSet.getString(5));
                System.out.print('|' + resultSet.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(tasks);
    }

    static void new_user() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите пользователя на добавление");
        String buffer = scanner.nextLine();
        String user = "INSERT INTO users(name) \n VALUES('" + buffer + "')";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(user);
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(user);
    }

    static void new_project() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название проекта на добавление");
        String buffer = scanner.nextLine();
        String project = "INSERT INTO projects(name) \n VALUES('" + buffer + "')";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(project);
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(project);

    }

    static void new_task() {
        boolean key = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите проект");
        String buffer = scanner.nextLine();
        int project_id = -1;
        int name_id = -1;
        String query = "SELECT name, project_id \n from projects";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (buffer.equals(resultSet.getString(1)))
                    project_id = resultSet.getInt(2);
                else {
                    System.out.println("такого проекта не существует");
                    key = false;
                    break;
                }
            }
            if (!key) return;
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        System.out.println("введите имя пользователя");
        buffer = scanner.nextLine();
        query = "SELECT name, user_id \n from users";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (buffer.equals(resultSet.getString(1)))
                    name_id = resultSet.getInt(2);
                else {
                    System.out.println("такого пользователя не существует");
                    key = false;
                    break;
                }
            }
            if (!key) return;
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }

        String task = "INSERT INTO tasks(project_id, user_id,theme,type,priority,description)" +
                "VALUES ('" + project_id + "','" + name_id;

        System.out.println("введите тему");
        buffer = scanner.nextLine();
        task += "','" + buffer;

        System.out.println("введите тип");
        buffer = scanner.nextLine();
        task += "','" + buffer;

        System.out.println("введите приоритет");
        buffer = scanner.nextLine();
        task += "','" + buffer;

        System.out.println("введите описание");
        buffer = scanner.nextLine();
        task += "','" + buffer + "')";

        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(task);
            System.out.println("добавление успешно выполнено");
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(task);
    }


    static void delete_project() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название проекта на удаление");
        String buffer = scanner.nextLine();
        int project_id = -1;
        String query = "SELECT name, project_id \n from projects";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (buffer.equals(resultSet.getString(1)))
                    project_id = resultSet.getInt(2);
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        query = "DELETE FROM tasks \n where task_id='" + buffer + "'";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
            System.out.println("удаление успешно выполнено");
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(query);

        query = "DELETE FROM projects \n where project_id='" + project_id + "'";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(query);
    }

    static void delete_task() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите id задачи на удаление");
        String buffer = scanner.nextLine();
        String query = "DELETE FROM tasks \n where task_id='" + buffer + "'";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
            System.out.println("удаление успешно выполнено");
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(query);

    }

    static void delete_user() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите пользователя на удаление");
        String buffer = scanner.nextLine();
        int user_id = -1;
        String query = "SELECT name, user_id \n from users";
        try {
            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (buffer.equals(resultSet.getString(1)))
                    user_id = resultSet.getInt(2);
            }
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        query = "UPDATE tasks SET user_id = NULL \n WHERE user_id ='" + buffer + "'";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(query);

        query = "DELETE FROM users \n WHERE user_id='" + user_id + "'";
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
            System.out.println("удаление прошло успешно");
        } catch (SQLException e) {
            System.out.println("sql quary is wrong " + e);
        }
        logs(query);
    }


    public static void main(String[] args) {
        String DB_Driver = "org.sqlite.JDBC";
        //String DB_URL= "jdbc:sqLite:C:\\Users\\Ammonium\\IdeaProjects\\EPUM\\users.db";
        System.out.println("введите путь к базе данных:");
        Scanner scanner = new Scanner(System.in);
        String DB_URL = "jdbc:sqLite:" + scanner.nextLine();
        try {
            Class.forName(DB_Driver);
            co = DriverManager.getConnection(DB_URL);
            System.out.println("Connected");
            boolean key = true;

            while (key) {
                System.out.println("Для добавления пользователя нажмите - 1");
                System.out.println("Для добавления проекта нажмите - 2");
                System.out.println("Для добавления задачи нажмите - 3");
                System.out.println("Для удаления пользователя нажмите - 4");
                System.out.println("Для удаления проекта нажмите - 5");
                System.out.println("Для удаления задачи нажмите -6");
                System.out.println("Для просмотра всех пользователей нажмите - 7");
                System.out.println("Для просмотра всех проектов нажмите - 8");
                System.out.println("Для просмотра всех задач в проекте нажмите - 9");
                System.out.println("Для просмотра всех задач конкретного пользователя нажмите - 0");
                System.out.println("Для выхода нажмите - e");
                System.out.println("Для просмотра логов напишите - logs");
                switch (scanner.nextLine()) {
                    case "1": {
                        new_user();
                        break;
                    }
                    case "2": {
                        new_project();
                        break;
                    }
                    case "3": {
                        new_task();
                        break;
                    }
                    case "4": {
                        delete_user();
                        break;
                    }
                    case "5": {
                        delete_project();
                        break;
                    }
                    case "6": {
                        delete_task();
                        break;
                    }
                    case "7": {
                        users();
                        break;
                    }
                    case "8": {
                        projects();
                        break;
                    }
                    case "9": {
                        tasks_of_project();
                        break;
                    }
                    case "0": {
                        tasks_of_user();
                        break;
                    }
                    case "e": {
                        key = false;
                        break;
                    }
                    case "logs": {
                        System.out.println("logs:");
                        output_logs();
                        break;
                    }
                    default: {
                        System.out.println("неверная команда");
                        break;
                    }
                }
            }


        } catch (ClassNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        } finally {
            try {
                co.close();
                System.out.println("соединение закрыто");
            } catch (SQLException e) {
                System.out.println("close goes wrong " + e);
            }
        }
    }

}
