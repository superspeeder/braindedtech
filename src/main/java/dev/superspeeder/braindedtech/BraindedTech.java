package dev.superspeeder.braindedtech;

import com.mojang.logging.LogUtils;
import dev.superspeeder.braindedtech.event.mod.BDRegisterEvent;
import dev.superspeeder.braindedtech.material.Material;
import dev.superspeeder.braindedtech.registry.BDRegistryManager;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

@Mod(BraindedTech.MODID)
public class BraindedTech {
    public static final String MODID = "braindedtech";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BraindedTech(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // I want this to run basically last, as this is the last event before real registries start (so this is where my custom registries run in order to load at the right time).
        modEventBus.addListener(EventPriority.LOWEST, this::onModifyRegistries);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void onModifyRegistries(final ModifyRegistriesEvent event) {
        BDRegistryManager.registerAll();
    }
}
