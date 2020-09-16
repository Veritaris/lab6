package dependencies;


import Collection.*;

import java.io.Serializable;
import java.util.PriorityQueue;

public class Command implements Serializable {

    private PriorityQueue<String> message;
    private StudyGroup studyGroup;
    private String thirdArgument;
    private Long secondArgument;
    private String name;

    public Command(String name, StudyGroup studyGroup){
        this.name = name;
        this.studyGroup = studyGroup;
    }

    public Command(String name, Long secondArgument){
        this.name = name;
        this.secondArgument = secondArgument;
    }

    public Command(String name, String thirdArgument){
        this.name = name;
        this.thirdArgument = thirdArgument;
    }

    public Command(String name){
        this.name = name;
    }

    public void setMessage(PriorityQueue<String> answer){
        this.message = answer;
    }

    public PriorityQueue<String> getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

    public StudyGroup getStudyGroup() {
        return this.studyGroup;
    }

    public Long getSecondArgument() {
        return this.secondArgument;
    }

    public String getThirdArgument() {
        return this.thirdArgument;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format(
                "Command: %s, 1st arg: %s, 2nd arg: %s, 3rd arg: %s",
                getName(), getStudyGroup(), getSecondArgument(), getThirdArgument()
        );
    }
}
