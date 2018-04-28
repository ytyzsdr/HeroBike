/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class FeedbackVO
/*     */ {
/*     */   @SerializedName("bluetoothConnection")
/*  15 */   private Integer bluetoothConnection = null;
/*     */   
/*     */   @SerializedName("feedDesc")
/*  18 */   private String feedDesc = null;
/*     */   
/*     */   @SerializedName("imgUrl1")
/*  21 */   private String imgUrl1 = null;
/*     */   
/*     */   @SerializedName("imgUrl2")
/*  24 */   private String imgUrl2 = null;
/*     */   
/*     */   @SerializedName("imgUrl3")
/*  27 */   private String imgUrl3 = null;
/*     */   
/*     */   @SerializedName("imgUrl4")
/*  30 */   private String imgUrl4 = null;
/*     */   
/*     */   @SerializedName("lockOnoff")
/*  33 */   private Boolean lockOnoff = null;
/*     */   
/*     */   @SerializedName("sysCode")
/*  36 */   private String sysCode = null;
/*     */   
/*     */   @SerializedName("tripId")
/*  39 */   private Long tripId = null;
/*     */   
/*     */   @SerializedName("typeId")
/*  42 */   private String typeId = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("蓝牙连接状态 1:正常 2:异常  3:超时")
/*     */   public Integer getBluetoothConnection()
/*     */   {
/*  52 */     return this.bluetoothConnection;
/*     */   }
/*     */   
/*  55 */   public void setBluetoothConnection(Integer bluetoothConnection) { this.bluetoothConnection = bluetoothConnection; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("问题表述")
/*     */   public String getFeedDesc()
/*     */   {
/*  64 */     return this.feedDesc;
/*     */   }
/*     */   
/*  67 */   public void setFeedDesc(String feedDesc) { this.feedDesc = feedDesc; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl1()
/*     */   {
/*  75 */     return this.imgUrl1;
/*     */   }
/*     */   
/*  78 */   public void setImgUrl1(String imgUrl1) { this.imgUrl1 = imgUrl1; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl2()
/*     */   {
/*  86 */     return this.imgUrl2;
/*     */   }
/*     */   
/*  89 */   public void setImgUrl2(String imgUrl2) { this.imgUrl2 = imgUrl2; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl3()
/*     */   {
/*  97 */     return this.imgUrl3;
/*     */   }
/*     */   
/* 100 */   public void setImgUrl3(String imgUrl3) { this.imgUrl3 = imgUrl3; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public String getImgUrl4()
/*     */   {
/* 108 */     return this.imgUrl4;
/*     */   }
/*     */   
/* 111 */   public void setImgUrl4(String imgUrl4) { this.imgUrl4 = imgUrl4; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Boolean getLockOnoff()
/*     */   {
/* 119 */     return this.lockOnoff;
/*     */   }
/*     */   
/* 122 */   public void setLockOnoff(Boolean lockOnoff) { this.lockOnoff = lockOnoff; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("车编号")
/*     */   public String getSysCode()
/*     */   {
/* 131 */     return this.sysCode;
/*     */   }
/*     */   
/* 134 */   public void setSysCode(String sysCode) { this.sysCode = sysCode; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   //("")
/*     */   public Long getTripId()
/*     */   {
/* 142 */     return this.tripId;
/*     */   }
/*     */   
/* 145 */   public void setTripId(Long tripId) { this.tripId = tripId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   //("问题类型id")
/*     */   public String getTypeId()
/*     */   {
/* 154 */     return this.typeId;
/*     */   }
/*     */   
/* 157 */   public void setTypeId(String typeId) { this.typeId = typeId; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 164 */     if (this == o) {
/* 165 */       return true;
/*     */     }
/* 167 */     if ((o == null) || (getClass() != o.getClass())) {
/* 168 */       return false;
/*     */     }
/* 170 */     FeedbackVO feedbackVO = (FeedbackVO)o;
/* 171 */     return (Objects.equals(this.bluetoothConnection, feedbackVO.bluetoothConnection)) && 
/* 172 */       (Objects.equals(this.feedDesc, feedbackVO.feedDesc)) && 
/* 173 */       (Objects.equals(this.imgUrl1, feedbackVO.imgUrl1)) && 
/* 174 */       (Objects.equals(this.imgUrl2, feedbackVO.imgUrl2)) && 
/* 175 */       (Objects.equals(this.imgUrl3, feedbackVO.imgUrl3)) && 
/* 176 */       (Objects.equals(this.imgUrl4, feedbackVO.imgUrl4)) && 
/* 177 */       (Objects.equals(this.lockOnoff, feedbackVO.lockOnoff)) && 
/* 178 */       (Objects.equals(this.sysCode, feedbackVO.sysCode)) && 
/* 179 */       (Objects.equals(this.tripId, feedbackVO.tripId)) && 
/* 180 */       (Objects.equals(this.typeId, feedbackVO.typeId));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 185 */     return Objects.hash(new Object[] { this.bluetoothConnection, this.feedDesc, this.imgUrl1, this.imgUrl2, this.imgUrl3, this.imgUrl4, this.lockOnoff, this.sysCode, this.tripId, this.typeId });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 190 */     StringBuilder sb = new StringBuilder();
/* 191 */     sb.append("class FeedbackVO {\n");
/*     */     
/* 193 */     sb.append("    bluetoothConnection: ").append(toIndentedString(this.bluetoothConnection)).append("\n");
/* 194 */     sb.append("    feedDesc: ").append(toIndentedString(this.feedDesc)).append("\n");
/* 195 */     sb.append("    imgUrl1: ").append(toIndentedString(this.imgUrl1)).append("\n");
/* 196 */     sb.append("    imgUrl2: ").append(toIndentedString(this.imgUrl2)).append("\n");
/* 197 */     sb.append("    imgUrl3: ").append(toIndentedString(this.imgUrl3)).append("\n");
/* 198 */     sb.append("    imgUrl4: ").append(toIndentedString(this.imgUrl4)).append("\n");
/* 199 */     sb.append("    lockOnoff: ").append(toIndentedString(this.lockOnoff)).append("\n");
/* 200 */     sb.append("    sysCode: ").append(toIndentedString(this.sysCode)).append("\n");
/* 201 */     sb.append("    tripId: ").append(toIndentedString(this.tripId)).append("\n");
/* 202 */     sb.append("    typeId: ").append(toIndentedString(this.typeId)).append("\n");
/* 203 */     sb.append("}");
/* 204 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 212 */     if (o == null) {
/* 213 */       return "null";
/*     */     }
/* 215 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\FeedbackVO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */