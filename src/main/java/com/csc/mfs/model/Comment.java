/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.mfs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author training
 */
@Entity
@Table(name = "comment", catalog = "finalfresherfilesharing", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id")
    , @NamedQuery(name = "Comment.findByLikeComment", query = "SELECT c FROM Comment c WHERE c.likeComment = :likeComment")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content", length = 2147483647)
    private String content;
    
    @Column(name = "likecomment")
    private Integer likeComment;
    
    @Column(name="id_file")
    private Integer idFile;
    
    @Column(name="id_user")
    private Integer idUser;
    
    @Column(name="datecomment")
    @Temporal(TemporalType.DATE)
    private Date datecomment;
    
    public Comment(String content, int likeComment, int idUser, int idFile, Date dateComment) {
    	this.content =content;
    	this.likeComment = likeComment;
    	this.idUser =idUser;
    	this.idFile = idFile;
    	this.datecomment = dateComment;
    }
    
    public Comment(){
    	
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeComment() {
		return likeComment;
	}

	public void setLikeComment(Integer likeComment) {
		this.likeComment = likeComment;
	}

	public Integer getIdFile() {
		return idFile;
	}

	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Date getDatecomment() {
		return datecomment;
	}

	public void setDatecomment(Date datecomment) {
		this.datecomment = datecomment;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csc.mfs.domain.Comment[ id=" + id + " ]";
    }
    
}
