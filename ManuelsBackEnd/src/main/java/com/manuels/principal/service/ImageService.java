package com.manuels.principal.service;

import com.manuels.principal.dao.IImageDao;
import com.manuels.principal.models.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageDao imageDao;

    @Override
    public List<Image> listImages() {
        return imageDao.findAll();
    }

    @Override
    public Image create(Image image) {
        return imageDao.save(image);
    }

    @Override
    public void delete(Image image) {
        imageDao.delete(image);
    }

    @Override
    public Image update(Image image) {
        Image existingImage = imageDao.findById(image.getIdImage()).orElse(null);

        existingImage.setName(image.getName());
        existingImage.setType(image.getType());
        existingImage.setBytes(image.getBytes());

        return imageDao.save(existingImage);
    }

    @Override
    public Image find(Long idImage) {
        return imageDao.findById(idImage).orElse(null);
    }

    // compress the image bytes before storing it in the database
    public byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        
        while (!deflater.finished()) {
            
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public byte[] decompressBytes(byte[] data) {
        
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        
        try {
            while (!inflater.finished()) {
                
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        }catch (IOException ioe) {
        }catch (DataFormatException e) {
        }
        
        return outputStream.toByteArray();
    }
    
    @Override
    public List<Image> findByName(String imageName) {
        
        return imageDao.findByName(imageName);
    }
}