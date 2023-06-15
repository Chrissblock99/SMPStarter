package me.chriss99.smpstarter.util.reflectivecommand;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@FunctionalInterface
public interface OptionEnumerator {
    @NotNull List<String> listStrings(@NotNull Class<?> listFor, @NotNull String input);
}
