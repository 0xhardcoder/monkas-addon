package com.monkas.addon;

import com.monkas.addon.commands.CommandExample;
import com.monkas.addon.hud.HudExample;
import com.monkas.addon.modules.ModuleExample;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Example");
    public static final HudGroup HUD_GROUP = new HudGroup("Example");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Monkas Addon");

        // Modules
        Modules.get().add(new ModuleExample());

        // Commands
        Commands.get().add(new CommandExample());

        // HUD
        Hud.get().register(HudExample.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.monkas.addon";
    }
}
