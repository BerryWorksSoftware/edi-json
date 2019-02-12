[Intro](#edi-to-json)
| [Features](#feature-summary)
| [Editions](#basic-and-premium-editions)
| [Example](#a-small-example)
| [Command Line](#command-line-interface)
| [RESTful API](#rest-api-accessing-an-edi-to-json-microservice)
| [Notes](#technical-notes)

# EDI -> JSON and JSON -> EDI
EDI transactions can be transformed, or *serialized*, into JSON objects to simplify processing and/or increase human readability.
This project provides a [Java program](https://github.com/BerryWorksSoftware/edi-json/blob/master/src/main/java/com/berryworks/edireader/json/EdiToJsonDriver.java)
that illustrates how to use a Java API for transforming your EDI into JSON
and provides a file-based [command line tool](#command-line-interface)
in the form of a [runnable jar](https://github.com/BerryWorksSoftware/edi-json/blob/master/edireader-json-5.5.4-basic.jar). 
There is also a [RESTful API](#rest-api-accessing-an-edi-to-json-microservice)
to a microservice performing the same EDI to JSON transformations. 
 
The reverse transformation, producing EDI from JSON input, is also supported in the Premium Edition
described below.

## Feature Summary for EDI to JSON
* Formatting
  - the JSON output may be formatted for human readability
  - feature is optional, allowing for smaller output files
* Multiple EDI standards are supported
  - ANSI X12
  - EDIFACT
  - potentially others given sufficient interest (HL7, TRADACOMS)
* Segment looping
  - JSON output reflects the segment looping structures within the EDI transactions
  - version aware; for example, 4010 versus 5010
* Enhanced support for the X12 HIPAA health care transactions
  - 270, 271, 276, 277, 278, 834, 835, 837
  - JSON reflects loop qualifiers; for example, 2010BA versus 2010BB
  - JSON nesting of HL loops based on the logical hierarchy expressed within the HL segments
* Annotations - (optional) meta-data for the EDI documents. For example:
  - "824": "Application Advice"
  - "PER": "Administrative Communications Contact"
  - "PER_04_description": "Communication Number"
  - "PER_03_code_TE": "Telephone"
* Supports multiple ...
  - transactions per functional group
  - functional groups per interchange
  - interchanges per file
* High throughput, efficient use of resources
  - serializes EDI to JSON for arbitrarily large input streams
  - no in-memory data structures that grow in proportion to volume
  - no disk-IO beyond reading/writing input/output streams 
  
## Basic and Premium Editions
The jar provided with this project is a free and fully usable Basic Edition.
A Premium Edition is also available for licensing. Contact json@canabrook.org for details.
Here is a summary of the differences.

Feature | Basic  | Premium
|:-------|:-------|:-------
Formatting | yes | yes
X12        | yes | yes
EDIFACT    | yes | yes
Annotation | limited | extensive
Segment loops vislble in JSON | no | yes
Enhanced X12 HIPAA features| no | yes
JSON to EDI (see below)| no | yes
  
## A Small Example
Here is a small EDI sample, an X12 interchange containing a single 824 Application Advice transaction.
```
ISA*00*          *00*          *08*9254110060     *ZZ*123456789      *041216*0805*U*00501*000095071*0*P*>~
GS*AG*5137624388*123456789*20041216*0805*95071*X*005010~
ST*824*021390001*005010X186A1~
BGN*11*FFA.ABCDEF.123456*20020709*0932**123456789**WQ~
N1*41*ABC INSURANCE*46*111111111~
PER*IC*JOHN JOHNSON*TE*8005551212*EX*1439~
N1*40*SMITHCO*46*A1234~
OTI*TA*TN*NA***20020709*0902*2*0001*834*005010X220A1~
SE*7*021390001~
GE*1*95071~
IEA*1*000095071~
```

Here is the JSON output produced by the Basic Edition with the formatting and annotation options enabled.
```json
{
  "interchanges": [
    {
      "ISA": "Interchange Control Header",
      "ISA_01_AuthorizationQualifier": "00",
      "ISA_02_AuthorizationInformation": "          ",
      "ISA_03_SecurityQualifier": "00",
      "ISA_04_SecurityInformation": "          ",
      "ISA_05_SenderQualifier": "08",
      "ISA_06_SenderId": "9254110060     ",
      "ISA_07_ReceiverQualifier": "ZZ",
      "ISA_08_ReceiverId": "123456789      ",
      "ISA_09_Date": "041216",
      "ISA_10_Time": "0805",
      "ISA_11_StandardsId": "U",
      "ISA_12_Version": "00501",
      "ISA_13_InterchangeControlNumber": "000095071",
      "ISA_14_AcknowledgmentRequested": "0",
      "ISA_15_TestIndicator": "P",
      "functional_groups": [
        {
          "GS": "Functional Group Header",
          "GS_01_FunctionalIdentifierCode": "AG",
          "GS_02_ApplicationSenderCode": "5137624388",
          "GS_03_ApplicationReceiverCode": "123456789",
          "GS_04_Date": "20041216",
          "GS_05_Time": "0805",
          "GS_06_GroupControlNumber": "95071",
          "GS_07_ResponsibleAgencyCode": "X",
          "GS_08_Version": "005010",
          "transactions": [
            {
              "824": "Application Advice",
              "ST": "Transaction Set Header",
              "ST_01_TransactionSetIdentifierCode": "824",
              "ST_02_TransactionSetControlNumber": "021390001",
              "ST_03_ImplementationConventionReference": "005010X186A1",
              "segments": [
                {
                  "BGN": "Beginning Segment",
                  "BGN_01": "11",
                  "BGN_02": "FFA.ABCDEF.123456",
                  "BGN_03": "20020709",
                  "BGN_04": "0932",
                  "BGN_06": "123456789",
                  "BGN_08": "WQ"
                },
                {
                  "N1": "Party Identification",
                  "N1_01": "41",
                  "N1_02": "ABC INSURANCE",
                  "N1_03": "46",
                  "N1_04": "111111111"
                },
                {
                  "PER": "Administrative Communications Contact",
                  "PER_01": "IC",
                  "PER_02": "JOHN JOHNSON",
                  "PER_03": "TE",
                  "PER_04": "8005551212",
                  "PER_05": "EX",
                  "PER_06": "1439"
                },
                {
                  "N1": "Party Identification",
                  "N1_01": "40",
                  "N1_02": "SMITHCO",
                  "N1_03": "46",
                  "N1_04": "A1234"
                },
                {
                  "OTI": "Original Transaction Identification",
                  "OTI_01": "TA",
                  "OTI_02": "TN",
                  "OTI_03": "NA",
                  "OTI_06": "20020709",
                  "OTI_07": "0902",
                  "OTI_08": "2",
                  "OTI_09": "0001",
                  "OTI_10": "834",
                  "OTI_11": "005010X220A1"
                }
              ]
            }
          ]
        }
      ]
    }
  ]
}

```

Here is the output with the Premium Edition. Notice the annotions for the individual elements and the code values, as well as the N1-1000 and OTI-2000 loops.

```json
{
  "interchanges": [
    {
      "ISA": "Interchange Control Header",
      "ISA_01_AuthorizationQualifier": "00",
      "ISA_02_AuthorizationInformation": "          ",
      "ISA_03_SecurityQualifier": "00",
      "ISA_04_SecurityInformation": "          ",
      "ISA_05_SenderQualifier": "08",
      "ISA_06_SenderId": "9254110060     ",
      "ISA_07_ReceiverQualifier": "ZZ",
      "ISA_08_ReceiverId": "123456789      ",
      "ISA_09_Date": "041216",
      "ISA_10_Time": "0805",
      "ISA_11_StandardsId": "U",
      "ISA_12_Version": "00501",
      "ISA_13_InterchangeControlNumber": "000095071",
      "ISA_14_AcknowledgmentRequested": "0",
      "ISA_15_TestIndicator": "P",
      "functional_groups": [
        {
          "GS": "Functional Group Header",
          "GS_01_FunctionalIdentifierCode": "AG",
          "GS_02_ApplicationSenderCode": "5137624388",
          "GS_03_ApplicationReceiverCode": "123456789",
          "GS_04_Date": "20041216",
          "GS_05_Time": "0805",
          "GS_06_GroupControlNumber": "95071",
          "GS_07_ResponsibleAgencyCode": "X",
          "GS_08_Version": "005010",
          "transactions": [
            {
              "824": "Application Advice",
              "ST": "Transaction Set Header",
              "ST_01_TransactionSetIdentifierCode": "824",
              "ST_02_TransactionSetControlNumber": "021390001",
              "ST_03_ImplementationConventionReference": "005010X186A1",
              "segments": [
                {
                  "BGN": "Beginning Segment",
                  "BGN_01_description": "Transaction Set Purpose Code",
                  "BGN_01": "11",
                  "BGN_01_code_11": "Response",
                  "BGN_02_description": "Reference Identification",
                  "BGN_02": "FFA.ABCDEF.123456",
                  "BGN_03_description": "Date",
                  "BGN_03": "20020709",
                  "BGN_04_description": "Time",
                  "BGN_04": "0932",
                  "BGN_06_description": "Reference Identification",
                  "BGN_06": "123456789",
                  "BGN_08_description": "Action Code",
                  "BGN_08": "WQ",
                  "BGN_08_code_WQ": "Accept"
                },
                {
                  "N1-1000_loop": [
                    {
                      "N1": "Party Identification",
                      "N1_01_description": "Entity Identifier Code",
                      "N1_01": "41",
                      "N1_01_code_41": "Submitter",
                      "N1_02_description": "Name",
                      "N1_02": "ABC INSURANCE",
                      "N1_03_description": "Identification Code Qualifier",
                      "N1_03": "46",
                      "N1_03_code_46": "Electronic Transmitter Identification Number (ETIN)",
                      "N1_04_description": "Identification Code",
                      "N1_04": "111111111"
                    },
                    {
                      "PER": "Administrative Communications Contact",
                      "PER_01_description": "Contact Function Code",
                      "PER_01": "IC",
                      "PER_01_code_IC": "Information Contact",
                      "PER_02_description": "Name",
                      "PER_02": "JOHN JOHNSON",
                      "PER_03_description": "Communication Number Qualifier",
                      "PER_03": "TE",
                      "PER_03_code_TE": "Telephone",
                      "PER_04_description": "Communication Number",
                      "PER_04": "8005551212",
                      "PER_05_description": "Communication Number Qualifier",
                      "PER_05": "EX",
                      "PER_05_code_EX": "Telephone Extension",
                      "PER_06_description": "Communication Number",
                      "PER_06": "1439"
                    }
                  ]
                },
                {
                  "N1-1000_loop": [
                    {
                      "N1": "Party Identification",
                      "N1_01_description": "Entity Identifier Code",
                      "N1_01": "40",
                      "N1_01_code_40": "Receiver",
                      "N1_02_description": "Name",
                      "N1_02": "SMITHCO",
                      "N1_03_description": "Identification Code Qualifier",
                      "N1_03": "46",
                      "N1_03_code_46": "Electronic Transmitter Identification Number (ETIN)",
                      "N1_04_description": "Identification Code",
                      "N1_04": "A1234"
                    }
                  ]
                },
                {
                  "OTI-2000_loop": [
                    {
                      "OTI": "Original Transaction Identification",
                      "OTI_01_description": "Application Acknowledgment Code",
                      "OTI_01": "TA",
                      "OTI_01_code_TA": "Transaction Set Accept",
                      "OTI_02_description": "Reference Identification Qualifier",
                      "OTI_02": "TN",
                      "OTI_02_code_TN": "Transaction Reference Number",
                      "OTI_03_description": "Reference Identification",
                      "OTI_03": "NA",
                      "OTI_06_description": "Date",
                      "OTI_06": "20020709",
                      "OTI_07_description": "Time",
                      "OTI_07": "0902",
                      "OTI_08_description": "Group Control Number",
                      "OTI_08": "2",
                      "OTI_09_description": "Transaction Set Control Number",
                      "OTI_09": "0001",
                      "OTI_10_description": "Transaction Set Identifier Code",
                      "OTI_10": "834",
                      "OTI_10_code_834": "Benefit Enrollment and Maintenance",
                      "OTI_11_description": "Version / Release / Industry Identifier Code",
                      "OTI_11": "005010X220A1"
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    }
  ]
}
```

## Command Line Interface
The jar is runnable with Java 7 or later with the following command line arguments.

**java -jar edireader-json-5.5.4-basic.jar**   *edi-input-file*  *json-output-file* *option...*

where *option* is zero or more of
```
--formatted={yes|no}   format JSON output (default: yes)
--annotate={yes|no}  annotate JSON ouptut (default: no)
--summarize={yes|no}  omit segment-level detail after first segment (default: no}
```


## JSON to EDI (with Premium Edition)

A recent addition to the Premium Edition is the ability to perform the
reverse transformation, converting JSON as shown above into EDI output. 

Here is a summary of the features:

* Integration options
  - a command line tool with filename arguments and configuration options
  - a Java API
* Configurable EDI syntax characteristics
  - element and sub-element delimiters
  - segment terminator
  - optional inclusion of line separators between segments
* EDI standards
  - ANSI X12 supported
  - EDIFACT not currently supported, but can be added
* JSON input
  - any of the JSON variations shown above are supported
  - segment looping structures are optional
* Supports multiple ...
  - transactions per functional group
  - functional groups per interchange
  - interchanges per file
* Transaction, group, and interchange trailers
  - the appropriate EDI segments are automatically generated
  - with proper counts and control numbers
  - for example: SE, GE, and IEA in ANSI X12
* Conflicts with EDI syntax characters
  - for example, a : (colon) in a data field while : is the configured sub-element delimiter
  - handled automatically, guaranteeing structurally correct EDI output
  - by substituting "?" for the character in conflict

