package com.arken.suppliers.web.model;

public class SuppliersBean 
{

private String updateflag,description,model,qty,units,unitprice,totalprice,insunitprice,instotalprice;
	
	private int projectid,quoteid,sno,supplierid,poid;
	
	public int getSupplierid() {
		return supplierid;
	}

	public int getPoid() {
		return poid;
	}

	public void setPoid(int poid) {
		this.poid = poid;
	}

	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getUpdateflag() {
		return updateflag;
	}

	public void setUpdateflag(String updateflag) {
		this.updateflag = updateflag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getInsunitprice() {
		return insunitprice;
	}

	public void setInsunitprice(String insunitprice) {
		this.insunitprice = insunitprice;
	}

	public String getInstotalprice() {
		return instotalprice;
	}

	public void setInstotalprice(String instotalprice) {
		this.instotalprice = instotalprice;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public int getQuoteid() {
		return quoteid;
	}

	public void setQuoteid(int quoteid) {
		this.quoteid = quoteid;
	}
}
