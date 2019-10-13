[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)


# EDI to JSON,  JSON to EDI
EDI transactions can be transformed, or *serialized*, into JSON objects to simplify processing and/or increase human readability.
This project provides a [Java program](https://github.com/BerryWorksSoftware/edi-json/blob/master/src/main/java/com/berryworks/edireader/json/EdiToJsonDriver.java)
that illustrates how to use a Java API for transforming your EDI into JSON
and provides a file-based [command line tool](#command-line-interface)
in the form of a [runnable jar](https://github.com/BerryWorksSoftware/edi-json/blob/master/repo/com/berryworks/edireader-json-basic/5.5.13/edireader-json-basic-5.5.13.jar)
that is provided for installation at your site.
 
The reverse transformation, producing EDI from JSON input, is also supported in the Premium Edition
described below. Also, an EDI to YAML feature is coming soon for both the Basic and Premium editions.

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
  - no disk I/O beyond reading/writing input/output streams 
  
[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)
  
## Editions: Basic, Premium, and Premium-837
The jar provided with this project is a free and fully usable Basic Edition.
A Premium Edition is also available for licensing,
as well as a Premium-837 which includes validation and balancing tools
specifically for 837 Health Care Claims.
Contact via GitHub or json@canabrook.org for details.
Here is a summary of the differences.

Feature | Basic  | Premium | Premium-837
|:-------|:-------|:-------|:-------
Formatting | yes | yes| yes
X12        | yes | yes| yes
EDIFACT    | yes | yes| yes
Annotation | limited | extensive | extensive
Segment loops visible in JSON | no | yes| yes
Enhanced X12 HIPAA features| no | yes| yes
JSON to EDI (see below)| no | yes| yes
Validation of 837-5010 | no | no | yes 
Claim balancing 837-5010 | no | no | yes 
  
[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)

## A Simple Example
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

Here is the output with the Premium Edition. Notice the annotations for the individual elements and the code values, as well as the N1-1000 and OTI-2000 loops.

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

[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)

## Another Example: 837 Health Care Claim

The 837 health care claim is one of the most common transactions used in new projects,
and one of the more complex. Below is an example of an 837P (Professional) version 005010.

```
ISA*00*AUTHORIZAT*00*SECURITY I*ZZ*000000060000000*ZZ*000000010000000*110705*1132*^*00501*110705001*0*T*:~
GS*HC*00000006*00000001*20110705*113253*110705001*X*005010X222A1~
ST*837*0021*005010X222~
BHT*0019*00*244579*20061015*1023*CH~
NM1*41*2*PREMIER BILLING SERVICE*****46*TGJ23~
PER*IC*JERRY*TE*3055552222*EX*231~
NM1*40*2*KEY INSURANCE COMPANY*****46*66783JJT~
HL*1**20*1~
PRV*BI*PXC*203BF0100Y~
NM1*85*2*BEN KILDARE SERVICE*****XX*9876543210~
N3*234 SEAWAY ST~
N4*MIAMI*FL*33111~
REF*EI*587654321~
NM1*87*2~
N3*2345 OCEAN BLVD~
N4*MIAMI*FL*33111~
HL*2*1*22*1~
SBR*P**2222-SJ******CI~
NM1*IL*1*SMITH*JANE****MI*JS00111223333~
DMG*D8*19430501*F~
NM1*PR*2*KEY INSURANCE COMPANY*****PI*999996666~
REF*G2*KA6663~
HL*3*2*23*0~
PAT*19~
NM1*QC*1*SMITH*TED~
N3*236 N MAIN ST~
N4*MIAMI*FL*33413~
DMG*D8*19730501*M~
CLM*26463774*100***11:B:1*Y*A*Y*I~
REF*D9*17312345600006351~
HI*BK:0340*BF:V7389~
LX*1~
SV1*HC:99213*40*UN*1***1~
DTP*472*D8*20061003~
LX*2~
SV1*HC:87070*15*UN*1***1~
DTP*472*D8*20061003~
LX*3~
SV1*HC:99214*35*UN*1***2~
DTP*472*D8*20061010~
LX*4~
SV1*HC:86663*10*UN*1***2~
DTP*472*D8*20061010~
SE*42*0021~
GE*1*110705001~
IEA*1*110705001~
```

Below is that same 837 as JSON, using the Premium Edition with full annotation.
Notice the NM1-1000A_loop and NM1-1000B_loop,
both initiated by NM1 segments, where the A and B qualifiers are assigned based on the code values in the
NM1-01 element. For an 837, 1000A is for the submitter and 1000B is for the receiver.
Notice also the 
HL-2000A_loop for the Billing/Pay-To Provider, the HL-2000B_loop(s) nested within for the Subscriber,
and the further nested HL_2000C_loop(s) for the Patient within the Subscriber.
This type of tagging/nesting relative to the semantics of the segment loops can be very convenient when
processing the transactions.

```
{
  "interchanges": [
    {
      "ISA": "Interchange Control Header",
      "ISA_01_AuthorizationQualifier": "00",
      "ISA_02_AuthorizationInformation": "AUTHORIZAT",
      "ISA_03_SecurityQualifier": "00",
      "ISA_04_SecurityInformation": "SECURITY I",
      "ISA_05_SenderQualifier": "ZZ",
      "ISA_06_SenderId": "000000060000000",
      "ISA_07_ReceiverQualifier": "ZZ",
      "ISA_08_ReceiverId": "000000010000000",
      "ISA_09_Date": "110705",
      "ISA_10_Time": "1132",
      "ISA_11_StandardsId": "null",
      "ISA_12_Version": "00501",
      "ISA_13_InterchangeControlNumber": "110705001",
      "ISA_14_AcknowledgmentRequested": "0",
      "ISA_15_TestIndicator": "T",
      "functional_groups": [
        {
          "GS": "Functional Group Header",
          "GS_01_FunctionalIdentifierCode": "HC",
          "GS_02_ApplicationSenderCode": "00000006",
          "GS_03_ApplicationReceiverCode": "00000001",
          "GS_04_Date": "20110705",
          "GS_05_Time": "113253",
          "GS_06_GroupControlNumber": "110705001",
          "GS_07_ResponsibleAgencyCode": "X",
          "GS_08_Version": "005010X222A1",
          "transactions": [
            {
              "837": "Health Care Claim",
              "ST": "Transaction Set Header",
              "ST_01_TransactionSetIdentifierCode": "837",
              "ST_02_TransactionSetControlNumber": "0021",
              "ST_03_ImplementationConventionReference": "005010X222",
              "segments": [
                {
                  "BHT": "Beginning of Hierarchical Transaction",
                  "BHT_01_description": "Hierarchical Structure Code",
                  "BHT_01": "0019",
                  "BHT_01_code_0019": "Information Source, Subscriber, Dependent",
                  "BHT_02_description": "Transaction Set Purpose Code",
                  "BHT_02": "00",
                  "BHT_02_code_00": "Original",
                  "BHT_03_description": "Reference Identification",
                  "BHT_03": "244579",
                  "BHT_04_description": "Date",
                  "BHT_04": "20061015",
                  "BHT_05_description": "Time",
                  "BHT_05": "1023",
                  "BHT_06_description": "Transaction Type Code",
                  "BHT_06": "CH",
                  "BHT_06_code_CH": "Chargeable"
                },
                {
                  "NM1-1000A_loop": [
                    {
                      "NM1": "Individual or Organizational Name",
                      "NM1_01_description": "Entity Identifier Code",
                      "NM1_01": "41",
                      "NM1_01_code_41": "Submitter",
                      "NM1_02_description": "Entity Type Qualifier",
                      "NM1_02": "2",
                      "NM1_02_code_2": "Non-Person Entity",
                      "NM1_03_description": "Name Last or Organization Name",
                      "NM1_03": "PREMIER BILLING SERVICE",
                      "NM1_08_description": "Identification Code Qualifier",
                      "NM1_08": "46",
                      "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                      "NM1_09_description": "Identification Code",
                      "NM1_09": "TGJ23"
                    },
                    {
                      "PER": "Administrative Communications Contact",
                      "PER_01_description": "Contact Function Code",
                      "PER_01": "IC",
                      "PER_01_code_IC": "Information Contact",
                      "PER_02_description": "Name",
                      "PER_02": "JERRY",
                      "PER_03_description": "Communication Number Qualifier",
                      "PER_03": "TE",
                      "PER_03_code_TE": "Telephone",
                      "PER_04_description": "Communication Number",
                      "PER_04": "3055552222",
                      "PER_05_description": "Communication Number Qualifier",
                      "PER_05": "EX",
                      "PER_05_code_EX": "Telephone Extension",
                      "PER_06_description": "Communication Number",
                      "PER_06": "231"
                    }
                  ]
                },
                {
                  "NM1-1000B_loop": [
                    {
                      "NM1": "Individual or Organizational Name",
                      "NM1_01_description": "Entity Identifier Code",
                      "NM1_01": "40",
                      "NM1_01_code_40": "Receiver",
                      "NM1_02_description": "Entity Type Qualifier",
                      "NM1_02": "2",
                      "NM1_02_code_2": "Non-Person Entity",
                      "NM1_03_description": "Name Last or Organization Name",
                      "NM1_03": "KEY INSURANCE COMPANY",
                      "NM1_08_description": "Identification Code Qualifier",
                      "NM1_08": "46",
                      "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                      "NM1_09_description": "Identification Code",
                      "NM1_09": "66783JJT"
                    }
                  ]
                },
                {
                  "HL-2000A_loop": [
                    {
                      "HL": "Hierarchical Level",
                      "HL_01_description": "Hierarchical ID Number",
                      "HL_01": "1",
                      "HL_03_description": "Hierarchical Level Code",
                      "HL_03": "20",
                      "HL_03_code_20": "Information Source",
                      "HL_04_description": "Hierarchical Child Code",
                      "HL_04": "1",
                      "HL_04_code_1": "Additional Subordinate HL Data Segment in This Hierarchical Structure."
                    },
                    {
                      "PRV": "Provider Information",
                      "PRV_01_description": "Provider Code",
                      "PRV_01": "BI",
                      "PRV_01_code_BI": "Billing",
                      "PRV_02_description": "Reference Identification Qualifier",
                      "PRV_02": "PXC",
                      "PRV_02_code_PXC": "Health Care Provider Taxonomy Code",
                      "PRV_03_description": "Reference Identification",
                      "PRV_03": "203BF0100Y"
                    },
                    {
                      "NM1-2010AA_loop": [
                        {
                          "NM1": "Individual or Organizational Name",
                          "NM1_01_description": "Entity Identifier Code",
                          "NM1_01": "85",
                          "NM1_01_code_85": "Billing Provider",
                          "NM1_02_description": "Entity Type Qualifier",
                          "NM1_02": "2",
                          "NM1_02_code_2": "Non-Person Entity",
                          "NM1_03_description": "Name Last or Organization Name",
                          "NM1_03": "BEN KILDARE SERVICE",
                          "NM1_08_description": "Identification Code Qualifier",
                          "NM1_08": "XX",
                          "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                          "NM1_09_description": "Identification Code",
                          "NM1_09": "9876543210"
                        },
                        {
                          "N3": "Party Location",
                          "N3_01_description": "Address Information",
                          "N3_01": "234 SEAWAY ST"
                        },
                        {
                          "N4": "Geographic Location",
                          "N4_01_description": "City Name",
                          "N4_01": "MIAMI",
                          "N4_02_description": "State or Province Code",
                          "N4_02": "FL",
                          "N4_03_description": "Postal Code",
                          "N4_03": "33111"
                        },
                        {
                          "REF": "Reference Information",
                          "REF_01_description": "Reference Identification Qualifier",
                          "REF_01": "EI",
                          "REF_01_code_EI": "Employer's Identification Number",
                          "REF_02_description": "Reference Identification",
                          "REF_02": "587654321"
                        }
                      ]
                    },
                    {
                      "NM1-2010AB_loop": [
                        {
                          "NM1": "Individual or Organizational Name",
                          "NM1_01_description": "Entity Identifier Code",
                          "NM1_01": "87",
                          "NM1_01_code_87": "Pay-to Provider",
                          "NM1_02_description": "Entity Type Qualifier",
                          "NM1_02": "2",
                          "NM1_02_code_2": "Non-Person Entity"
                        },
                        {
                          "N3": "Party Location",
                          "N3_01_description": "Address Information",
                          "N3_01": "2345 OCEAN BLVD"
                        },
                        {
                          "N4": "Geographic Location",
                          "N4_01_description": "City Name",
                          "N4_01": "MIAMI",
                          "N4_02_description": "State or Province Code",
                          "N4_02": "FL",
                          "N4_03_description": "Postal Code",
                          "N4_03": "33111"
                        }
                      ]
                    },
                    {
                      "HL-2000B_loop": [
                        {
                          "HL": "Hierarchical Level",
                          "HL_01_description": "Hierarchical ID Number",
                          "HL_01": "2",
                          "HL_02_description": "Hierarchical Parent ID Number",
                          "HL_02": "1",
                          "HL_03_description": "Hierarchical Level Code",
                          "HL_03": "22",
                          "HL_03_code_22": "Subscriber",
                          "HL_04_description": "Hierarchical Child Code",
                          "HL_04": "1",
                          "HL_04_code_1": "Additional Subordinate HL Data Segment in This Hierarchical Structure."
                        },
                        {
                          "SBR": "Subscriber Information",
                          "SBR_01_description": "Payer Responsibility Sequence Number Code",
                          "SBR_01": "P",
                          "SBR_01_code_P": "Primary",
                          "SBR_03_description": "Reference Identification",
                          "SBR_03": "2222-SJ",
                          "SBR_09_description": "Claim Filing Indicator Code",
                          "SBR_09": "CI",
                          "SBR_09_code_CI": "Commercial Insurance Co."
                        },
                        {
                          "NM1-2010BA_loop": [
                            {
                              "NM1": "Individual or Organizational Name",
                              "NM1_01_description": "Entity Identifier Code",
                              "NM1_01": "IL",
                              "NM1_01_code_IL": "Insured or Subscriber",
                              "NM1_02_description": "Entity Type Qualifier",
                              "NM1_02": "1",
                              "NM1_02_code_1": "Person",
                              "NM1_03_description": "Name Last or Organization Name",
                              "NM1_03": "SMITH",
                              "NM1_04_description": "Name First",
                              "NM1_04": "JANE",
                              "NM1_08_description": "Identification Code Qualifier",
                              "NM1_08": "MI",
                              "NM1_08_code_MI": "Member Identification Number",
                              "NM1_09_description": "Identification Code",
                              "NM1_09": "JS00111223333"
                            },
                            {
                              "DMG": "Demographic Information",
                              "DMG_01_description": "Date Time Period Format Qualifier",
                              "DMG_01": "D8",
                              "DMG_01_code_D8": "Date Expressed in Format CCYYMMDD",
                              "DMG_02_description": "Date Time Period",
                              "DMG_02": "19430501",
                              "DMG_03_description": "Gender Code",
                              "DMG_03": "F",
                              "DMG_03_code_F": "Female"
                            }
                          ]
                        },
                        {
                          "NM1-2010BB_loop": [
                            {
                              "NM1": "Individual or Organizational Name",
                              "NM1_01_description": "Entity Identifier Code",
                              "NM1_01": "PR",
                              "NM1_01_code_PR": "Payer",
                              "NM1_02_description": "Entity Type Qualifier",
                              "NM1_02": "2",
                              "NM1_02_code_2": "Non-Person Entity",
                              "NM1_03_description": "Name Last or Organization Name",
                              "NM1_03": "KEY INSURANCE COMPANY",
                              "NM1_08_description": "Identification Code Qualifier",
                              "NM1_08": "PI",
                              "NM1_08_code_PI": "Payor Identification",
                              "NM1_09_description": "Identification Code",
                              "NM1_09": "999996666"
                            },
                            {
                              "REF": "Reference Information",
                              "REF_01_description": "Reference Identification Qualifier",
                              "REF_01": "G2",
                              "REF_01_code_G2": "Provider Commercial Number",
                              "REF_02_description": "Reference Identification",
                              "REF_02": "KA6663"
                            }
                          ]
                        },
                        {
                          "HL-2000C_loop": [
                            {
                              "HL": "Hierarchical Level",
                              "HL_01_description": "Hierarchical ID Number",
                              "HL_01": "3",
                              "HL_02_description": "Hierarchical Parent ID Number",
                              "HL_02": "2",
                              "HL_03_description": "Hierarchical Level Code",
                              "HL_03": "23",
                              "HL_03_code_23": "Dependent",
                              "HL_04_description": "Hierarchical Child Code",
                              "HL_04": "0",
                              "HL_04_code_0": "No Subordinate HL Segment in This Hierarchical Structure."
                            },
                            {
                              "PAT": "Patient Information",
                              "PAT_01_description": "Individual Relationship Code",
                              "PAT_01": "19",
                              "PAT_01_code_19": "Child"
                            },
                            {
                              "NM1-2010CA_loop": [
                                {
                                  "NM1": "Individual or Organizational Name",
                                  "NM1_01_description": "Entity Identifier Code",
                                  "NM1_01": "QC",
                                  "NM1_01_code_QC": "Patient",
                                  "NM1_02_description": "Entity Type Qualifier",
                                  "NM1_02": "1",
                                  "NM1_02_code_1": "Person",
                                  "NM1_03_description": "Name Last or Organization Name",
                                  "NM1_03": "SMITH",
                                  "NM1_04_description": "Name First",
                                  "NM1_04": "TED"
                                },
                                {
                                  "N3": "Party Location",
                                  "N3_01_description": "Address Information",
                                  "N3_01": "236 N MAIN ST"
                                },
                                {
                                  "N4": "Geographic Location",
                                  "N4_01_description": "City Name",
                                  "N4_01": "MIAMI",
                                  "N4_02_description": "State or Province Code",
                                  "N4_02": "FL",
                                  "N4_03_description": "Postal Code",
                                  "N4_03": "33413"
                                },
                                {
                                  "DMG": "Demographic Information",
                                  "DMG_01_description": "Date Time Period Format Qualifier",
                                  "DMG_01": "D8",
                                  "DMG_01_code_D8": "Date Expressed in Format CCYYMMDD",
                                  "DMG_02_description": "Date Time Period",
                                  "DMG_02": "19730501",
                                  "DMG_03_description": "Gender Code",
                                  "DMG_03": "M",
                                  "DMG_03_code_M": "Male"
                                }
                              ]
                            },
                            {
                              "CLM-2300_loop": [
                                {
                                  "CLM": "Health Claim",
                                  "CLM_01_description": "Claim Submitter's Identifier",
                                  "CLM_01": "26463774",
                                  "CLM_02_description": "Monetary Amount",
                                  "CLM_02": "100",
                                  "CLM_05_description": "Health Care Service Location Information",
                                  "CLM_05": {
                                    "CLM_05_01_description": "Facility Code Value",
                                    "CLM_05_01": "11",
                                    "CLM_05_02_description": "Facility Code Qualifier",
                                    "CLM_05_02": "B",
                                    "CLM_05_02_code_B": "Place of Service Codes for Professional or Dental Services",
                                    "CLM_05_03_description": "Claim Frequency Type Code",
                                    "CLM_05_03": "1"
                                  },
                                  "CLM_06_description": "Yes/No Condition or Response Code",
                                  "CLM_06": "Y",
                                  "CLM_06_code_Y": "Yes",
                                  "CLM_07_description": "Provider Accept Assignment Code",
                                  "CLM_07": "A",
                                  "CLM_07_code_A": "Assigned",
                                  "CLM_08_description": "Yes/No Condition or Response Code",
                                  "CLM_08": "Y",
                                  "CLM_08_code_Y": "Yes",
                                  "CLM_09_description": "Release of Information Code",
                                  "CLM_09": "I",
                                  "CLM_09_code_I": "Informed Consent to Release Medical Information for Conditions or Diagnoses Regulated by Federal Statutes"
                                },
                                {
                                  "REF": "Reference Information",
                                  "REF_01_description": "Reference Identification Qualifier",
                                  "REF_01": "D9",
                                  "REF_01_code_D9": "Claim Number",
                                  "REF_02_description": "Reference Identification",
                                  "REF_02": "17312345600006351"
                                },
                                {
                                  "HI": "Health Care Information Codes",
                                  "HI_01_description": "Health Care Code Information",
                                  "HI_01": {
                                    "HI_01_01_description": "Code List Qualifier Code",
                                    "HI_01_01": "BK",
                                    "HI_01_01_code_BK": "International Classification of Diseases Clinical Modification (ICD-9-CM) Principal Diagnosis",
                                    "HI_01_02_description": "Industry Code",
                                    "HI_01_02": "0340"
                                  },
                                  "HI_02_description": "Health Care Code Information",
                                  "HI_02": {
                                    "HI_02_01_description": "Code List Qualifier Code",
                                    "HI_02_01": "BF",
                                    "HI_02_01_code_BF": "International Classification of Diseases Clinical Modification (ICD-9-CM) Diagnosis",
                                    "HI_02_02_description": "Industry Code",
                                    "HI_02_02": "V7389"
                                  }
                                },
                                {
                                  "LX-2400_loop": [
                                    {
                                      "LX": "Transaction Set Line Number",
                                      "LX_01_description": "Assigned Number",
                                      "LX_01": "1"
                                    },
                                    {
                                      "SV1": "Professional Service",
                                      "SV1_01_description": "Composite Medical Procedure Identifier",
                                      "SV1_01": {
                                        "SV1_01_01_description": "Product/Service ID Qualifier",
                                        "SV1_01_01": "HC",
                                        "SV1_01_01_code_HC": "Healthcare Common Procedure Coding System (HCPCS) Codes",
                                        "SV1_01_02_description": "Product/Service ID",
                                        "SV1_01_02": "99213"
                                      },
                                      "SV1_02_description": "Monetary Amount",
                                      "SV1_02": "40",
                                      "SV1_03_description": "Unit or Basis for Measurement Code",
                                      "SV1_03": "UN",
                                      "SV1_03_code_UN": "Unit",
                                      "SV1_04_description": "Quantity",
                                      "SV1_04": "1",
                                      "SV1_07_description": "Diagnosis Code Pointer",
                                      "SV1_07": "1"
                                    },
                                    {
                                      "DTP": "Date or Time or Period",
                                      "DTP_01_description": "Date/Time Qualifier",
                                      "DTP_01": "472",
                                      "DTP_01_code_472": "Service",
                                      "DTP_02_description": "Date Time Period Format Qualifier",
                                      "DTP_02": "D8",
                                      "DTP_02_code_D8": "Date Expressed in Format CCYYMMDD",
                                      "DTP_03_description": "Date Time Period",
                                      "DTP_03": "20061003"
                                    }
                                  ]
                                },
                                {
                                  "LX-2400_loop": [
                                    {
                                      "LX": "Transaction Set Line Number",
                                      "LX_01_description": "Assigned Number",
                                      "LX_01": "2"
                                    },
                                    {
                                      "SV1": "Professional Service",
                                      "SV1_01_description": "Composite Medical Procedure Identifier",
                                      "SV1_01": {
                                        "SV1_01_01_description": "Product/Service ID Qualifier",
                                        "SV1_01_01": "HC",
                                        "SV1_01_01_code_HC": "Healthcare Common Procedure Coding System (HCPCS) Codes",
                                        "SV1_01_02_description": "Product/Service ID",
                                        "SV1_01_02": "87070"
                                      },
                                      "SV1_02_description": "Monetary Amount",
                                      "SV1_02": "15",
                                      "SV1_03_description": "Unit or Basis for Measurement Code",
                                      "SV1_03": "UN",
                                      "SV1_03_code_UN": "Unit",
                                      "SV1_04_description": "Quantity",
                                      "SV1_04": "1",
                                      "SV1_07_description": "Diagnosis Code Pointer",
                                      "SV1_07": "1"
                                    },
                                    {
                                      "DTP": "Date or Time or Period",
                                      "DTP_01_description": "Date/Time Qualifier",
                                      "DTP_01": "472",
                                      "DTP_01_code_472": "Service",
                                      "DTP_02_description": "Date Time Period Format Qualifier",
                                      "DTP_02": "D8",
                                      "DTP_02_code_D8": "Date Expressed in Format CCYYMMDD",
                                      "DTP_03_description": "Date Time Period",
                                      "DTP_03": "20061003"
                                    }
                                  ]
                                },
                                {
                                  "LX-2400_loop": [
                                    {
                                      "LX": "Transaction Set Line Number",
                                      "LX_01_description": "Assigned Number",
                                      "LX_01": "3"
                                    },
                                    {
                                      "SV1": "Professional Service",
                                      "SV1_01_description": "Composite Medical Procedure Identifier",
                                      "SV1_01": {
                                        "SV1_01_01_description": "Product/Service ID Qualifier",
                                        "SV1_01_01": "HC",
                                        "SV1_01_01_code_HC": "Healthcare Common Procedure Coding System (HCPCS) Codes",
                                        "SV1_01_02_description": "Product/Service ID",
                                        "SV1_01_02": "99214"
                                      },
                                      "SV1_02_description": "Monetary Amount",
                                      "SV1_02": "35",
                                      "SV1_03_description": "Unit or Basis for Measurement Code",
                                      "SV1_03": "UN",
                                      "SV1_03_code_UN": "Unit",
                                      "SV1_04_description": "Quantity",
                                      "SV1_04": "1",
                                      "SV1_07_description": "Diagnosis Code Pointer",
                                      "SV1_07": "2"
                                    },
                                    {
                                      "DTP": "Date or Time or Period",
                                      "DTP_01_description": "Date/Time Qualifier",
                                      "DTP_01": "472",
                                      "DTP_01_code_472": "Service",
                                      "DTP_02_description": "Date Time Period Format Qualifier",
                                      "DTP_02": "D8",
                                      "DTP_02_code_D8": "Date Expressed in Format CCYYMMDD",
                                      "DTP_03_description": "Date Time Period",
                                      "DTP_03": "20061010"
                                    }
                                  ]
                                },
                                {
                                  "LX-2400_loop": [
                                    {
                                      "LX": "Transaction Set Line Number",
                                      "LX_01_description": "Assigned Number",
                                      "LX_01": "4"
                                    },
                                    {
                                      "SV1": "Professional Service",
                                      "SV1_01_description": "Composite Medical Procedure Identifier",
                                      "SV1_01": {
                                        "SV1_01_01_description": "Product/Service ID Qualifier",
                                        "SV1_01_01": "HC",
                                        "SV1_01_01_code_HC": "Healthcare Common Procedure Coding System (HCPCS) Codes",
                                        "SV1_01_02_description": "Product/Service ID",
                                        "SV1_01_02": "86663"
                                      },
                                      "SV1_02_description": "Monetary Amount",
                                      "SV1_02": "10",
                                      "SV1_03_description": "Unit or Basis for Measurement Code",
                                      "SV1_03": "UN",
                                      "SV1_03_code_UN": "Unit",
                                      "SV1_04_description": "Quantity",
                                      "SV1_04": "1",
                                      "SV1_07_description": "Diagnosis Code Pointer",
                                      "SV1_07": "2"
                                    },
                                    {
                                      "DTP": "Date or Time or Period",
                                      "DTP_01_description": "Date/Time Qualifier",
                                      "DTP_01": "472",
                                      "DTP_01_code_472": "Service",
                                      "DTP_02_description": "Date Time Period Format Qualifier",
                                      "DTP_02": "D8",
                                      "DTP_02_code_D8": "Date Expressed in Format CCYYMMDD",
                                      "DTP_03_description": "Date Time Period",
                                      "DTP_03": "20061010"
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
              ]
            }
          ]
        }
      ]
    }
  ]
}
```

[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)

## 837 Validating and Balancing Add-on

The X12 837 Health Care Claim is a very common transaction set converted to/from JSON
and perhaps the most complex. The 837 add-on to the Premium Edition brings two additioanl tools
useful when handling health care claims. These tools are supported for version 005010
of the 837P (Professional),
837I (Institutional), and 837D (Dental).

* Detailed EDI validation
  - Unexpected or invalid segment type
  - Required segment missing
  - Too many repetitions of a segment
  - Required element missing
  - Element value too long or too short
  - Element value not consistent with data type
  - Element value not defined in code list
  - Required loop missing
  - Too many repetitions of a loop
  - Enveloping error with ISA, GS, ST, SE, GE, or IEA
* Balancing monetary amounts within a claim
  - Total claim amount with sum of line item charges
  - Line item charge with the sum of adjudication and adjustments for that item
  - Coordination of benefits involving primary and secondary payers and patient payments

The 837 add-on is provided as a separate command line tool that accepts filename arguments for the
EDI input and an output file for a text report like the one shown below.

(sample report goes here)

## Command Line Interface
The jar is runnable with Java 7 or later with the following command line arguments.

**java -jar edireader-json-basic-5.5.13.jar**   *edi-input-file*  *json-output-file* *option...*


Option | Values |Description  | Default
|:-------|:-------|:-------|:-------
--format=value | yes, no |format JSON output |yes
--annotate=value | yes, no | annotate JSON output|no
--summarize=value | yes, no |omit segment-level detail after first segment |no

[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)

## JSON to EDI (with Premium Edition)

A recent addition to the Premium Edition is the ability to perform the
reverse transformation, converting JSON like that shown above into EDI output. 

Here is a summary of the features:

* Integration options
  - command line tool with filename arguments and configuration options
  - Java API
* Configurable EDI syntax characteristics
  - element and sub-element delimiters
  - segment terminator
  - optional inclusion of line separators between segments
* EDI standards
  - ANSI X12
  - EDIFACT (not currently supported, but can be added)
* JSON input
  - any of the JSON variations shown above are supported
  - segment looping structures are optional
* Supports multiple ...
  - transactions per functional group
  - functional groups per interchange
  - interchanges per file
* Transaction, group, and interchange trailers
  - appropriate EDI segments are automatically generated
  - with proper counts and control numbers
  - for example: SE, GE, and IEA segments in ANSI X12
* Resolves conflicts with EDI syntax characters
  - for example, a : (colon) in a data field when : is the configured sub-element delimiter
  - handled automatically, guaranteeing structurally correct EDI output
  - by substituting "?" for the character in conflict

[Intro](#edi-to-json--json-to-edi)
| [Features](#feature-summary-for-edi-to-json)
| [Editions](#basic-and-premium-editions)
| [Simple Example](#a-simple-example)
| [837 Example](#another-example-837-health-care-claim)
| [837 Validation](#837-validating-and-balancing-add-on)
| [Command Line Interface](#command-line-interface)
| [JSON to EDI](#json-to-edi-with-premium-edition)
| [EDI to YAML](#edi-to-yaml-coming-soon)

## EDI to YAML (coming soon!)

The same framework that parses EDI and serializes to JSON or XML can also easily generate a YAML
expression of EDI input as well.
While EDI content accessible as JSON or XML is useful to simplify processing,
the YAML representation is advantageous for a human-readability, especially with the descriptive annotations available.

This features is in active development. If you are interested becoming a pilot user, please let us know.