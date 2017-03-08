/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.mfs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VuMin
 */
@Entity
@Table(name = "rank", catalog = "finalfresherfilesharing", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rank.findAll", query = "SELECT r FROM Rank r"),
    @NamedQuery(name = "Rank.findById", query = "SELECT r FROM Rank r WHERE r.id = :id"),
    @NamedQuery(name = "Rank.findByName", query = "SELECT r FROM Rank r WHERE r.name = :name"),
    @NamedQuery(name = "Rank.findBySizeupload", query = "SELECT r FROM Rank r WHERE r.sizeupload = :sizeupload"),
    @NamedQuery(name = "Rank.findBySizedownload", query = "SELECT r FROM Rank r WHERE r.sizedownload = :sizedownload"),
    @NamedQuery(name = "Rank.findBySizerank", query = "SELECT r FROM Rank r WHERE r.sizerank = :sizerank")})
public class Rank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 100)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sizeupload", precision = 22)
    private Double sizeupload;
    @Column(name = "sizedownload", precision = 22)
    private Double sizedownload;
    @Column(name = "sizerank", precision = 22)
    private Double sizerank;

    public Rank() {
    }
    
    public Rank(String name, double sizeupload, double sizedownload, double sizerank) {
    	this.name = name;
    	this.sizeupload = sizeupload;
    	this.sizedownload = sizedownload;
    	this.sizerank = sizerank;
    }

    public Rank(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Double getSizeupload() {
		return sizeupload;
	}

	public void setSizeupload(Double sizeupload) {
		this.sizeupload = sizeupload;
	}

	public Double getSizedownload() {
		return sizedownload;
	}

	public void setSizedownload(Double sizedownload) {
		this.sizedownload = sizedownload;
	}

	public Double getSizerank() {
		return sizerank;
	}

	public void setSizerank(Double sizerank) {
		this.sizerank = sizerank;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rank)) {
            return false;
        }
        Rank other = (Rank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Rank[ id=" + id + " ]";
    }
    
}
