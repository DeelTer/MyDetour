package ru.deelter.detour.commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

public class DetourNextCommand {

	public static void execute(@NotNull CommandSender sender, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("У вас нет прав на выполнение этой команды");
			return;
		}
		if (!(sender instanceof Player player)) {
			sender.sendMessage("Эту команду может выполнить только игрок");
			return;
		}

		Detour detour = DetourManager.getDetour();
		if (!detour.isStarted()) {
			sender.sendMessage("Обход ещё не начался!");
			return;
		}

		DetourPlayer prevDetourPlayer = detour.getCurrentPlayer();
		if (prevDetourPlayer != null) {
			Player previousPlayer = prevDetourPlayer.getBukkitPlayer();
			if (previousPlayer != null && previousPlayer.isOnline()) {
				previousPlayer.sendMessage("Обходящий переходит к следующему участнику. Можете выдохнуть");
				previousPlayer.playSound(previousPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
			}
		}

		DetourPlayer detourPlayer = detour.nextPlayer();
		if (detourPlayer == null) {
			sender.sendMessage("В обходе больше не осталось участников");
			return;
		}
		Location location = detourPlayer.getCurrentLocation();
		player.teleport(location);

		Player bukkitPlayer = detourPlayer.getBukkitPlayer();
		if (bukkitPlayer == null || !bukkitPlayer.isOnline()) {
			sender.sendMessage("Добро пожаловать к игроку " + detourPlayer.getName() + ". К сожалению, сейчас он не онлайн");
			return;
		}
		sender.sendMessage("Добро пожаловать к игроку " + detourPlayer.getName() + "! Осталось: " + detour.getPlayers().size());
		bukkitPlayer.sendMessage("До вас дошла очередь в обходе. Можете начинать действовать");
		bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
	}
}
