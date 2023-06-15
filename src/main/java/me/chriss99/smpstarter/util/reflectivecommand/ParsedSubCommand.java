package me.chriss99.smpstarter.util.reflectivecommand;

import org.jetbrains.annotations.NotNull;

public record ParsedSubCommand(@NotNull SubCommand subCommand, @NotNull Object[] parameters) {
}
