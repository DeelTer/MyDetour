package ru.deelter.detour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deelter.detour.MyDetour;

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
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		return null;
	}
}
