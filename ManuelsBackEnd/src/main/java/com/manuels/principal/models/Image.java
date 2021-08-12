package com.manuels.principal.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@JsonInclude
@Data
@Entity
@Table(name = "image")
public class Image implements Serializable {
          
    private static final long serialVersionUID = 1L;
    
    public Image(){
        super();
    }
    
    public Image(String name, String type, byte[] bytes) {
         
        this.name = name;
        this.type = type;
        this.bytes = bytes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;
            
    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "bytes")
    private byte[] bytes;
}