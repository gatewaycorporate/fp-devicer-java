package org.gatewaycorporate.fpdevicer;

public class FPDevicer {
    /**
     * Calculates the confidence score between two fingerprints based on their difference score and comparison.
     * The confidence score is a value between 0 and 100, where 100 indicates a perfect match.
     *
     * @param f1 The first {@link Fingerprint} to compare.
     * @param f2 The second {@link Fingerprint} to compare.
     * @return A Double representing the confidence score.
     */
    public static Double calculateConfidence(Fingerprint f1, Fingerprint f2) {
        Integer differenceScore = TlshUtils.getDifferenceScore(f1, f2);
        Comparison comparison = JsonUtils.compareObjects(f1, f2);
        Double inverseMatchScore = 1 - (comparison.getMatches() / (double) comparison.getFields());
        Double x = (differenceScore / 1.5) * inverseMatchScore;

        if (inverseMatchScore == 0 || differenceScore == 0) {
            return 100.0;
        }

        return 100.0 / (1.0 + Math.exp(-4.5 + (0.25 * x)));
    }

    /**
     * Calculates the confidence score between two fingerprints represented as JSON strings.
     * See the {@link Fingerprint} class for the structure of the JSON.
     * The confidence score is a value between 0 and 100, where 100 indicates a perfect match.
     *
     * @param f1 The first fingerprint in JSON string format.
     * @param f2 The second fingerprint in JSON string format.
     * @return A Double representing the confidence score.
     */
    public static Double calculateConfidence(String f1, String f2) {
        Fingerprint fingerprint1 = Fingerprint.fromJson(f1);
        Fingerprint fingerprint2 = Fingerprint.fromJson(f2);
        return calculateConfidence(fingerprint1, fingerprint2);
    }
}
