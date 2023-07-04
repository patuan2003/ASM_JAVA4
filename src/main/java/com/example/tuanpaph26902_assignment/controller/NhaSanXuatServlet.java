package com.example.tuanpaph26902_assignment.controller;

import com.example.tuanpaph26902_assignment.entity.NhaSanXuat;
import com.example.tuanpaph26902_assignment.service.Service;
import com.example.tuanpaph26902_assignment.service.impl.NhaSanXuatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NSXController", value = {
        "/nsx/hien-thi",  // GET
        "/nsx/add",      // POST
        "/nsx/update",      // POST
        "/nsx/delete",    // GET
        "/nsx/view-update",// GET
        "/nsx/view-add", // GET
        "/nsx/detail"    // GET
})
public class NhaSanXuatServlet extends HttpServlet {
    private Service<NhaSanXuat> service = new NhaSanXuatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThi(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.save(request, response);
        }
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nsxs", service.getAll());
        request.getRequestDispatcher("/view/nsx/nsx.jsp").forward(request, response);
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NhaSanXuat nhaSanXuat = new NhaSanXuat();
        try {
            BeanUtils.populate(nhaSanXuat, request.getParameterMap());
            String valiate = service.validate(nhaSanXuat);
            if (valiate != null) {
                request.setAttribute("errorNSX", valiate);
                this.hienThi(request, response);
                return;
            }
            service.add(nhaSanXuat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nsx/hien-thi");
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhaSanXuat nhaSanXuat = this.service.getOne(UUID.fromString(id));
        request.setAttribute("nsx", nhaSanXuat);
        request.setAttribute("nsxs", service.getAll());
        request.getRequestDispatcher("/view/nsx/nsx.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(service.getOne(UUID.fromString(id)));
        response.sendRedirect("/nsx/hien-thi");
    }

    private UUID idUpdate = null;

    protected void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NhaSanXuat nhaSanXuat = service.getOne(UUID.fromString(id));
        request.setAttribute("nsx", nhaSanXuat);
        request.getRequestDispatcher("/view/nsx/update-nsx.jsp").forward(request, response);
        idUpdate = UUID.fromString(id);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NhaSanXuat nhaSanXuat = new NhaSanXuat();
        nhaSanXuat.setId(idUpdate);
        try {
            BeanUtils.populate(nhaSanXuat, request.getParameterMap());
            service.update(nhaSanXuat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nsx/hien-thi");
    }
}
