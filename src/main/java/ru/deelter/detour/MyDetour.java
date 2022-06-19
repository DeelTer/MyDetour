package ru.deelter.detour;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.deelter.detour.commands.DetourCommand;
import ru.deelter.detour.configs.DetourConfig;
import ru.deelter.detour.listeners.PlayerDetourActionListener;
import ru.deelter.detour.listeners.PlayerNotifyListener;
import ru.deelter.detour.managers.DetourDataManager;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.LocationUtils;

public final class MyDetour extends JavaPlugin {

	private static MyDetour instance;

	public static MyDetour getInstance() {
		return instance;
	}

	@Override
	public void onLoad() {
		instance = this;
		this.saveDefaultConfig();
	}

	@Override
	public void onEnable() {
		// Plugin startup logic
		DetourConfig.load(this);
		DetourDataManager.setupFiles();
		DetourManager.load();

		DetourCommand.setup(this);
		LocationUtils.init();
		this.setupListeners();
	}

	private void setupListeners() {
		PluginManager manager = Bukkit.getPluginManager();
		manager.registerEvents(new PlayerNotifyListener(), this);
		manager.registerEvents(new PlayerDetourActionListener(), this);
	}

	@Override
	public void onDisable() {
		DetourManager.unload();
	}
}
