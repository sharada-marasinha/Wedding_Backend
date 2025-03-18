package edu.lmu.service;

import edu.lmu.entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
     List<Vendor> getAllVendors();
     Optional<Vendor> getVendorById(Integer id);
     Vendor createVendor(Vendor vendor);
     Vendor updateVendor(Integer id, Vendor updatedVendor);
     void deleteVendor(Integer id);
}
