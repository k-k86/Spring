package com.example.koyanagi_app.service.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.repository.employee.EmployeeMapper;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeMapper employeeMapper;

	/**
	 * 社員情報を全件取得するメソッド<br>
	 * 戻り値は社員情報が全件入ったリスト<br>
	 */
	@Override
	public List<Employee> selectAllEmployee() {

		return employeeMapper.findAllEmployee();
	}

	/**
	 * ページングのための社員情報取得メソッド<br>
	 */
	public Page<Employee> selectAllEmployeePage(Pageable pageable){

		//取得件数
		Integer total = employeeMapper.countAll();

		List<Employee> empList;

		if(total > 0) {

			empList = employeeMapper.pageFindAllEmployee(pageable);

		} else {

			empList = Collections.emptyList();
		}


		return new PageImpl<>(empList, pageable, total);
	}

	/**
	 * 社員IDを条件に社員情報を取得するメソッド<br>
	 * 戻り値は社員情報<br>
	 */
	@Override
	public Employee selectByIdEmployee(Integer empId) {

		return employeeMapper.findByIdEmployee(empId);
	}

	/**
	 * 社員情報を部署IDを条件に取得するメソッド<br>
	 * 引数は部署ID、戻り値は部署IDによって取得した社員情報<br>
	 * 取得できなかった場合はnull<br>
	 * @param String deptId(部署ID)
	 * @return employee(社員情報)
	 */
	public List<Employee> selectEmployeeListByDepartmentId(String deptId) {

		return employeeMapper.findBydepartmentIdEmployee(deptId);
	}

	/**
	 * 複数件選択されている社員IDを条件に社員情報を複数取得するメソッド。<br>
	 * 戻り値は複数の社員情報。<br>
	 * 取得できなかった場合はnull。
	 */
	@Override
	public List<Employee> selectEmployeeByListById(List<Integer> empId){
		//リストの初期化
		List<Employee> empList = new ArrayList<Employee>();

		//チェックボックスの数だけ繰り返す
		for(Integer ids : empId) {

			Employee emp = new Employee();

			emp = employeeMapper.findByIdEmployee(ids);

			empList.add(emp);

		}

		return empList;
	}

	/**
	 * 部署IDや社員名で絞り込み社員情報を取得するメソッド<br>
	 * 引数は部署IDと社員名(部分一致)<br>
	 * 検索条件に引っかからなかった場合はnullを返す<br>
	 * 戻り値は条件によって絞り込まれた社員情報<br>
	 * @param 部署ID<br>
	 * @param 社員名<br>
	 * @return 条件によって取得した社員名<br>
	 */
	public Page<Employee> selectEmployeeBYDepartmentIdOrEmployeeName(String departmentId, String employeeName, Pageable pageable) {

		//プルダウンでどの部署も選択されていないと0が送られてくるため
		if("0".equals(departmentId)) {

			//SELECT文で検索させないためにnullを入れる
			departmentId = null;
		}

		//取得件数
		Integer total = employeeMapper.countByDeptIdAndEmpName(departmentId, employeeName);
		
		List<Employee> empList;

		if(total > 0) {

			empList = employeeMapper.pageFindByDeptIdAndEmpName(departmentId, employeeName, pageable);

		} else {

			empList = Collections.emptyList();
		}

		return new PageImpl<>(empList, pageable, total);
	}

	/**
	 * 社員情報を登録するメソッド<br>
	 * 引数はフォームから送られてきた社員情報
	 */
	public void insertEmployee(Employee employee) {

		employeeMapper.insertEmployee(employee);
	}

	/** 社員情報を更新するメソッド<br>
	 *  戻り値は更新件数<br>
	 */  
	public Integer updateEmployee(Employee employee) {

		Integer result = 0;

		result = employeeMapper.updateEmployee(employee);

		return result;
	}

	/**
	 * 社員情報を削除するメソッド<br>
	 * 戻り値は削除された件数<br>
	 * @param Integer id(社員ID)<br>
	 * @return 削除件数<br>
	 */
	@Override
	public Integer deleteByIdEmployee(List<Integer> ids) {

		Integer result = 0;

		//チェックボックスで選択されてる回数分、削除を実行する
		for(Integer id : ids) {
			result += employeeMapper.deleteByIdEmployee(id);
		}

		return result;
	}
}
