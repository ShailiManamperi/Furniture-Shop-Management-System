package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.AttendanceDTO;
import lk.ijse.shaili.system.Dto.SalaryDTO;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.NotFoundException;

public interface AttendanceService extends SuperService {
    public SalaryDTO searchSalaryType(String type) throws NotFoundException;

    public AttendanceDTO searchAttendance(String Employee, String Month);
}
