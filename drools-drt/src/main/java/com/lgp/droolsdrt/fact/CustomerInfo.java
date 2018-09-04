package com.lgp.droolsdrt.fact;

import com.lgp.droolsdrt.annotation.Fact;
import com.lgp.droolsdrt.annotation.FactProperty;

import java.util.Date;
import java.util.Set;

/**
 * @AUTHOR lgp
 * 客户基本信息
 **/
@Fact(value = ActivityEvent.REGISTER, prefix = "cust")
public class CustomerInfo {

    private static final long serialVersionUID = 8519334321117301310L;

    @FactProperty(desc = "出生月份", format = "例：cust.bornMonth == 6", optionalValue = {"1-12整数"})
    private Integer bornMonth;

    @FactProperty(desc = "出生日期", format = "例：cust.bornDay == 20", optionalValue = {"1-31整数"})
    private Integer bornDay;

    /**
     * 注册日期
     */
    @FactProperty(desc = "注册日期", format = "例：cust.registerTime &lt;= &quot;2016-06-06 00:00:00&quot;", optionalValue = {"yyyy-MM-dd HH:mm:ss"})
    private Date registerTime;

    @FactProperty(desc = "注册来源", format = "例：cust.registerFrom == &quot;activity_xianshituijian_20160401&quot;", optionalValue = {"注册来源"})
    private String registerFrom;

    @FactProperty(desc = "注册渠道id", format = "例：cust.registerChannelId &lt;= &quot;12346&quot;", optionalValue = {"渠道码字符串"})
    private String registerChannelId;

    @FactProperty(desc = "渠道组", format = "例：cust.channelGroupSet.contains(&quot;渠道组1&quot;)", optionalValue = {"渠道组名"})
    private Set<String> channelGroupSet;

    /**
     * 性别
     */
    @FactProperty(desc = "性别", format = "cust.gender == &quot;女&quot;", optionalValue = {"男", "女"})
    private String gender;

    @FactProperty(desc = "已成为会员天数", format = "cust.growAge &lt;=20", optionalValue = "单位:天")
    private Integer growAge;


    /**
     * 邀请人id
     */
    @FactProperty(desc = "邀请人id", format = "例：cust.extenderUserId == &quot;A2D0B591-9564-4B61-A227-5E0240AA47F3quot;", optionalValue = {"邀请人id"})
    private String extenderUserId;


    /**
     * 活期待收金额
     */
    @FactProperty(desc = "活期待收金额", format = "例：cust.flexibleDueAmount &gt;= 3000.00", optionalValue = {"数值，单位：元"})
    private double flexibleDueAmount;

    /**
     * 固定待收金额
     */
    @FactProperty(desc = "固定待收金额", format = "例：cust.regularDueAmount &lt;= 3000.00", optionalValue = {"数值，单位：元"})
    private double regularDueAmount;

    @FactProperty(desc = "固定待收本金", format = "例：cust.regularDuePrincipal &lt;= 3000.00", optionalValue = {"数值，单位：元"})
    private double regularDuePrincipal;

    /**
     * 累记投资金额
     */
    @FactProperty(desc = "累计投资金额", format = "例：cust.totalInvestAmount &gt;= 3000.00", optionalValue = {"数值，单位：元"})
    private double totalInvestAmount;


    /**
     * 累记投资金额
     */
    @FactProperty(desc = "累计签到天数", format = "例：cust.totalSignedDays &gt;= 10", optionalValue = {"数值，单位：天"})
    private double totalSignedDays;


    /**
     * 最后投资金额
     */
    @FactProperty(desc = "最后投资金额", format = "例：cust.lastInvestAmount == 3000.00", optionalValue = {"数值，单位：元"})
    private double lastInvestAmount;

    /**
     * 总固定期项目投资金额
     */
    @FactProperty(desc = "累计定期项目投资金额", format = "例：cust.totalRegularProjectAmount &lt;= 3000.00", optionalValue = {"数值，单位：元"})
    private double totalRegularProjectAmount;

    /**
     * 最近7天投资次数
     */
    @FactProperty(desc = "最近7天投资次数", format = "例：cust.lastSevenDaysInvestNum &gt;= 10", optionalValue = {"数值，单位：次"})
    private int lastSevenDaysInvestNum;

    /**
     * 最近7天投资金额
     */
    @FactProperty(desc = "最近7天投资金额", format = "例：cust.lastSevenDaysInvestAmount &gt;= 10000.00", optionalValue = {"数值，单位：元"})
    private double lastSevenDaysInvestAmount;

