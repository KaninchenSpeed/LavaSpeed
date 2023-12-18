package de.itotterstadt.lavaspeed

import de.itotterstadt.lavaspeed.listeners.MoveListener
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

var plugin: JavaPlugin? = null
class LavaSpeed : JavaPlugin() {
    override fun onEnable() {
        plugin = this
        registerListener()
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
    }

    private fun registerListener() {
        server.pluginManager.registerEvents(MoveListener(), this)
    }
}
