package ru.deelter.detour.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.managers.DetourManager;
import ru.deelter.detour.utils.Detour;

public class StartCommand {

	public static void execute(@NotNull CommandSender sender, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("У вас нет прав на выполнение этой команды");
			return;
		}
		Detour detour = DetourManager.getDetour();
		if (detour.isStarted()) {
			sender.sendMessage("Обход уже начался!");
			return;
		}
		detour.start();
	}
}
