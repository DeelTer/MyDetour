package ru.deelter.detour.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.managers.DetourDataManager;
import ru.deelter.detour.managers.DetourManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Detour {

	private final List<DetourPlayer> players = new ArrayList<>();
	private long startedTimeMs;
	private DetourPlayer currentPlayer;

	public List<DetourPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<DetourPlayer> players) {
		this.players.addAll(players);
	}

	public void addPlayer(@NotNull DetourPlayer player) {
		this.players.add(player);
	}

	public void removePlayer(@NotNull DetourPlayer player) {
		this.players.remove(player);
	}

	public @Nullable DetourPlayer getPlayerByUuid(@NotNull UUID uuid) {
		for (DetourPlayer player : this.players) {
			if (player.getUuid().equals(uuid))
				return player;
		}
		return null;
	}

	public int getPlayerPoint(@NotNull DetourPlayer player) {
		return this.players.indexOf(player) + 1;
	}

	public long getStartedTimeMs() {
		return startedTimeMs;
	}

	public void setStartedTimeMs(long startedTimeMs) {
		this.startedTimeMs = startedTimeMs;
	}

	public @Nullable DetourPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isStarted() {
		return startedTimeMs != 0L;
	}

	public void start() {
		Bukkit.broadcast(Component.text("Обход начался! Вступите в него командой /detour join"));
		MyDetour.getInstance().getLogger().info("Detour started");
		this.clearStats();
		this.startedTimeMs = System.currentTimeMillis();
	}

	public @Nullable DetourPlayer nextPlayer() {
		if (this.players.isEmpty()) {
			this.stop();
			return null;
		}
		this.currentPlayer = players.get(0);
		this.players.remove(0);
		return this.currentPlayer;
	}

	public void stop() {
		MyDetour.getInstance().getLogger().info("Detour ended");
		Bukkit.broadcast(Component.text("Обход закончился! Время обхода: " + DetourManager.getDetourFormattedTime()));
		this.clearStats();
//		Title title = Title.title(titleComp, subtitleComp, times);
//		Audience.audience(Bukkit.getOnlinePlayers()).showTitle(title);
	}

	private void clearStats() {
		this.players.clear();
		this.startedTimeMs = 0L;
		this.currentPlayer = null;
	}

	public void saveData(boolean forced) {
		if (forced || this.isStarted())
			DetourDataManager.saveData(this);
	}

	public void loadFromData() {
		DetourDataManager.loadData(this);
	}
}
