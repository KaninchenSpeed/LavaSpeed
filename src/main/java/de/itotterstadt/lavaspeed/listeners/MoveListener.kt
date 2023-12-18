package de.itotterstadt.lavaspeed.listeners

import de.itotterstadt.lavaspeed.configuration
import de.itotterstadt.lavaspeed.plugin
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.util.Vector

class MoveListener: Listener {

    private val teams = configuration?.getStringList("teams")
    private val lavaSpeed = configuration?.getDouble("lavaSpeed")?.toFloat() ?: 0.3f
    private val lavaSpeedEffect = configuration?.getDouble("lavaSpeedEffect")?.toFloat() ?: 0.05f

    init {
        if (configuration!!.get("lavaSpeed") == null) {
            configuration!!.set("lavaSpeed", 0.05f)
            configuration!!.save()
        }
        if (configuration!!.get("lavaSpeedEffect") == null) {
            configuration!!.set("lavaSpeedEffect", 0.3f)
            configuration!!.save()
        }
    }

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (event.player.location.add(Vector(0f, 0.75f, 0f)).block.type != Material.LAVA) {
            event.player.walkSpeed = 0.2f
            if (event.player.gameMode != GameMode.SPECTATOR) {
                event.player.flySpeed = 0.1f
            }
            if (event.player.gameMode == GameMode.SURVIVAL || event.player.gameMode == GameMode.ADVENTURE) {
                event.player.allowFlight = false
                event.player.isFlying = false
            } else {
                event.player.allowFlight = true
            }
            return
        }


        val team = plugin!!.server.scoreboardManager.mainScoreboard.teams.find {
            it.hasPlayer(event.player)
        }
        if (teams != null && teams.size > 0 && (team == null || !teams.contains(team.name))) {
            return
        }

        event.player.allowFlight = true
        event.player.isFlying = true
        event.player.walkSpeed = lavaSpeedEffect
        event.player.flySpeed = lavaSpeed
    }

}