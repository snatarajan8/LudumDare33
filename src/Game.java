/**
 * Created by marc on 8/21/15.
 */

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Set;
import java.util.HashSet;

public class Game extends Application {

    public static final int START_X = 100;
    public static final int START_Y = 500;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 704;

    private Pane levelPane;
    private Player player;
    private Set<CollisionUnit> units;
    private Set<Character> characters;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        levelPane = (Pane) FXMLLoader.load(getClass().getResource("GameBackground.fxml"));
        initUnits();

        levelPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                player.moveHorizontal(false, false);
            } else if (event.getCode() == KeyCode.LEFT) {
                player.moveHorizontal(true, false);
            } else if (event.getCode() == KeyCode.UP) {
                player.jump();
            }
        });
/*
        levelPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.D) {
                player.moveHorizontal(false, true);
            } else if (event.getCode() == KeyCode.A) {
                player.moveHorizontal(true, true);
            }
        });
        */
        levelPane.setOnMousePressed(event -> {
            player.attack();
        });

        Scene scene = new Scene(levelPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            Task updateTask = new Task<Void>() {
                public Void call() throws Exception {
                    while (true) {
                        Platform.runLater(() -> {
                            updateAll();
                        });
                        Thread.sleep(50);
                    }
                }
            };
            Thread thread = new Thread(updateTask);
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            System.out.println("Error.");
        }

        levelPane.requestFocus();
    }

    private void initUnits() {
        units = new HashSet<>();
        characters = new HashSet<>();
        player = new Player(this, START_X, START_Y);
        addUnit(player);
        initWalls();
    }

    private void initWalls() {
        Wall ground = new Wall(0, HEIGHT- 60, WIDTH, 40);
        addUnit(ground);
        addUnit(new Wall(0, 0, 160, 288));
        addUnit(new Wall(0, 288, 96, 64));
        addUnit(new Wall(0, 352, 32, 320));
        addUnit(new Wall(1120, 0, 160, 288));
        addUnit(new Wall(1184, 288, 96, 64));
        addUnit(new Wall(1248, 352, 32, 320));
        addUnit(new Wall(192, 480, 256, 32));
        addUnit(new Wall(480, 608, 96, 32));
        addUnit(new Wall(608, 576, 96, 32));
        addUnit(new Wall(704, 512, 192, 32));
    }

    public Set<CollisionUnit> getCollisionSet() {
        return units;
    }

    public Set<Character> getCharacterSet() {
        return characters;
    }

    private void updateAll() {
        for (Character item : characters) {
            item.update();
        }
    }

    public void addUnit(CollisionUnit unit) {
        ImageView image = unit.getImageView();
        levelPane.getChildren().add(image);
        image.setLayoutX((int)unit.getRectangle().getX());
        image.setLayoutY((int)unit.getRectangle().getY());
        units.add(unit);
        if (unit instanceof Character) {
            characters.add((Character) unit);
        }
    }

    public void removeUnit(CollisionUnit unit) {
        levelPane.getChildren().remove(unit.getImageView());
        units.remove(unit);
        if (unit instanceof Character) {
            characters.remove((Character) unit);
        }
    }
}
