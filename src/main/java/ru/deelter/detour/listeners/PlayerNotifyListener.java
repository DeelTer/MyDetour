package ru.deelter.detour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.deelter.detour.managers.DetourManager;

public class PlayerNotifyListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (DetourManager.getDetour().isStarted()) {
			Player player = event.getPlayer();
			player.sendMessage("Обход уже начался! Чтобы присоединиться - пиши /detour join");
		}
	}
}
