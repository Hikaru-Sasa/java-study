package raisetech.StudentManagement;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper

public interface StudentRepository {

  @Select("SELECT * FROM kadai WHERE name = #{name}")
  student searchByName(String name);

  @Insert("INSERT kadai values(#{name}, #{age})")
  void registerStudent(String name, int age);

  @Update("UPDATE kadai SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name , int age);

  @Delete("DELETE FROM kadai WHERE name = #{name}")
  void deleteStudent(String name);


}
