package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.CuaHang;
import com.example.tuanpaph26902_assignment.repository.CuaHangRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class CuaHangServiceImpl implements Service<CuaHang> {

    private CuaHangRepository cuaHangRepository;

    public CuaHangServiceImpl() {
        cuaHangRepository = new CuaHangRepository();
    }

    @Override
    public List<CuaHang> getAll() {
        return cuaHangRepository.getAll();
    }

    @Override
    public CuaHang getOne(UUID id) {
        return cuaHangRepository.getOne(id);
    }

    @Override
    public Boolean add(CuaHang cuaHang) {
        return cuaHangRepository.add(cuaHang);
    }

    @Override
    public Boolean update(CuaHang cuaHang) {
        return cuaHangRepository.update(cuaHang);
    }

    @Override
    public Boolean delete(CuaHang cuaHang) {
        return cuaHangRepository.delete(cuaHang);
    }

    @Override
    public CuaHang getByMa(String ma) {
        return cuaHangRepository.getByMa(ma);
    }

    @Override
    public String validate(CuaHang cuaHang) {
        String error = null;
        if (cuaHang.getMa().trim().length() == 0
                || cuaHang.getDiaChi().trim().length() == 0
                || cuaHang.getTen().trim().length() == 0
                || cuaHang.getThanhPho().trim().length() == 0) {
            error = "Không được để trống";
        }
        CuaHang check = getByMa(cuaHang.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
