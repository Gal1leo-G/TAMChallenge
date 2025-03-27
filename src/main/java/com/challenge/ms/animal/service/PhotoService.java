package com.challenge.ms.animal.service;


import com.challenge.ms.animal.repository.PhotoRepository;
import com.challenge.ms.animal.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    @Autowired
    private JdbcClient jdbcClient;

    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    public Photo getById(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo saveImage(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        return photoRepository.save(photo);
    }
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public byte[] getLastPhoto() {
        String sql="select Data from Photos where ID in (select max(ID) from Photos)";
        JdbcClient.StatementSpec stSpec = jdbcClient.sql(sql);
        JdbcClient.ResultQuerySpec ob = stSpec.query();
        List<Map<String, Object>> list = ob.listOfRows();
        byte[] imgBytes = (byte[]) list.getFirst().get("Data");
        return imgBytes;
    }


}
