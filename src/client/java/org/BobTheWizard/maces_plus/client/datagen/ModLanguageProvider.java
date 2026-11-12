package org.BobTheWizard.maces_plus.client.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import org.BobTheWizard.maces_plus.ModItemGroups;
import org.BobTheWizard.maces_plus.ModItems;

/**
 * Generates {@code assets/maces_plus/lang/en_us.json}, the display names shown
 * in game. Add other languages by registering more providers with a locale.
 */
public class ModLanguageProvider extends FabricLanguageProvider {

    public ModLanguageProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, "en_us", registries);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add(ModItems.STONE_MACE, "Stone Mace");
        builder.add(ModItems.GOLDEN_MACE, "Golden Mace");
        builder.add(ModItems.DIAMOND_MACE, "Diamond Mace");
        builder.add(ModItems.NETHERITE_MACE, "Netherite Mace");

        builder.add(ModItemGroups.MACES_PLUS_KEY, "Maces Plus");
    }
}
