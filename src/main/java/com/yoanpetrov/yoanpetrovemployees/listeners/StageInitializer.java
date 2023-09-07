package com.yoanpetrov.yoanpetrovemployees.listeners;

import com.yoanpetrov.yoanpetrovemployees.events.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    public StageInitializer(
            @Value("${spring.application.ui.title}") String applicationTitle,
            @Value("classpath:/ui.fxml") Resource fxml,
            ApplicationContext ac) {
        this.applicationTitle = applicationTitle;
        this.fxml = fxml;
        this.ac = ac;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        try {
            URL url = fxml.getURL();
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(ac::getBean);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
