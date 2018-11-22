package org.person.dfw.refelct.util;

import java.util.Date;

/**
 * @moudle: CreditGuradTemp 
 * @version:v1.0
 * @Description: CREDIT_GURAD_TEMP 对应dto
 * @author: fw
 * @date: 2017年 03月 09日 16:47:19
 *
 */
public class Dto  {

    /**
     * 序列号
     */
     //TODO 重新生成序列号
    private static final long serialVersionUID = -1;

    /** 主键 */
    private Long id;
    /** 抽取日期 */
    private Date extractDate;
    /** 发送芝麻的日期 */
    private Date sendZhimaDate;
    /** 推送给芝麻的状态，0发送失败、1发送成功 */
    private Integer sendZhimaState;
    /** 发送时订单状态，0待交款，1部分已交款，2已结清 */
    private Integer sendOrderStatus;
    /** 数据统计日期 */
    private Date bizDate;
    /** 证件类型 */
    private String userCredentialsType;
    /** 证件号码 */
    private String userCredentialsNo;
    /** 姓名 */
    private String userName;
    /** 业务号，使用出房合同编号 */
    private String orderNo;
    /** 业务开始时间，合同正式生效时间 */
    private Date orderStartDate;
    /** 业务结束时间，合同结束时间 */
    private Date orderEndDate;
    /** 当前业务状态，0正常、1违约、2业务清算 */
    private Integer orderStatus;
    /** 账单号，用收款单号 */
    private String billNo;
    /** 账期，如201608，代表2016年8月份的账单 */
    private String billDate;
    /** 账单类型，121租金、122杂费、123其他违约金、500其他违约 */
    private String billType;
    /** 账单描述 */
    private String billDesc;
    /** 账单应还款金额 */
    private Integer billAmt;
    /** 账单应还款日 */
    private Date billLastDate;
    /** 账单状态，0正常、1违约、2账单结清 */
    private Integer billStatus;
    /** 账单完结日期 */
    private Date billPayoffDate;
    /** 备注 */
    private String memo;


    /**
     * 取得 主键
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得 抽取日期
     * @return 抽取日期
     */
    public Date getExtractDate() {
        return extractDate;
    }

    /**
     * 设置 抽取日期
     * @param extractDate 抽取日期
     */
    public void setExtractDate(Date extractDate) {
        this.extractDate = extractDate;
    }

    /**
     * 取得 发送芝麻的日期
     * @return 发送芝麻的日期
     */
    public Date getSendZhimaDate() {
        return sendZhimaDate;
    }

    /**
     * 设置 发送芝麻的日期
     * @param sendZhimaDate 发送芝麻的日期
     */
    public void setSendZhimaDate(Date sendZhimaDate) {
        this.sendZhimaDate = sendZhimaDate;
    }

    /**
     * 取得 推送给芝麻的状态，0发送失败、1发送成功
     * @return 推送给芝麻的状态，0发送失败、1发送成功
     */
    public Integer getSendZhimaState() {
        return sendZhimaState;
    }

    /**
     * 设置 推送给芝麻的状态，0发送失败、1发送成功
     * @param sendZhimaState 推送给芝麻的状态，0发送失败、1发送成功
     */
    public void setSendZhimaState(Integer sendZhimaState) {
        this.sendZhimaState = sendZhimaState;
    }

    /**
     * 取得 发送时订单状态，0待交款，1部分已交款，2已结清
     * @return 发送时订单状态，0待交款，1部分已交款，2已结清
     */
    public Integer getSendOrderStatus() {
        return sendOrderStatus;
    }

    /**
     * 设置 发送时订单状态，0待交款，1部分已交款，2已结清
     * @param sendOrderStatus 发送时订单状态，0待交款，1部分已交款，2已结清
     */
    public void setSendOrderStatus(Integer sendOrderStatus) {
        this.sendOrderStatus = sendOrderStatus;
    }

    /**
     * 取得 数据统计日期
     * @return 数据统计日期
     */
    public Date getBizDate() {
        return bizDate;
    }

    /**
     * 设置 数据统计日期
     * @param bizDate 数据统计日期
     */
    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * 取得 证件类型
     * @return 证件类型
     */
    public String getUserCredentialsType() {
        return userCredentialsType;
    }

    /**
     * 设置 证件类型
     * @param userCredentialsType 证件类型
     */
    public void setUserCredentialsType(String userCredentialsType) {
        this.userCredentialsType = userCredentialsType;
    }

