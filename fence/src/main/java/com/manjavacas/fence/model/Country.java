package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "countries")
public class Country {

	@Id
	private ObjectId _id;

	private String name;
	private int pdi, idv, mas, uai, ltowvs, ivr;

	public Country() {
	}

	public Country(String name, int pdi, int idv, int mas, int uai, int ltowvs, int ivr) {
		super();
		this.name = name;
		this.pdi = pdi;
		this.idv = idv;
		this.mas = mas;
		this.uai = uai;
		this.ltowvs = ltowvs;
		this.ivr = ivr;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPdi() {
		return pdi;
	}

	public void setPdi(int pdi) {
		this.pdi = pdi;
	}

	public int getIdv() {
		return idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public int getMas() {
		return mas;
	}

	public void setMas(int mas) {
		this.mas = mas;
	}

	public int getUai() {
		return uai;
	}

	public void setUai(int uai) {
		this.uai = uai;
	}

	public int getLtowvs() {
		return ltowvs;
	}

	public void setLtowvs(int ltowvs) {
		this.ltowvs = ltowvs;
	}

	public int getIvr() {
		return ivr;
	}

	public void setIvr(int ivr) {
		this.ivr = ivr;
	}

}
