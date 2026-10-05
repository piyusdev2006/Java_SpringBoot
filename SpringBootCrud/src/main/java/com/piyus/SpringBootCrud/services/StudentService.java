package com.piyus.SpringBootCrud.services;

import com.piyus.SpringBootCrud.dto.CreateStudentRequestDTO;
import com.piyus.SpringBootCrud.dto.CreateStudentResponseDTO;
import com.piyus.SpringBootCrud.dto.UpdateStudentRequestDTO;
import com.piyus.SpringBootCrud.dto.UpdateStudentResponseDTO;
import com.piyus.SpringBootCrud.entity.Student;
import com.piyus.SpringBootCrud.repository.StudentRepository;
import org.springframework.stereotype.Service;

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

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student studentRes = studentRepository.save(student);

        return mapToDto(studentRes);
    }

    public CreateStudentResponseDTO getStudent(Long id){
//        findById optional return karta hai
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentRes.isPresent()){
            return mapToDto(studentRes.get());
        }
        return null;
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
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());

        Student savedStudent =  studentRepository.save(studentToSave);

        return mapToUpdateDto(savedStudent);
    }


    public Boolean deleteStudent(Long id){
        Boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) return false;

        studentRepository.deleteById(id);
        return true;
    }


    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()){
            return false;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);

        studentRepository.save(studentToSave);

        return true;
    }

    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());

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
}
