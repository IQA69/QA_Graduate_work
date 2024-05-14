package ru.netology.webservice.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    public static void cleanDatabase() throws SQLException {
        String deleteOrderEntity = "delete from order_entity;";
        String deletePaymentEntity = "delete from payment_entity;";
        String deleteCreditEntity = "delete from credit_request_entity;";

        try (
                Connection connectionMysql = DriverManager.getConnection(url, user, password);

                PreparedStatement statementOrderEntity = connectionMysql.prepareStatement(deleteOrderEntity);
                PreparedStatement statementPaymentEntity = connectionMysql.prepareStatement(deletePaymentEntity);
                PreparedStatement statementCreditEntity = connectionMysql.prepareStatement(deleteCreditEntity);
        ) {
            statementOrderEntity.executeUpdate();
            statementPaymentEntity.executeUpdate();
            statementCreditEntity.executeUpdate();
        }
    }

    @SneakyThrows
    public static String getCardRequestStatus() {
        var codeSQL = "SELECT status FROM payment_entity;";
        return runQuery(codeSQL);
    }
    @SneakyThrows
    public static String getCreditRequestStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity;";
        return runQuery(codeSQL);
    }

    @SneakyThrows
    public static String getOrderCount() {
        var codeSQL = " SELECT COUNT(*) FROM order_entity;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        Long count = runner.query(conn, codeSQL, new ScalarHandler<>());
        return Long.toString(count);
    }

    @SneakyThrows
    private static String runQuery(String query) {
        var runner = new QueryRunner();
        var connection = DriverManager.getConnection(url, user, password);
        return runner.query(connection, query, new ScalarHandler<>());
    }
}