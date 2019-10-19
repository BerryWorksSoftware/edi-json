# Release Notes

### 5.5.19 - October 18, 2019

* Feature: **stdin/stdout** support. The provided EdiToJsonDriver was enhanced to support
EDI input from stdin and JSON output to stdout.
The class is provided in source form and also in compiled form in edireader-json-basic-5.5.19.jar.
It serves both as a command line tool and an example of how to use the underlying EdiToJson class in your
own Java applications.

### 5.5.14 - July 17, 2019

* Feature: Improved support for **repeated elements** within a segment. Repeated elements are rarely used in X12
but do appear in a few places. For example, the EB-03 element may be repeated in the EB segment of a 271
(Eligibility, Coverage or Benefit Information) transacton set.
With this release, unique JSON keys are generated using the repetition number. This avoids duplicate keys
in a JSON map.
* Fix: With some HIPAA transaction sets, such as the 271, a RuntimeException was thrown: 
> Could not find queued element loop for putAttribute()

### 5.5.13 - March 8, 2019

* Feature: Use **JSON escape sequences** when necessary
to handle collisions between characters within an EDI data element and JSON reserved characters.
* Feature: (Premium Edition) Support **JSON to EDI**.
* Upgrade: to release 5.4.15 of EDIReader for EDI parsing.

### 5.5.4 - December 14, 2017

* Initial release

