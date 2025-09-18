package br.com.walter.GoodOldTimes;

import br.com.walter.GoodOldTimes.block.ModBlocks;
import br.com.walter.GoodOldTimes.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodOldTimes implements ModInitializer {
	public static final String MOD_ID = "good-old-times";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.initialize();
	}
}