package org.tinteeam.customcrafting.item;



import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;

public class ModCreativeModeTab {
    public static final CreativeModeTab customcrafting = new CreativeModeTab("custom_crafting_items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(item.BLANK_DIAMOND.get());
        }
    };
}
