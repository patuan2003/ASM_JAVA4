package com.example.tuanpaph26902_assignment.service.impl;

import com.example.tuanpaph26902_assignment.entity.ChucVu;
import com.example.tuanpaph26902_assignment.repository.ChucVuRepository;
import com.example.tuanpaph26902_assignment.service.Service;

import java.util.List;
import java.util.UUID;

public class ChucVuServiceImpl implements Service<ChucVu> {
    private ChucVuRepository chucVuRepository;

    public ChucVuServiceImpl() {
        chucVuRepository = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.getAll();
    }

    @Override
    public ChucVu getOne(UUID id) {
        return chucVuRepository.getOne(id);
    }

    @Override
    public Boolean add(ChucVu chucVu) {
        return chucVuRepository.add(chucVu);
    }

    @Override
    public Boolean update(ChucVu chucVu) {
        return chucVuRepository.update(chucVu);
    }

    @Override
    public Boolean delete(ChucVu chucVu) {
        return chucVuRepository.delete(chucVu);
    }

    @Override
    public ChucVu getByMa(String ma) {
        return chucVuRepository.getByMa(ma);
    }

    @Override
    public String validate(ChucVu chucVu) {
        String error = null;
        if (chucVu.getMa().trim().length() == 0
                || chucVu.getTen().trim().length() == 0) {
            error = "Không được để trống";
        }

        ChucVu checkMa = getByMa(chucVu.getMa());
        if (checkMa != null) {
            error = "Mã đã tồn tại";
        }
        return error;
    }
}
