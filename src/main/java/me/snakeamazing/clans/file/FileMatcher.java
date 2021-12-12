package me.snakeamazing.clans.file;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class FileMatcher {

    private final Plugin plugin;

    public FileMatcher(Plugin plugin) {
        this.plugin = plugin;

        setup();
    }

    private final Map<String, FileCreator> files = new HashMap<>();

    public void setup() {
        files.put("config", new FileCreator(plugin, "config"));
        files.put("clans", new FileCreator(plugin, "clans"));
    }

    public FileCreator getFile(String key) {
        return files.get(key);
    }
}