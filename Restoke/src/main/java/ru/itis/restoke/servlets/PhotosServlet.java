package ru.itis.restoke.servlets;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@WebServlet(urlPatterns = {"/photos/*"})
public class PhotosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        String encryptedPhotoRef = url.substring(url.lastIndexOf("/") + 1);
        String decryptedPhotoRef = encryptedPhotoRef.replace("%20", " ");

        final FileInputStream photoStream = new FileInputStream(decryptedPhotoRef);
        IOUtils.copy(photoStream, resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
