package com.berryworks.edireader.json.fromedi;

import com.berryworks.edireader.EDISyntaxException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EdiToJsonTest {

    EdiToJson ediToJson;

    @Test
    public void nonEdi() throws IOException, SAXException {
        ediToJson = new EdiToJson();
        try {
            ediToJson.asJson("abc");
            fail();
        } catch (EDISyntaxException e) {
            assertEquals("No supported EDI standard interchange begins with abc", e.getMessage());
        }
    }

    @Test
    public void minimalX12_formatted() throws IOException, SAXException {
        ediToJson = new EdiToJson();
        ediToJson.setFormatting(true);
        String json = ediToJson.asJson(MINIMAL_X12);
        assertTrue(json.length() > 1600);
        assertEquals(MINIMAL_X12_JSON_FORMATTED, json);
    }

    @Test
    public void minimalX12_notFormatted() throws IOException, SAXException {
        ediToJson = new EdiToJson();
        ediToJson.setFormatting(false);
        String json = ediToJson.asJson(MINIMAL_X12);
        assertTrue(json.length() < 1200);
        assertEquals(MINIMAL_X12_JSON_FORMATTED
                        .replace("\r", "")
                        .replaceAll("\n *", "")
                , json);
    }

    public static final String MINIMAL_X12 = """
            ISA*00*          *00*          *ZZ*SENDERID       *ZZ*RECEIVERID     *260513*0715*^*00501*000000001*0*T*:~
            GS*HC*SENDERID*RECEIVERID*20260513*0715*1*X*005010X222A1~
            ST*837*0001*005010X222A1~
            BHT*0019*00*BATCH1001*20260513*0715*CH~
            SE*3*0001~
            GE*1*1~
            IEA*1*000000001~
            """;

    public static final String MINIMAL_X12_JSON_FORMATTED = """
            {
              "interchanges": [
                {
                  "ISA_01_AuthorizationQualifier": "00",
                  "ISA_02_AuthorizationInformation": "          ",
                  "ISA_03_SecurityQualifier": "00",
                  "ISA_04_SecurityInformation": "          ",
                  "ISA_05_SenderQualifier": "ZZ",
                  "ISA_06_SenderId": "SENDERID       ",
                  "ISA_07_ReceiverQualifier": "ZZ",
                  "ISA_08_ReceiverId": "RECEIVERID     ",
                  "ISA_09_Date": "260513",
                  "ISA_10_Time": "0715",
                  "ISA_11_RepetitionSeparator": "^",
                  "ISA_12_Version": "00501",
                  "ISA_13_InterchangeControlNumber": "000000001",
                  "ISA_14_AcknowledgmentRequested": "0",
                  "ISA_15_TestIndicator": "T",
                  "functional_groups": [
                    {
                      "GS_01_FunctionalIdentifierCode": "HC",
                      "GS_02_ApplicationSenderCode": "SENDERID",
                      "GS_03_ApplicationReceiverCode": "RECEIVERID",
                      "GS_04_Date": "20260513",
                      "GS_05_Time": "0715",
                      "GS_06_GroupControlNumber": "1",
                      "GS_07_ResponsibleAgencyCode": "X",
                      "GS_08_Version": "005010X222A1",
                      "transactions": [
                        {
                          "ST_01_TransactionSetIdentifierCode": "837",
                          "ST_02_TransactionSetControlNumber": "0001",
                          "ST_03_ImplementationConventionReference": "005010X222A1",
                          "segments": [
                            {
                              "BHT_01": "0019",
                              "BHT_02": "00",
                              "BHT_03": "BATCH1001",
                              "BHT_04": "20260513",
                              "BHT_05": "0715",
                              "BHT_06": "CH"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
              ]
            }""";

}
