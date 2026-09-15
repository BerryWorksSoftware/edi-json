# EDI to JSON

Convert X12, EDIFACT, and HL7 files to readable JSON with **EDIReader Framework
Community Edition**.

This repository is a small Java command-line application with two purposes:

- Convert an EDI file to a JSON file.
- Show how to use the Framework's `EdiToJson` class in your own Java application.

The [CLI source](src/main/java/com/berryworks/edireader/framework/cli/EdiToJsonCli.java)
is deliberately short. Maven supplies the Framework from Maven Central:
`com.berryworks:edireader-framework-community:5.12.0`.

## Quick start

To build, install **JDK 21 or later** and **Maven 3.9 or later**, then run these
commands from the repository directory:

```sh
mvn clean verify
java -jar target/edi-to-json.jar samples/x12-850.edi target/purchase-order.json
```

Open `target/purchase-order.json` to see the converted purchase order. The output
preserves the interchange, functional groups, transactions, and segment data,
including the purchase order number `DEMO-PO-001`.

The build produces **`target/edi-to-json.jar`**, a runnable JAR containing its
runtime dependencies. You can copy that file to another directory or machine;
only **Java 21 or later** is needed to run it. Maven is needed only to build.

Prebuilt reboot downloads will be published on the
[GitHub Releases page](https://github.com/BerryWorksSoftware/edi-json/releases).
Until then, build from source using the commands above.

## Command line

```sh
java -jar edi-to-json.jar input.edi output.json
java -jar edi-to-json.jar --help
java -jar edi-to-json.jar -h
```

Use the path to your JAR if it is in another directory. Quote filenames containing
spaces. Conversion takes **exactly two filenames**, in input/output order.
`--help` and `-h` show help when used alone.

- Input and output use **UTF-8**.
- JSON is formatted for readability.
- Descriptive annotations and recoverable-error recovery are disabled.
- Existing output is overwritten. Input and output must be different files,
  including when symbolic links or hard links identify the same file.
- A failed conversion may leave partial output; discard it.
- Successful conversion is silent. Errors and invalid-argument usage go to stderr;
  requested help goes to stdout.

| Exit code | Meaning |
| --- | --- |
| `0` | Conversion succeeded, or help was displayed |
| `1` | Conversion or file I/O failed |
| `2` | Incorrect number of arguments |

The CLI currently has no conversion options or stdin/stdout conversion mode.
The Java API provides more configuration choices.

## Samples

All public examples live in [`samples/`](samples/README.md).

| File | Example |
| --- | --- |
| [x12-850.edi](samples/x12-850.edi) | X12 4010 purchase order: one item, synthetic buyer and supplier |
| [x12-824.edi](samples/x12-824.edi) | X12 5010 application advice |
| [x12-837.edi](samples/x12-837.edi) | X12 5010 professional healthcare claims, with two transaction sets |
| [edifact-invoic.edi](samples/edifact-invoic.edi) | EDIFACT D93A invoice |
| [hl7-adt.hl7](samples/hl7-adt.hl7) | HL7 v2.5 ADT A01 admission message, entirely synthetic with no PHI |

For example:

```sh
java -jar target/edi-to-json.jar samples/x12-837.edi target/claims.json
java -jar target/edi-to-json.jar samples/edifact-invoic.edi target/invoice.json
java -jar target/edi-to-json.jar samples/hl7-adt.hl7 target/admission.json
```

These examples demonstrate conversion; they are not implementation guides or
certification of trading-partner compliance.

## Use the Framework in Java

Add the Community Edition dependency to your application's `pom.xml`:

```xml
<dependency>
    <groupId>com.berryworks</groupId>
    <artifactId>edireader-framework-community</artifactId>
    <version>5.12.0</version>
</dependency>
```

The core conversion is just a few lines:

```java
import com.berryworks.edireader.json.fromedi.EdiToJson;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Inside your method; handle or declare conversion and I/O exceptions.
EdiToJson converter = new EdiToJson();
converter.setFormatting(true);
converter.setAnnotated(false);

try (var reader = Files.newBufferedReader(Path.of("input.edi"), StandardCharsets.UTF_8);
     var writer = Files.newBufferedWriter(Path.of("output.json"), StandardCharsets.UTF_8)) {
    converter.asJson(reader, writer);
}
```

The [complete CLI](src/main/java/com/berryworks/edireader/framework/cli/EdiToJsonCli.java)
adds argument checks, input-file protection, and error reporting. Conversion
streams through a reader and writer rather than loading the whole input file.
`asJson(Reader, Writer)` closes the output writer, so use a dedicated writer.

The CLI fixes its defaults to keep the example easy to read. Applications can
configure the Framework directly, for example with `setAnnotated(true)` or
`setFormatting(false)`.

## Build and test

```sh
mvn clean verify
```

The tests convert every sample, check help and exit codes, and verify that the
input is protected when the output identifies the same file. The same tests run
again against a copy of the packaged JAR in a temporary directory, without an
external dependency classpath.

`mvn test` runs the compiled-code tests; `mvn package` creates the JAR;
`mvn verify` also checks the standalone JAR. For a GitHub Release, upload
`target/edi-to-json.jar`. The `original-edi-to-json.jar` file is a build
intermediate without bundled dependencies, not the download artifact.

Maven Shade packages the application and runtime dependencies, retaining parser
resources and service registrations. A no-operation SLF4J provider keeps normal
CLI execution quiet; the CLI reports conversion failures itself.

## License and contact

See the [EDIReader Framework Community License](LICENSE.md) for the terms that
apply to Community Edition. Public availability does not make the Framework
open-source software. Bundled dependencies retain their respective licenses.

Questions and commercial licensing: [json@canabrook.org](mailto:json@canabrook.org).

[Historical release notes](ReleaseNotes.md) describe earlier versions of this project.
