package ru.deelter.detour.managers;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.utils.Detour;

import java.util.concurrent.TimeUnit;

public class DetourManager {

	private static final Detour DETOUR = new Detour();

	public static Detour getDetour() {
		return DETOUR;
	}

	public static void load() {
		DETOUR.loadFromData();
	}

	public static void unload() {
		DETOUR.saveData(false);
	}

	public static void test() {
		Bukkit.getScheduler().runTaskTimer(MyDetour.getInstance(), () -> {

		}, 0, 20L);
	}

	public static @NotNull String getDetourFormattedTime() {
		long startedTimeMs = DETOUR.getStartedTimeMs();
		long currentTimeMs = System.currentTimeMillis();
		long difference = startedTimeMs - currentTimeMs;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		return minutes + " минут";
	}
}
