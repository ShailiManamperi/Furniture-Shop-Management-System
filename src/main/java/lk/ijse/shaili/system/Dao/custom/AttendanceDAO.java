package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.Attendance;
import lk.ijse.shaili.system.Entity.Salary;

import java.util.List;
import java.util.Optional;

public interface AttendanceDAO extends SuperDAO {
    Attendance save(Attendance entity) throws ConstraintViolationException;

    Attendance update(Attendance entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Attendance> findAll();

    Optional<Attendance> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

    public Salary findSalaryType(String type);

    public Attendance findAttendance(String eid,String month) ;

    boolean existsbytype(String type);

}
