package dev.superspeeder.braindedtech;

import com.mojang.logging.LogUtils;
import dev.superspeeder.braindedtech.common.Parameter;
import dev.superspeeder.braindedtech.common.ParameterType;
import dev.superspeeder.braindedtech.event.mod.BDRegisterEvent;
import dev.superspeeder.braindedtech.material.Material;
import dev.superspeeder.braindedtech.part.Part;
import dev.superspeeder.braindedtech.part.PartConfiguration;
import dev.superspeeder.braindedtech.part.PartDefinition;
import dev.superspeeder.braindedtech.registry.BDRegistryManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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

        modEventBus.addListener(this::registerParts);
        modEventBus.addListener(this::onRegister);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // test creating a part based on the "ingot" part

        PartDefinition INGOT = BDRegistryManager.PARTS.resolve(ResourceLocation.fromNamespaceAndPath(MODID, "ingot"));

        Material COPPER = BDRegistryManager.MATERIALS.resolve(ResourceLocation.fromNamespaceAndPath(MODID, "copper"));
        Material RUBBER = BDRegistryManager.MATERIALS.resolve(ResourceLocation.fromNamespaceAndPath(MODID, "rubber"));

        Part copperIngot = new Part(INGOT, COPPER);
        Part rubberBar = new Part(INGOT, RUBBER);

        boolean ciIB = copperIngot.configuration().getParameter("subst_bar_for_ingot");
        boolean rbIB = rubberBar.configuration().getParameter("subst_bar_for_ingot");

        LOGGER.info("Copper uses bar: {}", ciIB);
        LOGGER.info("Rubber uses bar: {}", rbIB);
    }

    private void onModifyRegistries(final ModifyRegistriesEvent event) {
        BDRegistryManager.registerAll();
    }

    private void registerParts(final BDRegisterEvent event) {
        if (event.getRegistryType() == PartDefinition.class) {
            LOGGER.info("Registering parts");

            event.register(ResourceLocation.fromNamespaceAndPath(MODID, "ingot"), new PartDefinition.Builder()
                    .withParameter("subst_bar_for_ingot", new Parameter<>(ParameterType.BOOLEAN, false))
                    .build());
        } else if (event.getRegistryType() == Material.class) {
            LOGGER.info("Registering materials");

            PartDefinition INGOT = BDRegistryManager.PARTS.resolve(ResourceLocation.fromNamespaceAndPath(MODID, "ingot"));

            event.register(ResourceLocation.fromNamespaceAndPath(MODID, "copper"), new Material.Builder()
                    .withPart(new PartConfiguration.Builder(INGOT)
                            .withParameter("subst_bar_for_ingot", false)
                            .build())
                    .withTint(0xD46E1C)
                    .build());

            event.register(ResourceLocation.fromNamespaceAndPath(MODID, "rubber"), new Material.Builder()
                    .withPart(new PartConfiguration.Builder(INGOT)
                            .withParameter("subst_bar_for_ingot", true)
                            .build())
                    .withTint(0x040404)
                    .build());
        }
    }

    private void onRegister(final RegisterEvent event) {
        LOGGER.info("Registering {}", event.getRegistryKey().location());
    }
}
