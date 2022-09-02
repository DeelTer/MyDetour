package ru.deelter.detour;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class DetourConfig {

	private final MyDetour instance;
	private Component detourStartMessage;
	private Component detourEndMessage;

	public DetourConfig(@NotNull MyDetour instance) {
		this.instance = instance;
		this.load();
	}
	public void load() {
		FileConfiguration config = this.instance.getConfig();
		this.detourStartMessage = Component.text(config.getString("messages.detour-start"));
		this.detourEndMessage = Component.text(config.getString("messages.detour-end"));
	}

	public void reload() {
		MyDetour instance = MyDetour.getInstance();
		instance.reloadConfig();
		this.load();
	}

	public Component getDetourStartMessage() {
		return detourStartMessage;
	}

	public Component getDetourEndMessage() {
		return detourEndMessage;
	}
}
