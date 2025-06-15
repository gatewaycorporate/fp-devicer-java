package org.gatewaycorporate.fpdevicer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.TreeMap;

class JsonUtils {
    private static final Gson gson = new GsonBuilder()
            // .setPrettyPrinting()
            // .disableHtmlEscaping()
            .create();

    static String deterministicJson(Object obj) {
        JsonElement tree = gson.toJsonTree(obj);
        JsonObject sorted = sortJsonObject(tree.getAsJsonObject());
        return gson.toJson(sorted);
    }

    private static JsonObject sortJsonObject(JsonObject obj) {
        JsonObject result = new JsonObject();
        TreeMap<String, JsonElement> sorted = new TreeMap<>(obj.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        for (Map.Entry<String, JsonElement> entry : sorted.entrySet()) {
            if (entry.getValue().isJsonObject()) {
                result.add(entry.getKey(), sortJsonObject(entry.getValue().getAsJsonObject()));
            } else {
                result.add(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    static JsonElement toJsonElement(Object obj) {
        return gson.toJsonTree(obj);
    }

    static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    static Comparison compareObjects(Object o1, Object o2) {
        return new Comparison(compareJsonElements(toJsonElement(o1), toJsonElement(o2)));
    }

    private static int[] compareJsonElements(JsonElement e1, JsonElement e2) {
        int fields = 0;
        int matches = 0;

        if (e1 == null || e2 == null || e1.isJsonNull() || e2.isJsonNull()) {
            return new int[]{fields, matches};
        }

        if (e1.isJsonObject() && e2.isJsonObject()) {
            JsonObject o1 = e1.getAsJsonObject();
            JsonObject o2 = e2.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : o1.entrySet()) {
                String key = entry.getKey();
                if (o2.has(key)) {
                    fields++;
                    int[] sub = compareJsonElements(entry.getValue(), o2.get(key));
                    fields += sub[0] - 1; // Subtract 1 for the key itself
                    matches += sub[1];
                    if (entry.getValue().equals(o2.get(key))) {
                        matches++;
                    }
                }
            }
        } else if (e1.isJsonArray() && e2.isJsonArray()) {
            if (e1.equals(e2)) {
                fields++;
                matches++;
            } else {
                fields++;
            }
        } else {
            fields++;
            if (e1.equals(e2)) {
                matches++;
            }
        }
        return new int[]{fields, matches};
    }
}
