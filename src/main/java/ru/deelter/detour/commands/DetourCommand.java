package ru.deelter.detour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.detour.MyDetour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetourCommand implements TabExecutor {

	public static void setup(@NotNull MyDetour instance) {
		PluginCommand command = instance.getCommand("detour");
		if (command == null) {
			instance.getLogger().warning("Hey.. Command 'detour' not found. Any changes in plugin.yml?");
			return;
		}

		DetourCommand detourCommand = new DetourCommand();
		command.setExecutor(detourCommand);
		command.setTabCompleter(detourCommand);
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
		if (args.length < 1) {
			sender.sendMessage("Недостаточно аргуметов в команде");
			return true;
		}

		String cmd = args[0].toLowerCase();
		switch (cmd) {
			case "join" -> DetourJoinCommand.execute(sender, args);
			case "leave" -> DetourLeaveCommand.execute(sender, args);
			case "start" -> DetourStartCommand.execute(sender, args);
			case "stop" -> DetourStopCommand.execute(sender, args);
			case "reload" -> DetourReloadCommand.execute(sender, args);
			case "next" -> DetourNextCommand.execute(sender, args);
			case "list" -> DetourListCommand.execute(sender, args);
		}
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
		List<String> suggestions = new ArrayList<>();
		if (args.length == 1) {
			suggestions.addAll(Arrays.asList("join", "leave"));
			if (sender.isOp()) suggestions.addAll(Arrays.asList("start", "stop", "next", "list", "reload"));
		}
		return filter(suggestions, args);
	}

	private static @NotNull List<String> filter(@NotNull List<String> variables, String @NotNull [] args) {
		List<String> result = new ArrayList<>();
		String lastArg = args[args.length - 1];
		for (String variable : variables) if (variable.startsWith(lastArg)) result.add(variable);
		return result;
	}
}
