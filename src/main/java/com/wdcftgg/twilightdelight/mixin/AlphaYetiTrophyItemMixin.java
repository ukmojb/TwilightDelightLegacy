package com.wdcftgg.twilightdelight.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.enums.BossVariant;
import twilightforest.item.ItemTFTrophy;

@Mixin(ItemTFTrophy.class)
public abstract class AlphaYetiTrophyItemMixin {

    @Inject(method = "getSubItems", at = @At("TAIL"))
    private void twilightdelight$addAlphaYetiTrophy(CreativeTabs tab, NonNullList<ItemStack> items, CallbackInfo ci) {
        Item trophy = (Item) (Object) this;
        int alphaYetiMetadata = BossVariant.ALPHA_YETI.ordinal();
        int insertionIndex = items.size();
        boolean foundTrophy = false;
        for (int index = 0; index < items.size(); index++) {
            ItemStack stack = items.get(index);
            if (stack.getItem() != trophy) {
                continue;
            }
            foundTrophy = true;
            if (stack.getMetadata() == alphaYetiMetadata) {
                return;
            }
            if (stack.getMetadata() > alphaYetiMetadata) {
                insertionIndex = index;
                break;
            }
        }
        if (foundTrophy) {
            items.add(insertionIndex, new ItemStack(trophy, 1, alphaYetiMetadata));
        }
    }

    @Inject(method = "getItemStackDisplayName", at = @At("HEAD"), cancellable = true)
    private void twilightdelight$fixAlphaYetiTrophyName(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if (stack.getMetadata() == BossVariant.ALPHA_YETI.ordinal()) {
            cir.setReturnValue(I18n.translateToLocalFormatted("item.twilightforest.tf_trophy.name",
                    I18n.translateToLocal("entity.twilightforest.yeti_alpha.name")));
        }
    }
}
