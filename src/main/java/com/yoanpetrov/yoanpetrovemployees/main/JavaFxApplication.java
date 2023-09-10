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

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

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

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
