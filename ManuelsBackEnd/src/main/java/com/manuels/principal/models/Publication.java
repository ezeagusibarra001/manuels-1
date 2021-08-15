package com.manuels.principal.models;

import java.io.Serializable;
import javax.persistence.CascadeType;

import com.manuels.principal.models.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Entity
@Table(name = "publication")
public class Publication implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publication")
    private Long idPublication;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "description1")
    private String description1;
    
    @Column(name = "description2")
    private String description2;
    
    @Column(name = "description3")
    private String description3;
    
    @Column(name = "subtitle")
    private String subtitle;
    
    @Column(name = "subtitle2")
    private String subtitle2;
    
    @Transient
    private MultipartFile file;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_img", nullable = true)
    private Image image;
}