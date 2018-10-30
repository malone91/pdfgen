package com.gome.pdfgen.util;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * XML依赖XML转为HTML文件，页眉页脚由工具类进行控制
 * @author malong-ds
 * @date 2018/10/16
 */
public final class Xml2HtmlStringUtil {

    /**
     * 获取xml和xsl生成的字符串
     * @return
     */
    public static String transferXml2Html(String xmlPath, String xslPath) {
        // 实例化 DocumentBuilderFactory 对象
        InputStream inputStream = null;
        InputStream stream = null;
        try {
            inputStream = Xml2HtmlStringUtil.class.getClassLoader().getResourceAsStream(xmlPath);
            Source source = new StreamSource(inputStream);
            //创建xsl输入流
            stream = Xml2HtmlStringUtil.class.getClassLoader().getResourceAsStream(xslPath);
            Source template = new StreamSource(stream);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(template);
            StringWriter sw = new StringWriter();
            Result resulted = new StreamResult(sw);
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.transform(source, resulted);
            return sw != null ? sw.toString() : null;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * xml字符串和xsl文件整合生成pdf
     * @param xslPath
     * @return
     */
    public static String transferXmlString2Html(String xslPath) {
        // 实例化 DocumentBuilderFactory 对象
        InputStream stream = null;
        try {
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<document>\n" +
                    "    <!-- 页眉 标题 编号 -->\n" +
                    "    <header-left>经销合同</header-left>\n" +
                    "    <header-right>国美电器</header-right>\n" +
                    "    <!-- 设置是否需要页脚 -->\n" +
                    "    <footer-display>true</footer-display>\n" +
                    "    <title>经销采购合同书</title>\n" +
                    "    <contract_id id=\"contract_id\">合同编号：</contract_id>\n" +
                    "\n" +
                    "    <!-- party -->\n" +
                    "    <parties>\n" +
                    "        <party id=\"party_a_name\">甲方（供方）：</party>\n" +
                    "        <party id=\"party_a_reg_addr\">注册地址：</party>\n" +
                    "        <party id=\"party_a_bus_addr\">经营地址：</party>\n" +
                    "        <party id=\"party_a_legal_contact\">法定代表人：</party>\n" +
                    "        <party id=\"party_b_name\">乙方（需方）：</party>\n" +
                    "        <party id=\"party_b_reg_addr\">注册地址：</party>\n" +
                    "        <party id=\"party_b_bus_addr\">经营地址：</party>\n" +
                    "        <party id=\"party_b_legal_contact\">法定代表人：</party>\n" +
                    "    </parties>\n" +
                    "\n" +
                    "    <!-- terms -->\n" +
                    "    <terms>\n" +
                    "        <term0>\n" +
                    "            <term_title>原则条款</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第1条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同书经甲乙双方通过友好协商，本着优势互补、互惠互利、共同发展的原则，依据《中华人民共和国合同法》、《中华人民共和国产品质量法》、《中华人民共和国消费者权益保护法》、《零售商供应商公平交易管理办法》等法律、法规的规定签订。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>适用范围</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第2条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同的甲方代表甲方及其所属的子公司、分公司、分支机构、办事处及甲方指定的代理商。乙方代表乙方及其所属的子公司、分公司、分支机构、关联公司（甲乙双方代表公司列表详见附件一）。甲乙双方分别代表的独立法人单位应向甲乙双方出具认可并履行本合同的确认函，该确认函为本合同附件，是本合同不可分割的一部分。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第3条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同约定的产品具体是指：\n" +
                    "                    <input type=\"text\" readonly=\"true\" width=\"30\" id=\"category\"></input>\n" +
                    "                    品类\n" +
                    "                    <input type=\"text\" readonly=\"true\" width=\"30\" id=\"brand\"></input>\n" +
                    "                    品牌的产品。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>名词定义</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第4条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    产品：指由甲方生产或经销的符合国家质量标准及双方约定的标准并不侵犯任何第三方知识产权和合法权益的合格产品。产品包括但不限于产品本身、附件、配件、促销礼品、赠品。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第5条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    智能产品：通过手机APP、语音声控、蓝牙、红外、网关类、WIFI、RF、感应器等方式并借助云平台进行远程控制的产品。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第6条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    供货价格：指甲方按照合同约定履行了供方应尽的各项基本义务的前提下向乙方提供产品的实际价格，该价格应低于甲方给予其他所有供货渠道的商品价格。甲方应尽的基本义务包括但不限于负责将产品运输至乙方指定地点（装卸均由甲方负责）、提供促销人员负责甲方产品的销售、提供专属甲方产品销售的展台展柜、按约定负责滞销产品的处理、按约定保证提供产品销售的账面毛利率等。甲方同意，如因甲方单方原因无法履行上述义务或履行不符合约定的，甲方同意按下述方式之一处理：（1）按照主合同约定或通过签订补充协议的方式约定由乙方为甲方提供上述服务，甲方支付相应的服务费用；或（2）甲方承担相应的违约责任，按约定向乙方支付损失赔偿金或违约金。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第7条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    残次品：指产品在售前、售中及售后过程中，本身固有的或发生的外观、性能、质量等任何一项不符合中国国家质量标准、行业标准、厂家标准、售后服务三包标准及合同约定标准等任一标准的产品。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第6条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    供货价格：指甲方按照合同约定履行了供方应尽的各项基本义务的前提下向乙方提供产品的实际价格，该价格应低于甲方给予其他所有供货渠道的商品价格。甲方应尽的基本义务包括但不限于负责将产品运输至乙方指定地点（装卸均由甲方负责）、提供促销人员负责甲方产品的销售、提供专属甲方产品销售的展台展柜、按约定负责滞销产品的处理、按约定保证提供产品销售的账面毛利率等。甲方同意，如因甲方单方原因无法履行上述义务或履行不符合约定的，甲方同意按下述方式之一处理：（1）按照主合同约定或通过签订补充协议的方式约定由乙方为甲方提供上述服务，甲方支付相应的服务费用；或（2）甲方承担相应的违约责任，按约定向乙方支付损失赔偿金或违约金。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第8条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    货到付款：指乙方在收到甲方提供的产品并进行验收入库向甲方出具入库单后5个工作日内向甲方支付货款。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第9条</term1_num>\n" +
                    "                <term1_body>合同年度：指合同有效期。</term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第10条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    账期：指甲方产品进入乙方指定仓库（以乙方入库单为准）到乙方支付货款之间的期间。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第11条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    降价款：根据经营需要甲方向下调整给予乙方的供货价格，由此产生的新老供货价格的差额。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第12条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    给乙方造成名誉损失的情况：是指给乙方或乙方相关公司造成名誉、商号、商标、商誉负面影响的情况，包括但不限于：1、因甲方产品问题包括但不限于质量、假冒伪劣、虚假宣传、侵犯他人知识产权等问题，售后服务问题、价格问题或其它甲方原因被媒体（媒体包括但不限于电视、广播、报纸、杂志、网络等）曝光的；2、因甲方产品问题、售后服务问题、价格问题被有关部门、机构、消协、质量监督部门等公告的；3、虽未被曝光，但因甲方产品问题或售后服务问题造成相当多人员认为或知悉乙方提供的产品或服务存在问题的；4、甲方产品质量出现批量问题，致使乙方名誉或商誉受到负面影响的； 5、甲方制作的展柜、展台、展架及吊楣造成第三人伤亡，致使乙方名誉或者商誉受到负面影响的；6、甲方及甲方工作人员对乙方进行言论诋毁及其它甲方给乙方或乙方相关公司造成名誉、商号、商标、商誉等负面影响的情况。甲方同意出现以上情况并造成乙方损失的，甲方全额赔偿乙方相关的产品、运输、管理成本及因此导致的其他开支及损失。甲方同意在乙方提出赔偿要求后30日内全额向乙方支付赔偿金额。如甲方逾期未支付，作为一种替代方式，甲方同意乙方在最近一次应支付给甲方的货款中扣除该赔偿金额。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第13条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    ECP平台：指Enterprise Cooperation Platform 系统，是双方指定网上供应链平台，网址为http://gomeb2b.gome.com.cn。对于合同中约定及实际业务中涉及的订单确认、发货、对账、结算、数据查询等业务，甲乙双方可以通过ECP平台来处理。甲方通过ECP平台进行上述操作的，应遵守ECP平台的操作规程。\n" +
                    "                </term1_body>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意：甲方通过其数字证书(含ECP平台用户账号【UKEY】等信息)进入ECP平台系统进行的所有操作，均为甲方自身行为，并视为甲方履行本合同的行为，对甲方具有法律约束力。甲方通过ECP平台自行进行内部权限的设置、管理和维护。甲方应妥善保管数字证书和密码，保证不将其泄露或提供给他人使用，如数字证书或密码遗失、被盗，甲方应及时办理挂失手续，挂失生效前发生的风险和损失由甲方承担。甲方的数字证书信息需变更的，应及时以书面形式告知乙方，否则，甲方承担由此产生的一切风险和损失，由此给乙方造成损失的，甲方应承担赔偿责任。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>价格管理</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第14条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意每月5日向乙方提供加盖有效印章的最新商品价格单（内容包括：供货价格、当地市场零售成交价等【含线上线下商品】）。如甲方产品供货价格调整，甲方同意提前3天通知乙方，并从调价日起对乙方调价型号的现有全部库存进行调价补差。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第14条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意每月5日向乙方提供加盖有效印章的最新商品价格单（内容包括：供货价格、当地市场零售成交价等【含线上线下商品】）。如甲方产品供货价格调整，甲方同意提前3天通知乙方，并从调价日起对乙方调价型号的现有全部库存进行调价补差。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第15条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意甲方每台产品在乙方销售的账面毛利率不低于\n" +
                    "                    <input type=\"number\" width=\"30\"  id=\"gross_profit\"></input>\n" +
                    "                        %，核算期间为当月一日至当月最后一日。如因甲方管理市场产品价格原因导致乙方销售甲方产品的实际毛利率低于双方约定，则视为甲方违约，甲方同意承担相应的违约责任，由甲方负责补足约定零售成交价与实际零售成交价之间的差额，在甲方最近的一次货款结算时予以扣除，甲方在增值税专用发票（本合同所述增值税专用发票特指税率为17%/16%的增值税专用发票，下同）上直接列明，不在价格中直接低开，乙方不再向甲方开具发票。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第16条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲乙双方合作生产OEM产品，甲方应保证OEM产品对乙方的供价不得高于乙方销售的同等质量、档次、规格的型号产品供价的\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" max=\"100\" id=\"gross_profit\"></input>\n" +
                    "                    %。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>产品订购</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第17条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方从甲方处进货时需提前向甲方提交经乙方确认的订单，甲方在接到订单后2日内答复乙方。甲方未在约定时间回复乙方则视为同意乙方订单。甲方对订单的任何修改均需得到乙方同意。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第18条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    订单达成一致后，甲方按乙方订单要求的送货时间送达乙方指定地点。对于乙方未提交订单甲方擅自送货的，乙方有权拒收。对于甲方未按照乙方提交的订单数量送货的，如果数量少于订单确认的数量（乙方不同意接收的残次商品除外），甲方应在2日内将订单差异补齐，否则视为甲方违约，由此给乙方造成的全部损失由甲方承担，同时甲方按照乙方订单额的\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" max=\"100\" id=\"po_comp\" ></input>\n" +
                    "                    %向乙方支付补偿金，乙方并有权要求甲方继续提供不足部分商品；数量多于订单确认的数量，乙方对于多出的部分有权拒收。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第19条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方在要求的送货时间前\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" id=\"po_change_days\" ></input>\n" +
                    "                    日有权撤销或变更订单，乙方撤销或变更订单的，应书面通知甲方，通知自发出之日起生效。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第20条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方应在本合同签订生效后一个月内全部进店完毕，并保证按合同约定应进入的门店展台、促销人员、样机齐全。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>产品运输、交货及验收</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第21条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方送货时，应至少于到货前一天通知乙方验货。乙方安排工作人员在到货后12小时内按照订单对产品的种类、规格、产地、数量、包装等进行初步验收，并出具收货凭证（以乙方入库单为准）；如产品不符合本合同及订单要求的，乙方有权拒绝接收。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第22条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方货物在交付乙方并经验收（验收以乙方入库单为准）前，一切风险及责任均由甲方承担。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>货款结算</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第23条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方从甲方购进的产品按账期结算，甲方给予乙方的账期为\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" id=\"term_days\" ></input>\n" +
                    "                    天，账期的计算起点为甲方产品进入乙方库房之日（以乙方入库单为准）。在账期内，甲方应按照乙方订单的需求无条件向乙方供货。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第24条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意当期货款的结算以按本合同约定的已到期的应付货款余额扣除甲方未向乙方支付的或逾期未支付的降价款、退货款、退残款、其它甲方承诺支付的款项以及依照本合同约定甲方应当向乙方支付的全部款项后的余额为准。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第25条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    如乙方应付账款低于甲方应付乙方的款项，甲方应在15个工作日内将所差款项以支票或电汇的方式支付给乙方。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第26条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    货款结算方式为\n" +
                    "                    <list type=\"number\" width=\"30\" id=\"loan_settle_option\">（在以下几种方式中任选一种）\n" +
                    "                        <options>\n" +
                    "                            <option value=\"1\" selected=\"selected\">1</option>\n" +
                    "                            <option value=\"2\">2</option>\n" +
                    "                            <option value=\"3\">3</option>\n" +
                    "                            <option value=\"4\">4</option>\n" +
                    "                        </options>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>1)</term2_num>\n" +
                    "                            <term2_body>电/信汇；</term2_body>\n" +
                    "                        </term2>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>2)</term2_num>\n" +
                    "                            <term2_body>\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"1\" max=\"12\" id=\"free_interest_bank_months\" ></input>\n" +
                    "                                个月无息银行承兑汇票；\n" +
                    "                            </term2_body>\n" +
                    "                        </term2>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>3)</term2_num>\n" +
                    "                            <term2_body>\n" +
                    "                                单笔付款大于等于\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"0\" id=\"free_int_loan_amount\" ></input>\n" +
                    "                                万元\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"1\" max=\"12\" id=\"free_int_loan_months\"></input>\n" +
                    "                                个月无息银行承兑汇票，\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"0\" id=\"tel_trans_amount\"></input>\n" +
                    "                                |万元电/信汇；\n" +
                    "                            </term2_body>\n" +
                    "                        </term2>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>4)</term2_num>\n" +
                    "                            <term2_body>\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"1\" max=\"12\" id=\"free_interest_com_months\"></input>\n" +
                    "                                个月无息商业承兑汇票。\n" +
                    "                            </term2_body>\n" +
                    "                        </term2>\n" +
                    "                    </list>\n" +
                    "        </term1_body>\n" +
                    "        </term1>\n" +
                    "        <term1>\n" +
                    "            <term1_num>第27条</term1_num>\n" +
                    "            <term1_body>\n" +
                    "                甲方应按时向乙方提供增值税专用发票。甲方提供的增值税专用发票应与乙方验收入库的产品的型号、数量、单价等信息相吻合，否则乙方有权拒收。如甲方未提供合法发票、提供虚假发票或提供其他不符合法律规定及合同约定的发票的，乙方有权延期支付费用且不承担任何责任，直至提供合格发票时止（如果因某些特殊原因【如履行生效法律文书等】而需由乙方在未取得发票时支付货款，则乙方有权从应付货款中扣除42%/41%的税金后将余额支付给甲方）；因此给乙方造成损失的（包括但不限于乙方应税务机关要求补交的税款、支付的罚款及乙方因补开发票而支出的费用等等），乙方有权要求甲方赔偿全部损失，并且甲方还应向乙方支付相当于上述损失的一倍的违约金。\n" +
                    "            </term1_body>\n" +
                    "        </term1>\n" +
                    "        <term1>\n" +
                    "            <term1_num>第28条</term1_num>\n" +
                    "            <term1_body>\n" +
                    "                结算时间为每周一至周四。结算日为法定节假日的双方不进行结算，结算日顺延至节假日后第一日。因甲方未及时提供增值税专用发票或提供增值税专用发票不合格原因致使乙方不能结算的，乙方不承担迟延结算的责任。\n" +
                    "            </term1_body>\n" +
                    "        </term1>\n" +
                    "        <term1>\n" +
                    "            <term1_num>第29条</term1_num>\n" +
                    "            <term1_body>\n" +
                    "                甲方承诺给予乙方的合同内费用（包括但不限于降价款、退残款、滞销费、利润补偿、补利等）、合同外费用（包括但不限于单台蓝卡、总额蓝卡、总额促销积分、财富增值卡、现金卡、市调蓝卡、美券等），付款时间不得超过最近一次货款结算日。\n" +
                    "            </term1_body>\n" +
                    "        </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>库存管理</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第30条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方在经营过程中发现的甲方产品中的残次品，乙方有权按残次品供价总额等额暂扣应付款直至甲方按合同约定处理为止。甲方应在乙方通知（包括但不限于书面通知、邮件通知、电话通知等，如甲方使用ECP系统的，则ECP系统显示了甲方产品残次数据即视为乙方已通知甲方）后10天（二级市场15天）负责更换或者收回残次产品,如甲方逾期未更换或者收回残次产品，应以未更换或者收回残次产品的供价总额为基础按每天0.3%的标准向乙方支付残次管理费用（包括仓储费用、人工管理费用等）。乙方有权从应付甲方的最近一次货款中直接扣收或要求甲方在最近一次货款结算日前以电汇的方式向乙方支付。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第31条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方办理退换货时，需向乙方出具经甲方确认的退货单据（包括但不限于红字出库单或负数出库单或蓝字入库单）进行确认，甲方向乙方支付退残（货）款的时间为最近一次货款结算日，支付方式为现款或由乙方在与甲方结算时账扣。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第32条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    当甲方提供的产品在乙方库存的时间达到30天时，甲方应采取有效措施解决该库存产品。当该批产品库存时间达到50天时，甲方应向乙方书面反馈滞销产品处理方案（退货、换货、促销处理）。甲方应在产品库存时间达到60天前处理完毕滞销产品。如出现超过60天的滞销库存产品，则甲方应向乙方支付该批滞销库存金额的1％做为滞销处理费用及仓储费用；如出现超过70天的滞销库存商品，甲方同意另行支付该批滞销库存金额的1％做为滞销处理费用及仓储费用；如出现超过80天的滞销库存商品，甲方同意继续另行支付该批滞销库存金额的2％做为滞销处理费用及仓储费用；甲方同意在80天后每增加10天再另行向乙方支付滞销库存金额的2%做为滞销处理费用及仓储费用；以此类推。该滞销处理费用及仓储费用每10天计算并挂账，甲方同意在最近一次结算时冲减乙方应付账款，乙方应付账款余额不足或乙方无应付账款则甲方应在滞销处理费用及仓储费用产生后的10日内以支票或电汇的方式向乙方支付，乙方向甲方开具增值税专用发票（税率6%）。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第33条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方承诺甲方提供的产品、配件、附件、宣传材料、促销礼品、赠品以及其他一切材料和物品等均不侵犯任何第三方的知识产权和合法权益。如甲方提供的上述物品存在或涉嫌存在（如涉及诉讼、仲裁或行政处罚等）损害任何第三方的知识产权和其它合法权益的情况，因此给乙方造成经济损失的，甲方双倍赔偿乙方损失。对于给乙方造成名誉损失的，甲方另行向乙方再赔偿名誉损失\n" +
                    "                    <input type=\"number\" readonly=\"true\" value=\"10\" width=\"30\" ></input>\n" +
                    "                    万元。乙方并有权根据情况选择退货或换货，甲方应在接到乙方通知后2日内更换或退货，否则承担延迟履行的违约责任。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第34条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    对于第30条至第33条所述的情况，更换或退货均由甲方负责从商品存放地点提货并自行运回，因此产生的全部往返运费、装卸费等均由甲方自行承担，期间甲方不能影响双方的正常合作，否则将视为甲方违约。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>（一）</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    对于甲方提供的产品及配件、附件、宣传材料、促销礼品、赠品等为假冒产品或存在虚假宣传的，乙方一经发现上述情况通知甲方后，甲方应于三日内向乙方支付不少于10万元人民币的商誉损失赔偿金，甲方未按约定时间赔偿的，乙方有权从应付甲方的货款中扣除。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>（二）</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    对于乙方因售甲方提供的假冒产品或被虚假宣传的产品而致使乙方遭受损失的，甲方应赔偿乙方全部损失（包括直接损失和间接损失）。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>（三）</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方对其提供给乙方但尚未售出的假冒或被虚假宣传的产品，应无条件退货并承担退货的一切费用。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>（四）</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    对已售出的甲方提供给乙方的假冒或被虚假宣传的产品，乙方按已售产品总金额的300%向甲方收取顾客赔偿准备金，在诉讼有效期结束后或产品售后保修期结束，以两者中后到期者为准，进行统一结算，多退少补。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第34条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    对于第30条至第33条所述的情况，更换或退货均由甲方负责从商品存放地点提货并自行运回，因此产生的全部往返运费、装卸费等均由甲方自行承担，期间甲方不能影响双方的正常合作，否则将视为甲方违约。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>场外交易行为管理</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第35条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    场外交易行为指甲方促销员或甲方其它相关人员引导顾客与甲方或厂家或其它经营商私下成交，从而导致或可能导致乙方丧失与顾客成交的机会，以下简称甲方的场外交易行为。“乙方没有顾客需要的产品”不能成为甲方进行场外交易行为的理由，换言之，“乙方没有顾客需要的产品”不能成为甲方不构成场外交易行为的抗辩理由。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第36条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    （1）甲方或其相关公司（见主合同附件一）发生场外交易行为均视为对乙方的违约，甲方应按照本合同约定就甲方及甲方促销人员的行为向乙方承担违约责任。（2）甲方在乙方或其任一相关公司（见主合同中附件一，下同）首次发生场外交易的，甲方应就该次违约向乙方支付 10 万元的违约金。如甲方在连续12个月内，在乙方同一相关公司中发生两次的场外交易的，则对于第二次场外交易行为，甲方应向乙方支付20万元违约金，并给予书面致歉，书面致歉函由乙方财务存档。（3）甲方在连续12个月内在乙方同一相关公司累计发生三次或三次以上的场外交易的，甲方应按20万元/次的标准向乙方支付违约金，同时乙方有权单方解除主合同及相关补充协议，并有权追究甲方的解约违约责任。（4）该违约金支付方式为现款，由甲方于收到乙方通知后7日内支付给乙方，逾期未付甲方同意乙方从最近一次结算的货款中扣收；乙方应付甲方的款项不足以抵扣上述违约金的，乙方有权继续向甲方追偿抵扣后的余额部分。乙方收到该违约金款项后，向甲方开具收据。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第37条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    为防止甲方场外交易行为的发生，甲方应对所属雇员加强管理和培训，乙方对此予以监督和检查。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>甲方权利、义务</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第38条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方系一家依照中华人民共和国相关法律成立的有限公司或经营实体，已合法办理开展本合同项下业务经营所需的法律手续（甲方应在本合同签订同时向乙方提供包括但不限于营业执照、法定代表人身份证明等材料）。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第39条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方免费向乙方提供每件商品的商品信息，包括完整的商品说明、电子图像、营销资料和标注信息（包括但不限于警示说明、使用说明、保质期和免责声明），及对上述资料随时的更新和修改。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第40条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方有义务向乙方提供电子商务经营的商品型号、供货价格、零售价格，及促销活动方式，并且甲方不得拒绝向乙方提供电子商务商品。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第41条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方需向乙方提供现有在售智能产品型号、价格等明细，并每月向乙方告知即将上市新品智能产品的型号价格、优势点等具体信息供乙方参考是否就新产品合作。甲方在乙方经营品牌中的在售智能产品，必须通过云对云的方式与乙方的国美智能云平台进行连接，实现国美云智超级APP对甲方智能产品的控制，并实现用户和设备数据的共享。如甲方不能履行此约定，乙方有权对甲方在售智能商品采取强制下架。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第42条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方通过乙方渠道销售的智能新品，需要优先采用国美的智能云平台提供服务，按照国美的Gomelink协议接入国美智能云平台。甲方如没有智能产品方案及云平台技术，需优先选择乙方技术平台进行智能产品的开发，并由乙方提供产品智能化解决方案及乙方销售渠道。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第43条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本着甲乙双方共同的合作目标，即为消费者提供良好的设备操控体验及确保良好沟通机制与项目推进进度，甲乙双方需确保各自网络服务平台的稳定性、安全性、保证数据互通流畅。如甲方对平台进行改动、升级等技术变更，需提前至少10个工作日告知乙方，并对可能对乙方产生的影响出具解决方案，以避免对乙方正常经营产生不利影响；如对乙方造成影响，甲方应当承担赔偿责任。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第44条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方销售给乙方的商品质量（包括商品包装）应符合国家标准、行业标准以及双方约定的质量标准。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第45条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意将产品信息、营销方案、市场信息的第一知情权给与乙方；对营销方案及促销资源，乙方有优先选择权。\n" +
                    "                </term1_body>\n" +
                    "                甲方有权要求乙方按照合同约定支付货款。\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第46条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第47条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方有义务采取有效措施，杜绝其工作人员以公司或私人名义向乙方工作人员私下直接或间接赠送礼金、贵重礼品、有价证券，或采取其它变相手段提供不当利益。甲方应对由此给乙方造成的直接和间接损失进行双倍赔偿，同时乙方并有权对甲方要求每次最低50万元人民币的违约金。同时，乙方保留追究甲方法律责任的权利。如果乙方人员私下向甲方或甲方业务人员索贿如：礼金、贵重礼品、有价证券等，甲方有义务拒绝支付并向乙方进行投诉。甲方如存在上述违约行为，乙方有权暂停结算货款，在甲方依约承担上述违约责任后，乙方才有义务恢复货款结算，上述期间乙方无需承担任何违约责任（包括但不限于暂停支付货款的利息、货物滞销导致质保期过期损失、诉讼费用等）。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第48条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方应根据市场情况变化及合同的约定随时调低供货价格，以保证乙方的销售价格更具有市场竞争力。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第49条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    在乙方进货后，甲方产品供价下调产生降价款时，甲方应\n" +
                    "                    <list type=\"number\" width=\"30\" id=\"price_cut_option\">：\n" +
                    "                        <options>\n" +
                    "                            <option value=\"1\" selected=\"selected\">1</option>\n" +
                    "                            <option value=\"2\">2</option>\n" +
                    "                        </options>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>1、</term2_num>\n" +
                    "                            <term2_body>对乙方尚未售出的（售出是指乙方已售出并已发货的商品）该产品进行价格补差；</term2_body>\n" +
                    "                        </term2>\n" +
                    "                        <term2>\n" +
                    "                            <term2_num>2、</term2_num>\n" +
                    "                            <term2_body>\n" +
                    "                                对\n" +
                    "                                <input type=\"number\" width=\"30\" min=\"0\" id=\"price_cut_days\"></input>\n" +
                    "                                日内乙方自甲方购进的全部产品进行价格补差。补差确认时间为乙方提供相关数据后二个工作日内，甲方须向乙方出具书面的降价确认函，标明调价的型号、数量、价格，加盖有效印章或有效授权人员签字；甲方交纳降价款的时间为最近一次货款结算日，支付方式为现款或账扣。\n" +
                    "                            </term2_body>\n" +
                    "                        </term2>\n" +
                    "                    </list>\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第50条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意乙方使用甲方提供产品的相关宣传资料包括但不限于乙方为推广产品之需要展示的产品标识、代言广告文字或视频内容等，甲方保证提供给乙方的产品信息、规格参数、商品图片、商品描述文字、宣传资料应真实可靠且符合《广告法》、《消费者权益保护法》等国家相关法律法规规定，若因存在虚假宣传或侵犯他人权利导致的投诉、处罚或诉讼的，甲方应积极出面处理，因此给乙方造成损失的，甲方承担一切责任及经济赔偿。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第51条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    若甲方提供的产品的配件（附件）是第三方的产品，甲方保证已获得使用许可，该使用不侵犯第三方的合法权益。否则因此而产生的一切责任均由甲方承担。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第52条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意与乙方开展每月不少于【\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" max=\"31\" id=\"promotion_days\"></input>\n" +
                    "                    】次的共同营销，营销活动投入不少于甲方给予其他任何零售商（包括但不限于电子商务）投入的促销资源，包括但不限于路演、降价促销、联合报广、店内广告宣传、签售、线上引流、新品首发等，乙方积极配合并在备货、场地提供、人员支持、营销方案等方面给予甲方必要的支持；如遇重大节假日，甲方应优先保障乙方的产品供应，并保证乙方在市场的促销能力处于优势。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第53条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    为促进甲方产品的销售，甲方同意向乙方支付促销服务费用，乙方应按照国家相关部委规定将该费用用于乙方销售甲方提供产品的市场推广及促销服务，包括但不限于印制海报、开展促销活动、广告宣传等。双方应在收取促销服务费前签订协议、备忘录或确认函。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第54条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    如果甲方自行召回商品，或如果甲方收到政府机构或生产商关于商品缺陷、召回或调查的通知，甲方应及时将此事件书面通知乙方。甲方应承担乙方因上述商品召回或调查所发生的费用。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第55条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                甲方应遵守全部有关消费者信息安全及隐私保护的法律规定。除此之外，甲方应制定和实施适当的管理、技术和物理防护措施以保护乙方信息的安全、机密和完整。这些防护措施的目的是保护这些信息的安全、机密和完整，防止对其安全和完整的威胁或破坏，及防止该信息及相关资料被未经授权的、会造成对任何消费者的损害或不便的访问或使用（“安全目的”）。上述有关消费者信息的访问和维护的安全措施应：(1) 至少符合行业通行的安全标准，和(2)提供合理的物理、技术和管理措施防止对该消费者信息的意外或非法修改或未经授权的访问或使用。在不影响上述规定的一般性的前提下，甲方应采取所有合理的措施保护其含有（即存储、处理或传送）消费者信息的系统不受黑客攻击或防止其他未经授权的人修改或访问其系统或其中的消费者信息。甲方应定期对其系统中有安全隐患的部分做检测。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第56条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                因甲方提供的产品、服务以及为销售产品而制作的展柜、展台、展架及吊楣给乙方或第三方造成人身伤残、经济损失的，甲方应承担一切赔偿责任。如甲方产品涉及到安装事宜，甲方确认由甲方承担安装义务。如甲方委托第三方负责安装，甲方应确保受托方及其工作人员具有合法资质从事该项安装工作。安装过程发生的安装方、消费者及其它第三方人身及财产损失由甲方承担，与乙方无关。如因安装问题导致产品造成人身及财产损失的，甲方应承担责任。\n" +
                    "                </term1_body>\n" +
                    "                <term1_body>\n" +
                    "                因上述原因给乙方造成损失的甲方应赔偿乙方的全部经济及名誉损失，乙方名誉损失无法确定的，以50万元/次作为损失赔偿标准。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>乙方权利、义务</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第57条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方应按合同规定向甲方支付货款。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第58条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    乙方有权优先参加甲方举办的各种促销活动，并优先享有各种促销活动支持的权利。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第59条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    任何情形下，乙方有权对以下商品随时退货：（1）任何违反中国产品质量、技术、卫生、环保、安全以及其它法律、法规规定的商品，或（2）任何侵害他人知识产权或其他民事权利的商品或任何引起有关侵权争议的商品，或（3）任何不符合本合同或订单规定质量标准或任何受损或有瑕疵的商品，或（4）任何被召回的商品。上述情形下的退货风险及费用由甲方承担。在商品召回的情况下，甲方应在召回决定作出或获知召回决定后12小时内书面通知乙方商品召回，乙方方因商品召回所发生的费用和损失，由甲方承担。乙方对上述商品货款的支付并不限制乙方获得补救和赔偿的权利。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>质保金及其他</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第60条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方向乙方提供产品在乙方门店进行销售，甲方应向乙方所代表的每个法人单位交纳所销售甲方提供产品的质量保证金。甲方按\n" +
                    "                    <input type=\"number\" width=\"30\" min=\"0\" id=\"promotion_days\"></input>\n" +
                    "                    万元/法人单位向乙方交纳质量保证金，甲方自合同签定之日起5日内向乙方交纳质量保证金。若甲方未能按时向乙方支付质量保证金，甲方同意乙方在第一次结算货款时扣减与质保金等额货款。在双方合作期间内，售后质量保证金无论因何原因发生扣除的，甲方应于扣除之日起3日内补足质量保证金，逾期应承担日千分之一的逾期付款的违约责任。如甲方撤场，从产品清场之日起至乙方已经销售的甲方所有产品保修期满止发生的一切欠收、退货、维修及赔偿责任等费用，由乙方直接用甲方交纳的质量保证金抵补。剩余的质量保证金在产品清场12个月后且甲方提供的所有产品保修期均已届满后10日内由乙方退还给甲方。如该质量保证金不足抵补上述费用则乙方享有追偿权。甲方在上一合同年度已经向乙方交纳的质保金，可继续沿用，低于本合同约定金额部分，由甲方按本合同约定的时间补足。在合同履行期间或终止后，如甲方对乙方存在应付未付的债务，乙方有权从该保证金中扣除相应金额而无需提前取得甲方同意。保证金发生扣除的，乙方在扣除后五日内通知甲方，甲方按前述约定的时间补足。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第61条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方同意在双方合作过程中如出现甲方无能力继续履行本合同约定的义务，乙方所代表的各法人单位库存中存在甲方生产或提供的产品，乙方所代表的各法人单位可直接依据库存数据办理向甲方退货，甲方不向乙方支付退货款的，可由乙方所代表的各法人单位之间通过债权债务相互转让方式进行抵消。若乙方所代表的各法人单位无对甲方存在债务的，退货款由甲方的担保方向乙方支付。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>违约责任</term_title>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第62条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲乙双方任何一方未按本合同约定履行，应承担违约责任。给对方造成损失的，本合同有约定的按约定赔偿，无约定的按实际损失额进行赔偿或参照相关法律、法规进行赔偿。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第63条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方单方终止合同需以书面形式提前三个月通知乙方，甲方并应退还乙方已支付给甲方的多余的货款，向乙方支付所有应付降价款、退残款、退货款及按照本合同有效期计算的甲方应支付乙方的其他款项。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第64条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    基于双方约定或法律规定，甲方应向乙方支付违约金、赔偿金或任何其他款项的，乙方均有权直接从乙方应付甲方的款项（包括但不限于货款等）抵扣；乙方应付甲方的款项不足抵扣的，乙方有权另行向甲方追偿。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>合同的变更、终止、解除、期满</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第65条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    除本合同另有明确约定的情形外，乙方在下列情况下享有解除合同的权利：\n" +
                    "                    <term1>\n" +
                    "                        <term1_num>1、</term1_num>\n" +
                    "                        <term1_body>\n" +
                    "                            甲方无正当理由停止向乙方供货的，或甲方收到乙方订单逾期5日未向乙方按订单约定数量供货达三次以上（含三次）的；\n" +
                    "                        </term1_body>\n" +
                    "                    </term1>\n" +
                    "                    <term1>\n" +
                    "                        <term1_num>2、</term1_num>\n" +
                    "                        <term1_body>\n" +
                    "                            甲方可能出现包括但不限于以下情况之一的：\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>①</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    经营状况严重恶化的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>②</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    出现重大负面新闻形成经营风险的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>③</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    转移资产抽逃资金的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>④</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    丧失商业信誉的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑤</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    丧失履行合约能力的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑥</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    股权发生重大变化的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑦</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    重要管理层发生重大变化的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑧</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    企业产生重大诉讼的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑨</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    未经乙方同意，把合同的权利义务全部或部分转让给第三方的；\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                            <term2>\n" +
                    "                                <term2_num>⑩</term2_num>\n" +
                    "                                <term2_body>\n" +
                    "                                    甲方的增值税一般纳税人资格被取消的。\n" +
                    "                                </term2_body>\n" +
                    "                            </term2>\n" +
                    "                        </term1_body>\n" +
                    "                    </term1>\n" +
                    "                    <term1>\n" +
                    "                        <term1_num>3、</term1_num>\n" +
                    "                        <term1_body>\n" +
                    "                            甲方发生其他严重违反合同约定的行为，经乙方书面催告7日后仍不改正的。\n" +
                    "                        </term1_body>\n" +
                    "                    </term1>\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第66条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    无论双方因任何原因停止合作后，甲方的保修义务和售后服务义务均不免除。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第67条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲乙双方无论因任何原因停止合作，除按本合同约定执行外，双方应于停止合作后10日内进行对账和结算。如乙方对甲方的应付款大于乙方对甲方的应收款，则乙方的应收应付进行抵消后，乙方将扣除质量保证金后的余额支付给甲方。如乙方对甲方的应付款小于乙方对甲方的应收款，则对于不足的部分由甲方于对账后3日内以现金补足。对于乙方尚未销售的甲方提供的产品，甲方同意按甲方的供货价办理退货。甲方于停止合作后10日内未办理退货手续。由此产生的损失，乙方有权在应付账款中予以扣除。应付账款不足以扣除的，甲方同意以现款方式补足。如甲方在本合同约定时间内不与乙方进行对账，则视为甲方默认乙方财务账目记录的应收应付数目结果，并按本合同第63条执行。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第68条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲乙双方另行约定事项：（另行约定条款与本合同其他条款内容不一致时，以另行约定为准）\n" +
                    "                    <input width=\"200\" id=\"terms_other\"></input>\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>不可抗力条款</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第69条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    “不可抗力事件”是指本合同任何一方当事人都不能抗拒，也无法预见，即使预见也无法避免的事件或者事由。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第70条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同任何一方遭受不可抗力事件的影响造成本合同项下的义务不能履行，根据不可抗力事件影响的程度，可以部分或者全部免除责任；但是遭受不可抗力事件不能履行义务的一方应当在发生不可抗力事件的48小时内及时通知另一方，并且在不可抗力事件结束后的 10个工作日内向另一方提供能够证明不可抗力事件发生、影响范围和影响程度的证明，否则遭受不可抗力事件不能履行义务的一方不得就此免除责任；任何一方延迟履行义务的，不能免除责任。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第71条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同任何一方遭受不可抗力事件的影响造成本合同项下的义务不能履行时，其应采取一切可以减少对方损失的措施进行挽救，否则其应就因其没有采取措施或者采取的措施不当而扩大的损失承担责任。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第72条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同因任何一方遭受不可抗力事件的影响不能全部履行时，遭受不可抗力事件影响的一方仅就不可抗力事件涉及的本合同有关条款或者约定可以中止、暂停或者延迟履行，而对于其它没有受到不可抗力事件影响的条款或者约定仍应履行，任何一方不得因另一方遭受不可抗力事件而停止本合同的履行。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第73条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    当不可抗力事件已经结束，不再对本合同的任何条款或者约定的履行产生任何影响时，本合同双方应尽一切可能恢复本合同的履行。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第74条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    甲方的任何一个相关公司或乙方的任何一个相关公司发生不可抗力不影响其它公司继续履行合同。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>争议的解决</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第75条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    因履行本合同发生的任何争议，双方应协商解决。协商不成时应向合同签订地的人民法院提起诉讼。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第76条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同于\n" +
                    "                    <!--<list type=\"number\" width=\"60\" id=\"sign_location\"> 。-->\n" +
                    "                        <!--<options>-->\n" +
                    "                            <!--<option value=\"1\" selected=\"selected\">北京鹏润大厦</option>-->\n" +
                    "                        <!--<option value=\"2\">乙方注册地</option>-->\n" +
                    "                        <!--</options>-->\n" +
                    "                    <!--</list>-->\n" +
                    "                    <input width=\"60\" id=\"sign_location\"></input>\n" +
                    "                    （北京鹏润大厦/乙方注册地）签订。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>合同有效期</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第77条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同有效期自\n" +
                    "                    <input id=\"con_date_begin\"></input>\n" +
                    "                    起，至\n" +
                    "                    <input id=\"con_date_end\"></input>\n" +
                    "                    止。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第78条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    合同期满如双方有意继续合作，在期满前一个月双方开始协商签订新的合同。如本合同已到期，但新的合同尚未签订，为确保双方合作的延续性，本合同及相关补充协议有效期自然顺延至新合同签订之日。在新的合同签署后乙方有权按“从高”原则选择在该顺延的期限内适用新合同或原合同，如最终没有签订新合同按本合同执行。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "\n" +
                    "        <term0>\n" +
                    "            <term_title>其它</term_title>\n" +
                    "\n" +
                    "            <term1>\n" +
                    "                <term1_num>第79条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同自双方盖章（乙方以加盖合同专用章为准）之日起生效。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第80条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同有效期内，甲方与乙方签署的与本合同相关的合同、协议、意向书及实质为合同的书面协议、备忘录等文件，自双方盖章（乙方以加盖合同专用章为准）之日起生效。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第81条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同未尽事宜，甲乙双方另行签订补充协议。补充协议及合同附件是本合同不可分割的一部分，与本合同具有同等法律效力。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "            <term1>\n" +
                    "                <term1_num>第82条</term1_num>\n" +
                    "                <term1_body>\n" +
                    "                    本合同正本一式六份，双方各执三份，具同等法律效力。\n" +
                    "                </term1_body>\n" +
                    "            </term1>\n" +
                    "        </term0>\n" +
                    "    </terms>\n" +
                    "\n" +
                    "    <!-- sign -->\n" +
                    "    <sign id_party_a=\"party_a_name\" id_party_b=\"party_b_name\" id_party_a_rep=\"party_a_legal_contact\"\n" +
                    "          id_party_b_rep=\"party_a_legal_contact\" id_party_a_date=\"party_a_sign_date\" id_party_b_date=\"party_b_sign_date\"\n" +
                    "    ></sign>\n" +
                    "</document>";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
            Source source = new StreamSource(inputStream);
            //创建xsl输入流
            stream = Xml2HtmlStringUtil.class.getClassLoader().getResourceAsStream(xslPath);
            Source template = new StreamSource(stream);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(template);
            StringWriter sw = new StringWriter();
            Result resulted = new StreamResult(sw);
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.transform(source, resulted);
            return sw != null ? sw.toString() : null;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
