package com.mygdx.BlockBreaker.Screens;

import com.badlogic.gdx.ScreenAdapter; // Para extender las clases de pantalla.
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; // Maneja los elementos de la interfaz.
import com.badlogic.gdx.scenes.scene2d.ui.TextButton; // Botones con texto.
import com.badlogic.gdx.scenes.scene2d.ui.Table; // Organiza elementos en pantalla.
import com.badlogic.gdx.scenes.scene2d.ui.Skin; // Define los estilos visuales.
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener; // Maneja clics en botones.
import com.badlogic.gdx.utils.viewport.ScreenViewport; // Vista para el Stage.
import com.badlogic.gdx.Gdx; // Acceso global a recursos y utilidades.
import com.badlogic.gdx.graphics.GL20; // Para limpiar la pantalla.
import com.badlogic.gdx.graphics.g2d.BitmapFont; // Fuente para textos.
import com.badlogic.gdx.graphics.Color; // Colores para botones y textos.
import com.badlogic.gdx.Input; // Para detectar teclas.
import com.badlogic.gdx.InputProcessor; // Si gestionas entradas manualmente.
import com.mygdx.BlockBreaker.*;

public class MainMenuScreen extends ScreenAdapter {
    private final BlockBreakerGame game;
    private Stage stage;

    public MainMenuScreen(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Crear estilos para los botones
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        skin.add("default", buttonStyle);

        // Crear la tabla
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Crear botones con sus acciones
        createButton("Iniciar Juego", new StartGameAction(game), table, skin);
        //createButton("Ajustes", new OpenSettingsAction(), table, skin);
        //createButton("Créditos", new ShowCreditsAction(), table, skin);
       // createButton("Salir", new ExitGameAction(), table, skin);
    }

    private void createButton(String text, MenuAction action, Table table, Skin skin) {
        TextButton button = new TextButton(text, skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.execute();
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
    public void hide() {
        stage.dispose();
    }
}
