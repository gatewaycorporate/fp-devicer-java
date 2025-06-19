# FP-Devicer-Java

FP-Devicer is a digital fingerprinting middleware library designed for ease of use and near-universal compatibility with servers.

Importing and using the library to compare fingerprints between users is as simple as collecting some user data and running the calculateConfidence function.

## Usage Example
```java
Fingerprint f1 = Fingerprint.builder()
    // ...set other fields...
    .build();

Fingerprint f2 = Fingerprint.builder()
    // ...set other fields...
    .build();

int confidence = FPDevicer.calculateConfidence(f1, f2);
```

The resulting confidence will range between 0 and 100, with 100 providing the highest confidence of the users being identical.
