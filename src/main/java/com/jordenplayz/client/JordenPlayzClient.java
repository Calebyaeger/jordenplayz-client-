
package com.jordenplayz.client;

import com.jordenplayz.client.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class JordenPlayzClient implements ClientModInitializer {
    public static final String MOD_ID = "jordenplayzclient";

    private static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.jordenplayzclient.open_gui",
                InputUtil.Type.KEYSYM,
                InputUtil.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.create(Identifier.of(MOD_ID, "general"))
            )
        );

        ConfigManager.load();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGuiScreen());
                }
            }
        });
    }
}
