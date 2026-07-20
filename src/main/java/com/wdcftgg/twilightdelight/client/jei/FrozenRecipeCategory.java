package com.wdcftgg.twilightdelight.client.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public final class FrozenRecipeCategory implements IRecipeCategory<FrozenRecipeWrapper> {

    public static final String UID = "twilightdelight.frozen";
    private static final ResourceLocation FURNACE_GUI = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic slot;
    private final IDrawableAnimated arrow;

    public FrozenRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(82, 35);
        this.slot = guiHelper.getSlotDrawable();
        this.arrow = guiHelper.drawableBuilder(FURNACE_GUI, 176, 14, 24, 17)
                .buildAnimated(100, IDrawableAnimated.StartDirection.LEFT, false);

        Item iconItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation("twilightdelight", "glacier_ice_tea"));
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(iconItem == null ? Items.SNOWBALL : iconItem));
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return I18n.format("effect.twilightdelight.frozen_range");
    }

    @Override
    public String getModName() {
        return "Twilight Delight Legacy";
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FrozenRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 1, 9);
        itemStacks.init(1, false, 65, 9);
        itemStacks.set(ingredients);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        this.slot.draw(minecraft, 0, 8);
        this.arrow.draw(minecraft, 29, 8);
        this.slot.draw(minecraft, 64, 8);
    }
}
