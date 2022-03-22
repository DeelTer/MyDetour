package ru.deelter.detour.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.deelter.detour.configs.DetourConfig;
import ru.deelter.detour.managers.DetourManager;

public class DetourReloadCommand {

	public static void execute(@NotNull CommandSender sender, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("У вас нет прав на выполнение этой команды");
			return;
		}
		DetourConfig.reload();
		sender.sendMessage("Конфиг успешно перезагружен");
	}
}
