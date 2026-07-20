package com.wdcftgg.twilightdelight.common.network;

import com.wdcftgg.twilightdelight.TwilightDelightLegacy;
import com.wdcftgg.twilightdelight.common.TwilightDelightConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class TwilightDelightNetwork {

    private static final SimpleNetworkWrapper CHANNEL =
            NetworkRegistry.INSTANCE.newSimpleChannel(TwilightDelightLegacy.MOD_ID);

    private TwilightDelightNetwork() {
    }

    public static void init() {
        CHANNEL.registerMessage(ConfigSyncHandler.class, ConfigSyncMessage.class, 0, Side.CLIENT);
    }

    public static void sendConfig(EntityPlayerMP player) {
        CHANNEL.sendTo(new ConfigSyncMessage(TwilightDelightConfig.effectRange, TwilightDelightConfig.auroraRange), player);
    }

    public static final class ConfigSyncMessage implements IMessage {

        private int effectRange;
        private int auroraRange;

        public ConfigSyncMessage() {
        }

        private ConfigSyncMessage(int effectRange, int auroraRange) {
            this.effectRange = effectRange;
            this.auroraRange = auroraRange;
        }

        @Override
        public void fromBytes(ByteBuf buffer) {
            this.effectRange = buffer.readInt();
            this.auroraRange = buffer.readInt();
        }

        @Override
        public void toBytes(ByteBuf buffer) {
            buffer.writeInt(this.effectRange);
            buffer.writeInt(this.auroraRange);
        }
    }

    public static final class ConfigSyncHandler implements IMessageHandler<ConfigSyncMessage, IMessage> {

        @Override
        public IMessage onMessage(ConfigSyncMessage message, MessageContext context) {
            FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(
                    () -> TwilightDelightConfig.applyServerRanges(message.effectRange, message.auroraRange));
            return null;
        }
    }
}
