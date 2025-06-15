package org.gatewaycorporate.fpdevicer;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a device fingerprint containing various attributes that can be used to uniquely identify a device.
 * This class is used to encapsulate the data collected from a device for fingerprinting purposes.
 */
@Data
@Builder
public class Fingerprint {
    private List<String> fonts;
    private Hardware hardware;
    private String userAgent;
    private Screen screen;
    private String timezone;
    private String ip;
    private List<String> languages;
    private List<String> plugins;
    private String canvasHash;
    private String audioHash;
    private String webglHash;

    @Data
    @Builder
    public static class Hardware {
        private String cpu;
        private String gpu;
        private int ram; // in MB
    }

    @Data
    @Builder
    public static class Screen {
        private int width;
        private int height;
        private int colorDepth;
    }

    /**
     * Converts a JSON string representation of a Fingerprint object into a Fingerprint instance
     *
     * @return A Fingerprint instance.
     */
    public static Fingerprint fromJson(String json) {
        return JsonUtils.fromJson(json, Fingerprint.class);
    }
}
