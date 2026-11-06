
package com.jordenplayz.client.config;

import com.jordenplayz.client.module.Module;
import com.jordenplayz.client.module.Modules;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static Path getConfigPath() {
        return MinecraftClient.getInstance()
            .runDirectory.toPath()
            .resolve("config")
            .resolve("jordenplayzclient.json");
    }

    public static void save() {
        try {
            Path path = getConfigPath();

            Files.createDirectories(path.getParent());

            JsonObject json = new JsonObject();

            for (Module module : Modules.MANAGER.getModules()) {
                json.addProperty(module.getName(), module.isEnabled());
            }

            Files.writeString(path, GSON.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            Path path = getConfigPath();

            if (!Files.exists(path)) {
                return;
            }

            JsonObject json = GSON.fromJson(
                Files.readString(path),
                JsonObject.class
            );

            if (json == null) {
                return;
            }

            for (Module module : Modules.MANAGER.getModules()) {
                if (json.has(module.getName())) {
                    module.setEnabled(
                        json.get(module.getName()).getAsBoolean()
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
