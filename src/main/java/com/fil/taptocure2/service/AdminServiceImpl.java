
package com.fil.taptocure2.service;

import com.fil.taptocure2.model.Admin;
import com.fil.taptocure2.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private AdminRepository adminRepository;

	@Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
	@Override
    public Optional<Admin> getAdminById(long adminId) {
        return adminRepository.findById(adminId);
    } 
    
    @Override
    public Admin saveAdmin(Admin admin) {
    	if(adminRepository.count() > 0) {
            throw new IllegalStateException("An admin already exists.");
        }
        return adminRepository.save(admin);
    }
    
	@Override
    public void deleteAdmin(long adminId) {
        adminRepository.deleteById(adminId);
    }

    
}
