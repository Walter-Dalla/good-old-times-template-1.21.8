package br.com.walter.GoodOldTimes.block;

import br.com.walter.GoodOldTimes.GoodOldTimes;
import br.com.walter.GoodOldTimes.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.function.Function;

public class ModBlocks {
    public static void initialize() {
        registerAllBlocks();
    }

    private static void registerAllBlocks(){
        var listName = new String[]{
            "compressor_block",
            "electric_furnace_block",
            "extractor_block",
            "macerator_block",
            "machine_block"
        };

        var listItems = new LinkedList<Block>();
        for (var blockName : listName) {
            var block = register(blockName, Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS), true);
            listItems.add(block);
        }

        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY).register(entries -> {
            for (var block : listItems) {
                entries.add(block.asItem());
            }
        });
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(GoodOldTimes.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(GoodOldTimes.MOD_ID, name));
    }
}
