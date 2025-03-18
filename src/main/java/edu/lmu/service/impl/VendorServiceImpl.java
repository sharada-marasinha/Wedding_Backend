package edu.lmu.service.impl;

import edu.lmu.entity.Vendor;
import edu.lmu.repository.VendorRepository;
import edu.lmu.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(Integer id) {
        return vendorRepository.findById(id);
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor updateVendor(Integer id, Vendor updatedVendor) {
        Optional<Vendor> existingVendor = vendorRepository.findById(id);
        if (existingVendor.isPresent()) {
            updatedVendor.setVendorId(id);
            return vendorRepository.save(updatedVendor);
        }
        return null; // Or throw an exception
    }

    public void deleteVendor(Integer id) {
        vendorRepository.deleteById(id);
    }
}
