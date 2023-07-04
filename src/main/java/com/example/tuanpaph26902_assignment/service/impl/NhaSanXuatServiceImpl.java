package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.NhaSanXuat;
import com.example.tuanpaph26902_assignment.repository.NhaSanXuatRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class NhaSanXuatServiceImpl implements Service<NhaSanXuat> {

    private NhaSanXuatRepository nhaSanXuatRepository;

    public NhaSanXuatServiceImpl() {
        nhaSanXuatRepository = new NhaSanXuatRepository();
    }

    @Override
    public List<NhaSanXuat> getAll() {
        return nhaSanXuatRepository.getAll();
    }

    @Override
    public NhaSanXuat getOne(UUID id) {
        return nhaSanXuatRepository.getOne(id);
    }

    @Override
    public Boolean add(NhaSanXuat nhaSanXuat) {
        return nhaSanXuatRepository.add(nhaSanXuat);
    }

    @Override
    public Boolean update(NhaSanXuat nhaSanXuat) {
        return nhaSanXuatRepository.update(nhaSanXuat);
    }

    @Override
    public Boolean delete(NhaSanXuat nhaSanXuat) {
        return nhaSanXuatRepository.delete(nhaSanXuat);
    }

    @Override
    public NhaSanXuat getByMa(String ma) {
        return nhaSanXuatRepository.getById(ma);
    }

    @Override
    public String validate(NhaSanXuat nhaSanXuat) {
        String error = null;
        if (nhaSanXuat.getMa().trim().length() == 0
                || nhaSanXuat.getTen().trim().length() == 0) {
            error = "Không được để trống";
        }
        NhaSanXuat check = getByMa(nhaSanXuat.getMa());
        if (check != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
