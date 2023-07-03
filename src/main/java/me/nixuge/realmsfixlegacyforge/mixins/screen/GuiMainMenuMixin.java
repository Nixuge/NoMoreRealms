package me.nixuge.realmsfixlegacyforge.mixins.screen;

import java.util.List;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {
    @Redirect(method = "addSingleplayerMultiplayerButtons", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2), expect = 0, require = 0)
    public boolean doNotAddRealmsButton(List<GuiButton> instance, Object e) {
        return false;
    }
    
    @Redirect(method = "addSingleplayerMultiplayerButtons", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 3), expect = 0, require = 0)
    public boolean makeModsButtonWider(List<GuiButton> instance, Object e) {
        GuiButton button = (GuiButton)e;
        button.setWidth(button.getButtonWidth() * 2 + 5);
        return instance.add(button);
    }

    // "areRealmsNotificationsEnabled" with yarn
    @Inject(method = "func_183501_a", at = @At("HEAD"), cancellable = true, expect = 0, require = 0)
    public void areRealmsNotificationsEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    // Same check as above but without calling the function made for that EXACT purpose for some reason
    @Redirect(method = "initGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/GameSettings;getOptionOrdinalValue(Lnet/minecraft/client/settings/GameSettings$Options;)Z"))
    public boolean areRealmsNotificationsEnabledInitGui(GameSettings instance, GameSettings.Options settingOption) {
        return false;
    }
}
