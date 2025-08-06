package fr.alasdiablo.mods.lib.item;

import fr.alasdiablo.mods.lib.DioLib;
import fr.alasdiablo.mods.lib.utils.DateRange;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class GroundCreativeModeTab {
    public static @NotNull ItemGroup.Builder createBaseBuilder(ItemGroup.Builder builder) {

        if (DateRange.IS_WINTER) {
            builder.texture(
                    Identifier.of(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground_winter.png")
            );
            return builder;
        }

        if (DateRange.IS_APRIL_FIRST) {
            builder.texture(
                    Identifier.of(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground_april.png")
            );
            return builder;
        }

        builder.texture(
                Identifier.of(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground.png")
        );

        return builder;
    }
}
