/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */
/*     */ public class MemberBO
/*     */ {
/*     */   @SerializedName("authApplyRemark")
/*  17 */   private String authApplyRemark = null;
/*     */   
/*     */   @SerializedName("authApplyStatus")
/*  20 */   private Integer authApplyStatus = null;
/*     */   
/*     */   @SerializedName("authStatus")
/*  23 */   private String authStatus = null;
/*     */   
/*     */   @SerializedName("authStep")
/*  26 */   private Integer authStep = null;
/*     */   
/*     */   @SerializedName("balance")
/*  29 */   private Double balance = null;
/*     */   
/*     */   @SerializedName("birthdate")
/*  32 */   private Date birthdate = null;
/*     */   
/*     */   @SerializedName("calorie")
/*  35 */   private Double calorie = null;
/*     */   
/*     */   @SerializedName("credentialsImages")
/*  38 */   private List<String> credentialsImages = new ArrayList();
/*     */   
/*     */   @SerializedName("deposit")
/*  41 */   private Double deposit = null;
/*     */   
/*     */   @SerializedName("email")
/*  44 */   private String email = null;
/*     */   
/*     */   @SerializedName("headImgPath")
/*  47 */   private String headImgPath = null;
/*     */   
/*     */   @SerializedName("height")
/*  50 */   private Integer height = null;
/*     */   
/*     */   @SerializedName("idCard")
/*  53 */   private String idCard = null;
/*     */   
/*     */   @SerializedName("inviteCode")
/*  56 */   private String inviteCode = null;
/*     */   
/*     */   @SerializedName("isBindQQ")
/*  59 */   private Boolean isBindQQ = null;
/*     */   
/*     */   @SerializedName("isBindWeixin")
/*  62 */   private Boolean isBindWeixin = null;
/*     */   
/*     */   @SerializedName("isVIP")
/*  65 */   private Boolean isVIP = null;
/*     */   
/*     */   @SerializedName("jobNumber")
/*  68 */   private String jobNumber = null;
/*     */   
/*     */   @SerializedName("mobile")
/*  71 */   private String mobile = null;
/*     */   
/*     */   @SerializedName("motorPower")
/*  74 */   private Integer motorPower = null;
/*     */   
/*     */   @SerializedName("name")
/*  77 */   private String name = null;
/*     */   
/*     */   @SerializedName("nickname")
/*  80 */   private String nickname = null;
/*     */   
/*     */   @SerializedName("remark")
/*  83 */   private String remark = null;
/*     */   
/*     */   @SerializedName("rideDistance")
/*  86 */   private Double rideDistance = null;
/*     */   
/*     */   @SerializedName("rideTime")
/*  89 */   private Long rideTime = null;
/*     */   
/*     */   @SerializedName("schoolName")
/*  92 */   private String schoolName = null;
/*     */   
/*     */   @SerializedName("sex")
/*  95 */   private Integer sex = null;
/*     */   
/*     */   @SerializedName("status")
/*  98 */   private String status = null;
/*     */   
/*     */   @SerializedName("studentId")
/* 101 */   private String studentId = null;
/*     */   
/*     */   @SerializedName("vipDiscount")
/* 104 */   private Double vipDiscount = null;
/*     */   
/*     */   @SerializedName("vipDxpireDateDesc")
/* 107 */   private String vipDxpireDateDesc = null;
/*     */   
/*     */   @SerializedName("vipExpiresIn")
/* 110 */   private Date vipExpiresIn = null;
/*     */   
/*     */   @SerializedName("weight")
/* 113 */   private Integer weight = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("审批备注")
/*     */   public String getAuthApplyRemark()
/*     */   {
/* 123 */     return this.authApplyRemark;
/*     */   }
/*     */   
/* 126 */   public void setAuthApplyRemark(String authApplyRemark) { this.authApplyRemark = authApplyRemark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("认证审批状态 1 审批中 2审批成功 3审批失败")
/*     */   public Integer getAuthApplyStatus()
/*     */   {
/* 135 */     return this.authApplyStatus;
/*     */   }
/*     */   
/* 138 */   public void setAuthApplyStatus(Integer authApplyStatus) { this.authApplyStatus = authApplyStatus; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("认证状态：0未认证，1认证中，2认证成功，3认证失败")
/*     */   public String getAuthStatus()
/*     */   {
/* 147 */     return this.authStatus;
/*     */   }
/*     */   
/* 150 */   public void setAuthStatus(String authStatus) { this.authStatus = authStatus; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("认证步骤：1.手机认证,2. 押金充值 3.实名认证 4.认证完成")
/*     */   public Integer getAuthStep()
/*     */   {
/* 159 */     return this.authStep;
/*     */   }
/*     */   
/* 162 */   public void setAuthStep(Integer authStep) { this.authStep = authStep; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("余额")
/*     */   public Double getBalance()
/*     */   {
/* 171 */     return this.balance;
/*     */   }
/*     */   
/* 174 */   public void setBalance(Double balance) { this.balance = balance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Date getBirthdate()
/*     */   {
/* 182 */     return this.birthdate;
/*     */   }
/*     */   
/* 185 */   public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("消耗热量kcal")
/*     */   public Double getCalorie()
/*     */   {
/* 194 */     return this.calorie;
/*     */   }
/*     */   
/* 197 */   public void setCalorie(Double calorie) { this.calorie = calorie; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("证件图片")
/*     */   public List<String> getCredentialsImages()
/*     */   {
/* 206 */     return this.credentialsImages;
/*     */   }
/*     */   
/* 209 */   public void setCredentialsImages(List<String> credentialsImages) { this.credentialsImages = credentialsImages; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("押金")
/*     */   public Double getDeposit()
/*     */   {
/* 218 */     return this.deposit;
/*     */   }
/*     */   
/* 221 */   public void setDeposit(Double deposit) { this.deposit = deposit; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getEmail()
/*     */   {
/* 229 */     return this.email;
/*     */   }
/*     */   
/* 232 */   public void setEmail(String email) { this.email = email; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("头像路径")
/*     */   public String getHeadImgPath()
/*     */   {
/* 241 */     return this.headImgPath;
/*     */   }
/*     */   
/* 244 */   public void setHeadImgPath(String headImgPath) { this.headImgPath = headImgPath; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getHeight()
/*     */   {
/* 252 */     return this.height;
/*     */   }
/*     */   
/* 255 */   public void setHeight(Integer height) { this.height = height; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("身份证")
/*     */   public String getIdCard()
/*     */   {
/* 264 */     return this.idCard;
/*     */   }
/*     */   
/* 267 */   public void setIdCard(String idCard) { this.idCard = idCard; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("邀请码")
/*     */   public String getInviteCode()
/*     */   {
/* 276 */     return this.inviteCode;
/*     */   }
/*     */   
/* 279 */   public void setInviteCode(String inviteCode) { this.inviteCode = inviteCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否绑定QQ")
/*     */   public Boolean getIsBindQQ()
/*     */   {
/* 288 */     return this.isBindQQ;
/*     */   }
/*     */   
/* 291 */   public void setIsBindQQ(Boolean isBindQQ) { this.isBindQQ = isBindQQ; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否绑定Weixin")
/*     */   public Boolean getIsBindWeixin()
/*     */   {
/* 300 */     return this.isBindWeixin;
/*     */   }
/*     */   
/* 303 */   public void setIsBindWeixin(Boolean isBindWeixin) { this.isBindWeixin = isBindWeixin; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否vip")
/*     */   public Boolean getIsVIP()
/*     */   {
/* 312 */     return this.isVIP;
/*     */   }
/*     */   
/* 315 */   public void setIsVIP(Boolean isVIP) { this.isVIP = isVIP; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("工号")
/*     */   public String getJobNumber()
/*     */   {
/* 324 */     return this.jobNumber;
/*     */   }
/*     */   
/* 327 */   public void setJobNumber(String jobNumber) { this.jobNumber = jobNumber; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("手机")
/*     */   public String getMobile()
/*     */   {
/* 336 */     return this.mobile;
/*     */   }
/*     */   
/* 339 */   public void setMobile(String mobile) { this.mobile = mobile; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("电机功率")
/*     */   public Integer getMotorPower()
/*     */   {
/* 348 */     return this.motorPower;
/*     */   }
/*     */   
/* 351 */   public void setMotorPower(Integer motorPower) { this.motorPower = motorPower; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("姓名")
/*     */   public String getName()
/*     */   {
/* 360 */     return this.name;
/*     */   }
/*     */   
/* 363 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("昵称")
/*     */   public String getNickname()
/*     */   {
/* 372 */     return this.nickname;
/*     */   }
/*     */   
/* 375 */   public void setNickname(String nickname) { this.nickname = nickname; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("备注")
/*     */   public String getRemark()
/*     */   {
/* 384 */     return this.remark;
/*     */   }
/*     */   
/* 387 */   public void setRemark(String remark) { this.remark = remark; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("骑行距离/单位M")
/*     */   public Double getRideDistance()
/*     */   {
/* 396 */     return this.rideDistance;
/*     */   }
/*     */   
/* 399 */   public void setRideDistance(Double rideDistance) { this.rideDistance = rideDistance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("骑行时间/分钟")
/*     */   public Long getRideTime()
/*     */   {
/* 408 */     return this.rideTime;
/*     */   }
/*     */   
/* 411 */   public void setRideTime(Long rideTime) { this.rideTime = rideTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("学校")
/*     */   public String getSchoolName()
/*     */   {
/* 420 */     return this.schoolName;
/*     */   }
/*     */   
/* 423 */   public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("性别 1，男 2，女")
/*     */   public Integer getSex()
/*     */   {
/* 432 */     return this.sex;
/*     */   }
/*     */   
/* 435 */   public void setSex(Integer sex) { this.sex = sex; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("状态 1:正常 2：未激活,3禁用")
/*     */   public String getStatus()
/*     */   {
/* 444 */     return this.status;
/*     */   }
/*     */   
/* 447 */   public void setStatus(String status) { this.status = status; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("学号")
/*     */   public String getStudentId()
/*     */   {
/* 456 */     return this.studentId;
/*     */   }
/*     */   
/* 459 */   public void setStudentId(String studentId) { this.studentId = studentId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("vip 折扣")
/*     */   public Double getVipDiscount()
/*     */   {
/* 468 */     return this.vipDiscount;
/*     */   }
/*     */   
/* 471 */   public void setVipDiscount(Double vipDiscount) { this.vipDiscount = vipDiscount; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("会员到期时间描述")
/*     */   public String getVipDxpireDateDesc()
/*     */   {
/* 480 */     return this.vipDxpireDateDesc;
/*     */   }
/*     */   
/* 483 */   public void setVipDxpireDateDesc(String vipDxpireDateDesc) { this.vipDxpireDateDesc = vipDxpireDateDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("vip到期时间")
/*     */   public Date getVipExpiresIn()
/*     */   {
/* 492 */     return this.vipExpiresIn;
/*     */   }
/*     */   
/* 495 */   public void setVipExpiresIn(Date vipExpiresIn) { this.vipExpiresIn = vipExpiresIn; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Integer getWeight()
/*     */   {
/* 503 */     return this.weight;
/*     */   }
/*     */   
/* 506 */   public void setWeight(Integer weight) { this.weight = weight; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 513 */     if (this == o) {
/* 514 */       return true;
/*     */     }
/* 516 */     if ((o == null) || (getClass() != o.getClass())) {
/* 517 */       return false;
/*     */     }
/* 519 */     MemberBO memberBO = (MemberBO)o;
/* 520 */     return (Objects.equals(this.authApplyRemark, memberBO.authApplyRemark)) && 
/* 521 */       (Objects.equals(this.authApplyStatus, memberBO.authApplyStatus)) && 
/* 522 */       (Objects.equals(this.authStatus, memberBO.authStatus)) && 
/* 523 */       (Objects.equals(this.authStep, memberBO.authStep)) && 
/* 524 */       (Objects.equals(this.balance, memberBO.balance)) && 
/* 525 */       (Objects.equals(this.birthdate, memberBO.birthdate)) && 
/* 526 */       (Objects.equals(this.calorie, memberBO.calorie)) && 
/* 527 */       (Objects.equals(this.credentialsImages, memberBO.credentialsImages)) && 
/* 528 */       (Objects.equals(this.deposit, memberBO.deposit)) && 
/* 529 */       (Objects.equals(this.email, memberBO.email)) && 
/* 530 */       (Objects.equals(this.headImgPath, memberBO.headImgPath)) && 
/* 531 */       (Objects.equals(this.height, memberBO.height)) && 
/* 532 */       (Objects.equals(this.idCard, memberBO.idCard)) && 
/* 533 */       (Objects.equals(this.inviteCode, memberBO.inviteCode)) && 
/* 534 */       (Objects.equals(this.isBindQQ, memberBO.isBindQQ)) && 
/* 535 */       (Objects.equals(this.isBindWeixin, memberBO.isBindWeixin)) && 
/* 536 */       (Objects.equals(this.isVIP, memberBO.isVIP)) && 
/* 537 */       (Objects.equals(this.jobNumber, memberBO.jobNumber)) && 
/* 538 */       (Objects.equals(this.mobile, memberBO.mobile)) && 
/* 539 */       (Objects.equals(this.motorPower, memberBO.motorPower)) && 
/* 540 */       (Objects.equals(this.name, memberBO.name)) && 
/* 541 */       (Objects.equals(this.nickname, memberBO.nickname)) && 
/* 542 */       (Objects.equals(this.remark, memberBO.remark)) && 
/* 543 */       (Objects.equals(this.rideDistance, memberBO.rideDistance)) && 
/* 544 */       (Objects.equals(this.rideTime, memberBO.rideTime)) && 
/* 545 */       (Objects.equals(this.schoolName, memberBO.schoolName)) && 
/* 546 */       (Objects.equals(this.sex, memberBO.sex)) && 
/* 547 */       (Objects.equals(this.status, memberBO.status)) && 
/* 548 */       (Objects.equals(this.studentId, memberBO.studentId)) && 
/* 549 */       (Objects.equals(this.vipDiscount, memberBO.vipDiscount)) && 
/* 550 */       (Objects.equals(this.vipDxpireDateDesc, memberBO.vipDxpireDateDesc)) && 
/* 551 */       (Objects.equals(this.vipExpiresIn, memberBO.vipExpiresIn)) && 
/* 552 */       (Objects.equals(this.weight, memberBO.weight));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 557 */     return Objects.hash(new Object[] { this.authApplyRemark, this.authApplyStatus, this.authStatus, this.authStep, this.balance, this.birthdate, this.calorie, this.credentialsImages, this.deposit, this.email, this.headImgPath, this.height, this.idCard, this.inviteCode, this.isBindQQ, this.isBindWeixin, this.isVIP, this.jobNumber, this.mobile, this.motorPower, this.name, this.nickname, this.remark, this.rideDistance, this.rideTime, this.schoolName, this.sex, this.status, this.studentId, this.vipDiscount, this.vipDxpireDateDesc, this.vipExpiresIn, this.weight });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 562 */     StringBuilder sb = new StringBuilder();
/* 563 */     sb.append("class MemberBO {\n");
/*     */     
/* 565 */     sb.append("    authApplyRemark: ").append(toIndentedString(this.authApplyRemark)).append("\n");
/* 566 */     sb.append("    authApplyStatus: ").append(toIndentedString(this.authApplyStatus)).append("\n");
/* 567 */     sb.append("    authStatus: ").append(toIndentedString(this.authStatus)).append("\n");
/* 568 */     sb.append("    authStep: ").append(toIndentedString(this.authStep)).append("\n");
/* 569 */     sb.append("    balance: ").append(toIndentedString(this.balance)).append("\n");
/* 570 */     sb.append("    birthdate: ").append(toIndentedString(this.birthdate)).append("\n");
/* 571 */     sb.append("    calorie: ").append(toIndentedString(this.calorie)).append("\n");
/* 572 */     sb.append("    credentialsImages: ").append(toIndentedString(this.credentialsImages)).append("\n");
/* 573 */     sb.append("    deposit: ").append(toIndentedString(this.deposit)).append("\n");
/* 574 */     sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
/* 575 */     sb.append("    headImgPath: ").append(toIndentedString(this.headImgPath)).append("\n");
/* 576 */     sb.append("    height: ").append(toIndentedString(this.height)).append("\n");
/* 577 */     sb.append("    idCard: ").append(toIndentedString(this.idCard)).append("\n");
/* 578 */     sb.append("    inviteCode: ").append(toIndentedString(this.inviteCode)).append("\n");
/* 579 */     sb.append("    isBindQQ: ").append(toIndentedString(this.isBindQQ)).append("\n");
/* 580 */     sb.append("    isBindWeixin: ").append(toIndentedString(this.isBindWeixin)).append("\n");
/* 581 */     sb.append("    isVIP: ").append(toIndentedString(this.isVIP)).append("\n");
/* 582 */     sb.append("    jobNumber: ").append(toIndentedString(this.jobNumber)).append("\n");
/* 583 */     sb.append("    mobile: ").append(toIndentedString(this.mobile)).append("\n");
/* 584 */     sb.append("    motorPower: ").append(toIndentedString(this.motorPower)).append("\n");
/* 585 */     sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
/* 586 */     sb.append("    nickname: ").append(toIndentedString(this.nickname)).append("\n");
/* 587 */     sb.append("    remark: ").append(toIndentedString(this.remark)).append("\n");
/* 588 */     sb.append("    rideDistance: ").append(toIndentedString(this.rideDistance)).append("\n");
/* 589 */     sb.append("    rideTime: ").append(toIndentedString(this.rideTime)).append("\n");
/* 590 */     sb.append("    schoolName: ").append(toIndentedString(this.schoolName)).append("\n");
/* 591 */     sb.append("    sex: ").append(toIndentedString(this.sex)).append("\n");
/* 592 */     sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
/* 593 */     sb.append("    studentId: ").append(toIndentedString(this.studentId)).append("\n");
/* 594 */     sb.append("    vipDiscount: ").append(toIndentedString(this.vipDiscount)).append("\n");
/* 595 */     sb.append("    vipDxpireDateDesc: ").append(toIndentedString(this.vipDxpireDateDesc)).append("\n");
/* 596 */     sb.append("    vipExpiresIn: ").append(toIndentedString(this.vipExpiresIn)).append("\n");
/* 597 */     sb.append("    weight: ").append(toIndentedString(this.weight)).append("\n");
/* 598 */     sb.append("}");
/* 599 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 607 */     if (o == null) {
/* 608 */       return "null";
/*     */     }
/* 610 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\MemberBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */