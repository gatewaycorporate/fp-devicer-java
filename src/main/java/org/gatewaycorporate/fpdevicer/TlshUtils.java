// create class TlshService.java
package org.gatewaycorporate.fpdevicer;

import com.trendmicro.tlsh.Tlsh;
import com.trendmicro.tlsh.TlshCreator;


class TlshUtils {
    static Integer getDifferenceScore(Fingerprint f1, Fingerprint f2) {
        Tlsh t1 = getTlshFromFingerprint(f1);
        Tlsh t2 = getTlshFromFingerprint(f2);

        return t1.totalDiff(t2, false);
    }

    private static Tlsh getTlshFromFingerprint(Fingerprint fingerprint) {
        TlshCreator tlshCreator = new TlshCreator();
        String json = JsonUtils.deterministicJson(fingerprint);
        tlshCreator.update(json.getBytes());
        return tlshCreator.getHash();
    }
}