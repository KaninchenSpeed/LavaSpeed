package de.itotterstadt.lavaspeed.listeners

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.util.Vector

class MoveListener: Listener {

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (event.player.location.add(Vector(0f, 0.75f, 0f)).block.type != Material.LAVA) {
            event.player.walkSpeed = 0.2f
            event.player.isFlying = false
            if (event.player.gameMode == GameMode.SURVIVAL || event.player.gameMode == GameMode.ADVENTURE) {
                event.player.allowFlight = false
            }
            return
        }

        event.player.allowFlight = true
        event.player.isFlying = true
        event.player.walkSpeed = 0.3f
        event.player.flySpeed = 0.05f
    }

}