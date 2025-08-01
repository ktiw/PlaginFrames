package com.KYRLA_Ktiw;

import org.bukkit.plugin.java.JavaPlugin;

public final class PlaginFrame extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FrameIvents(), this);
        getLogger().info("Плагин на рамки включен");
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин на рамки выключен");

    }
}
