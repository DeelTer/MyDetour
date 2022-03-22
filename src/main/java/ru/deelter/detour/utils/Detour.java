package ru.deelter.detour.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.managers.DetourDataManager;

import java.util.ArrayList;
import java.util.List;

public class Detour {

	private final List<DetourPlayer> players = new ArrayList<>();
	private long startedTimeMs;

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

	public int getPlayerPoint(@NotNull DetourPlayer player) {
		return this.players.indexOf(player) + 1;
	}

	public long getStartedTimeMs() {
		return startedTimeMs;
	}

	public void setStartedTimeMs(long startedTimeMs) {
		this.startedTimeMs = startedTimeMs;
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
		if (players.isEmpty()) {
			this.stop();
			return null;
		}
		DetourPlayer detourPlayer = players.get(0);
		players.remove(0);
		return detourPlayer;
	}

	public @NotNull DetourPlayer getCurrentPlayer() {
		if (players.isEmpty()) throw new RuntimeException("Players list is empty");
		return players.get(0);
	}

	public void stop() {
		Bukkit.broadcast(Component.text("Обход закончился!"));
		this.clearStats();
//		Title title = Title.title(titleComp, subtitleComp, times);
//		Audience.audience(Bukkit.getOnlinePlayers()).showTitle(title);
		MyDetour.getInstance().getLogger().info("Detour ended");
	}

	private void clearStats() {
		this.players.clear();
		this.startedTimeMs = 0L;
	}

	public void saveData(boolean forced) {
		if (forced || this.isStarted())
			DetourDataManager.saveData(this);
	}

	public void loadFromData() {
		DetourDataManager.loadData(this);
	}
}
