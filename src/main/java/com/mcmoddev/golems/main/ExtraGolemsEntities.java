package com.mcmoddev.golems.main;

import com.mcmoddev.golems.entity.*;
import com.mcmoddev.golems.entity.base.GolemMultiColorized;
import com.mcmoddev.golems.events.GolemRegistrarEvent;
import com.mcmoddev.golems.util.BlockTagUtil;
import com.mcmoddev.golems.util.GolemNames;
import com.mcmoddev.golems.util.config.GolemContainer;
import com.mcmoddev.golems.util.config.GolemContainer.SwimMode;
import com.mcmoddev.golems.util.config.GolemDescription;
import com.mcmoddev.golems.util.config.GolemRegistrar;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;

public final class ExtraGolemsEntities {
  
  

  private ExtraGolemsEntities() {}

  public static void initEntityTypes() {
    // Frequently-used GolemDescription components
    final GolemDescription descFireproof = new GolemDescription(
        new TranslationTextComponent("entitytip.is_fireproof").applyTextStyle(TextFormatting.GOLD));
    final GolemDescription descSwims = new GolemDescription(new TranslationTextComponent("entitytip.advanced_swim").applyTextStyle(TextFormatting.AQUA));
    final ITextComponent descHeals = new TranslationTextComponent("entitytip.heals").applyTextStyle(TextFormatting.LIGHT_PURPLE);
    final ITextComponent descSplits = new TranslationTextComponent("entitytip.splits_upon_death");
    final String comSplits = "The number of mini-golems to spawn when this golem dies";
    // used for loot tables in multi-colored golems
    final String[] sRange = new String[GolemMultiColorized.DYE_COLORS.length];
    for (int i = 0, l = sRange.length; i < l; i++) {
      sRange[i] = String.valueOf(i);
    }

    // ANDESITE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.ANDESITE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(52.0D).setAttack(6.0D).setSpeed(0.26D).setKnockback(0.8D).addBlocks(Blocks.POLISHED_ANDESITE)
        .basicTexture().build());
    // BEDROCK GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.BEDROCK_GOLEM, BedrockGolem.class, BedrockGolem::new)
        .setHealth(999.0D).setAttack(32.0D).setKnockback(1.0D).basicTexture()
        .addDesc(
            new GolemDescription(new TranslationTextComponent("entitytip.indestructible").applyTextStyles(TextFormatting.WHITE, TextFormatting.BOLD)),
            new GolemDescription(new TranslationTextComponent("tooltip.creative_only_item").applyTextStyle(TextFormatting.DARK_RED)))
        .build());
    // BONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.BONE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(40.0D).setAttack(9.5D).setSpeed(0.30D).addBlocks(Blocks.BONE_BLOCK).setTexture(makeTexture(GolemNames.BONE_GOLEM + "_skeleton"))
        .enableFallDamage().addHealItem(Items.BONE, 0.25D).addHealItem(Items.BONE_MEAL, 0.08D).build());
    // BOOKSHELF GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.BOOKSHELF_GOLEM, BookshelfGolem.class, BookshelfGolem::new)
        .setHealth(28.0D).setAttack(1.5D).setSpeed(0.29D).addBlocks(Blocks.BOOKSHELF).basicTexture()
        .addSpecial(BookshelfGolem.ALLOW_SPECIAL, true, "Whether this golem can give itself potion effects",
            new TranslationTextComponent("entitytip.grants_self_potion_effects").applyTextStyle(TextFormatting.LIGHT_PURPLE))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_WOOD_STEP)
        .addHealItem(Items.BOOK, 0.25D).addHealItem(Items.OAK_PLANKS, 0.25D).build());
    // CLAY GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.CLAY_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(20.0D).setAttack(2.0D).addBlocks(Blocks.CLAY).basicTexture().setSound(SoundEvents.BLOCK_GRAVEL_STEP)
        .addHealItem(Items.CLAY_BALL, 0.25D).build());
    // COAL GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.COAL_GOLEM, CoalGolem.class, CoalGolem::new)
        .setHealth(24.0D).setAttack(2.5D).setSpeed(0.28D).setKnockback(0.2D).addBlocks(Blocks.COAL_BLOCK).basicTexture()
        .addSpecial(CoalGolem.ALLOW_SPECIAL, false, "Whether this golem can inflict blindness",
            new TranslationTextComponent("entitytip.blinds_creatures").applyTextStyle(TextFormatting.GRAY))
        .addHealItem(Items.COAL, 0.25D).addHealItem(Items.CHARCOAL, 0.25D).build());
    // CONCRETE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.CONCRETE_GOLEM, ConcreteGolem.class, ConcreteGolem::new)
        .setHealth(38.0D).setAttack(5.0D).setSpeed(0.26D).addBlocks(BlockTagUtil.TAG_CONCRETE)
        .addSpecial(ConcreteGolem.ALLOW_RESIST, true, "Whether this golem reduces damage it takes",
            new TranslationTextComponent("effect.minecraft.resistance").applyTextStyle(TextFormatting.DARK_GRAY))
        .build());
    // CORAL GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.CORAL_GOLEM, CoralGolem.class, CoralGolem::new)
        .setHealth(38.0D).setAttack(6.0D).setSpeed(0.29D).setSwimMode(SwimMode.SWIM).addDesc(descSwims).setSound(SoundEvents.BLOCK_CORAL_BLOCK_STEP)
        .addSpecial(CoralGolem.ALLOW_HEALING, true, "Whether this golem can occasionally heal when wet", descHeals)
        .addSpecial(CoralGolem.DRY_TIMER, 425, "Number of ticks golem can stay out of water before drying out").addBlocks(BlockTags.CORAL_BLOCKS)
        .addBlocks(BlockTagUtil.TAG_DEAD_CORAL_BLOCKS).build());
    // CRAFTING GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.CRAFTING_GOLEM, CraftingGolem.class, CraftingGolem::new)
        .setHealth(24.0D).setAttack(2.0D).setSpeed(0.29D).addBlocks(Blocks.CRAFTING_TABLE).basicTexture()
        .addSpecial(CraftingGolem.ALLOW_SPECIAL, true, "Whether this golem can open a crafting grid",
            new TranslationTextComponent("entitytip.click_open_crafting").applyTextStyle(TextFormatting.BLUE))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_WOOD_STEP)
        .addHealItem(Items.OAK_PLANKS, 0.25D).build());
    // DIAMOND GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.DIAMOND_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(220.0D).setAttack(20.0D).setKnockback(0.8D).addBlocks(Blocks.DIAMOND_BLOCK).basicTexture()
        .addHealItem(Items.DIAMOND, 0.25D).build());
    // DIORITE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.DIORITE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(50.0D).setAttack(6.0D).setSpeed(0.25D).setKnockback(0.8D).addBlocks(Blocks.POLISHED_DIORITE)
        .basicTexture().build());
    // DISPENSER GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.DISPENSER_GOLEM, DispenserGolem.class, DispenserGolem::new)
        .setHealth(68.0D).addSpecial(DispenserGolem.ALLOW_SPECIAL, true, "Whether the golem can shoot arrows",
            new TranslationTextComponent("entitytip.shoots_arrows").applyTextStyle(TextFormatting.LIGHT_PURPLE))
        .addSpecial(DispenserGolem.ARROW_DAMAGE, Double.valueOf(4.25D), "Base amount of damage dealt per arrow")
        .addSpecial(DispenserGolem.ARROW_SPEED, 30, "Number of ticks between shooting arrows")
        .addDesc(new GolemDescription(new TranslationTextComponent("entitytip.click_refill").applyTextStyle(TextFormatting.GRAY),
            DispenserGolem.ALLOW_SPECIAL))
        .basicTexture().addBlocks(Blocks.DISPENSER).addHealItem(Items.COBBLESTONE, 0.25D).build());
    // EMERALD GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.EMERALD_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(190.0D).setAttack(18.0D).setKnockback(0.8D).addBlocks(Blocks.EMERALD_BLOCK).basicTexture()
        .addHealItem(Items.EMERALD, 0.25D).build());
    // ENDSTONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.ENDSTONE_GOLEM, EndstoneGolem.class, EndstoneGolem::new)
        .setHealth(50.0D).setAttack(8.0D).setSpeed(0.26D).setKnockback(0.3D).addBlocks(Blocks.END_STONE, Blocks.END_STONE_BRICKS)
        .addSpecial(EndstoneGolem.ALLOW_WATER_HURT, true, "Whether the Endstone Golem takes damage from water")
        .addSpecial(EndstoneGolem.ALLOW_SPECIAL, true, "Whether this golem can teleport",
            new TranslationTextComponent("entitytip.can_teleport").applyTextStyle(TextFormatting.DARK_AQUA))
        .basicTexture().build());
    // FURNACE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.FURNACE_GOLEM, FurnaceGolem.class, FurnaceGolem::new)
        .setHealth(88.0D).setAttack(6.5D).setSpeed(0.24D).setKnockback(1.0D).addBlocks(Blocks.FURNACE)
        .addDesc(new GolemDescription(new TranslationTextComponent("entitytip.use_fuel").applyTextStyle(TextFormatting.GRAY)))
        .addSpecial(FurnaceGolem.FUEL_FACTOR, 10, "Number of ticks between using fuel points").immuneToFire()
        .addHealItem(Items.COBBLESTONE, 0.25D).build());
    // GLASS GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.GLASS_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(8.0D).setAttack(13.0D).setSpeed(0.30D).addBlocks(Blocks.GLASS).basicTexture().enableFallDamage()
        .setSound(SoundEvents.BLOCK_GLASS_STEP).build());
    // GLOWSTONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.GLOWSTONE_GOLEM, GlowstoneGolem.class, GlowstoneGolem::new)
        .setHealth(8.0D).setAttack(12.0D).setSpeed(0.26D).enableFallDamage().addBlocks(Blocks.GLOWSTONE)
        .addSpecial(GlowstoneGolem.ALLOW_SPECIAL, true, "Whether this golem can glow",
            new TranslationTextComponent("entitytip.lights_area").applyTextStyle(TextFormatting.RED))
        .immuneToFire().addDesc(descFireproof).setSound(SoundEvents.BLOCK_GLASS_STEP).basicTexture().setSwimMode(SwimMode.FLOAT)
        .addHealItem(Items.GLOWSTONE_DUST, 0.25D).build());
    // GOLD GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.GOLD_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(80.0D).setAttack(8.0D).setSpeed(0.21D).setKnockback(1.0D).addBlocks(Blocks.GOLD_BLOCK).basicTexture()
        .addHealItem(Items.GOLD_INGOT, 0.25D).addHealItem(Items.GOLD_NUGGET, 0.025D).build());
    // GRANITE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.GRANITE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(58.0D).setAttack(5.2D).setSpeed(0.25D).setKnockback(0.8D).addBlocks(Blocks.POLISHED_GRANITE)
        .basicTexture().build());
    // HONEY GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.HONEY_GOLEM, HoneyGolem.class, HoneyGolem::new)
        .setHealth(42.0D).setAttack(1.0D).setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_SLIME_BLOCK_STEP)
        .addBlocks(Blocks.HONEY_BLOCK).addHealItem(Items.HONEY_BOTTLE, 0.25D).addHealItem(Items.HONEYCOMB, 0.25D)
        .addSpecial(HoneyGolem.ALLOW_HONEY, Boolean.valueOf(true), "Whether this golem applies honey effect to mobs", 
            new TranslationTextComponent("entitytip.sticky").applyTextStyle(TextFormatting.GOLD))
        .addSpecial(HoneyGolem.SPLITTING_CHILDREN, Integer.valueOf(2), comSplits)
        .addDesc(new GolemDescription(descSplits.shallowCopy().applyTextStyle(TextFormatting.GOLD), 
            HoneyGolem.SPLITTING_CHILDREN, c -> (Integer) c.get() > 0))
        .basicTexture().build());
    // HONEYCOMB GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.HONEYCOMB_GOLEM, HoneycombGolem.class, HoneycombGolem::new)
        .setHealth(68.0D).setAttack(2.0D).setSpeed(0.27D).setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_CORAL_BLOCK_STEP)
        .addBlocks(Blocks.HONEYCOMB_BLOCK).addHealItem(Items.HONEYCOMB, 0.25D)
        .addSpecial(HoneycombGolem.SUMMON_BEE_CHANCE, Integer.valueOf(30), "Percent chance to summon a bee when attacked [0,100]")
        .addDesc(new GolemDescription(new TranslationTextComponent("entitytip.summons_bees").applyTextStyle(TextFormatting.GOLD), 
            HoneycombGolem.SUMMON_BEE_CHANCE, c -> (Integer) c.get() > 0))
        .basicTexture().build());
    // ICE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.ICE_GOLEM, IceGolem.class, IceGolem::new)
        .setHealth(18.0D).setAttack(6.0D).setSpeed(0.27D).addBlocks(BlockTags.ICE).basicTexture()
        .addSpecial(IceGolem.AOE, Integer.valueOf(3), "Radial distance at which this golem can freeze / cool liquids (0=disable)")
        .addSpecial(IceGolem.FROST, false, "When true, this golem places frosted (temporary) ice")
        .addDesc(new GolemDescription(new TranslationTextComponent("entitytip.freezes_blocks").applyTextStyle(TextFormatting.AQUA), IceGolem.AOE,
            c -> (Integer) c.get() > 0))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GLASS_STEP).build());
    // KELP GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.KELP_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(34.0D).setAttack(2.0D).addBlocks(Blocks.DRIED_KELP_BLOCK).basicTexture()
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GRASS_STEP)
        .addHealItem(Items.KELP, 0.35D).addHealItem(Items.DRIED_KELP, 0.25D)
        .build());
    // LAPIS GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.LAPIS_GOLEM, LapisGolem.class, LapisGolem::new)
        .setHealth(50.0D).setAttack(1.5D).setSpeed(0.285D).basicTexture().addBlocks(Blocks.LAPIS_BLOCK)
        .addSpecial(LapisGolem.ALLOW_SPECIAL, true, "Whether this golem can inflict harmful potion effects",
            new TranslationTextComponent("entitytip.attacks_use_potion_effects").applyTextStyle(TextFormatting.LIGHT_PURPLE))
        .addHealItem(Items.LAPIS_LAZULI, 0.25D).build());
    // LEAF GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.LEAF_GOLEM, LeafGolem.class, LeafGolem::new)
        .setHealth(6.0D).setAttack(0.5D).setSpeed(0.31D).setKnockback(0.0D).addBlocks(BlockTags.LEAVES)
        .addSpecial(LeafGolem.ALLOW_SPECIAL, true, "Whether this golem can heal itself",
            new TranslationTextComponent("effect.minecraft.regeneration").applyTextStyle(TextFormatting.DARK_GREEN).appendText(" ")
                .appendSibling(new TranslationTextComponent("enchantment.level.1").applyTextStyle(TextFormatting.DARK_GREEN)))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GRASS_STEP).build());
    // MAGMA GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.MAGMA_GOLEM, MagmaGolem.class, MagmaGolem::new)
        .setHealth(46.0D).setAttack(4.5D).setSpeed(0.28D).addBlocks(Blocks.MAGMA_BLOCK)
        .addSpecial(MagmaGolem.MELT_DELAY, Integer.valueOf(240),
            "Number of ticks it takes to melt cobblestone if enabled (12 sec * 20 t/sec = 240 t)")
        .addSpecial(MagmaGolem.ALLOW_WATER_DAMAGE, true, "When true, water will hurt this golem")
        .addSpecial(MagmaGolem.ALLOW_LAVA_SPECIAL, false, "Whether this golem can slowly melt cobblestone",
            new TranslationTextComponent("entitytip.slowly_melts", new TranslationTextComponent("block.minecraft.cobblestone"))
                .applyTextStyle(TextFormatting.RED))
        .addSpecial(MagmaGolem.ALLOW_FIRE_SPECIAL, true, "Whether this golem can light creatures on fire",
            new TranslationTextComponent("entitytip.lights_mobs_on_fire").applyTextStyle(TextFormatting.GOLD))
        .addSpecial(MagmaGolem.SPLITTING_CHILDREN, Integer.valueOf(2), comSplits)
        .addDesc(new GolemDescription(descSplits.shallowCopy().applyTextStyle(TextFormatting.RED), 
            MagmaGolem.SPLITTING_CHILDREN, c -> (Integer) c.get() > 0))
        .immuneToFire().addDesc(descFireproof).addHealItem(Items.MAGMA_CREAM, 0.25D).build());
    // MELON GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.MELON_GOLEM, MelonGolem.class, MelonGolem::new)
        .setHealth(18.0D).setAttack(1.5D).setSpeed(0.265D).basicTexture().addBlocks(Blocks.MELON)
        .addSpecial(MelonGolem.ALLOW_HEALING, true, "Whether this golem can occasionally heal", descHeals)
        .addSpecial(MelonGolem.ALLOW_SPECIAL, true, "Whether this golem can plant flowers randomly",
            new TranslationTextComponent("entitytip.plants_flowers", new TranslationTextComponent("tile.flower1.name"))
                .applyTextStyle(TextFormatting.GREEN))
        .addSpecial(MelonGolem.FREQUENCY, Integer.valueOf(240), "Average number of ticks between planting flowers")
        .setSwimMode(SwimMode.FLOAT).addHealItem(Items.MELON_SLICE, 0.25D).addHealItem(Items.GLISTERING_MELON_SLICE, 0.35D).build());
    // MUSHROOM GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.MUSHROOM_GOLEM, MushroomGolem.class, MushroomGolem::new)
        .setHealth(30.0D).setAttack(3.0D).setSpeed(0.30D).addBlocks(Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK)
        .addSpecial(MushroomGolem.FREQUENCY, Integer.valueOf(420), "Average number of ticks between planting mushrooms")
        .addSpecial(MushroomGolem.ALLOW_HEALING, true, "Whether this golem can randomly heal (at night)", descHeals)
        .addSpecial(MushroomGolem.ALLOW_SPECIAL, true, "Whether this golem can plant mushrooms randomly",
            new TranslationTextComponent("entitytip.plants_shrooms").applyTextStyle(TextFormatting.DARK_GREEN))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GRASS_STEP)
        .addHealItem(Items.RED_MUSHROOM, 0.25D).addHealItem(Items.BROWN_MUSHROOM, 0.25D).build());
    // NETHER BRICK GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.NETHERBRICK_GOLEM, NetherBrickGolem.class, NetherBrickGolem::new)
        .setHealth(25.0D).setAttack(6.5D).setSpeed(0.28D).setKnockback(0.2D).addBlocks(Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        .addSpecial(NetherBrickGolem.ALLOW_FIRE_SPECIAL, true, "Whether this golem can light creatures on fire",
            new TranslationTextComponent("entitytip.lights_mobs_on_fire").applyTextStyle(TextFormatting.RED))
        .immuneToFire().addDesc(descFireproof).basicTexture().addHealItem(Items.NETHER_BRICK, 0.25D).build());
    // NETHER WART GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.NETHERWART_GOLEM, NetherWartGolem.class, NetherWartGolem::new)
        .setHealth(22.0D).setAttack(1.5D).setSpeed(0.26D).basicTexture().addBlocks(Blocks.NETHER_WART_BLOCK)
        .addSpecial(NetherWartGolem.FREQUENCY, Integer.valueOf(880), "Average number of ticks between planting nether wart (if enabled)")
        .addSpecial(NetherWartGolem.ALLOW_HEALING, true, "Whether this golem can randomly heal (at night)", descHeals)
        .addSpecial(NetherWartGolem.ALLOW_SPECIAL, true, "Whether this golem can plant netherwart randomly",
            new TranslationTextComponent("entitytip.plants_warts").applyTextStyle(TextFormatting.RED))
        .immuneToFire().addDesc(descFireproof).setSound(SoundEvents.BLOCK_WOOD_STEP).setSwimMode(SwimMode.FLOAT)
        .addHealItem(Items.NETHER_WART, 0.25D).build());
    // OBSIDIAN GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.OBSIDIAN_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(120.0D).setAttack(18.0D).setSpeed(0.23D).setKnockback(0.8D).addBlocks(Blocks.OBSIDIAN).basicTexture()
        .immuneToFire().addDesc(descFireproof).build());
    // PRISMARINE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.PRISMARINE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(34.0D).setAttack(8.0D).setKnockback(0.7D).addBlocks(BlockTagUtil.TAG_PRISMARINE).basicTexture()
        .addDesc(descSwims).setSwimMode(SwimMode.SWIM).addHealItem(Items.PRISMARINE_SHARD, 0.25D).build());
    // QUARTZ GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.QUARTZ_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(85.0D).setAttack(8.5D).setSpeed(0.28D).setKnockback(0.6D).addBlocks(BlockTagUtil.TAG_QUARTZ).basicTexture()
        .setSound(SoundEvents.BLOCK_GLASS_STEP).addHealItem(Items.QUARTZ, 0.25D).build());
    // RED SANDSTONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.REDSANDSTONE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(22.0D).setAttack(4.0D).setSpeed(0.28D).setKnockback(0.6D).addBlocks(BlockTagUtil.TAG_RED_SANDSTONE)
        .basicTexture().addHealItem(Items.RED_SAND, 0.25D).build());
    // REDSTONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.REDSTONE_GOLEM, RedstoneGolem.class, RedstoneGolem::new)
        .setHealth(18.0D).setAttack(2.0D).setSpeed(0.26D).setKnockback(0.1D).addBlocks(Blocks.REDSTONE_BLOCK)
        .addSpecial(RedstoneGolem.ALLOW_SPECIAL, true, "Whether this golem can emit redstone power",
            new TranslationTextComponent("entitytip.emits_redstone_signal").applyTextStyle(TextFormatting.RED))
        .basicTexture().addHealItem(Items.REDSTONE, 0.25D).build());
    // REDSTONE LAMP GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.REDSTONELAMP_GOLEM, RedstoneLampGolem.class, RedstoneLampGolem::new)
        .setHealth(28.0D).setAttack(6.0D).setSpeed(0.26D).addBlocks(Blocks.REDSTONE_LAMP)
        .addSpecial(RedstoneLampGolem.ALLOW_SPECIAL, true, "Whether this golem can light up the area",
            new TranslationTextComponent("entitytip.lights_area_toggle").applyTextStyle(TextFormatting.GOLD))
        .enableFallDamage().setSound(SoundEvents.BLOCK_GLASS_STEP).addHealItem(Items.REDSTONE, 0.25D)
        .addHealItem(Items.GLOWSTONE_DUST, 0.25D).build());
    // SANDSTONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.SANDSTONE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(22.0D).setAttack(4.0D).setSpeed(0.28D).setKnockback(0.6D).addBlocks(BlockTagUtil.TAG_SANDSTONE).basicTexture()
        .addHealItem(Items.SAND, 0.25D).build());
    // SEA LANTERN GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.SEALANTERN_GOLEM, SeaLanternGolem.class, SeaLanternGolem::new)
        .setHealth(34.0D).setAttack(6.0D).setSpeed(0.26D).setKnockback(0.9D).addBlocks(Blocks.SEA_LANTERN).basicTexture()
        .addSpecial(SeaLanternGolem.ALLOW_SPECIAL, true, "Whether this golem lights up the area",
            new TranslationTextComponent("entitytip.lights_area").applyTextStyle(TextFormatting.GOLD))
        .addDesc(descSwims).setSound(SoundEvents.BLOCK_GLASS_STEP).setSwimMode(SwimMode.SWIM)
        .addHealItem(Items.PRISMARINE_SHARD, 0.25D).addHealItem(Items.PRISMARINE_CRYSTALS, 0.25D).build());
    // SLIME GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.SLIME_GOLEM, SlimeGolem.class, SlimeGolem::new)
        .setHealth(58.0D).setAttack(2.5D).setSpeed(0.288D).setKnockback(0.35D).addBlocks(Blocks.SLIME_BLOCK).basicTexture()
        .addSpecial(SlimeGolem.SPLITTING_CHILDREN, Integer.valueOf(2), comSplits)
        .addSpecial(SlimeGolem.ALLOW_SPECIAL, true, "Whether this golem can apply extra knockback when attacking",
            new TranslationTextComponent("entitytip.has_knockback").applyTextStyle(TextFormatting.GREEN))
        .addSpecial(SlimeGolem.KNOCKBACK, Double.valueOf(1.0412D), "Slime Golem knockback power (Higher Value = Further Knockback)")
        .addDesc(new GolemDescription(descSplits.shallowCopy().applyTextStyle(TextFormatting.GREEN), 
            SlimeGolem.SPLITTING_CHILDREN, c -> (Integer) c.get() > 0))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.ENTITY_SLIME_SQUISH).addHealItem(Items.SLIME_BALL, 0.25D).build());
    // SMOOTH STONE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.SMOOTHSTONE_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(60.0D).setAttack(5.4D).setSpeed(0.27D).setKnockback(0.6D).addBlocks(Blocks.SMOOTH_STONE)
        .basicTexture().build());
    // SPONGE GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.SPONGE_GOLEM, SpongeGolem.class, SpongeGolem::new)
        .setHealth(20.0D).setAttack(1.5D).basicTexture().addBlocks(Blocks.SPONGE, Blocks.WET_SPONGE)
        .addSpecial(SpongeGolem.RANGE, Integer.valueOf(5), "Radial distance at which this golem can absorb water (Warning: larger values cause lag)")
        .addSpecial(SpongeGolem.INTERVAL, Integer.valueOf(10), "Number of ticks between each water-check; increase to reduce lag")
        .addSpecial(SpongeGolem.ALLOW_SPECIAL, true, "Whether this golem can absorb water",
            new TranslationTextComponent("entitytip.absorbs_water").applyTextStyle(TextFormatting.GOLD))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_WOOL_STEP).build());
    // STAINED GLASS GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.STAINEDGLASS_GOLEM, StainedGlassGolem.class, StainedGlassGolem::new)
        .setHealth(9.0D).setAttack(12.0D).setSpeed(0.29D).addBlocks(Tags.Blocks.STAINED_GLASS).enableFallDamage()
        .setSound(SoundEvents.BLOCK_GLASS_STEP).build());
    // STAINED TERRACOTTA GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.STAINEDTERRACOTTA_GOLEM, StainedTerracottaGolem.class, StainedTerracottaGolem::new)
        .setHealth(42.0D).setAttack(4.0D).setSpeed(0.22D).setKnockback(0.6D).addBlocks(BlockTagUtil.TAG_TERRACOTTA).build());
    // STRAW GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.STRAW_GOLEM, StrawGolem.class, StrawGolem::new)
        .setHealth(10.0D).setAttack(1.0D).setSpeed(0.32D).setKnockback(0.0D).addBlocks(Blocks.HAY_BLOCK).basicTexture()
        .addSpecial(StrawGolem.SPECIAL_FREQ, Integer.valueOf(460), "Minimum number of ticks between crop-boosts")
        .addSpecial(StrawGolem.ALLOW_SPECIAL, true, "Whether this golem can speed up crop growth",
            new TranslationTextComponent("entitytip.grows_crops").applyTextStyle(TextFormatting.GREEN))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GRASS_STEP).addHealItem(Items.WHEAT, 0.25D).build());
    // TERRACOTTA GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.TERRACOTTA_GOLEM, GenericGolem.class, GenericGolem::new)
        .setHealth(42.0D).setAttack(4.0D).setSpeed(0.208D).setKnockback(0.6D).addBlocks(Blocks.TERRACOTTA).basicTexture().build());
    // TNT GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.TNT_GOLEM, TNTGolem.class, TNTGolem::new)
        .setHealth(14.0D).setAttack(2.5D).setSpeed(0.26D).basicTexture().addBlocks(Blocks.TNT)
        .addSpecial(TNTGolem.ALLOW_SPECIAL, true, "Whether this golem can explode when fighting or dying",
            new TranslationTextComponent("entitytip.explodes").applyTextStyle(TextFormatting.RED))
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_GRAVEL_STEP).addHealItem(Items.GUNPOWDER, 0.25D)
        .addHealItem(Items.SAND, 0.25D).build());
    // WOODEN GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.WOODEN_GOLEM, WoodenGolem.class, WoodenGolem::new)
        .setHealth(20.0D).setAttack(3.0D).setSpeed(0.298D).setKnockback(0.2D).addBlocks(BlockTags.LOGS)
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_WOOD_STEP).addHealItem(Items.STICK, 0.1D).build());
    // WOOL GOLEM
    GolemRegistrar.registerGolem(new GolemContainer.Builder(GolemNames.WOOL_GOLEM, WoolGolem.class, WoolGolem::new)
        .setHealth(10.0D).setAttack(1.0D).setSpeed(0.295D).setKnockback(0.2D).addBlocks(BlockTags.WOOL)
        .setSwimMode(SwimMode.FLOAT).setSound(SoundEvents.BLOCK_WOOL_STEP)
        .addHealItem(Items.STRING, 0.1D).build());

    // fire GolemRegistrar event for any listening child mods (addons)
    MinecraftForge.EVENT_BUS.post(new GolemRegistrarEvent());
  }

  /**
   * Calls {@link #makeTexture(String, String)} on the assumption that MODID is
   * 'golems'. Texture should be at 'assets/golems/textures/entity/[TEXTURE].png'
   **/
  public static ResourceLocation makeTexture(final String TEXTURE) {
    return makeTexture(ExtraGolems.MODID, TEXTURE);
  }

  /**
   * Makes a ResourceLocation using the passed mod id and part of the texture
   * name. Texture should be at 'assets/[MODID]/textures/entity/[TEXTURE].png'
   * 
   * @see #makeTexture(String)
   **/
  public static ResourceLocation makeTexture(final String MODID, final String TEXTURE) {
    return new ResourceLocation(MODID + ":textures/entity/" + TEXTURE + ".png");
  }
}
