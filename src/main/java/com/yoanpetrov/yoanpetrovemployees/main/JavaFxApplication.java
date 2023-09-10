package com.yoanpetrov.yoanpetrovemployees.main;

import com.yoanpetrov.yoanpetrovemployees.controllers.AppController;
import com.yoanpetrov.yoanpetrovemployees.events.StageReadyEvent;
import com.yoanpetrov.yoanpetrovemployees.listeners.StageInitializer;
import com.yoanpetrov.yoanpetrovemployees.services.CsvService;
import com.yoanpetrov.yoanpetrovemployees.services.EmployeeService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * JavaFX {@code Application} class. Responsible for starting the JavaFX UI
 * and initializing the application context.
 */
public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    /**
     * Initializes the application context and registers the needed beans.
     */
    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                ac -> {
                    ac.registerBean(Application.class, () -> this);
                    ac.registerBean(StageInitializer.class);
                    ac.registerBean(AppController.class);
                    ac.registerBean(EmployeeService.class);
                    ac.registerBean(CsvService.class);
                };

        applicationContext = new SpringApplicationBuilder(ApplicationRunner.class)
                .sources(ApplicationRunner.class)
                .initializers(initializer)
                .run();
    }

    /**
     * Starts the JavaFX app by publishing a {@code StageReadyEvent}.
     *
     * @param stage the main {@code Stage} of the app.
     */
    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    /**
     * Stops the JavaFX app, closing the application context and the UI.
     */
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
