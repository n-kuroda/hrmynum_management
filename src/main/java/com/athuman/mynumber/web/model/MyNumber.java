package com.athuman.mynumber.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "MYNUMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MyNumber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HIMODUKE_NO", nullable = false, length = 200)
	private String himodukeNo;

	@Column(name = "SHODAKU_FLAG", length = 1, nullable = false)
	private String shodakuFlag;

	@Column(name = "MITEIKYO_RIYU1", length = 1, nullable = false)
	private String miteikyoRiyu1;

	@Column(name = "MITEIKYO_RIYU2", length = 1, nullable = false)
	private String miteikyoRiyu2;

	@Column(name = "MITEIKYO_RIYU3", length = 1, nullable = false)
	private String miteikyoRiyu3;

	@Column(name = "MITEIKYO_RIYU4", length = 1, nullable = false)
	private String miteikyoRiyu4;

	@Column(name = "STAFF_MYNUMBER", length = 100)
	private String staffMyNumber;

	@Column(name = "FUYO1_MYNUMBER", length = 100)
	private String fuyo1MyNumber;

	@Column(name = "FUYO2_MYNUMBER", length = 100)
	private String fuyo2MyNumber;

	@Column(name = "FUYO3_MYNUMBER", length = 100)
	private String fuyo3MyNumber;

	@Column(name = "FUYO4_MYNUMBER", length = 100)
	private String fuyo4MyNumber;

	@Column(name = "FUYO5_MYNUMBER", length = 100)
	private String fuyo5MyNumber;

	@Column(name = "FUYO6_MYNUMBER", length = 100)
	private String fuyo6MyNumber;

	@Column(name = "FUYO7_MYNUMBER", length = 100)
	private String fuyo7MyNumber;

	@Column(name = "FUYO8_MYNUMBER", length = 100)
	private String fuyo8MyNumber;

	@Column(name = "FUYO9_MYNUMBER", length = 100)
	private String fuyo9MyNumber;

	@Column(name = "FUYO10_MYNUMBER", length = 100)
	private String fuyo10MyNumber;

	@Column(name = "FUYO11_MYNUMBER", length = 100)
	private String fuyo11MyNumber;

	@Column(name = "FUYO12_MYNUMBER", length = 100)
	private String fuyo12MyNumber;

	@Column(name = "FUYO13_MYNUMBER", length = 100)
	private String fuyo13MyNumber;

	@Column(name = "FUYO14_MYNUMBER", length = 100)
	private String fuyo14MyNumber;

	@Column(name = "FUYO15_MYNUMBER", length = 100)
	private String fuyo15MyNumber;

	@Column(name = "FUYO16_MYNUMBER", length = 100)
	private String fuyo16MyNumber;

	@Column(name = "FUYO17_MYNUMBER", length = 100)
	private String fuyo17MyNumber;

	@Column(name = "FUYO18_MYNUMBER", length = 100)
	private String fuyo18MyNumber;

	@Column(name = "FUYO19_MYNUMBER", length = 100)
	private String fuyo19MyNumber;

	@Column(name = "FUYO20_MYNUMBER", length = 100)
	private String fuyo20MyNumber;

	@Column(name = "MYNUMBER_KAKUNIN_TEIJI_SHORUI", nullable = false, length = 2)
	private String myNumberKakuninTeijiShorui;

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

	@Column(name = "KENKO_HOKENSHASHO", nullable = false, length = 1)
	private String kenkoHokenshasho;

	@Column(name = "NENKIN_TECHO", nullable = false, length = 1)
	private String nenkonTecho;

	@Column(name = "SONOTA", nullable = false, length = 1)
	private String sonota;

	@Column(name = "HONNIN_SYOMEI", nullable = false)
	// BLOB
	@Lob
	private byte[] honninSyomei;

	@Column(name = "BUSINESS_UPDATE_TIMESTAMP", nullable = false)
	private Timestamp businessUpdateTimeStamp;

	@Column(name = "KAKUNINSHA", nullable = false, length = 6)
	private String kakuninsha;

	@Column(name = "TOROKU_USER", nullable = false, length = 6)
	private String torokuUser;

	@Column(name = "TOROKU_TIMESTAMP", nullable = false)
	private Timestamp torokuTimestamp;

	@Column(name = "LAST_UPDATE_USER", nullable = false, length = 6)
	private String lastUpdateUser;

	@Column(name = "LAST_UPDATE_TIMESTAMP", nullable = false)
	private Timestamp lastUpdateTimeStamp;

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

	public String getShodakuFlag() {
		return shodakuFlag;
	}

	public void setShodakuFlag(String shodakuFlag) {
		this.shodakuFlag = shodakuFlag;
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

	public String getFuyo11MyNumber() {
		return fuyo11MyNumber;
	}

	public void setFuyo11MyNumber(String fuyo11MyNumber) {
		this.fuyo11MyNumber = fuyo11MyNumber;
	}

	public String getFuyo12MyNumber() {
		return fuyo12MyNumber;
	}

	public void setFuyo12MyNumber(String fuyo12MyNumber) {
		this.fuyo12MyNumber = fuyo12MyNumber;
	}

	public String getFuyo13MyNumber() {
		return fuyo13MyNumber;
	}

	public void setFuyo13MyNumber(String fuyo13MyNumber) {
		this.fuyo13MyNumber = fuyo13MyNumber;
	}

	public String getFuyo14MyNumber() {
		return fuyo14MyNumber;
	}

	public void setFuyo14MyNumber(String fuyo14MyNumber) {
		this.fuyo14MyNumber = fuyo14MyNumber;
	}

	public String getFuyo15MyNumber() {
		return fuyo15MyNumber;
	}

	public void setFuyo15MyNumber(String fuyo15MyNumber) {
		this.fuyo15MyNumber = fuyo15MyNumber;
	}

	public String getFuyo16MyNumber() {
		return fuyo16MyNumber;
	}

	public void setFuyo16MyNumber(String fuyo16MyNumber) {
		this.fuyo16MyNumber = fuyo16MyNumber;
	}

	public String getFuyo17MyNumber() {
		return fuyo17MyNumber;
	}

	public void setFuyo17MyNumber(String fuyo17MyNumber) {
		this.fuyo17MyNumber = fuyo17MyNumber;
	}

	public String getFuyo18MyNumber() {
		return fuyo18MyNumber;
	}

	public void setFuyo18MyNumber(String fuyo18MyNumber) {
		this.fuyo18MyNumber = fuyo18MyNumber;
	}

	public String getFuyo19MyNumber() {
		return fuyo19MyNumber;
	}

	public void setFuyo19MyNumber(String fuyo19MyNumber) {
		this.fuyo19MyNumber = fuyo19MyNumber;
	}

	public String getFuyo20MyNumber() {
		return fuyo20MyNumber;
	}

	public void setFuyo20MyNumber(String fuyo20MyNumber) {
		this.fuyo20MyNumber = fuyo20MyNumber;
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

	public byte[] getHonninSyomei() {
		return honninSyomei;
	}

	public void setHonninSyomei(byte[] honninSyomei) {
		this.honninSyomei = honninSyomei;
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

	public Timestamp getTorokuTimestamp() {
		return torokuTimestamp;
	}

	public void setTorokuTimestamp(Timestamp torokuTimestamp) {
		this.torokuTimestamp = torokuTimestamp;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Timestamp getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(Timestamp lastUpdateTimeStamp) {
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

	public String getMiteikyoRiyu1() {
		return miteikyoRiyu1;
	}

	public void setMiteikyoRiyu1(String miteikyoRiyu1) {
		this.miteikyoRiyu1 = miteikyoRiyu1;
	}

	public String getMiteikyoRiyu2() {
		return miteikyoRiyu2;
	}

	public void setMiteikyoRiyu2(String miteikyoRiyu2) {
		this.miteikyoRiyu2 = miteikyoRiyu2;
	}

	public String getMiteikyoRiyu3() {
		return miteikyoRiyu3;
	}

	public void setMiteikyoRiyu3(String miteikyoRiyu3) {
		this.miteikyoRiyu3 = miteikyoRiyu3;
	}

	public String getMiteikyoRiyu4() {
		return miteikyoRiyu4;
	}

	public void setMiteikyoRiyu4(String miteikyoRiyu4) {
		this.miteikyoRiyu4 = miteikyoRiyu4;
	}

	public String getMyNumberKakuninTeijiShorui() {
		return myNumberKakuninTeijiShorui;
	}

	public void setMyNumberKakuninTeijiShorui(String myNumberKakuninTeijiShorui) {
		this.myNumberKakuninTeijiShorui = myNumberKakuninTeijiShorui;
	}

	public Timestamp getBusinessUpdateTimeStamp() {
		return businessUpdateTimeStamp;
	}

	public void setBusinessUpdateTimeStamp(Timestamp businessUpdateTimeStamp) {
		this.businessUpdateTimeStamp = businessUpdateTimeStamp;
	}

}