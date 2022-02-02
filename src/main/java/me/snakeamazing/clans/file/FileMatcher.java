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

    private final Map<String, YAMLFile> files = new HashMap<>();

    public void setup() {
        files.put("config", new YAMLFile(plugin, "config"));
        files.put("clans", new YAMLFile(plugin, "clans"));
    }

    public YAMLFile getFile(String key) {
        return files.get(key);
    }
}