package Collection;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Long studentsCount;
    private Integer expelledStudents;
    private Integer shouldBeExpelled;
    private Semester semester;
    private Person groupAdmin;
    Random random = new Random();

    public StudyGroup (String name, Semester semester, Coordinates coordinates, Long studentsCount, Person groupAdmin, Integer shouldBeExpelled, Integer expelledStudents){
        this.name = name;
        this.semester = semester;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.groupAdmin = groupAdmin;
        this.shouldBeExpelled = shouldBeExpelled;
        this.expelledStudents = expelledStudents;
        creationDate = LocalDateTime.now();
        id = random.nextLong();
    }


    @Override
    public int compareTo(StudyGroup studyGroup) {
        int result = this.name.compareTo(studyGroup.name);
        if (result==0){
            if (this.studentsCount - studyGroup.studentsCount>0) return 1;
            else if (this.studentsCount - studyGroup.studentsCount<0) return -1;
            else return 0;
        }
        else return result;
    }

    public Long getId(){return this.id;}
    public void setId(Long id){this.id = id;}
    public String getName(){return this.name;}
    public Coordinates getCoordinates(){return this.coordinates;}
    public Long getStudentsCount(){return  this.studentsCount;}
    public Integer getExpelledStudents(){return expelledStudents;}
    public Integer getShouldBeExpelled(){return shouldBeExpelled;}
    public Person getGroupAdmin(){return groupAdmin;}
    public Semester getSemester(){return semester;}
    public void setGroupAdmin(){this.groupAdmin = groupAdmin;}

    @Override
    public String toString()
    {
        return this.name + " (" + this.id + "), " + "semester: " + this.semester.getTittle() + ", coordinates: ("
                + coordinates.getX() + ", " + coordinates.getY() + "), students count: " + this.studentsCount +
                ", should be expelled students: " + this.shouldBeExpelled + ", expelled students: " +
                this.expelledStudents + ", group admin: " + groupAdmin.getName() + ", his height: " +
                groupAdmin.getHeight() + ", his weight: " + groupAdmin.getWeight() + ", he from " +
                groupAdmin.getNationality().getTittle() + ". Creation date: " + creationDate;
    }
}
