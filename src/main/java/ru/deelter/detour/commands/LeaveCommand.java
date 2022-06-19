package ru.deelter.detour.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

public class LeaveCommand {

	public static void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player player)) {
			sender.sendMessage("Эту команду может выполнить только игрок");
			return;
		}

		Detour detour = DetourManager.getDetour();
		if (!detour.isStarted()) {
			sender.sendMessage("Обход ещё не начался!");
			return;
		}
		DetourPlayer detourPlayer = DetourPlayer.from(player);
		if (!detour.getPlayers().contains(detourPlayer)) {
			sender.sendMessage("Вы не участвуете в обходе!");
			return;
		}
		detour.removePlayer(detourPlayer);
		sender.sendMessage("Вы покинули обход!");
	}
}
