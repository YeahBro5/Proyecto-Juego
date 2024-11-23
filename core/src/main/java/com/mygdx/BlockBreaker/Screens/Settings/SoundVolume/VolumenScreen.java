package com.mygdx.BlockBreaker.Screens.Settings.SoundVolume;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Managers.AudioManager;
import com.mygdx.BlockBreaker.Screens.MenuAction;
import com.mygdx.BlockBreaker.Screens.Settings.SettingsAction;

public class VolumenScreen extends ScreenAdapter {
    private BitmapFont font;
    private SpriteBatch batch;
    private String volumenTexto;
    private final BlockBreakerGame game;
    private Stage stage;

    public VolumenScreen(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        volumenTexto = "Volumen: " + (int) (AudioManager.getInstance().getVolumenGlobal() * 100) + "%";


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin();
        skin.add("default", font);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        skin.add("default", buttonStyle);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        createButton("Volver", new SettingsAction(game), table, skin);
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
        // Ajuste volumen
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            AudioManager.getInstance().aumentarVolumen(0.1f);
            volumenTexto = "Volumen: " + (int) (AudioManager.getInstance().getVolumenGlobal() * 100) + "%";
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            AudioManager.getInstance().disminuirVolumen(0.1f);
            volumenTexto = "Volumen: " + (int) (AudioManager.getInstance().getVolumenGlobal() * 100) + "%";
        }

        // Limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar texto
        batch.begin();
        float textWidth = font.getRegion().getRegionWidth();
        float textX = ((Gdx.graphics.getWidth() - textWidth) / 2) + 80;
        float textY = ((Gdx.graphics.getHeight() / 2)) + 50; //
        font.draw(batch, volumenTexto, textX, textY);

        String instrucciones = "Subir volumen: Flecha arriba\nBajar volumen: Flecha abajo";
        float instruccionesX = 665;
        float instruccionesY = 65;

        font.draw(batch, instrucciones, instruccionesX, instruccionesY);

        //
        batch.end();

        // Dibujar el Stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
        stage.dispose();
    }
}


