/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class SysSettingBO
/*     */ {
/*     */   @SerializedName("appDownloadUrl")
/*  15 */   private String appDownloadUrl = null;
/*     */   
/*     */   @SerializedName("appName")
/*  18 */   private String appName = null;
/*     */   
/*     */   @SerializedName("appNameEnglish")
/*  21 */   private String appNameEnglish = null;
/*     */   
/*     */   @SerializedName("authenType")
/*  24 */   private String authenType = null;
/*     */   
/*     */   @SerializedName("cardDesc")
/*  27 */   private String cardDesc = null;
/*     */   
/*     */   @SerializedName("communicationIp")
/*  30 */   private String communicationIp = null;
/*     */   
/*     */   @SerializedName("communicationPort")
/*  33 */   private Integer communicationPort = null;
/*     */   
/*     */   @SerializedName("contactMobile")
/*  36 */   private String contactMobile = null;
/*     */   
/*     */   @SerializedName("corporateName")
/*  39 */   private String corporateName = null;
/*     */   
/*     */   @SerializedName("defaultLanguage")
/*  42 */   private String defaultLanguage = null;
/*     */   
/*     */   @SerializedName("imgTime")
/*  45 */   private Integer imgTime = null;
/*     */   
/*     */   @SerializedName("isNeedAuthen")
/*  48 */   private Boolean isNeedAuthen = null;
/*     */   
/*     */   @SerializedName("isNeedUpCertificates")
/*  51 */   private Boolean isNeedUpCertificates = null;
/*     */   
/*     */   @SerializedName("isOpenAd")
/*  54 */   private Boolean isOpenAd = null;
/*     */   
/*     */   @SerializedName("isOpenBalance")
/*  57 */   private Boolean isOpenBalance = null;
/*     */   
/*     */   @SerializedName("isOpenBattery")
/*  60 */   private Boolean isOpenBattery = null;
/*     */   
/*     */   @SerializedName("isOpenBicycle")
/*  63 */   private Boolean isOpenBicycle = null;
/*     */   
/*     */   @SerializedName("isOpenMemberCard")
/*  66 */   private Boolean isOpenMemberCard = null;
/*     */   
/*     */   @SerializedName("isPayTrip")
/*  69 */   private Boolean isPayTrip = null;
/*     */   
/*     */   @SerializedName("isSpotParking")
/*  72 */   private Boolean isSpotParking = null;
/*     */   
/*     */   @SerializedName("isUserRegister")
/*  75 */   private Boolean isUserRegister = null;
/*     */   
/*     */   @SerializedName("logoImg")
/*  78 */   private String logoImg = null;
/*     */   
/*     */   @SerializedName("payWay")
/*  81 */   private String payWay = null;
/*     */   
/*     */   @SerializedName("reserveNum")
/*  84 */   private Integer reserveNum = null;
/*     */   
/*     */   @SerializedName("reserveTime")
/*  87 */   private Integer reserveTime = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getAppDownloadUrl()
/*     */   {
/*  96 */     return this.appDownloadUrl;
/*     */   }
/*     */   
/*  99 */   public void setAppDownloadUrl(String appDownloadUrl) { this.appDownloadUrl = appDownloadUrl; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("应用名称")
/*     */   public String getAppName()
/*     */   {
/* 108 */     return this.appName;
/*     */   }
/*     */   
/* 111 */   public void setAppName(String appName) { this.appName = appName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("app 英文")
/*     */   public String getAppNameEnglish()
/*     */   {
/* 120 */     return this.appNameEnglish;
/*     */   }
/*     */   
/* 123 */   public void setAppNameEnglish(String appNameEnglish) { this.appNameEnglish = appNameEnglish; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("认证类型：身份证 id_card，学生证 student_card")
/*     */   public String getAuthenType()
/*     */   {
/* 132 */     return this.authenType;
/*     */   }
/*     */   
/* 135 */   public void setAuthenType(String authenType) { this.authenType = authenType; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("会员卡描述")
/*     */   public String getCardDesc()
/*     */   {
/* 144 */     return this.cardDesc;
/*     */   }
/*     */   
/* 147 */   public void setCardDesc(String cardDesc) { this.cardDesc = cardDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("通讯 ip")
/*     */   public String getCommunicationIp()
/*     */   {
/* 156 */     return this.communicationIp;
/*     */   }
/*     */   
/* 159 */   public void setCommunicationIp(String communicationIp) { this.communicationIp = communicationIp; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("通讯端口")
/*     */   public Integer getCommunicationPort()
/*     */   {
/* 168 */     return this.communicationPort;
/*     */   }
/*     */   
/* 171 */   public void setCommunicationPort(Integer communicationPort) { this.communicationPort = communicationPort; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("联系电话")
/*     */   public String getContactMobile()
/*     */   {
/* 180 */     return this.contactMobile;
/*     */   }
/*     */   
/* 183 */   public void setContactMobile(String contactMobile) { this.contactMobile = contactMobile; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("公司名称")
/*     */   public String getCorporateName()
/*     */   {
/* 192 */     return this.corporateName;
/*     */   }
/*     */   
/* 195 */   public void setCorporateName(String corporateName) { this.corporateName = corporateName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("1 简体中文 ，2 英语")
/*     */   public String getDefaultLanguage()
/*     */   {
/* 204 */     return this.defaultLanguage;
/*     */   }
/*     */   
/* 207 */   public void setDefaultLanguage(String defaultLanguage) { this.defaultLanguage = defaultLanguage; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("app初始化图片时间（单位s）")
/*     */   public Integer getImgTime()
/*     */   {
/* 216 */     return this.imgTime;
/*     */   }
/*     */   
/* 219 */   public void setImgTime(Integer imgTime) { this.imgTime = imgTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否需要认证 true 需要，false 不需要")
/*     */   public Boolean getIsNeedAuthen()
/*     */   {
/* 228 */     return this.isNeedAuthen;
/*     */   }
/*     */   
/* 231 */   public void setIsNeedAuthen(Boolean isNeedAuthen) { this.isNeedAuthen = isNeedAuthen; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否需要上传证件  true 需要，false 不需要")
/*     */   public Boolean getIsNeedUpCertificates()
/*     */   {
/* 240 */     return this.isNeedUpCertificates;
/*     */   }
/*     */   
/* 243 */   public void setIsNeedUpCertificates(Boolean isNeedUpCertificates) { this.isNeedUpCertificates = isNeedUpCertificates; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否开放广告")
/*     */   public Boolean getIsOpenAd()
/*     */   {
/* 252 */     return this.isOpenAd;
/*     */   }
/*     */   
/* 255 */   public void setIsOpenAd(Boolean isOpenAd) { this.isOpenAd = isOpenAd; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否开放余额支付 true 开放，false 不开放")
/*     */   public Boolean getIsOpenBalance()
/*     */   {
/* 264 */     return this.isOpenBalance;
/*     */   }
/*     */   
/* 267 */   public void setIsOpenBalance(Boolean isOpenBalance) { this.isOpenBalance = isOpenBalance; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否开启电池业务 true 开启，false 关闭")
/*     */   public Boolean getIsOpenBattery()
/*     */   {
/* 276 */     return this.isOpenBattery;
/*     */   }
/*     */   
/* 279 */   public void setIsOpenBattery(Boolean isOpenBattery) { this.isOpenBattery = isOpenBattery; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否开启单车业务 true 开启，false 关闭")
/*     */   public Boolean getIsOpenBicycle()
/*     */   {
/* 288 */     return this.isOpenBicycle;
/*     */   }
/*     */   
/* 291 */   public void setIsOpenBicycle(Boolean isOpenBicycle) { this.isOpenBicycle = isOpenBicycle; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否开放会员卡体系  true开放，false 不开放")
/*     */   public Boolean getIsOpenMemberCard()
/*     */   {
/* 300 */     return this.isOpenMemberCard;
/*     */   }
/*     */   
/* 303 */   public void setIsOpenMemberCard(Boolean isOpenMemberCard) { this.isOpenMemberCard = isOpenMemberCard; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("行程是否支付 true 支付，false 不支付")
/*     */   public Boolean getIsPayTrip()
/*     */   {
/* 312 */     return this.isPayTrip;
/*     */   }
/*     */   
/* 315 */   public void setIsPayTrip(Boolean isPayTrip) { this.isPayTrip = isPayTrip; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("是否是 定点停车")
/*     */   public Boolean getIsSpotParking()
/*     */   {
/* 324 */     return this.isSpotParking;
/*     */   }
/*     */   
/* 327 */   public void setIsSpotParking(Boolean isSpotParking) { this.isSpotParking = isSpotParking; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("用户是否注册  true 用户注册 ， false 后台注册")
/*     */   public Boolean getIsUserRegister()
/*     */   {
/* 336 */     return this.isUserRegister;
/*     */   }
/*     */   
/* 339 */   public void setIsUserRegister(Boolean isUserRegister) { this.isUserRegister = isUserRegister; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("logo 地址")
/*     */   public String getLogoImg()
/*     */   {
/* 348 */     return this.logoImg;
/*     */   }
/*     */   
/* 351 */   public void setLogoImg(String logoImg) { this.logoImg = logoImg; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("1 微信 ，2 支付宝  ， 1,2 微信和支付宝")
/*     */   public String getPayWay()
/*     */   {
/* 360 */     return this.payWay;
/*     */   }
/*     */   
/* 363 */   public void setPayWay(String payWay) { this.payWay = payWay; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("一天预约次数限制")
/*     */   public Integer getReserveNum()
/*     */   {
/* 372 */     return this.reserveNum;
/*     */   }
/*     */   
/* 375 */   public void setReserveNum(Integer reserveNum) { this.reserveNum = reserveNum; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("预约时间/单位分钟")
/*     */   public Integer getReserveTime()
/*     */   {
/* 384 */     return this.reserveTime;
/*     */   }
/*     */   
/* 387 */   public void setReserveTime(Integer reserveTime) { this.reserveTime = reserveTime; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 394 */     if (this == o) {
/* 395 */       return true;
/*     */     }
/* 397 */     if ((o == null) || (getClass() != o.getClass())) {
/* 398 */       return false;
/*     */     }
/* 400 */     SysSettingBO sysSettingBO = (SysSettingBO)o;
/* 401 */     return (Objects.equals(this.appDownloadUrl, sysSettingBO.appDownloadUrl)) && 
/* 402 */       (Objects.equals(this.appName, sysSettingBO.appName)) && 
/* 403 */       (Objects.equals(this.appNameEnglish, sysSettingBO.appNameEnglish)) && 
/* 404 */       (Objects.equals(this.authenType, sysSettingBO.authenType)) && 
/* 405 */       (Objects.equals(this.cardDesc, sysSettingBO.cardDesc)) && 
/* 406 */       (Objects.equals(this.communicationIp, sysSettingBO.communicationIp)) && 
/* 407 */       (Objects.equals(this.communicationPort, sysSettingBO.communicationPort)) && 
/* 408 */       (Objects.equals(this.contactMobile, sysSettingBO.contactMobile)) && 
/* 409 */       (Objects.equals(this.corporateName, sysSettingBO.corporateName)) && 
/* 410 */       (Objects.equals(this.defaultLanguage, sysSettingBO.defaultLanguage)) && 
/* 411 */       (Objects.equals(this.imgTime, sysSettingBO.imgTime)) && 
/* 412 */       (Objects.equals(this.isNeedAuthen, sysSettingBO.isNeedAuthen)) && 
/* 413 */       (Objects.equals(this.isNeedUpCertificates, sysSettingBO.isNeedUpCertificates)) && 
/* 414 */       (Objects.equals(this.isOpenAd, sysSettingBO.isOpenAd)) && 
/* 415 */       (Objects.equals(this.isOpenBalance, sysSettingBO.isOpenBalance)) && 
/* 416 */       (Objects.equals(this.isOpenBattery, sysSettingBO.isOpenBattery)) && 
/* 417 */       (Objects.equals(this.isOpenBicycle, sysSettingBO.isOpenBicycle)) && 
/* 418 */       (Objects.equals(this.isOpenMemberCard, sysSettingBO.isOpenMemberCard)) && 
/* 419 */       (Objects.equals(this.isPayTrip, sysSettingBO.isPayTrip)) && 
/* 420 */       (Objects.equals(this.isSpotParking, sysSettingBO.isSpotParking)) && 
/* 421 */       (Objects.equals(this.isUserRegister, sysSettingBO.isUserRegister)) && 
/* 422 */       (Objects.equals(this.logoImg, sysSettingBO.logoImg)) && 
/* 423 */       (Objects.equals(this.payWay, sysSettingBO.payWay)) && 
/* 424 */       (Objects.equals(this.reserveNum, sysSettingBO.reserveNum)) && 
/* 425 */       (Objects.equals(this.reserveTime, sysSettingBO.reserveTime));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 430 */     return Objects.hash(new Object[] { this.appDownloadUrl, this.appName, this.appNameEnglish, this.authenType, this.cardDesc, this.communicationIp, this.communicationPort, this.contactMobile, this.corporateName, this.defaultLanguage, this.imgTime, this.isNeedAuthen, this.isNeedUpCertificates, this.isOpenAd, this.isOpenBalance, this.isOpenBattery, this.isOpenBicycle, this.isOpenMemberCard, this.isPayTrip, this.isSpotParking, this.isUserRegister, this.logoImg, this.payWay, this.reserveNum, this.reserveTime });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 435 */     StringBuilder sb = new StringBuilder();
/* 436 */     sb.append("class SysSettingBO {\n");
/*     */     
/* 438 */     sb.append("    appDownloadUrl: ").append(toIndentedString(this.appDownloadUrl)).append("\n");
/* 439 */     sb.append("    appName: ").append(toIndentedString(this.appName)).append("\n");
/* 440 */     sb.append("    appNameEnglish: ").append(toIndentedString(this.appNameEnglish)).append("\n");
/* 441 */     sb.append("    authenType: ").append(toIndentedString(this.authenType)).append("\n");
/* 442 */     sb.append("    cardDesc: ").append(toIndentedString(this.cardDesc)).append("\n");
/* 443 */     sb.append("    communicationIp: ").append(toIndentedString(this.communicationIp)).append("\n");
/* 444 */     sb.append("    communicationPort: ").append(toIndentedString(this.communicationPort)).append("\n");
/* 445 */     sb.append("    contactMobile: ").append(toIndentedString(this.contactMobile)).append("\n");
/* 446 */     sb.append("    corporateName: ").append(toIndentedString(this.corporateName)).append("\n");
/* 447 */     sb.append("    defaultLanguage: ").append(toIndentedString(this.defaultLanguage)).append("\n");
/* 448 */     sb.append("    imgTime: ").append(toIndentedString(this.imgTime)).append("\n");
/* 449 */     sb.append("    isNeedAuthen: ").append(toIndentedString(this.isNeedAuthen)).append("\n");
/* 450 */     sb.append("    isNeedUpCertificates: ").append(toIndentedString(this.isNeedUpCertificates)).append("\n");
/* 451 */     sb.append("    isOpenAd: ").append(toIndentedString(this.isOpenAd)).append("\n");
/* 452 */     sb.append("    isOpenBalance: ").append(toIndentedString(this.isOpenBalance)).append("\n");
/* 453 */     sb.append("    isOpenBattery: ").append(toIndentedString(this.isOpenBattery)).append("\n");
/* 454 */     sb.append("    isOpenBicycle: ").append(toIndentedString(this.isOpenBicycle)).append("\n");
/* 455 */     sb.append("    isOpenMemberCard: ").append(toIndentedString(this.isOpenMemberCard)).append("\n");
/* 456 */     sb.append("    isPayTrip: ").append(toIndentedString(this.isPayTrip)).append("\n");
/* 457 */     sb.append("    isSpotParking: ").append(toIndentedString(this.isSpotParking)).append("\n");
/* 458 */     sb.append("    isUserRegister: ").append(toIndentedString(this.isUserRegister)).append("\n");
/* 459 */     sb.append("    logoImg: ").append(toIndentedString(this.logoImg)).append("\n");
/* 460 */     sb.append("    payWay: ").append(toIndentedString(this.payWay)).append("\n");
/* 461 */     sb.append("    reserveNum: ").append(toIndentedString(this.reserveNum)).append("\n");
/* 462 */     sb.append("    reserveTime: ").append(toIndentedString(this.reserveTime)).append("\n");
/* 463 */     sb.append("}");
/* 464 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 472 */     if (o == null) {
/* 473 */       return "null";
/*     */     }
/* 475 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\SysSettingBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */