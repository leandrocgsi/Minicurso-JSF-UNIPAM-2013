package br.com.jsf.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    public static final String HIBERNATE_SESSION = "hibernate_session";

    static {
        try {
            System.out.println("Tentando configurar a SF");            
                Configuration configuration = new Configuration().configure("br/com/jsf/util/hibernate.cfg.xml");
                ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();	    
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
            
            System.out.println("Configuração da SF beleza");
        } catch (Throwable ex) {
            System.err.println("Ocorreu um erro na criação da SessionFactory. " + ex);            
                throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}