/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.mfs.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VuMin
 */
@Entity
@Table(name = "categories_type", catalog = "finalfresherfilesharing", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriesType.findAll", query = "SELECT c FROM CategoriesType c"),
    @NamedQuery(name = "CategoriesType.findById", query = "SELECT c FROM CategoriesType c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriesType.findByFileType", query = "SELECT c FROM CategoriesType c WHERE c.fileType = :fileType")})
public class CategoriesType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "file_type", nullable = false, length = 100)
    private String fileType;
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Categories categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType")
    private Collection<Files> filesCollection;

    public CategoriesType() {
    }

    public CategoriesType(Integer id) {
        this.id = id;
    }

    public CategoriesType(Integer id, String fileType) {
        this.id = id;
        this.fileType = fileType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public Collection<Files> getFilesCollection() {
        return filesCollection;
    }

    public void setFilesCollection(Collection<Files> filesCollection) {
        this.filesCollection = filesCollection;
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
        if (!(object instanceof CategoriesType)) {
            return false;
        }
        CategoriesType other = (CategoriesType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.CategoriesType[ id=" + id + " ]";
    }
    
}
