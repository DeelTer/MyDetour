package ru.deelter.detour.commands;

import org.bukkit.command.CommandSender;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;
import ru.deelter.detour.utils.DetourPlayer;

import java.util.List;

public class DetourListCommand {

	public static void execute(CommandSender sender, String[] args) {
		Detour detour = DetourManager.getDetour();
		if (!detour.isStarted()) {
			sender.sendMessage("Обход ещё не начался!");
			return;
		}

		List<DetourPlayer> players = detour.getPlayers();
		StringBuilder s = new StringBuilder("Участники обхода [" + players.size() + "]:\n");
		players.forEach(detourPlayer -> s.append(detourPlayer.getName()).append(", "));

		String substring = s.substring(0, s.length() - 2);
		sender.sendMessage(substring);
	}
}
