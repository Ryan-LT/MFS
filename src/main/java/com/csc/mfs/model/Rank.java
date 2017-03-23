/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.mfs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author training
 */
@Entity
@Table(name = "rank", catalog = "finalfresherfilesharing", schema = "")
public class Rank implements Serializable, Comparable<Rank> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sizeupload", precision = 22)
    private Double sizeupload;
    @Column(name = "sizedownload", precision = 22)
    private Double sizedownload;
    @Column(name = "sizerank", precision = 22)
    private Double sizerank;
    @OneToMany(mappedBy = "rankId")
    @JsonIgnore
    private List<User> userList;

    public Rank() {
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

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        return "com.csc.mfs.model.Rank[ id=" + id + " ]";
    }

	@Override
	public int compareTo(Rank rank) {
		if(rank.getSizerank()==sizerank){
			return 0;	
		} else if(rank.getSizerank()<sizerank){
			return 1;
		} else {
			return -1;
		}
		
	}
    
}
