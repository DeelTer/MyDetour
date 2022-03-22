package ru.deelter.detour.commands;

import org.bukkit.Location;
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

		DetourPlayer detourPlayer = detour.nextPlayer();
		if (detourPlayer == null) {
			sender.sendMessage("В обходе больше не осталось участников");
			return;
		}
		sender.sendMessage("Добро пожаловать к игроку " + detourPlayer.getName() + "! Осталось участников: " + detour.getPlayers().size());

		Location location = detourPlayer.getCurrentLocation();
		player.teleport(location);
	}
}
