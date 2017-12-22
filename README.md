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
  
## Basic and Premium Editions
The jar provided with this project is a free and fully usable basic edition, and a premium edition is coming soon.
Here is a summary of the differences.

Feature | Basic  | Premium
--------|--------|--------
Formatting | yes | yes
X12        | yes | yes
EDIFACT    | yes | yes
Annotation | limited | extensive
Segment loops shown in JSON | no | yes 
  
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

Here is the JSON output produced by the Basic Edition.
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
