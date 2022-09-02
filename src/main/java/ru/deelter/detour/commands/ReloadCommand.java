package ru.deelter.detour.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.MyDetour;

public class ReloadCommand {

	public static void execute(@NotNull CommandSender sender, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("У вас нет прав на выполнение этой команды");
			return;
		}
		MyDetour.getInstance().getDetourConfig().reload();
		sender.sendMessage("Конфиг успешно перезагружен");
	}
}
