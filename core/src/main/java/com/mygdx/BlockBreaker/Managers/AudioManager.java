package com.mygdx.BlockBreaker.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static AudioManager instance;

    private Music musicaActual;
    private final Map<String, Music> musicas;
    private final Map<String, Sound> sonidos;

    private AudioManager() {
        musicas = new HashMap<>();
        sonidos = new HashMap<>();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void cargarMusica(String key, String path) {
        Music musica = Gdx.audio.newMusic(Gdx.files.internal(path));
        musicas.put(key, musica);
    }

    public void cargarSonido(String key, String path) {
        Sound sonido = Gdx.audio.newSound(Gdx.files.internal(path));
        sonidos.put(key, sonido);
    }

    public void reproducirMusica(String key, boolean loop) {
        if (musicaActual != null) {
            musicaActual.stop();
            musicaActual.dispose();
        }

        musicaActual = musicas.get(key);
        if (musicaActual != null) {
            musicaActual.setLooping(loop);
            musicaActual.play();
            musicaActual.setVolume(.2f);
        }
    }

    public void reproducirSonido(String key) {
        Sound sonido = sonidos.get(key);
        if (sonido != null) {
            sonido.play(.12f);
        }
    }

    public void dispose() {
        if (musicaActual != null) {
            musicaActual.dispose();
        }
        for (Music cancion: musicas.values()) {
            cancion.dispose();
        }
        for (Sound sonido: sonidos.values()) {
            sonido.dispose();
        }
        musicas.clear();
        sonidos.clear();
    }
}
