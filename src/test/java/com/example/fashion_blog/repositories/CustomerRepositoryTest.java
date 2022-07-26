package com.example.fashion_blog.repositories;



import com.example.fashion_blog.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Test
    void findByEmail() {
        // given
        Customer customer = new Customer();
        customer.setFirstName("Bee");
        customer.setLastName("Honey");
        customer.setEmail("bee@gmail.com");
        customer.setPassword("12345");
        underTest.save(customer);
        //when
        Optional<Customer> dbCustomer = underTest.findByEmail("bee@gmail.com");
        //then
        if (dbCustomer.isPresent()) {
            assertThat(dbCustomer.get()).isEqualTo(customer);
        }
        else{
            assertThat(dbCustomer.get()).isEqualTo(null);
        }
    }

    @Test
    void findByEmailAndPassword() {
        //given
        Customer customer = new Customer();
        customer.setFirstName("Bee");
        customer.setLastName("Honey");
        customer.setEmail("bee@gmail.com");
        customer.setPassword("12345");
        underTest.save(customer);
        //when
        Optional<Customer> dbCustomer = underTest.findByEmailAndPassword("bee@gmail.com", "12345");
        //then
        if (dbCustomer.isPresent()){
            assertThat(dbCustomer.get()).isEqualTo(customer);
        }else{
            assertThat(dbCustomer).isEqualTo(null);
        }
    }
}