package lk.ijse.shaili.system.Service.custom.impl;



import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Dao.custom.AttendanceDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.AttendanceDTO;
import lk.ijse.shaili.system.Dto.SalaryDTO;
import lk.ijse.shaili.system.Service.custom.AttendanceService;
import lk.ijse.shaili.system.Service.exception.NotFoundException;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;

public class AttendanceServiceImpl implements AttendanceService {

    private final Connection connection;
    private final Converter converter;
    private final AttendanceDAO attendanceDAO;


    public AttendanceServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        attendanceDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.ATTEND);

    }
    @Override
    public SalaryDTO searchSalaryType(String type) throws NotFoundException {
        if (!attendanceDAO.existsbytype(type)){
            throw new NotFoundException("Salary type is Not Found!");
        }
        return converter.fromSalary(attendanceDAO.findSalaryType(type));
    }

    @Override
    public AttendanceDTO searchAttendance(String id, String Month) {
        if (!attendanceDAO.existByPk(id)){
            throw new NotFoundException("This employee is not fonud");
        }
        return converter.fromAttendance(attendanceDAO.findAttendance(id,Month));
    }
}
