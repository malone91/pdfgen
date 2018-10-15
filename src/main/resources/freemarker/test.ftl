<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <title>合同书</title>
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
                content: counter(page);
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
        .pull-right{float:right;}
        .pull-left{float:left;}
        .clearfix:after{clear:both;content:'';display:block;}
        .text-center{text-align:center;}
        .text-right{text-align:right;}
        .text-indent{text-indent:2em;}
        .page-mark{page-break-after:always;}
        .table{border-collapse:collapse; margin:0 auto; width:520pt;width:100%;table-layout:fixed;}
        .table td{border:0.75pt solid #000;padding:0 5.03pt;font-weight:normal;line-height:20.8pt;word-break: break-word;}
        .table th{background-color:#fff;line-height:20.8pt;}
        td.bor,span.bor{border-bottom:1px solid #000;padding:0 10px;}
        .seal-leaguer{position:absolute;right:50px;top:-45px;}
        .apply-type li{width:50%;}
        .apply-type span{display:inline-block;width:14px;height:14px;vertical-align:text-bottom;background:url(/data/App/jgoms.cfaoe.local/purple.png) no-repeat;margin-right:5px;}
        .apply-type .checked span{background-position:0 -14px;}

        div.right-header {
            display: block;
            position: running(right-header);
            border-bottom: 1px solid black;
        }
        div.left-header {
            display: block;
            position: running(left-header);
            border-bottom: 1px solid black;
        }

        /* from huwei */
        .level1 {
            margin-left: 1cm;
            float: left;
        }

        .level2 {
            margin-left: 3cm;
            float: right;
        }
        .input-text {
            width: 200px;
            border-bottom:1px solid;
        }
    </style>

</head>
<body>

<div class="left-header">$!{left-head}</div>
<div class="right-header">$!{right-head}</div>

<p>电子合同版本2018.9</p>
<div class="level1">通用名称</div>
<div class="level2">电子合同</div>
<div class="level2">电子合同，我是一个很长很长的条款，看能不能合适的进行换行。由于目前还在摸索阶段，任何形式的输出都是可能的。</div>
<div class="level1">退货条款</div>
<div class="level2">电子合同，我是一个很长很长的条款，<span class="input-text">__________</span> 看看能不能合适的进行换行。由于目前还在摸索阶段，任何形式的输出都是可能的。</div>

<div style="padding:20px;" class="clearfix">
    <h1 class="text-center">合同单子</h1><br /><br />
    <table style="width:100%;table-layout:fixed;">
        <col style="width:15%" />
        <col style="width:15%" />
        <col style="width:20%" />
        <col style="width:15%" />
        <col style="width:15%" />
        <col style="width:20%" />
        <tbody>
        <tr>
            <td colspan="2">申请机构（主承销商）：</td>
            <td align="center" colspan="4" class="bor"><p>$!{companyName}</p>
            </td>
        </tr>
        <tr>
            <td colspan="6"><br/></td>
        </tr>
        <tr>
            <td>会员编号：</td>
            <td align="center" colspan="2" class="bor">$!userCode</td>
            <td>申请书编号：</td>
            <td align="center" colspan="2" class="bor">$!projectCode</td>
        </tr>
        </tbody>
    </table>
</div>

<p>原则条款</p>
<div class="level1">第1条</div>
<div class="level2">本合同书经甲乙双方通过友好协商，本着优势互补、互惠互利、共同发展的原则，依据《中华人民共和国合同法》、《中华人民共和国产品质量法》、《中华人民共和国消费者权益保护法》、《零售商供应商公平交易管理办法》等法律、法规的规定签订。
</div>
<div class="level1">第2条</div>
<div class="level2">
    甲方授权乙方作为其生产或经销的商品的代销商；乙方同意有选择地销售甲方提供的适销对路的商品。由甲方提供商品，乙方负责营造有益于销售的氛围，为甲方提供有益于销售商品的平台；甲方负责提供适销对路，且质优价廉的商品，并适时给予促销资源的保障。
</div>


</body>
</html>