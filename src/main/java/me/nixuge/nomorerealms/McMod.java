package me.nixuge.nomorerealms;

import lombok.Getter;
import lombok.Setter;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = McMod.MOD_ID,
        name = McMod.NAME,
        version = McMod.VERSION,
        clientSideOnly = true
)

@Setter
public class McMod {
    public static final String MOD_ID = "nomorerealms";
    public static final String NAME = "No More Realms";
    public static final String VERSION = "1.0.0";


    @Getter
    @Mod.Instance(value = McMod.MOD_ID)
    private static McMod instance;
}
