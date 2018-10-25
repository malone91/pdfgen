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
                    margin-left: 0cm;
                    word-break:keep-all;           /* 不换行 */
                    white-space:nowrap;          /* 不换行 */

                    }
                    /* from huwei */
                    .level1 {
                    margin-left: 1.7cm;
                    word-break:keep-all;           /* 不换行 */
                    white-space:nowrap;          /* 不换行 */

                    }

                    .level2 {
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
                    <div class="left-header"><p style="margin:-3px 0px;"><xsl:value-of select="document/header-left"></xsl:value-of></p></div>
                    <div class="right-header"><p style="margin:-3px 310px;"><xsl:value-of select="document/header-right"></xsl:value-of></p></div>
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
                            <xsl:for-each select="document/terms/term_segment">
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
                                                       <xsl:value-of select="text()"></xsl:value-of>
                                                   <xsl:for-each select="input">
                                                       <xsl:value-of select="text()"></xsl:value-of>
                                                       <xsl:value-of select="@width"></xsl:value-of>
                                                       <xsl:value-of select="@id"></xsl:value-of>
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

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>