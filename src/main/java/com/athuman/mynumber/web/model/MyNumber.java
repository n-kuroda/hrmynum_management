package com.athuman.mynumber.web.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="MYNUMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MyNumber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HIMODUKE_NO", nullable = false, length = 36)
	private String himodukeNo;
	
	@Column(name = "STAFF_MYNUMBER", nullable = false, length = 12)
	private String staffMyNumber;
	
	@Column(name = "FUYO1_MYNUMBER", length = 12)
	private String fuyo1MyNumber;
	
	@Column(name = "FUYO2_MYNUMBER", length = 12)
	private String fuyo2MyNumber;
	
	@Column(name = "FUYO3_MYNUMBER", length = 12)
	private String fuyo3MyNumber;
	
	@Column(name = "FUYO4_MYNUMBER", length = 12)
	private String fuyo4MyNumber;
	
	@Column(name = "FUYO5_MYNUMBER", length = 12)
	private String fuyo5MyNumber;
	
	@Column(name = "FUYO6_MYNUMBER", length = 12)
	private String fuyo6MyNumber;
	
	@Column(name = "FUYO7_MYNUMBER", length = 12)
	private String fuyo7MyNumber;
	
	@Column(name = "FUYO8_MYNUMBER", length = 12)
	private String fuyo8MyNumber;
	
	@Column(name = "FUYO9_MYNUMBER", length = 12)
	private String fuyo9MyNumber;
	
	@Column(name = "FUYO10_MYNUMBER", length = 12)
	private String fuyo10MyNumber;
	
	@Column(name = "MYNUMBER_KAKUNINSHORUI", nullable = false, length = 2)
	private String myNumberKakuninshorui;
	
	@Column(name = "UNTEN_MENKYOSHO", nullable = false, length = 1)
	private String untenMenkyyosho;
	
	@Column(name = "UNTEN_KEIREKI_SHOUMEISHO", nullable = false, length = 1)
	private String untenKeirekiShoumeisho;
	
	@Column(name = "PASSPORT", nullable = false, length = 1)
	private String passport;
	
	@Column(name = "SHINTAI_SHOGAISHA_TECHO", nullable = false, length = 1)
	private String shintaiShogaishaTecho;
	
	@Column(name = "SEISHIN_SHOGAISHA_TECHO", nullable = false, length = 1)
	private String seishinShogaishaTecho;
	
	@Column(name = "RYOIKU_TECHO", nullable = false, length = 1)
	private String ryoikuTecho;
	
	@Column(name = "ZAIRYU_CARD", nullable = false, length = 1)
	private String zairyuCard;
	
	@Column(name = "HONNIN_AKIRAKA", nullable = false, length = 1)
	private String honninAkiraka;
	
	@Column(name = "KENKO_HOKENSHASHO", nullable = false, length = 1)
	private String kenkoHokenshasho;
	
	@Column(name = "NENKIN_TECHO", nullable = false, length = 1)
	private String nenkonTecho;
	
	@Column(name = "SONOTA", nullable = false, length = 1)
	private String sonota;
	
	@Column(name = "HONNIN_SYOMEI", nullable = false)//   BLOB
	@Lob
	private Blob honninSyomei;
	
	@Column(name = "KAKUNINSHA", nullable = false, length = 6)
	private String kakuninsha;
	
	@Column(name = "TOROKU_USER", nullable = false, length = 6)
	private String torokuUser;
	
