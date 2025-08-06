package fr.alasdiablo.mods.lib.data;

import net.minecraft.block.Block;
import net.minecraft.data.loottable.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.*;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public abstract class CustomBlockLootSubProvider extends BlockLootTableGenerator {

    protected CustomBlockLootSubProvider(Set<Item> explosionImmuneItems, FeatureSet requiredFeatures, Map<RegistryKey<LootTable>, LootTable.Builder> lootTables, RegistryWrapper.WrapperLookup registries) {
        super(explosionImmuneItems, requiredFeatures, lootTables, registries);
    }

    protected CustomBlockLootSubProvider(Set<Item> explosionImmuneItems, FeatureSet requiredFeatures, RegistryWrapper.WrapperLookup registries) {
        super(explosionImmuneItems, requiredFeatures, registries);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        this.generate();
        Set<RegistryKey<LootTable>> set = new HashSet<>();

        for (Block block : Registries.BLOCK) {
            if (block.isEnabled(this.requiredFeatures)) {
                Optional<RegistryKey<LootTable>> optionalRegistryKey = block.getLootTableKey();

                if (optionalRegistryKey.isPresent()) {
                    RegistryKey<LootTable> lootTableKey = optionalRegistryKey.get();
                    if (set.add(lootTableKey)) {
                        LootTable.Builder builder = this.lootTables.remove(lootTableKey);
                        if (builder != null) {
                            lootTableBiConsumer.accept(lootTableKey, builder);
                        }
                    }
                }
            }
        }

        if (!this.lootTables.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.lootTables.keySet());
        }
    }
}
