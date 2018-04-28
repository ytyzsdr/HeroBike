/*     */ package vc.thinker.colours.client.model;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
/*     */ public class ModelAndView
/*     */ {
/*     */   @SerializedName("empty")
/*  18 */   private Boolean empty = null;
/*     */   
/*     */   @SerializedName("model")
/*  21 */   private Object model = null;
/*     */   
/*     */   @SerializedName("modelMap")
/*  24 */   private Map<String, Object> modelMap = new HashMap();
/*     */   
/*     */   @SerializedName("reference")
/*  27 */   private Boolean reference = null;
/*     */   
/*     */   @SerializedName("view")
/*  30 */   private View view = null;
/*     */   
/*     */   @SerializedName("viewName")
/*  33 */   private String viewName = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Boolean getEmpty()
/*     */   {
/*  42 */     return this.empty;
/*     */   }
/*     */   
/*  45 */   public void setEmpty(Boolean empty) { this.empty = empty; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Object getModel()
/*     */   {
/*  53 */     return this.model;
/*     */   }
/*     */   
/*  56 */   public void setModel(Object model) { this.model = model; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Map<String, Object> getModelMap()
/*     */   {
/*  64 */     return this.modelMap;
/*     */   }
/*     */   
/*  67 */   public void setModelMap(Map<String, Object> modelMap) { this.modelMap = modelMap; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public Boolean getReference()
/*     */   {
/*  75 */     return this.reference;
/*     */   }
/*     */   
/*  78 */   public void setReference(Boolean reference) { this.reference = reference; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public View getView()
/*     */   {
/*  86 */     return this.view;
/*     */   }
/*     */   
/*  89 */   public void setView(View view) { this.view = view; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */  //("")
/*     */   public String getViewName()
/*     */   {
/*  97 */     return this.viewName;
/*     */   }
/*     */   
/* 100 */   public void setViewName(String viewName) { this.viewName = viewName; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 107 */     if (this == o) {
/* 108 */       return true;
/*     */     }
/* 110 */     if ((o == null) || (getClass() != o.getClass())) {
/* 111 */       return false;
/*     */     }
/* 113 */     ModelAndView modelAndView = (ModelAndView)o;
/* 114 */     return (Objects.equals(this.empty, modelAndView.empty)) && 
/* 115 */       (Objects.equals(this.model, modelAndView.model)) && 
/* 116 */       (Objects.equals(this.modelMap, modelAndView.modelMap)) && 
/* 117 */       (Objects.equals(this.reference, modelAndView.reference)) && 
/* 118 */       (Objects.equals(this.view, modelAndView.view)) && 
/* 119 */       (Objects.equals(this.viewName, modelAndView.viewName));
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 124 */     return Objects.hash(new Object[] { this.empty, this.model, this.modelMap, this.reference, this.view, this.viewName });
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 129 */     StringBuilder sb = new StringBuilder();
/* 130 */     sb.append("class ModelAndView {\n");
/*     */     
/* 132 */     sb.append("    empty: ").append(toIndentedString(this.empty)).append("\n");
/* 133 */     sb.append("    model: ").append(toIndentedString(this.model)).append("\n");
/* 134 */     sb.append("    modelMap: ").append(toIndentedString(this.modelMap)).append("\n");
/* 135 */     sb.append("    reference: ").append(toIndentedString(this.reference)).append("\n");
/* 136 */     sb.append("    view: ").append(toIndentedString(this.view)).append("\n");
/* 137 */     sb.append("    viewName: ").append(toIndentedString(this.viewName)).append("\n");
/* 138 */     sb.append("}");
/* 139 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String toIndentedString(Object o)
/*     */   {
/* 147 */     if (o == null) {
/* 148 */       return "null";
/*     */     }
/* 150 */     return o.toString().replace("\n", "\n    ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\android-sdk-client-1.0.jar!\vc\thinker\colours\client\model\ModelAndView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */