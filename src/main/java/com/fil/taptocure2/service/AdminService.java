package com.fil.taptocure2.service;
import com.fil.taptocure2.model.Admin;
import com.fil.taptocure2.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AdminService  {

    public List<Admin> getAllAdmins();

    public Optional<Admin> getAdminById(long adminId);

    public Admin saveAdmin(Admin admin);

    public void deleteAdmin(long adminId);
}

