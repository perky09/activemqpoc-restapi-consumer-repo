package com.genworth.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.genworth.demo.exception.CustomedException;
import com.genworth.demo.model.Address;
import com.genworth.demo.model.Customer;
import com.genworth.demo.model.Occupation;
import com.genworth.demo.repository.AddressRepository;
import com.genworth.demo.repository.CustomerRepository;
import com.genworth.demo.repository.OccupationRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OccupationRepository occupationRepository;

	public List<Customer> createAllCustomer(List<Customer> customer) {
		List<Customer> allCustomer = new ArrayList<>();
		List<Occupation> occupation = new ArrayList<>();
		
		for (Customer customerToBeAdded : customer) {
			Customer customerTobeIncluded = new Customer();
			Address addressOfCustomer = new Address();
			addressOfCustomer.setCity(customerToBeAdded.getAddress().getCity());
			addressOfCustomer.setLine1(customerToBeAdded.getAddress().getLine1());
			addressOfCustomer.setLine2(customerToBeAdded.getAddress().getLine2());
			addressOfCustomer.setState(customerToBeAdded.getAddress().getState());
			addressOfCustomer.setZip(customerToBeAdded.getAddress().getZip());
			for (Occupation oc : customerToBeAdded.getOccupation()) {
				Occupation addOccupationInCustomer = new Occupation();
				Address address = new Address();
				addOccupationInCustomer.setEarnings(oc.getEarnings());
				addOccupationInCustomer.setType(oc.getType());
				address.setCity(oc.getJobAddress().getCity());
				address.setLine1(oc.getJobAddress().getLine1());
				address.setLine2(oc.getJobAddress().getLine2());
				address.setState(oc.getJobAddress().getState());
				address.setZip(oc.getJobAddress().getZip());
				occupation.add(addOccupationInCustomer);
			}
			customerTobeIncluded.setAddress(addressOfCustomer);
			customerTobeIncluded.setOccupation(occupation);	
			customerTobeIncluded.setName(customerToBeAdded.getName());
			allCustomer.add(customerTobeIncluded);
		}		
		return customerRepository.saveAll(allCustomer);

	}

	@Override
	public Customer createCustomer(Customer customer) {

		for (Occupation oc : customer.getOccupation()) {
			occupationRepository.save(oc);
		}

		addressRepository.save(customer.getAddress());
		return customerRepository.save(customer);

	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Optional<Customer> customerFromDb = this.customerRepository.findById(customer.getId());

		if (customerFromDb.isPresent()) {

			Customer customerToUpdate = customerFromDb.get();
			customerToUpdate.setId(customer.getId());
			customerToUpdate.setName(customer.getName());
			customerToUpdate.setAddress(customer.getAddress());
			customerToUpdate.getOccupation().clear();
			customerToUpdate.getOccupation().addAll(customer.getOccupation());
			for (Occupation oc : customer.getOccupation()) {
				occupationRepository.save(oc);
			}

			addressRepository.save(customerToUpdate.getAddress());
			customerRepository.save(customerToUpdate);
			return customerToUpdate;

		} else {

			throw new CustomedException("Unable To update details for provided ID : " + customer.getId());
		}

	}

	@Override
	public List<Customer> getAllCustomer() {

		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(long customerId) {

		Optional<Customer> customerFromDb = this.customerRepository.findById(customerId);

		if (customerFromDb.isPresent()) {

			return customerFromDb.get();

		} else {

			throw new CustomedException("Record Not Found with ID : " + customerId);
		}

	}

	@Override
	public void deleteCustomer(long id) {

		Optional<Customer> customerFromDb = this.customerRepository.findById(id);

		if (customerFromDb.isPresent()) {

			this.customerRepository.delete(customerFromDb.get());

		} else {

			throw new CustomedException("Unable to delete ID : " + id);
		}

	}

	@Override
	public List<Customer> getCustomerByName(String name) {

		List<Customer> customerFromDb = this.customerRepository.findByName(name);

		if (customerFromDb != null) {

			return customerFromDb;

		} else {

			throw new CustomedException("Record Not Found with Name : " + name);
		}
	}

	@Override
	public List<Occupation> getCustomerByEarnings(int earnings) {

		List<Occupation> occupationFromDb = this.occupationRepository.findByEarningsGreaterThan(earnings);

		if (occupationFromDb != null) {

			return occupationFromDb;

		} else {

			throw new CustomedException("Record Not Found with Earnings Greater Than : " + earnings);
		}
	}

	@Override
	public List<Object[]> getOccupationByType(String type) {

		List<Object[]> occupationType = this.occupationRepository.getJobByType(type);
		if (occupationType != null) {

			return occupationType;

		} else {

			throw new CustomedException("Record Not Found with Occupation Type : " + type);
		}

	}

	@Override
	public List<Occupation> getCustomerByEarningsLessThan(int earnings) {

		List<Occupation> occupationFromDb = this.occupationRepository.findByEarningsLessThan(earnings);

		if (occupationFromDb != null) {

			return occupationFromDb;

		} else {

			throw new CustomedException("Record Not Found with Earnings Less Than : " + earnings);
		}
	}

	@Override
	public List<Occupation> getCustomerByEarningsEqualsTo(int earnings) {

		List<Occupation> occupationFromDb = this.occupationRepository.findByEarningsEquals(earnings);

		if (occupationFromDb != null) {

			return occupationFromDb;

		} else {

			throw new CustomedException("Record Not Found with Earnings Equals To : " + earnings);
		}
	}

}
