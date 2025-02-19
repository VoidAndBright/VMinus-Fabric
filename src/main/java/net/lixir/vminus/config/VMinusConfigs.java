package net.lixir.vminus.config;

import com.terraformersmc.modmenu.config.option.BooleanConfigOption;
import com.terraformersmc.modmenu.config.option.OptionConvertable;
import net.minecraft.client.option.SimpleOption;

import java.util.Arrays;

public class VMinusConfigs {
    public static final BooleanConfigOption TOOLTIP_REWORK = new BooleanConfigOption("tooltip_rework", true);

    public SimpleOption<?>[] get_configs(){
        return Arrays.stream(VMinusConfigs.class.getDeclaredFields()).map(field->{
            try {
                return ((OptionConvertable)field.get(null)).asOption();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toArray(SimpleOption<?>[]::new);
    }
}
