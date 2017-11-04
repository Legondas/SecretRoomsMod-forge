package com.wynprice.secretroomsmod;

import java.io.File;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public class SecretConfig 
{		
	public static final Configuration CONFIG = new Configuration(new File(Loader.instance().getConfigDir(), SecretRooms5.MODID + ".cfg"));
	public static final String CATEGORYGENRAL = "general";

	static
	{
		syncConfig();
	}
	
	public static String[] forcedBlockColors;
	public static boolean updateChecker;
		
	
	public static void syncConfig()
	{
		CONFIG.load(); 
		
		Property doUpdate = CONFIG.get(CATEGORYGENRAL, "update_checker", true);
		doUpdate.setComment(new TextComponentTranslation("config.do_update.comment").getUnformattedText());
		
		Property allowedBlockColors = CONFIG.get(CATEGORYGENRAL, "forced_blockcolors", new String[]{
				"minecraft:tallgrass",
				"minecraft:reeds",
				"minecraft:double_plant"
		});
		allowedBlockColors.setComment(new TextComponentTranslation("config.blockcolors.comment").getUnformattedText());
		
			forcedBlockColors = allowedBlockColors.getStringList();
			updateChecker = doUpdate.getBoolean();
		
		allowedBlockColors.set(forcedBlockColors);
		doUpdate.set(updateChecker);
		
		if(CONFIG.hasChanged())
			CONFIG.save();
	}
}
