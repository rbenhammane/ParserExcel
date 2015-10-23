package model.inwi;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;

@Entity
@Table(name = "V_SRMS_ACQUISITION_SELECTED_INWI")
public class AdminsitrativeSTCW implements Serializable
{

   @Id
   @Column(name = "NOM_SITE_INST_ID")
   private String nomSiteInstId;
   @Column(name = "NOM_SITE_HUM_ID")
   private String nomSiteHumId;
   @Column(name = "SITE_INST_ID")
   private String siteInstId;
   @Column(name = "NGO_SRMS_INST_ID")
   private String ngoSrmsInstId;
   @Transient
   private String constructReqDepositDate;
   @Transient
   private String constructReqObtentionDate;
   @Transient
   private String connectionReqDepositDate;
   @Transient
   private String connectionReqObtentionDate;
   @Column(name = "TODO")
   private String cwResponsible;
   @Column(name = "TODO")
   private String cwResponsiblePhone;
   @Column(name = "TODO")
   private String cwResponsibleEmail;
   @Transient
   private String adminSTCW;
   @Transient
   private String adminSTCWPhone;
   @Transient
   private String adminSTCWEmail;

   public String getNomSiteInstId()
   {
      return nomSiteInstId;
   }

   public void setNomSiteInstId(String nomSiteInstId)
   {
      this.nomSiteInstId = nomSiteInstId;
   }

   public String getNomSiteHumId()
   {
      return nomSiteHumId;
   }

   public void setNomSiteHumId(String nomSiteHumId)
   {
      this.nomSiteHumId = nomSiteHumId;
   }

   public String getSiteInstId()
   {
      return siteInstId;
   }

   public void setSiteInstId(String siteInstId)
   {
      this.siteInstId = siteInstId;
   }

   public String getNgoSrmsInstId()
   {
      return ngoSrmsInstId;
   }

   public void setNgoSrmsInstId(String ngoSrmsInstId)
   {
      this.ngoSrmsInstId = ngoSrmsInstId;
   }

   public String getConstructReqDepositDate()
   {
      return constructReqDepositDate;
   }

   public void setConstructReqDepositDate(String constructReqDepositDate)
   {
      this.constructReqDepositDate = constructReqDepositDate;
   }

   public String getConstructReqObtentionDate()
   {
      return constructReqObtentionDate;
   }

   public void setConstructReqObtentionDate(String constructReqObtentionDate)
   {
      this.constructReqObtentionDate = constructReqObtentionDate;
   }

   public String getConnectionReqDepositDate()
   {
      return connectionReqDepositDate;
   }

   public void setConnectionReqDepositDate(String connectionReqDepositDate)
   {
      this.connectionReqDepositDate = connectionReqDepositDate;
   }

   public String getConnectionReqObtentionDate()
   {
      return connectionReqObtentionDate;
   }

   public void setConnectionReqObtentionDate(String connectionReqObtentionDate)
   {
      this.connectionReqObtentionDate = connectionReqObtentionDate;
   }

   public String getCwResponsible()
   {
      return cwResponsible;
   }

   public void setCwResponsible(String cwResponsible)
   {
      this.cwResponsible = cwResponsible;
   }

   public String getCwResponsiblePhone()
   {
      return cwResponsiblePhone;
   }

   public void setCwResponsiblePhone(String cwResponsiblePhone)
   {
      this.cwResponsiblePhone = cwResponsiblePhone;
   }

   public String getCwResponsibleEmail()
   {
      return cwResponsibleEmail;
   }

   public void setCwResponsibleEmail(String cwResponsibleEmail)
   {
      this.cwResponsibleEmail = cwResponsibleEmail;
   }

   public String getAdminSTCW()
   {
      return adminSTCW;
   }

   public void setAdminSTCW(String adminSTCW)
   {
      this.adminSTCW = adminSTCW;
   }

   public String getAdminSTCWPhone()
   {
      return adminSTCWPhone;
   }

   public void setAdminSTCWPhone(String adminSTCWPhone)
   {
      this.adminSTCWPhone = adminSTCWPhone;
   }

   public String getAdminSTCWEmail()
   {
      return adminSTCWEmail;
   }

   public void setAdminSTCWEmail(String adminSTCWEmail)
   {
      this.adminSTCWEmail = adminSTCWEmail;
   }
}
