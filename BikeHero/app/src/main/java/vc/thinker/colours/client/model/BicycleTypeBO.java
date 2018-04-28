/*    */ package vc.thinker.colours.client.model;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */
/*    */ public class BicycleTypeBO
/*    */ {
/*    */   @SerializedName("id")
/* 15 */   private Long id = null;
/*    */   
/*    */   @SerializedName("typeName")
/* 18 */   private String typeName = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public Long getId()
/*    */   {
/* 27 */     return this.id;
/*    */   }
/*    */   
/* 30 */   public void setId(Long id) { this.id = id; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   //("")
/*    */   public String getTypeName()
/*    */   {
/* 38 */     return this.typeName;
/*    */   }
/*    */   
/* 41 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 48 */     if (this == o) {
/* 49 */       return true;
/*    */     }
/* 51 */     if ((o == null) || (getClass() != o.getClass())) {
/* 52 */       return false;
/*    */     }
/* 54 */     BicycleTypeBO bicycleTypeBO = (BicycleTypeBO)o;
/* 55 */     return (Objects.equals(this.id, bicycleTypeBO.id)) && 
/* 56 */       (Objects.equals(this.typeName, bicycleTypeBO.typeName));
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 61 */     return Objects.hash(new Object[] { this.id, this.typeName });
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     StringBuilder sb = new StringBuilder();
/* 67 */     sb.append("class BicycleTypeBO {\n");
/*    */     
/* 69 */     sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
/* 70 */     sb.append("    typeName: ").append(toIndentedString(this.typeName)).append("\n");
/* 71 */     sb.append("}");
/* 72 */     return sb.toString();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private String toIndentedString(Object o)
/*    */   {
/* 80 */     if (o == null) {
/* 81 */       return "null";
/*    */     }
/* 83 */     return o.toString().replace("\n", "\n    ");
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\BicycleTypeBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */