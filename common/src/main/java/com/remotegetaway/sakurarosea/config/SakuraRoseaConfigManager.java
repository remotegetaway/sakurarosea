package com.remotegetaway.sakurarosea.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.remotegetaway.sakurarosea.SakuraRosea;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SakuraRoseaConfigManager {
	private final Path biomeConfigPath;
	private final Path biomeConfigBackupPath;
	private final Path clientConfigPath;
	private final Path clientConfigBackupPath;
	private SakuraRoseaBiomeConfig biomeConfig;
	private SakuraRoseaClientConfig clientConfig;

	private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

	public SakuraRoseaConfigManager() {
		Path configDirectory = FabricLoader.getInstance().getConfigDir().resolve(SakuraRosea.MOD_ID);

		try {
			Files.createDirectories(configDirectory);
		} catch (IOException e) {
			SakuraRosea.LOGGER.error("Failed to create config directory at " + configDirectory, e);
		}

		biomeConfigPath = configDirectory.resolve("biome.json");
		biomeConfigBackupPath = configDirectory.resolve("biome-invalid-syntax.json");

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			clientConfigPath = configDirectory.resolve("client.json");
			clientConfigBackupPath = configDirectory.resolve("client-invalid-syntax.json");
		} else {
			clientConfigPath = null;
			clientConfigBackupPath = null;
		}

	}

	private static <T> T loadConfig(Path configPath, T defaults, Class<T> clazz, Path backupPath) {
		if (!Files.exists(configPath)) {
			if (!saveConfig(configPath, defaults)) {
				SakuraRosea.LOGGER.error("Unable to save default configuration values for " + configPath);

				// Not much else to do at this point.
				return defaults;
			}
		}

		String content;

		try {
			// Load the entire file first, so that we don't get any weird IO errors halfway through.
			content = Files.readString(configPath);
		} catch (IOException e) {
			SakuraRosea.LOGGER.error("Failed to load Sakura Rosea configuration file at " + configPath, e);
			SakuraRosea.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions");
			SakuraRosea.LOGGER.error("Reverting to default configuration");

			return defaults;
		}

		try {
			return GSON.fromJson(content, clazz);
		} catch (JsonSyntaxException e) {
			SakuraRosea.LOGGER.error("Failed to parse Sakura Rosea configuration file at " + configPath, e);

			// Revert the config so that the user has a fresh start if they need it.
			// It's also possible for the user to delete the config to recreate it, but that seems a bit unintuitive.
			SakuraRosea.LOGGER.error("Reverting to default configuration, ensure that your file has correct syntax");
			saveConfig(configPath, defaults);

			// There are a few websites like this, but this was the first result that came up.
			SakuraRosea.LOGGER.error("In the future, consider using something like https://jsonchecker.com/ to check your syntax");

			// It would be quite annoying if a user just spent 10 minutes editing the file, only for it to be wiped away.
			// Save a backup so they can review the errors and fix them.
			SakuraRosea.LOGGER.error("The previous configuration file content has been written to " + backupPath);

			try {
				Files.writeString(backupPath, content);
			} catch (IOException ioe) {
				SakuraRosea.LOGGER.error("Couldn't save previous configuration file content at " + backupPath, ioe);
				SakuraRosea.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions and that your disk isn't full!");
			}

			return defaults;
		}
	}

	private static <T> boolean saveConfig(Path configPath, T instance) {
		String jsonString = GSON.toJson(instance);

		try {
			Files.writeString(configPath, jsonString);

			return true;
		} catch (IOException e) {
			SakuraRosea.LOGGER.error("Couldn't save Sakura Rosea configuration file at " + configPath, e);
			SakuraRosea.LOGGER.error("This shouldn't happen under normal conditions, ensure that you have the correct permissions and that your disk isn't full!");

			return false;
		}
	}

	public SakuraRoseaBiomeConfig getBiomeConfig() {
		if (biomeConfig == null) {
			biomeConfig = loadConfig(biomeConfigPath, new SakuraRoseaBiomeConfig(), SakuraRoseaBiomeConfig.class, biomeConfigBackupPath);
		}

		return biomeConfig;
	}

	public SakuraRoseaClientConfig getClientConfig() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			throw new UnsupportedOperationException("Cannot get the Sakura Rosea client configuration when not on the client.");
		}

		if (clientConfig == null) {
			clientConfig = loadConfig(clientConfigPath, new SakuraRoseaClientConfig(), SakuraRoseaClientConfig.class, clientConfigBackupPath);
		}

		return clientConfig;
	}

}
