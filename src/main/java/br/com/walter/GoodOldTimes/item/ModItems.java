package br.com.walter.GoodOldTimes.item;

import br.com.walter.GoodOldTimes.GoodOldTimes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ModItems {

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(GoodOldTimes.MOD_ID, "item_group"));


    public static void registerModItems() {
        GoodOldTimes.LOGGER.info("Registring mod items for " + GoodOldTimes.MOD_ID);

        var allItems = registerAllItems();

        var CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(allItems.getFirst()))
                .displayName(Text.translatable("itemGroup.ic2"))
                .build();
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

    }

    private static List<Item> registerAllItems(){

        var listName = new String[]{
            "electronic_circuit",
            "pe_queimado",
            "advanced_alloy",
            "advanced_circuit",
            "batpack",
            "biofuel_cell",
            "bronze",
            "bronze_dust",
            "carbon_plate",
            "cf_backpack",
            "cf_pellet",
            "cf_sprayer",
            "chainsaw",
            "coalfuel_cell",
            "coal_ball",
            "coal_chunk",
            "coal_dust",
            "coffee",
            "coffee_beans",
            "coffee_powder",
            "cold_coffee",
            "composite_vest",
            "compressed_coal_ball",
            "compressed_plants",
            "copper_axe",
            "copper_boots",
            "copper_cable",
            "copper_chestplate",
            "copper_dust",
            "copper_helmet",
            "copper_hoe",
            "copper_ingot",
            "copper_legs",
            "copper_pickaxe",
            "copper_shovel",
            "copper_swikrd",
            "cropnalizer",
            "dark_coffee",
            "depleted_isotope_cell",
            "diamond_drill",
            "dynamite",
            "dynamite_o_mote",
            "electric_hoe",
            "electric_jetpack",
            "electric_treetap",
            "electric_wrench",
            "electrolyzed_walter_cell",
            "empty_booze_barrel",
            "empty_cell",
            "energy_crystal_1",
            "energy_crystal_2",
            "energy_crystal_3",
            "energy_crystal_4",
            "energy_crystal_5",
            "energy_storage_upgrade",
            "eu_detector_cable",
            "eu_reader",
            "eu_splitter_cable",
            "fertilizer",
            "four_x_insulated_hv_cable",
            "frequency_transmitter",
            "fuel_can_empty",
            "fuel_can_filled",
            "glass_fibre_cable",
            "gold_cable",
            "gold_dust",
            "hops",
            "hv_cable",
            "hydrated_coal_dust",
            "hydration_cell",
            "h_coal",
            "h_coal_cell",
            "industrial_credit",
            "industrial_diamond",
            "insulated_gold_cable",
            "insulated_hv_cable",
            "insulation_cutter",
            "integrated_heat_disperser",
            "integrated_reactor_plating",
            "iridium_ore",
            "iridium_plate",
            "iron_dust",
            "jetpack",
            "lapotron_crystal_1",
            "lapotron_crystal_2",
            "lapotron_crystal_3",
            "lapotron_crystal_4",
            "lapotron_crystal_5",
            "lappack",
            "lava_cell",
            "mining_drill",
            "mining_laser",
            "mixed_metal_ingot",
            "nanosuit_bodyarmor",
            "nanosuit_boots",
            "nanosuit_helmet",
            "nanosuit_leggings",
            "nano_saber_off",
            "nano_saber_on",
            "nano_saber_on_2",
            "near_depleted_uranium_cell",
            "od_scanner",
            "overclocker_upgrade",
            "ov_scanner",
            "plantball",
            "quantumsuit_bodyarmor",
            "quantumsuit_boots",
            "quantumsuit_helmet",
            "quantumsuit_leggins",
            "raw_carbon_fibre",
            "raw_carbon_mesh",
            "refined_uranium",
            "reinforced_door",
            "re_battery_1",
            "re_battery_2",
            "re_battery_3",
            "re_battery_4",
            "re_battery_5",
            "re_enriched_uranium_cell",
            "rubber",
            "rubber_boots",
            "scrap",
            "scrap_box",
            "single_use_battery",
            "small_pile_of_iron_dust",
            "solar_helmet",
            "steel_ingot",
            "sticky_dynamite",
            "sticky_resin",
            "stone_mug",
            "terra_wart",
            "tin_can",
            "tin_can_empty",
            "tin_dust",
            "tin_ingot",
            "tool_box_closed",
            "tool_box_open",
            "treetap",
            "two_x_insulated_gold_cable",
            "two_x_insulated_hv_cable",
            "ultra_low_current_cable",
            "uninsulated_copper_cable",
            "uranium_cell",
            "uranium_ore",
            "uu_matter",
            "water_cell",
                "meu_item_unico"
        };

        var listItems = new LinkedList<Item>();
        for (var itemName : listName) {
            var item = register(itemName, Item::new, new Item.Settings());
            listItems.add(item);
        }

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(entries -> {
            for (var item : listItems) {
                entries.add(item);
            }
        });

        return listItems;
    }


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(GoodOldTimes.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

}
