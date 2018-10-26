<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">

        <html>
            <head>
                <style type="text/css">
                    @page {
                        font-family: SimSun;
                        /* it is just A4 size */
                        size: 8.5in 11in;
                        /* 设置页脚 */
                        @bottom-center {
                            content: counter(page) "/" counter(pages);
                        }
                    }
                    *{margin:0;padding:0;}
                    body {
                        font-family:'SimSun';font-size:10.5pt;
                    }

                    .text-center {
                        text-align: center;
                        font-size: 15pt;
                    }

                    h1,h4 {
                       font-weight: normal;
                    }

                    .level0 {
                        width: 300px;
                        margin-left: 0cm;
                        word-break:keep-all;           /* 不换行 */
                    }

                    .level1 {
                        margin-left: 1.7cm;
                        word-break:keep-all;           /* 不换行 */
                        white-space:nowrap;          /* 不换行 */
                    }

                    .level2 {
                        margin-left: 0cm;
                    }

                    .level3 {
                        margin-left: 0cm;
                    }

                    .level4 {
                        margin-left: 0.3cm;
                    }

                    .level5 {
                        margin-left: 25px
                    }

                    tr {
                        vertical-align: top;
                    }

                    .item_index {
                        width: 50px;
                    }
                </style>
            </head>
            <body>
                <body>
                    <br></br>
                    <br></br>
                    <br></br>

                    <!-- 合同标题 -->
                    <div class="text-center" ><span style="border-bottom:1px solid #000;padding:0 105px;"></span><xsl:value-of select="document/title"></xsl:value-of></div>
                    <br></br>
                    <br></br>
                    <xsl:for-each select="document/agg_id">
                        <h4 style="text-align:right;"><xsl:value-of select="text()"></xsl:value-of><span style="border-bottom:1px solid #000;padding:0 50px;">$!{<xsl:value-of select="@contract_id"></xsl:value-of>}</span></h4>
                    </xsl:for-each>
                    <br></br>
                    <br></br>
                    <div>
                        <xsl:for-each select="document/parties/party">
                            <h4><span style="display:inline-block; width:200px"><xsl:value-of select="text()"></xsl:value-of></span><span style="border-bottom:1px solid #000;padding:0 150px;">$!{<xsl:value-of select="@id"></xsl:value-of>}</span></h4>
                            <br></br>
                        </xsl:for-each>
                    </div>
                    <br></br>
                    <br></br>

                    <!-- 合同条款 -->
                    <div>
                    </div>
                </body>

                <!-- sign place -->
                <div>
                    <table style="width:100%;table-layout:fixed;">
                        <tbody>
                            <tr><td><br></br></td></tr>
                            <tr>
                                <td style="width: 230px;">甲方：</td>
                                <td colspan="3">
                                </td>
                                <td style="width: 230px;">乙方：</td>
                                <td colspan="3"></td>
                            </tr>

                            <!-- 换行 -->
                            <tr><td><br></br></td></tr>
                            <tr><td><br></br></td></tr>
                            <tr><td><br></br></td></tr>
                            <tr><td><br></br></td></tr>

                            <tr>
                                <td style="width: 230px;">代表人：</td>
                                <td colspan="3"><xsl:value-of select="document/sign/@party_a_legal_contact"></xsl:value-of></td>
                                <td style="width: 230px;">代表人：</td>
                                <td colspan="3"><xsl:value-of select="document/sign/@party_a_legal_contact"></xsl:value-of></td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- 日期单独一个table表示 -->
                    <table>
                        <tr>
                            <td style="width:20%"><xsl:value-of select="document/sign/@party_a_sign_date"></xsl:value-of></td><td></td>

                            <td style="width:30%"></td>

                            <td style="width:20%"><xsl:value-of select="document/sign/@party_b_sign_date"></xsl:value-of></td><td></td>

                            <td style="width:30%"></td>
                        </tr>
                    </table>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>