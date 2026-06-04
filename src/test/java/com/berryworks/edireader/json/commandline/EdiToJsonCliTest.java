package com.berryworks.edireader.json.commandline;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.berryworks.edireader.json.ResourceUtil;

import static com.berryworks.edireader.json.FileUtil.fileToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdiToJsonCliTest {

    @Test
    void ediToJson() throws IOException {
        EdiToJsonCli.main(new String[]{"837.edi", "output.edi"});
        assertEquals("""
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
                                        },
                                        {
                                          "NM1-1000A_loop": [
                                            {
                                              "NM1_01": "41",
                                              "NM1_02": "2",
                                              "NM1_03": "SAMPLE BILLING SERVICE",
                                              "NM1_08": "46",
                                              "NM1_09": "123456789"
                                            },
                                            {
                                              "PER_01": "IC",
                                              "PER_02": "EDI SUPPORT",
                                              "PER_03": "TE",
                                              "PER_04": "8005551212"
                                            }
                                          ]
                                        },
                                        {
                                          "NM1-1000B_loop": [
                                            {
                                              "NM1_01": "40",
                                              "NM1_02": "2",
                                              "NM1_03": "SAMPLE PAYER",
                                              "NM1_08": "46",
                                              "NM1_09": "999999999"
                                            }
                                          ]
                                        },
                                        {
                                          "HL-2000A_loop": [
                                            {
                                              "HL_01": "1",
                                              "HL_03": "20",
                                              "HL_04": "1"
                                            },
                                            {
                                              "NM1-2010AA_loop": [
                                                {
                                                  "NM1_01": "85",
                                                  "NM1_02": "2",
                                                  "NM1_03": "SAMPLE CLINIC",
                                                  "NM1_08": "XX",
                                                  "NM1_09": "1234567893"
                                                },
                                                {
                                                  "N3_01": "100 MAIN STREET"
                                                },
                                                {
                                                  "N4_01": "NASHVILLE",
                                                  "N4_02": "TN",
                                                  "N4_03": "37201"
                                                },
                                                {
                                                  "REF_01": "EI",
                                                  "REF_02": "123456789"
                                                }
                                              ]
                                            },
                                            {
                                              "HL-2000B_loop": [
                                                {
                                                  "HL_01": "2",
                                                  "HL_02": "1",
                                                  "HL_03": "22",
                                                  "HL_04": "0"
                                                },
                                                {
                                                  "SBR_01": "P",
                                                  "SBR_02": "18",
                                                  "SBR_09": "CI"
                                                },
                                                {
                                                  "NM1-2010BA_loop": [
                                                    {
                                                      "NM1_01": "IL",
                                                      "NM1_02": "1",
                                                      "NM1_03": "DOE",
                                                      "NM1_04": "JOHN",
                                                      "NM1_08": "MI",
                                                      "NM1_09": "ABC12345"
                                                    },
                                                    {
                                                      "N3_01": "200 OAK AVENUE"
                                                    },
                                                    {
                                                      "N4_01": "NASHVILLE",
                                                      "N4_02": "TN",
                                                      "N4_03": "37202"
                                                    },
                                                    {
                                                      "DMG_01": "D8",
                                                      "DMG_02": "19800101",
                                                      "DMG_03": "M"
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM_01": "CLAIM0001",
                                                      "CLM_02": "125.00",
                                                      "CLM_05": {
                                                        "CLM_05_01": "11",
                                                        "CLM_05_02": "B",
                                                        "CLM_05_03": "1"
                                                      },
                                                      "CLM_06": "Y",
                                                      "CLM_07": "A",
                                                      "CLM_08": "Y",
                                                      "CLM_09": "I"
                                                    },
                                                    {
                                                      "HI_01": {
                                                        "HI_01_01": "ABK",
                                                        "HI_01_02": "J1099"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1_01": "82",
                                                          "NM1_02": "1",
                                                          "NM1_03": "SMITH",
                                                          "NM1_04": "JANE",
                                                          "NM1_08": "XX",
                                                          "NM1_09": "1111111111"
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "LX-2400_loop": [
                                                        {
                                                          "LX_01": "1"
                                                        },
                                                        {
                                                          "SV1_01": {
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_02": "99213"
                                                          },
                                                          "SV1_02": "75",
                                                          "SV1_03": "UN",
                                                          "SV1_04": "1",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "DTP_01": "472",
                                                          "DTP_02": "D8",
                                                          "DTP_03": "20260510"
                                                        }
                                                      ]
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM_01": "CLAIM0002",
                                                      "CLM_02": "240.00",
                                                      "CLM_05": {
                                                        "CLM_05_01": "11",
                                                        "CLM_05_02": "B",
                                                        "CLM_05_03": "1"
                                                      },
                                                      "CLM_06": "Y",
                                                      "CLM_07": "A",
                                                      "CLM_08": "Y",
                                                      "CLM_09": "I"
                                                    },
                                                    {
                                                      "HI_01": {
                                                        "HI_01_01": "ABK",
                                                        "HI_01_02": "M545"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1_01": "82",
                                                          "NM1_02": "1",
                                                          "NM1_03": "SMITH",
                                                          "NM1_04": "JANE",
                                                          "NM1_08": "XX",
                                                          "NM1_09": "1111111111"
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "LX-2400_loop": [
                                                        {
                                                          "LX_01": "1"
                                                        },
                                                        {
                                                          "SV1_01": {
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_02": "93000"
                                                          },
                                                          "SV1_02": "240",
                                                          "SV1_03": "UN",
                                                          "SV1_04": "1",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "DTP_01": "472",
                                                          "DTP_02": "D8",
                                                          "DTP_03": "20260511"
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
                                    },
                                    {
                                      "ST_01_TransactionSetIdentifierCode": "837",
                                      "ST_02_TransactionSetControlNumber": "0002",
                                      "ST_03_ImplementationConventionReference": "005010X222A1",
                                      "segments": [
                                        {
                                          "BHT_01": "0019",
                                          "BHT_02": "00",
                                          "BHT_03": "BATCH1002",
                                          "BHT_04": "20260513",
                                          "BHT_05": "0720",
                                          "BHT_06": "CH"
                                        },
                                        {
                                          "NM1-1000A_loop": [
                                            {
                                              "NM1_01": "41",
                                              "NM1_02": "2",
                                              "NM1_03": "SAMPLE BILLING SERVICE",
                                              "NM1_08": "46",
                                              "NM1_09": "123456789"
                                            },
                                            {
                                              "PER_01": "IC",
                                              "PER_02": "EDI SUPPORT",
                                              "PER_03": "TE",
                                              "PER_04": "8005551212"
                                            }
                                          ]
                                        },
                                        {
                                          "NM1-1000B_loop": [
                                            {
                                              "NM1_01": "40",
                                              "NM1_02": "2",
                                              "NM1_03": "SAMPLE PAYER",
                                              "NM1_08": "46",
                                              "NM1_09": "999999999"
                                            }
                                          ]
                                        },
                                        {
                                          "HL-2000A_loop": [
                                            {
                                              "HL_01": "1",
                                              "HL_03": "20",
                                              "HL_04": "1"
                                            },
                                            {
                                              "NM1-2010AA_loop": [
                                                {
                                                  "NM1_01": "85",
                                                  "NM1_02": "2",
                                                  "NM1_03": "SAMPLE CLINIC",
                                                  "NM1_08": "XX",
                                                  "NM1_09": "1234567893"
                                                },
                                                {
                                                  "N3_01": "100 MAIN STREET"
                                                },
                                                {
                                                  "N4_01": "NASHVILLE",
                                                  "N4_02": "TN",
                                                  "N4_03": "37201"
                                                },
                                                {
                                                  "REF_01": "EI",
                                                  "REF_02": "123456789"
                                                }
                                              ]
                                            },
                                            {
                                              "HL-2000B_loop": [
                                                {
                                                  "HL_01": "2",
                                                  "HL_02": "1",
                                                  "HL_03": "22",
                                                  "HL_04": "0"
                                                },
                                                {
                                                  "SBR_01": "P",
                                                  "SBR_02": "18",
                                                  "SBR_09": "CI"
                                                },
                                                {
                                                  "NM1-2010BA_loop": [
                                                    {
                                                      "NM1_01": "IL",
                                                      "NM1_02": "1",
                                                      "NM1_03": "DOE",
                                                      "NM1_04": "JANE",
                                                      "NM1_08": "MI",
                                                      "NM1_09": "XYZ67890"
                                                    },
                                                    {
                                                      "N3_01": "500 MAPLE DRIVE"
                                                    },
                                                    {
                                                      "N4_01": "FRANKLIN",
                                                      "N4_02": "TN",
                                                      "N4_03": "37064"
                                                    },
                                                    {
                                                      "DMG_01": "D8",
                                                      "DMG_02": "19751212",
                                                      "DMG_03": "F"
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM_01": "CLAIM0003",
                                                      "CLM_02": "88.00",
                                                      "CLM_05": {
                                                        "CLM_05_01": "11",
                                                        "CLM_05_02": "B",
                                                        "CLM_05_03": "1"
                                                      },
                                                      "CLM_06": "Y",
                                                      "CLM_07": "A",
                                                      "CLM_08": "Y",
                                                      "CLM_09": "I"
                                                    },
                                                    {
                                                      "HI_01": {
                                                        "HI_01_01": "ABK",
                                                        "HI_01_02": "R101"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1_01": "82",
                                                          "NM1_02": "1",
                                                          "NM1_03": "ADAMS",
                                                          "NM1_04": "ROBERT",
                                                          "NM1_08": "XX",
                                                          "NM1_09": "2222222222"
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "LX-2400_loop": [
                                                        {
                                                          "LX_01": "1"
                                                        },
                                                        {
                                                          "SV1_01": {
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_02": "87070"
                                                          },
                                                          "SV1_02": "88",
                                                          "SV1_03": "UN",
                                                          "SV1_04": "1",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "DTP_01": "472",
                                                          "DTP_02": "D8",
                                                          "DTP_03": "20260512"
                                                        }
                                                      ]
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM_01": "CLAIM0004",
                                                      "CLM_02": "315.00",
                                                      "CLM_05": {
                                                        "CLM_05_01": "11",
                                                        "CLM_05_02": "B",
                                                        "CLM_05_03": "1"
                                                      },
                                                      "CLM_06": "Y",
                                                      "CLM_07": "A",
                                                      "CLM_08": "Y",
                                                      "CLM_09": "I"
                                                    },
                                                    {
                                                      "HI_01": {
                                                        "HI_01_01": "ABK",
                                                        "HI_01_02": "E119"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1_01": "82",
                                                          "NM1_02": "1",
                                                          "NM1_03": "ADAMS",
                                                          "NM1_04": "ROBERT",
                                                          "NM1_08": "XX",
                                                          "NM1_09": "2222222222"
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "LX-2400_loop": [
                                                        {
                                                          "LX_01": "1"
                                                        },
                                                        {
                                                          "SV1_01": {
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_02": "99214"
                                                          },
                                                          "SV1_02": "150",
                                                          "SV1_03": "UN",
                                                          "SV1_04": "1",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "SV1_01": {
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_02": "80053"
                                                          },
                                                          "SV1_02": "165",
                                                          "SV1_03": "UN",
                                                          "SV1_04": "1",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "DTP_01": "472",
                                                          "DTP_02": "D8",
                                                          "DTP_03": "20260512"
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
                        }""",
                fileToString("output.edi"));
    }

    @Test
    void ediToJson_annotate() throws IOException {
        EdiToJsonCli.main(new String[]{"837.edi", "output.edi", "--annotate=yes"});
        assertEquals("""
                        {
                          "interchanges": [
                            {
                              "ISA": "Interchange Control Header",
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
                                  "GS": "Functional Group Header",
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
                                      "837": "Health Care Claim",
                                      "ST": "Transaction Set Header",
                                      "ST_01_TransactionSetIdentifierCode": "837",
                                      "ST_02_TransactionSetControlNumber": "0001",
                                      "ST_03_ImplementationConventionReference": "005010X222A1",
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
                                          "BHT_03": "BATCH1001",
                                          "BHT_04_description": "Date",
                                          "BHT_04": "20260513",
                                          "BHT_05_description": "Time",
                                          "BHT_05": "0715",
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
                                              "NM1_03": "SAMPLE BILLING SERVICE",
                                              "NM1_08_description": "Identification Code Qualifier",
                                              "NM1_08": "46",
                                              "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                                              "NM1_09_description": "Identification Code",
                                              "NM1_09": "123456789"
                                            },
                                            {
                                              "PER": "Administrative Communications Contact",
                                              "PER_01_description": "Contact Function Code",
                                              "PER_01": "IC",
                                              "PER_01_code_IC": "Information Contact",
                                              "PER_02_description": "Name",
                                              "PER_02": "EDI SUPPORT",
                                              "PER_03_description": "Communication Number Qualifier",
                                              "PER_03": "TE",
                                              "PER_03_code_TE": "Telephone",
                                              "PER_04_description": "Communication Number",
                                              "PER_04": "8005551212"
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
                                              "NM1_03": "SAMPLE PAYER",
                                              "NM1_08_description": "Identification Code Qualifier",
                                              "NM1_08": "46",
                                              "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                                              "NM1_09_description": "Identification Code",
                                              "NM1_09": "999999999"
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
                                                  "NM1_03": "SAMPLE CLINIC",
                                                  "NM1_08_description": "Identification Code Qualifier",
                                                  "NM1_08": "XX",
                                                  "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                  "NM1_09_description": "Identification Code",
                                                  "NM1_09": "1234567893"
                                                },
                                                {
                                                  "N3": "Party Location",
                                                  "N3_01_description": "Address Information",
                                                  "N3_01": "100 MAIN STREET"
                                                },
                                                {
                                                  "N4": "Geographic Location",
                                                  "N4_01_description": "City Name",
                                                  "N4_01": "NASHVILLE",
                                                  "N4_02_description": "State or Province Code",
                                                  "N4_02": "TN",
                                                  "N4_03_description": "Postal Code",
                                                  "N4_03": "37201"
                                                },
                                                {
                                                  "REF": "Reference Information",
                                                  "REF_01_description": "Reference Identification Qualifier",
                                                  "REF_01": "EI",
                                                  "REF_01_code_EI": "Employer's Identification Number",
                                                  "REF_02_description": "Reference Identification",
                                                  "REF_02": "123456789"
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
                                                  "HL_04": "0",
                                                  "HL_04_code_0": "No Subordinate HL Segment in This Hierarchical Structure."
                                                },
                                                {
                                                  "SBR": "Subscriber Information",
                                                  "SBR_01_description": "Payer Responsibility Sequence Number Code",
                                                  "SBR_01": "P",
                                                  "SBR_01_code_P": "Primary",
                                                  "SBR_02_description": "Individual Relationship Code",
                                                  "SBR_02": "18",
                                                  "SBR_02_code_18": "Self",
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
                                                      "NM1_03": "DOE",
                                                      "NM1_04_description": "Name First",
                                                      "NM1_04": "JOHN",
                                                      "NM1_08_description": "Identification Code Qualifier",
                                                      "NM1_08": "MI",
                                                      "NM1_08_code_MI": "Member Identification Number",
                                                      "NM1_09_description": "Identification Code",
                                                      "NM1_09": "ABC12345"
                                                    },
                                                    {
                                                      "N3": "Party Location",
                                                      "N3_01_description": "Address Information",
                                                      "N3_01": "200 OAK AVENUE"
                                                    },
                                                    {
                                                      "N4": "Geographic Location",
                                                      "N4_01_description": "City Name",
                                                      "N4_01": "NASHVILLE",
                                                      "N4_02_description": "State or Province Code",
                                                      "N4_02": "TN",
                                                      "N4_03_description": "Postal Code",
                                                      "N4_03": "37202"
                                                    },
                                                    {
                                                      "DMG": "Demographic Information",
                                                      "DMG_01_description": "Date Time Period Format Qualifier",
                                                      "DMG_01": "D8",
                                                      "DMG_01_code_D8": "Date Expressed in Format CCYYMMDD",
                                                      "DMG_02_description": "Date Time Period",
                                                      "DMG_02": "19800101",
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
                                                      "CLM_01": "CLAIM0001",
                                                      "CLM_02_description": "Monetary Amount",
                                                      "CLM_02": "125.00",
                                                      "CLM_05_description": "Health Care Service Location Information",
                                                      "CLM_05": {
                                                        "CLM_05_01_description": "Facility Code Value",
                                                        "CLM_05_01": "11",
                                                        "CLM_05_01_code_11": "Office",
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
                                                      "HI": "Health Care Information Codes",
                                                      "HI_01_description": "Health Care Code Information",
                                                      "HI_01": {
                                                        "HI_01_01_description": "Code List Qualifier Code",
                                                        "HI_01_01": "ABK",
                                                        "HI_01_01_code_ABK": "International Classification of Diseases Clinical Modification (ICD-10-CM) Principal Diagnosis",
                                                        "HI_01_02_description": "Industry Code",
                                                        "HI_01_02": "J1099"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1": "Individual or Organizational Name",
                                                          "NM1_01_description": "Entity Identifier Code",
                                                          "NM1_01": "82",
                                                          "NM1_01_code_82": "Rendering Provider",
                                                          "NM1_02_description": "Entity Type Qualifier",
                                                          "NM1_02": "1",
                                                          "NM1_02_code_1": "Person",
                                                          "NM1_03_description": "Name Last or Organization Name",
                                                          "NM1_03": "SMITH",
                                                          "NM1_04_description": "Name First",
                                                          "NM1_04": "JANE",
                                                          "NM1_08_description": "Identification Code Qualifier",
                                                          "NM1_08": "XX",
                                                          "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                          "NM1_09_description": "Identification Code",
                                                          "NM1_09": "1111111111"
                                                        }
                                                      ]
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
                                                          "SV1_02": "75",
                                                          "SV1_03_description": "Unit or Basis for Measurement Code",
                                                          "SV1_03": "UN",
                                                          "SV1_03_code_UN": "Unit",
                                                          "SV1_04_description": "Quantity",
                                                          "SV1_04": "1",
                                                          "SV1_07_description": "Diagnosis Code Pointer",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
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
                                                          "DTP_03": "20260510"
                                                        }
                                                      ]
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM": "Health Claim",
                                                      "CLM_01_description": "Claim Submitter's Identifier",
                                                      "CLM_01": "CLAIM0002",
                                                      "CLM_02_description": "Monetary Amount",
                                                      "CLM_02": "240.00",
                                                      "CLM_05_description": "Health Care Service Location Information",
                                                      "CLM_05": {
                                                        "CLM_05_01_description": "Facility Code Value",
                                                        "CLM_05_01": "11",
                                                        "CLM_05_01_code_11": "Office",
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
                                                      "HI": "Health Care Information Codes",
                                                      "HI_01_description": "Health Care Code Information",
                                                      "HI_01": {
                                                        "HI_01_01_description": "Code List Qualifier Code",
                                                        "HI_01_01": "ABK",
                                                        "HI_01_01_code_ABK": "International Classification of Diseases Clinical Modification (ICD-10-CM) Principal Diagnosis",
                                                        "HI_01_02_description": "Industry Code",
                                                        "HI_01_02": "M545"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1": "Individual or Organizational Name",
                                                          "NM1_01_description": "Entity Identifier Code",
                                                          "NM1_01": "82",
                                                          "NM1_01_code_82": "Rendering Provider",
                                                          "NM1_02_description": "Entity Type Qualifier",
                                                          "NM1_02": "1",
                                                          "NM1_02_code_1": "Person",
                                                          "NM1_03_description": "Name Last or Organization Name",
                                                          "NM1_03": "SMITH",
                                                          "NM1_04_description": "Name First",
                                                          "NM1_04": "JANE",
                                                          "NM1_08_description": "Identification Code Qualifier",
                                                          "NM1_08": "XX",
                                                          "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                          "NM1_09_description": "Identification Code",
                                                          "NM1_09": "1111111111"
                                                        }
                                                      ]
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
                                                            "SV1_01_02": "93000"
                                                          },
                                                          "SV1_02_description": "Monetary Amount",
                                                          "SV1_02": "240",
                                                          "SV1_03_description": "Unit or Basis for Measurement Code",
                                                          "SV1_03": "UN",
                                                          "SV1_03_code_UN": "Unit",
                                                          "SV1_04_description": "Quantity",
                                                          "SV1_04": "1",
                                                          "SV1_07_description": "Diagnosis Code Pointer",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
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
                                                          "DTP_03": "20260511"
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
                                    },
                                    {
                                      "837": "Health Care Claim",
                                      "ST": "Transaction Set Header",
                                      "ST_01_TransactionSetIdentifierCode": "837",
                                      "ST_02_TransactionSetControlNumber": "0002",
                                      "ST_03_ImplementationConventionReference": "005010X222A1",
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
                                          "BHT_03": "BATCH1002",
                                          "BHT_04_description": "Date",
                                          "BHT_04": "20260513",
                                          "BHT_05_description": "Time",
                                          "BHT_05": "0720",
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
                                              "NM1_03": "SAMPLE BILLING SERVICE",
                                              "NM1_08_description": "Identification Code Qualifier",
                                              "NM1_08": "46",
                                              "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                                              "NM1_09_description": "Identification Code",
                                              "NM1_09": "123456789"
                                            },
                                            {
                                              "PER": "Administrative Communications Contact",
                                              "PER_01_description": "Contact Function Code",
                                              "PER_01": "IC",
                                              "PER_01_code_IC": "Information Contact",
                                              "PER_02_description": "Name",
                                              "PER_02": "EDI SUPPORT",
                                              "PER_03_description": "Communication Number Qualifier",
                                              "PER_03": "TE",
                                              "PER_03_code_TE": "Telephone",
                                              "PER_04_description": "Communication Number",
                                              "PER_04": "8005551212"
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
                                              "NM1_03": "SAMPLE PAYER",
                                              "NM1_08_description": "Identification Code Qualifier",
                                              "NM1_08": "46",
                                              "NM1_08_code_46": "Electronic Transmitter Identification Number (ETIN)",
                                              "NM1_09_description": "Identification Code",
                                              "NM1_09": "999999999"
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
                                                  "NM1_03": "SAMPLE CLINIC",
                                                  "NM1_08_description": "Identification Code Qualifier",
                                                  "NM1_08": "XX",
                                                  "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                  "NM1_09_description": "Identification Code",
                                                  "NM1_09": "1234567893"
                                                },
                                                {
                                                  "N3": "Party Location",
                                                  "N3_01_description": "Address Information",
                                                  "N3_01": "100 MAIN STREET"
                                                },
                                                {
                                                  "N4": "Geographic Location",
                                                  "N4_01_description": "City Name",
                                                  "N4_01": "NASHVILLE",
                                                  "N4_02_description": "State or Province Code",
                                                  "N4_02": "TN",
                                                  "N4_03_description": "Postal Code",
                                                  "N4_03": "37201"
                                                },
                                                {
                                                  "REF": "Reference Information",
                                                  "REF_01_description": "Reference Identification Qualifier",
                                                  "REF_01": "EI",
                                                  "REF_01_code_EI": "Employer's Identification Number",
                                                  "REF_02_description": "Reference Identification",
                                                  "REF_02": "123456789"
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
                                                  "HL_04": "0",
                                                  "HL_04_code_0": "No Subordinate HL Segment in This Hierarchical Structure."
                                                },
                                                {
                                                  "SBR": "Subscriber Information",
                                                  "SBR_01_description": "Payer Responsibility Sequence Number Code",
                                                  "SBR_01": "P",
                                                  "SBR_01_code_P": "Primary",
                                                  "SBR_02_description": "Individual Relationship Code",
                                                  "SBR_02": "18",
                                                  "SBR_02_code_18": "Self",
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
                                                      "NM1_03": "DOE",
                                                      "NM1_04_description": "Name First",
                                                      "NM1_04": "JANE",
                                                      "NM1_08_description": "Identification Code Qualifier",
                                                      "NM1_08": "MI",
                                                      "NM1_08_code_MI": "Member Identification Number",
                                                      "NM1_09_description": "Identification Code",
                                                      "NM1_09": "XYZ67890"
                                                    },
                                                    {
                                                      "N3": "Party Location",
                                                      "N3_01_description": "Address Information",
                                                      "N3_01": "500 MAPLE DRIVE"
                                                    },
                                                    {
                                                      "N4": "Geographic Location",
                                                      "N4_01_description": "City Name",
                                                      "N4_01": "FRANKLIN",
                                                      "N4_02_description": "State or Province Code",
                                                      "N4_02": "TN",
                                                      "N4_03_description": "Postal Code",
                                                      "N4_03": "37064"
                                                    },
                                                    {
                                                      "DMG": "Demographic Information",
                                                      "DMG_01_description": "Date Time Period Format Qualifier",
                                                      "DMG_01": "D8",
                                                      "DMG_01_code_D8": "Date Expressed in Format CCYYMMDD",
                                                      "DMG_02_description": "Date Time Period",
                                                      "DMG_02": "19751212",
                                                      "DMG_03_description": "Gender Code",
                                                      "DMG_03": "F",
                                                      "DMG_03_code_F": "Female"
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM": "Health Claim",
                                                      "CLM_01_description": "Claim Submitter's Identifier",
                                                      "CLM_01": "CLAIM0003",
                                                      "CLM_02_description": "Monetary Amount",
                                                      "CLM_02": "88.00",
                                                      "CLM_05_description": "Health Care Service Location Information",
                                                      "CLM_05": {
                                                        "CLM_05_01_description": "Facility Code Value",
                                                        "CLM_05_01": "11",
                                                        "CLM_05_01_code_11": "Office",
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
                                                      "HI": "Health Care Information Codes",
                                                      "HI_01_description": "Health Care Code Information",
                                                      "HI_01": {
                                                        "HI_01_01_description": "Code List Qualifier Code",
                                                        "HI_01_01": "ABK",
                                                        "HI_01_01_code_ABK": "International Classification of Diseases Clinical Modification (ICD-10-CM) Principal Diagnosis",
                                                        "HI_01_02_description": "Industry Code",
                                                        "HI_01_02": "R101"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1": "Individual or Organizational Name",
                                                          "NM1_01_description": "Entity Identifier Code",
                                                          "NM1_01": "82",
                                                          "NM1_01_code_82": "Rendering Provider",
                                                          "NM1_02_description": "Entity Type Qualifier",
                                                          "NM1_02": "1",
                                                          "NM1_02_code_1": "Person",
                                                          "NM1_03_description": "Name Last or Organization Name",
                                                          "NM1_03": "ADAMS",
                                                          "NM1_04_description": "Name First",
                                                          "NM1_04": "ROBERT",
                                                          "NM1_08_description": "Identification Code Qualifier",
                                                          "NM1_08": "XX",
                                                          "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                          "NM1_09_description": "Identification Code",
                                                          "NM1_09": "2222222222"
                                                        }
                                                      ]
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
                                                            "SV1_01_02": "87070"
                                                          },
                                                          "SV1_02_description": "Monetary Amount",
                                                          "SV1_02": "88",
                                                          "SV1_03_description": "Unit or Basis for Measurement Code",
                                                          "SV1_03": "UN",
                                                          "SV1_03_code_UN": "Unit",
                                                          "SV1_04_description": "Quantity",
                                                          "SV1_04": "1",
                                                          "SV1_07_description": "Diagnosis Code Pointer",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
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
                                                          "DTP_03": "20260512"
                                                        }
                                                      ]
                                                    }
                                                  ]
                                                },
                                                {
                                                  "CLM-2300_loop": [
                                                    {
                                                      "CLM": "Health Claim",
                                                      "CLM_01_description": "Claim Submitter's Identifier",
                                                      "CLM_01": "CLAIM0004",
                                                      "CLM_02_description": "Monetary Amount",
                                                      "CLM_02": "315.00",
                                                      "CLM_05_description": "Health Care Service Location Information",
                                                      "CLM_05": {
                                                        "CLM_05_01_description": "Facility Code Value",
                                                        "CLM_05_01": "11",
                                                        "CLM_05_01_code_11": "Office",
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
                                                      "HI": "Health Care Information Codes",
                                                      "HI_01_description": "Health Care Code Information",
                                                      "HI_01": {
                                                        "HI_01_01_description": "Code List Qualifier Code",
                                                        "HI_01_01": "ABK",
                                                        "HI_01_01_code_ABK": "International Classification of Diseases Clinical Modification (ICD-10-CM) Principal Diagnosis",
                                                        "HI_01_02_description": "Industry Code",
                                                        "HI_01_02": "E119"
                                                      }
                                                    },
                                                    {
                                                      "NM1-2310B_loop": [
                                                        {
                                                          "NM1": "Individual or Organizational Name",
                                                          "NM1_01_description": "Entity Identifier Code",
                                                          "NM1_01": "82",
                                                          "NM1_01_code_82": "Rendering Provider",
                                                          "NM1_02_description": "Entity Type Qualifier",
                                                          "NM1_02": "1",
                                                          "NM1_02_code_1": "Person",
                                                          "NM1_03_description": "Name Last or Organization Name",
                                                          "NM1_03": "ADAMS",
                                                          "NM1_04_description": "Name First",
                                                          "NM1_04": "ROBERT",
                                                          "NM1_08_description": "Identification Code Qualifier",
                                                          "NM1_08": "XX",
                                                          "NM1_08_code_XX": "Centers for Medicare and Medicaid Services National Provider Identifier",
                                                          "NM1_09_description": "Identification Code",
                                                          "NM1_09": "2222222222"
                                                        }
                                                      ]
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
                                                            "SV1_01_02": "99214"
                                                          },
                                                          "SV1_02_description": "Monetary Amount",
                                                          "SV1_02": "150",
                                                          "SV1_03_description": "Unit or Basis for Measurement Code",
                                                          "SV1_03": "UN",
                                                          "SV1_03_code_UN": "Unit",
                                                          "SV1_04_description": "Quantity",
                                                          "SV1_04": "1",
                                                          "SV1_07_description": "Diagnosis Code Pointer",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
                                                        },
                                                        {
                                                          "SV1": "Professional Service",
                                                          "SV1_01_description": "Composite Medical Procedure Identifier",
                                                          "SV1_01": {
                                                            "SV1_01_01_description": "Product/Service ID Qualifier",
                                                            "SV1_01_01": "HC",
                                                            "SV1_01_01_code_HC": "Healthcare Common Procedure Coding System (HCPCS) Codes",
                                                            "SV1_01_02_description": "Product/Service ID",
                                                            "SV1_01_02": "80053"
                                                          },
                                                          "SV1_02_description": "Monetary Amount",
                                                          "SV1_02": "165",
                                                          "SV1_03_description": "Unit or Basis for Measurement Code",
                                                          "SV1_03": "UN",
                                                          "SV1_03_code_UN": "Unit",
                                                          "SV1_04_description": "Quantity",
                                                          "SV1_04": "1",
                                                          "SV1_07_description": "Diagnosis Code Pointer",
                                                          "SV1_07": {
                                                            "SV1_07_01": "1"
                                                          }
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
                                                          "DTP_03": "20260512"
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
                        }""",
                fileToString("output.edi"));
    }

    @Test
    void ediToJson_FormatNo() throws IOException {
        EdiToJsonCli.main(new String[]{"837.edi", "output.edi", "--format=no"});
        assertEquals("""
                        {"interchanges": [{"ISA_01_AuthorizationQualifier": "00","ISA_02_AuthorizationInformation": "          ","ISA_03_SecurityQualifier": "00","ISA_04_SecurityInformation": "          ","ISA_05_SenderQualifier": "ZZ","ISA_06_SenderId": "SENDERID       ","ISA_07_ReceiverQualifier": "ZZ","ISA_08_ReceiverId": "RECEIVERID     ","ISA_09_Date": "260513","ISA_10_Time": "0715","ISA_11_RepetitionSeparator": "^","ISA_12_Version": "00501","ISA_13_InterchangeControlNumber": "000000001","ISA_14_AcknowledgmentRequested": "0","ISA_15_TestIndicator": "T","functional_groups": [{"GS_01_FunctionalIdentifierCode": "HC","GS_02_ApplicationSenderCode": "SENDERID","GS_03_ApplicationReceiverCode": "RECEIVERID","GS_04_Date": "20260513","GS_05_Time": "0715","GS_06_GroupControlNumber": "1","GS_07_ResponsibleAgencyCode": "X","GS_08_Version": "005010X222A1","transactions": [{"ST_01_TransactionSetIdentifierCode": "837","ST_02_TransactionSetControlNumber": "0001","ST_03_ImplementationConventionReference": "005010X222A1","segments": [{"BHT_01": "0019","BHT_02": "00","BHT_03": "BATCH1001","BHT_04": "20260513","BHT_05": "0715","BHT_06": "CH"},{"NM1-1000A_loop": [{"NM1_01": "41","NM1_02": "2","NM1_03": "SAMPLE BILLING SERVICE","NM1_08": "46","NM1_09": "123456789"},{"PER_01": "IC","PER_02": "EDI SUPPORT","PER_03": "TE","PER_04": "8005551212"}]},{"NM1-1000B_loop": [{"NM1_01": "40","NM1_02": "2","NM1_03": "SAMPLE PAYER","NM1_08": "46","NM1_09": "999999999"}]},{"HL-2000A_loop": [{"HL_01": "1","HL_03": "20","HL_04": "1"},{"NM1-2010AA_loop": [{"NM1_01": "85","NM1_02": "2","NM1_03": "SAMPLE CLINIC","NM1_08": "XX","NM1_09": "1234567893"},{"N3_01": "100 MAIN STREET"},{"N4_01": "NASHVILLE","N4_02": "TN","N4_03": "37201"},{"REF_01": "EI","REF_02": "123456789"}]},{"HL-2000B_loop": [{"HL_01": "2","HL_02": "1","HL_03": "22","HL_04": "0"},{"SBR_01": "P","SBR_02": "18","SBR_09": "CI"},{"NM1-2010BA_loop": [{"NM1_01": "IL","NM1_02": "1","NM1_03": "DOE","NM1_04": "JOHN","NM1_08": "MI","NM1_09": "ABC12345"},{"N3_01": "200 OAK AVENUE"},{"N4_01": "NASHVILLE","N4_02": "TN","N4_03": "37202"},{"DMG_01": "D8","DMG_02": "19800101","DMG_03": "M"}]},{"CLM-2300_loop": [{"CLM_01": "CLAIM0001","CLM_02": "125.00","CLM_05": {"CLM_05_01": "11","CLM_05_02": "B","CLM_05_03": "1"},"CLM_06": "Y","CLM_07": "A","CLM_08": "Y","CLM_09": "I"},{"HI_01": {"HI_01_01": "ABK","HI_01_02": "J1099"}},{"NM1-2310B_loop": [{"NM1_01": "82","NM1_02": "1","NM1_03": "SMITH","NM1_04": "JANE","NM1_08": "XX","NM1_09": "1111111111"}]},{"LX-2400_loop": [{"LX_01": "1"},{"SV1_01": {"SV1_01_01": "HC","SV1_01_02": "99213"},"SV1_02": "75","SV1_03": "UN","SV1_04": "1","SV1_07": {"SV1_07_01": "1"}},{"DTP_01": "472","DTP_02": "D8","DTP_03": "20260510"}]}]},{"CLM-2300_loop": [{"CLM_01": "CLAIM0002","CLM_02": "240.00","CLM_05": {"CLM_05_01": "11","CLM_05_02": "B","CLM_05_03": "1"},"CLM_06": "Y","CLM_07": "A","CLM_08": "Y","CLM_09": "I"},{"HI_01": {"HI_01_01": "ABK","HI_01_02": "M545"}},{"NM1-2310B_loop": [{"NM1_01": "82","NM1_02": "1","NM1_03": "SMITH","NM1_04": "JANE","NM1_08": "XX","NM1_09": "1111111111"}]},{"LX-2400_loop": [{"LX_01": "1"},{"SV1_01": {"SV1_01_01": "HC","SV1_01_02": "93000"},"SV1_02": "240","SV1_03": "UN","SV1_04": "1","SV1_07": {"SV1_07_01": "1"}},{"DTP_01": "472","DTP_02": "D8","DTP_03": "20260511"}]}]}]}]}]},{"ST_01_TransactionSetIdentifierCode": "837","ST_02_TransactionSetControlNumber": "0002","ST_03_ImplementationConventionReference": "005010X222A1","segments": [{"BHT_01": "0019","BHT_02": "00","BHT_03": "BATCH1002","BHT_04": "20260513","BHT_05": "0720","BHT_06": "CH"},{"NM1-1000A_loop": [{"NM1_01": "41","NM1_02": "2","NM1_03": "SAMPLE BILLING SERVICE","NM1_08": "46","NM1_09": "123456789"},{"PER_01": "IC","PER_02": "EDI SUPPORT","PER_03": "TE","PER_04": "8005551212"}]},{"NM1-1000B_loop": [{"NM1_01": "40","NM1_02": "2","NM1_03": "SAMPLE PAYER","NM1_08": "46","NM1_09": "999999999"}]},{"HL-2000A_loop": [{"HL_01": "1","HL_03": "20","HL_04": "1"},{"NM1-2010AA_loop": [{"NM1_01": "85","NM1_02": "2","NM1_03": "SAMPLE CLINIC","NM1_08": "XX","NM1_09": "1234567893"},{"N3_01": "100 MAIN STREET"},{"N4_01": "NASHVILLE","N4_02": "TN","N4_03": "37201"},{"REF_01": "EI","REF_02": "123456789"}]},{"HL-2000B_loop": [{"HL_01": "2","HL_02": "1","HL_03": "22","HL_04": "0"},{"SBR_01": "P","SBR_02": "18","SBR_09": "CI"},{"NM1-2010BA_loop": [{"NM1_01": "IL","NM1_02": "1","NM1_03": "DOE","NM1_04": "JANE","NM1_08": "MI","NM1_09": "XYZ67890"},{"N3_01": "500 MAPLE DRIVE"},{"N4_01": "FRANKLIN","N4_02": "TN","N4_03": "37064"},{"DMG_01": "D8","DMG_02": "19751212","DMG_03": "F"}]},{"CLM-2300_loop": [{"CLM_01": "CLAIM0003","CLM_02": "88.00","CLM_05": {"CLM_05_01": "11","CLM_05_02": "B","CLM_05_03": "1"},"CLM_06": "Y","CLM_07": "A","CLM_08": "Y","CLM_09": "I"},{"HI_01": {"HI_01_01": "ABK","HI_01_02": "R101"}},{"NM1-2310B_loop": [{"NM1_01": "82","NM1_02": "1","NM1_03": "ADAMS","NM1_04": "ROBERT","NM1_08": "XX","NM1_09": "2222222222"}]},{"LX-2400_loop": [{"LX_01": "1"},{"SV1_01": {"SV1_01_01": "HC","SV1_01_02": "87070"},"SV1_02": "88","SV1_03": "UN","SV1_04": "1","SV1_07": {"SV1_07_01": "1"}},{"DTP_01": "472","DTP_02": "D8","DTP_03": "20260512"}]}]},{"CLM-2300_loop": [{"CLM_01": "CLAIM0004","CLM_02": "315.00","CLM_05": {"CLM_05_01": "11","CLM_05_02": "B","CLM_05_03": "1"},"CLM_06": "Y","CLM_07": "A","CLM_08": "Y","CLM_09": "I"},{"HI_01": {"HI_01_01": "ABK","HI_01_02": "E119"}},{"NM1-2310B_loop": [{"NM1_01": "82","NM1_02": "1","NM1_03": "ADAMS","NM1_04": "ROBERT","NM1_08": "XX","NM1_09": "2222222222"}]},{"LX-2400_loop": [{"LX_01": "1"},{"SV1_01": {"SV1_01_01": "HC","SV1_01_02": "99214"},"SV1_02": "150","SV1_03": "UN","SV1_04": "1","SV1_07": {"SV1_07_01": "1"}},{"SV1_01": {"SV1_01_01": "HC","SV1_01_02": "80053"},"SV1_02": "165","SV1_03": "UN","SV1_04": "1","SV1_07": {"SV1_07_01": "1"}},{"DTP_01": "472","DTP_02": "D8","DTP_03": "20260512"}]}]}]}]}]}]}]}]}""",
                fileToString("output.edi"));
    }

    @Test
    void ediToJson_recoverNo() throws IOException {
        try {
            EdiToJsonCli.main(new String[]{"837-errors.edi", "output.edi", "--recover=no"});
        } catch (RuntimeException e) {
            assertEquals("?", e.getMessage());
        }
    }

    @Test
    void help() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured));
            EdiToJsonCli.main(new String[]{"help"});
            assertEquals("""
                    
                    Usage Summary
                    =============
                    
                    Read EDI from an input file, write JSON to an output file
                      java -jar <jarFileName>  <ediInputFile>  <jsonOutputFile>  <options>
                    
                    Read EDI from an input file, write JSON to stdout
                      java -jar <jarFileName>  <ediInputFile>  <options>
                    
                    Read EDI from stdin, write JSON to stdout
                      java -jar <jarFileName>  <options>
                    
                    Display this usage summary
                      java -jar <jarFileName>  help
                    
                    
                    options
                       --annotate={yes|no} : if yes, include descriptive "annotations" (default is no)
                       --format={yes|no} : if yes, format JSON output (default is yes)
                       --recover={yes|no} : if yes, ignore any recoverable EDI errors (default is no)
                    
                    
                    """, captured.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
