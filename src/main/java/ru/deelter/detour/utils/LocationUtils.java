package ru.deelter.detour.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class LocationUtils {

	@NotNull
	public static Location fromString(@NotNull String str, @NotNull String splitSymbol) {
		String[] args = str.split(splitSymbol);
		if (args.length != 4) throw new RuntimeException("Missing arguments");

		World world = Bukkit.getWorld(args[0]);
		if (world == null) throw new RuntimeException("World is null");
		return new Location(world, Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}

	@NotNull
	public static Location fromString(@NotNull String str) {
		return fromString(str, ",");
	}

	@NotNull
	public static String toString(@NotNull Location loc) {
		return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ();
	}

	public static void init() {
	}
}