    /**
     * 取得 证件号码
     * @return 证件号码
     */
    public String getUserCredentialsNo() {
        return userCredentialsNo;
    }

    /**
     * 设置 证件号码
     * @param userCredentialsNo 证件号码
     */
    public void setUserCredentialsNo(String userCredentialsNo) {
        this.userCredentialsNo = userCredentialsNo;
    }

    /**
     * 取得 姓名
     * @return 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 姓名
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 取得 业务号，使用出房合同编号
     * @return 业务号，使用出房合同编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 业务号，使用出房合同编号
     * @param orderNo 业务号，使用出房合同编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 取得 业务开始时间，合同正式生效时间
     * @return 业务开始时间，合同正式生效时间
     */
    public Date getOrderStartDate() {
        return orderStartDate;
    }

    /**
     * 设置 业务开始时间，合同正式生效时间
     * @param orderStartDate 业务开始时间，合同正式生效时间
     */
    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    /**
     * 取得 业务结束时间，合同结束时间
     * @return 业务结束时间，合同结束时间
     */
    public Date getOrderEndDate() {
        return orderEndDate;
    }

    /**
     * 设置 业务结束时间，合同结束时间
     * @param orderEndDate 业务结束时间，合同结束时间
     */
    public void setOrderEndDate(Date orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    /**
     * 取得 当前业务状态，0正常、1违约、2业务清算
     * @return 当前业务状态，0正常、1违约、2业务清算
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置 当前业务状态，0正常、1违约、2业务清算
     * @param orderStatus 当前业务状态，0正常、1违约、2业务清算
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 取得 账单号，用收款单号
     * @return 账单号，用收款单号
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * 设置 账单号，用收款单号
     * @param billNo 账单号，用收款单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * 取得 账期，如201608，代表2016年8月份的账单
     * @return 账期，如201608，代表2016年8月份的账单
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * 设置 账期，如201608，代表2016年8月份的账单
     * @param billDate 账期，如201608，代表2016年8月份的账单
     */
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    /**
     * 取得 账单类型，121租金、122杂费、123其他违约金、500其他违约
     * @return 账单类型，121租金、122杂费、123其他违约金、500其他违约
     */
    public String getBillType() {
        return billType;
    }

    /**
     * 设置 账单类型，121租金、122杂费、123其他违约金、500其他违约
     * @param billType 账单类型，121租金、122杂费、123其他违约金、500其他违约
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * 取得 账单描述
     * @return 账单描述
     */
    public String getBillDesc() {
        return billDesc;
    }

    /**
     * 设置 账单描述
     * @param billDesc 账单描述
     */
    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    /**
     * 取得 账单应还款金额
     * @return 账单应还款金额
     */
    public Integer getBillAmt() {
        return billAmt;
    }

    /**
     * 设置 账单应还款金额
     * @param billAmt 账单应还款金额
     */
    public void setBillAmt(Integer billAmt) {
        this.billAmt = billAmt;
    }

    /**
     * 取得 账单应还款日
     * @return 账单应还款日
     */
    public Date getBillLastDate() {
        return billLastDate;
    }

    /**
     * 设置 账单应还款日
     * @param billLastDate 账单应还款日
     */
    public void setBillLastDate(Date billLastDate) {
        this.billLastDate = billLastDate;
    }

    /**
     * 取得 账单状态，0正常、1违约、2账单结清
     * @return 账单状态，0正常、1违约、2账单结清
     */
    public Integer getBillStatus() {
        return billStatus;
    }

    /**
     * 设置 账单状态，0正常、1违约、2账单结清
     * @param billStatus 账单状态，0正常、1违约、2账单结清
     */
    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * 取得 账单完结日期
     * @return 账单完结日期
     */
    public Date getBillPayoffDate() {
        return billPayoffDate;
    }

    /**
     * 设置 账单完结日期
     * @param billPayoffDate 账单完结日期
     */
    public void setBillPayoffDate(Date billPayoffDate) {
        this.billPayoffDate = billPayoffDate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

	/**
	 * Title:</br>
	 * </br>Description:</br>
	 */ 
	public Dto() {
		super();
	}

	/**
	 * Title:</br>
	 * </br>Description:</br>
	 * @param userName
	 * @param billAmt
	 */ 
	public Dto(String userName, Integer billAmt) {
		super();
		this.userName = userName;
		this.billAmt = billAmt;
	}
    
}