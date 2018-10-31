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
                        <!-- 1.5倍行距 -->
                        line-height: 15.75pt;
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
                    <!-- 合同标题 -->
                    <div class="text-center"><span style="border-bottom:1px solid #000;padding:0 105px;"></span><xsl:value-of select="document/title"></xsl:value-of></div>
                    <br></br>
                    <br></br>
                    <br></br>
                    <xsl:for-each select="document/agg_id">
                        <h4 style="text-align:right;"><xsl:value-of select="text()"></xsl:value-of>
                            <xsl:if test="@id = 'contract_id' ">
                                <span style="border-bottom:1px solid #000;padding:0 45px;">$!{<xsl:value-of select="@id"></xsl:value-of>}</span>
                            </xsl:if>
                            <xsl:if test="@id = 'agg_id' ">
                                <span style="border-bottom:1px solid #000;padding:0 45px;">$!{<xsl:value-of select="@id"></xsl:value-of>}</span>
                            </xsl:if>
                        </h4>
                    </xsl:for-each>
                    <br></br>
                    <br></br>
                    <div align="center">
                        <xsl:for-each select="document/parties/party">
                            <h4>
                                <span style="display:inline-block; width:120px">
                                <xsl:value-of select="text()"></xsl:value-of>
                                </span>
                                    <span style="border-bottom:1px solid #000;padding:0 200px;">$!{<xsl:value-of select="@id"></xsl:value-of>}
                                </span>
                            </h4>
                            <br></br>
                        </xsl:for-each>
                    </div>
                    <br></br>
                    <br></br>

                    <!-- 合同条款 -->
                    <div>
                        <!-- 开头 -->
                        <xsl:for-each select="document/terms/agg_term0">
                            <!-- 空格 -->
                            <span style="border-bottom:0px solid #000;padding:0 17px;"></span>
                            <xsl:value-of select="text()"></xsl:value-of>
                            <xsl:for-each select="date">
                                <span style="border-bottom:1px solid #000;padding:0 40px;">$!{<xsl:value-of select="@id"></xsl:value-of>}</span>
                                <xsl:value-of select="following-sibling::text()[1]"/>
                            </xsl:for-each>
                            <xsl:for-each select="input">
                                <span style="border-bottom:1px solid #000;padding:0 60px;">$!{<xsl:value-of select="@id"></xsl:value-of>}</span>
                                <xsl:value-of select="following-sibling::text()[1]"/>
                            </xsl:for-each>
                        </xsl:for-each>
                        <!-- 条款 -->
                            <table>
                                <xsl:for-each select="document/terms/agg_term1">
                                    <tr>
                                        <td>
                                            <div class="level1 item_index">
                                                <xsl:value-of select="agg_term1_num"></xsl:value-of>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="level2">
                                                <xsl:for-each select="agg_term1_body">
                                                    <!-- 条款正文 -->
                                                    <xsl:value-of select="text()"></xsl:value-of>
                                                    <!-- table框 -->
                                                    <table border="1px" cellspacing="0" cellpadding="0">
                                                        <xsl:for-each select="table/tr">
                                                            <tr>
                                                                <xsl:for-each select="th">
                                                                    <th style="width:8.6%">
                                                                        <xsl:value-of select="text()"></xsl:value-of>
                                                                    </th>
                                                                </xsl:for-each>
                                                                <xsl:for-each select="td">
                                                                    <th style="width:8.6%">
                                                                        <xsl:value-of select="text()"></xsl:value-of>
                                                                    </th>
                                                                </xsl:for-each>

                                                            </tr>
                                                        </xsl:for-each>
                                                    </table>
                                                    <!-- input框 -->
                                                    <xsl:for-each select="input">
                                                        <!-- input内的值不为空则不显示占位符-->
                                                        <xsl:if test="@id != '' ">
                                                            <span style="border-bottom:1px solid #000;padding:0 30px;">
                                                                $!{<xsl:value-of select="@id"></xsl:value-of>}
                                                            </span>
                                                        </xsl:if>
                                                        <!-- 寻找input后边的文字内容 -->
                                                        <xsl:value-of select="following-sibling::text()[1]"/>
                                                    </xsl:for-each>
                                                    <!-- date框 -->
                                                    <xsl:for-each select="date">
                                                        <!-- input内的值不为空则不显示占位符-->
                                                        <xsl:if test="@id != '' ">
                                                            <span style="border-bottom:1px solid #000;padding:0 30px;">
                                                                $!{<xsl:value-of select="@id"></xsl:value-of>}
                                                            </span>
                                                        </xsl:if>
                                                        <!-- 寻找input后边的文字内容 -->
                                                        <xsl:value-of select="following-sibling::text()[1]"/>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </div>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
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