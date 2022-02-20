package JDBC;

import java.sql.*;

public class DatabaseExample {

    private static Connection connection;// нужен для работы с БД.
    private static Statement statement;// абстракция для создания запроса
    private static PreparedStatement ps;// подготовленный запрос. фича именно баз данных. Мы его создаем, когда мы уже подключились к БД. И от нас БД ожидает что мы создаим запрос и передадим туда аргументы.
    private static String insertStatement = "insert into students (name, score) values (?, ?);";
    private static final String EXAMPLE_CALL = "{call do_something_prc(?,?,?)}";
    private static final String DB_CONNECTION_STRING = "jdbc:sqlite:db/example.db";// db/example.db = - путь к БД
    private static final String CREATE_REQUEST = "create table if not exists students " + "(id integer primary key autoincrement, name text, score integer);";
    private static final String DROP_REQUEST = "drop table if exists students";
    private static final String SIMPLE_INSERT_REQUEST = "insert into students (name, score) values ('Vasya Pupkin'), 80), ('Kolya Morzhov', 90), ('Vitaly Petrov', 100);";


    public static void main(String[] args) {

        try {
            connect();
            createTable();
//            simpleInsertExample();
//            simpleUpdateExample();
//            dropExample();
//            simpleReadExample();
//            notReallyCorrectInsertExample("Radamir", 100);
//            preparedStatementExample("Pavel", 100);
//            simpleReadExample();
//            massInsertExample();
//            massInsertTransactionalExample();
//            massInsertBatchExample();

//            var callableStatement = connection.prepareCall(EXAMPLE_CALL);// предназначен для вызова процедур в базе данных. Т.е. в БД поддерживается создание процедур. Они пишут на SQL и создают процедуру как метод. И мы ее можем вызвать.
//            callableStatement.setString(1,"dsfkasdf");
//            callableStatement.execute();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            disconnect();
        }
    }

    private static void massInsertBatchExample() throws SQLException {
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        for (int i = 0; i < 5000; i++) {
            ps.setInt(2,i);
            ps.setString(1,"Student #" + i + 1);
            ps.addBatch();// добавь запрос в пакет
        }
        ps.executeBatch();// выполни пакетны запрос.возвращает массив интов.
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void massInsertTransactionalExample() throws SQLException {
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false);// включен по умолчанию. Делает каждый запрос транзакционным, если мы его отключаем то запросы идут в рамках одной транзакции
        for (int i = 0; i < 5000; i++) {
            preparedStatementExample("Student #" + i + 1, i);
        }
//        connection.commit();// Делает коммит, но не включает автокоммит
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void massInsertExample() throws SQLException {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            preparedStatementExample("Student #" + i + 1, i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }


    private static void preparedStatementExample(String name, int score) throws SQLException {
        ps = connection.prepareStatement(insertStatement);// name-> ? score-> ?
        ps.setString(1, name);// 1 - потому что первый вопрос 1
        ps.setInt(2, score);
        ps.executeUpdate();
    }

    private static void notReallyCorrectInsertExample(String name, int score) throws SQLException {
        var count = statement.executeUpdate("insert into students (name, score) values (\'" + name + "\', " + score + ");");
        System.out.printf("Updated %d rows\n", count);
    }

//    private static void simpleReadExample() throws SQLException {
//        try (var resultSet = statement.executeQuery("select * from students;")) {
//            while (resultSet.next()) {
//                System.out.printf("Student id: %d, name: %s, score: %d\n",
//                        resultSet.getInt(1),
//                        resultSet.getString("name"),
//                        resultSet.getInt("score"));
//            }
//        }
//    }

    private static void simpleReadExample() throws SQLException {
        try (var resultSet = statement.executeQuery("select * from students order by name desc;")) {
            while (resultSet.next()) {
                System.out.printf("Student id: %d, name: %s, score: %d\n",
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getInt("score"));
            }
        }
    }

    private static void simpleUpdateExample() throws SQLException {
        var count = statement.executeUpdate("update students set name = 'Alex' where score > 90;");
        System.out.printf("Updated %d rows\n", count);
    }

    private static void dropExample() throws SQLException {
        statement.executeUpdate(DROP_REQUEST);
    }

    private static void simpleInsertExample() throws SQLException {
        var count = statement.executeUpdate(SIMPLE_INSERT_REQUEST);
        System.out.printf("Inserted %d rows\n", count);
    }

    private static void createTable() throws SQLException {
        statement.execute(CREATE_REQUEST);
    }

    private static void connect() throws SQLException {
        /*
        Раньше требовалось проинициализировать драйвер. Вызов метода forName и передавча ему класса заставляет наше приложение загрузить этот класс в память. В java есть так называемая ленивая инициализация, когда мы запускаем приложение, то все все все классы не загружаются сразу в пямять. Загружаются они в память - лениво, то есть не при старте приложения, а по мере необходимости.
           */
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        connection = DriverManager.getConnection(DB_CONNECTION_STRING);// DriverManager - метод из пакета SQL.
        statement = connection.createStatement();// подключились к БД и создали запрос
        ps = connection.prepareStatement(insertStatement);


    }

    private static void disconnect() {

    }
}
