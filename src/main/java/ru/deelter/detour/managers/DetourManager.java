package ru.deelter.detour.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

import java.util.concurrent.TimeUnit;

public class DetourManager {

	private static final Detour DETOUR = new Detour();

	public static Detour getDetour() {
		return DETOUR;
	}

	public static void load() {
		DETOUR.loadFromData();
		runPlayerGlowingTimer();
	}

	public static void unload() {
		DETOUR.saveData(false);
	}

	public static void runPlayerGlowingTimer() {
		Bukkit.getScheduler().runTaskTimer(MyDetour.getInstance(), () -> {
			if (DETOUR.isStarted()) {
				DetourPlayer detourPlayer = DETOUR.getCurrentPlayer();
				if (detourPlayer == null) return;

				Player player = detourPlayer.getBukkitPlayer();
				if (player == null || !player.isOnline()) return;

				player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 70, 1, false, false));
			}
		}, 0, 60L);
	}

	public static @NotNull String getDetourFormattedTime() {
		long startedTimeMs = DETOUR.getStartedTimeMs();
		long currentTimeMs = System.currentTimeMillis();
		long difference = currentTimeMs - startedTimeMs;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		return minutes + " мин";
	}
}
