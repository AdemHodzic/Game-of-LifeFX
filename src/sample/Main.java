package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static MainGrid grid = new MainGrid();
    private static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setScene(grid.getScene());
        window.setTitle("Game Of Life");
        window.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateMain();
            }
        };
        timer.start();
        window.setOnCloseRequest(e -> {
            timer.stop();
            window.close();
        });

    }

    //Updates scene every frame
    private static void updateMain(){
        grid.update();
        window.setScene(grid.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
