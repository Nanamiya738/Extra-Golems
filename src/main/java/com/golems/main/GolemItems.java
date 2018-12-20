package com.golems.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GolemItems {

	private GolemItems() {
		//
	}
	////// ITEMS //////
	@GameRegistry.ObjectHolder(ExtraGolems.MODID + ":golem_paper")
	public static Item golemPaper;
	
	@GameRegistry.ObjectHolder(ExtraGolems.MODID + ":spawn_bedrock_golem")
	public static Item spawnBedrockGolem;
	
	////// BLOCKS //////
	@GameRegistry.ObjectHolder(ExtraGolems.MODID + ":golem_head")
	public static Block golemHead;

	@GameRegistry.ObjectHolder(ExtraGolems.MODID + ":light_provider_full")
	public static Block blockLightSource;

	@GameRegistry.ObjectHolder(ExtraGolems.MODID + ":power_provider_all")
	public static Block blockPowerSource;
}
