package org.BobTheWizard.maces_plus.client.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import org.BobTheWizard.maces_plus.ModItems;

/**
 * Generates the recipes. Stone, golden and diamond maces are their material's
 * block on top of a two-stick shaft; the netherite mace is a smithing-table
 * upgrade of the diamond one, the way vanilla handles netherite gear.
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                offerMace(ModItems.STONE_MACE, Items.STONE);
                offerMace(ModItems.GOLDEN_MACE, Items.GOLD_BLOCK);
                offerMace(ModItems.DIAMOND_MACE, Items.DIAMOND_BLOCK);

                // Diamond mace + netherite upgrade template + netherite ingot,
                // in a smithing table.
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_MACE, RecipeCategory.COMBAT, ModItems.NETHERITE_MACE);
            }

            /**
             * The material's block as the head, over a shaft of two sticks:
             * <pre>
             * X
             * S
             * S
             * </pre>
             */
            private void offerMace(Item mace, ItemConvertible block) {
                createShaped(RecipeCategory.COMBAT, mace)
                        .pattern("X")
                        .pattern("S")
                        .pattern("S")
                        .input('X', block)
                        .input('S', Items.STICK)
                        .criterion(hasItem(block), conditionsFromItem(block))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Maces Plus Recipes";
    }
}
