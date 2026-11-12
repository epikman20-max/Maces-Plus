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
 * Generates the crafting recipes. Every mace is its material's block on top of
 * a two-stick shaft.
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
                offerMace(ModItems.NETHERITE_MACE, Items.NETHERITE_BLOCK);
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
