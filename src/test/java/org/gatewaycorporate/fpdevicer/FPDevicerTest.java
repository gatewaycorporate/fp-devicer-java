package org.gatewaycorporate.fpdevicer;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FPDevicerTest {
    @Test
    void testCalculateConfidence() {
        Fingerprint f1 = Fingerprint.builder()
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .hardware(Fingerprint.Hardware.builder()
                        .cpu("Intel Core i7")
                        .gpu("NVIDIA GeForce GTX 1080")
                        .ram(16384) // 16 GB
                        .build())
                .screen(Fingerprint.Screen.builder()
                        .width(1920)
                        .height(1080)
                        .colorDepth(24)
                        .build())
                .timezone("America/New_York")
                .ip("0.0.0.0")
                .languages(Lists.newArrayList("en-US", "en"))
                .plugins(Lists.newArrayList("Chrome PDF Viewer", "Shockwave Flash"))
                .canvasHash("1234567890abcdef")
                .audioHash("abcdef1234567890")
                .webglHash("fedcba0987654321")
                .build();

        Fingerprint f2 = Fingerprint.builder()
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .hardware(Fingerprint.Hardware.builder()
                        .cpu("Intel Core i7")
                        .gpu("NVIDIA GeForce GTX 1080")
                        .ram(16384) // 16 GB
                        .build())
                .screen(Fingerprint.Screen.builder()
                        .width(1920)
                        .height(1080)
                        .colorDepth(24)
                        .build())
                .timezone("America/New_York")
                .ip("0.0.0.0")
                .languages(Lists.newArrayList("en-US", "en"))
                .plugins(Lists.newArrayList("Chrome PDF Viewer", "Shockwave Flash"))
                .canvasHash("1234567890abcdef")
                .audioHash("abcdef1234567890")
                .webglHash("fedcba0987654321")
                .build();

        Double confidence = FPDevicer.calculateConfidence(f1, f2);

        assertNotNull(confidence);
    }
}
