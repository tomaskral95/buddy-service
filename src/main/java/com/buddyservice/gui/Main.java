package com.buddyservice.gui;

import com.buddyservice.cache.CacheManager;
import com.buddyservice.domain.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-connection-context.xml");
    private static Student loggedStudent;
    private static CacheManager<String> cacheManager;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("graphics/fxml/logIn.fxml"));
        loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        Main.stage = stage;
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Student getLoggedStudent() {
        return loggedStudent;
    }

    public static void setLoggedStudent(Student loggedStudent) {
        Main.loggedStudent = loggedStudent;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static CacheManager<String> getCacheManager() {
        if (cacheManager == null) {
            cacheManager = new CacheManager<>();
        }
        return cacheManager;
    }
}
