package ru.deelter.detour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.deelter.detour.MyDetour;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

public class PlayerDetourActionListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Detour detour = DetourManager.getDetour();
		if (detour.isStarted()) {
			Player player = event.getPlayer();
			DetourPlayer detourPlayer = detour.getPlayerByUuid(player.getUniqueId());
			if (detourPlayer != null) {
				detourPlayer.setLastLocation(player.getLocation());
				MyDetour.getInstance().getLogger().info("Player " + player.getName() + " leave the game. We saved his last location");
			}
		}
	}
}
