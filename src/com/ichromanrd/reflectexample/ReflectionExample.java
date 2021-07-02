package com.ichromanrd.reflectexample;

import com.ichromanrd.reflectexample.model.Company;
import com.ichromanrd.reflectexample.model.Employee;
import com.ichromanrd.reflectexample.model.Organisation;

public class ReflectionExample {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Company company = new Company();
        company.setLocation("Indonesia");

        Organisation org = new Organisation();
        org.setName("Finance");
        org.setCompany(company);

        Employee employee = new Employee();
        employee.setOrganisation(org);
        employee.setId(1);

        String field = "organisation.company.location";

        FieldExtractor extractor = new FieldExtractor();
        extractor.clazz = Employee.class;
        extractor.field = field;

        String result = (String) extractor.processDefault(employee);
        System.out.println(result);
    }
}
