package net.lixir.vminus.vision.deserielizer;

import com.google.gson.*;
import lixir.vminus.vision.type.TestVision;

import java.lang.reflect.Type;

public class TestVisionDeserializer implements JsonDeserializer<TestVision> {
    @Override
    public TestVision deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json_object = jsonElement.getAsJsonObject();

        JsonArray banned = json_object.get("banned").getAsJsonArray();
        return new TestVision(banned.get(0).getAsBoolean());
    }
}
