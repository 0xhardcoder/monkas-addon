package monkas.addon.mixins.meteor;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.movement.Blink;
import meteordevelopment.meteorclient.systems.modules.movement.ReverseStep;
import meteordevelopment.meteorclient.systems.modules.world.Timer;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import venomhack.mixinInterface.ISpoofName;

@Mixin(
   value = {Module.class},
   remap = false
)
public abstract class ModuleMixin implements IBlink, ISpoofName {
   @Shadow
   @Final
   public String title;
   @Unique
   private Vec3d oldPos;
   @Unique
   private String spoofName = "";

   private void storeSpoofName(NbtCompound tag, String key, String value) {
      tag.putString(key, value);
      tag.putString("spoofName", this.spoofName);
   }


   private void loadSpoofName(NbtCompound tag, CallbackInfoReturnable<Module> cir) {
      if (tag.contains("spoofName")) {
         this.spoofName = tag.getString("spoofName");
      }
   }

   @Override
   public String getSpoofName() {
      return this.spoofName;
   }

   @Override
   public void setSpoofName(String spoofName) {
      this.spoofName = spoofName;
   }
}