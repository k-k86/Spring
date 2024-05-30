package com.example.koyanagi_app.service.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.koyanagi_app.entity.Department;
import com.example.koyanagi_app.entity.Employee;
import com.example.koyanagi_app.repository.department.DepartmentMapper;
import com.example.koyanagi_app.repository.employee.EmployeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * 部署サービスを提供するインターフェースを実装。<br>
 * 部署のサービス処理を行う。<br>
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	//マッパーをコンテナから取得
	private final DepartmentMapper departmentMapper;

	private final EmployeeMapper employeeMapper;

	/**
	 * 部署情報を全件取得するメソッド<br>
	 * 戻り値は部署情報が全件入ったリスト<br>
	 */
	@Override
	public List<Department> selectDepartmentAll() {

		return departmentMapper.findAllDepartment();
	}
	
	/**
	 * 部署情報を無所属の部署以外を取得するメソッド<br>
	 * 戻り値は無所属以外の部署情報<br>
	 */
	public List<Department> selectDepartmentByNot000() {
		
		return departmentMapper.findByNot000Department();
	}

	/**
	 * 引数で渡された部署名(あいまい検索)で部署情報を取得するメソッド<br>
	 * 戻り値は部署名で取得した部署情報<br>
	 * 取得できなかった場合はNULL
	 * @param 部署名
	 * @return Department
	 */
	@Override
	public List<Department> selectDepartmentByAmbiguousName(String id) {

		List<Department> dept = departmentMapper.findByNameAmbiguousDepartment(id);

		return dept;
	}

	/**
	 * 部署IDを条件に取得するメソッド<br>
	 * 戻り値は単一のデータ(部署情報)<br>
	 * 取得できなかった場合はnull<br>
	 */
	public Department selectDepartmentById(String id) {

		return departmentMapper.findByIdDepartment(id);
	}

	/**
	 * 部署IDを条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	public List<Department> selectDepartmentByListId(List<String> id){
		//リストの初期化
		List<Department> deptList = new ArrayList<Department>();

		//チェックボックスの数だけ繰り返す
		for(String ids : id) {

			Department dept = new Department();
			dept = departmentMapper.findByIdDepartment(ids);

			deptList.add(dept);

		}

		return deptList;
	}

	/**
	 * 部署名を条件に取得するメソッド。<br>
	 * 戻り値は複数の部署情報。<br>
	 * 取得できなかった場合はnull。
	 */
	public Department selectDepartmentByName(String name){

		return departmentMapper.findByNameDepartment(name);
	}

	/**
	 * 引数で渡された部署情報を登録するメソッド。<br>
	 */
	@Override
	public void insertDepartment(Department dept) {

		departmentMapper.createDepartment(dept);
	}

	/**
	 * 引数で渡された部署IDで部署を検索し、
	 * その部署IDに所属している社員情報を取得するメソッド。<br>
	 */
	public List<Department> selectAllDepartmentAndEmployee(String id) {

		return departmentMapper.findAllDepartmentAndEmployee(id);
	}

	/**
	 * 部署情報を更新するメソッド<br>
	 * 戻り値は更新件数<br>
	 * 引数は更新する部署情報<br>
	 */
	public void departmentUpdate(Department department){

		departmentMapper.updateDepartment(department);
	}

	//	/**
	//	 * 部署情報を削除するメソッド<br>
	//	 * 引数は部署名<br>
	//	 * 戻り値は削除件数<br>
	//	 */
	public Integer deleteDepartmentById(List<String> id) {

		Integer result = 0;

		//チェックボックスで選択されている数分、繰り返す
		for(String ids : id) {

			//削除される部署に所属している社員を取得
			List<Employee> empList = employeeMapper.findBydepartmentIdEmployee(ids);
			
			//
			for(Employee emp : empList) {


				updateEmployeeByDeleteDepartment(emp);

			}

			result += departmentMapper.deleteDepartmentById(ids);
		}

		return result;
	}

	/**
	 * 部署情報の条件に対して部署IDで取得できた場合と、部署名で取得できた場合と、
	 * 両方取得できた場合の判別を提供するサービス<br>
	 * 引数はフォームから取得したIDと名前<br>
	 * 戻り値はその条件にあった文字列<br>
	 * <ul>●戻り値の種類
	 * <li>部署IDと部署名で取得：IdAndNameOut</li>
	 * <li>部署IDで取得：IdOut</li>
	 * <li>部署名で取得：NameOut</li>
	 * </ul>
	 */
	@Override
	public String departmentIdAndNameCheck(String id , String name) {

		String departmentInputCheck = null;

		//IDを条件に部署情報を取得
		Department deptIdList = departmentMapper.findByIdDepartment(id);

		//部署名を条件に部署情報を取得
		Department deptNameList = departmentMapper.findByNameDepartment(name);

		//部署IDと部署名を条件で部署情報が取得できたとき
		if( deptIdList != null && deptNameList != null ) {

			return  departmentInputCheck ="IdAndNameOut";

			//部署IDを条件で取得できたとき
		} else if( deptIdList != null ) {

			return departmentInputCheck = "IdOut";

			//部署名を条件で取得できたとき
		} else if( deptNameList != null ) {

			return departmentInputCheck = "NameOut";
		}

		return departmentInputCheck;
	}

	/**
	 * 部署情報を削除する際に無所属になる社員情報の部署IDを所属なしに置換するメソッド<br>
	 * 引数は所属している部署を削除される社員情報<br>
	 */
	public void updateEmployeeByDeleteDepartment(Employee emp) {

		Employee newEmp = new Employee();
		newEmp.setEmployeeId( emp.getEmployeeId() );
		newEmp.setEmployeeName( emp.getEmployeeName() );
		newEmp.setEmployeeNameKana( emp.getEmployeeNameKana() );
		newEmp.setEmployeeAge( emp.getEmployeeAge() );
		newEmp.setEmployeeAddress( emp.getEmployeeAddress() );
		newEmp.setEmployeeMail( emp.getEmployeeMail() );
		newEmp.setEmployeePhone( emp.getEmployeePhone() );
		newEmp.setEmployeeHiredate( emp.getEmployeeHiredate() );
		newEmp.setDepartmentId( "000" );
		employeeMapper.updateEmployee(newEmp);

	}

}
