package com.genworth.demo.consumer;

import static com.genworth.demo.constants.RestConstant.FIRST;
import static com.genworth.demo.constants.RestConstant.PRIMARYTYPE;
import static com.genworth.demo.constants.RestConstant.QUEUENAME;
import static com.genworth.demo.constants.RestConstant.SECOND;
import static com.genworth.demo.constants.RestConstant.SECONDARYTYPE;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genworth.demo.constants.RestConstant;
import com.genworth.demo.exception.CustomedException;
import com.genworth.demo.jsonmodel.CustomerDTO;
import com.genworth.demo.xmlmodel.Customer;
import com.genworth.demo.xmlmodel.Occupation;

@Component
public class MessageConsumer {

	static RestTemplate restTemplate = new RestTemplate();

	@JmsListener(destination = QUEUENAME)
	public void recieveMessage(String message) {

		CustomerDTO customerDTO = new CustomerDTO();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(new StringReader(message));
			List<Occupation> occupation = new ArrayList<>();
			occupation.add(customer.getPrimary());
			occupation.get(FIRST).setType(PRIMARYTYPE);
			occupation.add(customer.getSecondary());
			occupation.get(SECOND).setType(SECONDARYTYPE);
			customer.setOccupation(occupation);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(customer, customerDTO);
			ObjectMapper customerJson = new ObjectMapper();
			String jsonInString = customerJson.writeValueAsString(customerDTO);
			CustomerDTO obj = customerJson.readValue(jsonInString, CustomerDTO.class);
			createCustomer(obj);

		} catch (Exception e) {
			throw new CustomedException("Unable to Process Message Consumed From Queue With Exception "+ e.getMessage());
		}

	}

	private static void createCustomer(CustomerDTO customer) {
		ResponseEntity<CustomerDTO> result = restTemplate.postForEntity(RestConstant.CREATE_CUSTOMER, customer,
				CustomerDTO.class);

		CustomerDTO acknowledge = result.getBody();
		long id = acknowledge.getId();
		// Need to log this
		// This we need to send to Queue - we can send this as acknowledgement to Queue
		// as success

	}

}