//	@Column(name = "TOROKU_TIMESTAMP", nullable = false)
//	private Date torokuTimestamp;
	
	@Column(name = "LAST_UPDATE_USER", nullable = false, length = 6)
	private String lastUpdateUser;
	
	@Column(name = "LAST_UPDATE_TIMESTAMP", nullable = false)
	private Date lastUpdateTimeStamp;
	
	@Column(name = "DELETE_FLAG", nullable = false, length = 1)
	private String deleteFlag;
	
	@Column(name = "DELETE_USER", length = 6)
	private String deleteUser;
	
	@Column(name = "DELETE_TIMESTAMP")
	private Date deleteTimeStamp;

	public String getHimodukeNo() {
		return himodukeNo;
	}

	public void setHimodukeNo(String himodukeNo) {
		this.himodukeNo = himodukeNo;
	}

	public String getStaffMyNumber() {
		return staffMyNumber;
	}

	public void setStaffMyNumber(String staffMyNumber) {
		this.staffMyNumber = staffMyNumber;
	}

	public String getFuyo1MyNumber() {
		return fuyo1MyNumber;
	}

	public void setFuyo1MyNumber(String fuyo1MyNumber) {
		this.fuyo1MyNumber = fuyo1MyNumber;
	}

	public String getFuyo2MyNumber() {
		return fuyo2MyNumber;
	}

	public void setFuyo2MyNumber(String fuyo2MyNumber) {
		this.fuyo2MyNumber = fuyo2MyNumber;
	}

	public String getFuyo3MyNumber() {
		return fuyo3MyNumber;
	}

	public void setFuyo3MyNumber(String fuyo3MyNumber) {
		this.fuyo3MyNumber = fuyo3MyNumber;
	}

	public String getFuyo4MyNumber() {
		return fuyo4MyNumber;
	}

	public void setFuyo4MyNumber(String fuyo4MyNumber) {
		this.fuyo4MyNumber = fuyo4MyNumber;
	}

	public String getFuyo5MyNumber() {
		return fuyo5MyNumber;
	}

	public void setFuyo5MyNumber(String fuyo5MyNumber) {
		this.fuyo5MyNumber = fuyo5MyNumber;
	}

	public String getFuyo6MyNumber() {
		return fuyo6MyNumber;
	}

	public void setFuyo6MyNumber(String fuyo6MyNumber) {
		this.fuyo6MyNumber = fuyo6MyNumber;
	}

	public String getFuyo7MyNumber() {
		return fuyo7MyNumber;
	}

	public void setFuyo7MyNumber(String fuyo7MyNumber) {
		this.fuyo7MyNumber = fuyo7MyNumber;
	}

	public String getFuyo8MyNumber() {
		return fuyo8MyNumber;
	}

	public void setFuyo8MyNumber(String fuyo8MyNumber) {
		this.fuyo8MyNumber = fuyo8MyNumber;
	}

	public String getFuyo9MyNumber() {
		return fuyo9MyNumber;
	}

	public void setFuyo9MyNumber(String fuyo9MyNumber) {
		this.fuyo9MyNumber = fuyo9MyNumber;
	}

	public String getFuyo10MyNumber() {
		return fuyo10MyNumber;
	}

	public void setFuyo10MyNumber(String fuyo10MyNumber) {
		this.fuyo10MyNumber = fuyo10MyNumber;
	}

	public String getMyNumberKakuninshorui() {
		return myNumberKakuninshorui;
	}

	public void setMyNumberKakuninshorui(String myNumberKakuninshorui) {
		this.myNumberKakuninshorui = myNumberKakuninshorui;
	}

	public String getUntenMenkyyosho() {
		return untenMenkyyosho;
	}

	public void setUntenMenkyyosho(String untenMenkyyosho) {
		this.untenMenkyyosho = untenMenkyyosho;
	}

	public String getUntenKeirekiShoumeisho() {
		return untenKeirekiShoumeisho;
	}

	public void setUntenKeirekiShoumeisho(String untenKeirekiShoumeisho) {
		this.untenKeirekiShoumeisho = untenKeirekiShoumeisho;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getShintaiShogaishaTecho() {
		return shintaiShogaishaTecho;
	}

	public void setShintaiShogaishaTecho(String shintaiShogaishaTecho) {
		this.shintaiShogaishaTecho = shintaiShogaishaTecho;
	}

	public String getSeishinShogaishaTecho() {
		return seishinShogaishaTecho;
	}

	public void setSeishinShogaishaTecho(String seishinShogaishaTecho) {
		this.seishinShogaishaTecho = seishinShogaishaTecho;
	}

	public String getRyoikuTecho() {
		return ryoikuTecho;
	}

	public void setRyoikuTecho(String ryoikuTecho) {
		this.ryoikuTecho = ryoikuTecho;
	}

	public String getZairyuCard() {
		return zairyuCard;
	}

	public void setZairyuCard(String zairyuCard) {
		this.zairyuCard = zairyuCard;
	}

	public String getHonninAkiraka() {
		return honninAkiraka;
	}

	public void setHonninAkiraka(String honninAkiraka) {
		this.honninAkiraka = honninAkiraka;
	}

	public String getKenkoHokenshasho() {
		return kenkoHokenshasho;
	}

	public void setKenkoHokenshasho(String kenkoHokenshasho) {
		this.kenkoHokenshasho = kenkoHokenshasho;
	}

	public String getNenkonTecho() {
		return nenkonTecho;
	}

	public void setNenkonTecho(String nenkonTecho) {
		this.nenkonTecho = nenkonTecho;
	}

	public String getSonota() {
		return sonota;
	}

	public void setSonota(String sonota) {
		this.sonota = sonota;
	}

	public String getKakuninsha() {
		return kakuninsha;
	}

	public void setKakuninsha(String kakuninsha) {
		this.kakuninsha = kakuninsha;
	}

	public String getTorokuUser() {
		return torokuUser;
	}

	public void setTorokuUser(String torokuUser) {
		this.torokuUser = torokuUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(Date lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Date getDeleteTimeStamp() {
		return deleteTimeStamp;
	}

	public void setDeleteTimeStamp(Date deleteTimeStamp) {
		this.deleteTimeStamp = deleteTimeStamp;
	}

	public Blob getHonninSyomei() {
		return honninSyomei;
	}

	public void setHonninSyomei(Blob honninSyomei) {
		this.honninSyomei = honninSyomei;
	}

//	public Date getTorokuTimestamp() {
//		return torokuTimestamp;
//	}
//
//	public void setTorokuTimestamp(Date torokuTimestamp) {
//		this.torokuTimestamp = torokuTimestamp;
//	}

}
