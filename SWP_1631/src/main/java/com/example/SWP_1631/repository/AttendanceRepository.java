package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    @Query("SELECT u FROM Attendance u WHERE u.studentId.KinderId= :id ")
    public List<Attendance> getAllAttendanceByIdKinder(@Param("id") Integer id);

    @Modifying
    @Query("DELETE  FROM Attendance s WHERE s.studentId.KinderId =:id")
    void deleteAttendanceByIdKinder(@Param("id") Integer id);
}
