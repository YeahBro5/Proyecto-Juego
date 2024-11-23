package com.mygdx.BlockBreaker.Screens.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MenuAction;
import com.mygdx.BlockBreaker.Screens.Settings.SoundVolume.VolumenAction;
import com.mygdx.BlockBreaker.Screens.Settings.VolverMenuPrincipal.VolverMenuPrincipalAction;

public class SettingsScreen extends ScreenAdapter {
    private final BlockBreakerGame game;
    private Stage stage;

    public SettingsScreen(BlockBreakerGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

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

        // Crear botones
        createButton("Volumen", new VolumenAction(game), table, skin);
        createButton("Volver al menu principal", new VolverMenuPrincipalAction(game), table, skin);
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
