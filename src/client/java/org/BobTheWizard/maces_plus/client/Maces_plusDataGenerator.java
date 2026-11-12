package org.BobTheWizard.maces_plus.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import org.BobTheWizard.maces_plus.client.datagen.ModItemModelProvider;
import org.BobTheWizard.maces_plus.client.datagen.ModItemTagProvider;
import org.BobTheWizard.maces_plus.client.datagen.ModLanguageProvider;
import org.BobTheWizard.maces_plus.client.datagen.ModRecipeProvider;

/**
 * Entry point for data generation. Run it with {@code ./gradlew runDatagen};
 * the output lands in {@code src/main/generated}, which the build treats as a
 * resource directory. Do not hand-edit anything under that folder: regenerate
 * instead.
 */
public class Maces_plusDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModItemModelProvider::new);
        pack.addProvider(ModLanguageProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModItemTagProvider::new);
    }
}
