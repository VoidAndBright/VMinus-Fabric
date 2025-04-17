package net.lixir.vminus;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lixir.vminus.datagen.*;

public class VMinusDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(VMinusLangProvider::new);
		pack.addProvider(VMinusItemTagProvider::new);
		pack.addProvider(VMinusBlockTagProvider::new);
		pack.addProvider(VMinusBiomeTagProvider::new);
		pack.addProvider(VMinusEntityTypeTagProvider::new);
		pack.addProvider(VMinusDamageTypeTagProvider::new);
	}
}
