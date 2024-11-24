package com.mygdx.BlockBreaker.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.BlockBreaker.*;
import com.mygdx.BlockBreaker.Managers.AudioManager;
import com.mygdx.BlockBreaker.Screens.Exit.ExitGameAction;
import com.mygdx.BlockBreaker.Screens.ResetProgress.ResetProgressAction;
import com.mygdx.BlockBreaker.Screens.Settings.SettingsAction;
import com.mygdx.BlockBreaker.Screens.StartGame.StartGameAction;

public class MainMenuScreen extends ScreenAdapter {

    private final BlockBreakerGame game;
    private Stage stage;

    // Atributo privado de tipo Strategy (MenuAction)
    private MenuAction currentAction;

    // Constructor que recibe un tipo Strategy y lo asigna
    public MainMenuScreen(BlockBreakerGame game, MenuAction initialAction) {
        this.game = game;
        this.currentAction = initialAction;
    }

    // Metodo encargado de asignar la nueva estrategia
    public void setStrategy(MenuAction newAction) {
        this.currentAction = newAction;
    }

    // Metodo que utiliza el metodo de la interfaz Strategy (Menu Action)
    public void executeCurrentAction() {
        if (currentAction != null) {
            currentAction.execute();
        }
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        AudioManager.getInstance().reproducirMusica("musica-1", true);

        // Estilos para los botones
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        skin.add("default", buttonStyle);

        // Tabla para ordenar los botones
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Botones
        createButton("Iniciar Juego", new StartGameAction(game), table, skin);
        createButton("Reiniciar Progreso", new ResetProgressAction(game), table, skin);
        createButton("Ajustes", new SettingsAction(game), table, skin);
        createButton("Salir", new ExitGameAction(game), table, skin);
    }

    private void createButton(String text, MenuAction action, Table table, Skin skin) {
        TextButton button = new TextButton(text, skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Usar la estrategia asignada
                setStrategy(action);
                executeCurrentAction();
            }
        });
        table.add(button).pad(10).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

