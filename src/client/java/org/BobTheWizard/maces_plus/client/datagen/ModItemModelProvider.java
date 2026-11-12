package org.BobTheWizard.maces_plus.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

import org.BobTheWizard.maces_plus.ModItems;

/**
 * Generates the item model and item definition JSON for each mace.
 *
 * <p>{@link Models#HANDHELD_MACE} is the model vanilla's own mace uses, so the
 * items are held and swung the same way.
 */
public class ModItemModelProvider extends FabricModelProvider {

    public ModItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // This mod adds no blocks yet.
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ModItems.STONE_MACE, Models.HANDHELD_MACE);
        generator.register(ModItems.GOLDEN_MACE, Models.HANDHELD_MACE);
        generator.register(ModItems.DIAMOND_MACE, Models.HANDHELD_MACE);
        generator.register(ModItems.NETHERITE_MACE, Models.HANDHELD_MACE);
    }
}
