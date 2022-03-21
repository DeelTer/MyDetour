package ru.deelter.detour.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.managers.DetourDataManager;

import java.util.ArrayList;
import java.util.List;

public class Detour {

	private final List<Player> players = new ArrayList<>();
	private int point;
	private long startedTimeMs;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players.addAll(players);
	}

	public void addPlayer(@NotNull Player player) {
		this.players.add(player);
	}

	public void removePlayer(@NotNull Player player) {
		this.players.remove(player);
	}

	public long getStartedTimeMs() {
		return startedTimeMs;
	}

	public void setStartedTimeMs(long startedTimeMs) {
		this.startedTimeMs = startedTimeMs;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isStarted() {
		return startedTimeMs != 0L;
	}

	public void start() {
		MyDetour.getInstance().getLogger().info("Detour started");
		this.clearStats();
		this.startedTimeMs = System.currentTimeMillis();
	}

	public @Nullable Player nextPlayer() {
		MyDetour.getInstance().getLogger().info("Detour point: " + point);
		if (players.size() < point) {
			this.end();
			return null;
		}
		return players.get(point++);
	}

	public @NotNull Player getCurrentPlayer() {
		if (players.isEmpty()) throw new RuntimeException("Players list is empty");
		return players.get(point);
	}

	public void end() {
		this.clearStats();
//		Title title = Title.title(titleComp, subtitleComp, times);
//		Audience.audience(Bukkit.getOnlinePlayers()).showTitle(title);
		MyDetour.getInstance().getLogger().info("Detour ended");
	}

	private void clearStats() {
		this.players.clear();
		this.point = 0;
		this.startedTimeMs = 0L;
	}

	public void saveData() {
		if (this.isStarted())
			DetourDataManager.saveData(this);
	}

	public void loadFromData() {
		DetourDataManager.loadData(this);
	}
}
