package org.bdickele.ambiguous.service;

import org.bdickele.ambiguous.domain.Customer;
import org.bdickele.ambiguous.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerService extends AbstractService {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(propagation = Propagation.REQUIRED)
    public Customer create(String fullName, String creationUser) {
        String uid = userService.generateUid(fullName);
        Customer customer = Customer.build(null, uid, fullName, creationUser, passwordEncoder);
        em().persist(customer);
        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer update(String uid, String fullName, String updateUser) {
        // UID cannot be updated : we use it to retrieve current customer
        Customer customer = repository.findByUid(uid);
        customer.update(fullName, updateUser);
        return customer;
    }
}
