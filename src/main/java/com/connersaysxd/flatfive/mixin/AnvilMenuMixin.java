package com.connersaysxd.flatfive.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
    @Shadow @Final private DataSlot cost;

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40), require = 0)
    private int flatfive$removeCap(int oldValue) { return Integer.MAX_VALUE; }

    @Inject(method = "createResult", at = @At("TAIL"))
    private void flatfive$setCost(CallbackInfo ci) {
        if (((AnvilMenu)(Object)this).getSlot(2).hasItem()) {
            cost.set(5);
        }
    }
}
