package ru.deelter.detour.managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.utils.Detour;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetourDataManager {

	private static final MyDetour INSTANCE = MyDetour.getInstance();

	private static final String fileName = "config.yml";

	private static File configFile;
	private static YamlConfiguration config;

	public static void setupFiles() {
		configFile = new File(INSTANCE.getDataFolder() + File.separator + fileName);
		if (!configFile.exists()) INSTANCE.saveResource(fileName, false);
		config = YamlConfiguration.loadConfiguration(configFile);
	}

	public static void saveConfigFile() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveData(@NotNull Detour detour) {
		config.set("detour.time", detour.getStartedTimeMs());
		config.set("detour.point", detour.getPoint());

		List<String> playerUuids = new ArrayList<>();
		detour.getPlayers().forEach(player -> playerUuids.add(player.getUniqueId().toString()));
		config.set("detour.players", playerUuids);
	}

	public static void loadData(@NotNull Detour detour) {
		ConfigurationSection detourSection = config.getConfigurationSection("detour");
		if (detourSection == null) {
			MyDetour.getInstance().getLogger().info("Previous detour data not found. All ok");
			return;
		}
		long timeMills = config.getLong("detour.time");
		if (timeMills == 0L) return; // Not started

		detour.setStartedTimeMs(timeMills);

		int point = config.getInt("detour.point");
		detour.setPoint(point);

		List<Player> players = new ArrayList<>();
		config.getStringList("detour.players").forEach(uuid -> {
			Player player = Bukkit.getPlayer(uuid);
			players.add(player);
		});
		detour.setPlayers(players);
		MyDetour.getInstance().getLogger().info("Previous detour data loaded successfully");
	}
}
