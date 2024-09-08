package dev.dubhe.anvilcraft.event.server;

import net.neoforged.bus.api.SubscribeEvent;
import dev.dubhe.anvilcraft.api.event.server.ServerEndDataPackReloadEvent;
import dev.dubhe.anvilcraft.api.event.server.ServerStartedEvent;
import dev.dubhe.anvilcraft.api.hammer.HammerManager;
import dev.dubhe.anvilcraft.api.world.load.LevelLoadManager;
import dev.dubhe.anvilcraft.init.ModHammerInits;
import org.jetbrains.annotations.NotNull;

public class ServerEventListener {
    /**
     * 服务器开启事件
     *
     * @param event 事件
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarted(@NotNull ServerStartedEvent event) {
        ModHammerInits.init();
        HammerManager.register();
        LevelLoadManager.notifyServerStarted();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerEndDataPackReload(@NotNull ServerEndDataPackReloadEvent event) {
    }

}
