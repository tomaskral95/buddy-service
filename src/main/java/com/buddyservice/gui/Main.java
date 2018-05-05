package com.buddyservice.gui;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.cache.CacheManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-connection-context.xml");
    private static Student loggedStudent;
    private static CacheManager<String> cacheManager;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("graphics/fxml/logIn.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

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
