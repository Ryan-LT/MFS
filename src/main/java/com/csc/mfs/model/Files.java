/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.mfs.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author VuMin
 */
@Entity
@Table(name = "files", catalog = "finalfresherfilesharing", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f"),
    @NamedQuery(name = "Files.findById", query = "SELECT f FROM Files f WHERE f.id = :id"),
    @NamedQuery(name = "Files.findByName", query = "SELECT f FROM Files f WHERE f.name = :name"),
    @NamedQuery(name = "Files.findByPath", query = "SELECT f FROM Files f WHERE f.path = :path"),
    @NamedQuery(name = "Files.findBySize", query = "SELECT f FROM Files f WHERE f.size = :size"),
    @NamedQuery(name = "Files.findByDateupload", query = "SELECT f FROM Files f WHERE f.dateupload = :dateupload")})
public class Files implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "path", length = 45)
    private String path;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "size", precision = 22)
    private Double size;
    @Column(name = "dateupload")
    @Temporal(TemporalType.DATE)
    private Date dateupload;
    @OneToMany(mappedBy = "idFile")
    @JsonIgnore
    private Collection<Download> downloadCollection;
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @ManyToOne(	cascade = {CascadeType.PERSIST})
//    @JsonIgnore
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "active")
    private int active;
    
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ManyToOne(optional = false)
    private CategoriesType idType;

    public Files() {
    }
    
    public Files(String name, String path, double size, int userId, Date dateUpload, 
    		CategoriesType type){
    	this.name = name;
    	this.path = path;
    	this.size = size;
    	this.userId = userId;
    	this.dateupload = dateUpload;
    	this.idType = type;
    }

    public Files(Integer id) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

	public Date getDateupload() {
		return dateupload;
	}

	public void setDateupload(Date dateupload) {
		this.dateupload = dateupload;
	}

	@XmlTransient
    public Collection<Download> getDownloadCollection() {
        return downloadCollection;
    }

    public void setDownloadCollection(Collection<Download> downloadCollection) {
        this.downloadCollection = downloadCollection;
    }


    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public CategoriesType getIdType() {
		return idType;
	}

	public void setIdType(CategoriesType idType) {
		this.idType = idType;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Files[ id=" + id + " ]";
    }
    
}
