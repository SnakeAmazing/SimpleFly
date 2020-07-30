package me.snakeamazing.menus;

import org.bukkit.entity.Player;
import team.unnamed.gui.menu.MenuBuilder;

public interface Menu {
    MenuBuilder create(Player player);
}
