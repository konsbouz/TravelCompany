package com.travelcompany.eshop.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.exit;

public class DBConnectionService {


        private static final String url = "jdbc:mysql://localhost:3306/travelcompany";
        private static final String username = "root";
        private static final String password = "1818";
        private static final Logger logger = LoggerFactory.getLogger(DBConnectionService.class.getName());

        public static void LoadDriver() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("Driver has been loaded successfully");
        }


        public static void GetDriverInfo() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);

                if (connection != null) {
                    DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                    System.out.println("Driver name: " + dm.getDriverName());
                    System.out.println("Driver version: " + dm.getDriverVersion());
                    System.out.println("Product name: " + dm.getDatabaseProductName());
                    System.out.println("Product version: " + dm.getDatabaseProductVersion());
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        public static Connection getConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);
               // logger.info("Connected to database successfully");

            } catch (SQLException ex) {
                logger.error("Error while retrieving database connection.", ex);
                exit(-1);
            }
            return connection;
        }

    }

