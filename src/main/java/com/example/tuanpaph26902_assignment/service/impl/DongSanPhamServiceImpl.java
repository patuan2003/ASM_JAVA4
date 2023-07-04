package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.DongSanPham;
import com.example.tuanpaph26902_assignment.repository.DongSanPhamRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class DongSanPhamServiceImpl implements Service<DongSanPham> {

    private DongSanPhamRepository dongSanPhamRepository;

    public DongSanPhamServiceImpl() {
        dongSanPhamRepository = new DongSanPhamRepository();
    }

    @Override
    public List<DongSanPham> getAll() {
        return dongSanPhamRepository.getAll();
    }

    @Override
    public DongSanPham getOne(UUID id) {
        return dongSanPhamRepository.getOne(id);
    }

    @Override
    public Boolean add(DongSanPham dongSanPham) {
        return dongSanPhamRepository.add(dongSanPham);
    }

    @Override
    public Boolean update(DongSanPham dongSanPham) {
        return dongSanPhamRepository.update(dongSanPham);
    }

    @Override
    public Boolean delete(DongSanPham dongSanPham) {
        return dongSanPhamRepository.delete(dongSanPham);
    }

    @Override
    public DongSanPham getByMa(String ma) {
        return dongSanPhamRepository.getByMa(ma);
    }

    @Override
    public String validate(DongSanPham dongSanPham) {
        String error = null;
        if (dongSanPham.getMa().trim().length() == 0
                || dongSanPham.getTen().trim().length() == 0) {
            error = "Không được để trống";
        }

        DongSanPham check = getByMa(dongSanPham.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
