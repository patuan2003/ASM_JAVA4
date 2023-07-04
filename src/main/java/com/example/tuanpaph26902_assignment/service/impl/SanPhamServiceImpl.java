package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.SanPham;
import com.example.tuanpaph26902_assignment.repository.SanPhamRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class SanPhamServiceImpl implements Service<SanPham> {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public SanPham getOne(UUID id) {
        return sanPhamRepository.getOne(id);
    }

    @Override
    public Boolean add(SanPham sp) {
        return sanPhamRepository.save(sp);
    }

    @Override
    public Boolean update(SanPham sp) {
        return sanPhamRepository.update(sp);
    }

    @Override
    public Boolean delete(SanPham sp) {
        return sanPhamRepository.delete(sp);
    }

    @Override
    public SanPham getByMa(String ma) {
        return sanPhamRepository.getByMa(ma);
    }

    @Override
    public String validate(SanPham sanPham) {
        String error = null;
        if (sanPham.getMa().trim().length() == 0
                || sanPham.getTen().trim().length() == 0) {
            error = "Không đươc để trống";
        }
        SanPham check = getByMa(sanPham.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
