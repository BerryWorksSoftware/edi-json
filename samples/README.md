# EDI samples

Run these commands from the repository root after `mvn clean verify`:

```sh
java -jar target/edi-to-json.jar samples/x12-850.edi target/purchase-order.json
java -jar target/edi-to-json.jar samples/x12-824.edi target/application-advice.json
java -jar target/edi-to-json.jar samples/x12-837.edi target/claims.json
java -jar target/edi-to-json.jar samples/edifact-invoic.edi target/invoice.json
java -jar target/edi-to-json.jar samples/hl7-adt.hl7 target/admission.json
```

## Contents and provenance

- **x12-850.edi** — A newly written, synthetic X12 4010 purchase order for ten
  demonstration widgets. Buyer, supplier, identifiers, and transaction data are
  invented for this example. The interchange is marked as test data.
- **x12-824.edi** — The existing repository's X12 5010 application advice sample.
- **x12-837.edi** — The existing repository's X12 5010 professional healthcare
  claim example, containing two transaction sets and four claims.
- **edifact-invoic.edi** — The existing repository's EDIFACT D93A invoice example.
- **hl7-adt.hl7** — A newly written, synthetic HL7 v2.5 ADT A01 admission message.
  Every identifier, name, facility, date, and location is invented. No patient
  record or other real-person data was used; it contains no PHI. `MSH-11` is `T`
  (test processing).

The HL7 file uses carriage returns (`CR`) between segments, including after the
last segment. `.gitattributes` preserves those bytes across checkouts; some text
editors may display the file on one line.

The samples are conversion demonstrations, not complete implementation guides.
The tests read these same files. JSON output is generated into `target/` rather
than checked in, so it reflects the current Framework version.
