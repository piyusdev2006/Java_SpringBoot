package com.piyus.GlobalException.services;

import com.piyus.GlobalException.dto.CreateStudentRequestDTO;
import com.piyus.GlobalException.dto.CreateStudentResponseDTO;
import com.piyus.GlobalException.dto.UpdateStudentRequestDTO;
import com.piyus.GlobalException.dto.UpdateStudentResponseDTO;
import com.piyus.GlobalException.entity.Student;
import com.piyus.GlobalException.exception.DuplicateResourceException;
import com.piyus.GlobalException.exception.ResourceNotFoundException;
import com.piyus.GlobalException.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){

        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentRequestDTO){
        Student student = mapToEntity(studentRequestDTO);

        if (emailExist(student)){
            throw new DuplicateResourceException("student with Email" + student.getEmail() + " Already Exist");
        }

        Student studentRes = studentRepository.save(student);

        return mapToDto(studentRes);
    }

    public CreateStudentResponseDTO getStudent(Long id){

        Student studentRes = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id" + id + "Not found"));

        return mapToDto(studentRes);

    }
    // select * from students where id = 1 and deleted = false
    // Spring JPA:-  function ke naam se ek QUERY bna sakta hai aur us naam ke hisab se uska implementation provide karega runtime pe

    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();

        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }


    // select * from students where deleted = false

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq){
//        findById optional return karta hai
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id\" + id + \"Not found"))
                ;




        existingStudent.setName(studentReq.getName());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent =  studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }


    public void deleteStudent(Long id){
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id\" + id + \"Not found"));
        studentRepository.delete(studentToBeDeleted);
    }


    public void deleteStudentSoftly(Long id){
        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id\" + id + \"Not found"));


        studentToBeDeleted.setDeleted(true);

        studentRepository.save(studentToBeDeleted);
    }

    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        // builder design pattern

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDTO mapToDto(Student student){
        CreateStudentResponseDTO responseDTO = new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student saved Successfully");
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());


        return responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDto(Student student){
        UpdateStudentResponseDTO responseDTO = new UpdateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student updated Successfully");
        responseDTO.setUpdatedAt(student.getUpdatedAt());


        return responseDTO;

    }

    private boolean emailExist(Student student){

        return studentRepository.existsByEmail(student.getEmail());
    }
}
