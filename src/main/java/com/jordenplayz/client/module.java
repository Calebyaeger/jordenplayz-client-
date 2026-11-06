
package com.jordenplayz.client.module;

public class Modules {
    public static final ModuleManager MANAGER = new ModuleManager();

    static {
        MANAGER.register(new Module("Sprint", Module.Category.MOVEMENT) {});
        MANAGER.register(new Module("Fullbright", Module.Category.RENDER) {});
        MANAGER.register(new Module("HUD", Module.Category.RENDER) {});
        MANAGER.register(new Module("Auto Tool", Module.Category.PLAYER) {});
        MANAGER.register(new Module("Status", Module.Category.MISCELLANEOUS) {});
    }
}
