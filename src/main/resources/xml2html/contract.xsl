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
        <!-- 设置顶线 -->
        border-top: 1px solid black;
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
        }/*宋五*/
        li{list-style:none;}
        h1{font-size:15.75pt;}/*黑三*/
        h2{font-size:12pt;}/*宋小四*/
        p{line-height:2;}
        .clearfix:after{clear:both;content:'';display:block;}
        .text-center{text-align:center;}
        .table td{border:0.75pt solid #000;padding:0 5.03pt;font-weight:normal;line-height:20.8pt;word-break: break-word;}
        .table th{background-color:#fff;line-height:20.8pt;}
        td.bor,span.bor{border-bottom:1px solid #000;padding:0 10px;}
        .apply-type li{width:50%;}
        .apply-type span{display:inline-block;width:14px;height:14px;vertical-align:text-bottom;background:url(/data/App/jgoms.cfaoe.local/purple.png) no-repeat;margin-right:5px;}
        .apply-type .checked span{background-position:0 -14px;}

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
        margin-left: 0.5cm;
        word-break:keep-all;           /* 不换行 */
        white-space:nowrap;          /* 不换行 */

        }
        /* from huwei */
        .level1 {
        margin-left: 1cm;
        word-break:keep-all;           /* 不换行 */
        white-space:nowrap;          /* 不换行 */

        }

        .level2 {
        margin-left: 1.3cm;
        }

        tr {
        vertical-align: top;
        }
    </style>
</head>
    <body>
        <!-- 页眉设置 -->
        <div class="left-header"><p style="margin:-3px 0px;"><xsl:value-of select="document/left-head"></xsl:value-of></p></div>
        <div class="right-header"><p style=""><xsl:value-of select="document/left-head"></xsl:value-of></p></div>
        <!-- 合同标题 -->
        <div>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
        </div>
        <h1 class="text-center"><xsl:value-of select="document/title"></xsl:value-of></h1>
        <!-- 合同信息 -->
        <div style="padding:20px;" class="clearfix">
            <table style="width:100%;table-layout:fixed;">
                <tbody>
                    <tr>
                        <td colspan="4" align="right"><h4>合同编号：</h4></td>
                        <td align="center" colspan="2" class="bor">
                            <xsl:value-of select="document/contract-no"></xsl:value-of>
                        </td>
                    </tr>

                    <tr><td><br></br></td></tr>
                    <tr><td><br></br></td></tr>

                    <tr>
                        <td style="width: 100px;">甲方（承销商）：</td>
                        <td align="center" colspan="4" class="bor">
                            <xsl:value-of select="document/info/first-party"></xsl:value-of>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100px;">注册地址：</td>
                        <td align="center" colspan="4" class="bor">
                            <xsl:value-of select="document/document/info/first-address"></xsl:value-of>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100px;">法定代表人：</td>
                        <td align="center" colspan="4" class="bor">
                            <xsl:value-of select="document/info/first-corporate"></xsl:value-of>
                        </td>
                    </tr>

                    <tr>
                        <td style="width: 100px;">乙方（需方）：</td>
                        <td align="center" colspan="4" class="bor"><p>
                            <xsl:value-of select="document/info/second-party"></xsl:value-of>
                        </p>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100px;">注册地址：</td>
                        <td align="center" colspan="4" class="bor">
                            <xsl:value-of select="document/info/second-address"></xsl:value-of>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100px;">法定代表人：</td>
                        <td align="center" colspan="4" class="bor">
                            <xsl:value-of select="document/info/second-corporate"></xsl:value-of>
                        </td>
                    </tr>

                    <tr><td><br></br></td></tr>
                    <tr><td><br></br></td></tr>
                </tbody>
            </table>
        </div>

            <!-- 合同条款 -->
            <table>
                <xsl:for-each select="document/itemlist/segment">
                    <!-- 合同条款 -->
                        <tr>
                            <td>
                                <div class="level0">
                                    <xsl:value-of select="title"/>
                                </div>
                            </td>
                        </tr>
                    <xsl:for-each select="clause/item">
                        <tr>
                            <td>
                                <div class="level1">
                                    <xsl:value-of select="index"/>
                                </div>
                            </td>
                            <td>
                                <div class="level2">
                                    <xsl:value-of select="content"/>
                                </div>
                            </td>
                        </tr>
                    </xsl:for-each>
                </xsl:for-each>
            </table>

        <!-- 合同落款，比如时间，签字人 -->
        <div class="clearfix">
            <table style="width:100%;table-layout:fixed;">
                <tbody>
                    <tr><td><br></br></td></tr>
                    <tr>
                        <td style="width: 230px;">甲方（盖章）：</td>
                        <td colspan="3">
                        </td>
                        <td style="width: 230px;">乙方（盖章）：</td>
                        <td colspan="3"></td>
                    </tr>

                    <!-- 换行 -->
                    <tr><td><br></br></td></tr>
                    <tr><td><br></br></td></tr>
                    <tr><td><br></br></td></tr>
                    <tr><td><br></br></td></tr>

                    <tr>
                        <td style="width: 230px;">法定代表人或授权代表（签字）：</td>
                        <td colspan="3"><xsl:value-of select="document/signature/first-signature"/></td>
                        <td style="width: 230px;">法定代表人或授权代表（签字）：</td>
                        <td colspan="3"><xsl:value-of select="document/signature/second-signature"/></td>
                    </tr>
                </tbody>
            </table>
            <!-- 日期单独一个table表示 -->
            <table>
                <tr>
                    <td style="width:10%"><xsl:value-of select="document/signature/first-year"/></td><td>年</td>
                    <td style="width:5%"><xsl:value-of select="document/signature/first-month"/></td><td>月</td>
                    <td style="width:5%"><xsl:value-of select="document/signature/first-day"/></td><td>日</td>

                    <td style="width:30%"></td>

                    <td style="width:10%"><xsl:value-of select="document/signature/second-year"/></td><td>年</td>
                    <td style="width:5%"><xsl:value-of select="document/signature/second-month"/></td><td>月</td>
                    <td style="width:5%"><xsl:value-of select="document/signature/second-day"/></td><td>日</td>

                    <td style="width:30%"></td>
                </tr>
            </table>

        </div>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>