package ru.deelter.detour.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class DetourPlayer {

	private final UUID uuid;
	private final String name;
	private Location lastLocation;

	public DetourPlayer(@NotNull UUID uuid, @NotNull String name, Location lastLocation) {
		this.uuid = uuid;
		this.name = name;
		this.lastLocation = lastLocation;
	}

	public UUID getUuid() {
		return uuid;
	}

	public @Nullable Player getBukkitPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public @NotNull Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	public @NotNull Location getCurrentLocation() {
		Player bukkitPlayer = this.getBukkitPlayer();
		if (bukkitPlayer == null || !bukkitPlayer.isOnline()) return this.lastLocation;
		return bukkitPlayer.getLocation();
	}

	public @NotNull String getName() {
		return name;
	}

	@Contract("_ -> new")
	public static @NotNull DetourPlayer from(@NotNull Player player) {
		return new DetourPlayer(player.getUniqueId(), player.getName(), player.getLocation());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DetourPlayer that = (DetourPlayer) o;
		return Objects.equals(uuid, that.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}
}
