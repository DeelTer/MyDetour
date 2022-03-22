package ru.deelter.detour.managers;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;
import ru.deelter.detour.utils.LocationUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetourDataManager {

	private static final MyDetour INSTANCE = MyDetour.getInstance();

	private static final String fileName = "detour-data.yml";
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
		detour.getPlayers().forEach(detourPlayer -> {
			ConfigurationSection section = config.createSection("detour.players." + detourPlayer.getUuid());
			String shortFormatLocation = LocationUtils.toString(detourPlayer.getLastLocation());
			section.set("location", shortFormatLocation);
			section.set("name", detourPlayer.getName());
		});
		saveConfigFile();
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

		List<DetourPlayer> players = new ArrayList<>();
		ConfigurationSection playersSection = config.getConfigurationSection("detour.players");
		if (playersSection != null) {
			playersSection.getKeys(false).forEach(uuidRaw -> {
				UUID uuid = UUID.fromString(uuidRaw);
				String name = playersSection.getString(uuidRaw + ".name");
				if (name == null) {
					MyDetour.getInstance().getLogger().info("Data error. Player name is null (" + uuidRaw + ")");
					return;
				}
				String rawLocation = playersSection.getString(uuidRaw + ".location");
				if (rawLocation == null) {
					MyDetour.getInstance().getLogger().info("Data error. Player location is null (" + uuidRaw + ")");
					return;
				}
				Location location = LocationUtils.fromString(rawLocation);
				DetourPlayer detourPlayer = new DetourPlayer(uuid, name, location);
				players.add(detourPlayer);
			});
		}
		detour.setPlayers(players);
		config.set("detour", null);
		saveConfigFile();
		MyDetour.getInstance().getLogger().info("Previous detour data loaded successfully");
	}
}
