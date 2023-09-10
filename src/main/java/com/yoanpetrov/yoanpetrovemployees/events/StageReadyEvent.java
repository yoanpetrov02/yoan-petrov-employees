package com.yoanpetrov.yoanpetrovemployees.events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Event that gets published whenever the applications starts and the main stage is ready for UI loading.
 */
public class StageReadyEvent extends ApplicationEvent {

    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
