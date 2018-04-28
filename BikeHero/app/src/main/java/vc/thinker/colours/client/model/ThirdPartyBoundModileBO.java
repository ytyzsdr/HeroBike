/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class ThirdPartyBoundModileBO
/*    */ {
/*    */   @SerializedName("isExistModile")
/* 15 */   private Boolean isExistModile = null;
/*    */   
/*    */   @SerializedName("loginName")
/* 18 */   private String loginName = null;
/*    */   
/*    */   @SerializedName("token")
/* 21 */   private String token = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Boolean getIsExistModile()
/*    */   {
/* 30 */     return this.isExistModile;
/*    */   }
/*    */   
/* 33 */   public void setIsExistModile(Boolean isExistModile) { this.isExistModile = isExistModile; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getLoginName()
/*    */   {
/* 41 */     return this.loginName;
/*    */   }
/*    */   
/* 44 */   public void setLoginName(String loginName) { this.loginName = loginName; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getToken()
/*    */   {
/* 52 */     return this.token;
/*    */   }
/*    */   
/* 55 */   public void setToken(String token) { this.token = token; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 62 */     if (this == o) {
/* 63 */       return true;
/*    */     }
/* 65 */     if ((o == null) || (getClass() != o.getClass())) {
/* 66 */       return false;
/*    */     }
/* 68 */     ThirdPartyBoundModileBO thirdPartyBoundModileBO = (ThirdPartyBoundModileBO)o;
/* 69 */     return (Objects.equals(this.isExistModile, thirdPartyBoundModileBO.isExistModile)) && 
/* 70 */       (Objects.equals(this.loginName, thirdPartyBoundModileBO.loginName)) && 
/* 71 */       (Objects.equals(this.token, thirdPartyBoundModileBO.token));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 76 */     return Objects.hash(new Object[] { this.isExistModile, this.loginName, this.token });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 81 */     StringBuilder sb = new StringBuilder();
/* 82 */     sb.append("class ThirdPartyBoundModileBO {\n");
/*    */     
/* 84 */     sb.append("    isExistModile: ").append(toIndentedString(this.isExistModile)).append("\n");
/* 85 */     sb.append("    loginName: ").append(toIndentedString(this.loginName)).append("\n");
/* 86 */     sb.append("    token: ").append(toIndentedString(this.token)).append("\n");
/* 87 */     sb.append("}");
/* 88 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 96 */     if (o == null) {
/* 97 */       return "null";
/*    */     }
/* 99 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ThirdPartyBoundModileBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */