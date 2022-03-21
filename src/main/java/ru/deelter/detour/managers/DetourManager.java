package ru.deelter.detour.managers;

import org.bukkit.Bukkit;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.utils.Detour;

public class DetourManager {

	public static final Detour DETOUR = new Detour();

	public static void load() {
		DETOUR.loadFromData();
	}

	public static void unload() {
		DETOUR.saveData();
	}

	public static void test() {
		Bukkit.getScheduler().runTaskTimer(MyDetour.getInstance(), () -> {

		}, 0, 20L);
	}
}