    /**
     * 近一个月投资次数
     */
    @FactProperty(desc = "近一个月投资次数", format = "例：cust.lastOneMonthInvestNum &gt;= 10", optionalValue = {"数值，单位：次"})
    private int lastOneMonthInvestNum;

    /**
     * 最后一次投资时间
     */
    @FactProperty(desc = "最后一次投资时间", format = "例：cust.lastInvestDate &lt;= &quot;2016-06-06 23:59:59&quot;", optionalValue = {"yyyy-MM-dd HH:mm:ss"})
    private Date lastInvestDate;

    public Integer getBornMonth() {
        return bornMonth;
    }

    public void setBornMonth(Integer bornMonth) {
        this.bornMonth = bornMonth;
    }

    public Integer getBornDay() {
        return bornDay;
    }

    public void setBornDay(Integer bornDay) {
        this.bornDay = bornDay;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getFlexibleDueAmount() {
        return flexibleDueAmount;
    }

    public void setFlexibleDueAmount(double flexibleDueAmount) {
        this.flexibleDueAmount = flexibleDueAmount;
    }

    public double getRegularDueAmount() {
        return regularDueAmount;
    }

    public void setRegularDueAmount(double regularDueAmount) {
        this.regularDueAmount = regularDueAmount;
    }

    public double getTotalInvestAmount() {
        return totalInvestAmount;
    }

    public void setTotalInvestAmount(double totalInvestAmount) {
        this.totalInvestAmount = totalInvestAmount;
    }

    public double getLastInvestAmount() {
        return lastInvestAmount;
    }

    public void setLastInvestAmount(double lastInvestAmount) {
        this.lastInvestAmount = lastInvestAmount;
    }

    public double getTotalRegularProjectAmount() {
        return totalRegularProjectAmount;
    }

    public void setTotalRegularProjectAmount(double totalRegularProjectAmount) {
        this.totalRegularProjectAmount = totalRegularProjectAmount;
    }

    public int getLastSevenDaysInvestNum() {
        return lastSevenDaysInvestNum;
    }

    public void setLastSevenDaysInvestNum(int lastSevenDaysInvestNum) {
        this.lastSevenDaysInvestNum = lastSevenDaysInvestNum;
    }

    public int getLastOneMonthInvestNum() {
        return lastOneMonthInvestNum;
    }

    public void setLastOneMonthInvestNum(int lastOneMonthInvestNum) {
        this.lastOneMonthInvestNum = lastOneMonthInvestNum;
    }

    public Date getLastInvestDate() {
        return lastInvestDate;
    }

    public void setLastInvestDate(Date lastInvestDate) {
        this.lastInvestDate = lastInvestDate;
    }

    public Integer getGrowAge() {
        return growAge;
    }

    public void setGrowAge(Integer growAge) {
        this.growAge = growAge;
    }

    public double getRegularDuePrincipal() {
        return regularDuePrincipal;
    }

    public void setRegularDuePrincipal(double regularDuePrincipal) {
        this.regularDuePrincipal = regularDuePrincipal;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public String getRegisterChannelId() {
        return registerChannelId;
    }

    public void setRegisterChannelId(String registerChannelId) {
        this.registerChannelId = registerChannelId;
    }

    /**
     * @return the channelGroupSet
     */
    public Set<String> getChannelGroupSet() {
        return channelGroupSet;
    }

    /**
     * @param channelGroupSet the channelGroupSet to set
     */
    public void setChannelGroupSet(Set<String> channelGroupSet) {
        this.channelGroupSet = channelGroupSet;
    }

    /**
     * @return the lastSevenDaysInvestAmount
     */
    public double getLastSevenDaysInvestAmount() {
        return lastSevenDaysInvestAmount;
    }

    /**
     * @param lastSevenDaysInvestAmount the lastSevenDaysInvestAmount to set
     */
    public void setLastSevenDaysInvestAmount(double lastSevenDaysInvestAmount) {
        this.lastSevenDaysInvestAmount = lastSevenDaysInvestAmount;
    }

    public String getExtenderUserId() {
        return extenderUserId;
    }

    public void setExtenderUserId(String extenderUserId) {
        this.extenderUserId = extenderUserId;
    }

    public double getTotalSignedDays() {
        return totalSignedDays;
    }

    public void setTotalSignedDays(double totalSignedDays) {
        this.totalSignedDays = totalSignedDays;
    }


}

