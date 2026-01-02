package com.prayujt.minecraft.FireballTrident.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class Messages {
    private Messages() {}

    public static Component success(String msg) {
        return Component.text(msg, NamedTextColor.GREEN);
    }

    public static Component error(String msg) {
        return Component.text(msg, NamedTextColor.RED);
    }

    public static Component info(String msg) {
        return Component.text(msg, NamedTextColor.YELLOW);
    }
}
