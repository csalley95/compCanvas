package bsu.comp152;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
        private Image pict;
        private int xLoc;
        private int yLoc;
        private int changeX;

    @Override
    public void start(Stage primaryStage) {
        var path = getClass().getResource("fighter-idle-1.png");
        pict = new Image(path.toString());
        Canvas drawingArea = new Canvas(800,800);
        GraphicsContext drawer = drawingArea.getGraphicsContext2D();    //allows to to draw on the canvas
        xLoc = 200;
        yLoc = 300;
        drawer.drawImage(pict, xLoc,yLoc);
        VBox organizer = new VBox();
        Button Quit = new Button("Quit");
        EventHandler<ActionEvent> buttonResponder = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        };
        Quit.setOnAction(buttonResponder);
        organizer.getChildren().add(Quit);
        organizer.getChildren().add(drawingArea);
        Scene windowContents = new Scene(organizer);
        windowContents.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode().toString());
                if (event.getCode() == LEFT|| event.getCode() == KeyCode.NUMPAD4) {
                    changeX = -2;
                }
                else if (event.getCode() == RIGHT || event.getCode() == KeyCode.NUMPAD6) {
                    changeX = 2;
                }
                event.consume();
            }
        });
        windowContents.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent key){if (key.getCode() == LEFT|| key.getCode() == KeyCode.NUMPAD4)
                changeX=0;
            else if (key.getCode() ==KeyCode.RIGHT|| key.getCode() == KeyCode.NUMPAD6)
                changeX = 0;
        }});
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext drawer = drawingArea.getGraphicsContext2D();
                xLoc += changeX;
                //yLoc += changeY;
                drawer.drawImage(pict, xLoc, yLoc);
            }
        };
        animator.start();
        primaryStage.setScene(windowContents);
        primaryStage.show();
    }
}