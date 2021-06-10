package me.cockram.mark.slpph;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ServerListPingProtocolHandler extends Plugin {

    @Override
    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ServerListPingProtocolManager failed to load!");
                return;
            }
        }
        Configuration config;
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ServerListPingProtocolManager failed to load!");
            return;
        }

        getProxy().getPluginManager().registerListener(this, new ServerListPingListener(
                config.getInt("mimimumprotocol"),
                config.getInt("maximumprotocol"),
                config.getInt("recommendedprotocol"),
                config.getString("supportedversions")
        ));
    }
}
