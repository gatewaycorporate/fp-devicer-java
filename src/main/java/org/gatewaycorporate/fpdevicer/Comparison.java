package org.gatewaycorporate.fpdevicer;

import lombok.Getter;

@Getter
class Comparison {
    private int fields;
    private int matches;

    Comparison(int[] comparisonResult) {
        if (comparisonResult == null || comparisonResult.length != 2) {
            throw new IllegalArgumentException("Comparison result must be an array of two integers.");
        }
        this.fields = comparisonResult[0];
        this.matches = comparisonResult[1];
    }

    void addField() {
        this.fields++;
    }

    void removeField() {
        this.fields--;
    }

    void addMatch() {
        this.matches++;
    }

    void removeMatch() {
        this.matches--;
    }
}
