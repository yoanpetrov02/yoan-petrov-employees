package com.yoanpetrov.yoanpetrovemployees.listeners;

import com.yoanpetrov.yoanpetrovemployees.events.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Listener that loads the application's UI from an whenever a {@code StageReadyEvent} gets published.
 */
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StageInitializer.class);

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

    /**
     * Loads the app's UI from the FXML file.
     *
     * @param event the {@code StageReadyEvent} that triggered the listener.
     */
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
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            LOGGER.error("An error occurred while loading FXML file.", e);
        }
    }
}
