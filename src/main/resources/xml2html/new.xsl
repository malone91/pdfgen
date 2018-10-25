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
                        /* 设置页眉 */
                        @top-left {
                            content: element(left-header);
                        }
                        @top-right {
                            content: element(right-header);
                        }
                        /* 设置页脚 */
                        @bottom-center {
                            content: counter(page) "/" counter(pages);
                        }
                    }
                    *{margin:0;padding:0;}
                    body {
                        font-family:'SimSun';font-size:10.5pt;
                        font-weight: bold;
                    }

                    div.left-header {
                        width:360px;
                        display: block;
                        position: running(left-header);
                        border-bottom: 1px solid black;
                        height: 20px;
                    }
                    div.right-header {
                        width: 360px;
                        display: block;
                        position: running(right-header);
                        border-bottom: 1px solid black;
                        height: 20px;
                    }

                    .level0 {
                        margin-left: 0cm;
                        word-break:keep-all;           /* 不换行 */
                        white-space:nowrap;          /* 不换行 */
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
                        margin-left: 0cm;
                    }

                    tr {
                        vertical-align: top;
                    }
                </style>
            </head>
            <body>
                <body>
                    <!-- 页眉设置 -->
                    <div class="left-header"><p style="margin:-3px 0px;font-weight: normal;"><xsl:value-of select="document/header-left"></xsl:value-of></p></div>
                    <div class="right-header"><p style="margin:-3px 300px;font-weight: normal;"><xsl:value-of select="document/header-right"></xsl:value-of></p></div>
                    <br></br>
                    <br></br>
                    <br></br>

                    <!-- 合同标题 -->
                    <h1 class="text-center"><xsl:value-of select="document/title"></xsl:value-of></h1>
                    <br></br>
                    <br></br>
                    <h4 style="text-align:right;"><xsl:value-of select="document/contract_id"></xsl:value-of><span style="border-bottom:1px solid #000;padding:0 50px;">$!{<xsl:value-of select="document/contract_id/@id"></xsl:value-of>}</span></h4>
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
                        <table>
                            <xsl:for-each select="document/terms/term0">
                                <br></br>
                                <tr>
                                    <td>
                                        <div class="level0">
                                            <xsl:value-of select="term_title"/>
                                        </div>
                                    </td>
                                </tr>
                                <br></br>
                                <xsl:for-each select="term1">
                                    <tr>
                                        <td>
                                            <div class="level1">
                                                <xsl:value-of select="term1_num"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="level2">
                                                <!--<xsl:value-of select="term1_body">-->

                                                <!--</xsl:value-of>-->
                                                <xsl:for-each select="term1_body">
                                                    <!-- term_body的内容 -->
                                                    <xsl:value-of select="text()"></xsl:value-of>
                                                    <!-- 遍历简单的填空内容-->
                                                    <xsl:for-each select="input">
                                                       <xsl:value-of select="text()"></xsl:value-of>
                                                       <!-- input内的值不为空则不显示占位符-->
                                                       <xsl:if test="@id != '' ">
                                                           <span style="border-bottom:1px solid #000;padding:0 width:30px;">
                                                               $!{<xsl:value-of select="@id"></xsl:value-of>}
                                                           </span>
                                                       </xsl:if>
                                                   </xsl:for-each>
                                                    <!-- 遍历嵌套的二级内容 -->
                                                    <xsl:for-each select="list">
                                                        <!--<xsl:if test="@id != '' ">-->
                                                            <span style="border-bottom:1px solid #000;padding:0 width:30px;">
                                                                $!{<xsl:value-of select="@id"></xsl:value-of>}
                                                            </span>
                                                        <!--</xsl:if>-->
                                                        <xsl:value-of select="text()"></xsl:value-of>
                                                        <!-- 三级缩进 -->
                                                        <div class="level3">
                                                            <xsl:for-each select="term2">
                                                                <br></br>
                                                                <xsl:value-of select="term2_num"></xsl:value-of>
                                                                <xsl:for-each select="term2_body">
                                                                    <xsl:value-of select="text()"></xsl:value-of>
                                                                    <xsl:for-each select="input">
                                                                        <!-- 根据第一个内容是否为空判断先遍历哪个 -->
                                                                        <xsl:value-of select="text()"></xsl:value-of>
                                                                        <!-- input内的值不为空则不显示占位符-->
                                                                        <xsl:if test="@type = 'number' ">
                                                                            <span style="border-bottom:1px solid #000;padding:0 width:70px;">
                                                                                $!{<xsl:value-of select="@id"></xsl:value-of>}
                                                                            </span>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </div>
                                                    </xsl:for-each>

                                                    <!-- term_body下遍历term1 含有序号的 -->
                                                    <xsl:for-each select="term1">
                                                        <br></br>
                                                        <div class="level4">
                                                            <xsl:value-of select="term1_num"></xsl:value-of>
                                                            <xsl:for-each select="term1_body">
                                                                <xsl:value-of select="text()"></xsl:value-of>
                                                                <xsl:for-each select="term2">
                                                                    <br></br>
                                                                    <div class="level5">
                                                                        <xsl:value-of select="term2_num"></xsl:value-of>
                                                                        <xsl:value-of select="term2_body"></xsl:value-of>
                                                                    </div>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </div>
                                                    </xsl:for-each>

                                                    <!-- term_body下有input -->
                                                    <xsl:for-each select="input">
                                                        <xsl:value-of select="text()"></xsl:value-of>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </div>
                                        </td>
                                    </tr>
                                </xsl:for-each>
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
                                <td colspan="3"><xsl:value-of select="document/sign/first-signature"/></td>
                                <td style="width: 230px;">代表人：</td>
                                <td colspan="3"><xsl:value-of select="document/sign/second-signature"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- 日期单独一个table表示 -->
                    <table>
                        <tr>
                            <td style="width:7%"><xsl:value-of select="document/sign/first-year"/></td><td>年</td>
                            <td style="width:5%"><xsl:value-of select="document/sign/first-month"/></td><td>月</td>
                            <td style="width:5%"><xsl:value-of select="document/sign/first-day"/></td><td>日</td>

                            <td style="width:30%"></td>

                            <td style="width:7%"><xsl:value-of select="document/sign/second-year"/></td><td>年</td>
                            <td style="width:5%"><xsl:value-of select="document/sign/second-month"/></td><td>月</td>
                            <td style="width:5%"><xsl:value-of select="document/sign/second-day"/></td><td>日</td>

                            <td style="width:30%"></td>
                        </tr>
                    </table>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>