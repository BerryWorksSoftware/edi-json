# EDI to JSON
This project provides a Java program that serializes EDI input into JSON output. 
The source code in this project is a small driver that illustrates the Java API used to call the EDI to JSON transformer.
That transformer is provided as a jar file which can be used in your own Java application. 
The jar is also a self-contained
runnable jar with the driver program providing a command line interface.

## Feature Summary
* Formatting - the JSON output may be formatted for human readability
* X12 and EDIFACT - both of the major EDI standards are supported
* Segment looping - the JSON may reflect the segment looping structures within an EDI transaction
* Annotation - the JSON may include additional key:value pairs to provide descriptive meta-data regarding the EDI documents, segments, and elements. For example:
  - "837": "Health Care Claim"
  - "N4": "Geographic Location"
  - "N4_01_description": "City Name"
  
