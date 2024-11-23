package com.mygdx.BlockBreaker.Screens.StartGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Managers.AudioManager;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Managers.UIManager;
import com.mygdx.BlockBreaker.Screens.MenuAction;
import com.mygdx.BlockBreaker.Screens.Settings.SettingsAction;
import com.mygdx.BlockBreaker.Screens.Settings.VolverMenuPrincipal.VolverMenuPrincipalAction;

public class StartGameScreen extends ScreenAdapter {
    private GameManager gameManager;
    private UIManager uiManager;
    private final BlockBreakerGame game;
    private boolean paused = false;
    private ShapeRenderer shape;
    private BitmapFont font;
    private SpriteBatch batch;
    private Stage stage;

    public StartGameScreen(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        gameManager = GameManager.getInstance();
        gameManager.initializeLevel(1);
        uiManager = new UIManager(gameManager);
        shape = new ShapeRenderer();
        font = new BitmapFont();
        batch = new SpriteBatch();
        AudioManager.getInstance().reproducirMusica("musica-2", true);

        // Para el menu de pausa.
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

        createButton("Volver al menu principal", new VolverMenuPrincipalAction(game) , table, skin);
    }

    @Override
    public void render(float delta) {
        // Pausar el juego con Escape
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
        }

        if (paused) {
            renderPauseMenu();
            return;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameManager.updateGameState();
        gameManager.checkCollisions();

        // Dibujar interfaz de usuario
        uiManager.renderUI();

        // Dibujar elementos del juego
        gameManager.drawGameObjects(shape);
    }

    private void renderPauseMenu() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // batch.begin();
       // font.getData().setScale(3);
       // font.draw(batch, "PAUSA", Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f);
       // batch.end();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
    public void dispose() {
        shape.dispose();
        gameManager.dispose();
        uiManager.dispose();
    }
}

