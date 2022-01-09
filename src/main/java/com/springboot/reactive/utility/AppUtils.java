package com.springboot.reactive.utility;

import com.springboot.reactive.dto.EmployeeDto;
import com.springboot.reactive.entity.Employee;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static Employee dtoToEntity(EmployeeDto employeeDto){
        Employee emp=new Employee();
        BeanUtils.copyProperties(employeeDto,emp);
        return  emp;
    }

    public static EmployeeDto entityToDto(Employee employee){
        EmployeeDto dto=new EmployeeDto();
        BeanUtils.copyProperties(employee,dto);
        return  dto;
    }
}
