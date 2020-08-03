package me.snakeamazing.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FlyEvent extends Event implements Cancellable {

    private Player p;
    private boolean Cancel = false;

    public FlyEvent(Player p){
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    @Override
    public boolean isCancelled() {
        return this.Cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.Cancel = cancel;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

}
