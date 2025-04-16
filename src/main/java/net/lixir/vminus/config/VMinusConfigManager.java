package net.lixir.vminus.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.cape.Cape;

import java.io.*;

public class VMinusConfigManager {
    private static File file;

    private static void setup_config_file() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), VMinus.MOD_ID + ".json");
        }
    }

    public static void load() {
        setup_config_file();

        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                VMinusConfigs.CAPE.setValue(Cape.from_string(json.get("cape").getAsString()));
            }
            else save();
        } catch (FileNotFoundException error) {
            VMinus.LOGGER.info("Couldn't find VMinus configuration file");
            VMinus.LOGGER.info(error.toString());
        }
    }
    public static void save() {
        setup_config_file();

        JsonObject config = new JsonObject();
        config.addProperty("cape", Cape.to_string(VMinusConfigs.CAPE.getValue()));

        String json_string = VMinus.GSON.toJson(config);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json_string);
        } catch (IOException error) {
            VMinus.LOGGER.info("Couldn't save VMinus configuration file");
            VMinus.LOGGER.info(error.toString());
        }
    }
}