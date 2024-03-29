package ru.deelter.detour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

public class PlayerNotifyListener implements Listener {

	@EventHandler
	public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Detour detour = DetourManager.getDetour();
		if (detour.isStarted()) {
			DetourPlayer detourPlayer = detour.getPlayerByUuid(player.getUniqueId());
			if (detourPlayer != null) {
				player.sendMessage("Ура, вы уже в обходе!");
				return;
			}
			player.sendMessage("Обход уже начался! Чтобы присоединиться - пиши /detour join");
		}
	}
}
