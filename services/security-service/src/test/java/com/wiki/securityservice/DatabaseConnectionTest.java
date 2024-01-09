package com.wiki.securityservice;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
@SpringBootTest
@SpringJUnitConfig
public class DatabaseConnectionTest {
    @Autowired
    private DataSource dataSource;
    @Test
    public void testDatabaseConnection() {
        // Intenta obtener una conexión de la fuente de datos
        try (Connection connection = dataSource.getConnection()) {
            // La conexión se obtuvo correctamente
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            // Error al obtener la conexión
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
