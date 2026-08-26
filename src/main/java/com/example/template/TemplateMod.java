package com.example.template;

import com.example.template.block.ExampleBlock;
import com.example.template.block.FlatBlock;
import com.example.template.block.SmallFlatBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TemplateMod implements ModInitializer {
    public static final String MOD_ID = "shsubwaypicture";

    public static final Block EXAMPLE_BLOCK = new ExampleBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block TEST_1 = new Block(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block PIC_TL = new ExampleBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block PIC_TR = new ExampleBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block PIC_BL = new ExampleBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block PIC_BR = new ExampleBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block FLAT_TL = new FlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block FLAT_TR = new FlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block FLAT_BL = new FlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block FLAT_BR = new FlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool()
    );
    public static final Block FLAT_TL_SMALL = new SmallFlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool(), 0
    );
    public static final Block FLAT_TR_SMALL = new SmallFlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool(), 1
    );
    public static final Block FLAT_BL_SMALL = new SmallFlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool(), 2
    );
    public static final Block FLAT_BR_SMALL = new SmallFlatBlock(
        AbstractBlock.Settings.create().strength(1.5f).requiresTool(), 3
    );

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.shsubwaypicture.main"))
        .icon(() -> new ItemStack(PIC_TL))
        .entries((ctx, entries) -> {
            entries.add(PIC_TL);
            entries.add(PIC_TR);
            entries.add(PIC_BL);
            entries.add(PIC_BR);
            entries.add(FLAT_TL);
            entries.add(FLAT_TR);
            entries.add(FLAT_BL);
            entries.add(FLAT_BR);
            entries.add(FLAT_TL_SMALL);
            entries.add(FLAT_TR_SMALL);
            entries.add(FLAT_BL_SMALL);
            entries.add(FLAT_BR_SMALL);
            entries.add(EXAMPLE_BLOCK);
            entries.add(TEST_1);
        })
        .build();

    @Override
    public void onInitialize() {
        reg("example_block", EXAMPLE_BLOCK);
        reg("test_1", TEST_1);
        reg("pic_tl", PIC_TL);
        reg("pic_tr", PIC_TR);
        reg("pic_bl", PIC_BL);
        reg("pic_br", PIC_BR);
        reg("flat_tl", FLAT_TL);
        reg("flat_tr", FLAT_TR);
        reg("flat_bl", FLAT_BL);
        reg("flat_br", FLAT_BR);
        reg("flat_tl_small", FLAT_TL_SMALL);
        reg("flat_tr_small", FLAT_TR_SMALL);
        reg("flat_bl_small", FLAT_BL_SMALL);
        reg("flat_br_small", FLAT_BR_SMALL);

        Registry.register(Registries.ITEM_GROUP, id("main"), ITEM_GROUP);
    }

    private void reg(String name, Block block) {
        Registry.register(Registries.BLOCK, id(name), block);
        Registry.register(Registries.ITEM, id(name), new BlockItem(block, new Item.Settings()));
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}