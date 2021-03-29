package ru.itis.restoke.controllers;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@Controller
@RequestMapping("/photos")
public class PhotosController  {

    @GetMapping("{photo_name}")
    public ResponseEntity<byte[]> doGet(@PathVariable("photo_name") String photoName) {

        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("photos/" + photoName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        try {
            while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] response = out.toByteArray();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
