package ru.deelter.detour.configs;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;

public class DetourConfig {

	public static Component detourEndTitle;
	public static Component detourEndSubtitle;

	public static void load(@NotNull MyDetour instance) {
		FileConfiguration config = instance.getConfig();
		detourEndTitle = Component.text(config.getString("messages.detour-end"));
		detourEndTitle = Component.text(config.getString("messages.detour-end"));
	}

	public static void reload() {
		MyDetour instance = MyDetour.getInstance();
		instance.reloadConfig();
		load(instance);
	}
}
