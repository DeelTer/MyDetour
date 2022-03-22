package ru.deelter.detour.configs;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import ru.deelter.detour.MyDetour;

public class DetourConfig {

	private static final MyDetour INSTANCE = MyDetour.getInstance();
	public static Component detourEndTitle;
	public static Component detourEndSubtitle;

	public static void loadValues() {
		FileConfiguration config = INSTANCE.getConfig();


		detourEndTitle = Component.text(config.getString("messages.detour-end"));
		detourEndTitle = Component.text(config.getString("messages.detour-end"));
	}

	public static void reload() {
		INSTANCE.reloadConfig();
		loadValues();
	}
}
