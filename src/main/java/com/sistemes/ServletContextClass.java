package com.sistemes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ServletContextClass implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        CadenaConnexio cadena = new CadenaConnexio();
        cadena.setServidor("localhost");
        cadena.setPort("3050");
        cadena.setDatabase("C:/DATABASE.IB");
        cadena.setUsuari("SYSDBA");
        cadena.setPassword("covertheworld");

        Application.connexio = new ClassConnexio(cadena);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            Application.connexio.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
